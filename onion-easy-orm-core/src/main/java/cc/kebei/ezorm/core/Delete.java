package cc.kebei.ezorm.core;

import cc.kebei.ezorm.core.param.Param;

import java.sql.SQLException;

public interface Delete extends Conditional<Delete>, TriggerSkipSupport<Delete> {
    Delete setParam(Param param);

    int exec() throws SQLException;
}
