package test;

import java.io.IOException;
import java.sql.Array;
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
 * Servlet implementation class DbTest
 */
@WebServlet("/test/DbTest")
public class DbTest extends HttpServlet {

    static final String URL = "jdbc:mysql://localhost/user_db";
    static final String USERNAME = "root";
    static final String PASSWORD = "aiaiaiai";
    static Array goodsmaster = null;

	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbTest() {
        super();
        // TODO Auto-generated constructor stub

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 初期表示でなければ更新
//		if(request.getParameter("txtGoods")!=null){
//			System.out.println("更新----------------------------------------------");
//			dbInsert(request);
//        }

	    prepData(request);
	    checkButten(request);
	    dbInsert(request);

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

	/**
	 * @see dbInsert(HttpServletRequest request)
	 * DBを更新する
	 */
	public void dbInsert(HttpServletRequest request){
        String sql = "INSERT INTO goodsmaster(ID,name,price) VALUES (?,?,?)";

        System.out.println("URL = " + URL + "、USERNAME = " + USERNAME + "、PASSWORD = " + PASSWORD);

        try (
        	Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        	PreparedStatement statement = connection.prepareStatement(sql); ) {

        	statement.setInt(1,0);
        	statement.setString(2,request.getParameter("txtGoods"));
        	statement.setInt(3,Integer.parseInt(request.getParameter("txtNumber")));

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

	/**
	 * @see prepData(HttpServletRequest request)
	 * テキストボックスに値を設定する
	 */
	private void prepData(HttpServletRequest request){

		System.out.println("-----初期値判定--------");
		System.out.println("txtGoods = " + request.getParameter("txtGoods") + "、txtNumber = " + request.getParameter("txtNumber"));

		// 商品名
        if(request.getParameter("txtGoods")==null){
        	System.out.println("-----初期表示時");
        	// 初期表示時
            request.setAttribute("txtGoods", "初期値");
        }else{
        	// 2回目以降
            request.setAttribute("txtGoods", request.getParameter("txtGoods"));
        }
        // 個数
        if(request.getParameter("txtNumber")==null){
        	// 初期表示時
            request.setAttribute("txtNumber", 1);
        }else if(request.getParameter("txtNumber")==""){
        	// 2回目以降 かつ 未入力
        	request.setAttribute("txtNumber", 2);
        }else {
        	// 2回目以降 かつ 入力値あり
            request.setAttribute("txtNumber", request.getParameter("txtNumber"));
        }
        // memo
        if(request.getParameter("txtMemo")==null){
        	// 初期表示時
            request.setAttribute("txtMemo", "");
        }else if(request.getParameter("txtMemo")==""){
        	// 2回目以降 かつ 未入力
        	request.setAttribute("txtMemo", "未入力");
        }else {
        	// 2回目以降 かつ 入力値あり
            request.setAttribute("txtMemo", request.getParameter("txtMemo"));
        }

	}

	/**
	 * ボタン判別
	 */
	private void checkButten(HttpServletRequest request){

		System.out.println("---------------------------------------------------------");
		System.out.println("ボタン判別");
		System.out.println("ボタンvalue = " + request.getParameter("a"));

		if(request.getParameter("a") == "cart1"){
			System.out.println("if内");
			request.setAttribute("txtMemo", "カート");
		}

	}

}
