package com.example.videoapp

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.SeekBar
import com.example.videoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),SurfaceHolder.Callback, SeekBar.OnSeekBarChangeListener,
MediaPlayer.OnPreparedListener{
    private val mediaPlayer=MediaPlayer()
    private lateinit var binding:ActivityMainBinding
    private lateinit var runnable: Runnable
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var selectedVideoUri: Uri

    companion object {
        const val GET_VIDEO = 123
        const val SECOND = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        mediaPlayer.setOnPreparedListener(this)
        //mediaPlayer.setOnDrmInfoListener(this)
        binding.videoView.holder.addCallback(this)
        binding.seekBar.setOnSeekBarChangeListener(this)
        binding.playButton.isEnabled = false

        binding.playButton.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
                binding.playButton.setImageResource(R.drawable.play)
            }
            else{
                mediaPlayer.start()
                binding.playButton.setImageResource(R.drawable.pause)
            }
        }
    }

    private fun timeInString(seconds:Int):String{
        return String.format(
            "%02d:%02d",
            (seconds/3600*60+((seconds%3600)/60)),
            (seconds%60)
        )
    }

    private fun initializeSeekBar(){
        binding.seekBar.max=mediaPlayer.seconds
        binding.textProgress.text=getString(R.string.default_value)
        binding.textTotalTime.text = timeInString(mediaPlayer.seconds)
        binding.playButton.isEnabled=true
    }

    private fun updateSeekBar(){
        runnable= Runnable {
            binding.textProgress.text=timeInString(mediaPlayer.currentSeconds)
            binding.seekBar.progress=mediaPlayer.currentSeconds
            handler.postDelayed(runnable,SECOND.toLong())
        }
        handler.postDelayed(runnable, SECOND.toLong())
    }

    // SurfaceHolder is ready
    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        mediaPlayer.apply{
            //setOnDrmInfoListener(this@MainActivity)
            setDataSource(applicationContext,selectedVideoUri)
            setDisplay(surfaceHolder)
            prepareAsync()
        }
    }


    //Ignore
    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
    }

    //Ignore
    override fun surfaceDestroyed(p0: SurfaceHolder) {
    }

    // For DRM files
   /* override fun onDrmInfo(mediaPlayer: MediaPlayer?, drmInfo: MediaPlayer.DrmInfo?) {
        //TODO(12): Add the code that will get executed when the DRM info is ready
    }*/

    override fun onPrepared(mediaPlayer: MediaPlayer?) {
        initializeSeekBar()
        updateSeekBar()
    }

    // Update media player when user changes seekBar
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if(fromUser){
            mediaPlayer.seekTo(progress* SECOND)
        }
    }

    // Ignore
    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    // Ignore
    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.select_file -> {
                val intent=Intent()
                intent.type="video/*"
                intent.action=Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(intent,getString(R.string.select_file)), GET_VIDEO)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==Activity.RESULT_OK){
            if(requestCode== GET_VIDEO){
                selectedVideoUri= data?.data!!
                binding.videoView.holder.addCallback(this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
        mediaPlayer.release()
    }

    private val MediaPlayer.seconds: Int
        get() {
            return this.duration / SECOND
        }

    private val MediaPlayer.currentSeconds: Int
        get() {
            return this.currentPosition / SECOND
        }
}