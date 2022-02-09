package com.mahirshadid.vax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adminhosembentry extends AppCompatActivity {

    Spinner location,type,available;
    EditText name,phone;
    Button submit;

    String nametext,phonetext,locationtext,typetext,avtext;
    private DatabaseReference databaseReference,dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhosembentry);

        location=findViewById(R.id.locationofha);
        type=findViewById(R.id.typeofha);
        available=findViewById(R.id.availableofha);
        name=findViewById(R.id.nameofha);
        phone=findViewById(R.id.phoneofha);
        submit=findViewById(R.id.submissionha);

        nametext=name.getText().toString().trim();
        phonetext=phone.getText().toString().trim();

        dbref= FirebaseDatabase.getInstance().getReference().child("H&A");

        String[] items1=new String[]{"Select Location","CHITTAGONG","DHAKA","SYLHET","MYMENSINGH","RAJSHAHI","RANGPUR"
                ,"BARISHAL","KHULNA"};
        location.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1));

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                locationtext=location.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] items2=new String[]{"Select Service Type","Hospital-Medical","Ambulance"};
        type.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2));

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typetext=type.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] items3=new String[]{"Select Availability Hour","24-7","12-7"};
        available.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3));

        available.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                avtext=available.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().trim().isEmpty()){
                    name.setError("Empty");
                    name.requestFocus();
                }else if(phone.getText().toString().trim().isEmpty()){
                    phone.setError("Empty");
                    phone.requestFocus();
                }else if(locationtext.equals("Select Location")){
                    Toast.makeText(adminhosembentry.this,"Please select Location",Toast.LENGTH_SHORT).show();
                }else if(typetext.equals("Select Service Type")){
                    Toast.makeText(adminhosembentry.this,"Please Select Service Type",Toast.LENGTH_SHORT).show();
                }else if(avtext.equals("Select Availability Hour")){
                    Toast.makeText(adminhosembentry.this,"Please Select Availability Hour",Toast.LENGTH_SHORT).show();
                }else{
                    insertData();
                }
            }
        });

    }

    private void insertData() {
        String Name=name.getText().toString().toUpperCase().trim();
        String Phone=phone.getText().toString().trim();
        String Location=locationtext;
        String Type=typetext;
        String Available=avtext;

        DataforAMBhos dataforAMBhos = new DataforAMBhos(Name,Phone,Location,Type,Available);
        dbref.child(Location).child(Type).child(Phone).setValue(dataforAMBhos);
        Toast.makeText(adminhosembentry.this,"Registration Complete",Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(adminhosembentry.this,adminselect.class);
        startActivity(intent);
        finish();
    }
}