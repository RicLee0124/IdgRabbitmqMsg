package com.gildaas.interfaceDataGenerate.model;

import com.gildaas.interfaceDataGenerate.domain.Cfg_DBF_Field;
import com.gildaas.interfaceDataGenerate.domain.Cfg_Task_Sql;
import com.gildaas.interfaceDataGenerate.enumeration.ExportFileType;

import java.io.Serializable;
import java.util.List;

public class InterFaceTaskInfo implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1546625716731077904L;

	
	private Long TaskId;
	private Long CustomerId;
	private Long SysTaskNameId;
	private String TaskName;
	private String FileName;
	private ExportFileType FileType;
	private String IfCompre;
	private String IfFirstRowByName;
	private String IfDoThreeDayTask;
	private String IfNoCreateEmptyFile;
	private String IfMonitDataNum;
	private Long MinDataNum;
	private String IfMonitFileSize;
	private Float MinFileSize;
	private Float MaxFileSize;
	private String IfIssued;
	private String IfUse;
	private String IfSpecifiedFileName;
	private String SpecifiedSql;
	private String Charset;
	private String IfCreateToTempDir;
	
	/**
	 * 文件类型后缀是否大写
	 */
	private Boolean ifCapitalFileType = false;
	
	/**
	 * 任务执行数据库ID
	 */
	private Long dbInfoID;
	
	/**
	 * 步骤执行ID
	 */
	private Long STEP_EXECUTION_ID;
	/**
	 * JobID
	 */
	private Long JOB_EXECUTION_ID;
	/**
	 * Job参数
	 */
	private String JOB_EXECUTION_PARAMS;
	
	/**
	 * 执行者
	 */
	private Long Performer;
	
	/**
	 * 执行者类型
	 */
	private String PerformerType;
	
	
	private String FilePach="/opt/gildata/gdaas/pivotal/share/InterFaceDBData";
	private String tempFilePath="/opt/gildata/gdaas/pivotal/share/tempInterFaceDBData";
	
	
	//任务生成目录列表
	private List<String> Directorys;

	//任务的sql配置信息
	private List<Cfg_Task_Sql> TaskSqls;
	
	//DBF字段配置
    private List<Cfg_DBF_Field> dbfFields;
    
    //发送邮箱列表
    private List<String> emailLists;

	@Override
	public String toString() {
		return "InterFaceTaskInfo{" +
				"TaskId=" + TaskId +
				", CustomerId=" + CustomerId +
				", SysTaskNameId=" + SysTaskNameId +
				", TaskName='" + TaskName + '\'' +
				", FileName='" + FileName + '\'' +
				", FileType=" + FileType +
				", IfCompre='" + IfCompre + '\'' +
				", IfFirstRowByName='" + IfFirstRowByName + '\'' +
				", IfDoThreeDayTask='" + IfDoThreeDayTask + '\'' +
				", IfNoCreateEmptyFile='" + IfNoCreateEmptyFile + '\'' +
				", IfMonitDataNum='" + IfMonitDataNum + '\'' +
				", MinDataNum=" + MinDataNum +
				", IfMonitFileSize='" + IfMonitFileSize + '\'' +
				", MinFileSize=" + MinFileSize +
				", MaxFileSize=" + MaxFileSize +
				", IfIssued='" + IfIssued + '\'' +
				", IfUse='" + IfUse + '\'' +
				", IfSpecifiedFileName='" + IfSpecifiedFileName + '\'' +
				", SpecifiedSql='" + SpecifiedSql + '\'' +
				", Charset='" + Charset + '\'' +
				", IfCreateToTempDir='" + IfCreateToTempDir + '\'' +
				", ifCapitalFileType=" + ifCapitalFileType +
				", dbInfoID=" + dbInfoID +
				", STEP_EXECUTION_ID=" + STEP_EXECUTION_ID +
				", JOB_EXECUTION_ID=" + JOB_EXECUTION_ID +
				", JOB_EXECUTION_PARAMS='" + JOB_EXECUTION_PARAMS + '\'' +
				", Performer=" + Performer +
				", PerformerType='" + PerformerType + '\'' +
				", FilePach='" + FilePach + '\'' +
				", tempFilePath='" + tempFilePath + '\'' +
				", Directorys=" + Directorys +
				", TaskSqls.size=" + TaskSqls.size() +
				", dbfFields.size=" + dbfFields.size() +
				", emailLists=" + emailLists +
				'}';
	}

	public void setIfSpecifiedFileName(String IfSpecifiedFileName)
	{
		this.IfSpecifiedFileName = IfSpecifiedFileName;
	}

	public String getIfSpecifiedFileName()
	{
		return IfSpecifiedFileName;
	}

	public void setSpecifiedSql(String SpecifiedSql)
	{
		this.SpecifiedSql = SpecifiedSql;
	}

	public String getSpecifiedSql()
	{
		return SpecifiedSql;
	}
	
	public  List<String> getEmailLists()
	{
		return emailLists;
	}
	
	public void setEmailLists(List<String> emailLists)
	{
		this.emailLists=emailLists;
	}

	public  List<String> getDirectorys()
	{
		return Directorys;
	}
	
	public void setDirectorys(List<String> Directorys)
	{
		this.Directorys=Directorys;
	}
	
	public  List<Cfg_DBF_Field> getDbfFields()
	{
		return dbfFields;
	}
	
	public void setDbfFields(List<Cfg_DBF_Field> dbfFields)
	{
		this.dbfFields=dbfFields;
	}
	
	public  List<Cfg_Task_Sql> getTaskSqls(){
		return TaskSqls;
	}
	
	public void setTaskSqls(List<Cfg_Task_Sql> TaskSqls){
		this.TaskSqls=TaskSqls;
		}

	public void setTaskId(Long TaskId){
	this.TaskId=TaskId;
	}


	public Long getTaskId(){
		return TaskId;
	}
	public void setCustomerId(Long CustomerId){
	this.CustomerId=CustomerId;
	}


	public Long getCustomerId(){
		return CustomerId;
	}
	public void setSysTaskNameId(Long SysTaskNameId){
	this.SysTaskNameId=SysTaskNameId;
	}


	public Long getSysTaskNameId(){
		return SysTaskNameId;
	}
	public void setTaskName(String TaskName){
	this.TaskName=TaskName;
	}


	public String getTaskName(){
		return TaskName;
	}
	public void setFileName(String FileName){
	this.FileName=FileName;
	}


	public String getFileName(){
		return FileName;
	}
	public void setFileType(ExportFileType FileType){
	this.FileType=FileType;
	}


	public ExportFileType getFileType(){
		return FileType;
	}
	public void setIfCompre(String IfCompre){
	this.IfCompre=IfCompre;
	}


	public String getIfCompre(){
		return IfCompre;
	}
	public void setIfFirstRowByName(String IfFirstRowByName){
	this.IfFirstRowByName=IfFirstRowByName;
	}


	public String getIfFirstRowByName(){
		return IfFirstRowByName;
	}
	public void setIfDoThreeDayTask(String IfDoThreeDayTask){
	this.IfDoThreeDayTask=IfDoThreeDayTask;
	}


	public String getIfDoThreeDayTask(){
		return IfDoThreeDayTask;
	}
	public void setIfNoCreateEmptyFile(String IfNoCreateEmptyFile){
	this.IfNoCreateEmptyFile=IfNoCreateEmptyFile;
	}


	public String getIfNoCreateEmptyFile(){
		return IfNoCreateEmptyFile;
	}
	public void setIfMonitDataNum(String IfMonitDataNum){
	this.IfMonitDataNum=IfMonitDataNum;
	}


	public String getIfMonitDataNum(){
		return IfMonitDataNum;
	}
	public void setMinDataNum(Long MinDataNum){
	this.MinDataNum=MinDataNum;
	}


	public Long getMinDataNum(){
		return MinDataNum;
	}
	public void setIfMonitFileSize(String IfMonitFileSize){
	this.IfMonitFileSize=IfMonitFileSize;
	}


	public String getIfMonitFileSize(){
		return IfMonitFileSize;
	}
	public void setMinFileSize(Float MinFileSize){
	this.MinFileSize=MinFileSize;
	}


	public Float getMinFileSize(){
		return MinFileSize;
	}
	public void setMaxFileSize(Float MaxFileSize){
	this.MaxFileSize=MaxFileSize;
	}


	public Float getMaxFileSize(){
		return MaxFileSize;
	}
	public void setIfIssued(String IfIssued){
	this.IfIssued=IfIssued;
	}


	public String getIfIssued(){
		return IfIssued;
	}
	public void setIfUse(String IfUse){
	this.IfUse=IfUse;
	}


	public String getIfUse(){
		return IfUse;
	}

	public Long getPerformer()
	{
		return Performer;
	}

	public void setPerformer(Long performer)
	{
		Performer = performer;
	}

	public String getPerformerType()
	{
		return PerformerType;
	}

	public void setPerformerType(String performerType)
	{
		PerformerType = performerType;
	}

	public Long getSTEP_EXECUTION_ID()
	{
		return STEP_EXECUTION_ID;
	}

	public void setSTEP_EXECUTION_ID(Long sTEP_EXECUTION_ID)
	{
		STEP_EXECUTION_ID = sTEP_EXECUTION_ID;
	}

	public Long getJOB_EXECUTION_ID()
	{
		return JOB_EXECUTION_ID;
	}

	public void setJOB_EXECUTION_ID(Long jOB_EXECUTION_ID)
	{
		JOB_EXECUTION_ID = jOB_EXECUTION_ID;
	}

	public String getJOB_EXECUTION_PARAMS()
	{
		return JOB_EXECUTION_PARAMS;
	}

	public void setJOB_EXECUTION_PARAMS(String jOB_EXECUTION_PARAMS)
	{
		JOB_EXECUTION_PARAMS = jOB_EXECUTION_PARAMS;
	}

	public String getFilePach()
	{
		return FilePach;
	}

	public void setFilePach(String filePach)
	{
		FilePach = filePach;
	}

	public String getCharset() {
		return Charset;
	}

	public void setCharset(String charset) {
		Charset = charset;
	}

	public Long getDbInfoID()
	{
		return dbInfoID;
	}

	public void setDbInfoID(Long dbInfoID)
	{
		this.dbInfoID = dbInfoID;
	}

	public String getIfCreateToTempDir()
	{
		return IfCreateToTempDir;
	}

	public void setIfCreateToTempDir(String ifCreateToTempDir)
	{
		IfCreateToTempDir = ifCreateToTempDir;
	}

	public String getTempFilePath()
	{
		return tempFilePath;
	}

	public void setTempFilePath(String tempFilePath)
	{
		this.tempFilePath = tempFilePath;
	}

	public Boolean getIfCapitalFileType() {
		return ifCapitalFileType;
	}

	public void setIfCapitalFileType(Boolean ifCapitalFileType) {
		this.ifCapitalFileType = ifCapitalFileType;
	}
	
}
