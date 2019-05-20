package cc.kebei.ezorm.core;

import java.util.List;

/**
 * @author Kebei
 * @since 1.0.0
 */
public interface TermSupplier {
    String getColumn();

    String getTermType();

    List<String> getOptions();

    Object getValue();
}
