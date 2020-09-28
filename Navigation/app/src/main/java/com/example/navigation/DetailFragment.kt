package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.user_list.*

class DetailFragment : Fragment() {

    val args:DetailFragment by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_detail, container, false)
      /*  val ename=
        view.editname.setText(ename,TextView.BufferType.EDITABLE)*/
       /* name=view.findViewById(R.id.editname)
        email=view.findViewById(R.id.editemail)
        gender=view.findViewById(R.id.editgender)
        status=view.findViewById(R.id.editstatus)
        name.setText()*/
        return view
    }


}



