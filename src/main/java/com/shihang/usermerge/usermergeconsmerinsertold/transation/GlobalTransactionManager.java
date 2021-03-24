package com.shihang.usermerge.usermergeconsmerinsertold.transation;

import com.shihang.usermerge.usermergeconsmerinsertold.util.SpringBootBeanUtil;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangzhuangnai
 * @date 2021/3/24
 * @since 1.0.0
 **/
public class GlobalTransactionManager {

    private volatile List<TransactionStatus> transactionStatuses = new ArrayList<>();

    private volatile boolean rollback = false;

    public void cache(TransactionStatus status) {
        transactionStatuses.add(status);
    }

    public boolean isRollback() {
        return rollback;
    }

    public void setRollback(boolean rollback) {
        this.rollback = rollback;
    }

    public void startRollback() {
        PlatformTransactionManager platformTransactionManager = SpringBootBeanUtil.getBean(PlatformTransactionManager.class);
        for (TransactionStatus transactionStatus : transactionStatuses) {
            platformTransactionManager.rollback(transactionStatus);
        }
    }

    public void commit() {
        PlatformTransactionManager platformTransactionManager = SpringBootBeanUtil.getBean(PlatformTransactionManager.class);
        for (TransactionStatus transactionStatus : transactionStatuses) {
            platformTransactionManager.commit(transactionStatus);
        }
    }
}
