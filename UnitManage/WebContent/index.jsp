<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Unit Management</title>
<link rel="stylesheet" href="Components/css/bootstrap.min.css" />
<script src="Components/js/jquery.min.js"></script>
<script src="Components/js/bootstrap.min.js"></script>
<script src="Components/js/unit.js"></script>
<style>
/*
 * Base structure
 */

/* Move down content because we have a fixed navbar that is 50px tall */
body {
	padding-top: 50px;
}

/*
 * Global add-ons
 */
.sub-header {
	padding-bottom: 10px;
	border-bottom: 1px solid #eee;
}

/*
 * Top navigation
 * Hide default border to remove 1px line.
 */
.navbar-fixed-top {
	border: 0;
}

/*
 * Sidebar
 */

/* Hide for mobile, show later */
.sidebar {
	display: none;
}

@media ( min-width : 768px) {
	.sidebar {
		position: fixed;
		top: 51px;
		bottom: 0;
		left: 0;
		z-index: 1000;
		display: block;
		padding: 20px;
		overflow-x: hidden;
		overflow-y: auto;
		/* Scrollable contents if viewport is shorter than content. */
		background-color: #f5f5f5;
		border-right: 1px solid #eee;
	}
}

/* Sidebar navigation */
.nav-sidebar {
	margin-right: -21px; /* 20px padding + 1px border */
	margin-bottom: 20px;
	margin-left: -20px;
}

.nav-sidebar>li>a {
	padding-right: 20px;
	padding-left: 20px;
}

.nav-sidebar>.active>a, .nav-sidebar>.active>a:hover, .nav-sidebar>.active>a:focus
	{
	color: #fff;
	background-color: #428bca;
}

/*
 * Main content
 */
.main {
	padding: 20px;
}

@media ( min-width : 768px) {
	.main {
		padding-right: 40px;
		padding-left: 40px;
	}
}

.main .page-header {
	margin-top: 0;
}

/*
 * Placeholder dashboard ideas
 */
.placeholders {
	margin-bottom: 30px;
	text-align: center;
}

.placeholders h4 {
	margin-bottom: 0;
}

.placeholder {
	margin-bottom: 20px;
}

.placeholder img {
	display: inline-block;
	border-radius: 50%;
}
</style>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">ElectorGrid(EG)</a>
			</div>

		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="/UnitManage">Electricity Unit
							Management <span class="sr-only">(current)</span>
					</a></li>

				</ul>


			</div>

			<div id="add_form"
				class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"
				style="display: none;">
				<form id="unit_add_form" class="form-horizontal">


					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">User
							Account Number</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="user_account_no"
								placeholder="User Account Number">
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Usage
							Date</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="usage_date"
								placeholder="Usage Date">
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Used
							Units</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="used_units"
								name="used_units" placeholder="Used Units">
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Price
							Per Unit</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="price_per_unit"
								name="price_per_unit" placeholder="Price Per Unit">
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Total
							Price</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" disabled="true"
								id="total_price" name="total_price" placeholder="Total Price">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">Submit</button>
							<button onclick="backtoList();" type="button"
								class="btn btn-danger">Cancel</button>

						</div>
					</div>
				</form>
			</div>


			<div id="update_form"
				class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"
				style="display: none;">
				<form id="unit_update_form" class="form-horizontal">


					
				</form>
			</div>

			<div id="list_view"
				class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">Electricity Unit Management</h1>



				<h2 class="sub-header">
					<button onclick="addFormView()" type="button"
						class="btn btn-primary">Add New Record</button>
				</h2>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>User Account Number</th>
								<th>Usage Date</th>
								<th>Used Units</th>
								<th>Price Per Unit</th>
								<th>Total Bill</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody id="tbl_body_day">


						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>