package cc.kebei.ezorm.core;

import cc.kebei.ezorm.core.param.TermType;

import java.util.Arrays;
import java.util.Collection;

public interface NestConditionalFromBean<B, T extends TermTypeConditionalFromBeanSupport<B>>
        extends LogicalOperation<NestConditionalFromBean<B, T>>,
        TermTypeConditionalFromBeanSupport<B> {

    T end();

    NestConditionalFromBean<B, NestConditionalFromBean<B, T>> nest();

    NestConditionalFromBean<B, NestConditionalFromBean<B, T>> nest(String column);

    NestConditionalFromBean<B, NestConditionalFromBean<B, T>> orNest();

    NestConditionalFromBean<B, NestConditionalFromBean<B, T>> orNest(String column);

    NestConditionalFromBean<B, T> and();

    NestConditionalFromBean<B, T> or();

    NestConditionalFromBean<B, T> and(String column, String termType);

    NestConditionalFromBean<B, T> or(String column, String termType);

    default NestConditionalFromBean<B, T> and(String column) {
        and();
        return and(column, TermType.eq);
    }

    default NestConditionalFromBean<B, T> or(String column) {
        or();
        return or(column, TermType.eq);
    }


    NestConditionalFromBean<B, T> sql(String sql, Object... params);

    default NestConditionalFromBean<B, T> like$(String column) {
        Object value = getValue(column);
        if (value == null)
            return like(column);
        return accept(column, TermType.like, String.valueOf(value).concat("%"));
    }

    default NestConditionalFromBean<B, T> $like(String column) {
        Object value = getValue(column);
        if (value == null)
            return like(column);
        return accept(column, TermType.like, "%".concat(String.valueOf(value)));
    }

    default NestConditionalFromBean<B, T> $like$(String column) {
        Object value = getValue(column);
        if (value == null)
            return like(column);
        return accept(column, TermType.like, "%".concat(String.valueOf(value)).concat("%"));
    }

    default NestConditionalFromBean<B, T> like(String column) {
        return accept(column, TermType.like);
    }

    default NestConditionalFromBean<B, T> notLike(String column) {
        return accept(column, TermType.nlike);
    }

    default NestConditionalFromBean<B, T> gt(String column) {
        return accept(column, TermType.gt);
    }

    default NestConditionalFromBean<B, T> lt(String column) {
        return accept(column, TermType.lt);
    }

    default NestConditionalFromBean<B, T> gte(String column) {
        return accept(column, TermType.gte);
    }

    default NestConditionalFromBean<B, T> lte(String column) {
        return accept(column, TermType.lte);
    }

    default NestConditionalFromBean<B, T> in(String column, Object... values) {
        return accept(column, TermType.in, values);
    }

    default NestConditionalFromBean<B, T> in(String column) {
        return accept(column, TermType.in);
    }

    default NestConditionalFromBean<B, T> in(String column, Collection values) {
        return accept(column, TermType.in, values);
    }

    default NestConditionalFromBean<B, T> notIn(String column) {
        return accept(column, TermType.nin);
    }

    default NestConditionalFromBean<B, T> isEmpty(String column) {
        return accept(column, TermType.empty, 1);
    }

    default NestConditionalFromBean<B, T> notEmpty(String column) {
        return accept(column, TermType.nempty, 1);
    }

    default NestConditionalFromBean<B, T> isNull(String column) {
        return accept(column, TermType.isnull, 1);
    }

    default NestConditionalFromBean<B, T> notNull(String column) {
        return accept(column, TermType.notnull, 1);
    }

    default NestConditionalFromBean<B, T> not(String column) {
        return accept(column, TermType.not);
    }

    default NestConditionalFromBean<B, T> between(String column, Object between, Object and) {
        return accept(column, TermType.btw, Arrays.asList(between, and));
    }

    default NestConditionalFromBean<B, T> notBetween(String column, Object between, Object and) {
        return accept(column, TermType.nbtw, Arrays.asList(between, and));
    }

    /*-----------lambda----------*/
    default NestConditionalFromBean<B, T> like(StaticMethodReferenceColumn<B> column) {
        return accept(column.getColumn(), TermType.like);
    }

    default NestConditionalFromBean<B, T> like$(StaticMethodReferenceColumn<B> column) {
        Object value = getValue(column);
        if (value == null)
            return like(column);
        return accept(column, TermType.like, String.valueOf(value).concat("%"));
    }

    default NestConditionalFromBean<B, T> $like(StaticMethodReferenceColumn<B> column) {
        Object value = getValue(column);
        if (value == null)
            return like(column);
        return accept(column, TermType.like, "%".concat(String.valueOf(value)));
    }

    default NestConditionalFromBean<B, T> $like$(StaticMethodReferenceColumn<B> column) {
        Object value = getValue(column);
        if (value == null)
            return like(column);
        return accept(column, TermType.like, "%".concat(String.valueOf(value)).concat("%"));
    }

    default NestConditionalFromBean<B, T> notLike(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.nlike);
    }

    default NestConditionalFromBean<B, T> gt(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.gt);
    }

    default NestConditionalFromBean<B, T> lt(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.lt);
    }

    default NestConditionalFromBean<B, T> gte(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.gte);
    }

    default NestConditionalFromBean<B, T> lte(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.lte);
    }

    default NestConditionalFromBean<B, T> in(StaticMethodReferenceColumn<B> column, Object... values) {
        return accept(column, TermType.in, values);
    }

    default NestConditionalFromBean<B, T> in(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.in);
    }

    default NestConditionalFromBean<B, T> in(StaticMethodReferenceColumn<B> column, Collection values) {
        return accept(column, TermType.in, values);
    }

    default NestConditionalFromBean<B, T> notIn(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.nin);
    }

    default NestConditionalFromBean<B, T> isEmpty(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.empty, 1);
    }

    default NestConditionalFromBean<B, T> notEmpty(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.nempty, 1);
    }

    default NestConditionalFromBean<B, T> isNull(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.isnull, 1);
    }

    default NestConditionalFromBean<B, T> notNull(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.notnull, 1);
    }

    default NestConditionalFromBean<B, T> not(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.not);
    }

    default NestConditionalFromBean<B, T> between(StaticMethodReferenceColumn<B> column, Object between, Object and) {
        return accept(column, TermType.btw, Arrays.asList(between, and));
    }

    default NestConditionalFromBean<B, T> notBetween(StaticMethodReferenceColumn<B> column, Object between, Object and) {
        return accept(column, TermType.nbtw, Arrays.asList(between, and));
    }

    default NestConditionalFromBean<B, T> accept(String column, String termType, Object value) {
        return getAccepter().accept(column, termType, value);
    }

    default NestConditionalFromBean<B, T> accept(String column, String termType) {
        return getAccepter().accept(column, termType, getValue(column));
    }

    default NestConditionalFromBean<B, T> accept(StaticMethodReferenceColumn<B> column, String termType, Object value) {
        return getAccepter().accept(column.getColumn(), termType, value);
    }

    default NestConditionalFromBean<B, T> accept(StaticMethodReferenceColumn<B> column, String termType) {
        return getAccepter().accept(column.getColumn(), termType, getValue(column));
    }

    TermTypeConditionalSupport.Accepter<NestConditionalFromBean<B, T>, Object> getAccepter();


}
