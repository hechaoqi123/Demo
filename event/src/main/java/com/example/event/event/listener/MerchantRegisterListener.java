package com.example.event.event.listener;

import com.example.event.annotation.ParamLog;
import com.example.event.event.instance.MerchantRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


/**
 * 商户入驻时间监听
 *
 * @author hcq
 * @date 2020/1/10 15:24
 */
@Component
@Slf4j
public class MerchantRegisterListener {

    @Async
    @EventListener
    @ParamLog
    public void merchantRegisterEventHandler(MerchantRegisterEvent event) {
    }
}
