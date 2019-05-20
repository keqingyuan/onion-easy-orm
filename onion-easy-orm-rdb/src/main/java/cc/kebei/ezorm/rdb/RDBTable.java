package cc.kebei.ezorm.rdb;

import cc.kebei.ezorm.core.Table;
import cc.kebei.ezorm.rdb.meta.RDBTableMetaData;

public interface RDBTable<T> extends Table<T> {
    RDBTableMetaData getMeta();

    RDBQuery<T> createQuery();

}
