package com.mahirshadid.vax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class bottomadminselect extends AppCompatActivity {

    CardView covidvaccinetaker,othervaccinetaker,vaccinedatelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomadminselect);

        covidvaccinetaker=findViewById(R.id.vaccinetakersinfo);
        othervaccinetaker=findViewById(R.id.othervaccinerequest);
        vaccinedatelist=findViewById(R.id.vaccinedatelist);

        covidvaccinetaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(bottomadminselect.this,updatevaccinedatewhengiven.class);
                startActivity(intent);
            }
        });

        othervaccinetaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(bottomadminselect.this,showdataofothervacs.class);
                startActivity(intent);
            }
        });

        vaccinedatelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(bottomadminselect.this,bottomshowregmembers.class);
                startActivity(intent);
            }
        });
    }
}