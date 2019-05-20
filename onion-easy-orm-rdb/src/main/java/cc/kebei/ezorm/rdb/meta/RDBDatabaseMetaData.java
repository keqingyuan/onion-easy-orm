package cc.kebei.ezorm.rdb.meta;

import cc.kebei.ezorm.core.meta.AbstractDatabaseMetaData;
import cc.kebei.ezorm.core.meta.DatabaseMetaData;
import cc.kebei.ezorm.rdb.meta.parser.TableMetaParser;
import cc.kebei.ezorm.rdb.render.SqlRender;
import cc.kebei.ezorm.rdb.render.dialect.Dialect;

public abstract class RDBDatabaseMetaData extends AbstractDatabaseMetaData implements DatabaseMetaData {
    private TableMetaParser parser;

    public abstract Dialect getDialect();

    public abstract void init();

    public abstract <T> SqlRender<T> getRenderer(SqlRender.TYPE type);

    public abstract String getName();

    public void setParser(TableMetaParser parser) {
        this.parser = parser;
    }

    public TableMetaParser getParser() {
        return parser;
    }

    @Override
    public RDBTableMetaData getTableMetaData(String name) {
        return super.getTableMetaData(name);
    }

    public RDBTableMetaData removeTable(String name) {
        return tableMetaDataStorage.removeTableMeta(name);
    }

    public RDBTableMetaData putTable(RDBTableMetaData tableMetaData) {
        tableMetaData.setDatabaseMetaData(this);
        return tableMetaDataStorage.putTableMetaData(tableMetaData);
    }

    public void shutdown() {
        tableMetaDataStorage.clear();
    }

}
