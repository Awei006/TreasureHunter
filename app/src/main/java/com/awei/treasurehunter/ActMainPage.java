package com.awei.treasurehunter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.awei.treasurehunter.Resources.ICONS_CLASSIFICATION;
import static com.awei.treasurehunter.Resources.TXT_CLASSIFICATION;

public class ActMainPage extends AppCompatActivity {

    private AlertDialog dialogClass;
    private final int LOGIN_REQUEST = 10;
    private final int NEW_ITEM_REQUEST = 11;

    public boolean onKeyDown(int keyCode,KeyEvent event){

        if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
            dialogClose();
        }
        return false;
    }
    private void dialogClose(){

        AlertDialog.Builder builder = new AlertDialog.Builder(ActMainPage.this);

        builder.setMessage("確定要離開？");

        builder.setTitle("離開");

        builder.setPositiveButton("確認", new DialogInterface.OnClickListener()  {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ActMainPage.this.finish();
            }

        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()  {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void loadClassification () {
        ArrayList<String> listFuncs = new ArrayList<String>();
        ArrayList<Integer> listIcon = new ArrayList<Integer>();
        for (String s : TXT_CLASSIFICATION)
            listFuncs.add(s);
        for (int i : ICONS_CLASSIFICATION)
            listIcon.add(i);

        View mView = getLayoutInflater().inflate(R.layout.dialog_classification, null);
        GridView gridView = (GridView) mView.findViewById(R.id.grid_dialog);
        gridView.setAdapter(new AdapterIcon(ActMainPage.this, listFuncs, listIcon));
        gridView.setOnItemClickListener(classification_click);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(mView);
        dialogClass = builder.show();
    }

    AdapterView.OnItemClickListener classification_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(ActMainPage.this,TXT_CLASSIFICATION[position],Toast.LENGTH_LONG);
            dialogClass.dismiss();
        }
    };

    @Override
    protected void onResume() {
        if(Resources.doRefreshScreen){
            Resources.doRefreshScreen = false;
            finish();
            startActivity(getIntent());
        }
        super.onResume();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case LOGIN_REQUEST:
                    break;
                case NEW_ITEM_REQUEST:
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

        menuSearch = menu.findItem(R.id.menuSearch);
        menuClass = menu.findItem(R.id.menuType);

        SearchView searchView = (SearchView)menuSearch.getActionView();
        Button classView = (Button)menuClass.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(ActMainPage.this,query,Toast.LENGTH_LONG).show();
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

        if (id == R.id.menuType) {
            loadClassification ();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        InitialComponent();
    }

    private void InitialComponent() {

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new ActMainPage.PagerAdapter(getSupportFragmentManager()));

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()){
                    case 0:
                        menuSearch.setVisible(true);
                        menuClass.setVisible(true);
                        fab.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        menuSearch.setVisible(false);
                        menuClass.setVisible(false);
                        fab.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        menuSearch.setVisible(false);
                        menuClass.setVisible(false);
                        fab.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        menuSearch.setVisible(false);
                        menuClass.setVisible(false);
                        fab.setVisibility(View.INVISIBLE);
                        break;
                    default:
                        return;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Resources.isLogin) {
                    Intent intentLogin = new Intent(getApplicationContext(), ActLogin.class);
                    startActivityForResult(intentLogin,LOGIN_REQUEST);
                }
                if(Resources.isLogin){
                    Intent inte = new Intent(ActMainPage.this, ActNewItem.class);
                    startActivityForResult(inte, NEW_ITEM_REQUEST);
                }
            }
        });
    }
    ViewPager pager;
    TabLayout tabLayout;
    FloatingActionButton fab = null;
    MenuItem menuSearch,menuClass;

    private class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MainFragment();
                case 1:
                    return new MallFragment();
                case 2:
                    return new NoticeFragment();
                case 3:
                    if(Resources.isLogin)
                        return new MemberFragment();
                    else
                        return new MemberNoLoginFragment();
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            return Resources.FRAG.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return Resources.FRAG[position];
        }
    }
}
