package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.user.UserDTO;
import service.login.LoginService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(description = "Clase que responde con el jsp de login", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = (String) request.getParameter("action");
		HttpSession session = request.getSession();
    	if(session != null){
    		if(session.getAttribute("user") != null && !session.getAttribute("user").equals(null)){
//    			System.out.println(session.getAttribute("user").getClass().getName());
    			if(!session.getAttribute("user").getClass().getName().equals("java.lang.String")){
		    		UserDTO user = (UserDTO)session.getAttribute("user");
		    		if(user != null){
		    			if(action != null){
			    			if(action.equals("init")){
		    					primerServicio(request, response);
		    				}else if(action.equals("doExit")){
		    					doExit(request, response);
		    				}else{
		    					System.out.println("El servicio en login no tiene accion1 redireccionando a login...0!");
		    					doExit(request, response);
		    				}
		    			}else{
		    				System.out.println("El servicio en login no tiene accion1 redireccionando a login...1!");
		    				login(request, response);
		    			}
		    		}else{
		    			login(request, response);
		    		}
    			}else{
    				if(action != null){
        				if(action.equals("try")){
        					login(request, response);    				
        				}else if(action.equals("doLogin")){
        					doLogin(request, response);
        				}else if(action.equals("doExit")){
	    					doExit(request, response);
	    				}else{
        					System.out.println("El servicio no tiene accion0 redireccionando a login por edicion de url!");
        					login(request, response);
        				}
        			}else{
        				System.out.println("El servicio en login no tiene accion1 redireccionando a login...2!");
        				login(request, response);
        			}
    			}
    		}else{
    			if(action != null){
    				if(action.equals("try")){
    					login(request, response);    				
    				}else if(action.equals("doLogin")){
    					doLogin(request, response);
    				}else if(action.equals("doExit")){
    					doExit(request, response);
    				}else if(action.equals("init")){
    					login(request, response);
    				}else{
    					System.out.println("El servicio no tiene accion0!");
    				}
    			}else{
    				System.out.println("El servicio en login no tiene accion1 redireccionando a login...2!");
    				login(request, response);
    			}
    		}
    	}else{
    		login(request, response);
    	}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void primerServicio(HttpServletRequest request, HttpServletResponse response){		
		LoginService loginService = new LoginService();		
//		System.out.println("Iniciando..(primerServicio)");
		loginService.configSistemService(request, response);
		String primerServicio = loginService.primerServicioService(request, response);		
		if(primerServicio != null){
//			System.out.println("Iniciando..(primerservicio)"+primerServicio);
			RequestDispatcher r = getServletContext().getRequestDispatcher(primerServicio);
			try {
				r.forward(request, response);
			} catch (ServletException | IOException e) {			
				e.printStackTrace();
			}
		}else{
			System.out.println("Sin primer servicio");
			login(request, response);
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void login(HttpServletRequest request, HttpServletResponse response){
		LoginService loginService = new LoginService();		
		loginService.configSistemLoginService(request, response);
		RequestDispatcher r = request.getRequestDispatcher("/jsp/security/dologin.jsp");
		try {
			r.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void doLogin(HttpServletRequest request, HttpServletResponse response){		
		LoginService loginService = new LoginService();
		loginService.doLoginService(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void doExit(HttpServletRequest request, HttpServletResponse response){		
		LoginService loginService = new LoginService();
		loginService.doExitService(request, response);
		
		RequestDispatcher r = request.getRequestDispatcher("/C4");
		try {
			r.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
