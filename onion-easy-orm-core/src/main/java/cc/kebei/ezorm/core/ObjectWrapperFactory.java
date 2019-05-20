package cc.kebei.ezorm.core;


import cc.kebei.ezorm.core.meta.TableMetaData;

public interface ObjectWrapperFactory {
    <T> ObjectWrapper<T> createObjectWrapper(TableMetaData metaData);
}
