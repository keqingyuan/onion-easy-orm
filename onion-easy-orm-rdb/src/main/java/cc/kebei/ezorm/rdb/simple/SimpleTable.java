package cc.kebei.ezorm.rdb.simple;

import cc.kebei.ezorm.core.Delete;
import cc.kebei.ezorm.core.Insert;
import cc.kebei.ezorm.core.ObjectWrapper;
import cc.kebei.ezorm.core.Update;
import cc.kebei.ezorm.rdb.executor.SqlExecutor;
import cc.kebei.ezorm.rdb.meta.RDBTableMetaData;
import cc.kebei.ezorm.rdb.RDBQuery;
import cc.kebei.ezorm.rdb.RDBTable;

/**
 * Created by Kebei on 16-6-4.
 */
class SimpleTable<T> implements RDBTable<T> {
    private RDBTableMetaData metaData;

    private SqlExecutor sqlExecutor;

    private ObjectWrapper objectWrapper;

    private SimpleDatabase database;

    public SimpleTable(RDBTableMetaData metaData, SimpleDatabase database, SqlExecutor sqlExecutor, ObjectWrapper objectWrapper) {
        this.metaData = metaData;
        this.sqlExecutor = sqlExecutor;
        this.objectWrapper = objectWrapper;
        this.database = database;
    }

    @Override
    public RDBTableMetaData getMeta() {
        return metaData;
    }

    @Override
    public RDBQuery<T> createQuery() {
        return new SimpleQuery<>(this, sqlExecutor, objectWrapper);
    }

    @Override
    public Update<T> createUpdate() {
        return new SimpleUpdate<>(this, sqlExecutor);
    }

    @Override
    public Delete createDelete() {
        return new SimpleDelete(this, sqlExecutor);
    }

    @Override
    public Insert<T> createInsert() {
        return new SimpleInsert<>(this, sqlExecutor);
    }

    public SimpleDatabase getDatabase() {
        return database;
    }
}
