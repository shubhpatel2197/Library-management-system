package com.example.fire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class next extends AppCompatActivity {
    private EditText bookName;
    private EditText authorName;
    private EditText bookID;
    private Button button;
//    private Task<Void> mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        bookName = findViewById(R.id.bookName);
        authorName = findViewById(R.id.authorName);
        bookID = findViewById(R.id.bookID);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(next.this, "hhhhhhh", Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance().getReference().child("Vendor1").setValue("Ram");
//                HashMap<String, Object> m = new HashMap<String , Object>();
//                m.put("Book",bookName.getText().toString());
////                m.put("Author Name: ",authorName.getText().toString());
//                m.put("Id",bookID.getText().toString());
//                FirebaseDatabase.getInstance().getReference().child("Database").push().setValue(m);
            }
        });
    }
}