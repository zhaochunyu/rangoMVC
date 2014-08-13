<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
	<form id="htmlmoco" name="htmlmoco" action="./rangodata" method="post">
		<table bgcolor="lightblue" align="center" width="1550">
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<table border="0">
						<tr>
							<h2 align="center">RANGO</h2>
						</tr>
						<tr>
							<td align="right">协议：</td>
							<td><select id="pact" name="pact">
									<option value="http">HTTP</option>
									<option value="https">HTTPS</option>
									<option value="socket">SOCKET</option>
									<option value="heasian">HESSIAN</option>
							</select></td>
							<td></td>
						</tr>
						<tr>
							<td align="right">端口</td>
							<td align="left"><input type="text" id="port" name="port"
								style="width: 150; height: 20" value="4800"></td>
							<td></td>
						</tr>
						<tr>
							<td align="right">URL</td>
							<td><input type="text" id="URL" name="URL"
								style="width: 150; height: 20" /></td>
							<td></td>
						</tr>
						<tr>
							<td align="right">返回协议：</td>
							<td><select id="returntype" name="returntype">
									<option value="xml">XML</option>
									<option value="json">JSON</option>
									<option value="ciphertext">密文</option>
									<option value="string">字符串</option>
							</select></td>
							<td></td>
						</tr>
						<tr>
							<td align="right">返回内容：</td>
							<td><textarea type="text" name="returnmsg" cols="80"
									rows="18" /></textarea></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="3"><div align="center">
									<input type="submit" name="submit" onclick="doCheckSubmit()"
										value="添加" />
								</div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>