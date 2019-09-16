package com.svitlanamozharovska.agronomist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_rv_elements.view.*
import java.text.SimpleDateFormat
import java.util.*

class MainRVAdapter(private var dataList: MutableLiveData<List<AccountData>>) :
    RecyclerView.Adapter<MainRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutView =
            LayoutInflater.from(parent.context).inflate(R.layout.main_rv_elements, parent, false)
        return ViewHolder(itemLayoutView)
    }

    override fun getItemCount(): Int = dataList.value!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList.value!![position]
        var imageFirst:String = ""
        var imageSecond:String = ""
        var imageThird:String = ""

        data.imageDTOList.forEach { imageDTO ->
            when {
                imageDTO.position == "FIRST" && imageDTO.type == "FULL_FORMAT" -> imageFirst = imageDTO.imageUrl
                imageDTO.position == "SECOND" && imageDTO.type == "FULL_FORMAT" -> imageSecond = imageDTO.imageUrl
                imageDTO.position == "THIRD" && imageDTO.type == "FULL_FORMAT" -> imageThird = imageDTO.imageUrl
            }
        }

        holder.bind(
            data.creatorName,
            data.address,
            "#${data.plantPredecessor} #${data.lastAgrotechnicalAction}",
            data.description,
            imageFirst,
            imageSecond,
            imageThird
            //data.createDateUNIX
        )
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        private var accountName: TextView = itemLayoutView.accountNameTV
        //private var createdTime: TextView = itemLayoutView.timeTV
        private var locationTV: TextView = itemLayoutView.locationTV
        private var hashesTV: TextView = itemLayoutView.hashTV
        private var descriptionTV: TextView = itemLayoutView.descriptionTV
        private var avatarIV: TextView = itemLayoutView.avatarIV
        private var imageFirstIV: ImageView = itemLayoutView.imageView1
        private var imageSecondIV: ImageView = itemLayoutView.imageView2
        private var imageThirdIV: ImageView = itemLayoutView.imageView3

        @SuppressLint("SimpleDateFormat")
        fun bind(
            name: String,
            location: String,
            hash: String,
            description: String,
            imageFirst: String,
            imageSecond: String,
            imageThird: String
            //time: Long
        ) {
            accountName.text = name
            locationTV.text = location
            hashesTV.text = hash
            descriptionTV.text = description
            avatarIV.text = name[0].toString()

            /*val date = Date(time*1000)
            val sdf = SimpleDateFormat("dd-MM HH:mm")
            sdf.timeZone = TimeZone.getTimeZone("GMT+3")
            createdTime.text = sdf.format(date)*/
            Picasso.get().load("http://app.crazyagro.com/admin/img/$imageFirst").error(R.color.colorWhite).into(imageFirstIV)
            Picasso.get().load("http://app.crazyagro.com/admin/img/$imageSecond").error(R.color.colorWhite).into(imageSecondIV)
            Picasso.get().load("http://app.crazyagro.com/admin/img/$imageThird").error(R.color.colorWhite).into(imageThirdIV)

        }

    }
}