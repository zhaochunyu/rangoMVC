<#import "spring.ftl" as s/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Bootstrap</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Bootstrap -->
	<link href="<@s.url'/plugins/bootstrap/css/bootstrap.css'/>"rel="stylesheet" media="screen" />
	<link href="<@s.url'/plugins/jquery/page/page.css'/>" rel="stylesheet" type="text/css" />
	<link href="<@s.url'/plugins/jquery/msgbox/msgbox.css'/>" rel="stylesheet" type="text/css" />
	<script src="<@s.url'/plugins/jquery/jquery-1.10.2.js'/>"></script>
	<script src="<@s.url'/plugins/bootstrap/js/bootstrap.min.js'/>"></script>
	<script type="text/javascript" src="<@s.url'/plugins/jquery/page/jquery.myPagination5.0.js'/>"></script>
	<script type="text/javascript" src="<@s.url'/plugins/jquery/msgbox/msgbox.js'/>"></script>
	<script type="text/javascript" src="<@s.url'/plugins/jquery/time.js'/>"></script>
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries-->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

	<script type="text/javascript">
		function update(obj) {
			var tds = $(obj).parent().parent().find('td');
			$('#u_id').val(tds.eq(0).text());
			$('#u_username').val(tds.eq(1).text());
			$('#u_email').val(tds.eq(2).text());
			if (tds.eq(3).text() == "W") {
				$('#u_sex').val(0);
			} else if (tds.eq(3).text() == "M") {
				$('#u_sex').val(1);
			} else {
				$('#u_sex').val(2);
			}

			$('#updateWin').modal('show');
		}
		var clickTrObj;
		function trClick(obj) {
			clickTrObj = obj;
			console.log($(obj).find('td').eq(0).text())
		}

		function updateUser() {
			if (clickTrObj == null) {
				alert("请选中要修改的列");
				return;
			}
			var tds = $(clickTrObj).find('td');
			$('#u_id').val(tds.eq(0).text());
			$('#u_username').val(tds.eq(1).text());
			$('#u_email').val(tds.eq(2).text());
			if (tds.eq(3).text() == "W") {
				$('#u_sex').val(0);
			} else if (tds.eq(3).text() == "M") {
				$('#u_sex').val(1);
			} else {
				$('#u_sex').val(2);
			}

			$('#updateWin').modal('show');
		}

		function deleteUser() {
			var id = $(clickTrObj).find('td').eq(0).text();
			if (id == '') {
				alert("请点击要删除的列");
				return;
			}
			$.ajax("<@s.url'/user/deleteUser?userId=" + id + "'/>");
			window.location.reload();
		}
		//时间戳转日期
		function timeStamp2String(time) {
			var datetime = new Date();
			datetime.setTime(time);
			var year = datetime.getFullYear();
			var month = datetime.getMonth() + 1 < 10 ? "0"
					+ (datetime.getMonth() + 1) : datetime.getMonth() + 1;
			var date = datetime.getDate() < 10 ? "0" + datetime.getDate()
					: datetime.getDate();
			var hour = datetime.getHours() < 10 ? "0" + datetime.getHours()
					: datetime.getHours();
			var minute = datetime.getMinutes() < 10 ? "0"
					+ datetime.getMinutes() : datetime.getMinutes();
			var second = datetime.getSeconds() < 10 ? "0"
					+ datetime.getSeconds() : datetime.getSeconds();
			return year + "-" + month + "-" + date + " " + hour + ":" + minute
					+ ":" + second;
		}

		function sexChange(str) {
			var sexStr;
			if (str == '0') {
				sexStr = "W";
			} else if (str == '1') {
				sexStr = "M";
			} else {
				sexStr = "M/W";
			}
			return sexStr;
		}

		$(function() {
			init();

		});

		function init() {
			myPagination = $("#pageBar").myPagination(
				{
					currPage : 1,
					pageNumber : 10,
					cssStyle : 'meneame',//分页样式，具体样式可以从css/page.css样式中选取
					panel : {
							first : 'First',
							last : 'Last',
							next : 'Next',
							prev : 'Prev',
							first_on : true,
							last_on : true,
							next_on : true,
							prev_on : true,
							links : '#',
							tipInfo_on : true,
							tipInfo : '<span>&nbsp;&nbsp;Page&nbsp;{currText}&nbsp;/&nbsp;{sumPage}</span>',
							tipInfo_css : {
								width : '20px',
								height : '20px',
								border : '1px solid #ff9600'
							}
						},
						ajax : {
							on : true,//是否启用异步请求，必须启用
							type : "POST",
							url : "<@s.url'/user/getJsonUserList'/>",
							dataType : 'json',
							param : {
								on : true,
								page : 1
							},//参数列表，其中  on 必须开启，page 参数必须存在,其他自定义

							ajaxStart : function() {
								ZENG.msgbox.show(" 正在加载中，请稍后...", 6, 10000);
							},
							onClick : function(page) {
								$.fn.debug(page);
							},
							//回调函数
							callback : function(data) {
								ZENG.msgbox.hide(); //隐藏加载提示
								$("#userTable > tbody").html(""); //每次加载的时候先设为空
									$.each(data.event,function(i, value) {
										$("#userTable > tbody").append("<tr><td>"
												+ value.id + "</td>"
												+ "<td> " + value.username + "</td>"
												+ "<td> " + value.email + "</td>"
												+ "<td> " + sexChange(value.sex) + "</td>"
												+ "<td> " + value.createUser + "</td>"
												+ "<td> " + value.updateUser + "</td>"
												+ "<td> " + timeStamp2String(value.createTime) + "</td>"
												+ "<td> " + timeStamp2String(value.updateTime) + "</td>"
												+ "<td>"
												+ "<a href=\"javascript:void(0)\" onclick=\"update(this);\" class=\"btn btn-primary btn-xs active\" role=\"button\">Update</a>&nbsp;|&nbsp;"
												+ "<a href=\"<@s.url'/user/deleteUser?userId=" + value.id + "'/>\" class=\"btn btn-danger btn-xs active\" role=\"button\">Delete</a>"
												+ "</td>"
												+ "</tr>");
									});
									$("#rowCount").text(data.pageBean.totalRow);
									$("#pageCount").text(data.pageBean.totalPage);
									$("#pageSize").text(data.pageBean.pageSize);
								}
							},
					});
			}
	</script>
