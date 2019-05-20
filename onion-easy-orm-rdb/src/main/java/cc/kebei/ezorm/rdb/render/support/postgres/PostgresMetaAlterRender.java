package cc.kebei.ezorm.rdb.render.support.postgres;

import cc.kebei.ezorm.rdb.meta.RDBColumnMetaData;
import cc.kebei.ezorm.rdb.meta.RDBDatabaseMetaData;
import cc.kebei.ezorm.rdb.render.SqlAppender;
import cc.kebei.ezorm.rdb.render.support.simple.AbstractMetaAlterRender;

import java.util.ArrayList;
import java.util.List;


/**
 * oracle数据库表结构修改sql渲染器
 *
 * @author Kebei
 * @see 1.0
 */
public class PostgresMetaAlterRender extends AbstractMetaAlterRender {

    public PostgresMetaAlterRender(RDBDatabaseMetaData databaseMetaData) {
        super(databaseMetaData);
    }

    @Override
    protected List<SqlAppender> buildAdd(RDBColumnMetaData column) {
        SqlAppender alter = new SqlAppender();
        SqlAppender comments = new SqlAppender();
        List<SqlAppender> all = new ArrayList<>();
        String columnFullName = column.getTableMetaData().getDatabaseMetaData().getDialect().buildColumnName(null, column.getName());

        alter.add("ALTER TABLE ",
                column.getTableMetaData().getFullName(),
                " ADD ",
                columnFullName,
                " ");
        if (column.getColumnDefinition() != null) {
            alter.add(column.getColumnDefinition());
        } else {
            alter.add(column.getDataType());
            if (column.isNotNull() || column.isPrimaryKey()) {
                alter.add(" NOT NULL");
            }
            if (column.getComment() != null) {
                comments.add(String.format("COMMENT ON COLUMN %s.%s is '%s'", column.getTableMetaData().getFullName(), columnFullName, column.getComment()));
            }
        }
        all.add(alter);
        all.add(comments);
        return all;
    }

    @Override
    protected List<SqlAppender> buildAlter(RDBColumnMetaData column) {
        SqlAppender alter = new SqlAppender();
        SqlAppender comments = new SqlAppender();
        List<SqlAppender> all = new ArrayList<>();
        String columnFullName = column.getTableMetaData().getDatabaseMetaData().getDialect().buildColumnName(null, column.getName());

        alter.add("ALTER TABLE ",
                column.getTableMetaData().getName(),
                " MODIFY ",
                columnFullName,
                " ");
        if (column.getColumnDefinition() != null) {
            alter.add(column.getColumnDefinition());
        } else {
            alter.add(column.getDataType());
            if (column.isNotNull() || column.isPrimaryKey()) {
                alter.add(" NOT NULL");
            }
            if (column.getComment() != null) {
                comments.add(String.format("COMMENT ON COLUMN %s.%s is '%s'", column.getTableMetaData().getFullName(),columnFullName, column.getComment()));
            }
        }
        all.add(alter);
        all.add(comments);
        return all;
    }

}
