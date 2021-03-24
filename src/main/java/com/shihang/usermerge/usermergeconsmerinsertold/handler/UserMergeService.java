package com.shihang.usermerge.usermergeconsmerinsertold.handler;

import com.shihang.user.merge.provider.CustomerDTO;
import com.shihang.user.merge.provider.CustomerStructDTO;
import com.shihang.usermerge.usermergeconsmerinsertold.api.IReportService;
import com.shihang.usermerge.usermergeconsmerinsertold.mapper.PointMapper;
import com.shihang.usermerge.usermergeconsmerinsertold.model.dto.UserPointDTO;
import com.shihang.usermerge.usermergeconsmerinsertold.transation.GlobalTransactionManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.shihang.usermerge.usermergeconsmerinsertold.config.TransationLogContant.*;
import static java.util.Objects.isNull;

/**
 * @author jiangzhuangnai
 * @date 2021/3/23
 * @since 1.0.0
 **/
@Service
public class UserMergeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMergeService.class);

    private final IReportService reportService;

    private final PointTransactionLogService pointTransactionLogService;

    private final PointMapper mapper;

    private final UserPointService userPointService;

    public UserMergeService(IReportService reportService, PointTransactionLogService pointTransactionLogService, PointMapper mapper, UserPointService userPointService) {
        this.reportService = reportService;
        this.pointTransactionLogService = pointTransactionLogService;
        this.mapper = mapper;
        this.userPointService = userPointService;
    }

    public void merge(CustomerStructDTO customerStructDTO) {
        // 数据库幂等校验就可以了
        int logStatus = pointTransactionLogService.log(customerStructDTO);
        switch (logStatus) {
            case Ready:
                doMerge(customerStructDTO);
                break;
            case Processing:
            case Finish:
                LOGGER.warn("user: {} merge status: {} ", customerStructDTO.getPhone(), logStatus);
        }
        reportService.success(customerStructDTO);
    }

    private void doMerge(CustomerStructDTO customerStructDTO) {
        final String mainGuid = customerStructDTO.getCustomerGuid();

        final List<String> combineGuids = customerStructDTO.getCombinedList().stream().map(CustomerDTO::getCustomerGuid).collect(Collectors.toList());

        boolean rollback = false;

        List<String> userGuids = new ArrayList<>(combineGuids);
        userGuids.add(mainGuid);

        // 新库处理
        final List<UserPointDTO> userPointDTOS = userPointService.findUserPointByGuids(userGuids);

        if (hasPoint(mainGuid, userPointDTOS)) {
            UserPointDTO mainPointDTO = userPointDTOS.stream().filter(u -> StringUtils.equals(u.getCustomerGuid(), mainGuid)).findFirst().orElse(null);
            if (isNull(mainPointDTO)) {
                LOGGER.error("用户主账号积分不存在：{}", mainGuid);
                // record failed
                return;
            }

            List<UserPointDTO> cityPoints = userPointDTOS.stream().filter(u -> !StringUtils.equals(u.getCustomerGuid(), mainGuid)).collect(Collectors.toList());
            GlobalTransactionManager globalTransactionManager = new GlobalTransactionManager();
            userPointService.handlerNew(mainPointDTO, cityPoints, globalTransactionManager);
            userPointService.handlerOld(mainPointDTO, cityPoints, globalTransactionManager);

            if (globalTransactionManager.isRollback()) {
                pointTransactionLogService.failed(customerStructDTO);
                globalTransactionManager.startRollback();
            }
            globalTransactionManager.commit();
        }
    }

    private boolean hasPoint(String mainGuid, List<UserPointDTO> userPointDTOS) {
        return userPointDTOS.stream().anyMatch(u -> !StringUtils.equals(mainGuid, u.getCustomerGuid()) && u.getPoints() > 0);
    }

}
