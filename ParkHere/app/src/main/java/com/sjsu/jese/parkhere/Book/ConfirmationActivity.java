package com.sjsu.jese.parkhere.Book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sjsu.jese.parkhere.MainActivity;
import com.sjsu.jese.parkhere.R;

/**
 * Created by jerry on 12/4/17.
 */

public class ConfirmationActivity extends AppCompatActivity { //needs to connect to book_confirm.xml

    String postID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_confirm);

        postID = getIntent().getStringExtra("POST_ID");
        Button confirmBtn = (Button) findViewById(R.id.SubmitBtn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMain();
            }
        });
    }
    public void toMain()
    {
        Intent intent= new Intent(this,MainActivity.class); //needs to connect to ConfirmationActivity
        startActivity(intent);

    }


}
