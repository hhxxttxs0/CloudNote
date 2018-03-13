package com.mycloudnote.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycloudnote.R;
import com.mycloudnote.net.context.AppContext;

import static com.mycloudnote.net.context.MsgConstant.CHANGEPASSWORD_FAILURE;
import static com.mycloudnote.net.context.MsgConstant.CHANGEPASSWORD_SUCCES;

public class ChangePswActivity extends AppCompatActivity {

    private Button btnChangeOk;
    private EditText etPswOld;
    private EditText etPsw;
    private EditText etPswVerify;
    private Handler changePswHandler = new Handler(){
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case CHANGEPASSWORD_SUCCES:
                    Toast.makeText(ChangePswActivity.this, "修改密码成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;

                case CHANGEPASSWORD_FAILURE:
                    Toast.makeText(ChangePswActivity.this, "修改密码失败，请确认原始密码！", Toast.LENGTH_SHORT).show();
                    break;

            }
        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_psw);

        initView();
        initEvent();
        AppContext.changepswHandler = changePswHandler;
    }
    public void initView(){
        btnChangeOk = (Button) findViewById(R.id.btn_change_psw_ok);
        etPswOld = (EditText) findViewById(R.id.et_change_psw_old);
        etPsw = (EditText) findViewById(R.id.et_change_psw_new);
        etPswVerify = (EditText) findViewById(R.id.et_change_psw_verify);
    }
    private void initEvent(){
        btnChangeOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                changePassword();
            }
        });
    }
    public void changePassword(){
        String oldPsw = etPswOld.getText().toString();
        String newPsw = etPsw.getText().toString();
        String verifyPsw = etPswVerify.getText().toString();
        boolean isLegal = true;
        if (TextUtils.isEmpty(newPsw) || newPsw.length()<6 || newPsw.length()>15) {
            etPsw.setError("密码必须6-15位");
            isLegal = false;
        }
        if(!newPsw.equals(verifyPsw)){
            etPswVerify.setError("两次密码不一致");
            isLegal = false;
        }
        if(isLegal){
            AppContext.action.requestChangePassword(AppContext.user.getUserId(),oldPsw,newPsw);
        }
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
