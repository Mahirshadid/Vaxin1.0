package com.mahirshadid.vax;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class nidsearch extends AppCompatActivity {

    EditText nidsearchh;
    Button search;
    String nidsearchedit;
    TextView nidtext1,datetext1,vactext1,loctext1,datetext2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nidsearch);

        nidsearchh=findViewById(R.id.nidsearch);
        search=findViewById(R.id.search);

        nidtext1=findViewById(R.id.nidtext1);
        vactext1=findViewById(R.id.vactext1);
        datetext1=findViewById(R.id.datetext1);
        datetext2=findViewById(R.id.datetext2);
        loctext1=findViewById(R.id.loctext1);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("vaccinetakers");

        search.setOnClickListener(v -> {

            nidsearchedit= nidsearchh.getText().toString().trim();

            Query id = ref.orderByChild("nid").equalTo(nidsearchedit);
            id.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){

                        if(nidsearchh.getText().toString().trim().isEmpty()){
                            nidsearchh.setError("Empty");
                            nidsearchh.requestFocus();
                        }
                        else{
                            String nidfromdb = snapshot.child(nidsearchedit).child("nid").getValue(String.class);
                            assert nidfromdb != null;
                            if(nidfromdb.equals(nidsearchedit)){
                                String date1fromdb = snapshot.child(nidsearchedit).child("date1").getValue(String.class);
                                String date2fromdb = snapshot.child(nidsearchedit).child("date2").getValue(String.class);;
                                String locfromdb = snapshot.child(nidsearchedit).child("location").getValue(String.class);
                                String vacfromdb = snapshot.child(nidsearchedit).child("vac").getValue(String.class);


                                nidtext1.setText(nidfromdb);
                                datetext1.setText(date1fromdb);
                                datetext2.setText(date2fromdb);
                                loctext1.setText(locfromdb);
                                vactext1.setText(vacfromdb);
                            }
                        }
                    }else{
                        Toast.makeText(nidsearch.this,"NID information isn't found", Toast.LENGTH_SHORT).show();
                        nidtext1.setText("Not Available");
                        datetext1.setText("Not Available");
                        datetext2.setText("Not Available");
                        loctext1.setText("Not Available");
                        vactext1.setText("Not Available");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(nidsearch.this,"Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        });


    }
}