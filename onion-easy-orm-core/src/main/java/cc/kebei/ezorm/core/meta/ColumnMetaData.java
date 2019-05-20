package cc.kebei.ezorm.core.meta;


import cc.kebei.ezorm.core.DefaultValue;
import cc.kebei.ezorm.core.OptionConverter;
import cc.kebei.ezorm.core.PropertyWrapper;
import cc.kebei.ezorm.core.ValueConverter;

import java.io.Serializable;
import java.util.Set;

@SuppressWarnings("all")
public interface ColumnMetaData extends Serializable, Cloneable {
    String getName();

    String getAlias();

    String getComment();

    Class getJavaType();

    <T extends TableMetaData> T getTableMetaData();

    ValueConverter getValueConverter();

    OptionConverter getOptionConverter();

    DefaultValue getDefaultValue();

    Set<String> getValidator();

    PropertyWrapper getProperty(String property);

    PropertyWrapper getProperty(String property, Object defaultValue);

    PropertyWrapper setProperty(String property, Object value);

    <T extends ColumnMetaData> T clone();
}
