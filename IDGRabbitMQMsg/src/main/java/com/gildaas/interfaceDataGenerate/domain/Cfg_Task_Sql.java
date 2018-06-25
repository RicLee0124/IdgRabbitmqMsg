package com.gildaas.interfaceDataGenerate.domain;

import java.io.Serializable;


public class Cfg_Task_Sql implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long Id;
	private Long TaskID;
	private Integer SerialNum;
	private String SerialName;
	private String SpiltChar;
	private String Sqlstr;

	@Override
	public String toString() {
		return "Cfg_Task_Sql{" +
				"Id=" + Id +
				", TaskID=" + TaskID +
				", SerialNum=" + SerialNum +
				", SerialName='" + SerialName + '\'' +
				", SpiltChar='" + SpiltChar + '\'' +
				", Sqlstr='" + Sqlstr + '\'' +
				'}';
	}

	public void setId(Long Id)
	{
		this.Id = Id;
	}

	public Long getId()
	{
		return Id;
	}

	public void setTaskID(Long TaskID)
	{
		this.TaskID = TaskID;
	}

	public Long getTaskID()
	{
		return TaskID;
	}

	public void setSerialNum(Integer SerialNum)
	{
		this.SerialNum = SerialNum;
	}

	public Integer getSerialNum()
	{
		return SerialNum;
	}

	public void setSerialName(String SerialName)
	{
		this.SerialName = SerialName;
	}

	public String getSerialName()
	{
		return SerialName;
	}

	public void setSpiltChar(String SpiltChar)
	{
		this.SpiltChar = SpiltChar;
	}

	public String getSpiltChar()
	{
		return SpiltChar;
	}

	public void setSqlstr(String Sqlstr)
	{
		this.Sqlstr = Sqlstr;
	}

	public String getSqlstr()
	{
		return Sqlstr;
	}
}
