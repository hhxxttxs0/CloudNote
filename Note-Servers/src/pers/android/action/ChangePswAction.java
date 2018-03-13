package pers.android.action;

import com.mycloudnote.net.pojo.request.ChangePswRequest;
import com.mycloudnote.net.pojo.response.ChangePswResponse;
import com.mycloudnote.net.pojo.response.ChangePswResponse.ChangePswState;

import pers.android.dao.ChangePswDao;

public class ChangePswAction {
	ChangePswRequest req;
	ChangePswDao dao = new ChangePswDao();

	public ChangePswAction(ChangePswRequest req) {
		this.req = req;
	}
	public ChangePswResponse getChangePswResponse(){
		ChangePswResponse resp = new ChangePswResponse();
		if(dao.changePassword(req.getUserId(), req.getOldPsw(), req.getNewPsw())){
			resp.setChangePswState(ChangePswState.ChangePswSuccess);
		}
		else
			resp.setChangePswState(ChangePswState.ChangePswFailure);
		return resp;
		
	}
}
