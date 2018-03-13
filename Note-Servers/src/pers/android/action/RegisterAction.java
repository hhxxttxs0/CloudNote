package pers.android.action;

import com.mycloudnote.net.pojo.request.RegisterRequest;
import com.mycloudnote.net.pojo.response.RegisterResponse;
import com.mycloudnote.net.pojo.response.LoginResponse.LoginState;
import com.mycloudnote.net.pojo.response.RegisterResponse.RegisterState;

import pers.android.dao.RegisterDao;

public class RegisterAction {
	private RegisterRequest regReq;
	private RegisterDao dao= new RegisterDao();
	
	public RegisterAction(RegisterRequest regReq){
		this.regReq = regReq;
	}
	public RegisterResponse getRegisterResponse(){
		RegisterResponse resp = new RegisterResponse();
		resp.setUserName(regReq.getUserName());
		if(dao.isRegisterSuccessful(regReq.getUserName(),regReq.getPassword())){
			resp.setRegisterState(RegisterState.RegisterSuccess);
		}
		else{
			resp.setRegisterState(RegisterState.RegisterFailure);
		}
		return resp;
	}
}
