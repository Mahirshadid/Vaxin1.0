package com.mahirshadid.vax;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;


public class UpN extends AppCompatActivity {

    private EditText regname;
    private EditText regpass,regpasscon,mobile,regnid,regbirth;
    private Spinner category,regloc,regdiv;

    private Button regbtn;

    private String cat,loc,div;



    private DatabaseReference databaseReference,dbref,reference2,query,reference3,refdivision;

    private ProgressDialog pd;

    Button regcap;
    private EditText regcaptext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_n);

        regname=findViewById(R.id.regname);
        regpass=findViewById(R.id.regpass);
        regpasscon=findViewById(R.id.regpassconfirm);
        mobile=findViewById(R.id.regphone);
        regnid=findViewById(R.id.regnid);
        regbirth=findViewById(R.id.regbirth);
        regloc=findViewById(R.id.regloc);



        regcaptext=findViewById(R.id.regcaptext);

        category=findViewById(R.id.category);
        regbtn=findViewById(R.id.regbutton);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Database");
        reference2= FirebaseDatabase.getInstance().getReference().child("Reference");
        reference3=FirebaseDatabase.getInstance().getReference().child("Reference2");
        query= FirebaseDatabase.getInstance().getReference().child("Reference");
        refdivision= FirebaseDatabase.getInstance().getReference().child("ReferenceDivision");

        pd=new ProgressDialog(this);
        final Random random= new Random();

        regcap=findViewById(R.id.regcap);
        final TextView captext=findViewById(R.id.textcap);

        regcap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captext.setText(String.valueOf(random.nextInt(100000)));
            }
        });

        String[] items1=new String[]{"Select Location","CHITTAGONG","DHAKA","SYLHET","MYMENSINGH","RAJSHAHI","RANGPUR"
                ,"BARISHAL","KHULNA"};
        regloc.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1));

        regloc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loc=regloc.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] items=new String[]{"Select Category","Front-line Worker","Doctor","Parliament Member","Teacher","Student","18+ NID-Birth-certificate Holder"};
        category.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items));

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat=category.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=regnid.getText().toString().trim();
                String id2=mobile.getText().toString().trim();

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                if(regname.getText().toString().isEmpty()){
                                    regname.setError("Empty");
                                    regname.requestFocus();
                                }else if(regpass.getText().toString().trim().isEmpty()){
                                    regpass.setError("Empty");
                                    regpass.requestFocus();
                                }else if(!regpass.getText().toString().trim().equals(regpasscon.getText().toString())){
                                    regpass.setError("Password Doesn't Match");
                                    regpass.requestFocus();
                                }else if(regpasscon.getText().toString().trim().isEmpty()){
                                    regpasscon.setError("Empty");
                                    regpasscon.requestFocus();
                                }else if(mobile.getText().toString().trim().isEmpty()){
                                    mobile.setError("Empty");
                                    mobile.requestFocus();
                                }else if(mobile.length()<11 | mobile.length()>11){
                                    mobile.setError("Invalid Mobile");
                                    mobile.requestFocus();
                                }else if(!mobile.getText().toString().trim().startsWith("0")){
                                    mobile.setError("Invalid Mobile");
                                    mobile.requestFocus();
                                }else if(regnid.getText().toString().trim().isEmpty()){
                                    regnid.setError("Empty");
                                    regnid.requestFocus();
                                }else if(regnid.length()<10 | regnid.length()>13){
                                    regnid.setError("Invalid NID");
                                    regnid.requestFocus();
                                }else if(!captext.getText().toString().trim().equals(regcaptext.getText().toString())){
                                    regcaptext.setError("Doesn't Match");
                                    regcaptext.requestFocus();
                                }else if(regbirth.getText().toString().isEmpty()){
                                    regbirth.setError("Empty");
                                    regbirth.requestFocus();
                                }else if(regbirth.length()>4 | regbirth.length()<4){
                                    regbirth.setError("Invalid Birth Year");
                                    regbirth.requestFocus();
                                }else if(!regbirth.getText().toString().trim().startsWith("1") && !regbirth.getText().toString().trim().startsWith("2")){
                                    regbirth.setError("Invalid Birth Year");
                                    regbirth.requestFocus();
                                }else if(cat.equals("Select Category")){
                                    Toast.makeText(UpN.this,"Please select category",Toast.LENGTH_SHORT).show();
                                }else if(loc.equals("Select Location")){
                                    Toast.makeText(UpN.this,"Please select Location",Toast.LENGTH_SHORT).show();
                                }else if(snapshot.hasChild(id)){
                                    Toast.makeText(UpN.this,"NID/BC is already registered",Toast.LENGTH_LONG).show();
                                }else if(snapshot2.hasChild(id2)){
                                    Toast.makeText(UpN.this,"Mobile already registered",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    pd.setMessage("Processing");
                                    pd.show();
                                    insertData();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

    private void insertData() {

        dbref=databaseReference.child(cat);
        final String uniquekey=dbref.push().getKey();

        String name=regname.getText().toString().toUpperCase();
        String pass=regpass.getText().toString().trim();
        String passcon=regpasscon.getText().toString().trim();
        String mob=mobile.getText().toString().trim();
        String nid=regnid.getText().toString().trim();
        String birth=regbirth.getText().toString().trim();
        String Loc=loc;





        Data data=new Data(name,birth,pass,passcon,mob,nid,Loc,uniquekey);
        referencedata refdata=new referencedata(nid);
        reference2.child(nid).setValue(refdata);
        referencedata2 refdata2=new referencedata2(mob,pass);
        reference3.child(mob).setValue(refdata2);
        reference3.child(pass).setValue(refdata2);

        referencedivision referencedivisiondata = new referencedivision(name,birth,nid,Loc,mob);
        refdivision.child(loc).child(nid).setValue(referencedivisiondata);



        assert uniquekey != null;
        dbref.child(uniquekey).setValue(data).addOnSuccessListener(unused -> {
            pd.dismiss();
            Toast.makeText(UpN.this,"Registration complete", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(UpN.this,MainActivity.class);
            startActivity(intent);
        }).addOnFailureListener(e -> {
            pd.dismiss();
            Toast.makeText(UpN.this,"Something went wrong", Toast.LENGTH_SHORT).show();
        });
    }
}