package HitBlow;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class hitBlow extends JFrame implements ActionListener {
/*
 *  ・ボタンを連打すると止まる
 *  ・もう一度をできるようにする
 *  ・終了時、結果ラベルの横幅が増すからかレイアウトがゆがむ
 *  ・もしかして乱数は毎回変わらない･･･？(乱数取得を初期処理に入れたら実現できそう)
*/

	// 変数
	int arr_rand[] = {0,0,0};		// ランダム変数を入れる配列
	String arr_randWork[] = {"■","■","■"};	// ランダム変数を入れるワーク用配列
	int arr_textBox[] = {7,7,7};	// テキストボックスの値を入れる配列

	int int_hit = 0;		// hit回数カウント用変数
	int int_blow = 0;		// blow回数カウント用変数

	int int_frg1 = 0;		// Hitカウントをするはどうかの判定用(左)
	int int_frg2 = 0;		// Hitカウントをするはどうかの判定用(真ん中)
	int int_frg3 = 0;		// Hitカウントをするはどうかの判定用(右)
	int int_endFrg = 0;

	String str_botton = "確認";
	String str_result;		// 結果表示用

	// 定数
	final String STR_RURE = "<html>三桁の数字を当ててください。"
			+ "<br>入力した数字が答えの中にあり、場所もあっている場合にはHitとして数えます。"
			+ "<br>入力した数字が答えの中にあるが、場所はあってない場合Blowとして数えます。"
			+ "<br>3つの数字すべてがHitになったら、ゲームクリアです。"
			+ "<br>・1つの数字は1～6の範囲を持つ"
			+ "<br>・数字はそれぞれ別であり、重複しない"
			+ "<br>・Hitしない数字は毎回変わる<br>";

	// 部品
    GridBagConstraints gbc = new GridBagConstraints();


    // テキストボックスとボタンの生成
    JTextField text1 = new JTextField(3);		// テキストボックス 数字左
    JTextField text2 = new JTextField(3);		// テキストボックス 数字真ん中
    JTextField text3 = new JTextField(3);		// テキストボックス 数字右
    JButton button = new JButton(str_botton);

    // ラベルの生成
    JLabel label_rule = new JLabel();			// ラベル ルール表示用
    JLabel label1 = new JLabel();				// ラベル 数字左
    JLabel label2 = new JLabel();				// ラベル 数字真ん中
    JLabel label3 = new JLabel();				// ラベル 数字右
    JLabel label_rusult = new JLabel();	// ラベル 結果表示用


    JLabel textlabel1 = new JLabel();			// ラベル 数字左
    JLabel textlabell2 = new JLabel();			// ラベル 数字真ん中
    JLabel textlabel3 = new JLabel();			// ラベル 数字右



	public static void main(String args[]){

	    hitBlow frame = new hitBlow("Hit&Blow");
	    frame.setVisible(true);

	}

	// 初期表示
	public hitBlow(String title){

	    setTitle(title);
	    setBounds(100, 100, 600, 400);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    GridBagLayout layout = new GridBagLayout();
	    JPanel p = new JPanel();
	    p.setLayout(layout);

	    button.addActionListener(this);


	    // 配置
		label_rule.setText(STR_RURE);
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth  = 3;
	    gbc.weightx = 1.0d;
	    gbc.weighty = 1.0d;
	    layout.setConstraints(label_rule, gbc);

	    label1.setText(arr_randWork[0]);
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth  = 1;
	    gbc.weightx = 1.0d;
	    gbc.weighty = 1.0d;
	    layout.setConstraints(label1, gbc);

	    label2.setText(arr_randWork[1]);
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    gbc.gridwidth  = 1;
	    gbc.weightx = 1.0d;
	    gbc.weighty = 1.0d;
	    layout.setConstraints(label2, gbc);

	    label3.setText(arr_randWork[2]);
	    gbc.gridx = 2;
	    gbc.gridy = 1;
	    gbc.gridwidth  = 1;
	    gbc.weightx = 1.0d;
	    gbc.weighty = 1.0d;
	    layout.setConstraints(label3, gbc);


	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.gridwidth  = 1;
	    gbc.weightx = 1.0d;
	    gbc.weighty = 1.0d;
	    layout.setConstraints(text1, gbc);

	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    gbc.gridwidth  = 1;
	    gbc.weightx = 1.0d;
	    gbc.weighty = 1.0d;
	    layout.setConstraints(text2, gbc);

	    gbc.gridx = 2;
	    gbc.gridy = 2;
	    gbc.gridwidth  = 1;
	    gbc.weightx = 1.0d;
	    gbc.weighty = 1.0d;
	    layout.setConstraints(text3, gbc);


	    label_rusult.setText("<html>Hit :" + int_hit + "回"
	    		+ "<br>Blow :" + int_blow + "回");
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.gridwidth  = 1;
	    gbc.weightx = 1.0d;
	    gbc.weighty = 1.0d;
	    layout.setConstraints(label_rusult, gbc);


	    gbc.gridx = 2;
	    gbc.gridy = 3;
	    gbc.weightx = 1.0d;
	    gbc.weighty = 1.0d;
	    layout.setConstraints(button, gbc);


	    p.add(label_rule);
	    p.add(label1);
	    p.add(label2);
	    p.add(label3);
	    p.add(text1);
	    p.add(text2);
	    p.add(text3);
	    p.add(label_rusult);
	    p.add(button);

	    getContentPane().add(p, BorderLayout.CENTER);


	}

	// ボタン押下時
	  public void actionPerformed(ActionEvent e){
		  // 乱数取得
		  for(int i = 0 ; i < arr_rand.length ; i++) {
		        do {
		            arr_rand[i] = (int)(Math.random()*6)+1;
		        } while (randCheck(arr_rand[i],i));
		  }

		  // テキストボックスの値を取得
		  // テキストボックスに未入力なら0にする

		  // 左のテキストボックス
		  if("".equals(text1.getText())) {
			  arr_textBox[0] = 0;
		  }else {
			  arr_textBox[0] = Integer.parseInt(text1.getText());
		  }
		  // 真ん中のテキストボックス
		  if("".equals(text2.getText())) {
			  arr_textBox[1] = 0;
		  }else {
			  arr_textBox[1] = Integer.parseInt(text2.getText());
		  }
		  // 右のテキストボックス
		  if("".equals(text3.getText())) {
			  arr_textBox[2] = 0;
		  }else {
			  arr_textBox[2] = Integer.parseInt(text3.getText());
		  }

		  // ランダム変数とテキストボックスの値を比較
		  // 　この中にHit時の処理とBlow時の処理
		  for(int i = 0 ; i < arr_rand.length ; i++) {
			  int t = 0;
			  if(arr_rand[i] == arr_textBox[i]) {
				  // Hit
				  // テキストボックスを触れなくする
				  switch (i) {
				  case 0:
					  if(int_frg1 == 0) {
						  // カウントアップ
						  hitProces();
						  text1.setEditable(false);
						  int_frg1 = 1;
					  }
				      break;
				  case 1:
					  if(int_frg2 == 0) {
						  // カウントアップ
						  hitProces();
						  text2.setEditable(false);
						  int_frg2 = 1;
					  }
				      break;
				  case 2:
					  if(int_frg3 == 0) {
						  // カウントアップ
						  hitProces();
						  text3.setEditable(false);
						  int_frg3 = 1;
					  }
				      break;
				  }
				  // Hitした数字をラベルに入れる
				  arr_randWork[i] = Integer.toString(arr_rand[i]);
			  }else {
				  // Blow
				  // カウントアップ
				  switch (i) {
				  // 左のラベルと直下以外のテキストボックスを比較
				  case 0:
					  if(arr_rand[i] == arr_textBox[t+1] || arr_rand[i] == arr_textBox[t+2]){
						  blowProces();
					  }
				      break;
				  // 真ん中のラベルと直下以外のテキストボックスを比較
				  case 1:
					  if(arr_rand[i] == arr_textBox[t] || arr_rand[i] == arr_textBox[t+2]){
						  blowProces();
					  }
				      break;
				  // 右のラベルと直下以外のテキストボックスを比較
				  case 2:
					  if(arr_rand[i] == arr_textBox[t] || arr_rand[i] == arr_textBox[t+1]){
						  blowProces();
					  }
				      break;
				  }
			  }
		  }

		  // 回数を反映
		  str_result = ("<html>Hit :" + int_hit + "回"
				  	  + "<br>Blow :" + int_blow + "回");

		  if(int_endFrg == 1) {
			  System.exit(0);
		  }

		  // 全部Hitしたら(int_hit = 3)最終表示にする
		  if(int_hit == 3){
			  // ボタンのテキストを変える
			  button.setText("終了");
			  // 結果ラベルのテキストを変える
			  str_result = ("<html>Hit :" + int_hit + "回、Blow :" + int_blow + "回"
					  	  + "<br>おめでとうございます！");
			  int_endFrg = 1;
		  }

		  hitBlow2("Hit&Blow");

	  }

	  // 重複チェック　引数(乱数,格納する配列キー) 戻り値(重複の有無)
	  public Boolean randCheck(int rand,int key) {
		  Boolean result=false;		// ture：重複している、false：重複していない

		  // keyが1なら配列0の中身と比較
		  if (key == 1) {
			  if(arr_rand[0] == rand) {
				  result = true;
			  }else {
				  result = false;
			  }

		  }else if(key == 2){
			  if(arr_rand[0] == rand) {
				  result = true;
			  }else {
				  if(arr_rand[1] == rand) {
					  result = true;
				  }else {
					  result = false;
				  }
			  }
		  }

		  return result;
	  }

	  // Hit処理　引数(なし) 戻り値(なし)
	  public void hitProces() {
		  // 回数をカウントアップ
		  int_hit += 1;
	  }

	  // Blow処理　引数(なし) 戻り値(なし)
	  public void blowProces() {
		  // 回数をカウントアップ
		  int_blow += 1;
	  }

		// 押下時表示変更処理 引数(String タイトル) 戻り値(なし)
		public void hitBlow2(String title){

		    setTitle(title);
		    setBounds(100, 100, 600, 400);
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		    GridBagLayout layout = new GridBagLayout();
		    JPanel p = new JPanel();
		    p.setLayout(layout);

		    // 配置
			label_rule.setText(STR_RURE);
		    gbc.gridx = 0;
		    gbc.gridy = 0;
		    gbc.gridwidth  = 3;
		    gbc.weightx = 1.0d;
		    gbc.weighty = 1.0d;
		    layout.setConstraints(label_rule, gbc);

		    label1.setText(arr_randWork[0]);
		    gbc.gridx = 0;
		    gbc.gridy = 1;
		    gbc.gridwidth  = 1;
		    gbc.weightx = 1.0d;
		    gbc.weighty = 1.0d;
		    layout.setConstraints(label1, gbc);

		    label2.setText(arr_randWork[1]);
		    gbc.gridx = 1;
		    gbc.gridy = 1;
		    gbc.gridwidth  = 1;
		    gbc.weightx = 1.0d;
		    gbc.weighty = 1.0d;
		    layout.setConstraints(label2, gbc);

		    label3.setText(arr_randWork[2]);
		    gbc.gridx = 2;
		    gbc.gridy = 1;
		    gbc.gridwidth  = 1;
		    gbc.weightx = 1.0d;
		    gbc.weighty = 1.0d;
		    layout.setConstraints(label3, gbc);


		    gbc.gridx = 0;
		    gbc.gridy = 2;
		    gbc.gridwidth  = 1;
		    gbc.weightx = 1.0d;
		    gbc.weighty = 1.0d;
		    layout.setConstraints(text1, gbc);

		    gbc.gridx = 1;
		    gbc.gridy = 2;
		    gbc.gridwidth  = 1;
		    gbc.weightx = 1.0d;
		    gbc.weighty = 1.0d;
		    layout.setConstraints(text2, gbc);

		    gbc.gridx = 2;
		    gbc.gridy = 2;
		    gbc.gridwidth  = 1;
		    gbc.weightx = 1.0d;
		    gbc.weighty = 1.0d;
		    layout.setConstraints(text3, gbc);


		    label_rusult.setText(str_result);
		    gbc.gridx = 0;
		    gbc.gridy = 3;
		    gbc.gridwidth  = 1;
		    gbc.weightx = 1.0d;
		    gbc.weighty = 1.0d;
		    layout.setConstraints(label_rusult, gbc);


		    gbc.gridx = 2;
		    gbc.gridy = 3;
		    gbc.weightx = 1.0d;
		    gbc.weighty = 1.0d;
		    layout.setConstraints(button, gbc);


		    p.add(label_rule);
		    p.add(label1);
		    p.add(label2);
		    p.add(label3);
		    p.add(text1);
		    p.add(text2);
		    p.add(text3);
		    p.add(label_rusult);
		    p.add(button);

		    getContentPane().add(p, BorderLayout.CENTER);
		}
}
