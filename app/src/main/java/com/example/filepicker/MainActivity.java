package com.example.filepicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    // Initialize variable
    Button btFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign variable
        btFile = findViewById(R.id.bt_file);

        btFile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Initialize intent
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                // Setting MIME type
                intent.setType("*/*");
                // Start activity result
                startActivityForResult(intent, 10);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Check condition
        if ((grantResults.length > 0) && (grantResults[0] != PackageManager.PERMISSION_GRANTED)) {
            // When permission is denied, display toast
            Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    String path = data.getData().getPath();
                    String s = "File Path: " + path;
                    displayToast(s);
                }
                break;
        }
    }

    private void displayToast(String s) {
        // Initialize and show toast
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}