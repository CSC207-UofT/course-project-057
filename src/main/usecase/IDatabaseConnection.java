package usecase;
import java.sql.*;

public interface IDatabaseConnection{
    Connection getConnection() throws SQLException;
}