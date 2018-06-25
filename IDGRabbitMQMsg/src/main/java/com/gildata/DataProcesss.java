package com.gildata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by LiChao on 2018/6/12
 */

@Component
public class DataProcesss implements CommandLineRunner {


    private final Logger loger = LoggerFactory.getLogger(DataProcesss.class);

    @Autowired
    private RabbitConsumer rabbitConsumer;


    @Override
    public void run(String... strings) throws Exception {
        loger.debug("start concuming .....");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    rabbitConsumer.pullDataRequests();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    rabbitConsumer.pullDataReplies();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
