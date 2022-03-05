package com.example.turbovenom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllPartsActivity extends AppCompatActivity {


    private RecyclerView rvAllParts;
    AdapterPart adapter;
    FirebaseServices fbs;
    ArrayList<Part> parts;
    MyCallback myCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_parts);

        fbs = FirebaseServices.getInstance();
        parts = new ArrayList<Part>();
        readData();
        myCallback = new MyCallback() {
            @Override
            public void onCallback(List<Part> restsList) {
                RecyclerView recyclerView = findViewById(R.id.rvAllParts);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new AdapterPart(getApplicationContext(), parts);
                recyclerView.setAdapter(adapter);
            }
        };

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAllParts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterPart(this, parts);
        recyclerView.setAdapter(adapter);
    }

    private void readData() {
        fbs.getFirestore().collection("parts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                parts.add(document.toObject(Part.class));
                            }
                            myCallback.onCallback(parts);

                        } else {
                            Log.e("AllPartActivity: readData()", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
