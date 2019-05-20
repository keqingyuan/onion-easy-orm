package cc.kebei.ezorm.core;

public interface ValueConverter {
    Object getData(Object value);

    Object getValue(Object data);
}
