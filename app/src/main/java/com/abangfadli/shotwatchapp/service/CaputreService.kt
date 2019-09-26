package com.abangfadli.shotwatchapp.service

import android.app.*
import android.content.ContentResolver
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import android.content.Context
import android.os.Build
import android.widget.Toast
import com.abangfadli.shotwatchapp.broadcastreceive.CaputreReceiver
import com.abangfadli.shotwatchapp.View.ImageEditTest
import com.abangfadli.shotwatchapp.R
import com.abangfadli.shotwatchapp.View.MainActivity
import com.abangfadli.shotwatchapp.util.FileUtils
import com.akexorcist.screenshotdetection.ScreenshotDetectionDelegate

import iamutkarshtiwari.github.io.ananas.editimage.ImageEditorIntentBuilder
import java.io.File
import java.lang.Exception
import java.util.*
import com.abangfadli.shotwatchapp.View.MainActivity.mContext
import iamutkarshtiwari.github.io.ananas.editimage.EditImageActivity
import iamutkarshtiwari.github.io.ananas.editimage.EditImageActivity.ctx


class CaputreService : Service(),ScreenshotDetectionDelegate.ScreenshotDetectionListener    {
    var screenshotDetectionDelegate = ScreenshotDetectionDelegate(this, this)

    override fun onScreenCaptured(path: String?) {
/*
        var intent =  Intent(this, ImageEditTest::class.java)

        intent.putExtra("url_path",path)
        intent.putExtra("is_open",true)

*/
        var outputFile = FileUtils.genEditFile()
  /*
        try {
      var   intent =  ImageEditorIntentBuilder(this, path,path)
            .withAddText()
                    .withPaintFeature()
                    .withFilterFeature()
                    .withRotateFeature()
                    .withCropFeature()
                    .withBrightnessFeature()
                    .withSaturationFeature()
                    .withBeautyFeature()
                    .forcePortrait(true)
                    .build();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
           // EditImageActivity.start(this,intent,9);
            (EditImageActivity.ctx as EditImageActivity).loadImageFromFile(path)


        }catch (e:Exception){
            Log.i("test",e.message);
        }
*/

        var   intent =  ImageEditorIntentBuilder(this, path,path)
                .withAddText()
                .withPaintFeature()
                .withFilterFeature()
                .withRotateFeature()
                .withCropFeature()
                .withBrightnessFeature()
                .withSaturationFeature()
                .withBeautyFeature()
                .forcePortrait(true)
                .build();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
//        (EditImageActivity.ctx as EditImageActivity).loadImageFromFile(path)


        // EditImageActivity.start(this,intent,9);




        // Toast.makeText(getApplicationContext(),"path : $path " ,Toast.LENGTH_LONG).show();
        Log.i("khs","이값은 몇번나오나 path : $path ");

    }

    override fun onScreenCapturedWithDeniedPermission() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        var serviceIntent: Intent? = null
    }


  // val screenshotDetectionDelegate = MainActivity.screenshotDetectionDelegate!!
    val ACTION_REQUEST_EDITIMAGE = 9
    var boolean =false



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        contentResolver

        Log.i("khs","service 시작")
        serviceIntent = intent
        initializeNotification()
        //Toast.makeText(applicationContext,"service 시작",Toast.LENGTH_LONG).show()

    //    (MainActivity.mContext as MainActivity).test()


        screenshotDetectionDelegate.startScreenshotDetection()


        if(intent?.getBooleanExtra("key",false)==true) {
            Log.i("khs","key값넘어옴")
        }

        val intents  = intent?.action
            if(intents != null) {
                if (intents == "9") { // same code you used while starting
                    val newFilePath = intent.getStringExtra("output_path")

                    Log.i("khs",newFilePath)
                }
            }

        return START_STICKY;

    }


    fun initializeNotification() {

        var builder = NotificationCompat.Builder(this, "1")
        builder.setSmallIcon(R.mipmap.ic_launcher)

        var style = NotificationCompat.BigTextStyle()
        style.bigText("설정을 보려면 누르세요")
        style.setBigContentTitle(null)
        style.setSummaryText("캐치캡쳐 서비스 동작중")

        builder.setContentText("텍스트")
        builder.setContentTitle("캐치캡쳐")
        builder.setOngoing(true)

        builder.setStyle(style)
        builder.setWhen(0)
        builder.setShowWhen(false)

        var notificationIntent = Intent(this, MainActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0)
        builder.setContentIntent(pendingIntent)

        var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            manager.createNotificationChannel(NotificationChannel("1","Capture_service",NotificationManager.IMPORTANCE_NONE))
            var notification =builder.build()
            startForeground(1,notification)
        }


    }


    override fun onDestroy() {
        super.onDestroy()


            var intent = Intent(this, CaputreReceiver::class.java)
            var sender = PendingIntent.getBroadcast(this, 0, intent, 0)
            val calendar = Calendar.getInstance()
            calendar.setTimeInMillis(System.currentTimeMillis())
            calendar.add(Calendar.SECOND, 3)
            val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender)
            boolean = true
        screenshotDetectionDelegate.stopScreenshotDetection()
        Log.i("khs", "onDestroy")
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)

            var intent = Intent(this, CaputreReceiver::class.java)
            var sender = PendingIntent.getBroadcast(this, 0, intent, 0)
            val calendar = Calendar.getInstance()
            calendar.setTimeInMillis(System.currentTimeMillis())
            calendar.add(Calendar.SECOND, 3)
            val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender)
            boolean = true
        screenshotDetectionDelegate.stopScreenshotDetection()
        Log.i("khs","onTaskRemoved")

    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}


