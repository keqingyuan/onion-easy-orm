package cc.kebei.ezorm.rdb.render;

/**
 * TODO 完成注释
 *
 * @author Kebei
 */
public interface Sql {
    String getSql();

    static Sql build(String sql) {
        return () -> sql;
    }
}
