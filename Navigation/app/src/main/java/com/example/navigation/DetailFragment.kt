package com.example.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.navigation.database.UserViewModel
import com.example.navigation.model.UsersInfo
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*


class DetailFragment : Fragment() {
   // lateinit var madapter:MyAdapter
    lateinit var mUserViewModel: UserViewModel
    private val args:DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_detail, container, false)
      //  madapter= MyAdapter()
        val incomingfirstName=args.cfname
        val incominglastName=args.clname
        val incomingEmail=args.email
        val incomingGen=args.gender
        val incomingStat=args.status

        //Setting Feild Values
        view.editfname.setText(incomingfirstName, TextView.BufferType.EDITABLE)
        view.editlname.setText(incominglastName, TextView.BufferType.EDITABLE)
        view.editemail.setText(incomingEmail, TextView.BufferType.EDITABLE)
        view.editgender.setText(incomingGen, TextView.BufferType.EDITABLE)
        view.editstatus.setText(incomingStat, TextView.BufferType.EDITABLE)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.submit.setOnClickListener {
            if (editfname.text.toString()=="" || editlname.text.toString()=="" ||editemail.text.toString()=="" || editgender.text.toString()=="" || editgender.text.toString()==""){
                Toast.makeText(context, "None of the Fields must be Empty", Toast.LENGTH_SHORT).show()
            }
            else{
                val editedfName=editfname.text.toString()
                val editedlName=editlname.text.toString()
                val editedEmail=editemail.text.toString()
                val editedGender=editgender.text.toString()
                val editedStatus=editstatus.text.toString()
                val editedUser=UsersInfo(args.id,editedfName,editedlName,editedEmail,editedGender,editedStatus)
                mUserViewModel.updateUser(editedUser)
                Navigation.findNavController(view).navigate(R.id.detailtocard)
                Toast.makeText(requireContext(), "Details updated Sucessfully", Toast.LENGTH_LONG).show()
            }
        }
        view.deleteUser.setOnClickListener{
            val deletedUser=UsersInfo(args.id,args.cfname,args.clname,args.email,args.gender,args.status)
            val builder=AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes"){
                _,_->mUserViewModel.deleteUser(deletedUser)
                Toast.makeText(requireContext(), "User Deleted Sucessfully", Toast.LENGTH_LONG).show()
                Navigation.findNavController(view).navigate(R.id.detailtocard)
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${args.cfname}?")
            builder.setMessage("Are you sure you want to delete ${args.cfname}?")
            builder.create().show()
        }
        //Invoking Patch Call
       /* view.submit.setOnClickListener {
            if (editname.text.toString()=="" || editemail.text.toString()=="" || editgender.text.toString()=="" || editgender.text.toString()==""){
               Toast.makeText(context, "None of the Fields must be Empty", Toast.LENGTH_SHORT).show()
            }
            else{
                val editedName=editname.text.toString()
                val editedEmail=editemail.text.toString()
                val editedGender=editgender.text.toString()
                val editedStatus=editstatus.text.toString()

                val userdata=Data("",editedEmail,editedGender,args.id,editedName,editedStatus,"")
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
                Navigation.findNavController(view).navigate(R.id.detailtocard)
            }
        }*/
        return view
    }
}



