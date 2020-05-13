package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DbDelivery
 */
@WebServlet("/test/DbDelivery")
public class DbDelivery extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbDelivery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    prepData(request);

        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/test/DbDeliveryJsp.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void prepData(HttpServletRequest request){

		String	txtGoods;
		String	txtNumber;

		// 商品名
        if(request.getParameter("txtGoods")==null){
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

		txtGoods = request.getParameter("txtGoods");
		txtNumber = request.getParameter("txtNumber");

		System.out.println("txtGoods = " + txtGoods + "、txtNumber = " + txtNumber);
	}

}
