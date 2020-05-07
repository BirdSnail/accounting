package com.birdsnail.accouting.model.persistent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * record 与 tag 之间的映射关系。避免重复的存储。
 *  1. 一条record拥有多个tag。
 *  2. 所有的tag专门存储在一张表中。
 *  3. 根据一个record id可以映射出多个 tag id，然后根据tag id在tag表中查询实际的tag内容。
 *
 * @author BirdSnail
 * @date 2020/5/7
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordTagMapping {
    private Long id;
    private Long recordId;
    private Long tagId;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
