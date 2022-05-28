package com.velu.easemusicplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.view.View;

public class ComplaintsActivity extends AppCompatActivity {
        EditText subject, body;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_complaints);
            subject = findViewById(R.id.sub_fld);
            body = findViewById(R.id.body_fld);
        }

        public void onClickSend(View view){
            String receiver = "velmuruganr165@gmail.com";
            String emailsubject = subject.getText().toString();
            String emailbody = body.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL,new String[] { receiver });
            intent.putExtra(Intent.EXTRA_SUBJECT, emailsubject);
            intent.putExtra(Intent.EXTRA_TEXT, emailbody);
            intent.setType("message/rfc822");

            startActivity(Intent.createChooser(intent,"Choose an Email client :"));
        }

    }