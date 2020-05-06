package test;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbSelect {

    static final String URL = "jdbc:mysql://localhost/user_db";
    static final String USERNAME = "root";
    static final String PASSWORD = "aiaiaiai";

    static Array goodsmaster = null;

    public static void main(String[] args) {

        String sql = "SELECT * FROM goodsmaster;";

        try (	Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        		PreparedStatement statement = connection.prepareStatement(sql); ) {

        	// 配列に結果をいれたい(途中)
//        	ResultSet result = statement.executeQuery();
//        	goodsmaster = result.getArray("name"); // 配列goodsmasterに結果を入れる(1件のみ…)
//        	Struct[] results = (Struct[]) goodsmaster.getArray();
//        	System.out.println("Struct[0] results = " + results[0]);

        	// テーブル内条件に合うレコード全部取得したい
        	// SQLの条件を変えたい時SQL内の?に以下のようにセットすると可変に出来る
//        	statement.setInt(1,2);
//        	statement.setString(2,"tanaka");

            try(ResultSet rs = statement.executeQuery()){
                while (rs.next()) {
                    System.out.println(
                    	rs.getInt("ID") + " " +
                    	rs.getString("name") + " " +
                        rs.getString("price"));
                }
            };


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("処理が完了しました");

        }

    }

}