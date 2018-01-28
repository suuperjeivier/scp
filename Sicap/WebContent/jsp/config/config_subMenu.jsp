<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- <c:if test="${user == null}">
	<c:redirect url="./" />
</c:if>	-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- pageContext.request.contextPath -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description"
	content="Pagina para consulta de lista de asistencia" />
<meta name="author" content="Sistemas C4 Tabasco">
<link rel="manifest" href="./web_app_manifest.json">
<link rel='shortcut icon' type='image/x-icon' href='./favicon.ico' />
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/base/base.css" rel="stylesheet">
<link rel="stylesheet" href="css/sweetalert2.min.css" />
<link rel="stylesheet"
	href="fonts/fontawesome/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />
<link rel="stylesheet"
	href="css/fontawesome_icon_picker/fontawesome-iconpicker.min.css" />
<c:set var="compTitle" value="Submenus"></c:set>
<c:set var="compTitleSing" value="Submenu"></c:set>
<title>Consulta de <c:out value="${compTitle}" /> | <c:out
		value="${configSistema.tituloSistema}" /></title>
<style type="text/css">
select {
	font-family: FontAwesome, sans-serif;
}
</style>
</head>
<body>
	<jsp:include page="/jsp/header/header.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row" id="initRow"></div>
		<div class="page-header">
			<h1>
				<i class="fa fa-search" aria-hidden="true"></i> Consulta de
				<c:out value="${compTitle}" />
				<small><c:out value="${compTitle}" /> del sistema</small>
			</h1>
		</div>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#"><i
					class="fa fa-clock-o" aria-hidden="true"></i> Inventario </a></li>
			<li class="breadcrumb-item"><i class="fa fa-search"
				aria-hidden="true"></i> Consulta</li>
			<li class="breadcrumb-item active"><i class="fa fa-search"
				aria-hidden="true"></i> <c:out value="${compTitle}" /></li>
		</ol>
		<div class="row">
			<!-- <form class="form-inline"
					action="./listados?action=buscarHorario" method="post">
					
						<h4><i class="fa fa-filter"></i>Filtros</h4>
						<div class="form-group">
							<label for="claveHorario">CLAVE DEL HORARIO:</label> <input
								type="text" value="<c:out value="filtroClave" />"
								class="form-control" id="claveHorario" name="claveHorario">
						</div>
						<button type="submit" class="btn btn-default"><i class="fa fa-search"></i> Buscar</button>
					
				</form>-->
		</div>
		<div class="row">
			<button type="button" class="btn btn-success btn-margin"
				data-target="#modal<c:out value="${compTitle}"/>"
				data-toggle="modal" data-new="1">
				<i class="fa fa-plus"></i> Nuevo
				<c:out value="${compTitleSing}" />
			</button>
			<c:if test="${fn:length(submenus) gt 0}">
				<button type="button" class="btn btn-primary btn-margin"
					id="btnImprimir<c:out value="${compTitle}"/>">
					<i class="fa fa-print"></i> Imprimir Listado
				</button>
			</c:if>
			<!-- Modal -->
			<div class="modal fade" id="modal<c:out value="${compTitle}"/>"
				tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="modalLabel"></h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<form id="form<c:out value="${compTitleSing}"/>">
							<div class="modal-body">
								<input type="hidden" name="idSubmenuHidden" id="idSubmenuHidden"
									value=""></input>
								<div class="form-group row">
									<label for="field1" class="col-2 col-form-label"><c:out
											value="${compTitleSing}" /> ID</label> <input type="text"
										class="col-7 form-control" name="field1" id="field1" readonly
										placeholder="Id <c:out value="${compTitleSing}"/>">
								</div>
								<div class="form-group row">
									<label for="field2" class="col-2 col-form-label">MENU</label> <select
										class="col-7 form-control" name="field2" id="field2">
										<option value="">Seleccione un menu para el
											<c:out value="${compTitleSing}" />...
										</option>
										<c:forEach var="menu" items="${menus}">
											<option value='<c:out value="${menu.idApp}"></c:out>'>
												<c:out value="${menu.nombreApp}"></c:out>
											</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group row">
									<label for="field3" class="col-2 col-form-label">SUBMEN�</label>
									<input type="text" class="col-7 form-control" name="field3"
										id="field3" placeholder=" <c:out value="${compTitleSing}"/>">
								</div>
								<div class="form-group row">
									<label for="field4" class="col-2 col-form-label">URL</label> <input
										type="text" class="col-7 form-control" name="field4"
										id="field4"
										placeholder="URL del <c:out value="${compTitleSing}"/>">
								</div>
								<div class="form-group row iconpicker-container">
									<label for="field5" class="col-2 col-form-label">�CONO</label>
									<select class="col-7 form-control" name="field8" id="field8">
										<option value="" selected="selected">Seleccione...</option>
										<option value="fa-align-left">&#xf036; align-left</option>
										<option value="fa-align-right">&#xf038; align-right</option>
										<option value="fa-amazon">&#xf270; amazon</option>
										<option value="fa-ambulance">&#xf0f9; ambulance</option>
										<option value="fa-anchor">&#xf13d; anchor</option>
										<option value="fa-android">&#xf17b; android</option>
										<option value="fa-angellist">&#xf209; angellist</option>
										<option value="fa-angle-double-down">&#xf103;
											angle-double-down</option>
										<option value="fa-angle-double-left">&#xf100;
											angle-double-left</option>
										<option value="fa-angle-double-right">&#xf101;
											angle-double-right</option>
										<option value="fa-angle-double-up">&#xf102;
											angle-double-up</option>
										<option value="fa-angle-left">&#xf104; angle-left</option>
										<option value="fa-angle-right">&#xf105; angle-right</option>
										<option value="fa-angle-up">&#xf106; angle-up</option>
										<option value="fa-apple">&#xf179; apple</option>
										<option value="fa-archive">&#xf187; archive</option>
										<option value="fa-area-chart">&#xf1fe; area-chart</option>
										<option value="fa-arrow-circle-down">&#xf0ab;
											arrow-circle-down</option>
										<option value="fa-arrow-circle-left">&#xf0a8;
											arrow-circle-left</option>
										<option value="fa-arrow-circle-o-down">&#xf01a;
											arrow-circle-o-down</option>
										<option value="fa-arrow-circle-o-left">&#xf190;
											arrow-circle-o-left</option>
										<option value="fa-arrow-circle-o-right">&#xf18e;
											arrow-circle-o-right</option>
										<option value="fa-arrow-circle-o-up">&#xf01b;
											arrow-circle-o-up</option>
										<option value="fa-arrow-circle-right">&#xf0a9;
											arrow-circle-right</option>
										<option value="fa-arrow-circle-up">&#xf0aa;
											arrow-circle-up</option>
										<option value="fa-arrow-down">&#xf063; arrow-down</option>
										<option value="fa-arrow-left">&#xf060; arrow-left</option>
										<option value="fa-arrow-right">&#xf061; arrow-right</option>
										<option value="fa-arrow-up">&#xf062; arrow-up</option>
										<option value="fa-arrows">&#xf047; arrows</option>
										<option value="fa-arrows-alt">&#xf0b2; arrows-alt</option>
										<option value="fa-arrows-h">&#xf07e; arrows-h</option>
										<option value="fa-arrows-v">&#xf07d; arrows-v</option>
										<option value="fa-asterisk">&#xf069; asterisk</option>
										<option value="fa-at">&#xf1fa; at</option>
										<option value="fa-automobile">&#xf1b9; automobile</option>
										<option value="fa-backward">&#xf04a; backward</option>
										<option value="fa-balance-scale">&#xf24e;
											balance-scale</option>
										<option value="fa-ban">&#xf05e; ban</option>
										<option value="fa-bank">&#xf19c; bank</option>
										<option value="fa-bar-chart">&#xf080; bar-chart</option>
										<option value="fa-bar-chart-o">&#xf080; bar-chart-o</option>
										<option value="fa-battery-full">&#xf240; battery-full</option>
										<option value="fa-beer">&#xf0fc; beer</option>
										<option value="fa-behance">&#xf1b4; behance</option>
										<option value="fa-behance-square">&#xf1b5;
											behance-square</option>
										<option value="fa-bell">&#xf0f3; bell</option>
										<option value="fa-bell-o">&#xf0a2; bell-o</option>
										<option value="fa-bell-slash">&#xf1f6; bell-slash</option>
										<option value="fa-bell-slash-o">&#xf1f7; bell-slash-o</option>
										<option value="fa-bicycle">&#xf206; bicycle</option>
										<option value="fa-binoculars">&#xf1e5; binoculars</option>
										<option value="fa-birthday-cake">&#xf1fd;
											birthday-cake</option>
										<option value="fa-bitbucket">&#xf171; bitbucket</option>
										<option value="fa-bitbucket-square">&#xf172;
											bitbucket-square</option>
										<option value="fa-bitcoin">&#xf15a; bitcoin</option>
										<option value="fa-black-tie">&#xf27e; black-tie</option>
										<option value="fa-bold">&#xf032; bold</option>
										<option value="fa-bolt">&#xf0e7; bolt</option>
										<option value="fa-bomb">&#xf1e2; bomb</option>
										<option value="fa-book">&#xf02d; book</option>
										<option value="fa-bookmark">&#xf02e; bookmark</option>
										<option value="fa-bookmark-o">&#xf097; bookmark-o</option>
										<option value="fa-briefcase">&#xf0b1; briefcase</option>
										<option value="fa-btc">&#xf15a; btc</option>
										<option value="fa-bug">&#xf188; bug</option>
										<option value="fa-building">&#xf1ad; building</option>
										<option value="fa-building-o">&#xf0f7; building-o</option>
										<option value="fa-bullhorn">&#xf0a1; bullhorn</option>
										<option value="fa-bullseye">&#xf140; bullseye</option>
										<option value="fa-bus">&#xf207; bus</option>
										<option value="fa-cab">&#xf1ba; cab</option>
										<option value="fa-calendar">&#xf073; calendar</option>
										<option value="fa-camera">&#xf030; camera</option>
										<option value="fa-car">&#xf1b9; car</option>
										<option value="fa-caret-up">&#xf0d8; caret-up</option>
										<option value="fa-cart-plus">&#xf217; cart-plus</option>
										<option value="fa-cc">&#xf20a; cc</option>
										<option value="fa-cc-amex">&#xf1f3; cc-amex</option>
										<option value="fa-cc-jcb">&#xf24b; cc-jcb</option>
										<option value="fa-cc-paypal">&#xf1f4; cc-paypal</option>
										<option value="fa-cc-stripe">&#xf1f5; cc-stripe</option>
										<option value="fa-cc-visa">&#xf1f0; cc-visa</option>
										<option value="fa-chain">&#xf0c1; chain</option>
										<option value="fa-check">&#xf00c; check</option>
										<option value="fa-chevron-left">&#xf053; chevron-left</option>
										<option value="fa-chevron-right">&#xf054;
											chevron-right</option>
										<option value="fa-chevron-up">&#xf077; chevron-up</option>
										<option value="fa-child">&#xf1ae; child</option>
										<option value="fa-chrome">&#xf268; chrome</option>
										<option value="fa-circle">&#xf111; circle</option>
										<option value="fa-circle-o">&#xf10c; circle-o</option>
										<option value="fa-circle-o-notch">&#xf1ce;
											circle-o-notch</option>
										<option value="fa-circle-thin">&#xf1db; circle-thin</option>
										<option value="fa-clipboard">&#xf0ea; clipboard</option>
										<option value="fa-clock-o">&#xf017; clock-o</option>
										<option value="fa-clone">&#xf24d; clone</option>
										<option value="fa-close">&#xf00d; close</option>
										<option value="fa-cloud">&#xf0c2; cloud</option>
										<option value="fa-cloud-download">&#xf0ed;
											cloud-download</option>
										<option value="fa-cloud-upload">&#xf0ee; cloud-upload</option>
										<option value="fa-cny">&#xf157; cny</option>
										<option value="fa-code">&#xf121; code</option>
										<option value="fa-code-fork">&#xf126; code-fork</option>
										<option value="fa-codepen">&#xf1cb; codepen</option>
										<option value="fa-coffee">&#xf0f4; coffee</option>
										<option value="fa-cog">&#xf013; cog</option>
										<option value="fa-cogs">&#xf085; cogs</option>
										<option value="fa-columns">&#xf0db; columns</option>
										<option value="fa-comment">&#xf075; comment</option>
										<option value="fa-comment-o">&#xf0e5; comment-o</option>
										<option value="fa-commenting">&#xf27a; commenting</option>
										<option value="fa-commenting-o">&#xf27b; commenting-o</option>
										<option value="fa-comments">&#xf086; comments</option>
										<option value="fa-comments-o">&#xf0e6; comments-o</option>
										<option value="fa-compass">&#xf14e; compass</option>
										<option value="fa-compress">&#xf066; compress</option>
										<option value="fa-connectdevelop">&#xf20e;
											connectdevelop</option>
										<option value="fa-contao">&#xf26d; contao</option>
										<option value="fa-copy">&#xf0c5; copy</option>
										<option value="fa-copyright">&#xf1f9; copyright</option>
										<option value="fa-creative-commons">&#xf25e;
											creative-commons</option>
										<option value="fa-credit-card">&#xf09d; credit-card</option>
										<option value="fa-crop">&#xf125; crop</option>
										<option value="fa-crosshairs">&#xf05b; crosshairs</option>
										<option value="fa-css3">&#xf13c; css3</option>
										<option value="fa-cube">&#xf1b2; cube</option>
										<option value="fa-cubes">&#xf1b3; cubes</option>
										<option value="fa-cut">&#xf0c4; cut</option>
										<option value="fa-cutlery">&#xf0f5; cutlery</option>
										<option value="fa-dashboard">&#xf0e4; dashboard</option>
										<option value="fa-dashcube">&#xf210; dashcube</option>
										<option value="fa-database">&#xf1c0; database</option>
										<option value="fa-dedent">&#xf03b; dedent</option>
										<option value="fa-delicious">&#xf1a5; delicious</option>
										<option value="fa-desktop">&#xf108; desktop</option>
										<option value="fa-deviantart">&#xf1bd; deviantart</option>
										<option value="fa-diamond">&#xf219; diamond</option>
										<option value="fa-digg">&#xf1a6; digg</option>
										<option value="fa-dollar">&#xf155; dollar</option>
										<option value="fa-download">&#xf019; download</option>
										<option value="fa-dribbble">&#xf17d; dribbble</option>
										<option value="fa-dropbox">&#xf16b; dropbox</option>
										<option value="fa-drupal">&#xf1a9; drupal</option>
										<option value="fa-edit">&#xf044; edit</option>
										<option value="fa-eject">&#xf052; eject</option>
										<option value="fa-ellipsis-h">&#xf141; ellipsis-h</option>
										<option value="fa-ellipsis-v">&#xf142; ellipsis-v</option>
										<option value="fa-empire">&#xf1d1; empire</option>
										<option value="fa-envelope">&#xf0e0; envelope</option>
										<option value="fa-envelope-o">&#xf003; envelope-o</option>
										<option value="fa-eur">&#xf153; eur</option>
										<option value="fa-euro">&#xf153; euro</option>
										<option value="fa-exchange">&#xf0ec; exchange</option>
										<option value="fa-exclamation">&#xf12a; exclamation</option>
										<option value="fa-exclamation-circle">&#xf06a;
											exclamation-circle</option>
										<option value="fa-exclamation-triangle">&#xf071;
											exclamation-triangle</option>
										<option value="fa-expand">&#xf065; expand</option>
										<option value="fa-expeditedssl">&#xf23e; expeditedssl</option>
										<option value="fa-external-link">&#xf08e;
											external-link</option>
										<option value="fa-external-link-square">&#xf14c;
											external-link-square</option>
										<option value="fa-eye">&#xf06e; eye</option>
										<option value="fa-eye-slash">&#xf070; eye-slash</option>
										<option value="fa-eyedropper">&#xf1fb; eyedropper</option>
										<option value="fa-facebook">&#xf09a; facebook</option>
										<option value="fa-facebook-f">&#xf09a; facebook-f</option>
										<option value="fa-facebook-official">&#xf230;
											facebook-official</option>
										<option value="fa-facebook-square">&#xf082;
											facebook-square</option>
										<option value="fa-fast-backward">&#xf049;
											fast-backward</option>
										<option value="fa-fast-forward">&#xf050; fast-forward</option>
										<option value="fa-fax">&#xf1ac; fax</option>
										<option value="fa-feed">&#xf09e; feed</option>
										<option value="fa-female">&#xf182; female</option>
										<option value="fa-fighter-jet">&#xf0fb; fighter-jet</option>
										<option value="fa-file">&#xf15b; file</option>
										<option value="fa-file-archive-o">&#xf1c6;
											file-archive-o</option>
										<option value="fa-file-audio-o">&#xf1c7; file-audio-o</option>
										<option value="fa-file-code-o">&#xf1c9; file-code-o</option>
										<option value="fa-file-excel-o">&#xf1c3; file-excel-o</option>
										<option value="fa-file-image-o">&#xf1c5; file-image-o</option>
										<option value="fa-file-movie-o">&#xf1c8; file-movie-o</option>
										<option value="fa-file-o">&#xf016; file-o</option>
										<option value="fa-file-pdf-o">&#xf1c1; file-pdf-o</option>
										<option value="fa-file-photo-o">&#xf1c5; file-photo-o</option>
										<option value="fa-file-picture-o">&#xf1c5;
											file-picture-o</option>
										<option value="fa-file-powerpoint-o">&#xf1c4;
											file-powerpoint-o</option>
										<option value="fa-file-sound-o">&#xf1c7; file-sound-o</option>
										<option value="fa-file-text">&#xf15c; file-text</option>
										<option value="fa-file-text-o">&#xf0f6; file-text-o</option>
										<option value="fa-file-video-o">&#xf1c8; file-video-o</option>
										<option value="fa-file-word-o">&#xf1c2; file-word-o</option>
										<option value="fa-file-zip-o">&#xf1c6; file-zip-o</option>
										<option value="fa-files-o">&#xf0c5; files-o</option>
										<option value="fa-film">&#xf008; film</option>
										<option value="fa-filter">&#xf0b0; filter</option>
										<option value="fa-fire">&#xf06d; fire</option>
										<option value="fa-fire-extinguisher">&#xf134;
											fire-extinguisher</option>
										<option value="fa-firefox">&#xf269; firefox</option>
										<option value="fa-flag">&#xf024; flag</option>
										<option value="fa-flag-checkered">&#xf11e;
											flag-checkered</option>
										<option value="fa-flag-o">&#xf11d; flag-o</option>
										<option value="fa-flash">&#xf0e7; flash</option>
										<option value="fa-flask">&#xf0c3; flask</option>
										<option value="fa-flickr">&#xf16e; flickr</option>
										<option value="fa-floppy-o">&#xf0c7; floppy-o</option>
										<option value="fa-folder">&#xf07b; folder</option>
										<option value="fa-folder-o">&#xf114; folder-o</option>
										<option value="fa-folder-open">&#xf07c; folder-open</option>
										<option value="fa-folder-open-o">&#xf115;
											folder-open-o</option>
										<option value="fa-font">&#xf031; font</option>
										<option value="fa-fonticons">&#xf280; fonticons</option>
										<option value="fa-forumbee">&#xf211; forumbee</option>
										<option value="fa-forward">&#xf04e; forward</option>
										<option value="fa-foursquare">&#xf180; foursquare</option>
										<option value="fa-frown-o">&#xf119; frown-o</option>
										<option value="fa-futbol-o">&#xf1e3; futbol-o</option>
										<option value="fa-gamepad">&#xf11b; gamepad</option>
										<option value="fa-gavel">&#xf0e3; gavel</option>
										<option value="fa-gbp">&#xf154; gbp</option>
										<option value="fa-ge">&#xf1d1; ge</option>
										<option value="fa-gear">&#xf013; gear</option>
										<option value="fa-gears">&#xf085; gears</option>
										<option value="fa-genderless">&#xf22d; genderless</option>
										<option value="fa-get-pocket">&#xf265; get-pocket</option>
										<option value="fa-gg">&#xf260; gg</option>
										<option value="fa-gg-circle">&#xf261; gg-circle</option>
										<option value="fa-gift">&#xf06b; gift</option>
										<option value="fa-git">&#xf1d3; git</option>
										<option value="fa-git-square">&#xf1d2; git-square</option>
										<option value="fa-github">&#xf09b; github</option>
										<option value="fa-github-alt">&#xf113; github-alt</option>
										<option value="fa-github-square">&#xf092;
											github-square</option>
										<option value="fa-gittip">&#xf184; gittip</option>
										<option value="fa-glass">&#xf000; glass</option>
										<option value="fa-globe">&#xf0ac; globe</option>
										<option value="fa-google">&#xf1a0; google</option>
										<option value="fa-google-plus">&#xf0d5; google-plus</option>
										<option value="fa-google-plus-square">&#xf0d4;
											google-plus-square</option>
										<option value="fa-google-wallet">&#xf1ee;
											google-wallet</option>
										<option value="fa-graduation-cap">&#xf19d;
											graduation-cap</option>
										<option value="fa-gratipay">&#xf184; gratipay</option>
										<option value="fa-group">&#xf0c0; group</option>
										<option value="fa-h-square">&#xf0fd; h-square</option>
										<option value="fa-hacker-news">&#xf1d4; hacker-news</option>
										<option value="fa-hand-grab-o">&#xf255; hand-grab-o</option>
										<option value="fa-hand-lizard-o">&#xf258;
											hand-lizard-o</option>
										<option value="fa-hand-o-down">&#xf0a7; hand-o-down</option>
										<option value="fa-hand-o-left">&#xf0a5; hand-o-left</option>
										<option value="fa-hand-o-right">&#xf0a4; hand-o-right</option>
										<option value="fa-hand-o-up">&#xf0a6; hand-o-up</option>
										<option value="fa-hand-paper-o">&#xf256; hand-paper-o</option>
										<option value="fa-hand-peace-o">&#xf25b; hand-peace-o</option>
										<option value="fa-hand-pointer-o">&#xf25a;
											hand-pointer-o</option>
										<option value="fa-hand-rock-o">&#xf255; hand-rock-o</option>
										<option value="fa-hand-scissors-o">&#xf257;
											hand-scissors-o</option>
										<option value="fa-hand-spock-o">&#xf259; hand-spock-o</option>
										<option value="fa-hand-stop-o">&#xf256; hand-stop-o</option>
										<option value="fa-hdd-o">&#xf0a0; hdd-o</option>
										<option value="fa-header">&#xf1dc; header</option>
										<option value="fa-headphones">&#xf025; headphones</option>
										<option value="fa-heart">&#xf004; heart</option>
										<option value="fa-heart-o">&#xf08a; heart-o</option>
										<option value="fa-heartbeat">&#xf21e; heartbeat</option>
										<option value="fa-history">&#xf1da; history</option>
										<option value="fa-home">&#xf015; home</option>
										<option value="fa-hospital-o">&#xf0f8; hospital-o</option>
										<option value="fa-hotel">&#xf236; hotel</option>
										<option value="fa-hourglass">&#xf254; hourglass</option>
										<option value="fa-hourglass-1">&#xf251; hourglass-1</option>
										<option value="fa-hourglass-2">&#xf252; hourglass-2</option>
										<option value="fa-hourglass-3">&#xf253; hourglass-3</option>
										<option value="fa-hourglass-end">&#xf253;
											hourglass-end</option>
										<option value="fa-hourglass-half">&#xf252;
											hourglass-half</option>
										<option value="fa-hourglass-o">&#xf250; hourglass-o</option>
										<option value="fa-hourglass-start">&#xf251;
											hourglass-start</option>
										<option value="fa-houzz">&#xf27c; houzz</option>
										<option value="fa-html5">&#xf13b; html5</option>
										<option value="fa-i-cursor">&#xf246; i-cursor</option>
										<option value="fa-ils">&#xf20b; ils</option>
										<option value="fa-image">&#xf03e; image</option>
										<option value="fa-inbox">&#xf01c; inbox</option>
										<option value="fa-indent">&#xf03c; indent</option>
										<option value="fa-industry">&#xf275; industry</option>
										<option value="fa-info">&#xf129; info</option>
										<option value="fa-info-circle">&#xf05a; info-circle</option>
										<option value="fa-inr">&#xf156; inr</option>
										<option value="fa-instagram">&#xf16d; instagram</option>
										<option value="fa-institution">&#xf19c; institution</option>
										<option value="fa-internet-explorer">&#xf26b;
											internet-explorer</option>
										<option value="fa-intersex">&#xf224; intersex</option>
										<option value="fa-ioxhost">&#xf208; ioxhost</option>
										<option value="fa-italic">&#xf033; italic</option>
										<option value="fa-joomla">&#xf1aa; joomla</option>
										<option value="fa-jpy">&#xf157; jpy</option>
										<option value="fa-jsfiddle">&#xf1cc; jsfiddle</option>
										<option value="fa-key">&#xf084; key</option>
										<option value="fa-keyboard-o">&#xf11c; keyboard-o</option>
										<option value="fa-krw">&#xf159; krw</option>
										<option value="fa-language">&#xf1ab; language</option>
										<option value="fa-laptop">&#xf109; laptop</option>
										<option value="fa-lastfm">&#xf202; lastfm</option>
										<option value="fa-lastfm-square">&#xf203;
											lastfm-square</option>
										<option value="fa-leaf">&#xf06c; leaf</option>
										<option value="fa-leanpub">&#xf212; leanpub</option>
										<option value="fa-legal">&#xf0e3; legal</option>
										<option value="fa-lemon-o">&#xf094; lemon-o</option>
										<option value="fa-level-down">&#xf149; level-down</option>
										<option value="fa-level-up">&#xf148; level-up</option>
										<option value="fa-life-bouy">&#xf1cd; life-bouy</option>
										<option value="fa-life-buoy">&#xf1cd; life-buoy</option>
										<option value="fa-life-ring">&#xf1cd; life-ring</option>
										<option value="fa-life-saver">&#xf1cd; life-saver</option>
										<option value="fa-lightbulb-o">&#xf0eb; lightbulb-o</option>
										<option value="fa-line-chart">&#xf201; line-chart</option>
										<option value="fa-link">&#xf0c1; link</option>
										<option value="fa-linkedin">&#xf0e1; linkedin</option>
										<option value="fa-linkedin-square">&#xf08c;
											linkedin-square</option>
										<option value="fa-linux">&#xf17c; linux</option>
										<option value="fa-list">&#xf03a; list</option>
										<option value="fa-list-alt">&#xf022; list-alt</option>
										<option value="fa-list-ol">&#xf0cb; list-ol</option>
										<option value="fa-list-ul">&#xf0ca; list-ul</option>
										<option value="fa-location-arrow">&#xf124;
											location-arrow</option>
										<option value="fa-lock">&#xf023; lock</option>
										<option value="fa-long-arrow-down">&#xf175;
											long-arrow-down</option>
										<option value="fa-long-arrow-left">&#xf177;
											long-arrow-left</option>
										<option value="fa-long-arrow-right">&#xf178;
											long-arrow-right</option>
										<option value="fa-long-arrow-up">&#xf176;
											long-arrow-up</option>
										<option value="fa-magic">&#xf0d0; magic</option>
										<option value="fa-magnet">&#xf076; magnet</option>
										<option value="fa-mars-stroke-v">&#xf22a;
											mars-stroke-v</option>
										<option value="fa-maxcdn">&#xf136; maxcdn</option>
										<option value="fa-meanpath">&#xf20c; meanpath</option>
										<option value="fa-medium">&#xf23a; medium</option>
										<option value="fa-medkit">&#xf0fa; medkit</option>
										<option value="fa-meh-o">&#xf11a; meh-o</option>
										<option value="fa-mercury">&#xf223; mercury</option>
										<option value="fa-microphone">&#xf130; microphone</option>
										<option value="fa-mobile">&#xf10b; mobile</option>
										<option value="fa-motorcycle">&#xf21c; motorcycle</option>
										<option value="fa-mouse-pointer">&#xf245;
											mouse-pointer</option>
										<option value="fa-music">&#xf001; music</option>
										<option value="fa-navicon">&#xf0c9; navicon</option>
										<option value="fa-neuter">&#xf22c; neuter</option>
										<option value="fa-newspaper-o">&#xf1ea; newspaper-o</option>
										<option value="fa-opencart">&#xf23d; opencart</option>
										<option value="fa-openid">&#xf19b; openid</option>
										<option value="fa-opera">&#xf26a; opera</option>
										<option value="fa-outdent">&#xf03b; outdent</option>
										<option value="fa-pagelines">&#xf18c; pagelines</option>
										<option value="fa-paper-plane-o">&#xf1d9;
											paper-plane-o</option>
										<option value="fa-paperclip">&#xf0c6; paperclip</option>
										<option value="fa-paragraph">&#xf1dd; paragraph</option>
										<option value="fa-paste">&#xf0ea; paste</option>
										<option value="fa-pause">&#xf04c; pause</option>
										<option value="fa-paw">&#xf1b0; paw</option>
										<option value="fa-paypal">&#xf1ed; paypal</option>
										<option value="fa-pencil">&#xf040; pencil</option>
										<option value="fa-pencil-square-o">&#xf044;
											pencil-square-o</option>
										<option value="fa-phone">&#xf095; phone</option>
										<option value="fa-photo">&#xf03e; photo</option>
										<option value="fa-picture-o">&#xf03e; picture-o</option>
										<option value="fa-pie-chart">&#xf200; pie-chart</option>
										<option value="fa-pied-piper">&#xf1a7; pied-piper</option>
										<option value="fa-pied-piper-alt">&#xf1a8;
											pied-piper-alt</option>
										<option value="fa-pinterest">&#xf0d2; pinterest</option>
										<option value="fa-pinterest-p">&#xf231; pinterest-p</option>
										<option value="fa-pinterest-square">&#xf0d3;
											pinterest-square</option>
										<option value="fa-plane">&#xf072; plane</option>
										<option value="fa-play">&#xf04b; play</option>
										<option value="fa-play-circle">&#xf144; play-circle</option>
										<option value="fa-play-circle-o">&#xf01d;
											play-circle-o</option>
										<option value="fa-plug">&#xf1e6; plug</option>
										<option value="fa-plus">&#xf067; plus</option>
										<option value="fa-plus-circle">&#xf055; plus-circle</option>
										<option value="fa-plus-square">&#xf0fe; plus-square</option>
										<option value="fa-plus-square-o">&#xf196;
											plus-square-o</option>
										<option value="fa-power-off">&#xf011; power-off</option>
										<option value="fa-print">&#xf02f; print</option>
										<option value="fa-puzzle-piece">&#xf12e; puzzle-piece</option>
										<option value="fa-qq">&#xf1d6; qq</option>
										<option value="fa-qrcode">&#xf029; qrcode</option>
										<option value="fa-question">&#xf128; question</option>
										<option value="fa-question-circle">&#xf059;
											question-circle</option>
										<option value="fa-quote-left">&#xf10d; quote-left</option>
										<option value="fa-quote-right">&#xf10e; quote-right</option>
										<option value="fa-ra">&#xf1d0; ra</option>
										<option value="fa-random">&#xf074; random</option>
										<option value="fa-rebel">&#xf1d0; rebel</option>
										<option value="fa-recycle">&#xf1b8; recycle</option>
										<option value="fa-reddit">&#xf1a1; reddit</option>
										<option value="fa-reddit-square">&#xf1a2;
											reddit-square</option>
										<option value="fa-refresh">&#xf021; refresh</option>
										<option value="fa-registered">&#xf25d; registered</option>
										<option value="fa-remove">&#xf00d; remove</option>
										<option value="fa-renren">&#xf18b; renren</option>
										<option value="fa-reorder">&#xf0c9; reorder</option>
										<option value="fa-repeat">&#xf01e; repeat</option>
										<option value="fa-reply">&#xf112; reply</option>
										<option value="fa-reply-all">&#xf122; reply-all</option>
										<option value="fa-retweet">&#xf079; retweet</option>
										<option value="fa-rmb">&#xf157; rmb</option>
										<option value="fa-road">&#xf018; road</option>
										<option value="fa-rocket">&#xf135; rocket</option>
										<option value="fa-rotate-left">&#xf0e2; rotate-left</option>
										<option value="fa-rotate-right">&#xf01e; rotate-right</option>
										<option value="fa-rouble">&#xf158; rouble</option>
										<option value="fa-rss">&#xf09e; rss</option>
										<option value="fa-rss-square">&#xf143; rss-square</option>
										<option value="fa-rub">&#xf158; rub</option>
										<option value="fa-ruble">&#xf158; ruble</option>
										<option value="fa-rupee">&#xf156; rupee</option>
										<option value="fa-safari">&#xf267; safari</option>
										<option value="fa-sliders">&#xf1de; sliders</option>
										<option value="fa-slideshare">&#xf1e7; slideshare</option>
										<option value="fa-smile-o">&#xf118; smile-o</option>
										<option value="fa-sort-asc">&#xf0de; sort-asc</option>
										<option value="fa-sort-desc">&#xf0dd; sort-desc</option>
										<option value="fa-sort-down">&#xf0dd; sort-down</option>
										<option value="fa-spinner">&#xf110; spinner</option>
										<option value="fa-spoon">&#xf1b1; spoon</option>
										<option value="fa-spotify">&#xf1bc; spotify</option>
										<option value="fa-square">&#xf0c8; square</option>
										<option value="fa-square-o">&#xf096; square-o</option>
										<option value="fa-star">&#xf005; star</option>
										<option value="fa-star-half">&#xf089; star-half</option>
										<option value="fa-stop">&#xf04d; stop</option>
										<option value="fa-subscript">&#xf12c; subscript</option>
										<option value="fa-tablet">&#xf10a; tablet</option>
										<option value="fa-tachometer">&#xf0e4; tachometer</option>
										<option value="fa-tag">&#xf02b; tag</option>
										<option value="fa-tags">&#xf02c; tags</option>
										<option value="otros">Otro</option>
									</select> <input type="text"
										class="col-6 form-control  icp icp-auto iconpicker-element iconpicker-input"
										name="field5" id="field5"
										placeholder="�cono del <c:out value="${compTitleSing}"/>"
										readonly="readonly">
								</div>
								<div class="form-group row">
									<label for="field6" class="col-2 col-form-label">ENCABEZADO</label>
									<input type="checkbox" class="col-3 form-control" name="field6"
										id="field6" value="1" /><label for="field7"
										class="col-1 col-form-label"> Si</label>
								</div>
								<div class="form-group row">
									<label for="field7" class="col-2 col-form-label">ESTATUS</label>
									<input type="checkbox" class="col-1 form-control" name="field7"
										id="field7" value="1" /><label for="field7"
										class="col-1 col-form-label"> Activo</label>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cerrar</button>
								<button type="submit" class="btn btn-primary"
									id="btnGuardar<c:out value="${compTitleSing}"/>">
									Guardar
									<c:out value="${compTitleSing}" />
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="contenedorTabla<c:out value="${compTitleSing}"/>">
			<c:out value="${mensaje}" />
			<c:choose>
				<c:when test="${fn:length(submenus) gt 0}">
					<div class="table-responsive rowInicial2"
						id="divTabla<c:out value="${compTitle}"/>">
						<table id="tb_lista<c:out value="${compTitle}"/>"
							class="table table-striped table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th><i class="fa fa-list" aria-hidden="true"></i> ID:</th>
									<th width="12%"><i class="fa fa-list" aria-hidden="true"></i>
										MEN�:</th>
									<th><i class="fa fa-list" aria-hidden="true"></i> SUBMEN�:
									</th>
									<td><i class="fa fa-list" aria-hidden="true"></i> URL:</td>
									<td><i class="fa fa-list" aria-hidden="true"></i> �CONO:</td>
									<td><i class="fa fa-list" aria-hidden="true"></i>
										ENCABEZADO:</td>
									<td><i class="fa fa-list" aria-hidden="true"></i> ESTATUS:
									</td>
									<td><i class="fa fa-list" aria-hidden="true"></i> EDITAR:
									</td>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tfoot>
							<tbody>
								<c:set var="i" value="1"></c:set>
								<c:forEach var="row" items="${submenus}">
									<tr>
										<td><c:out value="${row.idAction}" /></td>
										<td><c:out value="${row.nombreMenu}" /></td>
										<td><c:out value="${row.nombreAction}" /></td>
										<td><c:out value="${row.urlAction}" /></td>
										<td><i class="fa <c:out value="${row.iconoAction}" />"></i>
											(<c:out value="${row.iconoAction}" />)</td>
										<td><img
											src="<c:out value='${row.isHeaderSubmenuDTO.iconoStringBase64}' />"
											id="logo_sistema2"
											width="<c:out value='${row.isHeaderSubmenuDTO.widthIcono}' />"
											height="<c:out value='${row.isHeaderSubmenuDTO.heightIcono}' />"
											alt="icono_status"></img></td>
										<td><img
											src="<c:out value='${row.estatusSubmenuDTO.iconoStringBase64}' />"
											id="logo_sistema"
											width="<c:out value='${row.estatusSubmenuDTO.widthIcono}' />"
											height="<c:out value='${row.estatusSubmenuDTO.heightIcono}' />"
											alt="icono_status"></img></td>
										<td><a role="button"
											data-target="#modal<c:out value="${compTitle}"></c:out>"
											data-toggle="modal" data-new="0"
											data-field1="<c:out value='${row.idAction}' />"
											data-field2="<c:out value='${row.fkApp}' />"
											data-field3="<c:out value='${row.nombreAction}' />"
											data-field4="<c:out value='${row.urlAction}' />"
											data-field5="<c:out value='${row.iconoAction}' />"
											data-field6="<c:out value='${row.isHeader}' />"
											data-field7="<c:out value='${row.statusAction}' escapeXml="false" />"><i
												class="fa fa-pencil"
												title="Editar <c:out value="${compTitleSing}"/> - <c:out value='${row.idAction}' />"></i></a></td>

									</tr>
									<c:set var="i" value="${i+1}"></c:set>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-info">
						<strong>Sin registros!</strong> La consulta solicitada ha
						retornado ningun resultado.
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<jsp:include page="../footer/footer.jsp"></jsp:include>
	<script type="text/javascript">
			var elemento = '<c:out value="${compTitleSing}"/>';
			var elementos = '<c:out value="${compTitle}"/>';
			
		</script>
	<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
	<script src="js/tether.min.js" type="text/javascript"></script>
	<script src="js/drop.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/base/base.js" type="text/javascript"></script>
	<script src="js/jquery.inputmask.bundle.min.js" type="text/javascript"></script>
	<script src="js/sweetalert2.min.js" type="text/javascript"></script>
	<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script src="js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
	<script src="js/config/configJS.js" type="text/javascript"></script>

</body>
</html>