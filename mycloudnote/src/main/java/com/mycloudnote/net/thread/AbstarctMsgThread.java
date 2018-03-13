package com.mycloudnote.net.thread;

import java.util.concurrent.LinkedBlockingQueue;
import android.util.Log;

public abstract class AbstarctMsgThread extends Thread implements NetMsgHandleInterface {

	public String name="AbstarctMsgThread";
	public  LinkedBlockingQueue queue = new LinkedBlockingQueue(1000);

	private volatile boolean isStart;
	
	
	public AbstarctMsgThread(String threaName){
		super(threaName);
		this.name=threaName;
	}

	public boolean isThreadStartState() {
		return isStart;
	}

	public void setThradStartState() {
		isStart = true;
	}

	public void setThradStopState() {
		isStart = false;
	}

	public void setQueue(LinkedBlockingQueue queue) {
		this.queue = queue;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Log.i("msgThread", name+"线程启动");
		while (true) {
			try {
				// isStart=true;
				Object vsInfoData = queue.take();
				if (!onMessage(vsInfoData) || !isStart) {
					if(name.equals("SendMsgThread")){
						Log.e("msgThread", "发送数据线程停止运行");
					}else{
						Log.e("msgThread", "接收数据线程停止运行");
					}
					isStart = false;
					break;
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public synchronized void start() {
		isStart = true;

		super.start();

	}

}
