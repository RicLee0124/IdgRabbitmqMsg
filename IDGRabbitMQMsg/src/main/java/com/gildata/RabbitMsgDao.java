package com.gildata;

import com.gildaas.interfaceDataGenerate.domain.Cfg_DBF_Field;
import com.gildaas.interfaceDataGenerate.domain.Cfg_Task_Sql;
import com.gildaas.interfaceDataGenerate.model.InterFaceTaskInfo;
import org.springframework.batch.integration.chunk.ChunkRequest;
import org.springframework.batch.integration.chunk.ChunkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LiChao on 2018/5/24
 */
@Repository
public class RabbitMsgDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void insertRequests(final ChunkRequest chunkRequest){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String chunkRequestSql = "insert into chunkrequest(jobId,itemsSize,stepContribution,sequence) values(?,?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(chunkRequestSql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1,chunkRequest.getJobId());
                ps.setInt(2,null==chunkRequest.getItems()?0:chunkRequest.getItems().size());
                ps.setString(3,chunkRequest.getStepContribution().toString());
                ps.setInt(4,chunkRequest.getSequence());
                return ps;
            }
        },keyHolder);
        final long chunkRequestId = keyHolder.getKey().longValue();
        if(0!=chunkRequestId){
            final String interFaceTaskInfoSql = "insert into interfaceTaskInfo(taskId,customerId,taskName,fileName,STEP_EXECUTION_ID,JOB_EXECUTION_ID,directorys,content,chunkrequestId,taskSqlsSize,dbfFieldsSize) values(?,?,?,?,?,?,?,?,?,?,?)";
            Collection<? extends InterFaceTaskInfo> items = chunkRequest.getItems();
            if(null!=items&&items.size()>0){
                Iterator<? extends InterFaceTaskInfo> it = items.iterator();
                while(it.hasNext()){
                    final InterFaceTaskInfo interFaceTaskInfo = it.next();
                    jdbcTemplate.update(new PreparedStatementCreator() {
                        @Override
                        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                            PreparedStatement ps = connection.prepareStatement(interFaceTaskInfoSql,Statement.RETURN_GENERATED_KEYS);
                            Map<String,String> map = getTaskLogData(interFaceTaskInfo.toString());
                            ps.setInt(1,Integer.valueOf(map.get("TaskId")));
                            ps.setInt(2,Integer.valueOf(map.get("CustomerId")));
                            ps.setString(3,map.get("TaskName"));
                            ps.setString(4,map.get("FileName"));
                            ps.setInt(5,Integer.valueOf(map.get("STEP_EXECUTION_ID")));
                            ps.setInt(6,Integer.valueOf(map.get("JOB_EXECUTION_ID")));
                            ps.setString(7,map.get("Directorys"));
                            ps.setString(8,interFaceTaskInfo.toString());
                            ps.setLong(9,chunkRequestId);
                            ps.setInt(10,null==interFaceTaskInfo.getTaskSqls()?0:interFaceTaskInfo.getTaskSqls().size());
                            ps.setInt(11,null==interFaceTaskInfo.getDbfFields()?0:interFaceTaskInfo.getDbfFields().size());
                            return ps;
                        }
                    },keyHolder);
                    final long interfaceTaskInfoId = keyHolder.getKey().longValue();
                    if(0!=interfaceTaskInfoId){
                        //批量添加cfg_task_sql
                        final String cfg_task_sql = "insert into Cfg_Task_sql(content,interfaceTaskInfoId) values(?,?)";
                        final List<Cfg_Task_Sql> cfg_task_sqls = interFaceTaskInfo.getTaskSqls();
                        jdbcTemplate.batchUpdate(cfg_task_sql, new BatchPreparedStatementSetter() {
                            @Override
                            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                                Cfg_Task_Sql cfg_task_sql = cfg_task_sqls.get(i);
                                preparedStatement.setString(1,cfg_task_sql.toString());
                                preparedStatement.setLong(2,interfaceTaskInfoId);
                            }

                            @Override
                            public int getBatchSize() {
                                return cfg_task_sqls.size();
                            }
                        });
                        //批量添加cfg_dbf_field
                        final String cfg_DBF_sql = "insert into Cfg_DBF_Field(content,interfaceTaskInfoId) values(?,?)";
                        final List<Cfg_DBF_Field> cfg_dbf_fields = interFaceTaskInfo.getDbfFields();
                        jdbcTemplate.batchUpdate(cfg_DBF_sql, new BatchPreparedStatementSetter() {
                            @Override
                            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                                Cfg_DBF_Field cfg_dbf_field = cfg_dbf_fields.get(i);
                                preparedStatement.setString(1,cfg_dbf_field.toString());
                                preparedStatement.setLong(2,interfaceTaskInfoId);
                            }

                            @Override
                            public int getBatchSize() {
                                return cfg_dbf_fields.size();
                            }
                        });
                    }
                }
            }
        }
    }


    public void insertResponse(final ChunkResponse chunkResponse){
        final String chunkRequestSql = "insert into chunkresponse(stepContribution,jobId,status,message,redelivered,sequence) values(?,?,?,?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(chunkRequestSql);
                ps.setString(1,chunkResponse.getStepContribution().toString());
                ps.setLong(2,chunkResponse.getJobId());
                ps.setBoolean(3,chunkResponse.isSuccessful());
                ps.setString(4,chunkResponse.getMessage());
                ps.setBoolean(5,chunkResponse.isRedelivered());
                ps.setInt(6,chunkResponse.getSequence());
                return ps;
            }
        });
    }


    public Map<String,String> getTaskLogData(String content){
        content = content.replaceAll("\n", "");
        Map<String,String> map = new HashMap<>();
        String regex = "TaskId=(.*), CustomerId=(.*), SysTaskNameId=(.*), TaskName=(.*), FileName=(.*), FileType(.*)STEP_EXECUTION_ID=(.*), JOB_EXECUTION_ID=(.*), JOB_EXECUTION_PARAMS=(.*)Directorys=(.*), TaskSqls(.*)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        if(m.find()){
            Pattern numpat = Pattern.compile("[0-9]{1,}");
            if(m.group(1)!=null&&numpat.matcher(m.group(1)).matches()){
                map.put("TaskId",m.group(1));
            }else{
                map.put("TaskId","0");
            }
            if(m.group(2)!=null&&numpat.matcher(m.group(2)).matches()){
                map.put("CustomerId",m.group(2));
            }else{
                map.put("CustomerId","0");
            }
            if (m.group(4).length() > 3) {
                map.put("TaskName", m.group(4).substring(1, m.group(4).length() - 1));
            } else {
                map.put("TaskName", "");
            }
            if(m.group(5).length()>3){
                map.put("FileName",m.group(5).substring(1,m.group(5).length()-1));
            }else{
                map.put("FileName","");
            }
            if(m.group(7)!=null&&numpat.matcher(m.group(7)).matches()){
                map.put("STEP_EXECUTION_ID",m.group(7));
            }else{
                map.put("STEP_EXECUTION_ID","0");
            }
            if(m.group(8)!=null&&numpat.matcher(m.group(8)).matches()){
                map.put("JOB_EXECUTION_ID",m.group(8));
            }else{
                map.put("JOB_EXECUTION_ID","0");
            }
            if(m.group(10).length()>3){
                map.put("Directorys",m.group(10).substring(1,m.group(10).length()-1));
            }else{
                map.put("Directorys","");
            }
        }
        return map;
    }

}
