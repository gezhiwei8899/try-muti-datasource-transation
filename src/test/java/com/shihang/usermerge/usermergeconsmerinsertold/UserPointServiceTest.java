package com.shihang.usermerge.usermergeconsmerinsertold;

import com.alibaba.fastjson.JSONObject;
import com.shihang.usermerge.usermergeconsmerinsertold.handler.UserPointService;
import com.shihang.usermerge.usermergeconsmerinsertold.model.dto.UserPointDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangzhuangnai
 * @date 2021/3/23
 * @since 1.0.0
 **/
@SpringBootTest
public class UserPointServiceTest {

    @Autowired
    UserPointService userPointService;


    @Test
    void test() {
        List<String> guids = new ArrayList<>();
        guids.add("4a59fb5e-7268-4d0f-8a7b-7e5c7ed8ffec");
        List<UserPointDTO> userPointDTOS = userPointService.findUserPointByGuids(guids);
        System.out.println(JSONObject.toJSONString(userPointDTOS));
    }
}
