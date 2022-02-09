package com.mahirshadid.vax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class bmicalc extends AppCompatActivity {

    Button recalbmi;
    TextView bmidisplay,bmicategory,gender;
    Intent intent;
    ImageView imageView;
    String mbmi;
    float intbmi;

    String height,weight;
    float intheight, intweight;

    RelativeLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalc);
        getSupportActionBar().hide();

        recalbmi=findViewById(R.id.recalbmi);


        intent = getIntent();

        bmidisplay=findViewById(R.id.bmidisplay);
        bmicategory=findViewById(R.id.bmicategory);
        gender=findViewById(R.id.genderdisplay);
        background=findViewById(R.id.contentlayout);
        imageView=findViewById(R.id.imageview);

        height=intent.getStringExtra("height");
        weight=intent.getStringExtra("weight");

        intheight=Float.parseFloat(height);
        intweight=Float.parseFloat(weight);

        intheight=intheight/100;
        intbmi=intweight/(intheight*intheight);

        mbmi=Float.toString(intbmi);

        if(intbmi<16){
            bmicategory.setText("Sever Thinness");
            //background.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.ic_cross);
        }else if(intbmi<16.9 && intbmi>16){
            bmicategory.setText("Moderate Thinness");
           // background.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.ic_warning);
        }else if(intbmi<18.4 && intbmi>17){
            bmicategory.setText("Mild Thinness");
          //  background.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.ic_warning);
        }else if(intbmi<25 && intbmi>18.4){
            bmicategory.setText("Normal");
            imageView.setImageResource(R.drawable.ic_ok);
        }else if(intbmi<29.4 && intbmi>25){
            bmicategory.setText("Overweight");
           // background.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.ic_warning);
        }else{
                bmicategory.setText("Obese");
              //  background.setBackgroundColor(Color.RED);
                imageView.setImageResource(R.drawable.ic_cross);
        }

        gender.setText(intent.getStringExtra("gender"));
        bmidisplay.setText(mbmi);

        recalbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(bmicalc.this,bmi.class);
                startActivity(intent);
                finish();
            }
        });

    }
}