package com.example.event.event.data;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商户入驻数据模型
 *
 * @author hcq
 * @date 2020/1/13 11:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class MerchantRegisterData {
    /**
     * 商户id
     */
    private String merchantId;
    /**
     * 商户名称
     */
    private String merchantName;

}