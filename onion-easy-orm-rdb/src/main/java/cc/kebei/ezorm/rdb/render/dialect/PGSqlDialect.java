package cc.kebei.ezorm.rdb.render.dialect;

import cc.kebei.ezorm.rdb.render.dialect.function.SqlFunction;
import cc.kebei.ezorm.rdb.render.dialect.term.BoostTermTypeMapper;
import cc.kebei.ezorm.rdb.executor.SqlExecutor;
import cc.kebei.ezorm.rdb.meta.parser.PGSqlTableMetaParser;
import cc.kebei.ezorm.rdb.meta.parser.TableMetaParser;
import cc.kebei.utils.StringUtils;

import java.sql.JDBCType;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author Kebei
 * @since 3.0
 */
public class PGSqlDialect extends DefaultDialect {


    public PGSqlDialect() {
        defaultDataTypeMapper = (meta) -> meta.getJdbcType().getName().toLowerCase();
        setDataTypeMapper(JDBCType.CHAR, (meta) -> StringUtils.concat("char(", meta.getLength(), ")"));
        setDataTypeMapper(JDBCType.VARCHAR, (meta) -> StringUtils.concat("varchar(", meta.getLength(), ")"));
        setDataTypeMapper(JDBCType.TIMESTAMP, (meta) -> "timestamp");
        setDataTypeMapper(JDBCType.TIME, (meta) -> "time");
        setDataTypeMapper(JDBCType.DATE, (meta) -> "date");
        setDataTypeMapper(JDBCType.CLOB, (meta) -> "text");
        setDataTypeMapper(JDBCType.LONGVARBINARY, (meta) -> "bytea");
        setDataTypeMapper(JDBCType.LONGVARCHAR, (meta) -> "text");
        setDataTypeMapper(JDBCType.BLOB, (meta) -> "bytea");
        setDataTypeMapper(JDBCType.BIGINT, (meta) -> "bigint");
        setDataTypeMapper(JDBCType.DOUBLE, (meta) -> "double");
        setDataTypeMapper(JDBCType.INTEGER, (meta) -> "integer");
        setDataTypeMapper(JDBCType.NUMERIC, (meta) -> StringUtils.concat("numeric(", meta.getPrecision(), ",", meta.getScale(), ")"));
        setDataTypeMapper(JDBCType.DECIMAL, (meta) -> StringUtils.concat("decimal(", meta.getPrecision(), ",", meta.getScale(), ")"));
        setDataTypeMapper(JDBCType.TINYINT, (meta) -> "smallint");
        setDataTypeMapper(JDBCType.BIGINT, (meta) -> "bigint");
        setDataTypeMapper(JDBCType.OTHER, (meta) -> "other");

        installFunction(SqlFunction.concat, param -> {
            List<Object> listParam = BoostTermTypeMapper.convertList(param.getParam());
            StringJoiner joiner = new StringJoiner("||");
            listParam.stream()
                    .map(String::valueOf)
                    .forEach(joiner::add);
            return joiner.toString();
        });

        installFunction(SqlFunction.bitand, param -> {
            List<Object> listParam = BoostTermTypeMapper.convertList(param.getParam());
            if (listParam.isEmpty()) {
                throw new IllegalArgumentException("[BITAND]参数不能为空");
            }
            StringJoiner joiner = new StringJoiner("&");
            listParam.stream().map(String::valueOf).forEach(joiner::add);
            return joiner.toString();
        });
    }

    @Override
    public String getQuoteStart() {
        return "\"";
    }

    @Override
    public String getQuoteEnd() {
        return "\"";
    }

    @Override
    public String doPaging(String sql, int pageIndex, int pageSize, boolean prepare) {
        if (prepare) {
            return sql + " limit #{pageSize} offset #{pageSize}*#{pageIndex}";
        }
        return new StringBuilder(sql)
                .append(" limit ")
                .append(pageSize)
                .append(" offset ")
                .append(pageSize * pageIndex)
                .toString();
    }

    @Override
    public boolean columnToUpperCase() {
        return false;
    }

    @Override
    public TableMetaParser getDefaultParser(SqlExecutor sqlExecutor) {
        return new PGSqlTableMetaParser(sqlExecutor);
    }
}
