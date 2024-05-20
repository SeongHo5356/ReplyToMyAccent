package com.example.smplnotitst

import android.app.Notification
import android.os.Bundle
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    private val PREFS_KEY:String = "bot";
    private val ON_KEY:String = "on";
    private var granted = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!isNotificationPermissionGranted()){
            startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
        }
    }

    private fun isNotificationPermissionGranted() : Boolean{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1){
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            return notificationManager.isNotificationListenerAccessGranted(ComponentName(application,MyNotificationListenerService::class.java))
        }
        else{
            return NotificationManagerCompat.getEnabledListenerPackages(applicationContext).contains(applicationContext.packageName)
        }
    }
}
