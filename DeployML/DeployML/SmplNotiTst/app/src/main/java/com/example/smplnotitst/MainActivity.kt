package com.example.smplnotitst


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val requestPermissionButton: Button = findViewById(R.id.request_permission_button)
        requestPermissionButton.setOnClickListener {
            val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            startActivity(intent)
        }

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
