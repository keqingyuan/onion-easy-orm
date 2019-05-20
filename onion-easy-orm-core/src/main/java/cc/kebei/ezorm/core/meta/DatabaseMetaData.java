package cc.kebei.ezorm.core.meta;

import cc.kebei.ezorm.core.ObjectWrapperFactory;
import cc.kebei.ezorm.core.ValidatorFactory;

public interface DatabaseMetaData {

    String getDatabaseName();

    ObjectWrapperFactory getObjectWrapperFactory();

    ValidatorFactory getValidatorFactory();

    <T extends TableMetaData> T getTableMetaData(String name);

}
