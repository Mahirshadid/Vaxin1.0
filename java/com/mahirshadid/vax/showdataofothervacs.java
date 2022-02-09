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
import com.mahirshadid.vax.reg.adapterOthervac;

import java.util.ArrayList;
import java.util.List;

public class showdataofothervacs extends AppCompatActivity {

    private RecyclerView rabies,ri,je,yf;
    private LinearLayout rabiesnodata,rinodata,yfnodata,jenodata;
    private List<othervaccineData> list11, list22, list33, list44;
    private adapterOthervac adapter2;

    private DatabaseReference reference,dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdataofothervacs);

        rabies=findViewById(R.id.rabiesdata);
        ri=findViewById(R.id.ridata);
        yf=findViewById(R.id.yellowfeverdata);
        je=findViewById(R.id.jedata);

        rabiesnodata=findViewById(R.id.rabiesnodata);
        rinodata=findViewById(R.id.rinodata);
        yfnodata=findViewById(R.id.yellowfevernodata);
        jenodata=findViewById(R.id.jenodata);

        reference= FirebaseDatabase.getInstance().getReference().child("OtherVaccineDatabase");

        Toast.makeText(showdataofothervacs.this,"Loading...",Toast.LENGTH_SHORT).show();

        rabiesfunc();
        rifunc();
        yffunc();
        jefunc();


    }

    private void rabiesfunc() {
        dbref=reference.child("Rabies");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list11=new ArrayList<>();
                if(!snapshot.exists()){
                    rabiesnodata.setVisibility(View.VISIBLE);
                    rabies.setVisibility(View.GONE);
                }else{
                    rabiesnodata.setVisibility(View.GONE);
                    rabies.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        othervaccineData data1=snapshot1.getValue(othervaccineData.class);
                        list11.add(data1);
                    }
                    rabies.setHasFixedSize(true);
                    rabies.setLayoutManager(new LinearLayoutManager(showdataofothervacs.this));
                    adapter2=new adapterOthervac(list11,showdataofothervacs.this,"Rabies");
                    rabies.setAdapter(adapter2);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(showdataofothervacs.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void yffunc() {
        dbref=reference.child("Yellow-Fever");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list33=new ArrayList<>();
                if(!snapshot.exists()){
                    yfnodata.setVisibility(View.VISIBLE);
                    yf.setVisibility(View.GONE);
                }else{
                    yfnodata.setVisibility(View.GONE);
                    yf.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        othervaccineData data1=snapshot1.getValue(othervaccineData.class);
                        list33.add(data1);
                    }
                    yf.setHasFixedSize(true);
                    yf.setLayoutManager(new LinearLayoutManager(showdataofothervacs.this));
                    adapter2=new adapterOthervac(list33,showdataofothervacs.this,"Yellow-Fever");
                    yf.setAdapter(adapter2);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(showdataofothervacs.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void rifunc() {
        dbref=reference.child("Routine Immunization");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list22=new ArrayList<>();
                if(!snapshot.exists()){
                    rinodata.setVisibility(View.VISIBLE);
                    ri.setVisibility(View.GONE);
                }else{
                    rinodata.setVisibility(View.GONE);
                    ri.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        othervaccineData data1=snapshot1.getValue(othervaccineData.class);
                        list22.add(data1);
                    }
                    ri.setHasFixedSize(true);
                    ri.setLayoutManager(new LinearLayoutManager(showdataofothervacs.this));
                    adapter2=new adapterOthervac(list22,showdataofothervacs.this,"Routine Immunization");
                    ri.setAdapter(adapter2);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(showdataofothervacs.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void jefunc() {
        dbref=reference.child("Japanese Encephalitis");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list44=new ArrayList<>();
                if(!snapshot.exists()){
                    jenodata.setVisibility(View.VISIBLE);
                    je.setVisibility(View.GONE);
                }else{
                    jenodata.setVisibility(View.GONE);
                    je.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        othervaccineData data1=snapshot1.getValue(othervaccineData.class);
                        list44.add(data1);
                    }
                    je.setHasFixedSize(true);
                    je.setLayoutManager(new LinearLayoutManager(showdataofothervacs.this));
                    adapter2=new adapterOthervac(list44,showdataofothervacs.this,"Japanese Encephalitis");
                    je.setAdapter(adapter2);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(showdataofothervacs.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}