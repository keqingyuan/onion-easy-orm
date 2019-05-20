/*
 * Copyright 2016 http://github.com/keqingyuan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.kebei.ezorm.core;

import cc.kebei.ezorm.core.param.TermType;

import java.util.Arrays;
import java.util.Collection;

public interface ConditionalFromBean<B, T extends ConditionalFromBean<B, T>> extends LogicalOperation<T>,
        TermTypeConditionalFromBeanSupport<B> {

    NestConditionalFromBean<B, T> nest();

    NestConditionalFromBean<B, T> nest(String column);

    NestConditionalFromBean<B, T> orNest();

    NestConditionalFromBean<B, T> orNest(String column);

    T and();

    T or();

    T and(String column, String termType);

    T or(String column, String termType);

    default T and(StaticMethodReferenceColumn<B> column, String termType) {
        return and(column.getColumn(), termType);
    }

    default T or(StaticMethodReferenceColumn<B> column, String termType) {
        return or(column.getColumn(), termType);
    }

    TermTypeConditionalSupport.Accepter<T, Object> getAccepter();

    default T where(String column) {
        return and(column, TermType.eq);
    }

    default T where(StaticMethodReferenceColumn<B> column) {
        return and(column, TermType.eq);
    }

    default T where() {
        return (T) this;
    }

    default T and(String column) {
        return and(column, TermType.eq);
    }

    default T or(String column) {
        return or(column, TermType.eq);
    }

    default T and(StaticMethodReferenceColumn<B> column) {
        return and(column, TermType.eq);
    }

    default T or(StaticMethodReferenceColumn<B> column) {
        return or(column, TermType.eq);
    }

    default T is(String column) {
        return accept(column, TermType.eq);
    }

    default T is(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.eq);
    }

    default T like(String column) {
        return accept(column, TermType.like);
    }

    default T like$(String column) {
        Object value = getValue(column);
        if (value == null)
            return like(column);
        return accept(column, TermType.like, String.valueOf(value).concat("%"));
    }

    default T $like(String column) {
        Object value = getValue(column);
        if (value == null)
            return like(column);
        return accept(column, TermType.like, "%".concat(String.valueOf(value)));
    }

    default T $like$(String column) {
        Object value = getValue(column);
        if (value == null)
            return like(column);
        return accept(column, TermType.like, "%".concat(String.valueOf(value)).concat("%"));
    }

    default T notLike(String column) {
        return accept(column, TermType.nlike);
    }

    default T gt(String column) {
        return accept(column, TermType.gt);
    }

    default T lt(String column) {
        return accept(column, TermType.lt);
    }

    default T gte(String column) {
        return accept(column, TermType.gte);
    }

    default T lte(String column) {
        return accept(column, TermType.lte);
    }

    default T in(String column) {
        return accept(column, TermType.in);
    }

    default T in(String column, Object... values) {
        return accept(column, TermType.in, values);
    }

    default T in(String column, Collection values) {
        return accept(column, TermType.in, values);
    }

    default T notIn(String column, Object... values) {
        return accept(column, TermType.nin, values);
    }

    default T notIn(String column, Collection values) {
        return accept(column, TermType.nin, values);
    }


    default T notIn(String column) {
        return accept(column, TermType.nin);
    }

    default T isEmpty(String column) {
        return accept(column, TermType.empty, 1);
    }

    default T notEmpty(String column) {
        return accept(column, TermType.nempty, 1);
    }

    default T isNull(String column) {
        return accept(column, TermType.isnull, 1);
    }

    default T notNull(String column) {
        return accept(column, TermType.notnull, 1);
    }

    default T not(String column) {
        return accept(column, TermType.not);
    }

    default T between(String column, Object between, Object and) {
        return accept(column, TermType.btw, Arrays.asList(between, and));
    }

    default T notBetween(String column, Object between, Object and) {
        return accept(column, TermType.nbtw, Arrays.asList(between, and));
    }


    /*lambda*/
    default T like(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.like);
    }

    default T like$(StaticMethodReferenceColumn<B> column) {
        Object value = getValue(column);
        if (value == null)
            return like(column);
        return accept(column, TermType.like, String.valueOf(value).concat("%"));
    }

    default T $like(StaticMethodReferenceColumn<B> column) {
        Object value = getValue(column);
        if (value == null)
            return like(column);
        return accept(column, TermType.like, "%".concat(String.valueOf(value)));
    }

    default T $like$(StaticMethodReferenceColumn<B> column) {
        Object value = getValue(column);
        if (value == null)
            return like(column);
        return accept(column, TermType.like, "%".concat(String.valueOf(value)).concat("%"));
    }

    default T notLike(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.nlike);
    }

    default T gt(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.gt);
    }

    default T lt(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.lt);
    }

    default T gte(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.gte);
    }

    default T lte(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.lte);
    }

    default T in(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.in);
    }

    default T in(StaticMethodReferenceColumn<B> column, Object... values) {
        return accept(column, TermType.in, values);
    }

    default T in(StaticMethodReferenceColumn<B> column, Collection values) {
        return accept(column, TermType.in, values);
    }

    default T notIn(StaticMethodReferenceColumn<B> column, Object... values) {
        return accept(column, TermType.nin, values);
    }

    default T notIn(StaticMethodReferenceColumn<B> column, Collection values) {
        return accept(column, TermType.nin, values);
    }

    default T notIn(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.nin);
    }

    default T isEmpty(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.empty, 1);
    }

    default T notEmpty(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.nempty, 1);
    }

    default T isNull(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.isnull, 1);
    }

    default T notNull(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.notnull, 1);
    }

    default T not(StaticMethodReferenceColumn<B> column) {
        return accept(column, TermType.not);
    }

    default T between(StaticMethodReferenceColumn<B> column, Object between, Object and) {
        return accept(column, TermType.btw, Arrays.asList(between, and));
    }

    default T notBetween(StaticMethodReferenceColumn<B> column, Object between, Object and) {
        return accept(column, TermType.nbtw, Arrays.asList(between, and));
    }

    default T accept(String column, String termType) {
        return accept(column, termType, getValue(column));
    }

    default T accept(StaticMethodReferenceColumn<B> column, String termType) {
        return accept(column, termType, getValue(column));
    }

    default T accept(String column, String termType, Object value) {
        return getAccepter().accept(column, termType, value);
    }

    default T accept(StaticMethodReferenceColumn<B> column, String termType, Object value) {
        return getAccepter().accept(column.getColumn(), termType, value);
    }

    T sql(String sql, Object... params);

}
