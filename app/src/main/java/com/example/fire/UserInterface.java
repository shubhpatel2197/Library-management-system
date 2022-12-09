package com.example.fire;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserInterface extends AppCompatActivity {
    private Button bookAV;
    private Button UIssue;
    public static final String EXTRA_PASS = "com.example.fire.extra.pass";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);
        bookAV = findViewById(R.id.bookAV);
        UIssue = findViewById(R.id.UIssue);
        Intent intent = getIntent();
        String name = intent.getStringExtra(login.EXTRA_NAME);
        Intent i = getIntent();
        String name1 = i.getStringExtra(loginAdmin.EXTRA_CODE);
        if(name == null){
            name = name1;
        }
        String finalName = name;
        bookAV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent im = new Intent(UserInterface.this,SearchUser.class);

//                String nameText = UCode.getText().toString();
                im.putExtra(EXTRA_PASS, finalName);
                startActivity(im);
//                startActivity(new Intent(UserInterface.this, next2.class));
            }
        });

        UIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ib = new Intent(UserInterface.this,issue.class);

//                String nameText = UCode.getText().toString();
                ib.putExtra(EXTRA_PASS, finalName);
                startActivity(ib);
//                startActivity(new Intent(UserInterface.this, next2.class));
            }
        });


    }
}