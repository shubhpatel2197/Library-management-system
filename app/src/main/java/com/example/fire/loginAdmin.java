package com.example.fire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class loginAdmin extends AppCompatActivity {
    private EditText AEmail;
    private EditText APassword;
    private EditText ACode;
    private TextView textView9;
    private Button ALogin;
    private FirebaseAuth auth;
    private FirebaseFirestore fStore;
    public static final String EXTRA_CODE = "com.example.fire.extra.code";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        AEmail = findViewById(R.id.AEmail);
        ACode = findViewById(R.id.ACode);
        APassword = findViewById(R.id.APassword);
        ALogin = findViewById(R.id.ALogin);
        textView9 = findViewById(R.id.textView9);
        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginAdmin.this, registerAdmin.class));
            }
        });
        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        ALogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = AEmail.getText().toString();
                String password1 = APassword.getText().toString();
                if(TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1)){
                    Toast.makeText(loginAdmin.this, "Enter Email and Password Both", Toast.LENGTH_SHORT).show();
                }
                else{
                    regis(email1, password1);
                }
            }
        });
    }
    private void regis(String email, String password){
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(loginAdmin.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
//                FirebaseUser user = authResult.getUser();

                checkUserAcesslevel(authResult.getUser().getUid());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(loginAdmin.this, "Login unsuccessfull please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkUserAcesslevel(String uid) {
        DocumentReference df = fStore.collection("Admins").document(ACode.getText().toString());
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if(documentSnapshot.getString("Admin") == "1"){
                    Toast.makeText(loginAdmin.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginAdmin.this,AdminInterface.class);

                    String nameText = ACode.getText().toString();
                    intent.putExtra(EXTRA_CODE, nameText);
                    startActivity(intent);
                    finish();
//                    startActivity(new Intent(login.this, UserInterface.class));
//                }
//                else{
//                    Toast.makeText(loginAdmin.this, "You are a User", Toast.LENGTH_SHORT).show();
////                    startActivity(new Intent(login.this, AdminInterface.class));
//                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(loginAdmin.this, "You are user login from user", Toast.LENGTH_SHORT).show();
            }
        });
    }
}