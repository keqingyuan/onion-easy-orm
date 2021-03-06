package cc.kebei.ezorm.rdb.simple.wrapper;

import cc.kebei.ezorm.core.OptionConverter;
import cc.kebei.ezorm.core.ValueConverter;
import cc.kebei.ezorm.rdb.meta.RDBColumnMetaData;
import cc.kebei.ezorm.rdb.meta.RDBTableMetaData;
import cc.kebei.ezorm.rdb.meta.expand.SimpleMapWrapper;

import java.util.Map;

public class AdvancedMapWrapper extends SimpleMapWrapper {
    private RDBTableMetaData tableMetaData;


    public AdvancedMapWrapper(RDBTableMetaData tableMetaData) {
        this.tableMetaData = tableMetaData;
    }

    @Override
    public void wrapper(Map<String, Object> instance, int index, String attr, Object value) {
        RDBColumnMetaData metaData = tableMetaData.findColumn(attr);
        if (null != metaData) {
            ValueConverter valueConverter = metaData.getValueConverter();
            value = valueConverter.getValue(value);
            super.wrapper(instance, index, attr, value);
            OptionConverter optionConverter = metaData.getOptionConverter();
            if (optionConverter != null) {
                Object value1 = optionConverter.converterValue(value);
                String targetName = optionConverter.getFieldName();
                if (attr.contains(".")) {
                    targetName = attr.split("[.]")[0] + "." + targetName;
                }
                putValue(instance, targetName, value1);
            }
        } else {
            super.wrapper(instance, index, attr, value);
        }
    }
}
