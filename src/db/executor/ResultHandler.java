package db.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 14.05.2018.
 */
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
