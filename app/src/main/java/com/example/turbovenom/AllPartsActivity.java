package com.example.turbovenom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("  TurboVenom");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //case R.id.miSearch:
            // User chose the "Settings" item, show the app settings UI...
            //return true;

            case R.id.miSHOP:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.miCATIGORIES:

                return true;
            case R.id.miSIGNOUT:

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
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
