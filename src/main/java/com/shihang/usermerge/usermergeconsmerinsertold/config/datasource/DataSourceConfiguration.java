package com.shihang.usermerge.usermergeconsmerinsertold.config.datasource;


import com.alibaba.ttl.TransmittableThreadLocal;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 *
 */
@Configuration
public class DataSourceConfiguration {


    public static final ThreadLocal<DBConfigValue.DbModule> dynamic_database_key = new TransmittableThreadLocal<>();

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);
    private static final String MAPPER_LOCATION = "classpath*:mybatis/*.xml";

    private final DBConfigValue dbConfigValue;

    private static final Map<String, List<String>> DynamicDatabaseBalance = new HashMap<>();

    public DataSourceConfiguration(DBConfigValue dbConfigValue) {
        this.dbConfigValue = dbConfigValue;
    }

    @Bean("dynamicRoutingDataSource")
    public DynamicRoutingDataSource dynamicRoutingDataSource() {

        log.error("dbConfig: {}", dbConfigValue);


        if (Objects.isNull(dbConfigValue) || Objects.isNull(dbConfigValue.getDatabase())) {
            throw new IllegalArgumentException("database config is empty.");
        }

        DynamicRoutingDataSource routingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> all_target_datasource = new HashMap<>();

        dbConfigValue.getDatabase()
                .stream()
                .filter(it -> DBConfigValue.DbModule.validCheck(it.getModule()))
                .peek(config -> config.setTypedModule(DBConfigValue.DbModule.valueOf(config.getModule())))
                .forEach(config -> {

                    AtomicInteger index = new AtomicInteger();
                    String key = gte_datasource_key(config.getTypedModule());
                    DynamicDatabaseBalance.put(key, new ArrayList<>());

                    Arrays.stream(config.getHost().split(","))
                            .forEach(host -> {
                                String url = MessageFormat.format(
                                        config.mysql == 1 ? DBConfigValue.MerchantDbConfig.jdbcTemplate : DBConfigValue.MerchantDbConfig.odbcTemplate,
                                        host,
                                        String.valueOf(config.getPort()),
                                        config.getSchema());

                                log.info("init. {}", url);

                                HikariConfig hikariConfig = new HikariConfig();
                                hikariConfig.setJdbcUrl(url); //数据源
                                hikariConfig.setMaximumPoolSize(10);
                                hikariConfig.setUsername(config.getUsername()); //用户名
                                hikariConfig.setPassword(config.getPassword()); //密码
                                hikariConfig.setDriverClassName(config.mysql == 1 ? DBConfigValue.MerchantDbConfig.jdbcDriver : DBConfigValue.MerchantDbConfig.odbcDriver);
                                hikariConfig.addDataSourceProperty("cachePrepStmts", "true"); //是否自定义配置，为true时下面两个参数才生效
                                hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250"); //连接池大小默认25，官方推荐250-500
                                hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048"); //单条语句最大长度默认256，官方推荐2048
                                hikariConfig.addDataSourceProperty("useServerPrepStmts", "true"); //新版本MySQL支持服务器端准备，开启能够得到显著性能提升
                                hikariConfig.addDataSourceProperty("useLocalTransactionState", "true");
                                hikariConfig.addDataSourceProperty("rewriteBatchedStatements", "true");
                                hikariConfig.addDataSourceProperty("cacheResultSetMetadata", "true");
                                hikariConfig.addDataSourceProperty("cacheServerConfiguration", "true");
                                hikariConfig.addDataSourceProperty("elideSetAutoCommits", "true");
                                hikariConfig.addDataSourceProperty("maintainTimeStats", "false");
                                HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

                                String lookup = key + ":" + index.getAndIncrement();
                                DynamicDatabaseBalance.get(key).add(lookup);
                                all_target_datasource.put(lookup, hikariDataSource);
                                log.info("init success. {} => {}", lookup, url);
                            });

                });

        routingDataSource.setTargetDataSources(all_target_datasource);

        return routingDataSource;
    }

    @Bean(name = "dataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dynamicRoutingDataSource") DynamicRoutingDataSource dynamicRoutingDataSource) {
        return new DataSourceTransactionManager(dynamicRoutingDataSource);
    }


    @Bean(name = "sqlSessionFactory")
    @Qualifier
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicRoutingDataSource") DynamicRoutingDataSource dynamicRoutingDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dynamicRoutingDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    public static String gte_datasource_key(DBConfigValue.DbModule dbModule) {
        return "DS:" + dbModule.name();
    }

    public static class DynamicRoutingDataSource extends AbstractRoutingDataSource {

        private static final Logger log = LoggerFactory.getLogger(DynamicRoutingDataSource.class);

        @Override
        protected Object determineCurrentLookupKey() {

            DBConfigValue.DbModule dbModule = dynamic_database_key.get();
            String key = gte_datasource_key(dbModule);

            List<String> balance = DynamicDatabaseBalance.get(key);
            String lookup = balance.get(((int) (System.currentTimeMillis() % balance.size())));

            log.debug("datasource key: {}", lookup);
            return lookup;
        }
    }

}
