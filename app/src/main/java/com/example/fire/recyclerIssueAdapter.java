package com.example.fire;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class recyclerIssueAdapter extends RecyclerView.Adapter<recyclerIssueAdapter.ViewHolder>{
    Context context;
    ArrayList<issueAcid> arrIssue;
    String name;

    recyclerIssueAdapter(Context context, ArrayList<issueAcid> arrIssue, String name){
        this.context = context;
        this.arrIssue = arrIssue;
        Log.d("kjkj", String.valueOf(arrIssue.size()));
        this.name = name;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recycler_adapter_issue, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.IbookID.setText(arrIssue.get(position).bookID);
        holder.IbookName.setText(arrIssue.get(position).bookName);

        holder.Issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore.getInstance().collection("Admins").document(name).collection("Books").
                        whereEqualTo("bookID", holder.IbookID.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        Toast.makeText(context, "Issue Successfull", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });




                Map<String, String> v = new HashMap<>();
                v.put("nameOfBook", holder.IbookName.getText().toString());

                v.put("bookID", holder.IbookID.getText().toString());

                //                FirebaseFirestore.getInstance().collection("Vendor").document("personal details").set(v, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                //                    @Override
                //                    public void onComplete(@NonNull Task<Void> task) {
                //                        Toast.makeText(AddBook.this, "INSERTED", Toast.LENGTH_SHORT).show();
                //                    }
                //                });
                FirebaseFirestore.getInstance().collection("Admins").document(name).collection("Books").add(v).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrIssue.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView IbookID, IbookName;
        Button Issue;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            IbookID = itemView.findViewById(R.id.IbookID);
            IbookName = itemView.findViewById(R.id.IbookName);
            Issue = itemView.findViewById(R.id.Issue);
        }
    }
}
