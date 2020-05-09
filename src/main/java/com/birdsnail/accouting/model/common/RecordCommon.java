package com.birdsnail.accouting.model.common;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BirdSnail
 * @date 2020/5/7
 */
@Data
@Builder
public class RecordCommon {
    /**
     * record id
     */
    private Long id;

    /**
     * 数据状态：DISABLE/ENABLE
     */
    private String status;

    /**
     * 所属用户的用户id
     */
    private Long userId;

    /**
     * 类别 ： 收入 or 支出
     */
    private String category;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 包含的标签
     */
    private List<TagCommon> tagList;

}
