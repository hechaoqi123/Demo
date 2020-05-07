package com.example.event.core;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 用于获取Spring容器上下文对象
 *
 * @author hcq
 * @date 2020/1/10 14:52
 */
@Component
@Data
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 获取指定类型的所有bean集合
     *
     * @param cla beanType
     * @return Map<BeanName, Bean>
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> cla) {
        return context.getBeansOfType(cla);
    }

    /**
     * 获取指定类型的Bean
     *
     * @param cla beanType
     * @return Bean
     */
    public static <T> T getBean(Class<T> cla) {
        return context.getBean(cla);
    }

    /**
     * 获取指定名称的Bean
     *
     * @param beanName beanName
     * @return Bean
     */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    /**
     * 发布事件
     */
    public static void publishEvent(Object obj) {
        context.publishEvent(obj);
    }
}
