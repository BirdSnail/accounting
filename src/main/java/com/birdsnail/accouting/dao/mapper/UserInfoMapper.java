package com.birdsnail.accouting.dao.mapper;


import com.birdsnail.accouting.model.common.UserInfoCommon;
import com.birdsnail.accouting.model.persistent.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author BirdSnail
 * @date 2020/3/16
 */
@Mapper
public interface UserInfoMapper {


    /**
     * get user object by username
     * @param username username
     * @return {@link UserInfo} object
     */
    @Select("select id, username, password, sale, create_time, update_time from hcas_userinfo where username =#{username}")
    UserInfo getUserInfoByUsername(@Param("username") String username);

    /**
     * create new user
     * @param userInfo user
     */
    @Insert("INSERT INTO hcas_userinfo(username, password, sale, create_time) VALUES(#{name}, #{passWord}, #{sale}, #{createTime})")
    void createNewUser(UserInfo userInfo);
}
