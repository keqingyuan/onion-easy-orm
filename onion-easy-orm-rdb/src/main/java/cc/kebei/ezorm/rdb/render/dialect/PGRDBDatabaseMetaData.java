package cc.kebei.ezorm.rdb.render.dialect;

import cc.kebei.ezorm.rdb.render.support.postgres.PostgresMetaAlterRender;
import cc.kebei.ezorm.rdb.render.support.postgres.PostgresMetaCreateRender;
import cc.kebei.ezorm.rdb.render.SqlRender;

/**
 * @author Kebei
 * @since 3.0
 */
public class PGRDBDatabaseMetaData extends AbstractRDBDatabaseMetaData {
    private static final String DEFAULT_NAME = "postgres";

    private String name;

    public PGRDBDatabaseMetaData() {
        super(Dialect.POSTGRES);
        name = DEFAULT_NAME;
        init();
    }

    @Override
    public void init() {
        super.init();
        putRenderer(SqlRender.TYPE.META_CREATE, new PostgresMetaCreateRender());
        putRenderer(SqlRender.TYPE.META_ALTER, new PostgresMetaAlterRender(this));
    }

    @Override
    public String getName() {
        return name;
    }
}
