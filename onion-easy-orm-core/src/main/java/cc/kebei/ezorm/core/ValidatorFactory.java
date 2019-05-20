package cc.kebei.ezorm.core;

import cc.kebei.ezorm.core.meta.TableMetaData;

public interface ValidatorFactory {
    Validator createValidator(TableMetaData tableMetaData);
}
