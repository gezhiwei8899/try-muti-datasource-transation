package com.shihang.usermerge.usermergeconsmerinsertold.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author jiangzhuangnai
 * @date 2021/3/9
 * @since 1.0.0
 **/
@Component
public class SpringBootBeanUtil implements ApplicationContextAware {

    private static ApplicationContext context = null;
    private static SpringBootBeanUtil bootBeanUtil = null;

    public synchronized static SpringBootBeanUtil init() {
        if (bootBeanUtil == null) {
            bootBeanUtil = new SpringBootBeanUtil();
        }
        return bootBeanUtil;
    }

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
        System.out.println("========ApplicationContext配置成功========");

    }

    public synchronized static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

}
