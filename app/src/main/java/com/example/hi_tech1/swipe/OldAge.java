package com.example.hi_tech1.swipe;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OldAge extends AppCompatActivity {

    ActionBar ab;

    EditText editText,editText2,editText4,editText5,editText6,editText8,editText7,editText9;
    Button button2,button3,button4,button5,button6,button,reset;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_work);
        myDb=new DatabaseHelper(this);
        ab=getSupportActionBar();
        reset = (Button) findViewById(R.id.button);
        editText=(EditText)findViewById(R.id.your_name);
        editText2=(EditText)findViewById(R.id.your_phone);

        editText8=(EditText)findViewById(R.id.editText8);
        editText4=(EditText)findViewById(R.id.editText4);
        editText5=(EditText)findViewById(R.id.editText5);

        //editText9=(EditText)findViewById(R.id.editText9);
        editText6=(EditText)findViewById(R.id.editText6);
        editText7=(EditText)findViewById(R.id.editText7);
        button2=(Button)findViewById(R.id.call);
        button3=(Button)findViewById(R.id.maps);
        button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        button6=(Button)findViewById(R.id.button6);
        button=(Button)findViewById(R.id.email);

        AddData();
        ShowData();
        UpdateData();
        Delete();
        reset.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         editText.setText("");
                                         editText2.setText("");
                                         editText4.setText("");
                                         editText5.setText("");
                                         editText6.setText("");
                                         editText7.setText("");
                                         editText8.setText("");
                                     }
                                 }

        );
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OldAge.this, List.class);
                startActivity(i);

                overridePendingTransition(R.anim.swipe_in_right, R.anim.slide_out_left);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OldAge.this,Regestration.class);
                startActivity(intent);
                overridePendingTransition(R.anim.swipe_in_right, R.anim.slide_out_left);
            }
        });

    }
    public void AddData()
    {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //*********validation*************
                if (!validateContact(editText4.getText().toString())) {
                    editText4.setError("Invalid !");
                    editText4.requestFocus();

                    if (!validateEmail(editText5.getText().toString())) {
                        editText5.setError("Invalid !");
                        editText5.requestFocus();
                    }
                    if (editText2.getText().toString().equals("") || editText8.getText().toString().equals("") || editText4.getText().toString().equals("") || editText5.getText().toString().equals("") || editText6.getText().toString().equals("")) {
                        Toast.makeText(OldAge.this, "Please enter the details", Toast.LENGTH_SHORT).show();
                    }
                }

                else
                {
                    boolean isInserted = myDb.insertData(editText2.getText().toString(), editText4.getText().toString(),editText5.getText().toString(),editText6.getText().toString(),editText8.getText().toString(),editText7.getText().toString());
                    if (isInserted == true)
                        Toast.makeText(OldAge.this, "DATA ADDED!", Toast.LENGTH_LONG).show();
                    else
                    { if(isInserted == false)
                        Toast.makeText(OldAge.this, "DATA CANNOT BE ADDED!", Toast.LENGTH_LONG).show();
                    }}}
        });}


    private boolean validateEmail(String s) {
        String emailExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        Pattern pattern = Pattern.compile(emailExpression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        return (matcher.matches());

    }

    private boolean validateContact(String s2) {
        if (s2 != null && s2.length() == 10)
            return true;
        else
            return false;
    }


    public void Delete()
    {
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res=myDb.deleteRow(editText.getText().toString());
                if(res==true)
                    Toast.makeText(OldAge.this, "DATA DELETED!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(OldAge.this,"DATA CANNOT BE DELETED!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ShowData()
    {
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=myDb.viewAll();
                if(cursor.getCount()==0)
                {    Intent intent=new Intent(OldAge.this,Display.class);
                    startActivity(intent);
                    showMsg("ERROR","No data to show");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(cursor.moveToNext())
                {
                    buffer.append("Code              : "+cursor.getString(0)+"\n");
                    buffer.append("Old-Age Name   : "+cursor.getString(1)+"\n");

                    buffer.append("Phone number      : "+cursor.getString(2)+"\n");
                    buffer.append("Email             : "+cursor.getString(3)+"\n");
                    buffer.append("Address           : "+cursor.getString(4)+"\n");
                    buffer.append("Lattitude         : "+cursor.getString(5)+"\n");
                    buffer.append("Longitude         : "+cursor.getString(6)+"\n");
                }
                //  Intent intent=new Intent(Orphanage.this,Display.class);
                //startActivity(intent);

                showMsg("NGO Details",buffer.toString());

            }
        });


    }
    public void showMsg(String error,String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(error);
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.show();

    }
    public void UpdateData()
    {
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res = myDb.updateAll(editText.getText().toString(), editText2.getText().toString(), editText4.getText().toString(), editText5.getText().toString(), editText6.getText().toString(), editText8.getText().toString(), editText7.getText().toString(),editText9.getText().toString());
                if (res == true)
                    Toast.makeText(OldAge.this, "DATA UPDATED!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(OldAge.this, "DATA CANNOT BE UPDATED!", Toast.LENGTH_LONG).show();
            }
        });
    }
    public  boolean onCreateOptionsMenu(Menu m)
    {
        super.onCreateOptionsMenu(m);
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.menu, m);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        int id=item.getItemId();
        switch(id)
        {
            case R.id.item1:
                Intent i=new Intent(OldAge.this,HomePage.class);
                startActivity(i);
                Toast.makeText(OldAge.this,"WELCOME",Toast.LENGTH_LONG).show();
                break;

            case R.id.item2:
                Intent i2=new Intent(OldAge.this,About.class);
                startActivity(i2);
                Toast.makeText(OldAge.this,"About",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item3:
                Intent i3=new Intent(OldAge.this,Feedback.class);
                startActivity(i3);
                Toast.makeText(OldAge.this,"Feedback",Toast.LENGTH_LONG).show();
                break;


        }
        return true;
    }


}
