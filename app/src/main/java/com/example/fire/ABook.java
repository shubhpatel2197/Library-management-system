package com.example.fire;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ABook extends AppCompatActivity {
    private ListView list1;
    private List<String> namelist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abook);
        list1 = findViewById(R.id.list1);
        Intent intent = getIntent();
        String name = intent.getStringExtra(AdminInterface.EXTRA_MASS);
//        show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
        FirebaseFirestore.getInstance().collection("Admins").document(name).collection("Books").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                namelist.clear();
                for(DocumentSnapshot s: value){
                    namelist.add("\n"+"Book ID: "+s.getString("bookID")+"\n"+"Name of the Book: "+s.getString("nameOfBook")+"\n"+"Name of the Author: "+s.getString("nameOfAuthor")+"\n"+"Book Description: "+s.getString("bookDescription")+"\n");
                }
                ArrayAdapter adapter = new ArrayAdapter(ABook.this, android.R.layout.simple_list_item_1, namelist);
                adapter.notifyDataSetChanged();
                list1.setAdapter(adapter);
            }
        });
//            }
//        });
    }
}