package shoppinglist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Actionクラス
 * テーブルSHOPPING_LISTの操作を行う
 *
 * @author onodera
 * @version バージョン 2020/05/DD 新規作成
 */
public class ShoppingListTable {

	static final String URL = "jdbc:mysql://localhost/user_db?autoReconnect=true&useSSL=false";
    static final String USERNAME = "root";
    static final String PASSWORD = "aiaiaiai";
    static final String TABLENAME = "shopping_list";

	/**
	 * @return テーブルSHOPPING_LISTの全レコード
	 */
	public List<String> getAll() {

		String sql = "SELECT * "
				   + "FROM " + TABLENAME + ";";
		List<String> arraySelectResult = new ArrayList<String>();
		arraySelectResult = selectDbList(sql);

		return arraySelectResult;
	}

	/**
	 * @return テーブルSHOPPING_LISTの未購入のレコード
	 */
	public List<String> getAllNotYetPurchesed() {

		String sql = "SELECT * "
				   + "FROM " + TABLENAME
				   + " WHERE purchased_datetime IS NULL "
				   + "ORDER BY registered_datetime ASC;";
		List<String> arraySelectResult = new ArrayList<String>();
		arraySelectResult = selectDbList(sql);

		return arraySelectResult;
	}

	/**
	 * @param uuid 検索条件のuuid
	 * @return テーブルSHOPPING_LISTの引数に紐づくレコード
	 */
	public Goods get(String uuid) {

		String sql = "SELECT * FROM " + TABLENAME + " Where UUID = " + uuid + " ;";
		Goods goods = selectDbGoods(sql);
		return goods;

	}

	/**
	 * @param uuid 追加するレコード
	 * @return 画面一覧更新用レコード
	 */
	public Goods add(Goods goods) {

		String sql = "INSERT INTO " + TABLENAME + "(UUID,ITEM,NUMBER,MEMO,REGISTERED_DATETIME,PURCHASED_DATETIME,UPDATED_DATETIME) "
					+ "VALUES ('" + goods.uuid + "','"
								  + goods.item + "',"
								  + goods.number + ",'"
								  + goods.memo + "',cast('"
								  + goods.registered_datetime + "' as datetime),"
								  + goods.purchased_datetime + ","
								  + goods.updated_datetime + ");";
		System.out.println("SQL = " + sql);
		sqlDb(sql);

		return goods;
	}

	/**
	 * @param uuid 更新するレコード
	 */
	public void update(Goods goods) {

		String sql;

		// 購入日時に値がない場合、登録ボタン押下時の更新
		if(goods.purchased_datetime.equals("")) {
			// 登録ボタン押下時のUpdate文
			sql = "UPDATE " + TABLENAME
					+ " SET "+ "ITEM = '" + goods.item
							+ "',NUMBER = " + goods.number
							+ ",MEMO = '" + goods.memo
							+ "',UPDATED_DATETIME = cast('" + goods.updated_datetime
							+ "' as datetime)"
					+ " WHERE UUID = '" + goods.uuid + "';";
		}else {
			// 購入済みボタン押下時のUpdate文
			sql = "UPDATE " + TABLENAME
					+ " SET " + "PURCHASED_DATETIME = cast('" + goods.purchased_datetime
							 + "' as datetime)"
					+ " WHERE UUID = '" + goods.uuid + "';";
		}
	}

	/**
	 * @param uuid 検索条件のuuid
	 */
	public void delete(String uuid) {
		String sql = "DELETE FROM " + TABLENAME + " WHERE UUID = " + uuid;
		System.out.println("SQL = " + sql);
		sqlDb(sql);
	}

	/**
	 * 結果をlistで返す
	 *
	 * @param 実行するSelect文
	 * @return テーブルSHOPPING_LISTの全レコード
	 */
	public List<String> selectDbList(String sql) {

        List<String> arraySelectResult = new ArrayList<String>();

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

			int cnt = 0;
			while (result.next()) {
				for(int i = 1; i < 8; i++) {
					// リストの最後に結果をいれていく
					arraySelectResult.add(result.getString(i));
				}
				cnt = cnt + 1;
			}

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

		return arraySelectResult;
	}

	/**
	 * 結果をGoodsで返す
	 *
	 * @param 実行するSelect文
	 * @return テーブルSHOPPING_LISTの全レコード
	 */
	public Goods selectDbGoods(String sql) {

		Goods goods = new Goods();

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

			while (result.next()) {
				// Goodsに入れる
				goods.uuid 					= result.getString(1);
				goods.item 					= result.getString(2);
				goods.number 				= result.getInt(3);
				goods.memo 					= result.getString(4);
				goods.registered_datetime 	= result.getString(5);
				goods.purchased_datetime 	= result.getString(6);
				goods.updated_datetime 		= result.getString(7);
			}

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

	return goods;
	}

	/**
	 * 追加更新削除用SQL実行
	 *
	 * @param 実行するSql文
	 */
	public void sqlDb(String sql) {

		// DB接続をします。
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

        try (
        	Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        	PreparedStatement statement = connection.prepareStatement(sql); ) {

        	connection.setAutoCommit(false);

        	statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
