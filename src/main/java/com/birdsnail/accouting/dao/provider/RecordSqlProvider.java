package com.birdsnail.accouting.dao.provider;

import com.birdsnail.accouting.model.persistent.RecordPersistent;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author BirdSnail
 * @date 2020/5/9
 */
public class RecordSqlProvider {

    public String updateRecord(RecordPersistent recordPersistent) {
        return new SQL() {
            {
                UPDATE("accounting_record");
                if (recordPersistent.getAmount() != null) {
                    SET("amount = #{amount}");
                }
                if (recordPersistent.getCategory() != null) {
                    SET("category = #{category}");
                }
                if (recordPersistent.getNote() != null) {
                    SET("note = #{note}");
                }
                if (recordPersistent.getStatus() != null) {
                    SET("status = #{status}");
                }
                if (recordPersistent.getUserId() != null) {
                    SET("user_id = #{userId}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }

}
