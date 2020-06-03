package com.example.validparam.scope;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author hcq
 * @date 2020/5/14 18:33
 */
@Component
@ThreadScope
@Data
public class UserEntity {

    private String username;

    static UserEntity entity = new UserEntity();

    public UserEntity() {
        System.out.println("创建User对象" + this);
    }
}
