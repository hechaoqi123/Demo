package com.example.event.controller;

import com.example.event.annotation.ParamLog;
import com.example.event.event.EventPublishManager;
import com.example.event.event.data.MerchantRegisterData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 使用Spring Event有什么好处？
 *    1、代码级别解耦：将不影响主业务流程的部分逻辑剥离出来进行异步处理，使主业务流程更加清晰，职责更加单一。
 *    2、观察者模式的经典实现
 * @author hcq
 * @date 2020/1/10 15:54
 */
@RestController
@RequestMapping("/event")
public class EventTest {

    @RequestMapping("/testMerchantRegisterEvent")
    @ParamLog
    public String testMerchantRegisterEvent(String a){
        MerchantRegisterData eventContent= MerchantRegisterData.builder()
                .merchantId(UUID.randomUUID().toString().replace("-",""))
                .merchantName("hello world").build();
        EventPublishManager.publishMerchantRegisterEvent(eventContent);
        return "SUCCESS";
    }
}