</head>
<body>
	<div class="container">
		<a href="<@s.url'/user/exit'/>" class="btn btn-link pull-right"
			role="button">Exit</a>
		<h1 class="page-header">User Management</h1>
		<div class="row">
			<div class="col-xs-3">
				<button type="button" class="btn btn-success" data-toggle="modal"
					data-target="#addWin">Add</button>
				<a href="javascript:void(0)" onclick="updateUser();"
					class="btn btn-primary" role="button">Update</a> <a
					href="javascript:void(0)" onclick="deleteUser();"
					class="btn btn-danger" role="button">Delete</a>
			</div>
			<div class="input-group col-xs-3 pull-right">
				<input type="text" id="search" placeholder="Search"
					class="form-control"> <span class="input-group-addon"><span
						class="glyphicon glyphicon-search"
						style="color: rgb(255, 140, 60);"></span></span>
			</div>
		</div>
		<div>&nbsp;</div>
		<table id="userTable" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>UserName</th>
					<th>Email</th>
					<th>Sex</th>
					<th>CreateUser</th>
					<th>UpdateUser</th>
					<th>CreateTime</th>
					<th>UpdateTime</th>
					<th>Handle</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>


		<hr />

		<div class="modal fade" id="addWin" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<form class="form-horizontal" action="<@s.url'/user/addUser'/>"
					role="form">
					<div class="modal-content">
					
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Add User</h4>
						</div>
						
						<div class="modal-body">
							<div class="form-group">
								<label for="userName" class="col-xs-2 control-label">UserName</label>
								<div class="col-xs-10">
									<input type="text" class="form-control" id="userName" name="username">
								</div>
							</div>
							
							<div class="form-group">
								<label for="password" class="col-xs-2 control-label">Password</label>
								<div class="col-xs-10">
									<input type="password" class="form-control" id="password" name="password">
								</div>
							</div>

							<div class="form-group">
								<label for="email" class="col-xs-2 control-label">Email</label>
								<div class="col-xs-10">
									<input type="email" class="form-control" id="email"
										name="email">
								</div>
							</div>
							
							<div class="form-group">
								<label for="sex" class="col-xs-2 control-label">Sex</label>
								<div class="col-xs-10">
									<select class="form-control" name="sex">
										<option value="1">M</option>
										<option value="0">W</option>
										<option value="2">M/W</option>
									</select>
								</div>
							</div>
							
						</div>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Save</button>
						</div>
						
					</div>
				</div>
			</form>
		</div>

		<div class="modal fade" id="updateWin" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<form class="form-horizontal" action="<@s.url'/user/updateUser'/>" role="form" method="post">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Update User</h4>
						</div>
						<div class="modal-body">

							<div class="form-group">
								<label for="u_id" class="col-xs-2 control-label">ID</label>
								<div class="col-xs-10">
									<input type="text" class="form-control" id="u_id" name="id">
								</div>
							</div>

							<div class="form-group">
								<label for="u_username" class="col-xs-2 control-label">UserName</label>
								<div class="col-xs-10">
									<input type="text" class="form-control" id="u_username" name="username">
								</div>
							</div>

							<div class="form-group">
								<label for="u_password" class="col-xs-2 control-label">Password</label>
								<div class="col-xs-10">
									<input type="text" class="form-control" id="u_password" name="password">
								</div>
							</div>

							<div class="form-group">
								<label for="u_email" class="col-xs-2 control-label">Email</label>
								<div class="col-xs-10">
									<input type="email" class="form-control" id="u_email" name="email">
								</div>
							</div>
							<div class="form-group">
								<label for="u_sex" class="col-xs-2 control-label">Sex</label>
								<div class="col-xs-10">
									<select class="form-control" id="u_sex" name="sex">
										<option value="1">M</option>
										<option value="0">W</option>
										<option value="2">M/W</option>
									</select>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Update</button>
						</div>
					</div>
			</div>
			</form>
		</div>
		<!-- 分页显示的div块开始 -->
		<div style="float: right;">
			<div id="pageBar" style="float:right"></div>
		</div>
		<!-- 分页显示的div块结束 -->
	</div>
</body>
</html>
