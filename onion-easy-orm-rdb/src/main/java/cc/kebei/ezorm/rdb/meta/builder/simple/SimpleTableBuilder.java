package cc.kebei.ezorm.rdb.meta.builder.simple;

import cc.kebei.ezorm.rdb.meta.builder.ColumnBuilder;
import cc.kebei.ezorm.rdb.meta.builder.TableBuilder;
import cc.kebei.ezorm.rdb.executor.SqlExecutor;
import cc.kebei.ezorm.rdb.meta.RDBColumnMetaData;
import cc.kebei.ezorm.rdb.meta.RDBTableMetaData;
import cc.kebei.ezorm.rdb.meta.builder.IndexBuilder;
import cc.kebei.ezorm.rdb.meta.parser.TableMetaParser;
import cc.kebei.ezorm.rdb.RDBDatabase;

import java.sql.SQLException;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author Kebei
 */
public class SimpleTableBuilder implements TableBuilder {
    public RDBTableMetaData rdbTableMetaData;
    public RDBDatabase      database;
    public SqlExecutor      sqlExecutor;
    public TableMetaParser  parser;

    public SimpleTableBuilder(RDBTableMetaData rdbTableMetaData, RDBDatabase database, SqlExecutor sqlExecutor) {
        this.rdbTableMetaData = rdbTableMetaData;
        this.database = database;
        this.sqlExecutor = sqlExecutor;
        this.parser = database.getMeta().getParser();
        if (this.parser == null) {
            this.parser = database.getMeta().getDialect().getDefaultParser(sqlExecutor);
        }
    }

    @Override
    public IndexBuilder index() {
        return new IndexBuilder(this, rdbTableMetaData);
    }

    public TableBuilder custom(Consumer<RDBTableMetaData> consumer) {
        consumer.accept(rdbTableMetaData);
        return this;
    }

    @Override
    public TableBuilder addColumn(Set<RDBColumnMetaData> columns) {
        columns.forEach(rdbTableMetaData::addColumn);
        return this;
    }

    @Override
    public ColumnBuilder addOrAlterColumn(String name) {
        RDBColumnMetaData rdbColumnMetaData = rdbTableMetaData.getColumn(name);
        if (null == rdbColumnMetaData) {
            rdbColumnMetaData = new RDBColumnMetaData();
            rdbColumnMetaData.setName(name);
        }
        return new SimpleColumnBuilder(rdbColumnMetaData, this, rdbTableMetaData);
    }

    @Override
    public TableBuilder removeColumn(String name) {
        rdbTableMetaData.removeColumn(name);
        return this;
    }

    @Override
    public ColumnBuilder addColumn() {
        RDBColumnMetaData rdbColumnMetaData = new RDBColumnMetaData();
        SimpleColumnBuilder columnBuilder = new SimpleColumnBuilder(rdbColumnMetaData, this, rdbTableMetaData);
        return columnBuilder;
    }

    @Override
    public TableBuilder comment(String comment) {
        rdbTableMetaData.setComment(comment);
        return this;
    }

    @Override
    public TableBuilder property(String propertyName, Object value) {
        rdbTableMetaData.setProperty(propertyName, value);
        return this;
    }

    @Override
    public TableBuilder alias(String name) {
        rdbTableMetaData.setAlias(name);
        return this;
    }

    @Override
    public void commit() throws SQLException {
        RDBTableMetaData old = parser.parse(rdbTableMetaData.getName());
        if (null != old) {
            //加载旧的表结构
            database.reloadTable(old);
            database.alterTable(rdbTableMetaData);
        } else {
            database.createTable(rdbTableMetaData);
        }
    }
}
