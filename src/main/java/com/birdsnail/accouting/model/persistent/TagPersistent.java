package com.birdsnail.accouting.model.persistent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author BirdSnail
 * @date 2020/4/13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagPersistent {

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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 标签数据更新时间
     */
    private LocalDateTime updateTime;

}
