package cc.kebei.ezorm.rdb.executor;

import java.util.List;

/**
 * Created by Kebei on 16-6-4.
 */
public class EmptySQL implements SQL {
    @Override
    public String getSql() {
        return null;
    }

    @Override
    public Object getParams() {
        return null;
    }

    @Override
    public List<BindSQL> getBinds() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
