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

package cc.kebei.ezorm.rdb.simple.wrapper;

import cc.kebei.ezorm.rdb.meta.RDBColumnMetaData;
import cc.kebei.ezorm.rdb.meta.RDBTableMetaData;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import cc.kebei.ezorm.core.ObjectWrapper;
import cc.kebei.ezorm.core.ValueConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanWrapper<T> implements ObjectWrapper<T> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private InstanceCreator<T> creator;
    private RDBTableMetaData tableMetaData;
    private Class<T>           type;
    private static final PropertyUtilsBean propertyUtil = BeanUtilsBean.getInstance().getPropertyUtils();

    public BeanWrapper(InstanceCreator<T> creator, RDBTableMetaData tableMetaData) {
        this.creator = creator;
        this.tableMetaData = tableMetaData;
        type = (Class<T>) creator.newInstance().getClass();
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public T newInstance() {
        return creator.newInstance();
    }

    @Override
    public void wrapper(T instance, int index, String attr, Object value) {
        RDBColumnMetaData metaData = tableMetaData.findColumn(attr);
        if (metaData != null) {
            try {
                ValueConverter valueConverter = metaData.getValueConverter();
                propertyUtil.setProperty(instance, attr, valueConverter.getValue(value));
            } catch (NoSuchMethodException e) {
            } catch (Throwable e) {
                logger.warn("{}.setProperty('{}',{});error ", instance.getClass().getName(), attr, value, e);
            }
        }
    }

    @Override
    public boolean done(T instance) {
        return true;
    }

    public interface InstanceCreator<T> {
        T newInstance();
    }
}
