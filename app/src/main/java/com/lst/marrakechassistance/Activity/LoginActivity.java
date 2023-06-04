package com.lst.marrakechassistance.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lst.marrakechassistance.R;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button loginBtn;
    TextView signupTxt;
    ImageView skipBtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        loginBtn = findViewById(R.id.btnLogin);


        signupTxt = findViewById(R.id.signupTxt);
        skipBtn = findViewById(R.id.skipBtn);

        skipBtn.setOnClickListener((v)->{
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });

        System.out.println("Hello WORLD");
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateemail()| !validatepassword()){

                }else {
                    checkUser();
                }
            }
        });
        signupTxt.setOnClickListener((v)->{
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });
    }



    public Boolean validateemail(){
        String val = email.getText().toString();
        if(val.isEmpty()){
            email.setError("Username cannot be empty");
            return false;
        }else {
            email.setError(null);
            return true;
        }
    }
    public Boolean validatepassword(){
        String val = password.getText().toString();
        if(val.isEmpty()){
            password.setError("Username cannot be empty");
            return false;
        }else {
            password.setError(null);
            return true;
        }
    }
    public void checkUser() {
        String useremail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("email").equalTo(useremail);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    email.setError(null);
                    String passwordFromDb = snapshot.child(useremail).child("password").getValue(String.class);
                    if (Objects.equals(passwordFromDb, userPassword)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        password.setError("Invalid Password");
                        password.requestFocus();
                    }
                } else {
                    email.setError("Email does not exist");
                    email.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Gérer les erreurs de base de données
            }
        });
    }

}

