package com.ray.course.mapper;

import com.ray.course.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 张烈文
 * @date 2021/7/27 22:34
 */

@Mapper
public interface CourseMapper {


    /**
     * 获取指定用户下的所有课程
     *
     * @param userId
     * @return
     */
    List<Course> listCourseByUserId(Integer userId);
}
