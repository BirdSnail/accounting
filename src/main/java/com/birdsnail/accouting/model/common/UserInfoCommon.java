package com.birdsnail.accouting.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 供manager层使用的数据模型，只关心业务处理需要的字段
 *
 * @author BirdSnail
 * @date 2020/3/15
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class UserInfoCommon {

    private long userId;
    private String name;
    private String salt;
    private String passWord;

}
