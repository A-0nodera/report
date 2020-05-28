package shoppinglist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Operate the table SHOPPING_LIST
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
	 * @return goods all records in table SHOPPING_LIST
	 */
	public List<String> getAll() {

		String sql = "SELECT * "
				   + "FROM " + TABLENAME + ";";
		List<String> goods = new ArrayList<String>();
		goods = selectDbList(sql);

		return goods;
	}

	/**
	 * @return goods the unpurchased records in table SHOPPING_LIST
	 */
	public List<String> getAllNotYetPurchesed() {

		String sql = "SELECT * "
				   + "FROM " + TABLENAME
				   + " WHERE purchased_datetime IS NULL "
				   + "ORDER BY registered_datetime ASC;";
		List<String> goods = new ArrayList<String>();
		goods = selectDbList(sql);

		return goods;
	}

	/**
	 * @param uuid the uuid of search condition, not null
	 * @return goods record associated with the argument of table SHOPPING_LIST
	 */
	public Goods get(String uuid) {

		String sql = "SELECT * FROM " + TABLENAME + " Where UUID = " + uuid + " ;";
		Goods goods = selectDbGoods(sql);
		return goods;

	}

	/**
	 * @param uuid the uuid of the record to add, not null
	 * @return the record for screen list update
	 */
	public Goods add(Goods goods) {

		String sql = "INSERT INTO " + TABLENAME + "(UUID, ITEM, NUMBER, MEMO, REGISTERED_DATETIME, PURCHASED_DATETIME, UPDATED_DATETIME) "
					+ "VALUES ('" + goods.uuid + "', '"
								  + goods.name + "', "
								  + goods.number + ", '"
								  + goods.memo + "', cast('"
								  + goods.registered_datetime + "' as datetime), "
								  + goods.purchased_datetime + ", "
								  + goods.updated_datetime + ");";

		sqlDb(sql);

		return goods;
	}

	/**
	 * @param uuid the record to update, not null
	 */
	public void update(Goods goods) {
		String sql;

		// 購入日時に値がない場合、登録ボタン押下時の更新
		if(goods.purchased_datetime.equals("")) {
			// 登録ボタン押下時のUpdate文
			sql = "UPDATE " + TABLENAME
					+ " SET " + "ITEM = '" + goods.name
							+ "', NUMBER = " + goods.number
							+ ", MEMO = '" + goods.memo
							+ "', UPDATED_DATETIME = cast('" + goods.updated_datetime
							+ "' as datetime)"
					+ " WHERE UUID = '" + goods.uuid + "';";
		}else {
			// 購入済みボタン押下時のUpdate文
			sql = "UPDATE " + TABLENAME
					+ " SET " + "PURCHASED_DATETIME = cast('" + goods.purchased_datetime
							 + "' as datetime)"
					+ " WHERE UUID = '" + goods.uuid + "';";
		}

		sqlDb(sql);
	}

	/**
	 * @param uuid the uuid of search condition, not null
	 */
	public void delete(String uuid) {
		String sql = "DELETE FROM " + TABLENAME + " WHERE UUID = '" + uuid + "'";

		sqlDb(sql);
	}

	/**
	 * the result as a list
	 *
	 * @param sql select statement to execute, not null
	 * @return arraySelectResult all records in table SHOPPING_LIST
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
		PreparedStatement statement = connection.prepareStatement(sql);
		){

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
	 * the result as a Goods
	 *
	 * @param sql select statement to execute, not null
	 * @return arraySelectResult all records in table SHOPPING_LIST
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
			PreparedStatement statement = connection.prepareStatement(sql);
		){

    		// resultの中に結果を取得します。
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				// Goodsに入れる
				goods.uuid 					= result.getString(1);
				goods.name 					= result.getString(2);
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
	 * Execute SQL for additional update deletion
	 *
	 * @param sql sql statement to execute, not null
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
			PreparedStatement statement = connection.prepareStatement(sql);
		){

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
