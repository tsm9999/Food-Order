package com.example.aissms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
public class SignUpActivity extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    CollectionReference cr;
    EditText name, mail, password, retypePassword, contact;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        cr = db.collection("Users");
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        retypePassword = findViewById(R.id.rpassword);
        signUp = findViewById(R.id.signup);
        contact = findViewById(R.id.mobile);
//        passwordCheck(password.getText().toString(),retypePassword.getText().toString());
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().isEmpty() && !mail.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !retypePassword.getText().toString().isEmpty() && !contact.getText().toString().isEmpty()) {
                    DataBaseUser dataBaseUser = new DataBaseUser(name.getText().toString(), mail.getText().toString(), password.getText().toString(), contact.getText().toString());
                    cr.add(dataBaseUser)
//                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                                @Override
//                                public void onComplete(@NonNull Task<DocumentReference> task) {
//                                    if (task.isSuccessful()) {
//                                        firebaseAuth.createUserWithEmailAndPassword(mail.getText().toString(), password.getText().toString())
//                                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                                    @Override
//                                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                                        if (task.isSuccessful()) {
//                                                            Toast.makeText(SignUpActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
//                                                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
//                                                        } else {
//                                                            Toast.makeText(SignUpActivity.this, "Some Error Occured Try Again", Toast.LENGTH_SHORT).show();
//                                                        }
//                                                    }
//                                                });
//                                    } else {
//                                        Toast.makeText(SignUpActivity.this, "Some Error Occured Try Again", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            })

                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    firebaseAuth.createUserWithEmailAndPassword(mail.getText().toString(), password.getText().toString())
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(SignUpActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                                    } else {
                                                        Toast.makeText(SignUpActivity.this, "Some Error Occured Try Again", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUpActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }

    public void passwordCheck(View v) {
        if (!password.getText().toString().equals(retypePassword.getText().toString()))
            retypePassword.setError("Enter same password");
    }
}

