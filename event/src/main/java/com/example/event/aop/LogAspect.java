package com.example.event.aop;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * spring Event 打印异步监听入参切面
 *
 * @author hcq
 * @date 2020/2/3 18:32
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    /**
     * 打印入参切入点
     */
    @Pointcut("@annotation(com.example.event.annotation.ParamLog)")
    public void printLog(){}


   @Before("printLog()")
    public void printLog(JoinPoint point) {
        log.info("MethodName：【{}】，JsonParam：【{}】",point.getSignature().getName(),JSONObject.toJSONString(point.getArgs()));
    }

}
