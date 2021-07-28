package com.ray.user.mapper;

import com.ray.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 张烈文
 * @date 2021/7/27 14:27
 */
@Mapper
public interface UserMapper {

    /**
     * 查询
     * @param name
     * @param password
     * @return
     */
    User findStuByNameAndPwd(@Param("name") String name, @Param("password") String password);

    /**
     * 插入
     * @param user
     */
    void insertStu(User user);

    User findStuById(Integer id);

}
