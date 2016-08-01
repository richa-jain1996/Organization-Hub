package com.example.hi_tech1.swipe;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends AppCompatActivity {
Cursor CR=null;

    Bundle bn;
    String USERNAME;
    Button Del;
    EditText PASS;
    String Pass;
    DatabaseOperation DOP;
    //com.example.hi_tech1.slideview.DatabaseOperations DOP;
    Context CTX = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Del = (Button) findViewById(R.id.b_delete);
        PASS = (EditText) findViewById(R.id.del_pass);
        bn = getIntent().getExtras();
        USERNAME = bn.getString("user_name");
        Del.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Pass = PASS.getText().toString();
                DOP = new DatabaseOperation(CTX);
                CR = DOP.getUserPass(DOP, USERNAME);
                CR.moveToFirst();
                boolean login_status = false;
                do
                {
                    if(Pass.equals(CR.getString(0)))
                    {
                        login_status = true;
                    }

                }while(CR.moveToNext());

                if(login_status)
                {
                    DOP.deleteUser(DOP,USERNAME , Pass);
                    Toast.makeText(getBaseContext(), "User Removed successfully.....", Toast.LENGTH_LONG).show();
                    finish();

                }
                else
                {
                    Toast.makeText(getBaseContext(), "Invalid user.....Try later", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

}
