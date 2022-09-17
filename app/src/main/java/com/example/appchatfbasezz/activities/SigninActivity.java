package com.example.appchatfbasezz.activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import com.example.appchatfbasezz.R;
import com.example.appchatfbasezz.databinding.ActivitySigninBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {
    private ActivitySigninBinding binding;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        if(preferenceManager.getboolean(Constants.Key_SignEDname)){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
        setListeners();
    }


    private void setListeners() {
        binding.BtnSignUp.setOnClickListener(v -> startActivities(new Intent[]{new Intent(getApplicationContext(), SignupActivity.class)}));
        binding.Btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidUpDetail()){
                    signiN();
                }
            }
        });
    }
    private void showTOAST(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void signiN() {

        FirebaseFirestore database = FirebaseFirestore.getInstance();
       database.collection(Constants.Key_collection_user)
               .whereEqualTo(Constants.Key_email, binding.edName.getText().toString())
               .whereEqualTo(Constants.Key_pass, binding.Edpass.getText().toString())
               .get().addOnCompleteListener(task -> {
                  if (task.isSuccessful() && task.getResult() != null
                          && task.getResult().getDocuments().size()>0 ){
                      DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                      preferenceManager.putBoolean(Constants.Key_SignEDname,true);
                      preferenceManager.putString(Constants.Key_id_user,  documentSnapshot.getId());
                      preferenceManager.putString(Constants.Key_id_user,  documentSnapshot.getString(Constants.Key_name));

                      Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                      startActivity(intent);


                  }else {
                      showTOAST("err in activity signin");
                  }
               });
    }

    private Boolean isValidUpDetail() {
        if (binding.edName.getText().toString().trim().isEmpty()) {
            showTOAST("Enter Name");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.edName.getText().toString()).matches()) {
            showTOAST("Enter valid Email");
            return false;
        } else if (binding.Edpass.getText().toString().trim().isEmpty()) {
            showTOAST("select image");
            return false;
        } else {
            return true;
        }
    }
}