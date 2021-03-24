package com.shihang.usermerge.usermergeconsmerinsertold;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shihang.usermerge.usermergeconsmerinsertold.mapper")
public class UserMergeConsmerInsertoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserMergeConsmerInsertoldApplication.class, args);
    }

}
