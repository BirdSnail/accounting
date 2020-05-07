package com.birdsnail.accouting.model.persistent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 一条账单记录
 * @author BirdSnail
 * @date 2020/4/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordPersistent {

    /**
     * record id
     */
    private Long id;

    /**
     * 数据状态。0 -> disable, 1 -> enable
     */
    private Integer status;

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
    private List<TagPersistent> tagList;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后一次修改时间
     */
    private LocalDateTime lastUpdateTime;

}
