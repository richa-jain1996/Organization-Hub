package com.example.hi_tech1.swipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UserFirstLogin extends AppCompatActivity {

    private RadioButton radioButton,radioButton2,radioButton3;
    private Button btnDisplay;
    View selectedRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_first_page);
        // radioSexGroup=(RadioGroup)findViewById(R.id.radioGroup);
        selectedRadio = findViewById(R.id.radio);
        radioButton=(RadioButton)findViewById(R.id.radioButton);
        radioButton2=(RadioButton)findViewById(R.id.radioButton2);
        radioButton3=(RadioButton)findViewById(R.id.radioButton3);
        btnDisplay=(Button)findViewById(R.id.button);
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // int selectedId=radioSexGroup.getCheckedRadioButtonId();
               if(radioButton3.isChecked()) {


                   Toast.makeText(UserFirstLogin.this, radioButton3.getText(), Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(UserFirstLogin.this, List.class);
                   startActivity(intent);
               }else if(radioButton2.isChecked()) {
                   Toast.makeText(UserFirstLogin.this, radioButton2.getText(), Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(UserFirstLogin.this, List.class);
                   startActivity(intent);
               }else {
                   Intent intent = new Intent(UserFirstLogin.this, List.class);
                   startActivity(intent);
                   Toast.makeText(UserFirstLogin.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
               }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
