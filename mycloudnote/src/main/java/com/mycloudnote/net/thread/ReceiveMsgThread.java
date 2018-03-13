package com.mycloudnote.net.thread;

import com.mycloudnote.net.context.NetContext;

public class ReceiveMsgThread extends AbstarctMsgThread {

	private static volatile ReceiveMsgThread mReceiveMsgThread;
	private NetContext netContext;

	
	public static ReceiveMsgThread getSingleInstance(NetContext netContext) {
		
		if (mReceiveMsgThread == null) {
			
			synchronized (ReceiveMsgThread.class) {
				if (mReceiveMsgThread == null){
					mReceiveMsgThread = new ReceiveMsgThread("ReceiveMsgThread");
					mReceiveMsgThread.setNetContext(netContext);
				}

			}
		}
		return mReceiveMsgThread;

	}

	public ReceiveMsgThread(String threadName) {
		super(threadName);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void postMessage(Object mes) {
		// TODO Auto-generated method stub
		try {
		
			queue.put(mes);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onMessage(Object msg) {
		// TODO Auto-generated method stub
	
		netContext.msgControlHelper.dispatchNetMsg(msg);
		return true;
	}

	public void setNetContext(NetContext netContext) {
		this.netContext = netContext;
	}

}
