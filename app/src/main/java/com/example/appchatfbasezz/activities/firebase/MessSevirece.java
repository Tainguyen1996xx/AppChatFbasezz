package com.example.appchatfbasezz.activities.firebase;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;

public class MessSevirece extends FirebaseMessagingService {
    public MessSevirece() {
    }

//    @Override
//    public void onNewToken(@NonNull String token) {
//        super.onNewToken(token);
//        Toast.makeText(this, "bbbbbbbbbb", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Toast.makeText(this, "bbbbbbbbbb", Toast.LENGTH_SHORT).show();
    }
}

