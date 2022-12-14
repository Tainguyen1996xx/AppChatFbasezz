package com.example.appchatfbasezz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appchatfbasezz.R;
import com.example.appchatfbasezz.databinding.ActivityMainBinding;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        loadUserDETAIL();
        getToken();
        setListen();
    }

    private void loadUserDETAIL() {
        binding.tvName.setText(preferenceManager.getString(Constants.Key_name));
    }

    private void getToken() {
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this::updateToken);
    }

    private void updateToken(String token) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.Key_collection_user).document(
                        preferenceManager.getString(Constants.Key_id_user)
                );

        documentReference.update((String) Constants.Key_FCM_TOKEN, token)
                .addOnSuccessListener(unused -> showTOAST("token update ssff"))
                .addOnFailureListener(e -> showTOAST("unable update"));
    }

    private void setListen() {
        binding.imgOut.setOnClickListener(v -> signOut());
        binding.floating.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), UserActivity.class)));

    }

    private void signOut() {
        showTOAST("sing out");
//
//        FirebaseFirestore database = FirebaseFirestore.getInstance();
//        DocumentReference documentReference =
//                database.collection(Constants.Key_collection_user).document(
//                        preferenceManager.getString(Constants.Key_id_user)
//                );
//
//        HashMap<String, String> update = new HashMap<>();
//        update.put(Constants.Key_FCM_TOKEN, FieldValue.delete());
//        documentReference.update(update)
//                .addOnSuccessListener(documentReference -> {
//                    preferenceManager.putBoolean(Constants.Key_SignEDname,true);
//                    preferenceManager.putString(Constants.Key_id_user,documentReference.getId());
//                    preferenceManager.putString(Constants.Key_name,documentReference.getId());
//
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
//
//                })
//                .addOnFailureListener(exception -> {
//                    showTOAST(exception.getMessage());
//                });
    }

    private void showTOAST(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}