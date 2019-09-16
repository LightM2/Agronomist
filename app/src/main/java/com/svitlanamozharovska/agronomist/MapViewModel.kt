package com.svitlanamozharovska.agronomist

import android.util.Log
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapViewModel: BaseViewModel(){
    fun onClick() {
        Log.d("Filter", "click")
    }

    var accountMutableList = MutableLiveData<List<AccountData>>()
    var visibility = MutableLiveData<Int>()


    fun callWebService() {

        val service =
            RetrofitClientInstance.retrofitInstance?.create(AccountService::class.java)
        val call = service?.getAllAccounts()
        call?.enqueue(object : Callback<List<AccountData>> {
            override fun onResponse(
                call: Call<List<AccountData>>,
                response: Response<List<AccountData>>
            ) {
                visibility.value = ProgressBar.VISIBLE
                Log.d("retrofit", "onResponse")
                val body = response.body()
                if (body != null) {
                    Log.d("retrofit", body.size.toString())
                    Log.d("retrofit", body[0].creatorName)
                    accountMutableList.value = body
                    Log.d("retrofit", accountMutableList.value!![0].creatorName)
                }
                visibility.value = ProgressBar.INVISIBLE




            }

            override fun onFailure(call: Call<List<AccountData>>, t: Throwable) {
                Log.d("retrofit", "error, onFailure $t")
            }

        })
    }

}