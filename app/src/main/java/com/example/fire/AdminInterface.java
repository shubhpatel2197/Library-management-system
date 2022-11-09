package com.example.fire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminInterface extends AppCompatActivity {
    private Button AddBook;
    private Button RemoveBook;
    private Button ABook;
    public static final String EXTRA_MASS = "com.example.fire.extra.mass";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_interface);
        AddBook = findViewById(R.id.AddBook);
        RemoveBook = findViewById(R.id.RemoveBook);
        ABook = findViewById(R.id.ABook);
//        Intent intent = getIntent();
//        String name1 = intent.getStringExtra(login.EXTRA_NAME);
        Intent i = getIntent();
        String name = i.getStringExtra(loginAdmin.EXTRA_CODE);
//        if(name == null){
//            name = name1;
//        }
        String finalName = name;
        AddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent im = new Intent(AdminInterface.this,AddBook.class);

//                String nameText = UCode.getText().toString();
                im.putExtra(EXTRA_MASS, name);
                startActivity(im);
            }
        });
        String finalName1 = name;
        RemoveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imn = new Intent(AdminInterface.this,RemoveBook.class);

//                String nameText = UCode.getText().toString();
                imn.putExtra(EXTRA_MASS, name);
                startActivity(imn);
            }
        });
        String finalName2 = name;
        ABook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imnt = new Intent(AdminInterface.this,ABook.class);

//                String nameText = UCode.getText().toString();
                imnt.putExtra(EXTRA_MASS, name);
                startActivity(imnt);
            }
        });
    }
}