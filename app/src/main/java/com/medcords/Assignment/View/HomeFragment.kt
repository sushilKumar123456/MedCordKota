package com.medcords.Assignment.View

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.geekysingh.core.architecture.domain.entity.Data
import com.kotlin.assigmnet.R
import com.medcords.Assignment.Adapter.CustomAdapter
import com.medcords.Assignment.Model.SearchViewModel
import com.medcords.Assignment.Model.ResponseData
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(){
    private lateinit var viewModel: SearchViewModel
    private lateinit var ids:String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        //adding a layoutmanager
        recyclerView.layoutManager = PeekingLinearLayoutManager(context, HORIZONTAL, false)


        //crating an arraylist to store users using the data class user
        val users = ArrayList<ResponseData>()

        //adding some dummy data to the list
        users.add(ResponseData("ABCD", "Ranchi Jharkhand"))
        users.add(ResponseData("ABCD", "Ranchi Jharkhand"))
        users.add(ResponseData("ABCD ", "Ranchi Jharkhand"))
        users.add(ResponseData("ABCD ", "Ranchi Jharkhand"))
        users.add(ResponseData("ABCD ", "Ranchi Jharkhand"))
        users.add(ResponseData("ABCD ", "Ranchi Jharkhand"))
        users.add(ResponseData("ABCD ", "Ranchi Jharkhand"))
        users.add(ResponseData("ABCD ", "Ranchi Jharkhand"))

        //creating our adapter
        val adapter = CustomAdapter(users)

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter

       button1.setOnClickListener(View.OnClickListener {
           viewModel.searchVolumes("B1");
           progressBar.visibility=View.VISIBLE
       })
        button2.setOnClickListener(View.OnClickListener {
            viewModel.searchVolumes("B2");
            progressBar.visibility=View.VISIBLE
        })
        button3.setOnClickListener(View.OnClickListener {
            viewModel.searchVolumes("B3");
            progressBar.visibility=View.VISIBLE
        })
        button4.setOnClickListener(View.OnClickListener {
            viewModel.searchVolumes("B4");
            progressBar.visibility=View.VISIBLE
        })


        viewModel =
            ViewModelProviders.of(this).get(SearchViewModel::class.java)
        viewModel.init()
        viewModel.getVolumesResponseLiveData()
            .observe(this, Observer<Any?> { volumesResponse  ->
                if (volumesResponse != null) {
                   var data= volumesResponse as Data

                    Toast.makeText(context, "data.rstr = "+data.rstr, Toast.LENGTH_LONG).show()

                }
                else
                {
                    Toast.makeText(context, "Something went worng!", Toast.LENGTH_LONG).show()

                }
                progressBar.visibility=View.GONE

            })
    }










}
class PeekingLinearLayoutManager : LinearLayoutManager {
    @JvmOverloads
    constructor(context: Context?, @RecyclerView.Orientation orientation: Int = RecyclerView.HORIZONTAL, reverseLayout: Boolean = false) : super(context, orientation, reverseLayout)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun generateDefaultLayoutParams() =
        scaledLayoutParams(super.generateDefaultLayoutParams())

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams?) =
        scaledLayoutParams(super.generateLayoutParams(lp))

    override fun generateLayoutParams(c: Context?, attrs: AttributeSet?) =
        scaledLayoutParams(super.generateLayoutParams(c, attrs))

    private fun scaledLayoutParams(layoutParams: RecyclerView.LayoutParams) =
        layoutParams.apply {
            when(orientation) {
                HORIZONTAL -> width = (horizontalSpace * ratio).toInt()
                VERTICAL -> height = (verticalSpace * ratioHeight).toInt()
            }
        }

    private val horizontalSpace get() = width - paddingStart - paddingEnd

    private val verticalSpace get() = height - paddingTop - paddingBottom

    private val ratio = 0.7f
    private val ratioHeight = 0.3f // change to 0.7f for 70%
}