package com.example.hi_tech1.swipe;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    String user_name,user_pass,New_user_name;
    Button b_update;
    EditText newuser;
    Context CTX = this;
    DatabaseOperation DOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Bundle BN = getIntent().getExtras();
        user_name = BN.getString("user_name");
        user_pass = BN.getString("user_pass");
        b_update = (Button) findViewById(R.id.b_update);
        newuser = (EditText) findViewById(R.id.new_user_name);
        b_update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                New_user_name = newuser.getText().toString();
                DOP = new DatabaseOperation(CTX);
                DOP.updateUserInfo(DOP, user_name, user_pass, New_user_name);
                Toast.makeText(getBaseContext(), "Updation Success.....", Toast.LENGTH_LONG).show();
                finish();
            }
        });





    }

}
