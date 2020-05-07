package com.example.validparam.model;

import com.example.validparam.controller.ParamConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;
import java.security.Permission;

/**
 * 限制参数为指定集合中的某一个元素
 *
 * @author hcq
 * @date 2020/3/10 10:13
 */
@Data
@Component
public class ParamIn extends SecurityManager  {

    ParamConfig paramConfig;

    @Autowired
    public void setParamConfig(ParamConfig paramConfig) {
        this.paramConfig = paramConfig;
    }

    /**
     * 支付类型
     */
    @Pattern(regexp = "^weixin$|^alipay$", message = "支付类型有误！")
    private String payType;

    @Override
    public void checkPermission(Permission perm) {
            throw new SecurityException("Can not change the permission dude.!");
    }
}
