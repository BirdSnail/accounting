package com.birdsnail.accouting.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author BirdSnail
 * @date 2020/4/20
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class TagCommon {

    /**
     * tag id
     */
    private long id;

    /**
     * 标签内容
     */
    private String description;

    /**
     * 创建该标签的用户id
     */
    private long userId;

    /**
     * 数据状态
     * 0 -> disable，1 -> enable
     */
    private int status;

}
