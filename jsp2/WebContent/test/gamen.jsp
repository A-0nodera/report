<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>買い物リスト</title>
  		<script language="javascript" type="text/javascript">
     		function OnButtonClickRegister () {
      			window.open('http://localhost:8080/jsp2/hallo.html', null, 'top=100,left=100,width=300,height=400');
        	}
    	</script>
	</head>
	<body>
		<form action="sample1_to.jsp" method="post">
			<table>
	    		<tr>
	        		<td>　</td><td>商品名</td><td>個数</td><td>メモ</td><td><label id="hidUpdateUuid" hidden></label></td>
				</tr>
				<tr>
				<td>
					<td><input type="text" size="40" minlength="1" id="txtGoods" name="txtGoods" required></td>
	        		<td><input type="text" size="5" maxlength="3" id="txtNumber" name="txtNumber"></td>
	        		<td><input type="text" size="40" id="txtMemo" name="txtMemo"></td>
	        		<td><input type="button" id="btnRegister" name="btnRegister" value="登録" onclick="OnButtonClickRegister()"></td>
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
					<td><input type="button" id="btnPurchase" name="btnPurchase" value="購入済" onclick="OnButtonClickRegister()"></td>
	    			<td><label id="hidUuid" hidden></label></td>
	    		</tr>
	    		<%} %>
			</table>
			<br/>
			<br/>
			<input type="button" id="btnUpdate" name="btnUpdate" value="修正" onclick="OnButtonClickRegister()">
			<input type="button" id="btnDelete" name="btnDelete" value="削除" onclick="OnButtonClickRegister()">
		</form>
	</body>
</html>