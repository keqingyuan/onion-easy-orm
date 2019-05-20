package cc.kebei.ezorm.rdb.meta.builder;

import cc.kebei.ezorm.rdb.meta.IndexMetaData;
import cc.kebei.ezorm.rdb.meta.RDBTableMetaData;

/**
 * @author Kebei
 * @since 1.0.0
 */
public class IndexBuilder {
    private TableBuilder tableBuilder;

    public IndexBuilder(TableBuilder tableBuilder, RDBTableMetaData table) {
        this.tableBuilder = tableBuilder;
        this.table = table;
    }

    private RDBTableMetaData table;

    private IndexMetaData index = new IndexMetaData();

    public IndexBuilder name(String indexName) {
        index.setIndexName(indexName);
        return this;
    }
    public IndexBuilder unique() {
        index.setUnique(true);
        return this;
    }
    public IndexBuilder column(String column) {
        return column(column, null);
    }

    public IndexBuilder column(String column, String sort) {
        index.getColumnName().add(IndexMetaData.IndexColumn.of(column, sort));
        return this;
    }

    public TableBuilder commit() {
        table.getIndexes().add(index);
        return tableBuilder;
    }

}
