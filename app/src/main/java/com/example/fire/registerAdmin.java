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

public class registerAdmin extends AppCompatActivity {
    private EditText AdminEmail;
    private EditText AdminPassword;
    private EditText AdminName;
    private Button AdminRegister;
    private EditText AdminCode;
    private FirebaseAuth auth;
    private FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);
        AdminEmail = findViewById(R.id.AdminEmail);
        AdminPassword = findViewById(R.id.AdminPassword);
        AdminName = findViewById(R.id.AdminName);
        AdminRegister = findViewById(R.id.AdminRegister);
        AdminCode = findViewById(R.id.AdminCode);
        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        AdminRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = AdminEmail.getText().toString();
                String password1 = AdminPassword.getText().toString();
                String name = AdminName.getText().toString();
                String code = AdminCode.getText().toString();
                if(TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(name) || TextUtils.isEmpty(code)){
                    Toast.makeText(registerAdmin.this, "Enter Email, Password, Name and Library Code properly", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(registerAdmin.this, "Succesfully Registered", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = auth.getCurrentUser();
                    DocumentReference df = fStore.collection("Admins").document(AdminCode.getText().toString());
                    Map<String, String> userInfo = new HashMap<>();
                    userInfo.put("Name", AdminName.getText().toString());
                    userInfo.put("Admin", "1");
                    userInfo.put("Code", AdminCode.getText().toString());
                    df.set(userInfo);
//                    startActivity(new Intent(registerAdmin.this, AdminInterface.class));
//                    finish();
                }
                else{
                    Toast.makeText(registerAdmin.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}