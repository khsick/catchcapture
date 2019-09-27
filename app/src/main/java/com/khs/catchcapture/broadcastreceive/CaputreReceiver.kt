package com.khs.catchcapture.broadcastreceive

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log

import com.khs.catchcapture.service.CaputreService

class CaputreReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                var intent = Intent(context, CaputreService::class.java)
                intent.putExtra("key", true)
                context.startForegroundService(intent)
            } else {
                var intent = Intent(context, CaputreService::class.java)
                intent.putExtra("key", true)
                //intent.putExtra("tests",MainActivity.screenshotDetectionDelegate)
                intent.putExtra("test", "test값")
                context.startService(intent)
            }

        /*
        var intent = Intent(context, MainActivity::class.java)

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.putExtra( "KILL_APP", true)

            intent.putExtra("rec", true)

            context.startActivity(intent)

         */
            Log.i("khs", "onReceive 호출")


    }
}
