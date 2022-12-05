package com.testapp.registersplash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signUpActivity extends AppCompatActivity {

    private EditText userEmail, userName, userPassword, userPhone;
    private Button btnSignUp;
    private ProgressBar progressBar;

    // firebase Auth

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userEmail = findViewById(R.id.email_sign_up_activity);
        userName = findViewById(R.id.user_name_sign_up_activity);
        userPassword = findViewById(R.id.password_sign_up_activity);
        userPhone = findViewById(R.id.phone_sign_up_activity);
        btnSignUp = findViewById(R.id.button);
        progressBar = findViewById(R.id.progress);

        // firebase auth
        mAuth = FirebaseAuth.getInstance();


    }

    public void validation() {
        // Code For Get The Text From The Fields

        String email = userEmail.getText().toString().trim();
        String Name = userName.getText().toString().trim();
        String Password = userPassword.getText().toString().trim();
        String Phone = userPhone.getText().toString().trim();

        // The Condition For Empty User Name

        if (email.isEmpty()) {
            Toast.makeText(this, "User Email Is Required", Toast.LENGTH_SHORT).show();
            userEmail.requestFocus();
            return;
        }

        // The Condition For Empty User Email

        if (Name.isEmpty()) {
            Toast.makeText(this, "user Name Is Required", Toast.LENGTH_SHORT).show();
            userName.requestFocus();
            return;
        }

        // The Condition For Incorrect Email Address

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Incorrect Email Address", Toast.LENGTH_SHORT).show();
            userEmail.requestFocus();
            return;
        }

        // The Condition For Empty Password

        if (Password.isEmpty()) {
            Toast.makeText(this, "Password Is Required", Toast.LENGTH_SHORT).show();
            userPassword.requestFocus();
            return;
        }

        // The Condition For Weak Password And Little Than 6 Digits

        if (Password.length() < 6) {
            Toast.makeText(this, "Weak Password Must Be More Than 6 Digits", Toast.LENGTH_SHORT).show();
            userPassword.requestFocus();
            return;
        }

        // The Condition For Empty Phone Number Field

        if (Phone.isEmpty()) {
            Toast.makeText(this, "Phone Number Is Required", Toast.LENGTH_SHORT).show();
            userPhone.requestFocus();
            return;
        }
        creatUser(email,Password);
    }

    private void creatUser(String userEmail, String userPassword) {
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(signUpActivity.this, "User Account Created", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(signUpActivity.this, "Error \n " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);

                    }
                });
    }
}