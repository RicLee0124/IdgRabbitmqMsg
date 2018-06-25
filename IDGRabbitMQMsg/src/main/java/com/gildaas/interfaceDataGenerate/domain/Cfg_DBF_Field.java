package com.gildaas.interfaceDataGenerate.domain;

import java.io.Serializable;

public class Cfg_DBF_Field implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long Id;
	private Long TaskID;
	private String FieldName;
	private String FieldCName;
	private String FieldType;
	private Integer FieldLength;
	private Integer FieldPrecision;

	@Override
	public String toString() {
		return "Cfg_DBF_Field{" +
				"Id=" + Id +
				", TaskID=" + TaskID +
				", FieldName='" + FieldName + '\'' +
				", FieldCName='" + FieldCName + '\'' +
				", FieldType='" + FieldType + '\'' +
				", FieldLength=" + FieldLength +
				", FieldPrecision=" + FieldPrecision +
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

	public void setFieldName(String FieldName)
	{
		this.FieldName = FieldName;
	}

	public String getFieldName()
	{
		return FieldName;
	}

	public void setFieldCName(String FieldCName)
	{
		this.FieldCName = FieldCName;
	}

	public String getFieldCName()
	{
		return FieldCName;
	}

	public void setFieldType(String FieldType)
	{
		this.FieldType = FieldType;
	}

	public String getFieldType()
	{
		return FieldType;
	}

	public void setFieldLength(Integer FieldLength)
	{
		this.FieldLength = FieldLength;
	}

	public Integer getFieldLength()
	{
		return FieldLength;
	}

	public void setFieldPrecision(Integer FieldPrecision)
	{
		this.FieldPrecision = FieldPrecision;
	}

	public Integer getFieldPrecision()
	{
		return FieldPrecision;
	}
}
