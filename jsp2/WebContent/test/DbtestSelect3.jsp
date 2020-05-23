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
		<form action="DbSelect3" method="get">

			<%

		    request.setCharacterEncoding("Shift_JIS");
			ArrayList<String> aid = new ArrayList<String>();
		    aid= (ArrayList<String>)request.getAttribute("list");


		    String txtId;
			String txtGd;
			int txtNb;
			String txtMm;

			txtId = "001";
			txtGd = "a";
			txtNb = 0;
			txtMm = "m";

			txtId = request.getAttribute("hidUpdateUuid").toString();
//			txtGd = request.getAttribute("txtGoods").toString();
//			txtNb = Integer.parseInt(request.getAttribute("txtNumber").toString());
//			txtMm = request.getAttribute("txtMemo").toString();

    		%>

			<table>
	    		<tr>
	        		<td>　</td>
	        		<td>商品名</td>
	        		<td>個数</td>
	        		<td>メモ</td>
	        		<td><INPUT id="hidUpdateUuid"  name="hidUpdateUuid" value=<%= txtId %> hidden><label id="hidUpdateUuid" name="hidUpdateUuid" hidden ><%= txtId %>></label></td>
				</tr>
				<tr>
				<td>
					<td><input type="text" size="40" minlength="1" maxlength="30" id="txtGoods" name="txtGoods" value="<%= txtGd %>" required></td>
	        		<td><input type="number" size="5" max="999" id="txtNumber" name="txtNumber"  value="<%= txtNb %>"></td>
	        		<td><input type="text" size="40" maxlength="200" id="txtMemo" name="txtMemo" value="<%= txtMm %>" ></td>
	        		<td><button id="btnRegister" name="btnRegister" value="register">登録</button></td>
	    		</tr>
			</table>
			<br/>
			<table>

				<%for(int i = 0; i < aid.size()/7 ; i++){ %>
	    		<tr>
	        		<td><input type="radio" id="rdoSelect1" name="rdoSelect" value="rdoSelect1"></td>
	        		<td><label id="lblGoods" ><%= aid.get(7 * i + 1) %></label></td>
	        		<td><label id="lblNumber" size="5"></label><%= aid.get(7 * i + 2) %></td>
	        		<td><label id="lblMemo" size="40"><%= aid.get(7 * i + 3) %></label></td>
					<td><button id="btnPurchase" name="btnPurchase" value="purchase">購入済</button></td>

	    			<td><label id="hidUuid" hidden></label></td>
	    		</tr>
	    		<%} %>
			</table>
			<br/>
			<br/>
			<button id="btnUpdate" name="btnUpdate" value="update">修正</button>
			<button id="btnDelete" name="btnDelete" value="delete">削除</button>
			<%



    		%>
		</form>
	</body>
</html>