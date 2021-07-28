package com.ray.dto;

import com.ray.entity.User;
import lombok.Data;

/**
 * @author 张烈文
 * @date 2021/7/27 14:43
 */
@Data
public class UserDTO extends User {
    private String code;
}
