package com.birdsnail.accouting.dao.provider;


import com.birdsnail.accouting.model.persistent.TagPersistent;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author BirdSnail
 * @date 2020/4/21
 */
@Slf4j
public class TagSqlProvider {

    public String updateTag(TagPersistent tagPersistent) {
        final String update_sql = new SQL() {
            {
                UPDATE("accounting_tag");
                if (tagPersistent.getDescription() != null) {
                    SET("description = #{description}");
                }

                SET("status = #{status}");
                WHERE("id = #{id}");
            }
        }.toString();
        log.info("tag update sql:{}", update_sql);
        return update_sql;
    }

}
