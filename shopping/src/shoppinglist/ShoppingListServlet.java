package shoppinglist;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Actionクラス
 *
 * @author onodera
 * @version バージョン 2020/05/DD 新規作成
 */
@WebServlet("/ShoppingListServlet")
public class ShoppingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		int number;

		// インスタンス生成
		Goods goods = new Goods();


		// 個数が未入力の時、0(int)を入れる
		number = toZero(request.getParameter("txtNumber"));

		// 押下ボタンによって処理を分ける
		if(request.getParameter("btnRegister") != null){			// 登録ボタン
			System.out.println("登録");
			registerGoods(request.getParameter("txtGoods"), number, request.getParameter("txtMemo"));

		}else if(request.getParameter("btnPurchase") != null) {		// 購入済ボタン
			System.out.println("購入済");

		}else if(request.getParameter("btnUpdate") != null) {		// 修正ボタン
			System.out.println("修正");
			updateGoods(request.getParameter("hidUpdateUuid"), request.getParameter("txtGoods"), Integer.parseInt(request.getParameter("txtNumber")), request.getParameter("txtMemo"));

		}else if(request.getParameter("btnDelete") != null) {		// 削除ボタン
			System.out.println("削除");

		}

        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/ShoppingList.jsp");
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
	 * @param name
	 * @param number
	 * @param memo
	 */
	public void registerGoods(String name, int number, String memo) {
		System.out.println("登録ux-----i");

	}

	/**
	 * @param uuid
	 * @param name
	 * @param number
	 * @param memo
	 */
	public void updateGoods(String uuid, String name, int number, String memo) {


	}

	/**
	 * @param uuid
	 */
	public void deleteGoods(String uuid) {


	}

	/**
	 * @param uuid
	 */
	public void purchaseGoods(String uuid) {


	}

	/**
	 * @param uuid
	 */
	public int toZero(String number) {

		int result = 0;
		if(number != null && number != "") {
			result = Integer.parseInt(number);
		}

		return result;
	}
}

