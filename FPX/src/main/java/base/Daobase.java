package base;

import java.util.ArrayList;

/**
 * DAO基础接口
 */
public interface Daobase<T> {

    public ArrayList<T> SelectAll();

    public ArrayList<T> QuerySQL(String sql);
}
