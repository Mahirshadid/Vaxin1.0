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
import com.mahirshadid.vax.reg.adapterforbottomshow;

import java.util.ArrayList;
import java.util.List;

public class bottomshowregmembers extends AppCompatActivity {

    private RecyclerView pm,student,fline,doctor,teacher,nid;
    private LinearLayout pmnodata,studentnodata,flinenodata,doctornodata,teachernodata,nidnodata;
    private List<Data> list1, list2, list3, list4,list5,list6;
    private adapterforbottomshow adapter1;

    private DatabaseReference reference,dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomshowregmembers);

        pm=findViewById(R.id.pm);
        student=findViewById(R.id.student);
        fline=findViewById(R.id.fline);
        doctor=findViewById(R.id.doctor);
        teacher=findViewById(R.id.teacher);
        nid=findViewById(R.id.nid);

        pmnodata=findViewById(R.id.pmnodata);
        studentnodata=findViewById(R.id.studentnodata);
        flinenodata=findViewById(R.id.flinenodata);
        doctornodata=findViewById(R.id.doctornodata);
        teachernodata=findViewById(R.id.teachernodata);
        nidnodata=findViewById(R.id.nidnodata);

        reference= FirebaseDatabase.getInstance().getReference().child("Database");

        Toast.makeText(bottomshowregmembers.this,"Loading...",Toast.LENGTH_LONG).show();

        pm();
        student();
        fline();
        doctor();
        teacher();
        nid();
    }

    private void fline() {
        dbref=reference.child("Front-line Worker");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list3=new ArrayList<>();
                if(!snapshot.exists()){
                    flinenodata.setVisibility(View.VISIBLE);
                    fline.setVisibility(View.GONE);
                }else{
                    flinenodata.setVisibility(View.GONE);
                    fline.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        Data data=snapshot1.getValue(Data.class);
                        list3.add(data);
                    }
                    fline.setHasFixedSize(true);
                    fline.setLayoutManager(new LinearLayoutManager(bottomshowregmembers.this));
                    adapter1=new adapterforbottomshow(list3,bottomshowregmembers.this,"Front-line Worker");
                    fline.setAdapter(adapter1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(bottomshowregmembers.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doctor() {
        dbref=reference.child("Doctor");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list4=new ArrayList<>();
                if(!snapshot.exists()){
                    doctornodata.setVisibility(View.VISIBLE);
                    doctor.setVisibility(View.GONE);
                }else{
                    doctornodata.setVisibility(View.GONE);
                    doctor.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        Data data=snapshot1.getValue(Data.class);
                        list4.add(data);
                    }
                    doctor.setHasFixedSize(true);
                    doctor.setLayoutManager(new LinearLayoutManager(bottomshowregmembers.this));
                    adapter1=new adapterforbottomshow(list4,bottomshowregmembers.this,"Doctor");
                    doctor.setAdapter(adapter1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(bottomshowregmembers.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void pm() {
        dbref=reference.child("Parliament Member");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list1=new ArrayList<>();
                if(!snapshot.exists()){
                    pmnodata.setVisibility(View.VISIBLE);
                    pm.setVisibility(View.GONE);
                }else{
                    pmnodata.setVisibility(View.GONE);
                    pm.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        Data data=snapshot1.getValue(Data.class);
                        list1.add(data);
                    }
                    pm.setHasFixedSize(true);
                    pm.setLayoutManager(new LinearLayoutManager(bottomshowregmembers.this));
                    adapter1=new adapterforbottomshow(list1,bottomshowregmembers.this,"Parliament Member");
                    pm.setAdapter(adapter1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(bottomshowregmembers.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void teacher() {
        dbref=reference.child("Teacher");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list5=new ArrayList<>();
                if(!snapshot.exists()){
                    teachernodata.setVisibility(View.VISIBLE);
                    teacher.setVisibility(View.GONE);
                }else{
                    teachernodata.setVisibility(View.GONE);
                    teacher.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        Data data=snapshot1.getValue(Data.class);
                        list5.add(data);
                    }
                    teacher.setHasFixedSize(true);
                    teacher.setLayoutManager(new LinearLayoutManager(bottomshowregmembers.this));
                    adapter1=new adapterforbottomshow(list5,bottomshowregmembers.this,"Teacher");
                    teacher.setAdapter(adapter1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(bottomshowregmembers.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void student() {
        dbref=reference.child("Student");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list2=new ArrayList<>();
                if(!snapshot.exists()){
                    studentnodata.setVisibility(View.VISIBLE);
                    student.setVisibility(View.GONE);
                }else{
                    studentnodata.setVisibility(View.GONE);
                    student.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        Data data=snapshot1.getValue(Data.class);
                        list2.add(data);
                    }
                    student.setHasFixedSize(true);
                    student.setLayoutManager(new LinearLayoutManager(bottomshowregmembers.this));
                    adapter1=new adapterforbottomshow(list2,bottomshowregmembers.this,"Student");
                    student.setAdapter(adapter1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(bottomshowregmembers.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void nid() {
        dbref=reference.child("18+ NID-Birth-certificate Holder");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list6=new ArrayList<>();
                if(!snapshot.exists()){
                    nidnodata.setVisibility(View.VISIBLE);
                    nid.setVisibility(View.GONE);
                }else{
                    nidnodata.setVisibility(View.GONE);
                    nid.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        Data data=snapshot1.getValue(Data.class);
                        list6.add(data);
                    }
                    nid.setHasFixedSize(true);
                    nid.setLayoutManager(new LinearLayoutManager(bottomshowregmembers.this));
                    adapter1=new adapterforbottomshow(list6,bottomshowregmembers.this,"18+ NID-Birth-certificate Holder");
                    nid.setAdapter(adapter1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(bottomshowregmembers.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}