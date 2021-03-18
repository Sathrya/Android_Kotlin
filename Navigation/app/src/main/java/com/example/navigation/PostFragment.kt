package com.example.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navigation.databinding.FragmentPostBinding
import com.example.navigation.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostFragment : Fragment() {
    private var binding:FragmentPostBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentPostBinding.inflate(inflater,container,false)
        val view= binding!!.root
        binding!!.cancelbtn.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_postFragment_to_mainFragment)}

        binding!!.submitbtn.setOnClickListener {

            val newName=binding!!.newname.text.toString()
            val newEmail=binding!!.newemail.text.toString()
            val newGender=binding!!.newgender.text.toString()
            val newStatus=binding!!.newstatus.text.toString()
            val service: ApiInterface = RetroFitInstance().getRetrofitInstance().create(ApiInterface::class.java)
            val postcall: Call<Users> = service.postUserData(newName,newEmail,newGender,newStatus)

            postcall.enqueue(object :Callback<Users>{
                override fun onResponse(call: Call<Users>, response: Response<Users>) {
                    if (response.isSuccessful){
                        Log.d("Submit","Buton Clicked")
                        Log.d("tag","$response")
                    }
                }

                override fun onFailure(call: Call<Users>, t: Throwable) {
                    Log.d("FAilure","Oops")
                }

            })
            Navigation.findNavController(view).navigate(R.id.action_postFragment_to_mainFragment)
        }
        return view
    }
}