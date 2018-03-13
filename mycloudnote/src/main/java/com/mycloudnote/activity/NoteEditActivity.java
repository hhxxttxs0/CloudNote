package com.mycloudnote.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycloudnote.R;
import com.mycloudnote.net.context.AppContext;
import com.mycloudnote.net.pojo.response.Note;
import static com.mycloudnote.net.context.MsgConstant.*;

import java.util.Date;

public class NoteEditActivity extends AppCompatActivity /*implements View.OnClickListener*/ {


    private Toolbar mToolbar;
    private EditText etTitle;
    private EditText etContent;
    private Menu menu_done;
    private Note note ;

    private Handler editnoteHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
//			dialog.dismiss();

            switch (msg.what)
            {
                case CREATENOTE_SUCCESS:
                    Toast.makeText(NoteEditActivity.this, "新建笔记成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;

                case EDITNOTE_SUCCESS:
                    Toast.makeText(NoteEditActivity.this, "修改笔记成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case CREATENOTE_FAILURE:
                    Toast.makeText(NoteEditActivity.this, "新建笔记失败", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case EDITNOTE_FAILURE:
                    Toast.makeText(NoteEditActivity.this, "修改笔记失败", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                default:
                    Toast.makeText(NoteEditActivity.this, "发生未知错误", Toast.LENGTH_SHORT).show();

                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        initView();
        // 显示返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // 监听Back键,必须放在设置back键后面
//        mToolbar.setNavigationOnClickListener(this);

        initValue();
        AppContext.editnoteHandler = editnoteHandler;
    }

    private void initView() {

        mToolbar = (Toolbar) findViewById(R.id.id_toolbar_edit);
        setSupportActionBar(mToolbar);
        etTitle = (EditText) findViewById(R.id.id_et_title);
        etContent = (EditText) findViewById(R.id.id_et_content);
        menu_done = (Menu) findViewById(R.id.action_edit_done);

    }
    private void initValue(){
        Log.i("EditNoteactivity","initValue");
        note = new Note();
        Intent i = getIntent();
        if(i.getExtras()!=null){
            Log.i("EditNoteactivity","extras is not null");
            note = (Note) i.getSerializableExtra("Note");
            etTitle.setText(note.getTitle().toString());
            etContent.setText(note.getContent().toString());
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.i("EditNoteactivity","onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.note_edit, menu);
        return true;
    }

//    @Override
//    public void onClick(View v) {
//        Log.i("EditNoteactivity","click");
//
//
//        onBackPressed();
//
//
//    }
    // 菜单项被选择事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("EditNoteactivity","select");
        switch (item.getItemId()) {
            case R.id.action_edit_done:
                doEdit();
                break;
            default:
                onBackPressed();
                break;
        }
        return true;
    }
    private void doEdit(){
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        boolean isLegal = true;

            if (TextUtils.isEmpty(title)) {
                etTitle.setError("标题不能为空");
                isLegal = false;
            }
        if (isLegal == true) {
            note.setTitle(title);
            note.setContent(content);
            note.setCreateTime(new Date());
            //

            AppContext.action.requestEditNote(AppContext.user.getUserId(), AppContext.user.getUserName(),
                    note);
//          finish();
            }

    }
}
