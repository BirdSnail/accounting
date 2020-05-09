package com.birdsnail.accouting.dao.mapper;

import com.birdsnail.accouting.model.persistent.RecordPersistent;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * record mapper
 *
 * @author BirdSnail
 */
@Mapper
public interface RecordMapper {


    /**
     * insert record to accounting_record
     *
     * @param record {@link RecordPersistent}
     */
    @Insert("insert into accounting_record(user_id, amount, note, category, status)"
            + "values(#{userId}, #{amount}, #{note}, #{category}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertRecord(RecordPersistent record);

    /**
     * 通过 id 查询record
     *
     * @param recordId record id
     * @return {@link RecordPersistent}
     */
    @Select("select id, status, user_id, category, amount, note from accounting_record where id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "amount", property = "amount"),
            @Result(column = "status", property = "status"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "category", property = "category"),
            @Result(column = "note", property = "note"),
            @Result(column = "id", property = "tagList", javaType = List.class,
                    many = @Many(select = "com.birdsnail.accouting.dao.mapper." +
                            "RecordTagMappingMapper.getTagListByRecordId"))})
    RecordPersistent getRecordById(@Param("id") Long recordId);
}
