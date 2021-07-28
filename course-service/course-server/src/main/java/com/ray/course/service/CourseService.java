package com.ray.course.service;

import com.ray.course.entity.Course;

import java.util.List;

/**
 * @author 张烈文
 * @date 2021/7/27 22:50
 */
public interface CourseService {

    /**
     * 获取指定用户下的所有课程
     *
     * @param userId
     * @return
     */
    List<Course> listCourseByUserId(Integer userId);
 }
