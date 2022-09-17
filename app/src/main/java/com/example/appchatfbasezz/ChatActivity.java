package com.example.appchatfbasezz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appchatfbasezz.activities.Constants;
import com.example.appchatfbasezz.activities.User;
import com.example.appchatfbasezz.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {
 private ActivityChatBinding binding;
 private User receiUser;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListeners();
        loadReceiDetail();

    }

    private void loadReceiDetail(){
     receiUser = (User) getIntent().getSerializableExtra(Constants.Key_User);
     binding.tvName.setText(receiUser.name);
    }
    private  void setListeners(){
     binding.imageback.setOnClickListener(v-> onBackPressed());
    }
}