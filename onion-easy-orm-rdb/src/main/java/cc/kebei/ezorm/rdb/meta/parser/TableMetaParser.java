package cc.kebei.ezorm.rdb.meta.parser;

import cc.kebei.ezorm.rdb.meta.RDBTableMetaData;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Kebei on 16-6-5.
 */
public interface TableMetaParser {

    RDBTableMetaData parse(String name) throws SQLException;

    boolean tableExists(String name);

    List<RDBTableMetaData> parseAll() throws SQLException;
}
