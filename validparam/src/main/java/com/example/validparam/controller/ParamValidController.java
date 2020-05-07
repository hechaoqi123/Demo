package com.example.validparam.controller;

import com.example.validparam.model.ParamIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参数校验
 *
 * @author hcq
 * @date 2020/3/9 18:07
 */
@RestController
@RequestMapping("/paramValid")
public class ParamValidController {

    @Autowired
    private ParamIn paramIn;

    @RequestMapping("/paramIn")
    public String paramIn() {
        return "SUCCESS";
    }

}
