package cc.kebei.ezorm.core;

import cc.kebei.ezorm.core.param.SqlTerm;
import cc.kebei.ezorm.core.param.Term;

/**
 * @author Kebei
 */
public abstract class SqlConditionSupport<T> {
    protected Term.Type nowTermType = Term.Type.and;

    protected abstract T addSqlTerm(SqlTerm term);

    public Term.Type getNowTermType() {
        return nowTermType;
    }

    protected T setOr() {
        nowTermType = Term.Type.or;
        return (T) this;
    }

    protected T setAnd() {
        nowTermType = Term.Type.and;
        return (T) this;
    }

    public T sql(String sql, Object... params) {
        SqlTerm sqlTerm = new SqlTerm();
        sqlTerm.setColumn(sql);
        sqlTerm.setValue(params);
        sqlTerm.setSql(sql);
        sqlTerm.setParam(params);
        sqlTerm.setType(getNowTermType());
        return addSqlTerm(sqlTerm);
    }
}
