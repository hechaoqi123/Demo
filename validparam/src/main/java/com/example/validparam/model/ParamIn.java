package com.example.validparam.model;

import com.example.validparam.annotation.verify.In;
import com.example.validparam.annotation.verify.Or;
import com.example.validparam.annotation.verify.PhoneNumber;
import lombok.Data;
import org.springframework.stereotype.Component;


/**
 * 限制参数为指定集合中的某一个元素
 *
 * @author hcq
 * @date 2020/3/10 10:13
 */
@Data
@Component
public class ParamIn {

    /**
     * 支付类型
     */
    @PhoneNumber
    private String payType;

    /**
     * 支付授权码
     */
    private String authCode;

}
