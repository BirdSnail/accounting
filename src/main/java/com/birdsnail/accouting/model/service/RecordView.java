package com.birdsnail.accouting.model.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BirdSnail
 * @date 2020/5/7
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecordView {

    /**
     * record id
     */
    private Long id;

     /**
     * 所属用户的用户id
     */
    private Long userId;

    /**
     * 类别 ： 收入 or 支出
     */
    private Integer category;

    /**
     * 收入/支出 金额
     */
    private BigDecimal amount;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 包含的标签
     */
    private List<TagView> tagList;
}
