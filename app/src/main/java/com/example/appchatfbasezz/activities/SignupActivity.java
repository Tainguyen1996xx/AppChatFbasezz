package com.example.appchatfbasezz.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appchatfbasezz.R;
import com.example.appchatfbasezz.databinding.ActivitySigninBinding;
import com.example.appchatfbasezz.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private  void setListeners(){
        binding.Btnlogin.setOnClickListener(v -> onBackPressed());
    }
}