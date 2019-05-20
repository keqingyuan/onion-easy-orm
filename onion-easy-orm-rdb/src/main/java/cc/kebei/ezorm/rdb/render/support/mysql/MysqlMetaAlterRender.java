package cc.kebei.ezorm.rdb.render.support.mysql;

import cc.kebei.ezorm.rdb.render.support.simple.AbstractMetaAlterRender;
import cc.kebei.ezorm.rdb.meta.RDBDatabaseMetaData;


public class MysqlMetaAlterRender extends AbstractMetaAlterRender {

    public MysqlMetaAlterRender(RDBDatabaseMetaData databaseMetaData) {
        super(databaseMetaData);
    }

}
