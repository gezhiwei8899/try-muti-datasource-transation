package com.shihang.usermerge.usermergeconsmerinsertold.handler;

import com.shihang.usermerge.usermergeconsmerinsertold.config.datasource.DBConfigValue;
import com.shihang.usermerge.usermergeconsmerinsertold.help.CustomerPointWrapper;
import com.shihang.usermerge.usermergeconsmerinsertold.mapper.PointMapper;
import com.shihang.usermerge.usermergeconsmerinsertold.model.dto.UserPointDTO;
import com.shihang.usermerge.usermergeconsmerinsertold.transation.GlobalTransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

import static com.shihang.usermerge.usermergeconsmerinsertold.config.datasource.DataSourceConfiguration.dynamic_database_key;

/**
 * @author jiangzhuangnai
 * @date 2021/3/23
 * @since 1.0.0
 **/
@Service
public class UserPointService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPointService.class);

    private final PlatformTransactionManager transactionManager;

    private final PointMapper mapper;

    public UserPointService(PlatformTransactionManager transactionManager, PointMapper mapper) {
        this.transactionManager = transactionManager;
        this.mapper = mapper;
    }

    public List<UserPointDTO> findUserPointByGuids(List<String> guids) {
        // 新库处理
        dynamic_database_key.set(DBConfigValue.DbModule.MEMBER_CENTER);
        final List<UserPointDTO> userPointDTOS = mapper.findUserPoint(guids);
        dynamic_database_key.remove();
        return userPointDTOS;
    }

    public void handlerNew(UserPointDTO mainUserPointDTO, List<UserPointDTO> userPointDTOS, GlobalTransactionManager globalTransactionManager) {
        try {
            CustomerPointWrapper customerPointWrapper = new CustomerPointWrapper(mapper);
            dynamic_database_key.set(DBConfigValue.DbModule.MEMBER_CENTER);
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            globalTransactionManager.cache(status);
            for (UserPointDTO userPointDTO : userPointDTOS) {
                // remove other city points
                customerPointWrapper.delPoints(userPointDTO, userPointDTO.getPoints());
                // mainCity add point
                customerPointWrapper.addPoints(mainUserPointDTO, userPointDTO.getPoints());
            }

            dynamic_database_key.remove();
        } catch (Exception e) {
            globalTransactionManager.setRollback(true);
        }
    }

    public void handlerOld(UserPointDTO mainPointDTO, List<UserPointDTO> cityPoints, GlobalTransactionManager globalTransactionManager) {
        try {
            CustomerPointWrapper customerPointWrapper = new CustomerPointWrapper(mapper);
            for (UserPointDTO cityPoint : cityPoints) {
                dynamic_database_key.set(DBConfigValue.DbModule.of(cityPoint.getCityFlag()));
                globalTransactionManager.cache(transactionManager.getTransaction(new DefaultTransactionDefinition()));
                customerPointWrapper.delOldPoints(cityPoint, cityPoint.getPoints(), cityPoint.getCityFlag());
                dynamic_database_key.remove();

                dynamic_database_key.set(DBConfigValue.DbModule.of(mainPointDTO.getCityFlag()));
                globalTransactionManager.cache(transactionManager.getTransaction(new DefaultTransactionDefinition()));
                customerPointWrapper.addOldPoints(mainPointDTO, cityPoint.getPoints(), mainPointDTO.getCityFlag());
                dynamic_database_key.remove();
            }

        } catch (Exception e) {
            globalTransactionManager.setRollback(true);
        }
    }
}
