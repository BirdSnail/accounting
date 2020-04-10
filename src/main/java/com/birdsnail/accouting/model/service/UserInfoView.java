package com.birdsnail.accouting.model.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 这一层的数据模型返回给前端，进一步的剔除不需要或者敏感的字段。
 * 只保留可以展示的字段。
 *
 * @author BirdSnail
 * @date 2020/3/15
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class UserInfoView {

    private long userId;
    private String username;
    private String passWord;

}
