package shoppinglist;

/**
 * Operate the table SHOPPING_LIST
 *
 * @author onodera
 * @version バージョン 2020/05/DD 新規作成
 */
public class Goods {

	/** UUID */
	String uuid;
	/** 商品名 */
	String name;
	/** 個数 */
	int number;
	/** メモ */
	String memo;
	/** 登録日時 */
	String registered_datetime;
	/** 購入日時 */
	String purchased_datetime;
	/** 更新日時 */
	String updated_datetime;

	/**
	 * default constructor
	 */
	void goods() {


	}

	/**
	 * @return uuid the uuid to hold
	 */
	public String gatUuid() {


		return uuid;
	}

	/**
	 * @return item the item to hold
	 */
	public String gatItem() {

		return name;
	}

	/**
	 * @return number the number to hold
	 */
	public int gatNumber() {

		return number;
	}

	/**
	 * @return memo the memo to hold
	 */
	public String getMemo() {

		return memo;
	}

	/**
	 * @return registered_datetime the registered_datetime to hold
	 */
	public String getRegistered_datetime() {

		return registered_datetime;
	}

	/**
	 * @return purchased_datetime the purchased_datetime to hold
	 */
	public String getPurchased_datetime() {

		return purchased_datetime;
	}

	/**
	 * @return updated_datetime the updated_datetime to hold
	 */
	public String getUpdated_datetime() {

		return updated_datetime;
	}
}
