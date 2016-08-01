package com.example.hi_tech1.swipe;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button Login;
    EditText USERNAME,USERPASS;
    String username,userpass;
    Context CTX = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login = (Button) findViewById(R.id.b_login);
        USERNAME = (EditText) findViewById(R.id.user_name);
        USERPASS = (EditText) findViewById(R.id.user_pass);
        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if(USERNAME.getText().toString().equals("admin") && USERPASS.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this,AdminFistEntry.class);
                    startActivity(intent);

                }
                Bundle b = getIntent().getExtras();
                int status = b.getInt("status");
                if(status == 1)
                {
                    Toast.makeText(getBaseContext(), "Please wait...", Toast.LENGTH_LONG).show();
                    username = USERNAME.getText().toString();
                    userpass = USERPASS.getText().toString();
                    DatabaseOperation DOP = new DatabaseOperation(CTX);
                    Cursor CR = DOP.getInformation(DOP);
                    CR.moveToFirst();
                    boolean loginstatus = false;
                    String NAME = "";
                    do
                    {

                        if(username.equals(CR.getString(0))&& (userpass.equals(CR.getString(1))))
                        {
                            loginstatus = true;
                          USERNAME.setText(username);
                            USERPASS.setText(userpass);
//USERNAME=CR.getString((CR.getColumnIndex("USERNAME")));
                            NAME = CR.getString(0);
                           // num = cursor.getString(cursor.getColumnIndex("ContactNumber"));
                           // CR.close();
                        }

                    }while(CR.moveToNext()&& CR!=null);
                    if(loginstatus)
                    {
                        Toast.makeText(getBaseContext(), "Login Success----\n Welcome "+NAME, Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Login.this,UserFirstLogin.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(), "Login Failed---- ", Toast.LENGTH_LONG).show();
                        finish();
                    }



                }
                else if(status == 2)
                {
                    Toast.makeText(getBaseContext(), "Please wait...", Toast.LENGTH_LONG).show();
                    username = USERNAME.getText().toString();
                    userpass = USERPASS.getText().toString();
                    DatabaseOperation DOP = new DatabaseOperation(CTX);
                    Cursor CR = DOP.getInformation(DOP);
                    CR.moveToFirst();
                    boolean loginstatus = false;
                    String NAME = "";
                    do
                    {
                        if(username.equals(CR.getString(0))&& (userpass.equals(CR.getString(1))))
                        {
                            loginstatus = true;
                            NAME = CR.getString(0);
                        }

                    }while(CR.moveToNext());
                    if(loginstatus)
                    {
                        Toast.makeText(getBaseContext(), "Login Success----\n Welcome "+NAME, Toast.LENGTH_LONG).show();

                        Intent i = new Intent(Login.this,Update.class);
                        Bundle BN = new Bundle();
                        BN.putString("user_name",NAME );
                        BN.putString("user_pass",userpass );
                        i.putExtras(BN);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(), "Login Failed---- ", Toast.LENGTH_LONG).show();
                        finish();
                    }






                }
                else if(status == 3)
                {
                    Toast.makeText(getBaseContext(), "Please wait...", Toast.LENGTH_LONG).show();
                    username = USERNAME.getText().toString();
                    userpass = USERPASS.getText().toString();
                    DatabaseOperation DOP = new DatabaseOperation(CTX);
                    Cursor CR = DOP.getInformation(DOP);
                    CR.moveToFirst();
                    boolean loginstatus = false;
                    String NAME = "";
                    do
                    {
                        if(username.equals(CR.getString(0))&& (userpass.equals(CR.getString(1))))
                        {
                            loginstatus = true;
                            NAME = CR.getString(0);
                        }

                    }while(CR.moveToNext());
                    if(loginstatus)
                    {
                        Toast.makeText(getBaseContext(), "Login Success----\n Welcome "+NAME, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Login.this,Delete.class);
                        Bundle B = new Bundle();
                        B.putString("user_name",NAME );
                        i.putExtras(B);
                        startActivity(i);

                        finish();
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(), "Login Failed---- ", Toast.LENGTH_LONG).show();
                        finish();
                    }



                    //Intent i = new Intent(com.example.hi_tech1.slideview.Login.this,Delete.class);
                    //startActivity(i);
                }

            }
        });
    }

}
