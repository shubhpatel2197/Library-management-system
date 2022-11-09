package com.example.fire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText userName;
    private Button register1;
    private EditText UserCode;
    private FirebaseAuth auth;
    private FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        UserCode = findViewById(R.id.UserCode);
        userName = findViewById(R.id.userName);
        register1 = findViewById(R.id.register1);
        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String name = userName.getText().toString();
                if(TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(name)){
                    Toast.makeText(register.this, "Enter Email, Password and Name properly", Toast.LENGTH_SHORT).show();
                }
                else{
                    regis(email1, password1);
                }
            }
        });
    }

    private void regis(String email, String password){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(register.this, "Succesfully Registered", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = auth.getCurrentUser();
                    DocumentReference df = fStore.collection("Admins").document(UserCode.getText().toString()).collection("Users").document(user.getUid());
                    Map<String, String> userInfo = new HashMap<>();
                    userInfo.put("Name", userName.getText().toString());
                    userInfo.put("Admin", "0");
                    df.set(userInfo);
//                    startActivity(new Intent(register.this, UserInterface.class));
//                    finish();
                }
                else{
                    Toast.makeText(register.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}