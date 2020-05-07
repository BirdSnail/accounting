package com.birdsnail.accouting.model.service;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author BirdSnail
 * @date 2020/4/20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagView {

    /**
     * tag id
     */
    private Long id;

    /**
     * 标签内容
     */
    private String description;

    /**
     * 创建该标签的用户id
     */
    private Long userId;

    /**
     * 数据状态
     */
    private String status;
}
