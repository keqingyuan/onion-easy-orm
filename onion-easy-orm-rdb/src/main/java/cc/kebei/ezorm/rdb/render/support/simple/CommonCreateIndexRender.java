package cc.kebei.ezorm.rdb.render.support.simple;


import cc.kebei.ezorm.rdb.executor.SQL;
import cc.kebei.ezorm.rdb.meta.IndexMetaData;
import cc.kebei.ezorm.rdb.meta.RDBTableMetaData;
import cc.kebei.ezorm.rdb.render.SqlAppender;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kebei
 * @since 1.0.0
 */
public class CommonCreateIndexRender {

    public static List<SQL> buildCreateIndexSql(RDBTableMetaData table) {
        return table.getIndexes()
                .stream()
                .map(index -> buildIndex(table.getFullName(), index, table))
                .collect(Collectors.toList());
    }

    public static SQL buildIndex(String table, IndexMetaData index, RDBTableMetaData tableMeta) {
        SqlAppender appender = new SqlAppender();
        appender.addSpc("create",
                index.isUnique() ? "unique" : "",
                "index",
                index.getIndexName(),
                "on", table, "("
        );

        for (IndexMetaData.IndexColumn indexColumn : index.getColumnName()) {
            appender.add(tableMeta.getDatabaseMetaData()
                            .getDialect()
                            .buildColumnName(null, indexColumn.getColumn()),
                    " ",
                    (indexColumn.getSort() == null ? "" : indexColumn.getSort()));
            appender.add(",");
        }
        appender.removeLast();
        appender.add(")");

        return new SimpleSQL(appender.toString());

    }

}
