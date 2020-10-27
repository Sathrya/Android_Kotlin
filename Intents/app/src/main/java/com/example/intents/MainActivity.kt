package com.example.intents

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.tapadoo.alerter.Alerter

class MainActivity : AppCompatActivity() {
    lateinit var img: ImageView
    lateinit var photo: Button
    lateinit var email: Button
    lateinit var share: Button
    lateinit var location: Button
    lateinit var msg: EditText
    lateinit var name: EditText
    val email_sub: String = "Birthday Wish"
    val url:String="https://www.greetingsisland.com/cards/birthday"

    val REQUEST_IMAGE_CAPTURE = 1
    val IMAGE_PICK_CODE = 1000
    val PERMISSION_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name=findViewById(R.id.title)
        img = findViewById(R.id.snap)
        photo = findViewById(R.id.shutterButton)
        email = findViewById(R.id.email)
        msg = findViewById(R.id.wish)
        share = findViewById(R.id.share)
        location = findViewById(R.id.location)

        photo.setOnClickListener {
            val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
            val alert=AlertDialog.Builder(this)
            alert.setTitle("Change Profile Photo")
            alert.setItems(options){dialog, which->
                when(which){
                    0->{
                        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    }
                    1->{
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                                //permission denied
                                val permission= arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                                requestPermissions(permission,PERMISSION_CODE)
                            }
                            else{
                                //permission granted
                                pickimagefromgallery()
                            }
                        }
                    }
                    2->{
                        dialog.dismiss()}
                }
            }
            val dialog = alert.create()
            dialog.show()
        }
        email.setOnClickListener {
            val email_body = msg.text.toString().trim()
            sendMail(email_sub, email_body)
        }
        share.setOnClickListener {
            val uwish=msg.text.toString()
            val shareIntent:Intent= Intent(Intent.ACTION_SEND).apply {
                type = "*/*"
                putExtra(Intent.EXTRA_TEXT,uwish)
            }
            if (shareIntent.resolveActivity(packageManager) != null) {
                startActivity(shareIntent)
            }
        }
        location.setOnClickListener {
            Log.d("Web","Button clicked")
            val webpage: Uri = Uri.parse(url)
            val webintent = Intent(Intent.ACTION_VIEW,webpage)
            startActivity(webintent)
            if (webintent.resolveActivity(packageManager) != null) {
                startActivity(webintent)
            }
        }
        name.setOnFocusChangeListener { view, b ->
            if(!b){
                Alerter.Companion.create(this)
                    .setTitle("Notification")
                    .setText("Name Entered")
                    .setIcon(R.drawable.done)
                    .setBackgroundColorRes(R.color.black)
                    .setDuration(4000)
                    .show()
            }
        }
        msg.setOnFocusChangeListener { view, b ->
            if(!b){
                Alerter.Companion.create(this)
                    .setTitle("Notification")
                    .setText("Wish Entered")
                    .setIcon(R.drawable.done)
                    .setBackgroundColorRes(R.color.black)
                    .setDuration(4000)
                    .show()
            }
        }
    }
    private fun sendMail(emailSub: String, emailBody: String) {
        val emailIntent: Intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_SUBJECT, emailSub)
            putExtra(Intent.EXTRA_TEXT, emailBody)
        }
        if (emailIntent.resolveActivity(packageManager) != null) {
            startActivity(emailIntent)
        }
    }

    private fun pickimagefromgallery() {
        val photointent:Intent=Intent(Intent.ACTION_PICK)
        photointent.type="image/*"
        startActivityForResult(photointent,IMAGE_PICK_CODE)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE->{
                if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    pickimagefromgallery()
                }
                else{
                    Toast.makeText(this,"Permission Required",Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode== RESULT_OK){
                val imageBitmap = data?.extras?.get("data") as? Bitmap
                img.setImageBitmap(imageBitmap)
            }
            else{
                if (data != null) {
                    img.setImageURI(data.data)
                }
            }
    }

}