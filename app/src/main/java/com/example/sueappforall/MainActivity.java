package com.example.sueappforall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sueappforall.fragments.AddGradesFragment;
import com.example.sueappforall.fragments.AddImprovementFragment;
import com.example.sueappforall.fragments.ListStudentsFragment;
import com.example.sueappforall.fragments.SearchGradeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    //Refers to the widgets in the layout
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    //Instantiates all widgets
    public void init(){
        navView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Implement the navigation drawer in the screen using the Open and Close strings defined
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.nav_open, R.string.nav_close);

        //Listens to the event of opening and closing the drawer
        mDrawerLayout.addDrawerListener(mToggle);

        //Shows the icon in the appropriated state of the drawer
        mToggle.syncState();

        //Show the button for the drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Configures the actions inside of the drawer
        setNavigationDrawer();
    }

    private void setNavigationDrawer(){
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment frag = null;
                int itemId = item.getItemId();

                //Decides which fragment open on each menu
                if(itemId == R.id.nav_enter_grades){
                    frag = new AddGradesFragment();
                }else if(itemId == R.id.nav_enter_improvement){
                    frag = new AddImprovementFragment();
                }else if(itemId == R.id.nav_search_grade){
                    frag = new SearchGradeFragment();
                }else if(itemId == R.id.nav_list_student){
                    frag = new ListStudentsFragment();
                }

                if(frag != null){
                    //Loads the fragment chosen
                    FragmentTransaction frgTrans = getSupportFragmentManager().beginTransaction();
                    frgTrans.replace(R.id.frame, frag);
                    frgTrans.commit();

                    //Closes the drawer once the fragment is loaded
                    mDrawerLayout.closeDrawers();

                    return true;
                }

                return false;

            }
        });
    }

    //Handles the click on the drawer button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}