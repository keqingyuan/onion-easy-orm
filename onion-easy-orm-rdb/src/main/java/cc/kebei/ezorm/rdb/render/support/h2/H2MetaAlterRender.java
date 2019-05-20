package cc.kebei.ezorm.rdb.render.support.h2;

import cc.kebei.ezorm.rdb.render.support.simple.AbstractMetaAlterRender;
import cc.kebei.ezorm.rdb.meta.RDBDatabaseMetaData;

public class H2MetaAlterRender extends AbstractMetaAlterRender {
    public H2MetaAlterRender(RDBDatabaseMetaData databaseMetaData) {
        super(databaseMetaData);
    }
}
