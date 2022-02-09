package com.mahirshadid.vax;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class afterlogin extends AppCompatActivity {

    private EditText mobileblock,date;
    private Button tokenbutton;
    private String mobile;
    private String filepath="https://drive.google.com/uc?export=download&id=1gbKlD8K_oCYaFDp8HS5_dqPNLwewLa6Y";
    private URL url = null;
    private String filename;

    private RecyclerView token;
    private LinearLayout tokennodata;

    private List<Dataadmin> list1;

    private DatabaseReference reference, dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);


        mobileblock = findViewById(R.id.mobileblock);
        date = findViewById(R.id.date);

        tokenbutton = findViewById(R.id.tokenbutton);

        showdata();

        try {
            url = new URL(filepath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        filename = url.getPath();
        filename = filename.substring(filename.lastIndexOf('/')+1);

        tokenbutton.setOnClickListener(v -> {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url + ""));
            request.setTitle(filename);
            request.setMimeType("application/pdf");
            request.allowScanningByMediaScanner();
            request.setAllowedOverMetered(true);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
            DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            dm.enqueue(request);
        });


    }


    private void showdata() {
        Intent intent = getIntent();
        String mobile = intent.getStringExtra("referencemobile");
        String Date = intent.getStringExtra("date");

        mobileblock.setText(mobile);
        date.setText(Date);
    }

}
