package com.example.navigation

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.navigation.database.UserViewModel
import com.example.navigation.model.UsersInfo
import kotlinx.android.synthetic.main.fragment_post.view.*


class PostFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_post, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.cancelBtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_postFragment_to_mainFragment)
        }
        view.submitbtn.setOnClickListener {
            val newFName=view.fname.text.toString()
            val newLname=view.lname.text.toString()
            val newEmail=view.newemail.text.toString()
            val newGender=view.newgender.text.toString()
            val newStatus=view.newstatus.text.toString()
            insertDataToDatabase(view,newFName,newLname,newEmail,newGender,newStatus)
    }

        //Deleting User
        return view
    }

    //Insertion Into User Db
    private fun insertDataToDatabase(view: View,fname:String,lname:String,email:String,gen:String,stat:String){
        if(validInput(fname,lname,email,gen,stat)){
            val user= UsersInfo(0,fname,lname,email,gen,stat)
            mUserViewModel.addUser(user)
            Navigation.findNavController(view).navigate(R.id.action_postFragment_to_mainFragment)
            Toast.makeText(requireContext(), "User Registered Sucessfully", Toast.LENGTH_LONG).show()

        }
        else{
            Toast.makeText(requireContext(), "None of the fields can be empty", Toast.LENGTH_LONG).show()
        }
    }

    //Validating the Inputs
    private fun validInput(fname:String,lname:String,email:String,gen:String,stat:String):Boolean{
        return !(TextUtils.isEmpty(fname)&&TextUtils.isEmpty(lname)&&TextUtils.isEmpty(email)&&TextUtils.isEmpty(gen)&&TextUtils.isEmpty(stat))
    }
}