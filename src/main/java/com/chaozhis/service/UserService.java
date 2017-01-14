package com.chaozhis.service;

import com.chaozhis.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author fangying | 2016-03-04
 */
@Service("UserService")
public class UserService {

    @Autowired
    private BaseService baseService;

    public UserDTO queryUserById(int user_id) {
        String sql = "select * from cz_web_user where id = ?";
        return baseService.executeQueryOneRecord(sql, UserDTO.class, user_id);
    }



}
