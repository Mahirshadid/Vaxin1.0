package com.mahirshadid.vax;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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
import com.mahirshadid.vax.reg.adapterBottomadmin;

import java.util.ArrayList;
import java.util.List;

public class bottomadminshowvacdate extends AppCompatActivity {

    private RecyclerView datedata;
    private LinearLayout datenodata;
    private List<Dataadmin> list11;
    private adapterBottomadmin adapter2;

    private DatabaseReference reference,dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomadminshowvacdate);

        datedata=findViewById(R.id.datedata);


        datenodata=findViewById(R.id.datenodata);

        reference= FirebaseDatabase.getInstance().getReference().child("Datebase");

        Toast.makeText(bottomadminshowvacdate.this,"Loading...",Toast.LENGTH_SHORT).show();

        datefunc();

    }

    private void datefunc() {
        dbref=reference;
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list11=new ArrayList<>();
                if(!snapshot.exists()){
                    datenodata.setVisibility(View.VISIBLE);
                    datedata.setVisibility(View.GONE);
                }else{
                    datenodata.setVisibility(View.GONE);
                    datedata.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        Dataadmin data1=snapshot1.getValue(Dataadmin.class);
                        list11.add(data1);
                    }
                    datedata.setHasFixedSize(true);
                    datedata.setLayoutManager(new LinearLayoutManager(bottomadminshowvacdate.this));
                    adapter2=new adapterBottomadmin(list11,bottomadminshowvacdate.this);
                    datedata.setAdapter(adapter2);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(bottomadminshowvacdate.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}