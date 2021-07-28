package com.ray.course.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张烈文
 * @date 2021/7/27 22:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    private Integer id;
    private String courseName;
    private String description;
}
