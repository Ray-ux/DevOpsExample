package com.ray.course.service.impl;

import com.ray.course.entity.Course;
import com.ray.course.mapper.CourseMapper;
import com.ray.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张烈文
 * @date 2021/7/27 22:51
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> listCourseByUserId(Integer userId) {
        return courseMapper.listCourseByUserId(userId);
    }
}
