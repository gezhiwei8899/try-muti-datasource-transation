package com.shihang.usermerge.usermergeconsmerinsertold.config.datasource;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("config.mysql")
public class DBConfigValue {


    List<MerchantDbConfig> database = new ArrayList<>();

    public enum DbModule {

        /*
        如果一个项目里有多个SCHEME，可以通过构造参数差异化
         */

        MEMBER_CENTER,

        USER_CENTER_DATA_SZ,
        USER_CENTER_DATA_WX,
        USER_CENTER_DATA_SH,
        USER_CENTER_DATA_CZ,
        ;


        DbModule() {
        }


        public static boolean validCheck(String name) {

            return Arrays.stream(values()).anyMatch(it -> it.name().equalsIgnoreCase(name));
        }

        public static DbModule of(String city) {
            switch (city) {
                case "sz":
                    return USER_CENTER_DATA_SZ;
                case "wx":
                    return USER_CENTER_DATA_WX;
                case "sh":
                    return USER_CENTER_DATA_SH;
                case "cz":
                    return USER_CENTER_DATA_CZ;

            }
            return USER_CENTER_DATA_SZ;
        }
    }

    public static class MerchantDbConfig {

        public static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        public static final String odbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        public static final String odbcTemplate = "jdbc:sqlserver://{0}:{1};databasename={2}";
        public static final String jdbcTemplate = "jdbc:mysql://{0}:{1}/{2}?serverTimezone=Asia/Shanghai&useUnicode=true&useCursorFetch=true&characterEncoding=utf-8&allowMultiQueries=true";

        int mch_id, port;
        String url, host, username, password;
        boolean base;
        String module;
        DbModule typedModule;
        int mysql;
        String schema;


        public int getMch_id() {
            return mch_id;
        }

        public void setMch_id(int mch_id) {
            this.mch_id = mch_id;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isBase() {
            return base;
        }

        public void setBase(boolean base) {
            this.base = base;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public DbModule getTypedModule() {
            return typedModule;
        }

        public void setTypedModule(DbModule typedModule) {
            this.typedModule = typedModule;
        }

        public static String getJdbcDriver() {
            return jdbcDriver;
        }

        public static String getOdbcDriver() {
            return odbcDriver;
        }

        public static String getOdbcTemplate() {
            return odbcTemplate;
        }

        public static String getJdbcTemplate() {
            return jdbcTemplate;
        }

        public int getMysql() {
            return mysql;
        }

        public void setMysql(int mysql) {
            this.mysql = mysql;
        }

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }
    }

    public List<MerchantDbConfig> getDatabase() {
        return database;
    }

    public void setDatabase(List<MerchantDbConfig> database) {
        this.database = database;
    }


}
