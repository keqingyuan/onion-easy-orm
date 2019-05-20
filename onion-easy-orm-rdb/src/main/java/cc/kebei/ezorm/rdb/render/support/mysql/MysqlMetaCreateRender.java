package cc.kebei.ezorm.rdb.render.support.mysql;

import cc.kebei.ezorm.rdb.render.support.simple.CommonCreateIndexRender;
import cc.kebei.ezorm.rdb.render.support.simple.SimpleSQL;
import cc.kebei.ezorm.rdb.executor.BindSQL;
import cc.kebei.ezorm.rdb.meta.RDBColumnMetaData;
import cc.kebei.utils.StringUtils;
import cc.kebei.ezorm.rdb.executor.SQL;
import cc.kebei.ezorm.rdb.meta.RDBTableMetaData;
import cc.kebei.ezorm.rdb.render.SqlAppender;
import cc.kebei.ezorm.rdb.render.SqlRender;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MysqlMetaCreateRender implements SqlRender {

    private String engine = "InnoDB";

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getEngine() {
        return engine;
    }

    public MysqlMetaCreateRender() {
    }

    public MysqlMetaCreateRender(String engine) {
        this.engine = engine;
    }

    @Override
    public SQL render(RDBTableMetaData metaData, Object param) {
        SqlAppender appender = new SqlAppender();
        Set<RDBColumnMetaData> columns = metaData.getColumns();
        if (columns.isEmpty()) {
            throw new UnsupportedOperationException("未指定任何字段");
        }
        appender.add("\nCREATE TABLE ", metaData.getFullName(), "(");
        columns.forEach(column -> {
            appender.add("\n\t`", column.getName(), "` ");
            if (column.getColumnDefinition() != null) {
                appender.add(column.getColumnDefinition());
            } else {
                appender.add(column.getDataType());
                if (column.isNotNull()) {
                    appender.add(" not null");
                }
                if (column.isPrimaryKey()) {
                    appender.add(" primary key");
                }
                //注释
                if (!StringUtils.isNullOrEmpty(column.getComment())) {
                    appender.add(String.format(" comment '%s'", column.getComment()));
                }
            }
            appender.add(",");
        });
        appender.removeLast();
        appender.add("\n)ENGINE = " + getEngine() + " CHARACTER SET utf8 ");
        if (metaData.getComment() != null) {
            appender.add("COMMENT=", "'", metaData.getComment(), "'");
        }
        List<BindSQL> bindSQLS = CommonCreateIndexRender.buildCreateIndexSql(metaData)
                .stream().map(BindSQL::new)
                .collect(Collectors.toList());
        SimpleSQL simpleSQL = new SimpleSQL(appender.toString(), param);
        simpleSQL.setBindSQLs(bindSQLS);
        return simpleSQL;
    }
}
