package com.example.fire;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class next2 extends AppCompatActivity {
//    private Button show;
    private ListView list;
    private List<String> namelist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next2);
//        show = findViewById(R.id.show);
        list = findViewById(R.id.list);
        Intent intent = getIntent();
        String name = intent.getStringExtra(UserInterface.EXTRA_PASS);
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
                        ArrayAdapter adapter = new ArrayAdapter(next2.this, android.R.layout.simple_list_item_1, namelist);
                        adapter.notifyDataSetChanged();
                        list.setAdapter(adapter);
                    }
                });
//            }
//        });
    }
}