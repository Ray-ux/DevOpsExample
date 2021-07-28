package com.ray.course.controller;

import com.ray.course.entity.Course;
import com.ray.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 张烈文
 * @date 2021/7/28 0:21
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("list")
    public List<Course> listCourse(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        return courseService.listCourseByUserId(userId);
    }
}
