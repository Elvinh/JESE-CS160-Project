package com.sjsu.jese.parkhere;

import android.content.Intent;
import android.icu.util.Freezable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sjsu.jese.parkhere.browsePost.BrowsePostActivity;
import com.sjsu.jese.parkhere.browsePost.BrowsePostFragment;
import com.sjsu.jese.parkhere.login.LoginActivity;
import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Customer;
import com.sjsu.jese.parkhere.newPost.NewPostActivity;
import com.sjsu.jese.parkhere.profile.ProfileViewFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    FirebaseAuth mAuth;
    private BottomNavigationView mBottomNav;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView loggedInAsTextView = (TextView) findViewById(R.id.currUserText);

        mAuth = FirebaseAuth.getInstance();
        setupBottomNavigationView();
        setupViewPager();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void selectFragment(MenuItem item) {
        // init corresponding fragment
        ProfileViewFragment frag = new ProfileViewFragment();
        switch (item.getItemId()) {
            case R.id.browse:
                viewPager.setCurrentItem(0);
                //toBrowsePost();
                break;
            case R.id.newPost:
                toNewPost();
                break;
            case R.id.profile:
                //frag = MenuFragment.newInstance(getString(R.string.text_search),
                //getColorFromRes(R.color.color_search));
                //FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //ft.add(R.id.container, frag, frag.getTag());
                //ft.commit();
                viewPager.setCurrentItem(1);
                break;
        }
    }

    // When button id = browsePostBtn clicked takes you to BrowsePostActivity
    public void toBrowsePost() {
        Intent intent = new Intent(this, BrowsePostActivity.class);
        startActivity(intent);
    }

    public void toNewPost() {
        Intent intent = new Intent(this, NewPostActivity.class);
        startActivity(intent);
    }

    private void setupBottomNavigationView() {
        mBottomNav = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });
    }

    private void setupViewPager() {
        NavigationPagerAdapter adapter = new NavigationPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new BrowsePostFragment(), "Browse");
        adapter.addFragment(new ProfileViewFragment(), "Profile");
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

    }
}
