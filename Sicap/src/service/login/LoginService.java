package service.login;

import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import dao.ConfigDAO;
import dao.user.UserDAO;
import dto.ConfigDTO;
import dto.user.UserDTO;
import dto.user.menu.MenuDTO;

public class LoginService {
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void doLoginService(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();		
		session.removeAttribute("user");
		final String _USERNAME = request.getParameter("user");
		final String _PASS = request.getParameter("pass");
		try {
			if(_USERNAME.length() > 0){				
				UserDTO userDTO = authenticateJndi(session,_USERNAME, _PASS);
				if( userDTO == null){//BUSCA USUARIO LOCAL				
					UserDAO userDAO = new UserDAO();			
					userDTO =  userDAO.selectUserPorUsernameAndPassword(_USERNAME, _PASS);
					if(userDTO != null){//USUARIO LOCAL ENCONTRADO
						Vector<MenuDTO> menuUsuario = userDAO.selectUserMenu(userDTO);
						if(menuUsuario != null && menuUsuario.size() > 0){
							userDTO.setMenu(menuUsuario);
						}else{
							System.out.println("Sin menu de usuario configurado");
						}
						if(userDTO.getImgAvatarDTO().getImagenBlob() == null || userDTO.getImgAvatarDTO().getImgStringBase64() == null || userDTO.getImgAvatarDTO().getImgStringBase64().isEmpty()){
							try {		           
								if(userDTO.getFotoEmpleado() != null && userDTO.getFotoEmpleado().length > 0){
									byte[] encodedImage = userDTO.getFotoEmpleado();
									StringBuilder sb = new StringBuilder();
									sb.append("data:image/png;base64,");
									sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(encodedImage, false)));
									userDTO.setFotoEmpleadoBase64(sb.toString());
									userDTO.getImgAvatarDTO().setImgStringBase64(userDTO.getFotoEmpleadoBase64());			            
								}else{
									System.out.println("Sin foto de empleado para usuario de dominio con configuracion local");
								}			            
							} catch (Exception e) {
								e.printStackTrace();
							}

						}	
						session.setMaxInactiveInterval(userDTO.getSessionTimeOut());
						session.setAttribute("user", userDTO);
						session.setAttribute("userName", userDTO.getUserName());
						try {
							response.getWriter().append("1");
							System.out.println("######-- INICIO DE SESION DE " + userDTO.getNombreCompletoEmpleado() + ", EN " + Calendar.getInstance().getTime().toString() + " --######### ");
						} catch (IOException e) {				
							e.printStackTrace();
						}
					}else{
						System.out.println("######-- INICIO DE SESION FALLDO EN " + Calendar.getInstance().getTime().toString() + " 0--######### ");
						try {
							response.getWriter().append("0");
						} catch (IOException e) {				
							e.printStackTrace();
						}
					}
				}else{//USUARIO DE DOMINIO // BUSCA CONFIGURACION LOCAL
					UserDAO userDAO = new UserDAO();
					UserDTO userDTO2 =  userDAO.selectUserPorUsername(_USERNAME);
					if(userDTO2 == null){//SIN USUARIO LOCAL //SIN CONFIGURACION LOCAL
						userDTO.setUserId(14);						
						String nombre = null, apellidos = null, correo = null;
						nombre = userDTO.getNombreEmpleado();
						apellidos = userDTO.getApellidosEmpleado();
						correo = userDTO.getCorreoElectronicoEmpleado();						
						userDTO = userDAO.selectUserPorUserId(userDTO.getUserId());//BUSCA USARUIO GENERICO
						userDTO.setMenu(userDAO.selectUserMenu(userDTO));
						userDTO.setNombreEmpleado(nombre);
						userDTO.setApellidosEmpleado(apellidos);
						userDTO.setUserEmail(correo);
						userDTO.setUserName(_USERNAME);
						userDTO.setNombreCompletoEmpleado(userDTO.getNombreEmpleado() + " " + userDTO.getApellidosEmpleado());
						//					session.setMaxInactiveInterval(userDTO.getSessionTimeOut());
						//					session.setAttribute("user", userDTO);
						try {
							response.getWriter().append("-2");
							System.out.println("######-- INICIO DE SESION 2 DE " + userDTO.getNombreCompletoEmpleado() + ", EN " + Calendar.getInstance().getTime().toString() + " --######### ");
						} catch (IOException e) {				
							e.printStackTrace();
						}
					}else{ // CONFIGURACION LOCAL DE USUARIO DE DOMINIO ENCONTRADA
						Vector<MenuDTO> menuUsuario = userDAO.selectUserMenu(userDTO2);
						if(menuUsuario != null && menuUsuario.size() > 0){
							userDTO2.setMenu(menuUsuario);
						}else{
							System.out.println("Sin menu de usuario configurado");
						}						
						if(userDTO2.getImgAvatarDTO().getImagenBlob() == null || userDTO2.getImgAvatarDTO().getImgStringBase64() == null || userDTO2.getImgAvatarDTO().getImgStringBase64().isEmpty()){
							try {		           
								if(userDTO2.getFotoEmpleado() != null && userDTO2.getFotoEmpleado().length > 0){
									byte[] encodedImage = userDTO2.getFotoEmpleado();
									StringBuilder sb = new StringBuilder();
									sb.append("data:image/png;base64,");
									sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(encodedImage, false)));
									userDTO2.setFotoEmpleadoBase64(sb.toString());
									userDTO2.getImgAvatarDTO().setImgStringBase64(userDTO2.getFotoEmpleadoBase64());			            
								}else{
									System.out.println("Sin foto de empleado para usuario de dominio con configuracion local");
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							//						response.setContentType("image/jpg");
						}
						session.setMaxInactiveInterval(userDTO2.getSessionTimeOut());
						session.setAttribute("user", userDTO2);
						session.setAttribute("userName", userDTO.getUserName());
						/*SendMail mail = new SendMail();
						mail.sendMail(userDTO2.getNombreCompletoEmpleado());*/
						try {
							response.getWriter().append("1");
							System.out.println("######-- INICIO DE SESION 3 DE " + userDTO2.getNombreCompletoEmpleado() + ", EN " + Calendar.getInstance().getTime().toString() + " --######### ");
						} catch (IOException e) {				
							e.printStackTrace();
						}
					}	
				}
			}else{

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public UserDTO authenticateJndi(HttpSession session, String username, String password) throws Exception{
		try
	    {
			 UserDTO us= null;
	        // Set up the environment for creating the initial context
	        Hashtable<String, String> env = new Hashtable<String, String>();
	        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        env.put(Context.PROVIDER_URL, "ldap://dc2.c4tabasco.gob.mx:389");
	        // 
	        env.put(Context.SECURITY_AUTHENTICATION, "simple");
	        env.put(Context.SECURITY_PRINCIPAL, username+"@c4tabasco.gob.mx"); //we have 2 \\ because it's a escape char
	        env.put(Context.SECURITY_CREDENTIALS, password);

	        // Create the initial context

	        LdapContext context = new InitialLdapContext(env, null);
	        boolean result2 = context != null;
	        SearchControls ctx = new SearchControls();
	        String[] attrIDs = { "distinguishedName",
                    "sn",
                    "givenname",
                    "mail",
                    "memberOf",
                    "telephonenumber"};
//	        String filter = "(uid={0})"; // You might want to limit search to user objects only based on objectClass
//	        String []filterAttributes = {username};
//	        String baseDN = "CN=Sistema scp. Administrativo,OU=\"Otros Usuarios\",DC=c4tabasco,DC=gob,DC=mx"; // Replace this with the real baseDN
//	        String baseDN = "CN=Javier Brito Pacheco,OU=\"Unidad de Administracion de Sistemas\",DC=c4tabasco,DC=gob,DC=mx"; // Replace this with the real baseDN
	        ctx.setReturningAttributes(attrIDs);
	        ctx.setSearchScope(SearchControls.SUBTREE_SCOPE);
//	        NamingEnumeration<SearchResult> answers = context.search(baseDN, filter, filterAttributes, ctrls);
	        if(result2){
		        NamingEnumeration<SearchResult> answers = context.search("DC=c4tabasco,DC=gob,DC=mx", "sAMAccountName="+ username, ctx);
	
		        
	//	        NamingEnumeration<javax.naming.directory.SearchResult> answers = context.search("dc=c4tabasco,dc=gob,dc=mx", "(uid=" + username + ")", ctrls);
	//	        javax.naming.directory.SearchResult result = null;
		        if(answers != null){  
		        	if (answers.hasMoreElements()) {
		        		Attributes attrs = ((SearchResult) answers.next()).getAttributes();
		                System.out.println("distinguishedName "+ attrs.get("distinguishedName"));
		                System.out.println("givenname "+ attrs.get("givenname"));
		                System.out.println("sn "+ attrs.get("sn"));
		                System.out.println("mail "+ attrs.get("mail"));
		                System.out.println("telephonenumber "+ attrs.get("telephonenumber"));
		                System.out.println("memberOf "+ attrs.get("memberOf"));
		                us= new UserDTO();		                
		                us.setNombreEmpleado(((String)attrs.get("givenname").get()) != null? (String)attrs.get("givenname").get() : "No name");
		                us.setApellidosEmpleado((String)attrs.get("sn").get() != null? (String)attrs.get("sn").get() : "No sn");//ERROR NULL POINTER CUANDO EL USUARIO NO TIENE APELLIDO!
		                us.setCorreoElectronicoEmpleado((String)attrs.get("mail").get() != null? (String)attrs.get("mail").get() : "No mail");
		                us.setUserEmail(us.getCorreoElectronicoEmpleado());
		                
		            }else{
		                throw new Exception("Invalid User");
		            }
//						SearchResult searchResult = (SearchResult) answers.nextElement();
//						System.out.println("mail: " + searchResult.getAttributes().get("mail"));
						
					
		        }else{
		        	System.out.println("NULL ANSWERS");
		        }
	        }else{
	        	System.out.println("result2 false");
	        }

		    //String user = result.getNameInNamespace();
		    //System.out.println("USUARIO LDAP" +user);
	        
	            
	        
		    if(context != null){
	        	context.close();
	        }

	        return us;
	    }
	    catch (Exception e)
	    {           
	    	System.out.println(e);
	        return null;
	    }
		
	    /*Properties props = new Properties();
	    props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	    props.put(Context.PROVIDER_URL, "ldap://dc2.c4tabasco.gob.mx:389");
	    props.put(Context.SECURITY_PRINCIPAL, "sAMAccountName@corp.XXX.com");//adminuser - User with special priviledge, dn user
	    props.put(Context.SECURITY_CREDENTIALS, "c4tabasco");//dn user password


	    InitialDirContext context = new InitialDirContext(props);

	    SearchControls ctrls = new SearchControls();
	    ctrls.setReturningAttributes(new String[] { "givenName", "sn","memberOf" });
	    ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);

	    NamingEnumeration<javax.naming.directory.SearchResult> answers = context.search("o=c4tabasco.gob.mx", "(uid=" + username + ")", ctrls);
	    javax.naming.directory.SearchResult result = answers.nextElement();

	    String user = result.getNameInNamespace();

	    try {
	        props = new Properties();
	        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        props.put(Context.PROVIDER_URL, "ldap://dc2.c4tabasco.gob.mx:389");
	        props.put(Context.SECURITY_PRINCIPAL, user);
	        props.put(Context.SECURITY_CREDENTIALS, password);

	   context = new InitialDirContext(props);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;*/
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void doExitService(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(false);		
		if (session != null) {
			try{
				Object o = session.getAttribute("user");
				if(o != null){
					if(!o.getClass().equals("java.lang.String")){
						UserDTO userDTO = (UserDTO) session.getAttribute("user");
						if(userDTO != null){
							session.removeAttribute("user");
							session.invalidate();
							System.out.println("######-- CIERRE DE SESION DE " + userDTO.getNombreCompletoEmpleado() + ", EN " + Calendar.getInstance().getTime().toString() + " --######### ");
						}else{
							System.out.println("######-- INTENTO DE CIERRE DE SESION DE ?? EN " + Calendar.getInstance().getTime().toString() + " --######### ");
						}
					}else{
						System.out.println("######-- CIERRE DE SESION fallido 1 ");
					}
				}else{
					System.out.println("######-- CIERRE DE SESION fallido 2 ");
				}
			}catch(ClassCastException e){
				e.printStackTrace();
			}
		}else{
			System.out.println("######-- INTENTO DE CIERRE DE SESION SIN SESION DE ?? EN " + Calendar.getInstance().getTime().toString() + " --######### ");
		}
			
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void configSistemService(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		ConfigDAO configDAO = new ConfigDAO();
		ConfigDTO config = configDAO.selectConfigSistema();
		if(config != null){
			byte[] encodedImage = config.getImagenSistema();
			StringBuilder sb = new StringBuilder();
			sb.append("data:image/png;base64,");
			sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(encodedImage, false)));
			config.setImagenSistemaBase64(sb.toString());		
			session.setAttribute("configSistema", config);
		}else{
			System.out.println("CONFIG INIT AFTER LOGIN == NULL configSistemService");
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void configSistemLoginService(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		ConfigDAO configDAO = new ConfigDAO();
		ConfigDTO config = configDAO.selectConfigLoginSistema();
		if(config != null){
			byte[] encodedImage = config.getImagenLoginSistema();
			StringBuilder sb = new StringBuilder();
			sb.append("data:image/png;base64,");
			sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(encodedImage, false)));
			config.setImagenLoginSistemaBase64(sb.toString());		
			session.setAttribute("configLoginSistema", config);		
		}else{
			System.out.println("CONFIG SISTEM B4 LOGIN == NULL configSistemLoginService");
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String primerServicioService(HttpServletRequest request, HttpServletResponse response){
		String primerServicio = null;
		HttpSession session = request.getSession();	
		try{
			if(session != null){
				if(session.getAttribute("user") != null && !session.getAttribute("user").equals(null)){
					UserDTO user = (UserDTO)session.getAttribute("user");			
					if(user != null){
						primerServicio = user.getUserConfigDTO().getUserInitService();
					}else{
//						primerServicio = "/jsp/documentos/cargaHorario.jsp";
						primerServicio = "/listados?action=consultarHorariosEmpleados";
					}
					response.setHeader("Location", primerServicio);
				}else{
					System.out.println("sin atributo de sesion user");
				}
			}else{
				System.out.println("sin sesion");
			}
		}catch(ClassCastException e){
			e.printStackTrace();
		}
		return primerServicio;
	}

}
