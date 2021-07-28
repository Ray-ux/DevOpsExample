package com.ray.message.service.impl;

import com.ray.message.exception.MessageException;
import com.ray.message.service.MessageService;
import com.ray.message.util.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author 张烈文
 * @date 2021/7/26 23:24
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageSender messageSender;

    @Override
    public Boolean sendMessage(String mobile, String email) throws MessageException {
        if (StringUtils.isEmpty(mobile) && StringUtils.isEmpty(email)) {
            log.error("mobile , email不允许都为null");
            throw new MessageException("mobile , email不允许都为null");
        }
        try {
            if (!StringUtils.isEmpty(mobile)) {
                return messageSender.sendMobile(mobile);
            } else {
                return messageSender.sendEmail(email);
            }
        } catch (Exception e) {
            log.error("信息发送失败");
            throw new MessageException(e.getMessage());
        }
    }
}
