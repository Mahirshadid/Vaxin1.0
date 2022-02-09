package com.mahirshadid.vax;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class updatevaccinedateseconddose extends AppCompatActivity {

    EditText nidsearchh,firstdose,locationspin,vaccinename;
    Button searchnid,submit;
    String searchnidedit,firstdosedit,locationedit,vaccineedit,date2nd,day;
    CalendarView calendarView2;
    String nidstr,locstr,vacstr;

    DatabaseReference databaseReference,dbref,dbrefdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatevaccinedateseconddose);

        nidsearchh=findViewById(R.id.nidgiver2);
        firstdose=findViewById(R.id.firstdose2);
        locationspin=findViewById(R.id.locationspin2);
        vaccinename=findViewById(R.id.vaccinename2);
        searchnid=findViewById(R.id.searchnid);
        submit=findViewById(R.id.submit2);
        calendarView2=findViewById(R.id.calendar2);

        dbref= FirebaseDatabase.getInstance().getReference().child("vaccinetakers");
        dbrefdate= FirebaseDatabase.getInstance().getReference().child("datesearchdb");
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Reference");

        calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month=month+1;
                date2nd = "Second Dose" + " : " + dayOfMonth + "-" + month + "-" + year;
                day = dayOfMonth + "-" + month + "-" + year;
            }
        });

        nidstr=getIntent().getStringExtra("nid");
        locstr=getIntent().getStringExtra("loc");

        nidsearchh.setText(nidstr);
        locationspin.setText(locstr);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("vaccinetakers");

        searchnid.setOnClickListener(v -> {

            searchnidedit= nidsearchh.getText().toString().trim();

            Query id = ref.orderByChild("nid").equalTo(searchnidedit);
            id.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){

                        if(searchnidedit.isEmpty()){
                            nidsearchh.setError("Empty");
                            nidsearchh.requestFocus();
                        }
                        else{
                            String nidfromdb = snapshot.child(searchnidedit).child("nid").getValue(String.class);
                            assert nidfromdb != null;
                            if(nidfromdb.equals(searchnidedit)){
                                String date1fromdb = snapshot.child(searchnidedit).child("date1").getValue(String.class);
                                String locfromdb = snapshot.child(searchnidedit).child("location").getValue(String.class);
                                String vacfromdb = snapshot.child(searchnidedit).child("vac").getValue(String.class);

                                firstdose.setText(date1fromdb);
                                locationspin.setText(locfromdb);
                                vaccinename.setText(vacfromdb);

                                firstdosedit=date1fromdb;
                                locationedit=locfromdb;
                                vaccineedit=vacfromdb;
                            }
                        }
                    }else{
                        Toast.makeText(updatevaccinedateseconddose.this,"NID information isn't found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(updatevaccinedateseconddose.this,"Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = searchnidedit;

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (!snapshot.hasChild(id)) {
                            Toast.makeText(updatevaccinedateseconddose.this, "NID is not registered yet", Toast.LENGTH_SHORT).show();
                        } else {

                            insertData();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

    private void insertData() {
        String nidd= nidsearchh.getText().toString().trim();
        String location = locationedit;
        String datee = firstdosedit;
        String vac = vaccineedit;
        String datee2 = date2nd;


        adminvaccineData2 data=new adminvaccineData2(nidd,location,datee,vac,datee2);
        dbref.child(nidd).setValue(data);
        Dataofdatesearch2 dataofdatesearch2= new Dataofdatesearch2(nidd,location,datee,vac,datee2);
        dbrefdate.child(day).child(nidd).setValue(dataofdatesearch2);
        Toast.makeText(updatevaccinedateseconddose.this,"Submitted", Toast.LENGTH_SHORT).show();
    }
}

