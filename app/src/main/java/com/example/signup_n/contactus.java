package com.example.signup_n;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class contactus extends AppCompatActivity {
    TextView conta;

int call_code=123;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 123:{
                if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"allow",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this,"not allow",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contactus);
        conta=findViewById(R.id.mycon);
        ActivityCompat .requestPermissions(contactus.this,new String[]{Manifest.permission.CALL_PHONE},call_code);

        conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcall=new Intent(Intent.ACTION_CALL);
                intentcall.setData(Uri.parse("tel:"+"01095423573"));
                startActivity(intentcall);
            }
        });

    }
}