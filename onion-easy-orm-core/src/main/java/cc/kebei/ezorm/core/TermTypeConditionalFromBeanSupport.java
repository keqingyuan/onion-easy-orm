package cc.kebei.ezorm.core;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public interface TermTypeConditionalFromBeanSupport<B> {
    Logger logger = LoggerFactory.getLogger("queryForBean");

    B getBean();

    default Object getValue(StaticMethodReferenceColumn<B> property) {
        return property.apply(getBean());
    }

    default Object getValue(String property) {
        if (getBean() == null) {
            return null;
        }
        PropertyUtilsBean propertyUtilsBean = BeanUtilsBean.getInstance().getPropertyUtils();
        try {
            return propertyUtilsBean.getProperty(getBean(), property);
        } catch (Exception e) {
            logger.warn("get bean property {} error", property, e);
        }
        return null;
    }

}
