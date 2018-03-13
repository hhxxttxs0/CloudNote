package com.mycloudnote.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.mycloudnote.net.context.AppContext;
import static com.mycloudnote.net.context.MsgConstant.*;
import com.example.mycloudnote.R;

public class RegisterActivity extends Activity{
	
	private Button btnRegisterOk;
	private EditText etUsername;
	private EditText etPsw;
	private EditText etPswVerify;
	

	private Handler registerHandler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case REGISTER_SUCCESS:
				Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
				finish(); 
				break;

			case REGISTER_FAILURE:
				Toast.makeText(RegisterActivity.this, "注册失败，请更换用户名！", Toast.LENGTH_SHORT).show();
				break;

			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);		
		initView();
		initEvent();
		AppContext.registerHandler = registerHandler;
    }
	private void initView() {
		btnRegisterOk = (Button)findViewById(R.id.btn_register_ok);
		etUsername = (EditText)findViewById(R.id.et_reg_username);
		etPsw = (EditText)findViewById(R.id.et_reg_userpsw);
		etPswVerify = (EditText)findViewById(R.id.et_reg_userpsw_verify);
	}
	private void initEvent(){
		btnRegisterOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				register();
			}
		});
	}
	private void register(){
		String userName = etUsername.getText().toString();
		String userPsw = etPsw.getText().toString();
		String userPswVfy = etPswVerify.getText().toString();
		boolean isLegal = true;
		if (TextUtils.isEmpty(userName) || userName.length()<3 || userName.length()>10) {
			 etUsername.setError("用户名字符数必须3-10位");
			 isLegal = false;
		}
		// 检查密码
        if (TextUtils.isEmpty(userPsw) || userPsw.length()<6 || userPsw.length()>15) {
        	etPsw.setError("密码必须6-15位");
        	isLegal = false;
        }
        if (!userPsw.equals(userPswVfy)) {
        	etPswVerify.setError("两次密码不一致");
        	isLegal = false;
        }
        if(isLegal){
        	//发送注册请求
        	AppContext.action.requestRegister(userName, userPsw);
        }
	}
	@Override
	public void onBackPressed() {
		finish();
	}
}
