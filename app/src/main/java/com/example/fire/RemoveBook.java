package com.example.fire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;

public class RemoveBook extends AppCompatActivity {
    private EditText bookRemove;
    private Button remove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_book);
        bookRemove = findViewById(R.id.bookRemove);
        remove = findViewById(R.id.remove);
        Intent intent = getIntent();
        String name = intent.getStringExtra(AdminInterface.EXTRA_MASS);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookremove = bookRemove.getText().toString();
                if(TextUtils.isEmpty(bookremove) ){
                    Toast.makeText(RemoveBook.this, "Enter Book ID properly", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseFirestore.getInstance().collection("Admins").document(name).collection("Books").
                            whereEqualTo("bookID",bookRemove.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    WriteBatch b=FirebaseFirestore.getInstance().batch();
                                    List<DocumentSnapshot> s = queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot snapshot:s){
                                        b.delete(snapshot.getReference());
                                    }
                                    b.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(RemoveBook.this, "Successfull", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                }
            }
        });
    }
}