package com.example.wearapp.presentation

import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService

class AndroidListener : WearableListenerService(){
    override fun onMessageReceived(messageEvent: MessageEvent) {
        if(messageEvent.path == "/message_path"){
            val message = String(messageEvent.data)
            Log.d("WearableMessage", "Message received: $message")
        }
        else{
            super.onMessageReceived(messageEvent)
        }
    }

}