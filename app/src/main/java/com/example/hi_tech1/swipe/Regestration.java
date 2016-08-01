package com.example.hi_tech1.swipe;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Regestration extends AppCompatActivity {

    EditText USER_NAME,USER_PASS,CON_PASS,REGIS_EMAIL,contact;
    String user_name,user_pass,con_pass,email,con;
    Button REG;
    Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration);
        USER_NAME = (EditText) findViewById(R.id.reg_user);
        USER_PASS = (EditText) findViewById(R.id.reg_pass) ;
        CON_PASS = (EditText) findViewById(R.id.con_pass);
        REGIS_EMAIL=(EditText)findViewById(R.id.REGIS_EMAIL);
        contact=(EditText)findViewById(R.id.contact_number);
        REG = (Button) findViewById(R.id.user_reg);
        REG.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                user_name = USER_NAME.getText().toString();
                user_pass = USER_PASS.getText().toString();
                con_pass = CON_PASS.getText().toString();
                email=REGIS_EMAIL.getText().toString();
                con=contact.getText().toString();


                if(!(user_pass.equals(con_pass)))
                {
                    Toast.makeText(getBaseContext(),"Passwords are not matching", Toast.LENGTH_LONG).show();
                    USER_NAME.setText("");
                    USER_PASS.setText("");
                    CON_PASS.setText("");
                    REGIS_EMAIL.setText("");
                    contact.setText("");
                }
                else
                {
                    DatabaseOperation DB = new DatabaseOperation(ctx);
                    DB.putInformation(DB, user_name, user_pass,email,con);
                    Toast.makeText(getBaseContext(), "Registration success", Toast.LENGTH_LONG).show();
                    finish();

                }

            }
        });
    }

}

