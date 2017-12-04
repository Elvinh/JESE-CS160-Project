package com.sjsu.jese.parkhere.postDetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sjsu.jese.parkhere.Book.BookActivity;
import com.sjsu.jese.parkhere.R;
import com.sjsu.jese.parkhere.Review.ReviewAcivity;
import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Post;

import org.w3c.dom.Text;

public class PostDetailActivity extends AppCompatActivity {
    final String POSTTAG="JESE.ParkHere.post.ID.to.Book";
    String postId="";

    final private String CURRENTPOST="com.sjsu.parkHere.current.Post";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details);
         postId = getIntent().getStringExtra("POST_ID");


        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference mPost = mDatabase.getReference("Posts").child(postId);

        final TextView mTitle = (TextView) findViewById(R.id.title);
        final TextView mAddress = (TextView) findViewById(R.id.address);
        final TextView mDailyRate = (TextView) findViewById(R.id.dailyRate);
        final TextView mCarSize = (TextView) findViewById(R.id.carSize);
        final TextView mStartDate = (TextView) findViewById(R.id.startDateText);
        final TextView mEndDate = (TextView) findViewById(R.id.endDateText);
        final Button mBookBt=(Button) findViewById(R.id.book_button);

        final Button mReviewBt=(Button) findViewById(R.id.create_review_bt);

        final ImageView mPostImage = (ImageView) findViewById(R.id.post_detail_image);


        mPost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTitle.setText((String) dataSnapshot.child("title").getValue());
                mDailyRate.setText("$" + ((Long) dataSnapshot.child("dailyRate").getValue()).toString() + "/hr");
                Address address = dataSnapshot.child("address").getValue(Address.class);
                mAddress.setText(address.getStreetAddress() + ", " + address.getCity() + ", " + address.getZipCode());
                mCarSize.setText( "Fits car of size: " + (String ) dataSnapshot.child("carSize").getValue());
                mStartDate.setText("Available from: " + (String) dataSnapshot.child("dateAvailable").getValue());
                mEndDate.setText("Ends: " + (String) dataSnapshot.child("dateEnd").getValue());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference imageRef = storageRef.child("images/" + postId + "/post_image");
        Glide.with(this /* context */)
                .using(new FirebaseImageLoader())
                .load(imageRef)
                .into(mPostImage);
        
        mBookBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tobooking();
            }
        });


        mReviewBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PostDetailActivity.this, ReviewAcivity.class);
                intent.putExtra(CURRENTPOST,postId);
                startActivity(intent);

            }
        });



    }
    public void tobooking()
    {
        Intent intent= new Intent(this,BookActivity.class);
        intent.putExtra("POST_ID",postId);
        startActivity(intent);

    }
}
