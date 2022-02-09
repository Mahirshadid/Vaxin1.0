package com.mahirshadid.vax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class othervaccine extends AppCompatActivity implements View.OnClickListener {

    CardView rabies,ri,je,yfever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_othervaccine);

        rabies=findViewById(R.id.rabies);
        ri=findViewById(R.id.ri);
        je=findViewById(R.id.je);
        yfever=findViewById(R.id.yfever);

        rabies.setOnClickListener(this);
        ri.setOnClickListener(this);
        je.setOnClickListener(this);
        yfever.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rabies:
                Intent intent=new Intent(othervaccine.this,rabies.class);
                startActivity(intent);
                break;

            case R.id.ri:
                intent=new Intent(othervaccine.this,ri.class);
                startActivity(intent);
                break;

            case R.id.je:
                intent=new Intent(othervaccine.this,je.class);
                startActivity(intent);
                break;

            case R.id.yfever:
                intent=new Intent(othervaccine.this,yellowfever.class);
                startActivity(intent);
                break;
        }
    }
}