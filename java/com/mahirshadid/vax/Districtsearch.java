package com.mahirshadid.vax;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.mahirshadid.vax.reg.adapterCTG;

import java.util.ArrayList;
import java.util.List;

public class Districtsearch extends AppCompatActivity {

    private Spinner locationspinner;
    Button dsearch;
    TextView locationame;

    private RecyclerView ctgdata;
    private LinearLayout ctgnodata;
    private List<referencedivision> list1;
    private adapterCTG adapter1;

    private DatabaseReference reference, dbref;
    String location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_districtsearch);

        ctgdata = findViewById(R.id.ctgdata);
        locationspinner = findViewById(R.id.locationspinner);
        dsearch = findViewById(R.id.dsearch);
        locationame = findViewById(R.id.locationname);

        ctgnodata = findViewById(R.id.ctgnodata);

        reference = FirebaseDatabase.getInstance().getReference().child("ReferenceDivision");

        String[] items=new String[]{"Select Location","CHITTAGONG","DHAKA","SYLHET","MYMENSINGH","RAJSHAHI","RANGPUR"
                ,"BARISHAL","KHULNA"};

        locationspinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items));

        locationspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                location=locationspinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(location.equals("Select Location")){
                    Toast.makeText(Districtsearch.this, "Select Location First", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Districtsearch.this, "Loading...", Toast.LENGTH_SHORT).show();
                    locationame.setText(location);
                    ctgfunc();
                }
            }
        });
    }

    private void ctgfunc() {
        dbref=reference.child(location);
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list1=new ArrayList<>();
                if(!snapshot.exists()){
                    ctgnodata.setVisibility(View.VISIBLE);
                    ctgdata.setVisibility(View.GONE);
                }else{
                    ctgnodata.setVisibility(View.GONE);
                    ctgdata.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        referencedivision data1=snapshot1.getValue(referencedivision.class);
                        list1.add(data1);
                    }
                    ctgdata.setHasFixedSize(true);
                    ctgdata.setLayoutManager(new LinearLayoutManager(Districtsearch.this));
                    adapter1=new adapterCTG(list1,Districtsearch.this);
                    ctgdata.setAdapter(adapter1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Districtsearch.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}