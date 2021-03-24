package com.shihang.usermerge.usermergeconsmerinsertold.config.datasource;


import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    DBConfigValue.DbModule value();
}
