package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
@WebServlet("/test/DbSelect3")
public class DbSelect3 extends HttpServlet {

	static final String URL = "jdbc:mysql://localhost/user_db?autoReconnect=true&useSSL=false";
    static final String USERNAME = "root";
    static final String PASSWORD = "aiaiaiai";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

// --------- 初期表示テスト用 --------------------------------------------------------------------------------------

		// インスタンス生成
		Table table = new Table();

		List<String> arraySelectResult = new ArrayList<String>();
//		arraySelectResult = table.getAllNotYetPurchesed();

		// 初期表示でなければ処理する
		if(request.getParameter("txtGoods")!=null){


// ----------- 登録ボタン押下 --------------------------------------------------------------------------------------------------------
// メソッドに分けなければ以下で登録ボタン押下時の処理ができた
			Goods goods = new Goods();
			// 画面の値を取得
			goods.uuid 					= request.getParameter("hidUpdateUuid");
			goods.item 					= request.getParameter("txtGoods");
			goods.number 				= Integer.parseInt(request.getParameter("txtNumber"));
			goods.memo 					= request.getParameter("txtMemo");

			//
			// 現在日時を取得
			LocalDateTime date1 = LocalDateTime.now();
			DateTimeFormatter dtformat1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			String fdate1 = dtformat1.format(date1);

			System.out.println("goods.uuid = " + goods.uuid);
			System.out.println("goods.item = " + goods.item);
			System.out.println("goods.number = " + goods.number);
			System.out.println("goods.memo = " + goods.memo);

//			goods.uuid 					= "c829f11d-535b-45e4-bdb7-556af23a4687";
//
			if(goods.uuid == null) {
				System.out.println("とうろく");
//				// 自動採番
//				UUID uuid = UUID.randomUUID();
//				String str = uuid.toString();
//
//				goods.uuid 					= str;
//				goods.registered_datetime 	= String.valueOf(fdate1);
//
//				// 実行
//				Goods goodsre = table.add(goods);
//
			}else {
				System.out.println("更新");
//				// 購入日時でボタンを分けているので登録ボタン押下時は購入日時に""を入れること
//				goods.purchased_datetime 	= "";						// 登録ボタン押下時の処理用
//				goods.updated_datetime 		= String.valueOf(fdate1);
//
//				// 実行
//				table.update(goods);
//
//				// 非表示UUIDをクリア
//
			}



			// インスタンス生成
//			Table table = new Table();
//
//// --------- Tableクラスメソッド実行用 --------------------------------------------------------------------------------------
//			// ■getAll用
//			List<String> arraySelectResult = new ArrayList<String>();
//			arraySelectResult = table.getAll();
//			// 結果の確認
//			for (int i=0; i<arraySelectResult.size(); ++i)
//			{
//			    System.out.println(arraySelectResult.get(i));
//			}
//			request.setAttribute("list", arraySelectResult);

//			// ■getAllNotYetPurchesed用
//			List<String> arraySelectResult = new ArrayList<String>();
//			arraySelectResult = table.getAllNotYetPurchesed();
//			// 結果の確認
//			for (int i=0; i<arraySelectResult.size(); ++i)
//			{
//			    System.out.println(arraySelectResult.get(i));
//			}

//			// ■get()用
//			Goods goods = table.get("001"); // ②Goodsを返すSelect
//			// 結果の確認
//	        System.out.println("uuid = " + goods.uuid);
//	        System.out.println("item = " + goods.item);
//	        System.out.println("number = " + goods.number);
//	        System.out.println("memo = " + goods.memo);
//	        System.out.println("registered_datetime = " + goods.registered_datetime);
//	        System.out.println("purchased_datetime = " + goods.purchased_datetime);
//	        System.out.println("updated_datetime = " + goods.updated_datetime);

//			// ■add用
//			Goods goods = new Goods();
//			// 現在日時を取得
//			LocalDateTime date1 = LocalDateTime.now();
//			DateTimeFormatter dtformat1 =
//					DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//			String fdate1 = dtformat1.format(date1);
//			System.out.println("現在日時　= " + fdate1);
//			// 自動採番
//			UUID uuid = UUID.randomUUID();
//			String str = uuid.toString();
//
//			goods.uuid 					= str;
//			goods.item 					= "生クリーム";
//			goods.number 				= 500;
//			goods.memo 					= "植物性";
//			goods.registered_datetime 	= String.valueOf(fdate1);
//
//			// 実行
//			Goods goodsre = table.add(goods);
//			// 結果の確認
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//	        System.out.println("uuid = " + goodsre.uuid);
//	        System.out.println("item = " + goodsre.item);
//	        System.out.println("number = " + goodsre.number);
//	        System.out.println("memo = " + goodsre.memo);
//	        System.out.println("registered_datetime = " + goodsre.registered_datetime);
//	        System.out.println("purchased_datetime = " + goodsre.purchased_datetime);
//	        System.out.println("updated_datetime = " + goodsre.updated_datetime);

//			// ■update用
			// 購入日時でボタンを分けているので登録ボタン押下時は購入日時に""を入れること
//			Goods goods = new Goods();
//			// 現在日時を取得
//			LocalDateTime date1 = LocalDateTime.now();
//			DateTimeFormatter dtformat1 =
//					DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//			String fdate1 = dtformat1.format(date1);
//			System.out.println("現在日時　= " + fdate1);
//
//			goods.uuid 					= "111";
//			goods.item 					= "牛乳";
//			goods.number 				= 400;
//			goods.memo 					= "低脂肪";
//			goods.registered_datetime 	= String.valueOf(fdate1);
//			goods.purchased_datetime 	= "";						// 登録ボタン押下時の処理用
////			goods.purchased_datetime 	= String.valueOf(fdate1);	// 購入済ボタン押下時の処理用
//			goods.updated_datetime 		= String.valueOf(fdate1);
//
//			// 実行
//			table.update(goods);

//			// ■Delete用
//			table.delete("111");

// -------------------------------------------------------------------------------------------------------------------
// --------- 共有クラステスト用 --------------------------------------------------------------------------------------

//			String sql = "SELECT * FROM goodsmaster;";							// ①全件(複数行)取得のSelect文
//			String sql = "SELECT * FROM goodsmaster Where ID = 0 ;";			// ②1件取得のSelect文
//			String sql = "INSERT INTO goodsmaster(ID,name,price) VALUES (3,'orange',90)"; // ③Insert文
//			String sql = "UPDATE goodsmaster SET name = 'budou' WHERE ID = 3";	// ③Update文
//			String sql = "DELETE FROM goodsmaster WHERE ID = 3";				// ③Delete文

//			table.selectDbList(sql);		// ①リストを返すSelect

//			Goods goods = table.selectDbGoods(sql); // ②Goodsを返すSelect
//	        System.out.println(goods.uuid);
//	        System.out.println(goods.item);

//			table.sqlDb(sql);						// ③更新追加削除用

// -------------------------------------------------------------------------------------------------------------------
//			dbSelect();
        }

