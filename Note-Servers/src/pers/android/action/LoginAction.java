package pers.android.action;

import com.mycloudnote.net.pojo.request.LoginRequest;
import com.mycloudnote.net.pojo.response.LoginResponse;
import com.mycloudnote.net.pojo.response.LoginResponse.LoginState;

import pers.android.dao.LoginDao;

public class LoginAction {
	private LoginRequest loginReq;
	private LoginDao dao ;
	
	public LoginAction(LoginRequest loginReq){
		this.loginReq = loginReq;
		dao = new LoginDao();
	}
	public LoginResponse getLoginResponse(){
		LoginResponse resp = new LoginResponse();
		resp.setUserId(dao.getNoteId(loginReq.getUserName()));
		resp.setUserName(loginReq.getUserName());		
		resp.setNotes(dao.getNoteList(loginReq.getUserName()));			
		if(dao.isLoginSuccessful(loginReq.getUserName(), loginReq.getPassword())){
			resp.setLoginState(LoginState.LoginSuccess);
		}
		else{
			resp.setLoginState(LoginState.LoginFailure);
		}
		return resp;
	}
	
}
