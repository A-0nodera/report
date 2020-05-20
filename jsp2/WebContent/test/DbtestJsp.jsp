<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>DBtest</title>
	</head>
	<body>
		<form action="DbUpdate2" method="get">

			<%
			String txtGd;
			int txtNb;
			String txtMm;


			txtGd = "a";
			txtNb = 1;
			txtMm = "m";

//			txtGd = request.getAttribute("txtGoods").toString();
//			txtNb = Integer.parseInt(request.getAttribute("txtNumber").toString());
//			txtMm = request.getAttribute("txtMemo").toString();

//			System.out.println("txtGd = " + txtGd + "、txtNb = " + txtNb + "、txtMm = " + txtMm);

    		%>


			<table>
	    		<tr>
	        		<td> ${txtGd} </td><td>商品名</td><td>個数</td><td>メモ</td><td><label id="hidUpdateUuid" hidden></label></td>
				</tr>
				<tr>
				<td>
					<td><input type="text" size="40" minlength="1" id="txtGoods" name="txtGoods"  value="<%= txtGd %>" required></td>
	        		<td><input type="text" size="5" maxlength="3" id="txtNumber" name="txtNumber"  value="<%= txtNb %>" ></td>
	        		<td><input type="text" size="40" id="txtMemo" name="txtMemo" value="<%= txtMm %>" ></td>
	        		<td><input type="submit" id="btnRegister" name="btnRegister" value="更新"></td>
	    		</tr>
			</table>
				<button id="a" name="a" value="cart1">カート</button>


		</form>
	</body>
</html>