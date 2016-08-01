package com.example.hi_tech1.swipe;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ngodisplay extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngodisplay);
        tv1=(TextView)findViewById(R.id.ngo_s_id);
        tv2=(TextView)findViewById(R.id.ngo_s_name);
        tv3=(TextView)findViewById(R.id.ngo_s_phone);
        tv4=(TextView)findViewById(R.id.ngo_s_email);
        tv5=(TextView)findViewById(R.id.ngo_s_address);
        tv6=(TextView)findViewById(R.id.ngo_s_type);
        btn1=(Button)findViewById(R.id.go_email);
        btn2=(Button)findViewById(R.id.go_call);
        btn3=(Button)findViewById(R.id.go_map);
        Bundle bundle=getIntent().getExtras();
        String t1=bundle.getString("aa");
        String t2=bundle.getString("bb");
        final String t3=bundle.getString("cc");
        final String t4=bundle.getString("dd");
        String t5=bundle.getString("ee");
        String t6=bundle.getString("ff");
        tv1.setText(t1);
        tv2.setText(t2);
        tv3.setText(t3);
        tv4.setText(t4);
        tv5.setText(t5);
        tv6.setText(t6);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Query");
                intent.putExtra(Intent.EXTRA_TEXT, "Hello Sir/Ma'am  This is regarding my query....   Thankyou.   Name:" + "....." + " |Phone number:" + "......");

                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{t4});
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an email client"));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                //Intent intent=new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel",phn,null));

                intent.setData(Uri.parse("tel:" + t3));
                startActivity(intent);

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(ngodisplay.this,MapsActivity.class);
                startActivity(i);
            }
        });
    }
}
