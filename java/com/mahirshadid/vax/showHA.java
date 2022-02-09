package com.mahirshadid.vax;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mahirshadid.vax.reg.adapterHA;

import java.util.ArrayList;
import java.util.List;

public class showHA extends AppCompatActivity {

    private RecyclerView hospital,ambulance;
    private LinearLayout hospitalnodata,ambulancenodata;
    private List<DataforAMBhos> list1, list2;
    private adapterHA adapter1;
    Spinner location;
    String locationtext;
    Button search;

    private DatabaseReference reference,dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ha);

        hospital=findViewById(R.id.hadata);
        ambulance=findViewById(R.id.ambdata);
        location=findViewById(R.id.halocspinner);
        search=findViewById(R.id.hasearch);

        hospitalnodata=findViewById(R.id.hanodata);
        ambulancenodata=findViewById(R.id.ambnodata);


        reference= FirebaseDatabase.getInstance().getReference().child("H&A");

        String[] items=new String[]{"Select Location","CHITTAGONG","DHAKA","SYLHET","MYMENSINGH","RAJSHAHI","RANGPUR"
                ,"BARISHAL","KHULNA"};

        location.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items));

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                locationtext=location.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(locationtext.equals("Select Location")){
                    Toast.makeText(showHA.this, "Select Location First", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(showHA.this, "Loading...", Toast.LENGTH_SHORT).show();
                    hospitalfunc();
                    ambulancefunc();
                }
            }
        });
    }

    private void hospitalfunc() {
        dbref=reference.child(locationtext).child("Hospital-Medical");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list1=new ArrayList<>();
                if(!snapshot.exists()){
                    hospitalnodata.setVisibility(View.VISIBLE);
                    hospital.setVisibility(View.GONE);
                }else{
                    hospitalnodata.setVisibility(View.GONE);
                    hospital.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        DataforAMBhos data=snapshot1.getValue(DataforAMBhos.class);
                        list1.add(data);
                    }
                    hospital.setHasFixedSize(true);
                    hospital.setLayoutManager(new LinearLayoutManager(showHA.this));
                    adapter1=new adapterHA(list1,showHA.this);
                    hospital.setAdapter(adapter1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(showHA.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ambulancefunc() {
        dbref=reference.child(locationtext).child("Ambulance");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list2=new ArrayList<>();
                if(!snapshot.exists()){
                    ambulancenodata.setVisibility(View.VISIBLE);
                    ambulance.setVisibility(View.GONE);
                }else{
                    ambulancenodata.setVisibility(View.GONE);
                    ambulance.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        DataforAMBhos data=snapshot1.getValue(DataforAMBhos.class);
                        list2.add(data);
                    }
                    ambulance.setHasFixedSize(true);
                    ambulance.setLayoutManager(new LinearLayoutManager(showHA.this));
                    adapter1=new adapterHA(list2,showHA.this);
                    ambulance.setAdapter(adapter1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(showHA.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}