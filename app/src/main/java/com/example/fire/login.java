package com.example.fire;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class login extends AppCompatActivity {
    private EditText email2;
    //hi

    private EditText password2;
    private EditText UCode;
    private TextView textView10;
    private Button login1;
    private FirebaseAuth auth;
    private FirebaseFirestore fStore;
    public static final String EXTRA_NAME = "com.example.fire.extra.name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email2 = findViewById(R.id.email2);
        UCode = findViewById(R.id.UCode);
        textView10 = findViewById(R.id.textView10);
        textView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, register.class));
            }
        });
        password2 = findViewById(R.id.password2);
        login1 = findViewById(R.id.login1);
        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email2.getText().toString();
                String password1 = password2.getText().toString();
                if(TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1)){
                    Toast.makeText(login.this, "Enter Email and Password Both", Toast.LENGTH_SHORT).show();
                }
                else{
                    regis(email1, password1);
                }
            }
        });
    }

    private void regis(String email, String password){
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(login.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
//                FirebaseUser user = authResult.getUser();
//                System.out.println("THIS IS FOR COMMIT");
                checkUserAcesslevel(authResult.getUser().getUid());
                
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(login.this, "Login unsuccessfull please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkUserAcesslevel(String uid) {
        DocumentReference df = fStore.collection("Admins").document(UCode.getText().toString()).collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if(documentSnapshot.getString("Admin") == "0") {
                    Toast.makeText(login.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, UserInterface.class);

                    String nameText = UCode.getText().toString();
                    intent.putExtra(EXTRA_NAME, nameText);
                    startActivity(intent);
                    finish();
//                }
//                else{
//                    Toast.makeText(login.this, "You are a Admin", Toast.LENGTH_SHORT).show();
//                }
//                    startActivity(new Intent(login.this, UserInterface.class));
                }
//                else{
//                    Intent intent = new Intent(login.this,AdminInterface.class);
//
//                    String nameText = UCode.getText().toString();
//                    intent.putExtra(EXTRA_NAME, nameText);
//                    startActivity(intent);
////                    startActivity(new Intent(login.this, AdminInterface.class));
//                }
            });

        };
    }
