package com.lst.marrakechassistance.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lst.marrakechassistance.Helper.HelperClass;
import com.lst.marrakechassistance.R;

public class RegisterActivity extends AppCompatActivity {
    EditText nom, prenom, email, password;
    Button signUp;
    FirebaseDatabase database;
    DatabaseReference reference;


    GoogleSignInClient gsc;
    GoogleSignInOptions gso;
    ImageView googleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        signUp = findViewById(R.id.btnSignUp);

        signUp.setOnClickListener(v -> {

            String name = nom.getText().toString();
            String lastName = prenom.getText().toString();
            String Email = email.getText().toString();
            String Password = password.getText().toString();

            if (name.isEmpty() || lastName.isEmpty() || Email.isEmpty() || Password.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                database = FirebaseDatabase.getInstance();
                reference= database.getReference("users");

                HelperClass helperClass = new HelperClass(name,lastName,Email, Password);
                reference.child(name).setValue(helperClass);

                Toast.makeText(RegisterActivity.this,"You have signup successfully!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);}
        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getApplicationContext(),gso);

        googleBtn = findViewById(R.id.googleLogin);
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut(); // Déconnectez l'utilisateur actuel

                signIn();
            }
        });
    }
    private void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Quelque chose s'est mal passé", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void signOut() {
        gsc.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                // Déconnexion réussie
            }
        });
    }



    private void navigateToSecondActivity() {
        finish();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);

    }

}