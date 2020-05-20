<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>買い物リスト</title>
	</head>
	<body>
		<form action="DbSelect3" method="get">

			<%
			String txtGd;
			int txtNb;
			String txtMm;

			txtGd = "a";
			txtNb = 1;
			txtMm = "m";

    		%>

			<table>
	    		<tr>
	        		<td>　</td><td>商品名</td><td>個数</td><td>メモ</td><td><label id="hidUpdateUuid" hidden></label></td>
				</tr>
				<tr>
				<td>
					<td><input type="text" size="40" minlength="1" id="txtGoods" name="txtGoods" value="<%= txtGd %>" required></td>
	        		<td><input type="text" size="5" maxlength="3" id="txtNumber" name="txtNumber"  value="<%= txtNb %>"></td>
	        		<td><input type="text" size="40" id="txtMemo" name="txtMemo" value="<%= txtMm %>" ></td>
	        		<td><button id="btnRegister" name="btnRegister" value="register">登録</button></td>
	    		</tr>
			</table>
			<br/>
			<table>
				<% int getDb = 3; %>
				<%for(int i = 0; i < getDb ; i++){ %>
	    		<tr>
	        		<td><input type="radio" id="rdoSelect1" name="rdoSelect" value="rdoSelect1"></td>
	        		<td><label id="lblGoods" >商品名00A</label></td>
	        		<td><label id="lblNumber" size="5"></label>1</td>
	        		<td><label id="lblMemo" size="40">めもめもめも</label></td>
					<td><button id="btnPurchase" name="btnPurchase" value="purchase">購入済</button></td>

	    			<td><label id="hidUuid" hidden></label></td>
	    		</tr>
	    		<%} %>
			</table>
		</form>
	</body>
</html>