package cc.kebei.ezorm.core.dsl;

import cc.kebei.ezorm.core.SqlConditionSupport;
import cc.kebei.ezorm.core.param.Param;
import cc.kebei.ezorm.core.param.SqlTerm;
import cc.kebei.ezorm.core.param.Term;
import cc.kebei.ezorm.core.Conditional;
import cc.kebei.ezorm.core.NestConditional;
import cc.kebei.ezorm.core.SimpleNestConditional;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Kebei
 */
public final class Delete<P extends Param> extends SqlConditionSupport<Delete<P>> implements Conditional<Delete<P>> {
    private P param = null;
    private Accepter<Delete<P>, Object> accepter = this::and;
    private Executor<P> executor;

    public Delete(P param) {
        this.param = param;
    }

    public Delete setExecutor(Executor<P> executor) {
        this.executor = executor;
        return this;
    }

    public P getParam() {
        return param;
    }

    public Delete setParam(P param) {
        this.param = param;
        return this;
    }

    @Override
    public Delete<P> accept(Term term) {
        param.addTerm(term);
        return this;
    }

    public NestConditional<Delete<P>> nest() {
        return new SimpleNestConditional<>(this, this.param.nest());
    }

    public NestConditional<Delete<P>> nest(String column, Object value) {
        return new SimpleNestConditional<>(this, this.param.nest(column, value));
    }

    @Override
    public NestConditional<Delete<P>> orNest() {
        return new SimpleNestConditional<>(this, this.param.orNest());
    }

    @Override
    public NestConditional<Delete<P>> orNest(String column, Object value) {
        return new SimpleNestConditional<>(this, this.param.orNest(column, value));
    }


    @Override
    public Delete<P> and() {
        setAnd();
        this.accepter = this::and;
        return this;
    }

    @Override
    public Delete<P> or() {
        setOr();
        this.accepter = this::or;
        return this;
    }

    @Override
    public Delete<P> and(String column, String termType, Object value) {
        this.param.and(column, termType, value);
        return this;
    }

    @Override
    public Delete<P> or(String column, String termType, Object value) {
        this.param.or(column, termType, value);
        return this;
    }

    public Delete<P> where(String column, String termType, Object value) {
        and(column, termType, value);
        return this;
    }

    @Override
    public Accepter<Delete<P>, Object> getAccepter() {
        return accepter;
    }

    @Override
    protected Delete<P> addSqlTerm(SqlTerm term) {
        param.addTerm(term);
        return this;
    }

    @FunctionalInterface
    public interface Executor<P> {
        int doExecute(P param);
    }

    public Delete<P> excludes(String... columns) {
        param.excludes(columns);
        return this;
    }

    public Delete<P> includes(String... columns) {
        param.includes(columns);
        return this;
    }

    public <R> R exec(Function<P, R> executor) {
        return executor.apply(getParam());
    }

    public int exec() {
        return executor.doExecute(param);
    }

    public static Delete<Param> empty() {
        return new Delete<>(new Param());
    }

    public static <P extends Param> Delete<P> empty(Supplier<P> supplier) {
        return new Delete<>(supplier.get());
    }
}
