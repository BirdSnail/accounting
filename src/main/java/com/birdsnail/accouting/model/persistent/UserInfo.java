package com.birdsnail.accouting.model.persistent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 持久层的数据模型与数据库中的表字段一一对应，供DAO层使用
 *
 * @author BirdSnail
 * @date 2020/3/15
 */
@Data
@Builder
@AllArgsConstructor
public class UserInfo {

    private long id;
    private String username;
    private String password;
    private String sale;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
