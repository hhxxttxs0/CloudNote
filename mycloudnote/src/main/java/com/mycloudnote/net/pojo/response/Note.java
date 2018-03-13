package com.mycloudnote.net.pojo.response;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable{
	private int noteId = -1;//默认值为-1 即默认为新增的Note
	private String title;
	private String content;
	private Date createTime;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
