package com.example.fire;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class issue extends AppCompatActivity {
    private RecyclerView recyclerIssue ;
    private ArrayList<issueAcid> arrIssue = new ArrayList<issueAcid>(5);
    private ArrayList<String> arrmussu = new ArrayList<>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);
        Intent intent = getIntent();
        String name = intent.getStringExtra(UserInterface.EXTRA_PASS);
       // issueAcid s = new issueAcid("asss", "dhkdjhfdkjf");
//      //  arrIssue.add(s);

        recyclerIssue = findViewById(R.id.recyclerIssue);
        recyclerIssue.setLayoutManager(new LinearLayoutManager(this));
        recyclerIssueAdapter adapter = new recyclerIssueAdapter(this, arrIssue, name);

        FirebaseFirestore.getInstance().collection("Admins").document(name).collection("Books").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                arrIssue.clear();
                for(DocumentSnapshot s: value){
                    issueAcid v = new issueAcid(s.getString("bookID"),s.getString("nameOfBook"));
                    arrIssue.add(v);
                    Log.d("Hehe",s.getString("bookID")+s.getString("nameOfBook"));
                }

//                arrmussu = arrIssue;
                recyclerIssue.setAdapter(adapter);
                Log.d("ghgh", String.valueOf(arrmussu.size()));
            }

        });

//  issueAcid a = arrIssue.get(0);
//        Log.d("Arraylist acidity", a.bookID);






    }
}