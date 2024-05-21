package com.example.smplnotitst

import android.app.Notification
import android.service.notification.StatusBarNotification
import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService

class WearMessageListenerService : WearableListenerService() {

    override fun onMessageReceived(messageEvent: MessageEvent) {
        if (messageEvent.path == "/send_meesage"){
            val message = String(messageEvent.data)
            Log.d("WearMessageListner", "Received message : $message")

            val notificationListenerService = MyNotificationListenerService()
            val notifications : Array<StatusBarNotification> = notificationListenerService.activeNotifications

            for (sbn in notifications){

                val wExt = Notification.WearableExtender(sbn.notification)
                val act = wExt.actions.firstOrNull() {
                    action -> action.remoteInputs != null && action.remoteInputs.isNotEmpty() &&
                        (action.title.toString().contains("reply", true) || action.title.toString().contains("답장", true))
                }
                if (act != null){
                    notificationListenerService.callResponder(
                        sbn.notification.extras?.getString("android.title"),
                        sbn.notification.extras?.get("android.text"),
                        act,
                        message
                    )
                    break
                }
            }
        }
    }
}