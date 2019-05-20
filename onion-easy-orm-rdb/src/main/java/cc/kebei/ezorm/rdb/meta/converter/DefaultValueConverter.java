package cc.kebei.ezorm.rdb.meta.converter;


import cc.kebei.ezorm.core.ValueConverter;

/**
 * Created by Kebei on 16-6-4.
 */
public class DefaultValueConverter implements ValueConverter {
    @Override
    public Object getData(Object value) {
        return value;
    }

    @Override
    public Object getValue(Object data) {
        return data;
    }
}
