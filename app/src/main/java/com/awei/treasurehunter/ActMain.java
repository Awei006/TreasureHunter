package com.awei.treasurehunter;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import static com.awei.treasurehunter.Resources.FUNC_LOGIN;
import static com.awei.treasurehunter.Resources.FUNC_NEW_ITEM;
import static com.awei.treasurehunter.Resources.ICONS_CLASSIFICATION;
import static com.awei.treasurehunter.Resources.TXT_CLASSIFICATION;

public class ActMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*listTitle.add(data.getExtras().getString("title"));
        listIcons.add(data.getExtras().getByteArray("bytes"));
        Toast.makeText(this,"新增成功1",Toast.LENGTH_LONG).show();

        GridView grid = (GridView)findViewById(R.id.gridItem);
        grid.setAdapter(new AdapterItemGridView(this));*/
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case FUNC_LOGIN:
                    Resources.isLogin = true;
                    break;
                case FUNC_NEW_ITEM:
                    break;
                default:
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.act_main, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        ArrayList<String> listFuncs = new ArrayList<String>();
        ArrayList<Integer> listIcon = new ArrayList<Integer>();
        for (String s : TXT_CLASSIFICATION)
            listFuncs.add(s);
        for (int i : ICONS_CLASSIFICATION)
            listIcon.add(i);

        View mView = getLayoutInflater().inflate(R.layout.dialog_classification, null);
        GridView gridView = (GridView) mView.findViewById(R.id.grid_dialog);
        gridView.setAdapter(new AdapterIcon(ActMain.this, listFuncs, listIcon));


        if (id == R.id.menuType) {
            new AlertDialog.Builder(this).setView(mView).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_home){
            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_member) {
            MemberFragment fragment = new MemberFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_mall) {

        } else if (id == R.id.nav_notice) {
            NoticeFragment fragment = new NoticeFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_billboard) {

        } else if (id == R.id.nav_customer_service) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_help) {

        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_main);
        if(!Resources.isLogin){
            Intent intentLogin = new Intent(this,ActLogin.class);
            startActivityForResult(intentLogin,FUNC_LOGIN);
        }
        InitialComponent();
    }

    private void InitialComponent() {
        MainFragment fragment = new MainFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(ActMain.this, ActNewItem.class);
                startActivityForResult(inte, FUNC_NEW_ITEM);
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    FloatingActionButton fab = null;
    DrawerLayout drawer = null;
    ActionBarDrawerToggle toggle = null;
}