		// 一覧再描画用
		arraySelectResult = table.getAllNotYetPurchesed();
		request.setAttribute("list", arraySelectResult);

		request.setAttribute("hidUpdateUuid","かくし");

        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/test/DbtestSelect3.jsp");
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

//        String sql = "SELECT * FROM goodsmaster Where ID = 0 ;";
        String sql = "SELECT * FROM goodsmaster;";

        // リスト
        List<String> arraySelectResult2 = new ArrayList<String>();

        // DB接続をします。
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

        try (
        	Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        	PreparedStatement statement = connection.prepareStatement(sql); ) {

        	// resultの中に結果を取得します。
			ResultSet result = statement.executeQuery();

			System.out.println("start");
			int cnt = 0;
			while (result.next()) {
				for(int i = 1; i < 4; i++) {
					// リストの最後に結果をいれていく
					arraySelectResult2.add(result.getString(i));
					System.out.println(arraySelectResult2.get(i-1));
				}
				cnt = cnt + 1;
			}

			System.out.println("------------------------------------------------");
			for(int i = 1; i < arraySelectResult2.size() ; i++) {
				System.out.println("i = " + i + "- 1 、中身 = " +arraySelectResult2.get(i-1));
			}
			System.out.println("------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("処理が完了しました");
        }

    }
}