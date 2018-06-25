package com.gildata;

import com.gildaas.interfaceDataGenerate.model.InterFaceTaskInfo;
import com.rabbitmq.client.*;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.batch.integration.chunk.ChunkRequest;
import org.springframework.batch.integration.chunk.ChunkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by LiChao on 2018/4/6
 */
@Repository
public class RabbitConsumer {

    @Autowired
    private RabbitMsgDao rabbitMsgDao;

    private static final String REQUEST_QUEUE_NAME = "requestsqueuelog";
    private static final String REPLY_QUEUE_NAME = "repliesqueuelog";
    private static final String IP_ADDRESS = "10.1.8.152";
    private static final int PORT = 5672;


    public void pullDataRequests() throws IOException, TimeoutException, InterruptedException {
        Connection connection = getConnection();
        final Channel channel = connection.createChannel();//创建信道
        channel.basicQos(64);//设置客户端最多接收未被ack的消息的个数
        boolean autoAck = false;
        String consumerTag = "myconsumerRequests";
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                ChunkRequest<InterFaceTaskInfo> chunkRequest = (ChunkRequest<InterFaceTaskInfo>) SerializationUtils.deserialize(body);
                rabbitMsgDao.insertRequests(chunkRequest);
                channel.basicAck(envelope.getDeliveryTag(),true);
            }
        };
        channel.basicConsume(REQUEST_QUEUE_NAME,autoAck,consumerTag,consumer);
    }


    public void pullDataReplies() throws IOException, TimeoutException, InterruptedException {
        Connection connection = getConnection();
        final Channel channel = connection.createChannel();//创建信道
        channel.basicQos(64);//设置客户端最多接收未被ack的消息的个数
        boolean autoAck = false;
        String consumerTag = "myconsumerReplies";
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                ChunkResponse chunkResponse = (ChunkResponse)SerializationUtils.deserialize(body);
                rabbitMsgDao.insertResponse(chunkResponse);
                channel.basicAck(envelope.getDeliveryTag(),true);
            }
        };
        channel.basicConsume(REPLY_QUEUE_NAME,autoAck,consumerTag,consumer);
    }

    private static Connection getConnection() throws IOException, TimeoutException {
        Address[] addresses = new Address[]{
                new Address(IP_ADDRESS,PORT)
        };
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        //这里的连接方式与生产者的demo略有不同，注意辨别区别
        return factory.newConnection(addresses);
    }
}
