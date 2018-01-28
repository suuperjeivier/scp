<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>    
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Incio para el sistema SICAP">
    <meta name="author" content="Sistemas C4 tabasco">
    <link rel="manifest" href="./web_app_manifest.json">
    <link rel='shortcut icon' type='image/x-icon' href='./favicon.ico' />

    <title>Iniciar sesión | SICAP</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" type="text/css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/cover.css" type="text/css" rel="stylesheet">
	<link href="./css/logincss.css" type="text/css" rel="stylesheet" >
  </head>

  <body style=" background: url(<c:out value='${configLoginSistema.rutaImgBgLogin}' />);">
	<!-- BEGIN # BOOTSNIP INFO -->
	<div id="background"></div>
	<c:if test="${!empty configLoginSistema.mensajeHeaderLogin}">
		<div class="container-fluid">
			<div class="row">
				<p id="pMensaje">
					<marquee><c:out value='${configLoginSistema.mensajeHeaderLogin}' /></marquee>
				</p>
			</div>
		</div>
	</c:if>
	<div class="container" id="containerLoginForm">
		<div class="row">
			<c:if test="${!empty configLoginSistema.rutaImgLoginFormIzquierda}">
				<div class="col-lg-3"><img id="img-logo-izquierda" alt="imagen logo izquierda"
								src="<c:out value='${configLoginSistema.rutaImgLoginFormIzquierda}' />"
								></div>
			</c:if>
			<c:choose >
				<c:when test="${!empty configLoginSistema.rutaImgLoginFormIzquierda && !empty configLoginSistema.rutaImgLoginFormDerecha}">
					<div class="col-lg-6">
						<div class="modal-dialog" id="main-modal">
					<div class="modal-content">
						<div class="modal-header" align="center">
							<img id="img-logo"
								src="<c:out value='${configLoginSistema.imagenLoginSistemaBase64}' />"
								width="<c:out value='${configLoginSistema.widthImgLoginSistema}' />"
								height="<c:out value='${configLoginSistema.heigthImgLoginSistema}' />">
							<p class="text-center">
								<span
									style="<c:out value='${configLoginSistema.cssTituloLogin}'/> "
									class="titulo"><c:out
										value="${configLoginSistema.tituloLoginSistema}" /> </span>
							</p>
						</div>
						<!-- Begin # DIV Form -->
						<div id="div-forms">
							<!-- Begin # Login Form -->
							<form id="login-form">
								<div class="modal-body">
									<div id="div-login-msg">
										<div id="icon-login-msg"
											class="glyphicon glyphicon-chevron-right"></div>
										<span id="text-login-msg" class="informacion">Use su
											cuenta de dominio.</span>
									</div>
									<input id="login_username" class="form-control" type="text"
										placeholder="Usuario" autocomplete="username" required> <input
										id="login_password" autocomplete="current-password" class="form-control" type="password"
										placeholder="Contraseña" required>
								</div>
								<div class="modal-footer">
									<div id="div-footer-btn">
										<button type="submit" class="btn btn-primary btn-block"
											id="loginBtn">Login</button>
									</div>
								</div>
							</form>
							<!-- End # Login Form -->
						</div>
						<!-- End # DIV Form -->
					</div>
				</div>
				<!-- END # MODAL LOGIN -->
			</div>
				</c:when>
				<c:when test="${!empty configLoginSistema.rutaImgLoginFormIzquierda}">
					<div class="col-lg-6">
					<div class="modal-dialog" id="main-modal">
					<div class="modal-content">
						<div class="modal-header" align="center">
							<img id="img-logo"
								src="<c:out value='${configLoginSistema.imagenLoginSistemaBase64}' />"
								width="<c:out value='${configLoginSistema.widthImgLoginSistema}' />"
								height="<c:out value='${configLoginSistema.heigthImgLoginSistema}' />">
							<p class="text-center">
								<span
									style="<c:out value='${configLoginSistema.cssTituloLogin}'/> "
									class="titulo"><c:out
										value="${configLoginSistema.tituloLoginSistema}" /> </span>
							</p>
						</div>
						<!-- Begin # DIV Form -->
						<div id="div-forms">
							<!-- Begin # Login Form -->
							<form id="login-form">
								<div class="modal-body">
									<div id="div-login-msg">
										<div id="icon-login-msg"
											class="glyphicon glyphicon-chevron-right"></div>
										<span id="text-login-msg" class="informacion">Usa tu
											cuenta de dominio.</span>
									</div>
									<input id="login_username" class="form-control" type="text"
										placeholder="Usuario" required autocomplete="username"> <input
										id="login_password" class="form-control" type="password"
										placeholder="Contraseña" autocomplete="current-password" required>
								</div>
								<div class="modal-footer">
									<div id="div-footer-btn">
										<button type="submit" class="btn btn-primary btn-block"
											id="loginBtn">Login</button>
									</div>
								</div>
							</form>
							<!-- End # Login Form -->
						</div>
						<!-- End # DIV Form -->
					</div>
				</div>
				<!-- END # MODAL LOGIN -->
			</div>
				</c:when>
				<c:when test="${!empty configLoginSistema.rutaImgLoginFormDerecha}">
					<div class="col-lg-9">
					<div class="modal-dialog" id="main-modal">
					<div class="modal-content">
						<div class="modal-header" align="center">
							<img id="img-logo"
								src="<c:out value='${configLoginSistema.imagenLoginSistemaBase64}' />"
								width="<c:out value='${configLoginSistema.widthImgLoginSistema}' />"
								height="<c:out value='${configLoginSistema.heigthImgLoginSistema}' />">
							<p class="text-center">
								<span
									style="<c:out value='${configLoginSistema.cssTituloLogin}'/> "
									class="titulo"><c:out
										value="${configLoginSistema.tituloLoginSistema}" /> </span>
							</p>
						</div>
						<!-- Begin # DIV Form -->
						<div id="div-forms">
							<!-- Begin # Login Form -->
							<form id="login-form">
								<div class="modal-body">
									<div id="div-login-msg">
										<div id="icon-login-msg"
											class="glyphicon glyphicon-chevron-right"></div>
										<span id="text-login-msg" class="informacion">Usa tu
											cuenta de dominio.</span>
									</div>
									<input id="login_username" class="form-control" type="text"
										placeholder="Usuario" required autocomplete="username"> <input
										id="login_password" class="form-control" type="password"
										placeholder="Contraseña" required autocomplete="current-password">
								</div>
								<div class="modal-footer">
									<div id="div-footer-btn">
										<button type="submit" class="btn btn-primary btn-block"
											id="loginBtn">Login</button>
									</div>
								</div>
							</form>
							<!-- End # Login Form -->
						</div>
						<!-- End # DIV Form -->
					</div>
				</div>
				<!-- END # MODAL LOGIN -->
			</div>
				</c:when>				
				<c:otherwise>
					<div class="col-lg-12">
					<div class="modal-dialog" id="main-modal">
					<div class="modal-content">
						<div class="modal-header" align="center">
							<img id="img-logo"
								src="<c:out value='${configLoginSistema.imagenLoginSistemaBase64}' />"
								width="<c:out value='${configLoginSistema.widthImgLoginSistema}' />"
								height="<c:out value='${configLoginSistema.heigthImgLoginSistema}' />">
							<p class="text-center">
								<span
									style="<c:out value='${configLoginSistema.cssTituloLogin}'/> "
									class="titulo"><c:out
										value="${configLoginSistema.tituloLoginSistema}" /> </span>
							</p>
						</div>
						<!-- Begin # DIV Form -->
						<div id="div-forms">
							<!-- Begin # Login Form -->
							<form id="login-form">
								<div class="modal-body">
									<div id="div-login-msg">
										<div id="icon-login-msg"
											class="glyphicon glyphicon-chevron-right"></div>
										<span id="text-login-msg" class="informacion">Usa tu
											cuenta de dominio.</span>
									</div>
									<input id="login_username" class="form-control" type="text"
										placeholder="Usuario" required autocomplete="username"> <input
										id="login_password" class="form-control" type="password"
										placeholder="Contraseña" required autocomplete="current-password">
								</div>
								<div class="modal-footer">
									<div id="div-footer-btn">
										<button type="submit" class="btn btn-primary btn-block"
											id="loginBtn">Login</button>
									</div>
								</div>
							</form>
							<!-- End # Login Form -->
						</div>
						<!-- End # DIV Form -->
					</div>
				</div>
				<!-- END # MODAL LOGIN -->
			</div>
				</c:otherwise>
			</c:choose>
				
			<c:if test="${!empty configLoginSistema.rutaImgLoginFormDerecha}">
				<div class="col-lg-3"><img id="img-logo-derecha" alt="imagen logo derecha"
								src="<c:out value='${configLoginSistema.rutaImgLoginFormDerecha}' />"
								></div>
			</c:if>
		</div>
	</div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./js/jquery-3.1.1.min.js" type="text/javascript"></script>
    <script src="./js/login/loginJS.js" type="text/javascript"></script>
    <script src="./js/tether.min.js" type="text/javascript"></script>
    <script src="./js/popper/popper.min.js" type="text/javascript"></script>
	<script src="./js/popper/popper-utils.min.js" type="text/javascript"></script>
    <script src="./js/bootstrap.min.js" type="text/javascript"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./js/ie10-viewport-bug-workaround.js" type="text/javascript"></script>
    <script src="./js/base/indexJS.js" type="text/javascript"></script>
    
  </body>
</html>