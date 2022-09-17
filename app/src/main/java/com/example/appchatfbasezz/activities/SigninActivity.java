package com.example.appchatfbasezz.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appchatfbasezz.R;
import com.example.appchatfbasezz.databinding.ActivitySigninBinding;

public class SigninActivity extends AppCompatActivity {
  private ActivitySigninBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySigninBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        setListeners();
    }

  private  void setListeners(){
        binding.SignUp.setOnClickListener(v ->
                startActivities(new Intent[]{new Intent(getApplicationContext(), SignupActivity.class)}));
  }
}