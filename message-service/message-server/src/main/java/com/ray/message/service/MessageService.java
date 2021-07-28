package com.ray.message.service;

import com.ray.message.exception.MessageException;

/**
 * @author 张烈文
 * @date 2021/7/26 23:23
 */
public interface MessageService {


    /**
     * 发邮件or短信
     * @param mobile
     * @param email
     * @return
     */
    Boolean sendMessage(String mobile,String email) throws MessageException;
}
