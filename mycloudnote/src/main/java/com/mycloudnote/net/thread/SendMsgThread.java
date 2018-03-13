package com.mycloudnote.net.thread;

import com.mycloudnote.net.context.NetContext;

public class SendMsgThread extends AbstarctMsgThread {

	private static volatile SendMsgThread mSendMsgThread;
	private NetContext netContext;

	public static SendMsgThread getSingleInstance(NetContext netContext) {
		if (mSendMsgThread == null) {
			synchronized (ReceiveMsgThread.class) {
				if (mSendMsgThread == null) {
					mSendMsgThread = new SendMsgThread("SendMsgThread");
					mSendMsgThread.setNetContext(netContext);
				}

			}
		}
		return mSendMsgThread;

	}

	private void setNetContext(NetContext netContext2) {
		// TODO Auto-generated method stub
		this.netContext = netContext2;

	}

	public SendMsgThread(String threaName) {
		super(threaName);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * 将要发送的消息进队
	 */
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
	/**
	 * 发送消息队列出队并发送到服务器
	 */
	public boolean onMessage(Object msg) {
		// TODO Auto-generated method stub

		netContext.msgControlHelper.writeAndFlush(msg);
		return true;
	}

}
