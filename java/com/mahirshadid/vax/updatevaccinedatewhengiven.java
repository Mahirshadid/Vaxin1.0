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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updatevaccinedatewhengiven extends AppCompatActivity {

     Spinner locationspin,vaccinename;
     CalendarView calendarView;
     Button submit,gotosec;
     String loc,date,vaccine,day;
     String nidstr,locstr,vacstr;
     EditText nid;

    private DatabaseReference databaseReference,dbref,dbrefdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatevaccinedatewhengiven);

        locationspin=findViewById(R.id.locationspin);
        vaccinename=findViewById(R.id.vaccinename);
        calendarView=findViewById(R.id.calendar);
        submit=findViewById(R.id.submit);
        nid=findViewById(R.id.nidgiver);
        gotosec=findViewById(R.id.gotoseconddose);

        dbref= FirebaseDatabase.getInstance().getReference().child("vaccinetakers");
        dbrefdate= FirebaseDatabase.getInstance().getReference().child("datesearchdb");
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Reference");



        String[] items=new String[]{"Select Location","CHITTAGONG","DHAKA","SYLHET","MYMENSINGH","RAJSHAHI","RANGPUR"
        ,"BARISHAL","KHULNA"};

        locationspin.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items));

        locationspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loc=locationspin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        String[] items2=new String[]{"Select Vaccine Name","Pfizer","Cinopharma","VEROCELL","Moderna","Astrageneca"};

        vaccinename.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2));

        vaccinename.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vaccine=vaccinename.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        nidstr=getIntent().getStringExtra("nid");
        locstr=getIntent().getStringExtra("loc");

        nid.setText(nidstr);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month=month+1;
                date = "First Dose" + " : " + dayOfMonth + "-" + month + "-" + year;
                day = dayOfMonth + "-" + month + "-" + year;
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id= nid.getText().toString().trim();

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(nid.getText().toString().trim().isEmpty()){
                            nid.setError("Empty");
                            nid.requestFocus();
                        }else if(loc.equals("Select Location")){
                            Toast.makeText(updatevaccinedatewhengiven.this,"Please select location",Toast.LENGTH_SHORT).show();
                        }else if(vaccine.equals("Select Vaccine Name")){
                            Toast.makeText(updatevaccinedatewhengiven.this,"Please select Vaccine Name",Toast.LENGTH_SHORT).show();
                        }else if(date.equals("0")){
                            Toast.makeText(updatevaccinedatewhengiven.this,"Please select date",Toast.LENGTH_SHORT).show();
                        }else if(!snapshot.hasChild(id)){
                            Toast.makeText(updatevaccinedatewhengiven.this,"NID is not registered yet",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            inserData();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        gotosec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updatevaccinedatewhengiven.this,updatevaccinedateseconddose.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void inserData() {


        String nidd= nid.getText().toString().trim();
        String location = loc;
        String datee = date;
        String vac = vaccine;
        String datee2 = null;


        adminvaccineData data=new adminvaccineData(nidd,location,datee,vac);
        dbref.child(nidd).setValue(data);
        Dataofdatesearch2 dataofdatesearch= new Dataofdatesearch2(nidd,location,datee,vac,datee2);
        dbrefdate.child(day).child(nidd).setValue(dataofdatesearch);

        Toast.makeText(updatevaccinedatewhengiven.this,"Submitted", Toast.LENGTH_SHORT).show();

    }
}