package com.mahirshadid.vax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class bmi extends AppCompatActivity {

    Button calbmi;
    TextView currentheight,currentweight;
    ImageView incrementweight,decrementweight;
    SeekBar seekbarforheight;
    RelativeLayout male,female;

    int intweight=50;

    int currentprogress;
    String intprogress="120";
    String typeofuser="0";
    String weight2="50";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        getSupportActionBar().hide();

        calbmi=findViewById(R.id.calbmi);

        currentweight=findViewById(R.id.currentweight);
        currentheight=findViewById(R.id.currentheight);

        incrementweight=findViewById(R.id.increment);
        decrementweight=findViewById(R.id.decrement);
        seekbarforheight=findViewById(R.id.seekbarforheight);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser="পুরুষ";
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser="মহিলা";
            }
        });

        seekbarforheight.setMax(220);
        seekbarforheight.setProgress(120);
        seekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentprogress=progress;
                intprogress = String.valueOf(currentprogress);
                currentheight.setText(intprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        incrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intweight = intweight+1;
                weight2 = String.valueOf(intweight);
                currentweight.setText(weight2);
            }
        });

        decrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intweight = intweight-1;
                weight2 = String.valueOf(intweight);
                currentweight.setText(weight2);
            }
        });

        calbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(typeofuser.equals("0")){
                    Toast.makeText(getApplicationContext(),"Select gender first",Toast.LENGTH_SHORT).show();
                }else if(intprogress.equals("0")){
                    Toast.makeText(getApplicationContext(),"Invalid Height",Toast.LENGTH_SHORT).show();
                }else if(intweight==0 || intweight<0){
                    Toast.makeText(getApplicationContext(),"Invalid Weight",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent(bmi.this,bmicalc.class);

                    intent.putExtra("gender",typeofuser);
                    intent.putExtra("height",intprogress);
                    intent.putExtra("weight",weight2);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}