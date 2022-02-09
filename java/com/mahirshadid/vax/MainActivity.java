package com.mahirshadid.vax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView reg,adminl,status,newsdesk,login,impinfo,game,othervac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reg=findViewById(R.id.registration);
        adminl=findViewById(R.id.admin);
        status=findViewById(R.id.status);
        newsdesk=findViewById(R.id.news);
        login=findViewById(R.id.login);
        impinfo=findViewById(R.id.impinfo);
        game=findViewById(R.id.game);
        othervac=findViewById(R.id.othervaccine);

        status.setOnClickListener(this);
        reg.setOnClickListener(this);
        adminl.setOnClickListener(this);
        newsdesk.setOnClickListener(this);
        login.setOnClickListener(this);
        impinfo.setOnClickListener(this);
        game.setOnClickListener(this);
        othervac.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registration:
                Intent intent=new Intent(MainActivity.this,UpN.class);
                startActivity(intent);
                break;

            case R.id.admin:
                intent=new Intent(MainActivity.this,adminlogin.class);
                startActivity(intent);
                break;

            case R.id.status:
                intent=new Intent(MainActivity.this,covidstatus.class);
                startActivity(intent);
                break;

            case R.id.news:
                intent=new Intent(MainActivity.this,newsdesk.class);
                startActivity(intent);
                break;

            case R.id.login:
                intent=new Intent(MainActivity.this,login.class);
                startActivity(intent);
                break;

            case R.id.impinfo:
                intent=new Intent(MainActivity.this,showHA.class);
                startActivity(intent);
                break;

            case R.id.game:
                intent=new Intent(MainActivity.this,bmi.class);
                startActivity(intent);
                break;

            case R.id.othervaccine:
                intent=new Intent(MainActivity.this,othervaccine.class);
                startActivity(intent);
                break;
        }
    }
}