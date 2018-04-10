package com.geeknot.example.creatingfolder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST = 1;

    Button folder, sub_folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        folder = findViewById(R.id.btn_folder);
        folder.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                File folder = new File(Environment.getExternalStorageDirectory() + "/My New Folder");
                boolean success = true;
                if (!folder.exists()) {
                    success = folder.mkdir();
                }
                if (success) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Done!")
                            .setMessage("Please check your internal storage to make sure 'My New Folder' folder is created or not!:\n Go to 'Internal Storage > My New Folder'")
                            .setCancelable(false)
                            .setPositiveButton("ok", null).show();

                } else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Something went wrong!")
                            .setMessage("Please make sure you have granted the storage permission.")
                            .setCancelable(false)
                            .setPositiveButton("ok", null).show();

                }



            }
        });


        sub_folder = findViewById(R.id.btn_sub_folder);
        sub_folder.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                File folder = new File(Environment.getExternalStorageDirectory() + "/My New Folder/My Sub Folder");
                boolean success = true;
                if (!folder.exists()) {
                    success = folder.mkdir();
                }

                if (success) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Done!")
                            .setMessage("Please check your internal storage to make sure 'My Sub Folder' is created or not in 'My New Folder' folder!:\n Go to 'Internal Storage > My New Folder > My Sub Folder'")
                            .setCancelable(false)
                            .setPositiveButton("ok", null).show();

                } else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Something went wrong!")
                            .setMessage("Please make sure you have granted the storage permission.")
                            .setCancelable(false)
                            .setPositiveButton("ok", null).show();

                }

                

            }
        });

        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "Permission granted.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}
