package com.svitlanamozharovska.agronomist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.svitlanamozharovska.agronomist.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.ArrayList

class MainFragment :BaseFragment<MainFragmentBinding,MainViewModel>()  {
    override fun getLayoutId(): Int = R.layout.main_fragment

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data= arrayListOf("hgmm", "hgjmn", "gfhj")

        viewModel.callWebService()

        viewModel.visibility.observe(this, Observer {
            //mainRV.adapter?.notifyItemRangeInserted(0,1)
            Log.d("retrofit", "visibility")
            mainRV.apply {
                layoutManager = LinearLayoutManager(activity)
                if(viewModel.accountMutableList.value != null){
                    adapter = MainRVAdapter(viewModel.accountMutableList)
                }

            }
        })





    }

}