package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DbUpdate2
 */
@WebServlet("/test/DbUpdate2")
public class DbUpdate2 extends HttpServlet {

    static final String URL = "jdbc:mysql://localhost/user_db";
    static final String USERNAME = "root";
    static final String PASSWORD = "aiaiaiai";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 初期表示でなければ更新
		if(request.getParameter("txtGoods")!=null){
			System.out.println("更新----------------------------------------------");
			dbInsert();
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

    public static void dbInsert() {

        String sql = "INSERT INTO goodsmaster(ID,name,price) VALUES (?,?,?)";

        try (



        	Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        	PreparedStatement statement = connection.prepareStatement(sql); ) {

        	statement.setInt(1,0);
        	statement.setString(2,"apple");
        	statement.setInt(3,4);

        	connection.setAutoCommit(false);

        	statement.executeUpdate();
            connection.commit();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("処理が完了しました");
        }

    }


}