package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DbSelect2
 */
@WebServlet("/test/DbSelect2")
public class DbSelect2 extends HttpServlet {

	static final String URL = "jdbc:mysql://localhost/user_db?autoReconnect=true&useSSL=false";
    static final String USERNAME = "root";
    static final String PASSWORD = "aiaiaiai";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 初期表示でなければ更新
		if(request.getParameter("txtGoods")!=null){
			dbSelect();
        }

        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/test/DbtestJsp.jsp");
        dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

    public static void dbSelect() {

        String sql = "SELECT * FROM goodsmaster Where ID = ?;";

        System.out.println("forName前");
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

        System.out.println("getConnection前");
        try (	Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        		PreparedStatement statement = connection.prepareStatement(sql); ) {
        	System.out.println("getConnection後");
        	statement.setInt(1,1);

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