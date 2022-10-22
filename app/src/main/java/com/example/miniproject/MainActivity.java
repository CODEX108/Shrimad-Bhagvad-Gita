package com.example.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bttmNav;
    private NavController navController;

    private DrawerLayout drawer_layout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation_drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bttmNav = findViewById(R.id.bttmNav);
        navController = Navigation.findNavController(this,R.id.frame_layout);
        NavigationUI.setupWithNavController(bttmNav,navController);
        drawer_layout =  findViewById(R.id.drawer_layout);
        navigation_drawer = findViewById(R.id.navigation_drawer);

        toggle= new ActionBarDrawerToggle(this,drawer_layout,R.string.open,R.string.close);

        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();
      // getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new video_lec()).commit();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigation_drawer.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //this will open and close the drawer
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }

    Fragment temp;
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_video:
                temp = new video_lec();
                Toast.makeText(this, "Video lectures are clicked", Toast.LENGTH_SHORT).show();
                drawer_layout.closeDrawer(GravityCompat.START);
                break;



            case R.id.navigation_gist:
                temp= new gitaSaar();
                Toast.makeText(this, "Gita Saar is clicked", Toast.LENGTH_SHORT).show();
                drawer_layout.closeDrawer(GravityCompat.START);
                break;

            case R.id.navigation_website:
                temp=new buyGita();
                Toast.makeText(this, "Visit website", Toast.LENGTH_SHORT).show();
                drawer_layout.closeDrawer(GravityCompat.START);
                break;


        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,temp).commit();
        return true;
    }
}