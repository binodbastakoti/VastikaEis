<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en" class="no-js" ng-app="app">
<head>
<meta charset="utf-8" />
<title update-title>Dashboard</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="MobileOptimized" content="320">
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=all"
	rel="stylesheet" type="text/css" />
<link
	href="resources/assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="resources/assets/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css" />
<link href="resources/assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="resources/assets/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="resources/assets/plugins/select2/select2.css" />
<link rel="stylesheet" type="text/css"
	href="resources/assets/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
<!-- END PAGE LEVEL STYLES -->
<!-- CSS for Grid view and List view-->
<link href="resources/assets/css/gridview.css" rel="stylesheet"
	type="text/css" />
<!-- END CSS for Grid view and List view-->
<!-- BEGIN THEME STYLES -->
<link href="resources/assets/css/style-conquer.css" rel="stylesheet"
	type="text/css" />
<link href="resources/assets/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="resources/assets/css/style-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="resources/assets/css/plugins.css" rel="stylesheet"
	type="text/css" />
<link href="resources/assets/css/themes/default.css" rel="stylesheet"
	type="text/css" id="style_color" />
<link href="resources/assets/css/pages/profile.css" rel="stylesheet"
	type="text/css" />
<link href="resources/assets/css/custom.css" rel="stylesheet"
	type="text/css" />
<!-- BEGIN DATE TIME PICKER STYLES-->
<link rel="stylesheet" type="text/css"
	href="resources/assets/plugins/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="resources/assets/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css"
	href="resources/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css"
	href="resources/assets/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
<link rel="stylesheet" type="text/css"
	href="resources/assets/plugins/bootstrap-toastr/toastr.min.css" />
<link rel="stylesheet" type="text/css"
	href="resources/assets/plugins/angular/lightbox/angular-bootstrap-lightbox.min.css" />
<!-- END DATE TIME PICKER STYLES-->
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="images/favicon.ico" />
</head>

<body class="page-header-fixed" ng-class="{'page-sidebar-closed': full == 'no'}" ng-controller="AppController">

	<div ui-view="header"></div>


	<div class="clearfix"></div>


	<!-- BEGIN CONTAINER -->
	<div class="page-container">


		<div ui-view="navigation"></div>



		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content" ui-view="content">
			
			</div>
		</div>
		<!-- END CONTENT -->
	</div>
	<!-- END CONTAINER -->


	<div ui-view="footer"></div>


	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="resources/assets/plugins/jquery-1.11.0.min.js"
		type="text/javascript"></script>
	<script src="resources/assets/plugins/jquery-migrate-1.2.1.min.js"
		type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script
		src="resources/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"
		type="text/javascript"></script>
	<script src="resources/assets/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="resources/assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
		type="text/javascript"></script>
	<script
		src="resources/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
		type="text/javascript"></script>
	<script src="resources/assets/plugins/jquery.blockui.min.js"
		type="text/javascript"></script>
	<script src="resources/assets/plugins/uniform/jquery.uniform.min.js"
		type="text/javascript"></script>
	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script type="text/javascript"
		src="resources/assets/plugins/select2/select2.min.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/bootstrap-daterangepicker/moment.min.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/bootstrap-toastr/toastr.min.js"></script>

	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="resources/assets/scripts/app.js"></script>
	<script src="resources/assets/scripts/table-managed.js"></script>
	<script src="resources/assets/scripts/form-components.js"></script>
	<script>
		jQuery(document).ready(function() {
			// initiate layout and plugins
			TableManaged.init();
			//FormComponents.init();
		});
	</script>

	<script type="text/javascript"
		src="resources/assets/plugins/angular/angular.min.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/angular/angular-ui-router.min.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/angular/angular-cookies.min.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/angular/angular-file-upload-shim.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/angular/angular-file-upload.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/angular/ui-bootstrap-0.13.3.min.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/angular/angular-showErrors.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/angular/lightbox/angular-bootstrap-lightbox.min.js"></script>
	<script type="text/javascript"
		src="resources/assets/plugins/underscore.js"></script>
	<script type="text/javascript" src="resources/js/app.js"></script>
	<script type="text/javascript" src="resources/js/services.js"></script>
	<script type="text/javascript" src="resources/js/directives.js"></script>
	<c:forEach items="${jsFiles}" var="jsFile">
		<script type="text/javascript" src="resources/js/${jsFile}"></script>
	</c:forEach>
	
	
	<script src="resources/js/angular/mainApp.js"></script>
	<script src="resources/js/angular/controller.js"></script>
	<script src="resources/js/angular/services.js"></script>

</body>