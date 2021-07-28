package com.ray.user.response;

/**
 * @author 张烈文
 * @date 2021/7/27 16:19
 */
public class ResponseUtil {

    public static Response success() {
        return common(null, "成功", 1);
    }

    public static Response success(Object data) {
        return common(data, "成功", 1);
    }

    private static Response common(Object data, String message, Integer code) {
        Response response = new Response();
        response.setCode(code);
        response.setData(data);
        response.setMessage(message);
        return response;
    }

    public static Response error() {
        return common(null, "失败", 0);
    }

    public static Response error(String message) {
        return common(null, message, 0);
    }
}
