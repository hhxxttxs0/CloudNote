package com.mycloudnote.net.thread;

public interface NetMsgHandleInterface {
	void postMessage(Object mes);
	 boolean onMessage(Object msg);
}
