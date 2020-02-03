package com.example.event.event.instance;

import com.example.event.event.data.MerchantRegisterData;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.ApplicationEvent;


/**
 * 商户入驻事件
 * @author hcq
 * @date 2020/1/10 16:25
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MerchantRegisterEvent extends ApplicationEvent {

    public MerchantRegisterEvent(MerchantRegisterData source) {
        super(source);
    }

    @Override
    public MerchantRegisterData getSource() {
        return (MerchantRegisterData)super.getSource();
    }

}
