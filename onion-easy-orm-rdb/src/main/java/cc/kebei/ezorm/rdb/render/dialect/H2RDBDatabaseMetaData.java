package cc.kebei.ezorm.rdb.render.dialect;

import cc.kebei.ezorm.rdb.render.support.h2.H2MetaAlterRender;
import cc.kebei.ezorm.rdb.render.support.oracle.OracleMetaCreateRender;
import cc.kebei.ezorm.rdb.render.SqlRender;

public class H2RDBDatabaseMetaData extends AbstractRDBDatabaseMetaData {
    private static final String DEFAULT_NAME = "h2";

    private String name;

    public H2RDBDatabaseMetaData() {
        super(Dialect.H2);
        name = DEFAULT_NAME;
        init();
    }

    @Override
    public void init() {
        super.init();
        putRenderer(SqlRender.TYPE.META_CREATE, new OracleMetaCreateRender());
        putRenderer(SqlRender.TYPE.META_ALTER, new H2MetaAlterRender(this));
    }

    @Override
    public String getName() {
        return name;
    }
}
