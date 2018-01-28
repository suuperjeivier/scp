package service.bitacora;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.DatabaseGateway4_Ordenes;
import dao.bitacora.BitacoraDAO;
import dao.documentos.DocumentosDAO;
import dao.empleado.EmpleadoDAO;
import dao.empleadoHorario.EmpleadoHorarioDAO;
import dao.listados.ListadosDAO;
import dao.listados.TipoEstatusDAO;
import dto.bitacora.BitacoraDTO;
import dto.bitacora.BitacoraEmpleadoDTO;
import dto.bitacora.PermisoDTO;
import dto.empleado.EmpleadoDTO;
import dto.empleado.EmpleadoHorarioDTO;
import dto.listados.TipoEstatusDTO;
import dto.logs.ErroresDTO;
import dto.logs.MensajesDTO;
import dto.user.UserDTO;
import herramientas.FechaDTO;
import herramientas.HoraDTO;
import herramientas.archivos.ProcesarArchivos;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class BitacoraService {
	
	private BitacoraDAO bitacoraDAO;
	private EmpleadoDAO empleadoDAO;
	private ListadosDAO listadosDAO;
	private TipoEstatusDAO tipoEstatusDAO;
	private ProcesarArchivos procesarArchivos;
	private DocumentosDAO documentosDAO;
	
	public BitacoraService(){
		if(this.getBitacoraDAO() == null){
			this.setBitacoraDAO(new BitacoraDAO());
		}
		if(this.getEmpleadoDAO() == null){
			this.setEmpleadoDAO(new EmpleadoDAO(getBitacoraDAO().getDatabase()));
		}
		if(this.getListadosDAO() == null){
			this.setListadosDAO(new ListadosDAO(getEmpleadoDAO().getDatabase()));
		}
		if(this.getTipoEstatusDAO() == null){
			this.setTipoEstatusDAO(new TipoEstatusDAO(getListadosDAO().getDatabase()));
		}
		if(this.getDocumentosDAO() == null){
			this.setDocumentosDAO(new DocumentosDAO(getTipoEstatusDAO().getDatabase()));
		}
		if(this.getProcesarArchivos() == null){
			this.setProcesarArchivos(new ProcesarArchivos());
		}
		
	}
	
	/**
	 * @return the tipoEstatusDAO
	 */
	public TipoEstatusDAO getTipoEstatusDAO() {
		return tipoEstatusDAO;
	}

	/**
	 * @param tipoEstatusDAO the tipoEstatusDAO to set
	 */
	public void setTipoEstatusDAO(TipoEstatusDAO tipoEstatusDAO) {
		this.tipoEstatusDAO = tipoEstatusDAO;
	}

	/**
	 * @return the bitacoraDAO
	 */
	public BitacoraDAO getBitacoraDAO() {
		return bitacoraDAO;
	}

	/**
	 * @param bitacoraDAO the bitacoraDAO to set
	 */
	public void setBitacoraDAO(BitacoraDAO bitacoraDAO) {
		this.bitacoraDAO = bitacoraDAO;
	}

	/**
	 * @return the empleadoDAO
	 */
	public EmpleadoDAO getEmpleadoDAO() {
		return empleadoDAO;
	}

	/**
	 * @param empleadoDAO the empleadoDAO to set
	 */
	public void setEmpleadoDAO(EmpleadoDAO empleadoDAO) {
		this.empleadoDAO = empleadoDAO;
	}

	/**
	 * @return the listadosDAO
	 */
	public ListadosDAO getListadosDAO() {
		return listadosDAO;
	}

	/**
	 * @param listadosDAO the listadosDAO to set
	 */
	public void setListadosDAO(ListadosDAO listadosDAO) {
		this.listadosDAO = listadosDAO;
	}	

	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void saveAttendanceRecord(HttpServletRequest request, HttpServletResponse response, UserDTO user)
	{		
		String sku = request.getParameter("inputSku");
		System.out.println(sku);
		if(sku != null && !sku.isEmpty()){
//			EmpleadoDTO empleado = getEmpleadoDAO().selectEmpleadoPorSku(sku, 1);
			/*if(empleado != null){*/
				BitacoraDTO attendanceRecord = new BitacoraDTO();
				attendanceRecord.setUsuarioInsercion(user);
				if(request.getParameter("horaRegistro") != null){					
					attendanceRecord.getEmpleado().setSkuEmpleado(Integer.parseInt(sku));
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
					formatter = formatter.withLocale( Locale.US );// Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
					HoraDTO horaRegistro = new HoraDTO();					
					LocalTime hora = LocalTime.parse(LocalTime.parse(request.getParameter("horaRegistro")).format(formatter));
					horaRegistro.setHoraLT(hora);
					System.out.println("SKU: " + sku + " Hora: " + hora);
//					Gson gson = new Gson();
					System.out.println(request.getParameter("idEmpleado"));
					int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
//					EmpleadoDTO empleadoDTO = gson.fromJson(, EmpleadoDTO.class);
					
					FechaDTO fechaRegistro = new FechaDTO();		
					formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			    	LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));	
			    	fechaRegistro.setFechaLD(date);
			    	attendanceRecord.setFechaRegistro(fechaRegistro);
			    	attendanceRecord.setHoraRegistro(horaRegistro);
			    	
			    	EmpleadoHorarioDTO empleadoHorarioDTO = new EmpleadoHorarioDTO();
			    	EmpleadoHorarioDAO empleadoHorarioDAO = new EmpleadoHorarioDAO();
			    	empleadoHorarioDTO.setHorario(empleadoHorarioDAO.selectHorarioDTOEnDiaPorEmpleado(date.getDayOfMonth(), date.getMonthValue(), date.getYear(), idEmpleado));//empleadoDTO.getIdEmpleado()
			    	Long nanos = empleadoHorarioDTO.getHorario().getHoraRetardo().toNanoOfDay();
			    	System.out.println("NANOS para suma: " + nanos );
			    	LocalTime horaEntrada1Empleado = empleadoHorarioDTO.getHorario().getHoraEntrada().getHoraLT();
			    	LocalTime horaEntradaConTolerancia =  horaEntrada1Empleado.plusNanos(nanos);
			    	
			    	//se busca un registro anterior en el dia
			    	BitacoraDTO registroAnt = getBitacoraDAO().selectRegistroBitacoraDTOEmpleadoEnFecha(Integer.parseInt(sku), date.toString());
			    	MensajesDTO mensaje = new MensajesDTO();
			    	
			    	//COMIENZA DECISION DE TIPO DE REGISTRO
			    	if(registroAnt != null){
			    		switch (registroAnt.getTipoRegistro().getEstatusInt()) {//QUE TiPO DE REGISTRO anterior ES??
							case 4: //entrada intermedia
							case 5://entrada
							case 6://entrada ya procesada
							case 7://entrada adelantada
							case 8://entrada con tolerancia
//									ENTRADA REGISTRADA PREVIAMENTE									
								mensaje.setMensaje("ENTRADA REGISTRADA PREVIAMENTE");
								System.out.println(mensaje.getMensaje());
								break;
							case 20:
							case 15:
							case 17:
							case 38:
							default:								
								mensaje.setMensaje("SALIDA REGISTRADA PREVIAMENTE");
								System.out.println(mensaje.getMensaje());
//								SALIDA REGISTRADA PREVIAMENTE
								break;
						}
//			    		
			    	}else{
//			    		SIN REGISTRO, PROCESO PARA REGISTRAR ENTRADA NORMAL DEL PRIMER REGISTRO DEL DIA
//			    		tambien checar lo del los horarios de descanso y guardias nocturnas
			    		/**
			    		 * --
			    		 *-VERIFCAR PARA HORARIOS DE DIA SIGUIENTE
			    		 * --
			    		 */
			    		if(horaEntradaConTolerancia.compareTo(hora) < 0){
				    		//registro tarde
			    			attendanceRecord.getTipoRegistro().setEstatusInt(10);
			    			mensaje.setMensaje("ENTRADA TARDE (RETARDO)");
							System.out.println(mensaje.getMensaje());			    			
				    	}else{
//				    		A TIEMPO, TEMPRANO O EN TOLERANCIA		
				    		if(horaEntrada1Empleado.compareTo(hora) < 0){
				    			attendanceRecord.getTipoRegistro().setEstatusInt(8);
					    		mensaje.setMensaje("ENTRADA EN TOLERANCIA");
								System.out.println(mensaje.getMensaje());	
				    		}else{
				    			if(horaEntrada1Empleado.compareTo(hora) == 0){
				    				attendanceRecord.getTipoRegistro().setEstatusInt(5);
				    				mensaje.setMensaje("ENTRADA EN TIEMPO");
									System.out.println(mensaje.getMensaje());
				    			}else{
				    				mensaje.setMensaje("ENTRADA ADELANTADA");
				    				attendanceRecord.getTipoRegistro().setEstatusInt(7);
									System.out.println(mensaje.getMensaje());
				    			}
				    		}
				    	}			    		
			    	}
			    	//TERMINA DECISION DE TIPO DE REGISTRO
			    	
			    	
//					int res = getBitacoraDAO().inserAttendanceRecord(attendanceRecord);
			    	int res = 1;
					if (res > 0){
//						response.getWriter().append("1");
						String json = new Gson().toJson(mensaje);
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						try {
							response.getWriter().write(json);
						} catch (IOException e) {
							e.printStackTrace();
						};			
					}else{
						try {
							response.getWriter().append("0");
						} catch (IOException e) {				
							e.printStackTrace();
						}
					}
				}else{
					System.err.println("sin hora");
				}
			/*}else{
				System.out.println("Empleado no encontrado!");
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				};
			}*/
		}else{
			System.out.println("sku vacio");
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectLast10AttendanceRecords(HttpServletRequest request, HttpServletResponse response)
	{
		Vector<ErroresDTO> vErrores = new Vector<ErroresDTO>();
		Vector<BitacoraDTO> lista = getBitacoraDAO().selectRegistrosBitacora(10);
		if (lista.size()>0){
			request.setAttribute("registros", lista);			
		}else{
			ErroresDTO error = new ErroresDTO();
			error.setTituloError("Sin registros!");
			error.setMensajeError("No se encontraron registros con los filtros especificados");
			vErrores.add(error);
//			System.out.println("Error " + vErrores.size());
			request.setAttribute("errores", vErrores);
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void consultarBitacora(HttpServletRequest request, HttpServletResponse response)
	{
		Vector<ErroresDTO> vErrores = new Vector<ErroresDTO>();
		Vector<BitacoraDTO> Lista = getBitacoraDAO().selectRegistrosBitacora();
		if (Lista.size()>0){
			request.setAttribute("registros", Lista);			
		}else{
			ErroresDTO error = new ErroresDTO();
			error.setTituloError("Sin registros!");
			error.setMensajeError("No se encontraron registros con los filtros especificados");
			vErrores.add(error);
//			System.out.println("Error " + vErrores.size());
			request.setAttribute("errores", vErrores);
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void consultarBitacoraFiltros(HttpServletRequest request, HttpServletResponse response)
	{	
		Vector<ErroresDTO> vErrores = new Vector<ErroresDTO>();
		HashMap<String, String> hash = new HashMap<String, String>();
		hash = makeFiltersBitacoraAsitencia(request, hash);
		Vector<BitacoraDTO> Lista = getBitacoraDAO().selectRegistrosBitacoraFiltros(hash);
		if (Lista.size()>0){
			request.setAttribute("registros", Lista);			
		}else{
			ErroresDTO error = new ErroresDTO();
			error.setTituloError("Sin registros!");
			error.setMensajeError("No se encontraron registros con los filtros especificados");
			vErrores.add(error);
//			System.out.println("Error " + vErrores.size());
			request.setAttribute("errores", vErrores);
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void inicializaListaAsistenciaJSP(HttpServletRequest request, HttpServletResponse response)
	{	
		Vector<TipoEstatusDTO> Lista = getTipoEstatusDAO().selectTiposEstatusDTO("bitacora");
		if(Lista != null){
			if (Lista.size()>0){
				request.setAttribute("tiposRegs", Lista);			
			}else{
				System.out.println("Error init en  inicializaListaAsistenciaJSP, vector menos a 0: " + Lista.size());
			}
		}else{
			System.out.println("Lista == null en inicializaListaAsistenciaJSP");
		}
		
	}
	
	/**
	 * 	
	 * @param request
	 * @param response
	 */
	public void consultarListaAsistencia(HttpServletRequest request, HttpServletResponse response)
	{
		Vector<ErroresDTO> vErrores = new Vector<ErroresDTO>();
		Vector<BitacoraEmpleadoDTO> Lista = getBitacoraDAO().selectRegistrosAsistencia();
		if (Lista.size()>0){
			request.setAttribute("registros", Lista);			
		}else{
			ErroresDTO error = new ErroresDTO();
			error.setTituloError("Sin registros!");
			error.setMensajeError("No se encontraron registros con los filtros especificados");
			vErrores.add(error);
			System.out.println("Sin registros " + vErrores.size());
			request.setAttribute("errores", vErrores);
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void consultarListaAsistenciaFiltros(HttpServletRequest request, HttpServletResponse response)
	{		
		Vector<ErroresDTO> vErrores = new Vector<ErroresDTO>();		
		HashMap<String, String> hash = new HashMap<String, String>();
		hash = makeFiltersBitacoraAsitencia(request, hash);
		Vector<BitacoraEmpleadoDTO> Lista = getBitacoraDAO().selectRegistrosAsistenciaFiltros(hash);
		if (Lista.size()>0){
			request.setAttribute("registros", Lista);			
		}else{
			
			ErroresDTO error = new ErroresDTO();
			error.setTituloError("Sin registros!");
			error.setMensajeError("No se encontraron registros con los filtros especificados");
			vErrores.add(error);
//			System.out.println("Error " + vErrores.size());
			request.setAttribute("errores", vErrores);
		}	
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void consultarListaAsistencia2(HttpServletRequest request, HttpServletResponse response)
	{
		Vector<ErroresDTO> vErrores = new Vector<ErroresDTO>();
		Vector<BitacoraEmpleadoDTO> Lista = getBitacoraDAO().selectRegistrosAsistencia2();
		if(Lista != null){
			if (Lista.size()>0){
				request.setAttribute("registros", Lista);			
				if(Lista.get(0).getRegistrosAsistencia() != null && Lista.get(0).getRegistrosAsistencia().size() > 0){
					request.setAttribute("dias", Lista.get(0).getRegistrosAsistencia());
				}else{
					System.out.println("Lista < 0 en consultarListaAsistencia2");
				}
			}else{
				ErroresDTO error = new ErroresDTO();
				error.setTituloError("Sin registros!");
				error.setMensajeError("No se encontraron registros con los filtros especificados");
				vErrores.add(error);
				System.out.println("Sin registros " + vErrores.size());
				request.setAttribute("errores", vErrores);
			}
		}else{
			System.out.println("Lista == null en consultarListaAsistencia2");
		}		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void consultarListaAsistenciaFiltros2(HttpServletRequest request, HttpServletResponse response)
	{
		Vector<ErroresDTO> vErrores = new Vector<ErroresDTO>();
		HashMap<String, String> hash = new HashMap<String, String>();
		hash = makeFiltersBitacoraAsitencia(request, hash);
		Vector<BitacoraEmpleadoDTO> Lista = getBitacoraDAO().selectRegistrosAsistenciaFiltros2(hash);
		if (Lista.size()>0){
			request.setAttribute("registros", Lista);		
			if(Lista.get(0).getRegistrosAsistencia() != null && Lista.get(0).getRegistrosAsistencia().size() > 0){
				request.setAttribute("dias", Lista.get(0).getRegistrosAsistencia());
			}
		}else{
			ErroresDTO error = new ErroresDTO();
			error.setTituloError("Sin registros!");
			error.setMensajeError("No se encontraron registros con los filtros especificados");
			vErrores.add(error);
			System.out.println("Sin registros " + vErrores.size());
			request.setAttribute("errores", vErrores);
		}
		
	}
	
	/**
	 * PROCESA LOS REGISTROS DE LA BITACORA [JAVIER]20170314
	 * @param request
	 * @param response
	 */
	public void procesarBitacora(HttpServletRequest request, HttpServletResponse response, UserDTO user)
	{	
		Vector<ErroresDTO> vErrores = new Vector<ErroresDTO>();
		Vector<BitacoraDTO> v = new Vector<BitacoraDTO>();		
		v = getBitacoraDAO().selectRegistrosEmpladosHorarios(user);
		if(v.size() > 0){
			response.setHeader("Content-Type", "text/xml; charset=ISO-8859-1");    		
    		try {
				response.getWriter().append("<div class='alert alert-success'><strong>Correcto!</strong> Incidencias procesadas correctamente!.</div>");
			} catch (IOException e) {				
				e.printStackTrace();
			}   
			
		}else{
			ErroresDTO error = new ErroresDTO();
			error.setTituloError("Sin registros!");
			error.setMensajeError("No se encontraron registros con los filtros especificados");
			vErrores.add(error);
			System.out.println("Sin registros " + vErrores.size());
			request.setAttribute("errores", vErrores);
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void inicializaConsultarPermisos(HttpServletRequest request, HttpServletResponse response)
	{
		Vector<PermisoDTO> v = new Vector<PermisoDTO>();		
		v = getBitacoraDAO().selectPermisos();
		if(v.size() > 0){
			request.setAttribute("permisos", v);	
		}else{
			System.out.println("SIN PERMISOS");
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */	
	public void consultaNombreIdEmpleados(HttpServletRequest request, HttpServletResponse response)
	{
		Vector<EmpleadoDTO> vemp = new Vector<EmpleadoDTO>();
		vemp = getEmpleadoDAO().selectNombreIdEmpleados(0);
		if (vemp.size() > 0){
			request.setAttribute("listaEmpleados", vemp);
		}else{
			System.out.println("SIN EMPLEADOS");
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void consultaNuevoSkuPermiso(HttpServletRequest request, HttpServletResponse response){ 
		int nuevoSkuPermiso = 0;
		int ultimoSku = getBitacoraDAO().selectUltimoSkuPermiso();
		nuevoSkuPermiso = ultimoSku +1;
		if (nuevoSkuPermiso > 0){
			try {							
				response.getWriter().append(String.valueOf(nuevoSkuPermiso));
			} catch (IOException e) {				
				e.printStackTrace();
			}		
		}else{
			try {
				response.getWriter().append("-1");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void consultarPermisosFiltros(HttpServletRequest request, HttpServletResponse response)
	{
		//Vector<ErroresDTO> vErrores = new Vector<ErroresDTO>();
		HashMap<String, String> hash =new HashMap<String, String>();
		hash = FiltrosPermisos(request, hash);
		Vector<PermisoDTO> v = new Vector<PermisoDTO>();
		if (hash.size() > 0){
			v = getBitacoraDAO().selectPermisosFiltros(hash);
			if(v.size() > 0){
				String json = new Gson().toJson(v);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				try {
					response.getWriter().write(json);
				} catch (IOException e) {
					e.printStackTrace();
				}
				//request.setAttribute("permisos", v);   	
			}
		} else {
			/*v = bitacoraDAO.selectPermisos();
			request.setAttribute("permisos", v);*/
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param hash
	 * @return
	 */
	public HashMap<String, String> makeFiltersBitacoraAsitencia(HttpServletRequest request, HashMap<String, String> hash){
		if(request.getParameter("fechaDe") != null){
			hash.put("fechaDe", (String) request.getParameter("fechaDe"));
			request.setAttribute("filtroFechaDe",(String) hash.get("fechaDe"));				
		}
		if(request.getParameter("fechaA") != null){
			hash.put("fechaA", (String) request.getParameter("fechaA"));
			request.setAttribute("filtroFechaA",(String) hash.get("fechaA"));			
		}
		//TODO FECHAS FUTURAS
		String sku = (request.getParameter("idEmpleado") != null ? (String) request.getParameter("idEmpleado") : "-1");
		if(!sku.equals("-1")){	
			hash.put("skuEmpleado", (String) request.getParameter("idEmpleado"));
			request.setAttribute("filtroId", (String) hash.get("skuEmpleado"));
		}
		String nombreEmpleado = (request.getParameter("nombreEmpleado") != null && !request.getParameter("nombreEmpleado").equals("") && request.getParameter("nombreEmpleado").length() > 0 ? (String) request.getParameter("nombreEmpleado") : null);
		if(nombreEmpleado != null){
			hash.put("nombreEmpleado", nombreEmpleado);
			request.setAttribute("filtroNombreEmpleado", (String) hash.get("nombreEmpleado"));			
		}
		
		int tipoEstatus = (request.getParameter("tipoReg") != null && !request.getParameter("tipoReg").equals("") && request.getParameter("tipoReg").length() > 0 ? Integer.parseInt(request.getParameter("tipoReg")) : 0);
		if(tipoEstatus != 0){
			hash.put("tipoReg", String.valueOf(tipoEstatus));
			request.setAttribute("tipoReg", (String) hash.get("tipoReg"));			
		}
		return hash;		
	}
	
	/**
	 * 
	 * @param request
	 * @param hash
	 * @return
	 * @throws JRException 
	 * @throws IOException 
	 */
	public void reporteJasper(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException{		
		 Map<String, Object> parameters = new HashMap<String, Object>();        
	        String path = request.getServletContext().getRealPath("/WEB-INF/");
	        parameters.put("CONTEXT", path);
	        parameters.put("runRemote", false);
	        parameters.put("no_orden", 580);
	        
		
		/*JasperPrint jasperPrint = JasperFillManager.fillReport(path+"/reports/bitacora/asistencias.jasper",  parameters, 
			    new JREmptyDataSource());
			    JasperExportManager.exportReportToPdfFile(jasperPrint, "sample.pdf");*/
	        /****--------**/
		JasperReport jasperReport = null;
        JasperDesign jasperDesign = null;
       
        jasperDesign = JRXmlLoader.load(path+"/reports/ordenes/oficio.jrxml");
        jasperReport = JasperCompileManager.compileReport(jasperDesign);
        OutputStream outputStream = response.getOutputStream(); 
        DatabaseGateway4_Ordenes db4 = new DatabaseGateway4_Ordenes(); 
        byte[] byteStream = JasperRunManager.runReportToPdf(jasperReport, parameters, db4.getConnection() );   
        response.setContentType("application/pdf");
        String fileName = "prueba.pdf";
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        System.out.println("pdf size" + byteStream.length);
       // response.setContentLength(byteStream.length);
        outputStream.write(byteStream,0,byteStream.length);
        outputStream.flush();
        outputStream.close();		
       

		/*try {
			javax.servlet.ServletOutputStream out = response.getOutputStream();
			try
			   {
			      JRReport reporte = (JRReport) JRLoader.loadObjectFromFile("WEB-INF/reporte2.jasper");

			      Map<String, String> parametros = new HashMap<String, String>();
			      parametros.put("autor", "Juan");
			      parametros.put("titulo", "Reporte");

			      JasperPrintFactory jasperPrint = JasperFillManager.fillReportToStream(reporte, parametros, new JRBeanCollectionDataSource(getBitacoraDAO().selectPermisos()));

			      Exporter exporter; 
			      exporter.setExporterInput(SimpleExporterInput(jasperPrint));
			   }
			   catch (Exception e)
			   {
			      e.printStackTrace();
			   }
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (net.sf.jasperreports.engine.JRException e) {
			
			e.printStackTrace();
		}*/
	}
	
	 /** 
	 * @param request
	 * @param hash
	 * @return
	 */
	public HashMap<String, String> FiltrosPermisos(HttpServletRequest request, HashMap<String, String> hash){
		String claveJ = (request.getParameter("claveJustificante") != null ? (String) request.getParameter("claveJustificante") : "-1");
		if(!claveJ.equals("-1")){	
			hash.put("cveJustificante", (String) request.getParameter("claveJustificante"));
			request.setAttribute("filtroCveJustificante", (String) hash.get("cveJustificante"));
		}
		String idEmpleado = (request.getParameter("idEmpleado2") != null ? (String) request.getParameter("idEmpleado2") : "-1");
		if(!idEmpleado.equals("-1")){	
			hash.put("idEmp", (String) request.getParameter("idEmpleado2"));
			request.setAttribute("filtroIdEmpleado", (String) hash.get("idEmp"));
		}
		return hash;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void nuevoPermiso(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		UserDTO usuarioSession = (UserDTO)session.getAttribute("user");
		PermisoDTO permiso = new PermisoDTO();	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatterT = DateTimeFormatter.ofPattern("HH:mm:ss");
		formatter = formatter.withLocale(Locale.US);//01/01/2017
		formatterT = formatterT.withLocale(Locale.US);
		String errores = "";
		try{
			System.out.println("id permiso antes "+request.getParameter("idPermisoHidden"));
			if(request.getParameter("idPermisoHidden") != null && request.getParameter("idPermisoHidden").length() > 0){
				permiso.setIdPermiso(Integer.parseInt(request.getParameter("idPermisoHidden")));
			}
			System.out.println("id permiso "+permiso.getIdPermiso());
			if(request.getParameter("idEmpleado") != null && request.getParameter("idEmpleado").length() > 0){
				permiso.getEmpledoDTO().setIdEmpleado(Integer.parseInt(request.getParameter("idEmpleado")));
			}

			if(request.getParameter("justificacion") != null && request.getParameter("justificacion") != ""){
				permiso.setDescripcionPermiso(request.getParameter("justificacion"));
			}
			
			if(request.getParameter("fechaDePermiso") != null && request.getParameter("fechaDePermiso") != ""){
				permiso.getFechaDePermiso().setFechaLD(LocalDate.parse(request.getParameter("fechaDePermiso"), formatter));
			}
			if(request.getParameter("fechaAPermiso") != null && request.getParameter("fechaAPermiso") != ""){
				permiso.getFechaAPermiso().setFechaLD(LocalDate.parse(request.getParameter("fechaAPermiso"), formatter));
			}
			if(request.getParameter("horaDe") != null && request.getParameter("horaDe").length() > 0){
				permiso.getHoraDePermiso().setHoraLT(LocalTime.parse(request.getParameter("horaDe"), formatterT));
			}
			if(request.getParameter("horaA") != null && request.getParameter("horaA").length() > 0){
				permiso.getHoraAPermiso().setHoraLT(LocalTime.parse(request.getParameter("horaA"), formatterT));
			}
			if(request.getParameter("statusJustificante") != null && request.getParameter("statusJustificante").length() > 0){
				permiso.getEstatusDTO().setIdTipoEstatus(Integer.parseInt(request.getParameter("statusJustificante")));
			}
			if(request.getParameter("tipoJustificante") != null && request.getParameter("tipoJustificante").length() > 0 && request.getParameter("tipoJustificante") != ""){
				permiso.getMotivoDTO().setIdTipoEstatus(Integer.parseInt(request.getParameter("tipoJustificante")));
			}
			try {
				if(request.getPart("archivoTarjeta") != null && request.getPart("archivoTarjeta").getSize() > 0){
					permiso.getArchivoTarjeta().setParteArchivo(getProcesarArchivos().procesarArchivosRequest(request, response, "archivoTarjeta"));
					permiso.setArchivoTarjeta(getProcesarArchivos().armaArchivoDTO(permiso.getArchivoTarjeta(), "permisos/"));
					permiso.getArchivoTarjeta().setIdDocumento(getProcesarArchivos().insertarArchivo(permiso.getArchivoTarjeta()));
					getProcesarArchivos().guardaArchivoEnRuta(permiso.getArchivoTarjeta(), permiso.getArchivoTarjeta().getIdDocumento()+ "_" +permiso.getArchivoTarjeta().getParteArchivo().getSubmittedFileName());
					System.out.println(permiso.getArchivoTarjeta().getParteArchivo().getSubmittedFileName());
				}else{
					errores += ", archivoTarjeta";
				}
			} catch (IOException e) {				
				e.printStackTrace();
			} catch (ServletException e) {				
				e.printStackTrace();
			}
			System.out.println("tipo: "+request.getParameter("tipoJustificante"));
			
		}catch(Exception error){
			System.out.println(error);
			try {
				response.getWriter().append("-2");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(errores);
		//int editarGlobal = -1;
		if (permiso.getIdPermiso() > 0){
			permiso.setUsuarioActualizacionDTO(usuarioSession);
			int editar = getBitacoraDAO().actualizarPermiso(permiso);
			if(editar > 0){
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else if(editar == 0){
				System.out.println("Error en databasegateway, nuevoPermisoService: editaPermiso");
				try {
					response.getWriter().append("0");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("Error en ListadosDAO, nuevoPermisoService: editaPermiso");
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}else{
//			getProcesarArchivos().insertarArchivo(permiso.getArchivoTarjeta());
			permiso.setUsuarioCreacionDTO(usuarioSession);
			int res = getBitacoraDAO().insertaNuevoPermiso(permiso);
//			int docRes = 
			if(res > 0){
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else if(res == 0){
				System.out.println("Error en databasegateway, nuevoPermisoService: insertaNuevoPermiso");
				try {
					response.getWriter().append("0");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("Error en ListadosDAO, nuevoPermisoService: insertaNuevoPermiso");
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @return the procesarArchivos
	 */
	public ProcesarArchivos getProcesarArchivos() {
		return procesarArchivos;
	}

	/**
	 * @param procesarArchivos the procesarArchivos to set
	 */
	public void setProcesarArchivos(ProcesarArchivos procesarArchivos) {
		this.procesarArchivos = procesarArchivos;
	}

	/**
	 * @return the documentosDAO
	 */
	public DocumentosDAO getDocumentosDAO() {
		return documentosDAO;
	}

	/**
	 * @param documentosDAO the documentosDAO to set
	 */
	public void setDocumentosDAO(DocumentosDAO documentosDAO) {
		this.documentosDAO = documentosDAO;
	}
	
}
