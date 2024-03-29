package com.example.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.navigation.databinding.FragmentDetailBinding
import com.example.navigation.model.ApiInterface
import com.example.navigation.model.Data
import com.example.navigation.model.RetroFitInstance
import com.example.navigation.model.Users
import kotlinx.android.synthetic.main.fragment_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {
    private var binding:FragmentDetailBinding?=null
    lateinit var madapter:MyAdapter
    private val args:DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentDetailBinding.inflate(inflater,container,false)
        val view=binding?.root
        madapter= MyAdapter()
        val incomingName=args.name
        val incomingEmail=args.email
        val incomingGen=args.gender
        val incomingStat=args.status

        //Setting Feild Values
        binding?.editname?.setText(incomingName, TextView.BufferType.EDITABLE)
        binding?.editemail?.setText(incomingEmail, TextView.BufferType.EDITABLE)
        binding?.editgender?.setText(incomingGen, TextView.BufferType.EDITABLE)
        binding?.editstatus?.setText(incomingStat, TextView.BufferType.EDITABLE)

        //Invoking Patch Call
        binding?.submit?.setOnClickListener {
            if (binding!!.editname.text.toString()=="" || binding!!.editemail.text.toString()=="" || binding!!.editgender.text.toString()=="" || binding!!.editgender.text.toString()==""){
               Toast.makeText(context, "None of the Fields must be Empty", Toast.LENGTH_SHORT).show()
            }
            else{
                val editedName=binding!!.editname.text.toString()
                val editedEmail=binding!!.editemail.text.toString()
                val editedGender=binding!!.editgender.text.toString()
                val editedStatus=binding!!.editstatus.text.toString()

                val userdata=Data("",editedEmail,editedGender,args.id.toString(),editedName,editedStatus,"",null,null)
                val patchservice: ApiInterface = RetroFitInstance().getRetrofitInstance().create(ApiInterface::class.java)
                val patchcall: Call<Data> = patchservice.updateuser(args.id,userdata)
                patchcall.enqueue(object :Callback<Data>{
                    override fun onResponse(call: Call<Data>, response: Response<Data>) {
                        Log.d("Patch","${response.code()}")
                        Log.d("Patch","$response")
                        //Refreshing the UI
                        val server: ApiInterface = RetroFitInstance().getRetrofitInstance().create(ApiInterface::class.java)
                        val getcall: Call<Users> = server.getUserData()
                        getcall.enqueue(object : Callback<Users> {
                            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                                Log.d("Repo", "${ response.code()}")
                                if(response.isSuccessful)
                                {
                                    madapter.setusers(response.body()!!.data)
                                }
                                else{
                                    Log.d("Application", "404 Not found")
                                }
                            }
                            override fun onFailure(call: Call<Users>, t: Throwable) {
                                Log.d("Application", "$t")
                            }
                        })
                    }
                    override fun onFailure(call: Call<Data>, t: Throwable) {
                        Log.d("Patch","$t")
                    }
                })
                if (view != null) {
                    Navigation.findNavController(view).navigate(R.id.detailtocard)
                }
            }

        }
        return view
    }
}



