package com.example.audioplayer

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.audioplayer.model.MusicFiles
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),SeekBar.OnSeekBarChangeListener{
    private lateinit var rcview:RecyclerView
    private lateinit var myAdapter: SongsAdapter
    private var btn_click=0
    private val Request_code=1
    var myservice:MediaPlaybackService?=null
    var isbound=false
    companion object Test{
        var musicFiles= ArrayList<MusicFiles>()
        lateinit var play_pause:FloatingActionButton
         lateinit var nextsong:ImageView
         lateinit var prevsong:ImageView
         lateinit var current:TextView
         lateinit var total:TextView
         lateinit var progressBar: SeekBar
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permission()
        setContentView(R.layout.activity_main)
        current=findViewById(R.id.current_time)
        total=findViewById(R.id.total_time)
        nextsong=findViewById(R.id.next)
        prevsong=findViewById(R.id.previous)
        play_pause=findViewById(R.id.play_pause)
        progressBar=findViewById(R.id.mSeekBarTime)
        play_pause.setOnClickListener {
            if (btn_click==0){
                val intent= Intent(this,MediaPlaybackService::class.java)
                bindService(intent,myConnection,Context.BIND_AUTO_CREATE)
                startService(intent)
                val time=myservice?.initialiseSeekBar()
                total.text=time.toString()
                play_pause.setImageResource(R.drawable.pause)
                btn_click++
            }
            else{
                val number=1
                val intent=Intent(this,MediaPlaybackService::class.java)
                intent.putExtra("number",number)
                startService(intent)
            }
        }
        musicFiles=getAll(this)
        rcview=findViewById(R.id.songs_container)
        rcview.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        myAdapter= SongsAdapter(musicFiles)
        rcview.adapter=myAdapter
        //progressBar.setOnSeekBarChangeListener(this)

        prevsong.setOnClickListener {
            val number=2
            val intent=Intent(this,MediaPlaybackService::class.java)
            intent.putExtra("number",number)
            startService(intent)
        }

        nextsong.setOnClickListener {
            val number=3
            val intent=Intent(this,MediaPlaybackService::class.java)
            intent.putExtra("number",number)
            startService(intent)
        }
        progressBar.setOnSeekBarChangeListener(this)
    }

    private val myConnection= object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder= p1 as MediaPlaybackService.MyLocalBinder
            myservice=binder.getService()
            isbound=true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isbound=false
        }

    }

    private fun getAll(context: Context):ArrayList<MusicFiles> {
        val tempList= ArrayList<MusicFiles>()
        val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DATA)
        val cursor: Cursor?=this.contentResolver.query(uri,projection,null,null,null)
        if (cursor != null){
            while (cursor.moveToNext()){
                val title=cursor.getString(0)
                val artist=cursor.getString(1)
                val path=cursor.getString(2)
                val song=MusicFiles(title,artist,path)
                Log.e("Test",artist)
                tempList.add(song)
            }
            cursor.close()
        }
        return tempList
    }

    private fun  permission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),Request_code)
        }
        else{
            Toast.makeText(this,"Permission Granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Request_code){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission Granted", Toast.LENGTH_SHORT).show()
                //musicFiles=getAll(this)
            }
            else if(grantResults[0]== PackageManager.PERMISSION_DENIED){
                Toast.makeText(this,"Need Access to locate songs", Toast.LENGTH_SHORT).show()
            }
            else{
                ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),Request_code)
            }
        }
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        if (p2){

        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        TODO("Not yet implemented")
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        TODO("Not yet implemented")
    }

}