package com.mycloudnote.net.context;

import android.util.Log;

import com.mycloudnote.net.control.MsgControlHelper;
import com.mycloudnote.net.thread.ConnectThread;
import com.mycloudnote.net.thread.ReceiveMsgThread;
import com.mycloudnote.net.thread.SendMsgThread;


public class NetContext {
	public static final MsgControlHelper msgControlHelper = new MsgControlHelper();
	public static ReceiveMsgThread recvMsgThread;
	public static SendMsgThread sendMsgThread;
	public static ConnectThread connectThread;

	public NetContext() {
		recvMsgThread = ReceiveMsgThread.getSingleInstance(this);
		sendMsgThread = SendMsgThread.getSingleInstance(this);
		connectThread = ConnectThread.getSingleInstance();
		initThread();
	}

	private void initThread() {
		recvMsgThread.start();
		sendMsgThread.start();
		connectThread.start();
	}

	public void sendNetMsg(Object msg) { 
		sendMsgThread.postMessage(msg);
	}

	public void receiveNetMsg(Object msg) { 
		recvMsgThread.postMessage(msg);
	}

	public boolean isNetOpen() {
		return msgControlHelper.getChannel().isOpen();
	}

	public boolean isNetActive() {
		return msgControlHelper.getChannel().isActive();
	}

}

