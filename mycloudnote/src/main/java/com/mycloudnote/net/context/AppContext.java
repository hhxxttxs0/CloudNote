package com.mycloudnote.net.context;

import android.os.Handler;
import com.mycloudnote.net.action.InterAction;
import com.mycloudnote.net.pojo.response.Note;

import java.util.ArrayList;

/**
 * 保存全局context
 * @author c
 *
 */
public class AppContext {
	public static volatile AppContext appContext;
	public static NetContext netContext;
	public static UserInfo user;
	public static ArrayList<Note> noteList;
	public static InterAction action;
	public static Handler loginHandler;
	public static Handler registerHandler;
	public static Handler notelistHandler;
	public static Handler editnoteHandler;
	public static Handler noteActivityHandler;
	public static Handler changepswHandler;
	public static boolean firstStartApp = true;

	public AppContext(){
		user = new UserInfo();
		netContext = new NetContext();
		action = new InterAction();
	}

	
}
