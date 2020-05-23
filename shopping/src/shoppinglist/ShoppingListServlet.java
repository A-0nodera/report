package shoppinglist;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
@WebServlet("/shoppinglist/ShoppingListServlet")
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

		ShoppingListTable table = new ShoppingListTable();
		Goods goods = new Goods();

		// 一覧表示法の未購入のレコード取得
		List<String> arraySelectResult = new ArrayList<String>();
		arraySelectResult = table.getAllNotYetPurchesed();

		// 画面の値を取得
		goods.uuid 					= request.getParameter("hidUpdateUuid");
		goods.item 					= request.getParameter("txtGoods");
		goods.number 				= Integer.parseInt(request.getParameter("txtNumber"));
		goods.memo 					= request.getParameter("txtMemo");
		// 後で消す
		goods.uuid 					= "c829f11d-535b-45e4-bdb7-556af23a4687";


		// 個数が未入力の時、0(int)を入れる
		number = toZero(request.getParameter("txtNumber"));

		// 押下ボタンによって処理を分ける
		if(request.getParameter("btnRegister") != null){			// 登録ボタン
			System.out.println("登録");

			if(goods.uuid == null) {
				// 登録
				registerGoods(goods.item, goods.number, goods.memo);
			}else {
				// 更新

				// 非表示UUIDをクリア
				request.setAttribute("hidUpdateUuid","");

			}



		}else if(request.getParameter("btnPurchase") != null) {		// 購入済ボタン
			System.out.println("購入済");

		}else if(request.getParameter("btnUpdate") != null) {		// 修正ボタン
			System.out.println("修正");
			updateGoods(request.getParameter("hidUpdateUuid"), request.getParameter("txtGoods"), Integer.parseInt(request.getParameter("txtNumber")), request.getParameter("txtMemo"));

		}else if(request.getParameter("btnDelete") != null) {		// 削除ボタン
			System.out.println("削除");

		}

		// 一覧再描画用
		arraySelectResult = table.getAllNotYetPurchesed();
		request.setAttribute("list", arraySelectResult);

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

		Goods goods = new Goods();
		// 現在日時を取得
		LocalDateTime date1 = LocalDateTime.now();
		DateTimeFormatter dtformat1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String fdate1 = dtformat1.format(date1);


		// 自動採番
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();

		goods.uuid 					= str;
		goods.registered_datetime 	= String.valueOf(fdate1);

		// 実行
//		Goods goodsre = table.add(goods);


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

