package com.mycloudnote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mycloudnote.net.context.AppContext;

import static com.mycloudnote.net.context.MsgConstant.*;
import com.example.mycloudnote.R;

public class LoginActivity extends Activity{
	
	private EditText etUsername;
    private EditText etPsw;

    private Button btnLogin;
    private Button btnRegister;
//    private Button btnLosePsw;

    private Handler loginHandler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{

			switch (msg.what)
			{
			case LOGIN_SUCCESS:
				Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(LoginActivity.this, NoteActivity.class);
				startActivity(intent);
				finish();
				break;

			case LOGIN_FAILURE:
				Toast.makeText(LoginActivity.this, "请检查用户名或密码！", Toast.LENGTH_SHORT).show();
				break;

			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initEvent();

		initNet();

    }

    private void initView() {
        etPsw = (EditText) findViewById(R.id.et_userpsw);
        etUsername = (EditText) findViewById(R.id.et_username);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
//        btnLosePsw = (Button) findViewById(R.id.btn_lose_psw);
    }
    private void initEvent(){
    	Button.OnClickListener buttonListener = new Button.OnClickListener(){
    		public void onClick(View v) {
				int i = v.getId();
				if (i == R.id.btn_login) {
					login();
					return;
				} else if (i == R.id.btn_register) {
					Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
					startActivity(intent);
					return;
				}
    		}
    	};
    	btnLogin.setOnClickListener(buttonListener);
    	btnRegister.setOnClickListener(buttonListener);
//    	btnLosePsw.setOnClickListener(buttonListener);
    }
	private void initNet(){
		if(AppContext.firstStartApp) {
			AppContext.appContext = new AppContext();
			AppContext.loginHandler = loginHandler;
			AppContext.firstStartApp = false;
		}
//		new ConnectThread("10.0.2.2",8888).start();
//		try {
//			new NoteClientBootstrap("10.0.2.2",8888).connect();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
    private void login(){
    	String userName = etUsername.getText().toString();
		String userPsw = etPsw.getText().toString();
		boolean isLegal = true;
		if (TextUtils.isEmpty(userName)) {
			 etUsername.setError("用户名不能为空");
			 isLegal = false;
		}
		// 检查密码
        if (TextUtils.isEmpty(userPsw)) {
        	etPsw.setError("密码不能为空");
        	isLegal = false;
        }
        if(isLegal){
        	//发送登录请求
        	AppContext.action.requestLogin(userName, userPsw);
        }
    }

}
