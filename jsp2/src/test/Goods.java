package test;

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
	public String gatUuid(String id) {
		// DBから取得した結果からuuidを戻り値の変数に入れる

		// .setAttribute()でjspにuuidを渡す？

		return uuid;
	}


}
