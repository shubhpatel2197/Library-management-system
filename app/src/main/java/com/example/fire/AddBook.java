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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddBook extends AppCompatActivity {
    private EditText nameOfBook;
    private EditText authorOfBook;
    private EditText BID;
    private EditText bookDescription;
    private Button addBook;
//    private Button button3;
//    private Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        nameOfBook = findViewById(R.id.nameOfBook);
        authorOfBook = findViewById(R.id.authorOfBook);
        bookDescription = findViewById(R.id.bookDescription);
        BID = findViewById(R.id.BID);
        addBook = findViewById(R.id.addBook);
//        button3 = findViewById(R.id.button3);
//        button4 = findViewById(R.id.button4);
        Intent intent = getIntent();
        String name = intent.getStringExtra(AdminInterface.EXTRA_MASS);
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AddBook.this, Delete.class));
//            }
//        });
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AddBook.this,next2.class));
//            }
//        });
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameofbook = nameOfBook.getText().toString();
                String authorofbook  = authorOfBook.getText().toString();
                String bookdescription = bookDescription.getText().toString();
                String bid = BID.getText().toString();
                if(TextUtils.isEmpty(nameofbook) || TextUtils.isEmpty(authorofbook) || TextUtils.isEmpty(bookdescription) || TextUtils.isEmpty(bid)){
                    Toast.makeText(AddBook.this, "Fill details properly", Toast.LENGTH_SHORT).show();
                }
                else{

                    Map<String, String> v = new HashMap<>();
                    v.put("nameOfBook", nameOfBook.getText().toString());
                    v.put("nameOfAuthor",authorOfBook.getText().toString());
                    v.put("bookID", BID.getText().toString());
                    v.put("bookDescription", bookDescription.getText().toString());
    //                FirebaseFirestore.getInstance().collection("Vendor").document("personal details").set(v, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
    //                    @Override
    //                    public void onComplete(@NonNull Task<Void> task) {
    //                        Toast.makeText(AddBook.this, "INSERTED", Toast.LENGTH_SHORT).show();
    //                    }
    //                });
                    FirebaseFirestore.getInstance().collection("Admins").document(name).collection("Books").add(v).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(AddBook.this, "INSERTED", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
}