package cc.kebei.ezorm.core;


public interface Validator {
    boolean validate(Object data, Operation operation);

    enum Operation {
        INSERT, UPDATE
    }
}
