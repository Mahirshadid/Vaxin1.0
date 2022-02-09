package com.mahirshadid.vax;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;

public class othervaccineregistration extends AppCompatActivity {

    EditText nameov,birthov,mobileov;
    Spinner locov,vacov,emergencyov;
    Button regov;
    String catov,locationov,name,birthyear,mobile,emergency,currentdate;
    DatabaseReference databaseReference,dbref;
    private String filepath="https://drive.google.com/uc?export=download&id=1tvn1qAyOuU9mD8-R9JEFTbp_7RE61Sp0";
    private URL url = null;
    private String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_othervaccineregistration);

        nameov = findViewById(R.id.nameov);
        birthov = findViewById(R.id.birthov);
        mobileov = findViewById(R.id.mobileov);
        locov = findViewById(R.id.locationothervac);
        vacov = findViewById(R.id.categoryothervac);
        regov = findViewById(R.id.regbuttonothervac);
        emergencyov = findViewById(R.id.emergencyspin);

        name = nameov.getText().toString().toUpperCase().trim();
        birthyear = birthov.getText().toString().trim();
        mobile = mobileov.getText().toString().trim();

        databaseReference= FirebaseDatabase.getInstance().getReference().child("OtherVaccineDatabase");


        Calendar calendar111 = Calendar.getInstance();
        currentdate = DateFormat.getDateInstance().format(calendar111.getTime());

        String[] items3=new String[]{"Is it an Emergency?","Yes","No"};
        emergencyov.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3));

        emergencyov.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                emergency=emergencyov.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        String[] items=new String[]{"Select Category","Rabies","Yellow-Fever","Routine Immunization","Japanese Encephalitis"};
        vacov.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items));

        vacov.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                catov=vacov.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] items1=new String[]{"Select Location","CHITTAGONG","DHAKA","SYLHET","MYMENSINGH","RAJSHAHI","RANGPUR"
                ,"BARISHAL","KHULNA"};
        locov.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1));

        locov.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                locationov=locov.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        regov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameov.getText().toString().isEmpty()){
                    nameov.setError("Empty");
                    nameov.requestFocus();
                }else if(birthov.getText().toString().isEmpty()){
                    birthov.setError("Empty");
                    birthov.requestFocus();
                }else if(mobileov.getText().toString().isEmpty()){
                    mobileov.setError("Empty");
                    mobileov.requestFocus();
                }else if(!mobileov.getText().toString().startsWith("0")){
                    mobileov.setError("Invalid Number");
                    mobileov.requestFocus();
                }else if(!birthov.getText().toString().startsWith("1") && !birthov.getText().toString().startsWith("2")){
                    birthov.setError("Invalid Birth year");
                    birthov.requestFocus();
                }else if(mobileov.length()>11 && mobileov.length()<11){
                    mobileov.setError("Invalid Number");
                    mobileov.requestFocus();
                }else if(birthov.length()>4 && birthov.length()<4){
                    birthov.setError("Invalid Birth year");
                    birthov.requestFocus();
                }else if(catov.equals("Select Category")){
                    Toast.makeText(othervaccineregistration.this,"Please select category",Toast.LENGTH_SHORT).show();
                }else if(locationov.equals("Select Location")){
                    Toast.makeText(othervaccineregistration.this,"Please select location",Toast.LENGTH_SHORT).show();
                }else if(emergency.equals("Is it an Emergency?")){
                    Toast.makeText(othervaccineregistration.this,"Please select Yes or No",Toast.LENGTH_SHORT).show();
                }else{
                    insertData();
                    downloadcard();
                }
            }
        });

    }

    private void downloadcard() {

        try {
            url = new URL(filepath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        filename = url.getPath();
        filename = filename.substring(filename.lastIndexOf('/')+1);

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url + ""));
        request.setTitle(filename);
        request.setMimeType("application/pdf");
        request.allowScanningByMediaScanner();
        request.setAllowedOverMetered(true);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        dm.enqueue(request);
    }

    private void insertData() {
        dbref=databaseReference.child(catov);
        final String uniquekey=dbref.push().getKey();

        String Name=nameov.getText().toString().toUpperCase();
        String Birth=birthov.getText().toString().trim();
        String Mobile=mobileov.getText().toString().trim();
        String Location=locationov;
        String Emergency=emergency;
        String Time=currentdate;

        othervaccineData data=new othervaccineData(Name,Birth,Mobile,uniquekey,Location,Emergency,Time);

        assert uniquekey != null;
        dbref.child(uniquekey).setValue(data).addOnSuccessListener(unused -> {
            Toast.makeText(othervaccineregistration.this,"Registration complete", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(othervaccineregistration.this,MainActivity.class);
            startActivity(intent);
        }).addOnFailureListener(e -> {
            Toast.makeText(othervaccineregistration.this,"Something went wrong", Toast.LENGTH_SHORT).show();
        });
    }
}