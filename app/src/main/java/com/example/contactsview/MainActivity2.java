package com.example.contactsview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ImageView img1;
    TextView pname, pnumber;
    EditText smsedittext;
    ImageButton sharebutton;
    Button backbutton, sendsmsbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        img1 = findViewById(R.id.personimage);
        pname = findViewById(R.id.personname);
        pnumber = findViewById(R.id.personnumber);
        backbutton = findViewById(R.id.backbutton);
        sharebutton = findViewById(R.id.sharebutton);
        pname.setText(getIntent().getStringExtra("name"));
        pnumber.setText(getIntent().getStringExtra("number"));
        sendsmsbutton = findViewById(R.id.sendsmsbutton);
        smsedittext = findViewById(R.id.sendmessageedittext);

        sendsmsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneno = getIntent().getStringExtra("number");
                String message = smsedittext.getText().toString();
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneno, null, message, null, null);
                    smsedittext.setText("");
                    Toast.makeText(MainActivity2.this, "Message sent", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity2.this, "Message field empty !", Toast.LENGTH_SHORT).show();
                }
            }
        });








        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_SEND);
                it.setType("text/plain");
                it.putExtra(Intent.EXTRA_SUBJECT, getIntent().getStringExtra("name"));
                it.putExtra(Intent.EXTRA_TEXT, getIntent().getStringExtra("number"));
                startActivity(Intent.createChooser(it, "Share via"));
            }
        });



        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();


            }
        });









    }

}




