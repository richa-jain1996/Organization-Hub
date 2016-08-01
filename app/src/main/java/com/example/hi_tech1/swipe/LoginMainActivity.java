package com.example.hi_tech1.swipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginMainActivity extends AppCompatActivity {

    Button Login, Register, Delete, Update;
    int status = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        Login = (Button) findViewById(R.id.Login);
        Register = (Button) findViewById(R.id.Reg);
       // Delete = (Button) findViewById(R.id.Delete);
        Update = (Button) findViewById(R.id.Update);
        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                status = 1;
                Bundle b = new Bundle();
                b.putInt("status", status);
                Intent i  = new Intent(LoginMainActivity.this,Login.class);
                i.putExtras(b);
                startActivity(i);

            }
        });

        Register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i  = new Intent(LoginMainActivity.this,Regestration.class);
                startActivity(i);
            }
        });
        Update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                status = 2;
                Bundle b = new Bundle();
                b.putInt("status", status);
                Intent i  = new Intent(LoginMainActivity.this,Update.class);
                i.putExtras(b);
                startActivity(i);
            }
        });

    }
}
