package test;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUpdate {

    static final String URL = "jdbc:mysql://localhost/user_db";
    static final String USERNAME = "root";
    static final String PASSWORD = "aiaiaiai";

    static Array goodsmaster = null;

    public static void main(String[] args) {

        String sql = "INSERT INTO goodsmaster(ID,name,price) VALUES (2,'Up',300)";

        try (	Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        		PreparedStatement statement = connection.prepareStatement(sql); ) {

        	connection.setAutoCommit(false);

            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.executeUpdate();
                connection.commit();
            } catch (Exception e) {
            	connection.rollback();
                System.out.println("rollback");
                throw e;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("処理が完了しました");

        }

    }

}