package com.example.validparam.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author hcq
 * @date 2020/5/14 11:27
 */
@Component
public class DbValue {

    /**
     * URL
     */
    @Value("${jdbc.url}")
    private String url;

    /**
     * username
     */
    @Value("${jdbc.username}")
    private String userName;

    /**
     * password
     */
    @Value("${jdbc.password}")
    private String password;

    @Override
    public String toString() {
        return "DbValue{" +
                "url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
