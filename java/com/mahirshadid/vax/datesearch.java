package com.mahirshadid.vax;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
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
import com.mahirshadid.vax.reg.adapterDATESEARCH2;

import java.util.ArrayList;
import java.util.List;

public class datesearch extends AppCompatActivity {

    Button datesearch111;
    TextView datetag;
    CalendarView calendarView333;

    private RecyclerView datesearchdata;
    private LinearLayout dsnodata;
    private List<Dataofdatesearch2> list111;
    private adapterDATESEARCH2 adapter111;

    private DatabaseReference reference, dbref;
    String day333;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datesearch);

        datesearch111=findViewById(R.id.datesearch333);
        datetag=findViewById(R.id.datetag);
        datesearchdata=findViewById(R.id.datesearchdata);
        dsnodata=findViewById(R.id.datesearchnodata);
        calendarView333=findViewById(R.id.datecalendar333);

        reference = FirebaseDatabase.getInstance().getReference().child("datesearchdb");

        calendarView333.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month=month+1;
                day333 = dayOfMonth + "-" + month + "-" + year;
            }
        });

        datesearch111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(day333.equals("0")){
                    Toast.makeText(datesearch.this, "Select date first", Toast.LENGTH_SHORT).show();
                }else{
                    datetag.setText(day333);
                    datefunc();
                }
            }
        });
    }

    private void datefunc() {
        dbref=reference.child(day333);
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list111=new ArrayList<>();
                if(!snapshot.exists()){
                    dsnodata.setVisibility(View.VISIBLE);
                    datesearchdata.setVisibility(View.GONE);
                }else{
                    dsnodata.setVisibility(View.GONE);
                    datesearchdata.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        Dataofdatesearch2 data1=snapshot1.getValue(Dataofdatesearch2.class);
                        list111.add(data1);
                    }
                    datesearchdata.setHasFixedSize(true);
                    datesearchdata.setLayoutManager(new LinearLayoutManager(datesearch.this));
                    adapter111=new adapterDATESEARCH2(list111,datesearch.this);
                    datesearchdata.setAdapter(adapter111);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(datesearch.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}