package com.example.frame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class album extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        /* the following code is for the navigation bar */
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selectedFragment = null;
                    switch(item.getItemId()){
                        case R.id.home:
//                            openHome();
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.albums:
//                            openAlbum();
                            selectedFragment = new AlbumsFragment();
                            break;

                        case R.id.groups:
//                            openGroups();
                            selectedFragment = new GroupsFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,selectedFragment).commit();

                    return true;
                }
            };
    /* end of code for navigation bar */

//    public void openAlbum() {
//        Intent intent = new Intent(this, album.class);
//        startActivity(intent);
//    }
//    public void openHome() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
//    public void openGroups() {
//        Intent intent = new Intent(this, groupActivity.class);
//        startActivity(intent);
//    }

}