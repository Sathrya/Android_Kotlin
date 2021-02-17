package com.example.audioplayer

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.*
import android.util.Log
import android.widget.RemoteViews
import android.widget.SeekBar
import androidx.core.app.NotificationCompat
import com.example.audioplayer.model.MusicFiles


class MediaPlaybackService: Service(),SeekBar.OnSeekBarChangeListener {

    private lateinit var uri: Uri
    private var musicFiles=ArrayList<MusicFiles>()
    private lateinit var mediaPlayer: MediaPlayer
    private val mybinder = MyLocalBinder()
    private lateinit var runnable: Runnable
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"
    lateinit var builder: Notification.Builder
    private var handler = Handler(Looper.getMainLooper())
    override fun onBind(p0: Intent?): IBinder {
        return mybinder
    }

    inner class MyLocalBinder : Binder() {
        fun getService() : MediaPlaybackService {
            return this@MediaPlaybackService
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        musicFiles=MainActivity.musicFiles
        val pos=intent?.getIntExtra("position",2)
        uri=Uri.parse(musicFiles[pos!!].path)
        Log.d("positon", "$pos")
        Log.d("uri", "$uri")
        val num= intent.getIntExtra("number", 1)
        play_resume(num, pos)
        return START_STICKY
    }

    companion object {
        const val SECOND = 1000
    }

    private fun playMusic(uri: Uri?,pos: Int) {
        if(mediaPlayer.isPlaying){
            mediaPlayer.stop()
            mediaPlayer=MediaPlayer.create(this, uri)
           // seekBar.setOnSeekBarChangeListener(this)
            MainActivity.play_pause.setImageResource(R.drawable.pause)
            initialiseSeekBar()
            mediaPlayer.start()
            createnotification(pos)
            updateSeekBar()
        }
        else{
            mediaPlayer.stop()
            mediaPlayer=MediaPlayer.create(this, uri)
            //seekBar.setOnSeekBarChangeListener(this)
            MainActivity.play_pause.setImageResource(R.drawable.pause)
            initialiseSeekBar()
            mediaPlayer.start()
            createnotification(pos)
            updateSeekBar()
        }
    }

    override fun onCreate() {
        mediaPlayer=MediaPlayer.create(this, R.raw.bell)
        MainActivity.progressBar.setOnSeekBarChangeListener(this)
        initialiseSeekBar()
        updateSeekBar()
        mediaPlayer.isLooping=true
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop()
            Log.d("Music", "Service Stopped")
            mediaPlayer.release()
        }
    }

    private val MediaPlayer.seconds: Int
        get() {
            return this.duration / SECOND
        }

    private val MediaPlayer.currentSeconds: Int
        get() {
            return this.currentPosition / SECOND
        }

    private fun timeInString(seconds: Int):String{
        return String.format(
                "%02d:%02d",
                (seconds / 3600 * 60 + ((seconds % 3600) / 60)),
                (seconds % 60)
        )
    }

    fun initialiseSeekBar(){
        MainActivity.total.text=timeInString(mediaPlayer.seconds)
        MainActivity.progressBar.max=mediaPlayer.seconds
    }

    fun play_resume(number: Int, pos: Int){
        if(number==1){
            if (mediaPlayer.isPlaying){
                mediaPlayer.pause()
                MainActivity.play_pause.setImageResource(R.drawable.play)
                initialiseSeekBar()
            }
            else{
                val length=mediaPlayer.currentPosition
                mediaPlayer.seekTo(length)
                mediaPlayer.start()
                createnotification(pos)
                //seekBar.setOnSeekBarChangeListener(this)
                MainActivity.play_pause.setImageResource(R.drawable.pause)
                initialiseSeekBar()
            }
        }
        else if (number==2){
            if (mediaPlayer.isPlaying){
                mediaPlayer.stop()
                uri=Uri.parse(musicFiles[pos - 1].path)
                playMusic(uri,pos)
            }
        }
        else if (number==3){
            if (mediaPlayer.isPlaying){
                mediaPlayer.stop()
                uri=Uri.parse(musicFiles[pos + 1].path)
                playMusic(uri,pos)
            }
        }
        else{
            playMusic(uri,pos)
        }
    }

    private fun updateSeekBar(){
        runnable= Runnable {
            MainActivity.current.text=timeInString(mediaPlayer.currentSeconds)
            MainActivity.progressBar.progress=mediaPlayer.currentSeconds
            handler.postDelayed(runnable, SECOND.toLong())
        }
        handler.postDelayed(runnable, SECOND.toLong())

    }

    fun createnotification(pos: Int){
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)
            builder=Notification.Builder(this,channelId)
                    .setSmallIcon(R.drawable.music_note)
                    .setContentTitle("MusicPlayer")
                    .setContentText(musicFiles[pos].title)
                    //.setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
        }
        else{
            builder=Notification.Builder(this)
                    .setSmallIcon(R.drawable.music_note)
                    .setContentTitle("MusicPlayer")
                    .setContentText("Music playing")
                    //.setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
        }
        notificationManager.notify(0,builder.build())
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (fromUser){
            mediaPlayer.seekTo(progress * SECOND)
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        TODO("Not yet implemented")
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        TODO("Not yet implemented")
    }

}

