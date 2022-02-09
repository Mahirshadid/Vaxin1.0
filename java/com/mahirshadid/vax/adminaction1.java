package com.mahirshadid.vax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class adminaction1 extends AppCompatActivity {

    private EditText updatenid,updatename,updatepass,updatebirth,updatemobile,updateloc;
    private Button updatebutton;
    private String name,pass,loc,nid,birth,mobile;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminaction1);

        name=getIntent().getStringExtra("nam");
        pass=getIntent().getStringExtra("pass");
        loc=getIntent().getStringExtra("loc");
        nid=getIntent().getStringExtra("nid");
        birth=getIntent().getStringExtra("birth");
        mobile=getIntent().getStringExtra("mob");

        updatenid=findViewById(R.id.updatenid);
        updatename=findViewById(R.id.updatename);
        updatepass=findViewById(R.id.updatepass);
        updatebirth=findViewById(R.id.updatebirth);
        updatemobile=findViewById(R.id.updatemobile);
        updateloc=findViewById(R.id.updateloc);

        updatebutton=findViewById(R.id.updatebutton);

        reference= FirebaseDatabase.getInstance().getReference().child("Database");

        updatenid.setText(nid);
        updatename.setText(name);
        updatepass.setText(pass);
        updatebirth.setText(birth);
        updatemobile.setText(mobile);
        updateloc.setText(loc);

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= updatename.getText().toString();
                pass= updatepass.getText().toString().trim();
                mobile= updatemobile.getText().toString().trim();
                loc= updateloc.getText().toString().trim().toUpperCase();
                birth= updatebirth.getText().toString().trim();
                nid= updatenid.getText().toString().trim();
                checkvalidation();
            }

            private void checkvalidation() {
                String idloc=loc;
                if(name.isEmpty()){
                    updatename.setError("Empty");
                    updatename.requestFocus();
                }else if(nid.isEmpty()){
                    updatenid.setError("Empty");
                    updatenid.requestFocus();
                }else if(nid.length()<10 | nid.length()>13){
                    updatenid.setError("Invalid NID");
                    updatenid.requestFocus();
                }else if(mobile.isEmpty()){
                    updatemobile.setError("Empty");
                    updatemobile.requestFocus();
                }else if(mobile.length()<11 | mobile.length()>11){
                    updatemobile.setError("Invalid Mobile");
                    updatemobile.requestFocus();
                }else if(!mobile.trim().startsWith("0")){
                    updatemobile.setError("Invalid Mobile");
                    updatemobile.requestFocus();
                }else if(loc.isEmpty()){
                    updateloc.setError("Empty");
                    updateloc.requestFocus();
                }else if(!idloc.equals("CHITTAGONG") &&
                        !idloc.equals("DHAKA") &&
                        !idloc.equals("SYLHET") &&
                        !idloc.equals("RAJSHAHI") &&
                        !idloc.equals("MYMENSINGH") &&
                        !idloc.equals("BARISHAL") &&
                        !idloc.equals("RANGPUR") &&
                        !idloc.equals("KHULNA")){
                    updateloc.setError("Invalid Division");
                    updateloc.requestFocus();
                }else if(birth.isEmpty()){
                    updatebirth.setError("Empty");
                    updatebirth.requestFocus();
                }else if(birth.length()>4 | birth.length()<4){
                    updatebirth.setError("Invalid Birth Year");
                    updatebirth.requestFocus();
                }else if(!birth.trim().startsWith("1") && !birth.trim().startsWith("2")){
                    updatebirth.setError("Invalid Birth Year");
                    updatebirth.requestFocus();
                }else if(pass.isEmpty()){
                    updatepass.setError("Empty");
                    updatepass.requestFocus();
                }else{
                    updatedata();
                }
            }
        });


    }

    private void updatedata() {
        HashMap hp= new  HashMap();
        hp.put("nam",name);
        hp.put("pass",pass);
        hp.put("mob",mobile);
        hp.put("nid",nid);
        hp.put("loc",loc);
        hp.put("birth",birth);

        String uniquekey= getIntent().getStringExtra("key");
        String category= getIntent().getStringExtra("category");;
        reference.child(category).child(uniquekey).updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(adminaction1.this,"Updated successfully",Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(adminaction1.this, updatedb.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(adminaction1.this,"Something went wrong",Toast.LENGTH_LONG).show();
            }
        });
    }
}