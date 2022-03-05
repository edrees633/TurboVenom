package com.example.turbovenom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class  PartDetailsActivity extends AppCompatActivity {

    private TextView tvName, tvDescription,tvLittleHistory, tvFinalInfo;
    private ImageView ivPhoto1, ivPhoto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_details);

        connectComponents();
        Intent i = this.getIntent();
        Part part = (Part) i.getSerializableExtra("part");

        tvName.setText(part.getName());
        tvDescription.setText(part.getDescription());
        tvLittleHistory.setText(part.getHistory());
        tvFinalInfo.setText(part.getName());
        Picasso.get().load(part.getPhoto()).into(ivPhoto1);
        Picasso.get().load(part.getPhoto()).into(ivPhoto2);
    }
    private void connectComponents() {
        tvName = findViewById(R.id.PartNameDetails);
        tvDescription = findViewById(R.id.tvDiscriptionDetails);
        tvLittleHistory = findViewById(R.id.tvHistoryDetails);
        tvFinalInfo = findViewById(R.id.tvFinalInfoDetails);
        ivPhoto1 = findViewById(R.id.IvPhotoDetails1);
        ivPhoto2 = findViewById(R.id.IvPhotoDetails2);
    }
}