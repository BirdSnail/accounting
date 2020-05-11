package com.birdsnail.accouting.dao;

import com.birdsnail.accouting.model.persistent.TagPersistent;

import java.util.List;

/**
 * record 与 tag 之间的映射表的dao层。
 *
 * @author BirdSnail
 */
public interface RecordTagMappingDao {

    /**
     * 批量插入 recordId 和 tagId
     * @param recordId record id
     * @param tagPersistentList list of tag in persistent
     */
    void batchInsert(Long recordId, List<TagPersistent> tagPersistentList);

    /**
     * 删除指定的recordId记录
     * @param recordId record id
     */
    void deleteRecordTagMappingByRecordId(Long recordId);
}
