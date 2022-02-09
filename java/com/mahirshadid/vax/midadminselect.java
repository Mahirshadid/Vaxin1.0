package com.mahirshadid.vax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class midadminselect extends AppCompatActivity {

    CardView nid,district,date,duty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midadminselect);

        nid=findViewById(R.id.nidinfosrch);
        district=findViewById(R.id.disinfosrch);
        date=findViewById(R.id.datesearchcard);
        duty=findViewById(R.id.midduty);

        nid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(midadminselect.this,nidsearch.class);
                startActivity(intent);
            }
        });

        district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(midadminselect.this,Districtsearch.class);
                startActivity(intent);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(midadminselect.this,datesearch.class);
                startActivity(intent);
            }
        });

        duty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(midadminselect.this,midadminduty.class);
                startActivity(intent);
            }
        });
    }
}