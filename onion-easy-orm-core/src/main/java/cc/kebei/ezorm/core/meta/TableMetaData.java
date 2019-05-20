package cc.kebei.ezorm.core.meta;

import cc.kebei.ezorm.core.ObjectWrapper;
import cc.kebei.ezorm.core.PropertyWrapper;
import cc.kebei.ezorm.core.Trigger;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author Kebei
 */
public interface TableMetaData extends Serializable {
    String getName();

    String getComment();

    String getAlias();

    <T extends DatabaseMetaData> T getDatabaseMetaData();

    <T extends ColumnMetaData> Set<T> getColumns();

    <T extends ColumnMetaData> T getColumn(String name);

    <T extends ColumnMetaData> T findColumn(String name);

    <T> ObjectWrapper<T> getObjectWrapper();

    PropertyWrapper getProperty(String property);

    PropertyWrapper getProperty(String property, Object defaultValue);

    PropertyWrapper getProperty(String name, Supplier<Object> defaultValue);

    PropertyWrapper setProperty(String property, Object value);

    void on(String name, Trigger trigger);

    Object on(String name, Map<String, Object> triggerContext);

    boolean triggerIsSupport(String name);
}
