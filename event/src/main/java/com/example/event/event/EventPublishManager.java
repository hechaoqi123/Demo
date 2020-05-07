package com.example.event.event;

import com.example.event.core.ApplicationContextUtil;
import com.example.event.event.data.MerchantRegisterData;
import com.example.event.event.instance.MerchantRegisterEvent;

/**
 * 事件发布管理器：统一管理所有事件的发布
 *
 * @author hcq
 * @date 2020/1/13 10:21
 */
public class EventPublishManager {

    /**
     * 发布商户入驻事件
     */
    public static void publishMerchantRegisterEvent(MerchantRegisterData data) {
        ApplicationContextUtil.publishEvent(new MerchantRegisterEvent(data));
    }


}
