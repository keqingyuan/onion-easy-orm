package cc.kebei.ezorm.rdb.render.dialect;

import cc.kebei.ezorm.rdb.render.support.sqlserver.SqlServerDeleteSqlRender;
import cc.kebei.ezorm.rdb.render.support.sqlserver.SqlServerMetaAlterRender;
import cc.kebei.ezorm.rdb.render.support.sqlserver.SqlServerMetaCreateRender;
import cc.kebei.ezorm.rdb.render.support.sqlserver.SqlServerSelectSqlRender;
import cc.kebei.ezorm.rdb.render.SqlRender;

public class MSSQLRDBDatabaseMetaData extends AbstractRDBDatabaseMetaData {
    private static final String DEFAULT_NAME = "mssql";

    private String name;

    @Override
    public void init() {
        super.init();
        renderMap.put(SqlRender.TYPE.META_CREATE, new SqlServerMetaCreateRender());
        renderMap.put(SqlRender.TYPE.META_ALTER, new SqlServerMetaAlterRender());
        renderMap.put(SqlRender.TYPE.SELECT, new SqlServerSelectSqlRender(getDialect()));
        renderMap.put(SqlRender.TYPE.DELETE, new SqlServerDeleteSqlRender(getDialect()));
    }

    public MSSQLRDBDatabaseMetaData() {
        super(Dialect.MSSQL);
        name = DEFAULT_NAME;
        init();
    }

    @Override
    public String getName() {
        return name;
    }
}
