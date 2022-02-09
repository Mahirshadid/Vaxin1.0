package com.mahirshadid.vax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class admindate extends AppCompatActivity {

    private EditText phone;
    private Button datebutton,updatebutton;
    private String mobile;
    CalendarView calendarView;
    Spinner dosenumber;

    private DatabaseReference reference,dbref;

    String date,dose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindate);

        mobile=getIntent().getStringExtra("mob");

        calendarView=findViewById(R.id.calendar);
        phone=findViewById(R.id.phone);
        datebutton=findViewById(R.id.datebutton);
        updatebutton=findViewById(R.id.updatebutton);
        dosenumber=findViewById(R.id.dosenumber);

        reference= FirebaseDatabase.getInstance().getReference().child("Datebase");
        dbref= FirebaseDatabase.getInstance().getReference().child("Datebase");

        phone.setText(mobile);




        String[] items1=new String[]{"Select Dose Number","First Dose","Second Dose"};

        dosenumber.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1));

        dosenumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dose=dosenumber.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month=month+1;
                date =dose + " : " + dayOfMonth + "/" + month + "/" + year;
            }
        });

        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=phone.getText().toString().trim();

                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        if(phone.getText().toString().trim().isEmpty()){
                            phone.setError("Empty");
                            phone.requestFocus();
                        } else if(snapshot.hasChild(id)){
                            Toast.makeText(admindate.this,"Date has been given already",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            insertData();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(admindate.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkvalid();
            }
        });


    }

    private void checkvalid() {

        String id1= date;
        String id2= phone.getText().toString().trim();

        HashMap hashMap = new HashMap();
        hashMap.put("date", id1);

        if(id2.isEmpty()) {
            phone.setError("Empty");
            phone.requestFocus();
        }else{
            dbref.child(id2).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                    Toast.makeText(admindate.this,"Date Updated",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(admindate.this,updatedb.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    private void insertData() {

        String Date = date;
        String Phone = phone.getText().toString();

        Dataadmin dataadmin = new Dataadmin(Phone,Date);
        reference.child(Phone).setValue(dataadmin);

        Toast.makeText(admindate.this,"Submitted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(admindate.this,updatedb.class);
        startActivity(intent);
        finish();

    }
}