package com.nexus.nurseryteacher.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.nexus.nurseryteacher.fragment.ClassesFragment;
import com.nexus.nurseryteacher.fragment.EventsFragment;
import com.nexus.nurseryteacher.fragment.PostsFragment;
import com.nexus.nurseryteacher.fragment.ScheduleFragment;
import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.model.Post;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Post> postsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("");

        getPosts();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationView logoutNavigationView = (NavigationView) findViewById(R.id.navigation_drawer_bottom);
        logoutNavigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.homelayout);
            navigationView.getMenu().performIdentifierAction(R.id.homelayout, 0);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            //MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, PostsFragment.newInstance(postsArrayList)).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.homelayout) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, PostsFragment.newInstance(postsArrayList)).commit();
        } //else if (id == R.id.settinglayout) {
            //Intent intent = new Intent(this, SettingsActivity.class);
            //startActivity(intent);
        //}
        else if (id == R.id.logoutlayout) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            this.finish();

        } else if (id == R.id.nav_classes) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, ClassesFragment.newInstance()).commit();

        } else if (id == R.id.nav_add_post) {
            Intent intent = new Intent(this, AddPostActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_schedule) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, ScheduleFragment.newInstance()).commit();

        } else if (id == R.id.nav_events) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, EventsFragment.newInstance()).commit();

            /*Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);*/
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getPosts(){
        postsArrayList =new ArrayList<>();
        Post post = new Post(R.drawable.tech, R.drawable.happy, "Mother's Day Celebration","", "Mrs Reem Salah", "Motivation is the driving force behind life-enhancing change. It comes from knowing exactly what you want to do and having an insatiable, burning desire to do what’s necessary to get it. " +
                "It keeps your dream on track as it is the power of motivation that keeps you going when the going gets tough.");
        postsArrayList.add(post);

        post = new Post(R.drawable.tech, R.drawable.sample_class, "Control Your Own Level Of Motivation", "", "Mrs Reem Salah", "cur fiscina assimilant.Nixs experimentum in moscua!Manducare satis ducunt ad castus detrius.A falsis, resistentia secundus frondator.");
        postsArrayList.add(post);
        //items.add(new Post(R.drawable.tech, R.drawable.happy, "Mother's Day Celebration",new Date(), "Mrs Reem Salah", "Motivation is the driving force behind life-enhancing change. It comes from knowing exactly what you want to do and having an insatiable, burning desire to do what’s necessary to get it. " +
        //        "It keeps your dream on track as it is the power of motivation that keeps you going when the going gets tough."));
        //items.add(new Post(getActivity(),R.drawable.tech, R.drawable.sample_class, "Control Your Own Level Of Motivation", new Date(), "Mrs Reem Salah", "cur fiscina assimilant.Nixs experimentum in moscua!Manducare satis ducunt ad castus detrius.A falsis, resistentia secundus frondator."));

    }
}







