package com.birdsnail.accouting.dao.mapper;


import com.birdsnail.accouting.model.persistent.RecordTagMapping;
import com.birdsnail.accouting.model.persistent.TagPersistent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * RecordTagMapping mapper
 *
 * @author BirdSnail
 */
@Mapper
public interface RecordTagMappingMapper {

    /**
     * batch insert to accounting_record_tag_mapping
     *
     * @param recordTagMappings list of {@link RecordTagMapping}
     * @return 更改的行数
     */
    @Insert({
            "<script>",
            "insert into accounting_record_tag_mapping (record_id, tag_id, status)",
            "values ",
            "<foreach  collection='recordTagMappings' item='item' index='index' separator=','>",
            "(#{item.recordId}, #{item.tagId}, #{item.status})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("recordTagMappings") List<RecordTagMapping> recordTagMappings);


    /**
     * 获取tag的集合
     *
     * @param recordId record id
     * @return {@link TagPersistent}
     */
    @Select("SELECT tag.id, tag.description, tag.status, tag.user_id FROM accounting_tag tag"
            + "JOIN accounting_record_tag_mapping record_tag_map"
            + "ON tag.id = record_tag_map.tag_id WHERE record_tag_map.record_id = #{recordId}")
    List<TagPersistent> getTagListByRecordId(@Param("recordId") Long recordId);
}
