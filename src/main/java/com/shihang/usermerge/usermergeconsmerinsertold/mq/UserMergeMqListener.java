package com.shihang.usermerge.usermergeconsmerinsertold.mq;

import com.alibaba.fastjson.JSONObject;
import com.shihang.user.merge.provider.CustomerStructDTO;
import com.shihang.usermerge.usermergeconsmerinsertold.handler.UserMergeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author jiangzhuangnai
 * @date 2021/3/13
 * @since 1.0.0
 **/
@Component
public class UserMergeMqListener {

    private static final Logger log = LoggerFactory.getLogger(UserMergeMqListener.class);

    private final UserMergeService userMergeService;

    public UserMergeMqListener(UserMergeService userMergeService) {
        this.userMergeService = userMergeService;
    }

//    @RabbitListener(queues = "${user.merge.queue:com.shihang.point.queue.user-merge-combine}", containerFactory = "tmpContainerFactory")
    public void onMessage(byte[] body) {
        final String combineStr = new String(body, StandardCharsets.UTF_8);
        log.warn("接收到重试合并消息[{}]", combineStr);
        if (isNull(body) || body.length == 0) {
            log.warn("接收消息为空，跳过");
            return;
        }

        log.warn("接收到合并消息[{}]", combineStr);

        CustomerStructDTO customerStructDTO = null;

        try {
            customerStructDTO = JSONObject.parseObject(combineStr, CustomerStructDTO.class);
        } catch (Exception exception) {
            log.error("序列化异常,[{}]", combineStr, exception);
        }

        if (nonNull(customerStructDTO)) {
            log.warn("接收到合并消息[{}]，转换后对象不合法，跳过", combineStr);
            return;
        }
        Throwable cause = null;
        int count = 3;
        while (count >= 1) {
            try {
                userMergeService.merge(customerStructDTO);
                cause = null;
                break;
            } catch (Exception exception) {
                cause = exception;
                count--;
            }
        }

//        if (nonNull(cause)) {
//            log.warn("消息处理失败，[{}]", combineStr);
//            if (cause instanceof UniqueException) {
//                try {
//                    log.error("记录合并信息失败,重新回传MQ[{}]", combineStr, cause);
//                    rabbitTemplate.send(retryExchange, "retry", new Message(body, new MessageProperties()));
//                } catch (Exception rabbitException) {
//                    log.error("重试消息推送失败，[{}]", combineStr);
//                }
//            }
//
//        }
    }

    ;

}
