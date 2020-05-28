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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int number;

		ShoppingListTable table = new ShoppingListTable();
		Goods goods = new Goods();

		// 一覧表示法の未購入のレコード取得
		List<String> arraySelectResult = new ArrayList<String>();
		arraySelectResult = table.getAllNotYetPurchesed();

		// 個数が未入力の時、0(int)を入れる
		if(request.getParameter("txtNumber") == null) {
			number = toZero(request.getParameter("txtNumber"));
		}else{
			number = Integer.parseInt(request.getParameter("txtNumber"));
		}

		// テキストボックスの初期表示
		request.setAttribute("hidUpdateUuid", "");
		request.setAttribute("txtGoods", "");
		request.setAttribute("txtNumber", 0);
		request.setAttribute("txtMemo", "");

		// 各ボタンのvalueを取得
		String btnRegisterVal = request.getParameter("btnRegister");
		String setBtnPurchaseVal = request.getParameter("setBtnPurchase");
		String setBtnDeleteVal = request.getParameter("setBtnDelete");
		String setBtnUpdateVal = request.getParameter("setBtnUpdate");

		// 初期表示時は行わない
		if(btnRegisterVal != null || setBtnPurchaseVal != null || setBtnDeleteVal != null || setBtnUpdateVal != null) {

			// nullの場合""を入れる
			if(btnRegisterVal == null) {
				btnRegisterVal = "";
			}
			if(setBtnPurchaseVal == null) {
				setBtnPurchaseVal = "";
			}
			if(setBtnDeleteVal == null) {
				setBtnDeleteVal = "";
			}
			if(setBtnUpdateVal == null) {
				setBtnUpdateVal = "";
			}

			// 押下ボタンによって処理を分ける
			if(btnRegisterVal.equals("register")){			// 登録ボタン

				if(request.getParameter("hidUpdateUuid") == "") {
					// 登録
					registerGoods(request.getParameter("txtGoods"), number, request.getParameter("txtMemo"));

				}else {
					// 更新
					updateGoods(request.getParameter("hidUpdateUuid"), request.getParameter("txtGoods"), number, request.getParameter("txtMemo"));
					// 非表示UUIDをクリア
					request.setAttribute("hidUpdateUuid", "");

				}
			}else if(setBtnPurchaseVal.equals("btnPurchase")) {	// 購入済ボタン

				purchaseGoods(request.getParameter("setUuid"));

			}else if(setBtnDeleteVal.equals("btnDelete")) {		// 削除ボタン

				deleteGoods(request.getParameter("setUuid"));

			}else if(setBtnUpdateVal.equals("btnUpdate")) {		// 修正ボタン
				// テキストボックス表示
				request.setAttribute("hidUpdateUuid", request.getParameter("setUuid"));		// uuid
				request.setAttribute("txtGoods", request.getParameter("setName"));			// 商品名
				request.setAttribute("txtNumber", request.getParameter("setNumber"));		// 個数
				request.setAttribute("txtMemo", request.getParameter("setMemo"));			// メモ
			}
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
		doGet(request, response);
	}

	/**
	 * Registers or update when the purchased button is pressed.
	 *
	 * @param name the name at which to insert or update
	 * @param number  the number at which to insert or update
	 * @param memo  the memo at which to insert or update
	 */
	public void registerGoods(String name, int number, String memo) {

		Goods goods = new Goods();
		ShoppingListTable table = new ShoppingListTable();

		// 画面の値をセット
		goods.name 		= name;
		goods.number 	= number;
		goods.memo 		= memo;

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
		Goods goodsre = table.add(goods);

	}

	/**
	 * @param uuid the uuid at which to insert or update
	 * @param name the name at which to insert or update
	 * @param number the number at which to insert or update
	 * @param memo the memo at which to insert or update
	 */
	public void updateGoods(String uuid, String name, int number, String memo) {

		Goods goods = new Goods();
		ShoppingListTable table = new ShoppingListTable();

		// 画面の値をセット
		goods.uuid 		= uuid;
		goods.name 		= name;
		goods.number 	= number;
		goods.memo 		= memo;

		// 現在日時を取得
		LocalDateTime date1 = LocalDateTime.now();
		DateTimeFormatter dtformat1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String fdate1 = dtformat1.format(date1);

		goods.purchased_datetime 	= "";
		goods.updated_datetime 	= String.valueOf(fdate1);

		// 更新
		table.update(goods);
	}

	/**
	 * @param uuid the uuid at which to select
	 */
	public void deleteGoods(String uuid) {

		ShoppingListTable table = new ShoppingListTable();

		// 削除
		table.delete(uuid);

	}

	/**
	 * @param uuid the uuid at which to select
	 */
	public void purchaseGoods(String uuid) {

		Goods goods = new Goods();
		ShoppingListTable table = new ShoppingListTable();

		// 画面の値をセット
		goods.uuid = uuid;

		// 現在日時を取得
		LocalDateTime date1 = LocalDateTime.now();
		DateTimeFormatter dtformat1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String fdate1 = dtformat1.format(date1);

		goods.purchased_datetime 	= String.valueOf(fdate1);

		// 更新
		table.update(goods);

	}

	/**
	 * @param uuid the uuid at which to select
	 */
	public int toZero(String number) {

		int result = 0;
		if(number != null && number != "") {
			result = Integer.parseInt(number);
		}

		return result;
	}
}

