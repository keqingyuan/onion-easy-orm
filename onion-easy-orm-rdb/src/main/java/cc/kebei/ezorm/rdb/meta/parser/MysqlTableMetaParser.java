package cc.kebei.ezorm.rdb.meta.parser;

import cc.kebei.ezorm.rdb.render.dialect.Dialect;
import cc.kebei.ezorm.rdb.executor.SqlExecutor;

import java.sql.JDBCType;

/**
 * @author Kebei
 */
public class MysqlTableMetaParser extends AbstractTableMetaParser {
    static final String TABLE_META_SQL = " select " +
            "column_name as `name`, " +
            "data_type as `data_type`, " +
            "character_maximum_length as `data_length`, " +
            "numeric_precision as `data_precision`, " +
            "numeric_scale as `data_scale`, " +
            "column_comment as `comment`, " +
            "case when is_nullable='YES' then 0 else 1 end as 'not-null' " +
            "from information_schema.columns where table_schema=%s and table_name=#{table}";

    static final String TABLE_COMMENT_SQL = " select " +
            "table_comment as `comment` " +
            "from information_schema.tables where table_schema=%s and table_name=#{table}";

    static final String ALL_TABLE_SQL = "select table_name as `name` from information_schema.`TABLES` where table_schema=%s";

    static final String TABLE_EXISTS_SQL = "select count(1) as 'total' from information_schema.`TABLES` where table_schema=%s and table_name=#{table}";

    public MysqlTableMetaParser(SqlExecutor sqlExecutor) {
        super(sqlExecutor);
        jdbcTypeMap.put("int", JDBCType.INTEGER);
        jdbcTypeMap.put("year", JDBCType.TIME);
        jdbcTypeMap.put("datetime", JDBCType.TIMESTAMP);
        jdbcTypeMap.put("text", JDBCType.CLOB);
    }

    protected String getRealDatabaseName() {
        String db = getDatabaseName();
        if (db == null) {
            return "database()";
        }
        return "'" + db + "'";
    }

    @Override
    Dialect getDialect() {
        return Dialect.MYSQL;
    }

    @Override
    String getTableMetaSql(String tname) {
        return String.format(TABLE_META_SQL, getRealDatabaseName());
    }

    @Override
    String getTableCommentSql(String tname) {
        return String.format(TABLE_COMMENT_SQL, getRealDatabaseName());
    }

    @Override
    String getAllTableSql() {
        return String.format(ALL_TABLE_SQL, getRealDatabaseName());
    }

    @Override
    String getTableExistsSql() {
        return String.format(TABLE_EXISTS_SQL, getRealDatabaseName());
    }
}
