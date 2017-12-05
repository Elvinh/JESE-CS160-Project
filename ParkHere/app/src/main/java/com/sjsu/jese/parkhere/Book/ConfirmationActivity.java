package com.sjsu.jese.parkhere.Book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sjsu.jese.parkhere.R;

/**
 * Created by jerry on 12/4/17.
 */

public class ConfirmationActivity extends AppCompatActivity { //needs to connect to book_confirm.xml

    String postID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postID = getIntent().getStringExtra("POST_ID");
        Button confirmBtn = (Button) findViewById(R.id.submitBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMain();
            }
        });
    }
    public void toMain()
    {
        Intent intent= new Intent(this,BookActivity.class); //needs to connect to ConfirmationActivity
        intent.putExtra("POST_ID",postID);
        startActivity(intent);

    }


}
