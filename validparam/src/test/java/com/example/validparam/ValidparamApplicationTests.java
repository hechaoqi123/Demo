package com.example.validparam;

import com.example.validparam.scope.UserEntity;
import com.example.validparam.vo.DbValue;
import com.example.validparam.vo.ValueConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

class ValidparamApplicationTests {

    @Test
    public void pushPaySucMsg() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();


        Map<String, Object> param = new HashMap<>();
        param.put("jdbc.url", "testUrl");
        param.put("jdbc.username", "username");
        param.put("jdbc.password", "password");

        context.getEnvironment().getPropertySources().addFirst(new MapPropertySource("jdbc", param));

        context.register(ValueConfig.class);
        context.refresh();

        DbValue value = context.getBean(DbValue.class);
        System.out.println(value);
    }

}
