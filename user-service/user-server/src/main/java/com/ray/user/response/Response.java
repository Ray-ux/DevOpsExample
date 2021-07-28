package com.ray.user.response;

import lombok.Data;

/**
 * @author 张烈文
 * @date 2021/7/27 16:18
 */
@Data
public class Response {
    private Integer code;
    private String message;
    private Object data;
}
