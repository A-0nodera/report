<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="java.util.ArrayList" %>
	<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>買い物リスト</title>
	</head>
	<body>
		<form name = "form1" action="ShoppingListServlet" method="get">
			<%

		    request.setCharacterEncoding("Shift_JIS");
			ArrayList<String> arraySelectResult = new ArrayList<String>();
			arraySelectResult = (ArrayList<String>)request.getAttribute("list");


			String hidUpUuid = "";
		    String txtId = "";
			String txtGd = "";
			int txtNb = 0;
			String txtMm = "";

			txtId = request.getAttribute("hidUpdateUuid").toString();
			txtGd = request.getAttribute("txtGoods").toString();
			txtNb = Integer.parseInt(request.getAttribute("txtNumber").toString());
			txtMm = request.getAttribute("txtMemo").toString();

    		%>
    		<script type="text/javascript">

				function button(btnNo){

					document.getElementById('setBtnPurchase').value = null;		// 購入済
					document.getElementById('setBtnUpdate').value = null;		// 修正
					document.getElementById('setBtnDelete').value = null;		// 削除

					// 選択された行の番号を取得
					const rdoSelect = document.form1.rdoSelect;

					switch (btnNo) {
					case 1:

						// 取得するID名を作成
						wkHidUuid = "hidUuid" + rdoSelect.value;						// uuid
						// 取得
						getHidUuid = document.getElementById(wkHidUuid).value;			// uuid
						// サーブレットに渡す(テキストボックス表示)用にset
						document.getElementById('setUuid').value = getHidUuid;			// uuid
						document.getElementById('setBtnPurchase').value = "btnPurchase";// 購入済

						document.form1.submit();
						break
					case 2:

						// 行番号を元にリストから取得
						// 取得するID名を作成
						wkHidUuid = "hidUuid" + rdoSelect.value;		// uuid
						wkLblGoods = "lblGoods" + rdoSelect.value;		// 商品名
						wkLblNumber = "lblNumber" + rdoSelect.value;	// 個数
						wkLblMemo = "lblMemo" + rdoSelect.value;		// メモ


						// 取得
						getHidUuid = document.getElementById(wkHidUuid).value;			// uuid
						getLblGoods = document.getElementById(wkLblGoods).value;		// 商品名
						getLblNumber = document.getElementById(wkLblNumber).value;		// 個数
						getLblMemo = document.getElementById(wkLblMemo).value;			// メモ


						// サーブレットに渡す(テキストボックス表示)用にset
						document.getElementById('setUuid').value = getHidUuid;			// uuid
						document.getElementById('setName').value = getLblGoods;			// 商品名
						document.getElementById('setNumber').value = getLblNumber;		// 個数
						document.getElementById('setMemo').value = getLblMemo;			// メモ
						document.getElementById('setBtnUpdate').value = "btnUpdate";	// 修正

						document.form1.submit();

						break
					case 3:

						// 取得するID名を作成
						wkHidUuid = "hidUuid" + rdoSelect.value;						// uuid
						// 取得
						getHidUuid = document.getElementById(wkHidUuid).value;			// uuid
						// サーブレットに渡す(テキストボックス表示)用にset
						document.getElementById('setUuid').value = getHidUuid;			// uuid
						document.getElementById('setBtnDelete').value = "btnDelete";	// 削除

						document.form1.submit();
						break
					}
				}
			</script>
			<table>
				<tr>
					<td>　</td>
					<td>商品名</td>
					<td>個数</td>
					<td>メモ</td>
					<td><INPUT type="hidden" id="hidUpdateUuid"  name="hidUpdateUuid" value=<%= txtId %>>
						<label id="hidUpdateUuid" name="hidUpdateUuid" hidden ><%= txtId %>></label></td>
				</tr>
				<tr>
				<td>
					<td><input type="text" size="40" minlength="1" maxlength="30" id="txtGoods" name="txtGoods"  value="<%= txtGd %>" required></td>
					<td><input type="number" size="5" max="999" min="0" id="txtNumber" name="txtNumber"  value="<%= txtNb %>"></td>
					<td><input type="text" size="40" maxlength="200" id="txtMemo" name="txtMemo" value="<%= txtMm %>" ></td>
					<td><button id="btnRegister" name="btnRegister" value="register">登録</button></td>
				</tr>
			</table>
			<br/>
			<table>
				<%for(int i = 0; i < arraySelectResult.size()/7 ; i++){ %>
				<tr>
					<td><input type="radio" id="rdoSelect" name="rdoSelect"  value=<%= i %>></td>

					<td><INPUT id="lblGoods<%=i%>" tabindex="-1" value="<%= arraySelectResult.get(7 * i + 1) %>" hidden>
						<label id="lblGoods<%=i%>" ><%= arraySelectResult.get(7 * i + 1) %></label></td>

					<td><INPUT id="lblNumber<%=i%>" tabindex="-1" value="<%= arraySelectResult.get(7 * i + 2) %>" hidden>
						<label id="lblNumber<%=i%>" size="5"><%= arraySelectResult.get(7 * i + 2) %></label></td>

					<td><INPUT id="lblMemo<%=i%>" tabindex="-1" value="<%= arraySelectResult.get(7 * i + 3) %>" hidden>
						<label id="lblMemo<%=i%>" size="40"><%= arraySelectResult.get(7 * i + 3) %></label></td>

					<td><button type="button" id="btnPurchase" name="btnPurchase" value="purchase" onClick="button(1)">購入済</button></td>

					<td><INPUT id="hidUuid<%=i%>"  name="hidUuid" value=<%= arraySelectResult.get(7 * i) %> hidden>
						<label id="hidUuid<%=i%>" hidden></label></td>
				</tr>
				<%} %>
			</table>
			<br/>
			<br/>
			<button type="button" id="btnUpdate" name="btnUpdate" value="update" onClick="button(2)">修正</button>
			<button type="button" id="btnDelete" name="btnDelete" value="delete" onClick="button(3)">削除</button>

			<INPUT type="hidden" id="setUuid"  name="setUuid" >
			<INPUT type="hidden" id="setName"  name="setName" >
			<INPUT type="hidden" id="setNumber"  name="setNumber" >
			<INPUT type="hidden" id="setMemo"  name="setMemo" >
			<INPUT type="hidden" id="setBtnPurchase"  name="setBtnPurchase" >
			<INPUT type="hidden" id="setBtnUpdate"  name="setBtnUpdate" >
			<INPUT type="hidden" id="setBtnDelete"  name="setBtnDelete" >

		</form>

	</body>
</html>