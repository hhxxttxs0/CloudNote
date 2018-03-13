package com.mycloudnote.net.thread;

import com.mycloudnote.net.client.NoteClientBootstrap;

public class ConnectThread extends Thread {

	private NoteClientBootstrap ncb;
	public volatile static boolean toConnect = true;
	private static volatile ConnectThread connectThread;

	public ConnectThread(String host, int port) {
		super("ConnectThread");
		ncb = new NoteClientBootstrap(host, port);
	}
	public static ConnectThread getSingleInstance() {

		if (connectThread == null) {

			synchronized (ConnectThread.class) {
				if (connectThread == null){
					connectThread = new ConnectThread("192.168.155.1",8888);
				}

			}
		}
		return connectThread;

	}
	@Override
	public void run() {

		try {
			if (toConnect)
				ncb.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
