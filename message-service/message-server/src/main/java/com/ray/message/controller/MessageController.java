package com.ray.message.controller;

import com.ray.message.exception.MessageException;
import com.ray.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张烈文
 * @date 2021/7/26 23:25
 */
@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/sendMessage")
    public Boolean sendMessage(String mobile, String email) throws MessageException {
        return messageService.sendMessage(mobile, email);
    }
}
