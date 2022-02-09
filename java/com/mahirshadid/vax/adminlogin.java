package com.mahirshadid.vax;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class adminlogin extends AppCompatActivity {

    private EditText pin;
    private Button pinbutton;
    private ProgressDialog pd;
    EditText etto,callto;
    Button emailbtn,callbutton;
    private Spinner role;
    private String rolename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        pin=findViewById(R.id.admintext);
        pinbutton=findViewById(R.id.adminbutton);

        pd=new ProgressDialog(this);

        etto=findViewById(R.id.to);
        callto=findViewById(R.id.callto);
        callbutton=findViewById(R.id.callbutton);
        emailbtn=findViewById(R.id.contactadmin);

        role=findViewById(R.id.role);

        String[] items=new String[]{"Select Role","High-Level","Mid-Level","Bottom-Level"};
        role.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items));

        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rolename=role.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        emailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + etto.getText().toString()));
                startActivity(intent);
            }
        });

        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = callto.getText().toString();
                String s= "tel:" + phone;
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(s));
                startActivity(intent);
            }
        });

        pinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pin.getText().toString().isEmpty()){
                    pin.setError("Empty");
                    pin.requestFocus();
                }else if(rolename.equals("Select Role")){
                    Toast.makeText(adminlogin.this,"Please Select your Role!",Toast.LENGTH_SHORT).show();
                }else if(rolename.equals("High-Level") && pin.getText().toString().trim().equals("123")){
                    Intent intent= new Intent(adminlogin.this,adminselect.class);
                    startActivity(intent);
                    finish();
                }else if(rolename.equals("Mid-Level") && pin.getText().toString().trim().equals("1234")){
                    Intent intent= new Intent(adminlogin.this,midadminselect.class);
                    startActivity(intent);
                    finish();
                }else if(rolename.equals("Bottom-Level") && pin.getText().toString().trim().equals("12345")){
                    Intent intent= new Intent(adminlogin.this,bottomadminselect.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(adminlogin.this,"Wrong PIN",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}