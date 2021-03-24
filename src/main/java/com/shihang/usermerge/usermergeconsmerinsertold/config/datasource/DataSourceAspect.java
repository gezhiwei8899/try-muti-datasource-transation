package com.shihang.usermerge.usermergeconsmerinsertold.config.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Order(-1)
@Component
public class DataSourceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);

//    @Before("mapper()")
//    public void enable_db_config(JoinPoint joinPoint) {
//
//        DataSource annotation = (DataSource) joinPoint.getSignature().getDeclaringType().getAnnotation(DataSource.class);
//        DataSource methodDataSource = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(DataSource.class);
////        LOGGER.info("{}", annotation);
////        LOGGER.info("{}", methodDataSource);
//
//        DBConfigValue.DbModule dbModule = Optional.ofNullable(methodDataSource).orElse(annotation).value();
//
//        dynamic_database_key.set(dbModule);
//    }
//
//    @After("mapper()")
//    public void reset_db_config() {
//        dynamic_database_key.remove();
//    }
//
//
//    @Pointcut("execution(public * com.shihang.productcenter.queue.common.mapper..*.*(..)) "
//            + "|| execution(public * com.shihang.productcenter.queue.product.mapper..*.*(..))"
//            + "|| execution(public * com.shihang.productcenter.queue.cutoff.mapper..*.*(..))"
//            + "|| execution(public * com.shihang.productcenter.queue.inventory.mapper..*.*(..))" )
//    public void mapper() {
//    }
}
