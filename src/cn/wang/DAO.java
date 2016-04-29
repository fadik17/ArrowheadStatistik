package cn.wang;
/**
 * Created by wang on 22/04/16.
 */
public interface DAO {

    void connect() throws Exception;

    void close() throws Exception;
}
