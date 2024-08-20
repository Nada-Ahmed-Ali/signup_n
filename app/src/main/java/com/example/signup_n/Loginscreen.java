package com.example.signup_n;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Loginscreen extends AppCompatActivity {
    EditText usernamelog, passlog;
    TextView directsign;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loginscreen);
        usernamelog = findViewById(R.id.userlog);
        passlog = findViewById(R.id.passlog);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

    }

    public void signDirect(View view) {
        Intent intent = new Intent(Loginscreen.this, signupscreen.class);
        startActivity(intent);
    }

    public void onClickLogIn(View view) {
        String username=usernamelog.getText().toString();
        String pasword=passlog.getText().toString();
        if (sameUserAndPass(username, pasword)&&validateInput(username,pasword)) {
            Intent intent = new Intent(Loginscreen.this, homepage.class);
            startActivity(intent);
        }
        else{
            ErrorDailog();
        }
    }

    public boolean sameUserAndPass(String username, String passl) {
        Log.d("sadsasdada",sharedPreferences.getString("username", ""));
        Log.d("sadsasdada",sharedPreferences.getString("pass", ""));
        if (username.equalsIgnoreCase(sharedPreferences.getString("username", ""))
                && passl.equalsIgnoreCase(sharedPreferences.getString("pass", ""))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateInput(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void ErrorDailog() {

        final Dialog errorDialog = new Dialog(this, R.style.erreorStyle);

        errorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        errorDialog.setContentView(R.layout.error_dialog_layout);
        errorDialog.setCancelable(true);
        TextView title = (TextView) errorDialog.findViewById(R.id.errortitle);
        TextView msg = (TextView) errorDialog.findViewById(R.id.errorMessage);
        title.setText("Error");
        msg.setText("Please verify your username and password.");

        errorDialog.show();
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (errorDialog.isShowing()) {
                    errorDialog.dismiss();
                }
            }
        };

        errorDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 3000);
    }



}