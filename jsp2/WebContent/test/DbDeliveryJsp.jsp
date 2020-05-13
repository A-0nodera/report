<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>DbDelivery</title>
	</head>
	<body>
		<form action="DbDelivery" method="get">

			<%
			String txtGd;
			int txtNb;

			txtGd = request.getAttribute("txtGoods").toString();
			txtNb = Integer.parseInt(request.getAttribute("txtNumber").toString());

    		%>
			<table>
	    		<tr>
	        		<td>　</td><td>商品名</td><td>個数</td><td>メモ</td><td><label id="hidUpdateUuid" hidden></label></td>
				</tr>
				<tr>
				<td>
					<td><input type="text" size="40" minlength="1" id="txtGoods" name="txtGoods" value="${txtGoods}" required></td>
	        		<td><input type="number" size="5" maxlength="3" id="txtNumber" name="txtNumber" value="${txtNumber}"></td>
	        		<td><input type="text" size="40" id="txtMemo" name="txtMemo"></td>
	        		<td><input type="submit" id="btnRegister" name="btnRegister" value="登録"></td>
	    		</tr>
			</table>
		</form>
	</body>
</html>