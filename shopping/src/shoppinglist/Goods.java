package shoppinglist;

/**
 * Actionクラス
 * テーブルSHOPPING_LISTの操作を行う
 *
 * @author onodera
 * @version バージョン 2020/05/DD 新規作成
 */
public class Goods {

	/** UUID */
	String uuid;
	/** UUID */
	String item;
	/** UUID */
	int number;
	/** UUID */
	String memo;
	/** UUID */
	String registered_datetime;
	/** UUID */
	String purchased_datetime;
	/** UUID */
	String updated_datetime;

	/**
	 * デフォルトコンストラクタ
	 */
	void goods() {


	}

	/**
	 * @return 画面のuuid
	 */
	public String gatUuid() {


		return uuid;
	}

	/**
	 * @return 画面の商品名
	 */
	public String gatItem() {

		return item;
	}

	/**
	 * @return 画面の個数
	 */
	public int gatNumber() {

		return number;
	}

	/**
	 * @return 画面のメモ
	 */
	public String getMemo() {

		return memo;
	}

	/**
	 * @return 画面の登録日時
	 */
	public String getRegistered_datetime() {

		return registered_datetime;
	}

	/**
	 * @return 画面の購入日時
	 */
	public String getPurchased_datetime() {

		return purchased_datetime;
	}

	/**
	 * @return 画面の更新日時
	 */
	public String getUpdated_datetime() {

		return updated_datetime;
	}
}
