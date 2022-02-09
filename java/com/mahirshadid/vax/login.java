package com.mahirshadid.vax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class login extends AppCompatActivity {

    private Button loginbutton;
    private EditText nidlogin,mobilelogin,passlogin;
    private DatabaseReference query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mobilelogin=findViewById(R.id.mobilelogin);
        passlogin=findViewById(R.id.passlogin);

        loginbutton=findViewById(R.id.loginbutton);

        query= FirebaseDatabase.getInstance().getReference().child("Reference2");


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id1=mobilelogin.getText().toString();
                String id2=passlogin.getText().toString();

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(mobilelogin.getText().toString().isEmpty()){
                            mobilelogin.setError("Empty");
                            mobilelogin.requestFocus();
                        }else if(passlogin.getText().toString().isEmpty()){
                            passlogin.setError("Empty");
                            passlogin.requestFocus();
                        }
                        else{
                            isUser();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(login.this,"Login failed",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        

    }

    private void isUser() {
        final String usermobile = mobilelogin.getText().toString().trim();
        String userpass = passlogin.getText().toString().trim();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Reference2");
        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Datebase");
        Query checkuser = ref.orderByChild("referencemobile").equalTo(usermobile);

        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    String passwordfromdb = snapshot.child(usermobile).child("referencepass").getValue(String.class);

                    if(passwordfromdb.equals(userpass)){
                       String mobilefromdb = snapshot.child(usermobile).child("referencemobile").getValue(String.class);

                       Query checkdate = ref2.orderByChild(usermobile);
                       checkdate.addListenerForSingleValueEvent(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot snapshot1) {
                               String datefromdb = snapshot1.child(usermobile).child("date").getValue(String.class);

                               Intent intent = new Intent(getApplicationContext(),afterlogin.class);
                               intent.putExtra("referencemobile",mobilefromdb);
                               intent.putExtra("date",datefromdb);
                               Toast.makeText(login.this, "Login Successful", Toast.LENGTH_LONG).show();
                               startActivity(intent);
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }
                       });

                    } else
                    {
                        Toast.makeText(login.this, "Password is wrong", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(login.this, "No such user exists", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}