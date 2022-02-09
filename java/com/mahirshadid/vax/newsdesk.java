package com.mahirshadid.vax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class newsdesk extends AppCompatActivity implements View.OnClickListener {

    CardView prothomalo,dailystar,nayadiganta,manabzamin,kk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdesk);

        prothomalo=findViewById(R.id.prothomalo);
        prothomalo.setOnClickListener(this);

        dailystar=findViewById(R.id.dailystar);
        dailystar.setOnClickListener(this);

        nayadiganta=findViewById(R.id.nayadiganta);
        nayadiganta.setOnClickListener(this);

        manabzamin=findViewById(R.id.manabzamin);
        manabzamin.setOnClickListener(this);

        kk=findViewById(R.id.kk);
        kk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.prothomalo:
                Intent intent=new Intent(newsdesk.this,prothomaloweb.class);
                startActivity(intent);
                break;

            case R.id.dailystar:
                intent=new Intent(newsdesk.this,dailystarweb.class);
                startActivity(intent);
                break;

            case R.id.nayadiganta:
                intent=new Intent(newsdesk.this,nayadigantaweb.class);
                startActivity(intent);
                break;

            case R.id.manabzamin:
                intent=new Intent(newsdesk.this,manabzaminweb.class);
                startActivity(intent);
                break;

            case R.id.kk:
                intent=new Intent(newsdesk.this,kalerkantho.class);
                startActivity(intent);
                break;
        }
    }
}