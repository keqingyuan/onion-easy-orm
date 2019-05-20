package cc.kebei.ezorm.rdb.render.support.sqlserver;

import cc.kebei.ezorm.core.param.Param;
import cc.kebei.ezorm.core.param.Term;
import cc.kebei.ezorm.rdb.executor.SQL;
import cc.kebei.ezorm.rdb.meta.RDBTableMetaData;
import cc.kebei.ezorm.rdb.render.SqlAppender;
import cc.kebei.ezorm.rdb.render.dialect.Dialect;
import cc.kebei.ezorm.rdb.render.support.simple.CommonSqlRender;
import cc.kebei.ezorm.rdb.render.support.simple.SimpleSQL;
import cc.kebei.ezorm.rdb.render.support.simple.SimpleWhereSqlBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kebei on 16-6-4.
 */
public class SqlServerDeleteSqlRender extends CommonSqlRender<Param> {
    public SqlServerDeleteSqlRender(Dialect dialect) {
        setDialect(dialect);
    }

    class SimpleDeleteSqlRenderProcess extends SimpleWhereSqlBuilder {
        private RDBTableMetaData metaData;
        private Param            param;
        private SqlAppender whereSql = new SqlAppender();

        public SimpleDeleteSqlRenderProcess(RDBTableMetaData metaData, Param param) {
            this.metaData = metaData;
            this.param = param;
            List<Term> terms = param.getTerms();
            terms = terms.stream().filter(term -> term.getColumn() == null || !term.getColumn().contains(".")).collect(Collectors.toList());
            param.setTerms(terms);
            //解析查询条件
            buildWhere(metaData, "", terms, whereSql, new HashSet<>());
            if (!whereSql.isEmpty()) whereSql.removeFirst();
        }

        public SQL process() {
            SqlAppender appender = new SqlAppender();
            appender.add("DELETE ", metaData.getAlias(), " FROM ", metaData.getFullName());
            if (whereSql.isEmpty()) {
                throw new UnsupportedOperationException("禁止执行未设置任何条件的删除操作!");
            }
            appender.add(" WHERE", " ").addAll(whereSql);
            String sql = appender.toString();
            SimpleSQL simpleSQL = new SimpleSQL(sql, param);
            return simpleSQL;
        }

        @Override
        public Dialect getDialect() {
            return dialect;
        }
    }

    @Override
    public SQL render(RDBTableMetaData metaData, Param param) {
        return new SimpleDeleteSqlRenderProcess(metaData, param).process();
    }

    private Dialect dialect;

    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }
}
