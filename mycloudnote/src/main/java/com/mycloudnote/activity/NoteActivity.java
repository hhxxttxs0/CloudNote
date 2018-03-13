package com.mycloudnote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.FrameLayout;

import com.example.mycloudnote.R;
import com.mycloudnote.fragment.NoteListFragment;
import com.mycloudnote.net.context.AppContext;
import com.mycloudnote.fragment.NoteListFragment.OnListFragmentInteractionListener;
import com.mycloudnote.net.pojo.response.Note;

import static com.mycloudnote.net.context.MsgConstant.*;

public class NoteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnListFragmentInteractionListener {


    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private RecyclerView mRecyclerView;
    private FrameLayout mFlContent;
    private Fragment noteListFragment;
    private TextView tv_user_name;
    private View mHeaderView;
    private long curTimeMills;
    private Handler noteActivityHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case DELETE_SUCCESS:
                    Toast.makeText(NoteActivity.this, "删除笔记成功", Toast.LENGTH_SHORT).show();
                    break;

                case DELETE_FAILURE:
                    Toast.makeText(NoteActivity.this, "删除笔记失败！", Toast.LENGTH_SHORT).show();
                    break;

            }
        };
    };

    private void initView(){

        mFlContent = (FrameLayout) findViewById(R.id.id_fl_content_note);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        mHeaderView = navigationView.getHeaderView(0);
        tv_user_name = (TextView) mHeaderView.findViewById(R.id.textView_username);

        noteListFragment = NoteListFragment.newInstance(1);
        getFragmentManager().beginTransaction().replace(R.id.id_fl_content_note, noteListFragment).commit();
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyler_view_list);
//        mRecyclerView.addItemDecoration(new SimplePaddingDecoration());
    }
    private void initEvent(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(NoteActivity.this, NoteEditActivity.class);
                startActivity(intent);

            }
        });


        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        tv_user_name.setText(AppContext.user.getUserName());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        initView();
        initEvent();

        AppContext.noteActivityHandler = noteActivityHandler;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            exitApp();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent com.mycloudnote.activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_add) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_change_password) {
            Intent intent = new Intent(NoteActivity.this,ChangePswActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_switch_account) {
            Intent intent = new Intent(NoteActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onListFragmentInteraction(Note item){

        Log.i("select item noteId",String.valueOf(item.getNoteId()));
        Log.i("select item noteTitle",item.getTitle());
        Log.i("select item createtime",item.getCreateTime().toString());

        //启动EditNoteActivity 将Note传过去
        Intent intent = new Intent(NoteActivity.this,NoteEditActivity.class);
        intent.putExtra("Note",item);
        startActivity(intent);
    }

    private void exitApp(){
        if (System.currentTimeMillis() - curTimeMills > 2000) {
            Toast.makeText(NoteActivity.this, "再次单击即可退出", Toast.LENGTH_SHORT).show();
            curTimeMills = System.currentTimeMillis();
        } else {
//            try {
//                AppContext.netContext.msgControlHelper.getChannel().close().sync();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            finish();
            System.exit(0);
        }
    }


}
