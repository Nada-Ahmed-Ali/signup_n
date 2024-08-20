package com.example.signup_n;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signupscreen extends AppCompatActivity {
    EditText username, email, pass, confipass;
    Button signup;
    TextView allog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signupscreen);
        username = findViewById(R.id.userSign);
        email = findViewById(R.id.emailSign);
        pass = findViewById(R.id.passSign);
        confipass = findViewById(R.id.conpassSign);
        signup = findViewById(R.id.signButton);
        allog = findViewById(R.id.loginDirect);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        editor = sharedPreferences.edit();


    }

    public void logdirekt(View view) {
        Intent intent = new Intent(signupscreen.this, Loginscreen.class);
        startActivity(intent);
    }


    public void onClickButton(View view) {
        String valueemail = email.getText().toString();
        String valueuser = username.getText().toString();
        String valuepass = pass.getText().toString();
        String valueconfipass = confipass.getText().toString();
        if (!validEmail(valueemail)) {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
        } else if (!userName(valueuser)) {
            Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show();
        } else if (!password(valuepass)) {
            Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
        } else if (!confirmpassword(valuepass, valueconfipass)) {
            Toast.makeText(this, "Password don't match", Toast.LENGTH_SHORT).show();
        } else {
            String user =username.getText().toString();
            String passs =pass.getText().toString();

            editor.putString("username", user);
            editor.putString("pass", passs);
            editor.apply();
            editor.commit();
            Intent intent = new Intent(signupscreen.this, Loginscreen.class);
            startActivity(intent);
        }
    }

    public boolean validEmail(String email) {
        if (!email.isEmpty() && email.length() >= 10 && email.contains("@") && email.contains(".com")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean password(String pass) {
        if (pass.length() < 8 || pass.length() > 20) {
            return false;
        }
        boolean conupper = false;
        boolean conlower = false;
        boolean consign = false;
        boolean conwhitespace = false;
        boolean condigit = false;

        for (int i = 0; i < pass.length(); i++) {
            char ch = pass.charAt(i);

            if (Character.isLowerCase(ch)) {
                conlower = true;
            } else if (Character.isUpperCase(ch)) {
                conupper = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                consign = true;
            } else if (Character.isDigit(ch)) {
                condigit = true;
            } else if (Character.isWhitespace(ch)) {
                conwhitespace = true;
            }

        }
        return conlower && conupper && !conwhitespace && condigit && consign;
    }

    public boolean userName(String userName) {
        if (userName.length() >= 5 && userName.length() <= 20) {
            return true;
        } else {
            return false;
        }
    }

    public boolean confirmpassword(String pass, String confipass) {
        if (pass.equals(confipass)) {
            return true;
        } else {
            return false;
        }
    }

}