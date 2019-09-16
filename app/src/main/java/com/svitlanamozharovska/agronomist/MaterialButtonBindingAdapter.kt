package com.svitlanamozharovska.agronomist

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton

class MaterialButtonBindingAdapter {
    companion object CurrencyBindingAdapter {
        @BindingAdapter("mapListener")
        @JvmStatic
        fun bindCurrency(view: MaterialButton, clickListener: View.OnClickListener) {
            view.setOnClickListener {
                view.findNavController().navigate(R.id.action_mainFragment_to_mapFragment)
            }
        }

        @BindingAdapter("listListener")
        @JvmStatic
        fun bind(view: MaterialButton, clickListener: View.OnClickListener) {
            view.setOnClickListener {
                view.findNavController().popBackStack()
            }
        }


    }
}