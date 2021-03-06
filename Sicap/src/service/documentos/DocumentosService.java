package service.documentos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.DatabaseGateway4_Ordenes;
import dao.DatabaseGateway5_Sist_Per;
import dao.documentos.DocumentosDAO;
import dao.empleadoHorario.EmpleadoHorarioDAO;
import dao.movimientos.ordenes.OrdenesDAO;
import dto.documentos.DocumentoHorarioDTO;
import dto.empleado.EmpleadoDTO;
import dto.empleado.EmpleadoHorarioDTO;
import dto.horario.HorarioDTO;
import dto.ordenes.DocumentoDTO;
import dto.user.UserDTO;
import herramientas.ArchivoDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class DocumentosService {
	
	private DocumentosDAO documentosDAO;
	
	public DocumentosService(){
		if(this.getDocumentosDAO() == null){
			this.setDocumentosDAO(new DocumentosDAO());
		}
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
	
	
	public void reportesJasper(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException{
		if (request.getParameter("tipo_doc") != null){
			String tipo_doc = request.getParameter("tipo_doc");
			OutputStream outputStream = response.getOutputStream(); 
			Map<String, Object> parameters = new HashMap<String, Object>();        
			String path = request.getServletContext().getRealPath("/WEB-INF/");
			parameters.put("CONTEXT", path);
			parameters.put("runRemote", false);
			JasperReport jasperReport = null;
			JasperDesign jasperDesign = null;
			/*JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
			JRPropertiesUtil jrPropertiesUtil = JRPropertiesUtil.getInstance(jasperReportsContext);
			jrPropertiesUtil.setProperty("net.sf.jasperreports.default.pdf.encoding", "ISO-8859-1");*/
			String fileName = "";
			DatabaseGateway4_Ordenes db4 = null;
			DatabaseGateway5_Sist_Per db5 = null;
			byte[] byteStream = null;
			switch (tipo_doc){
			case "oficio":
			case "orden_doble":
				if(request.getParameter("numero_orden") != null){
					if(!request.getParameter("numero_orden").isEmpty()){
						int numeroOrden = Integer.parseInt(request.getParameter("numero_orden"));
						switch (tipo_doc) {	
						case "oficio":
							jasperDesign = JRXmlLoader.load(path+"/reports/ordenes/oficio_editable.jrxml");					
							fileName = "Oficio_"+numeroOrden+".pdf";					
							break;
						case "orden_doble":
							jasperDesign = JRXmlLoader.load(path+"/reports/ordenes/orden_doble.jrxml");					
							fileName = "Orden_doble_"+numeroOrden+".pdf";					
							break;
						default:
							break;
						}
						parameters.put("no_orden", numeroOrden);
						db4 = new DatabaseGateway4_Ordenes();
						jasperReport = JasperCompileManager.compileReport(jasperDesign);
						byteStream = JasperRunManager.runReportToPdf(jasperReport, parameters, db4.getConnection());   
					}else{
						
					}
				}else{

				}
				break;
			case "lisAsistenciaPersonal":
				if (request.getParameter("fecha1") != null){
					if(!request.getParameter("fecha1").isEmpty()){
						String fecha1 = request.getParameter("fecha1"); 
						String fecha2 = request.getParameter("fecha2");
						jasperDesign = JRXmlLoader.load(path+"/reports/sist_personal/Asistencia.jrxml");
						fileName = "Lista_asistencia_de_"+fecha1+"_a_"+fecha2+".pdf";
						db5 = new DatabaseGateway5_Sist_Per();						
						parameters.put("FechaInicial", fecha1);
						parameters.put("FechaFinal",fecha2);
						jasperReport = JasperCompileManager.compileReport(jasperDesign);
						byteStream = JasperRunManager.runReportToPdf(jasperReport, parameters, db5.getConnection() );   
					}
				}
				break;
			default:
				break;
			}
			if(!fileName.isEmpty()){				
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName);					
				outputStream.write(byteStream,0,byteStream.length);
				outputStream.flush();
				outputStream.close();
			}
		}
	}
	
	public void procesarHorarioArchivoExcel(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		System.out.println("request " + request.getRequestURI());
		session.removeAttribute("horariosEmpleadosDTO");
		session.removeAttribute("archivoEmpleadosDTO");
		Part filePart = null;
		try {
			filePart = request.getPart("horario");
		} catch (IOException | ServletException e1) {
			System.out.print(e1);
			e1.printStackTrace();
		} 
		if(filePart != null){ 	
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.	    
			System.out.println("Nombre " + fileName);
			String rutaResp = "";
			String os = System.getProperty("os.name").toLowerCase();
			System.out.println("OS:" + System.getProperty("os.name").toLowerCase());
			if(os.contains("linux")){
				rutaResp = "/home/jbritop/archivos/horarios/";///home/jbritop/archivos/horarios
			}else{
				rutaResp = "C:/pruebas/horario/";
			}
			File targetFile = new File(rutaResp+fileName); 
			String ext = fileName.substring(fileName.lastIndexOf('.') + 1);

			if(targetFile.getName().endsWith("xlsx")){
				DocumentoHorarioDTO doc = new DocumentoHorarioDTO();
				doc.setNombreDocumento(fileName);

				System.out.println("Hola procesando archivo");
				InputStream fileContent = null;
				try {
					fileContent = filePart.getInputStream();
				} catch (IOException e1) {
					e1.printStackTrace();
				}	    
				try {
					java.nio.file.Files.copy(
							fileContent, 
							targetFile.toPath(), 
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				//	    	doc.setDocumento(targetFile);
				doc.setExtDocumento(ext);

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				formatter = formatter.withLocale( Locale.US );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
				LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));	    	
				doc.setFechaCargaDocumento(date);
				formatter = DateTimeFormatter.ofPattern("HH:mm:ss");	    	
				LocalTime time = LocalTime.parse(LocalTime.now().format(formatter));
				doc.setHoraCargaDocumento(time);
				doc.setRutaDocumento(rutaResp);
				doc.setTamanoDocumento(filePart.getSize());
				


				//COMIENZA LECTURA DEL ARCHIVO XLSX
				try {           
					FileInputStream fis = new FileInputStream(targetFile);
					XSSFWorkbook book = new XSSFWorkbook(fis);
					XSSFSheet sheet = book.getSheetAt(0);

					Iterator<Row> itr = sheet.iterator();
					response.setHeader("Content-Type", "text/xml; charset=ISO-8859-1");
					Vector<EmpleadoHorarioDTO> v = new Vector<EmpleadoHorarioDTO>();
					// Iterating over Excel file in Java
					boolean terminado = false;
					response.getWriter().append("<thead>");	   
					response.getWriter().append("<tr>");				
					while (itr.hasNext()) {
						if(terminado)
							break;
						Row row = itr.next();

						//	    			System.out.println("r" + row.getRowNum());	    			

						EmpleadoDTO empleadoDTO = new EmpleadoDTO();
						EmpleadoHorarioDTO empleadoHorarioDTO = new EmpleadoHorarioDTO();		    		

						if(row.getRowNum() == 4){
							response.getWriter().append("<tbody><tr>");

						}else if(row.getRowNum() == 2)
						{
							response.getWriter().append("<th colspan='4'> </th>");

						}else{
							if(row.getRowNum() > 3){
								if(!terminado)
									response.getWriter().append("<tr>");
							}
						}
						// Iterating over each column of Excel file
						Iterator<Cell> cellIterator = row.cellIterator();
						while (cellIterator.hasNext()) {

							Cell cell = cellIterator.next();
							switch (cell.getCellTypeEnum()) {
							case BLANK:
								//	    					System.out.print("null" + "\t");	   

								if(row.getRowNum() == 2)
								{	    	    				
									response.getWriter().append("<th></th>");	    	    				
								}else{
									if(row.getRowNum() > 3){
										if(!terminado)
											response.getWriter().append("<td></td>");
									}
								}
								break;
							case BOOLEAN:
								//	    					System.out.print("columna: " + cell.getColumnIndex() + ", valor boolean: " + cell.getBooleanCellValue() + "\t");

								if(row.getRowNum() == 2 || row.getRowNum() == 3)
								{	    	    				
									response.getWriter().append("<th>"+cell.getBooleanCellValue()+"</th>");	    	    				
								}else{
									if(row.getRowNum() > 3){
										response.getWriter().append("<td>"+cell.getBooleanCellValue()+"</td>");
									}
								}
								break;
							case ERROR:
								//	    					System.out.print("columna: " + cell.getColumnIndex() + ",valor error: " + cell.getErrorCellValue() + "\t");
								if(row.getRowNum() == 2 || row.getRowNum() == 3)
								{	    	    				
									response.getWriter().append("<th>"+cell.getErrorCellValue()+"</th>");	    	    				
								}else{
									if(row.getRowNum() > 3){
										response.getWriter().append("<td>"+cell.getErrorCellValue()+"</td>");
									}

								}
								break;
							case FORMULA:
								//	    					System.out.print("columna: " + cell.getColumnIndex() + ",valor formula: " + cell.getCellFormula() + "\t");
								if(row.getRowNum() == 2 || row.getRowNum() == 3)
								{	    	    				
									response.getWriter().append("<th>"+cell.getCellFormula()+"</th>");	    	    				
								}else{	    	    		
									if(row.getRowNum() > 3){
										response.getWriter().append("<td>"+cell.getCellFormula()+"</td>");
									}
								}
								break;
							case NUMERIC:
								//	    					System.out.print("columna: " + cell.getColumnIndex() + ",valor numeric: " + cell.getNumericCellValue() + "\t");
								if(row.getRowNum() == 1 && cell.getColumnIndex() == 4){
									Date javaDate= DateUtil.getJavaDate((double) cell.getNumericCellValue());
									String fechaHorario = new SimpleDateFormat("yyyy-MM-dd").format(javaDate);	    				          				    	
									date = LocalDate.parse(fechaHorario);
									doc.setFechaValidezDocumento(date);
									empleadoHorarioDTO.setFechaValidez(doc.getFechaValidezDocumento());
									empleadoHorarioDTO.setAnioValidez(doc.getFechaValidezDocumento().getYear());
									empleadoHorarioDTO.setMesValidez(doc.getFechaValidezDocumento().getMonthValue());
									System.out.println("fecha de validez " + doc.getFechaValidezDocumento());
								}
								if(row.getRowNum() == 2 || row.getRowNum() == 3)
								{	    	    				
									response.getWriter().append("<th>"+(int)cell.getNumericCellValue()+"</th>");	    	    				
								}else{
									if(row.getRowNum() > 3){
										if(cell.getNumericCellValue() != 2.0){
											response.getWriter().append("<td>"+(int) cell.getNumericCellValue()+"</td>");
										}else{
											if(!terminado){
												if(cell.getColumnIndex() > 3){
													response.getWriter().append("<td class='bg-danger'>"+(int) cell.getNumericCellValue()+"</td>");
												}else{
													response.getWriter().append("<td>"+(int) cell.getNumericCellValue()+"</td>");
												}
											}
										}
										if(!terminado){
											if(cell.getColumnIndex() == 0) {
												Double d = 0.0;
												d = cell.getNumericCellValue();
												empleadoDTO.setSkuEmpleado((int) d.intValue());										
											}else if (cell.getColumnIndex() > 3 && cell.getColumnIndex() < 35){
												if(empleadoHorarioDTO.getHorarios() == null || empleadoHorarioDTO.getHorarios().size() <= 0){
													empleadoHorarioDTO.setHorarios(new Vector<HorarioDTO>());
												}
												if(cell.getNumericCellValue() != 0 && cell.getNumericCellValue() != 0.0)
												{
													HorarioDTO horarioDTO = new HorarioDTO();											
													horarioDTO.setClaveHorario(String.valueOf((int) cell.getNumericCellValue()));
													empleadoHorarioDTO.getHorarios().add(horarioDTO);
												}
											}
										}
									}
								}
								break;
							case STRING:
								//	    					System.out.print("columna: " + cell.getColumnIndex() + ",valor string: " + cell.getStringCellValue() + "\t");
								if(row.getRowNum() == 2 || row.getRowNum() == 3)
								{	    	    				
									response.getWriter().append("<th>"+cell.getStringCellValue()+"</th>");	    	    				
								}else{
									if(!cell.getStringCellValue().equals("#FIN#") && !terminado){
										if(!cell.getStringCellValue().equals("2.0")){
											switch (cell.getStringCellValue()) {
											case "D":
												response.getWriter().append("<td class='bg-primary'>"+cell.getStringCellValue()+"</td>");
												break;
											case "V":
												response.getWriter().append("<td class='bg-primary'>"+cell.getStringCellValue()+"</td>");
												break;
											default:
												response.getWriter().append("<td>"+cell.getStringCellValue()+"</td>");
												break;
											}
										}else{

											response.getWriter().append("<td class='bg-danger'>"+cell.getStringCellValue()+"</td>");
										}
									}
									if(row.getRowNum() > 3){	    	    					
										if(!terminado){

											if(cell.getColumnIndex() == 2) {
												if(empleadoDTO.getNombreCompletoEmpleado() == null){
													empleadoDTO.setNombreCompletoEmpleado(cell.getStringCellValue());
												}
											}else if (cell.getColumnIndex() > 3 && cell.getColumnIndex() < 35){
												if(empleadoHorarioDTO.getHorarios() == null || empleadoHorarioDTO.getHorarios().size() <= 0){
													empleadoHorarioDTO.setHorarios(new Vector<HorarioDTO>());
												}
												if(cell.getStringCellValue() != null && !cell.getStringCellValue().isEmpty() && !cell.getStringCellValue().equals("") && cell.getStringCellValue().length() > 0 && cell.getStringCellValue().charAt(0) != ' ')
												{
													HorarioDTO horarioDTO = new HorarioDTO();
													horarioDTO.setClaveHorario(cell.getStringCellValue());
													empleadoHorarioDTO.getHorarios().add(horarioDTO);
												}
											}else{
												if(cell.getStringCellValue().equals("#FIN#")){
													terminado = true;
													System.out.println("Terminado");
												}
											}
										}
									}
								}
								break;
							default:
								//	    					System.out.print("columna: " + cell.getColumnIndex() + ",tipo de celda desconocido!" + "\t");
								if(row.getRowNum() == 2 || row.getRowNum() == 3)
								{	    	    				
									response.getWriter().append("<th>unknown cell type!</th>");	    	    				
								}else{
									if(row.getRowNum() > 3){
										response.getWriter().append("<td>unknown cell type!</td>");
									}
								}
								break;
							}

							if(empleadoDTO.getSkuEmpleado() > 0 || empleadoDTO.getNombreCompletoEmpleado() != null){
								empleadoHorarioDTO.setEmpleado(empleadoDTO);	    		    		
							}	    				

						}//Termina ciclo while
						//	    			System.out.println("");
						if(empleadoHorarioDTO.getEmpleado() != null && (empleadoHorarioDTO.getEmpleado().getSkuEmpleado() > 0 || empleadoDTO.getNombreCompletoEmpleado() != null)){
							v.add(empleadoHorarioDTO);
						}
						if(row.getRowNum() == 2)
						{	response.getWriter().append("<tr>");	    				    				

						}else if(row.getRowNum() == 3){
							response.getWriter().append("</thead>");	
						}else{
							response.getWriter().append("</tr>");
						}
					}//Termina ciclo while	    		
					response.getWriter().append("</tbody>");
					book.close();  
					session.setAttribute("horariosEmpleadosDTO", v);
					session.setAttribute("archivoEmpleadosDTO", doc);

				}catch(Exception e){
					System.out.println("E: " + e);
				}
				IOUtils.closeQuietly(fileContent);
			}else{
				if(targetFile.getName().endsWith("xls")){

					DocumentoHorarioDTO doc = new DocumentoHorarioDTO();
					doc.setNombreDocumento(fileName);

					System.out.println("Hola procesando archivo");
					InputStream fileContent = null;
					try {
						fileContent = filePart.getInputStream();
					} catch (IOException e1) {
						e1.printStackTrace();
					}	    
					try {
						java.nio.file.Files.copy(
								fileContent, 
								targetFile.toPath(), 
								StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					//	    	doc.setDocumento(targetFile);
					doc.setExtDocumento(ext);

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					formatter = formatter.withLocale( Locale.US );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
					LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));	    	
					doc.setFechaCargaDocumento(date);
					formatter = DateTimeFormatter.ofPattern("HH:mm:ss");	    	
					LocalTime time = LocalTime.parse(LocalTime.now().format(formatter));
					doc.setHoraCargaDocumento(time);
					doc.setRutaDocumento(rutaResp);
					doc.setTamanoDocumento(filePart.getSize());
					


					//COMIENZA LECTURA DEL ARCHIVO XLSX
					try {           
						FileInputStream fis = new FileInputStream(targetFile);
						HSSFWorkbook book = new HSSFWorkbook(fis);
						HSSFSheet sheet = book.getSheetAt(0);

						Iterator<Row> itr = sheet.iterator();
						response.setHeader("Content-Type", "text/xml; charset=ISO-8859-1");
						Vector<EmpleadoHorarioDTO> v = new Vector<EmpleadoHorarioDTO>();
						// Iterating over Excel file in Java
						boolean terminado = false;
						response.getWriter().append("<thead>");	   
						response.getWriter().append("<tr>");				
						while (itr.hasNext()) {
							if(terminado)
								break;
							Row row = itr.next();

							//	    			System.out.println("r" + row.getRowNum());	    			

							EmpleadoDTO empleadoDTO = new EmpleadoDTO();
							EmpleadoHorarioDTO empleadoHorarioDTO = new EmpleadoHorarioDTO();		    		

							if(row.getRowNum() == 4){
								response.getWriter().append("<tbody><tr>");

							}else if(row.getRowNum() == 2)
							{
								response.getWriter().append("<th colspan='4'> </th>");

							}else{
								if(row.getRowNum() > 3){
									if(!terminado)
										response.getWriter().append("<tr>");
								}
							}
							// Iterating over each column of Excel file
							Iterator<Cell> cellIterator = row.cellIterator();
							while (cellIterator.hasNext()) {

								Cell cell = cellIterator.next();
								switch (cell.getCellTypeEnum()) {
								case BLANK:
									//	    					System.out.print("null" + "\t");	   

									if(row.getRowNum() == 2)
									{	    	    				
										response.getWriter().append("<th></th>");	    	    				
									}else{
										if(row.getRowNum() > 3){
											if(!terminado)
												response.getWriter().append("<td></td>");
										}
									}
									break;
								case BOOLEAN:
									//	    					System.out.print("columna: " + cell.getColumnIndex() + ", valor boolean: " + cell.getBooleanCellValue() + "\t");

									if(row.getRowNum() == 2 || row.getRowNum() == 3)
									{	    	    				
										response.getWriter().append("<th>"+cell.getBooleanCellValue()+"</th>");	    	    				
									}else{
										if(row.getRowNum() > 3){
											response.getWriter().append("<td>"+cell.getBooleanCellValue()+"</td>");
										}
									}
									break;
								case ERROR:
									//	    					System.out.print("columna: " + cell.getColumnIndex() + ",valor error: " + cell.getErrorCellValue() + "\t");
									if(row.getRowNum() == 2 || row.getRowNum() == 3)
									{	    	    				
										response.getWriter().append("<th>"+cell.getErrorCellValue()+"</th>");	    	    				
									}else{
										if(row.getRowNum() > 3){
											response.getWriter().append("<td>"+cell.getErrorCellValue()+"</td>");
										}

									}
									break;
								case FORMULA:
									//	    					System.out.print("columna: " + cell.getColumnIndex() + ",valor formula: " + cell.getCellFormula() + "\t");
									if(row.getRowNum() == 2 || row.getRowNum() == 3)
									{	    	    				
										response.getWriter().append("<th>"+cell.getCellFormula()+"</th>");	    	    				
									}else{	    	    		
										if(row.getRowNum() > 3){
											response.getWriter().append("<td>"+cell.getCellFormula()+"</td>");
										}
									}
									break;
								case NUMERIC:
									//	    					System.out.print("columna: " + cell.getColumnIndex() + ",valor numeric: " + cell.getNumericCellValue() + "\t");
									if(row.getRowNum() == 1 && cell.getColumnIndex() == 4){
										Date javaDate= DateUtil.getJavaDate((double) cell.getNumericCellValue());
										String fechaHorario = new SimpleDateFormat("yyyy-MM-dd").format(javaDate);	    				          				    	
										date = LocalDate.parse(fechaHorario);
										doc.setFechaValidezDocumento(date);
										empleadoHorarioDTO.setFechaValidez(doc.getFechaValidezDocumento());
										empleadoHorarioDTO.setAnioValidez(doc.getFechaValidezDocumento().getYear());
										empleadoHorarioDTO.setMesValidez(doc.getFechaValidezDocumento().getMonthValue());
										System.out.println("fecha de validez " + doc.getFechaValidezDocumento());
									}
									if(row.getRowNum() == 2 || row.getRowNum() == 3)
									{	    	    				
										response.getWriter().append("<th>"+(int)cell.getNumericCellValue()+"</th>");	    	    				
									}else{
										if(row.getRowNum() > 3){
											if(cell.getNumericCellValue() != 2.0){
												response.getWriter().append("<td>"+(int) cell.getNumericCellValue()+"</td>");
											}else{
												if(!terminado){
													if(cell.getColumnIndex() > 3){
														response.getWriter().append("<td class='bg-danger'>"+(int) cell.getNumericCellValue()+"</td>");
													}else{
														response.getWriter().append("<td>"+(int) cell.getNumericCellValue()+"</td>");
													}
												}
											}
											if(!terminado){
												if(cell.getColumnIndex() == 0) {
													Double d = 0.0;
													d = cell.getNumericCellValue();
													empleadoDTO.setSkuEmpleado((int) d.intValue());										
												}else if (cell.getColumnIndex() > 3 && cell.getColumnIndex() < 35){
													if(empleadoHorarioDTO.getHorarios() == null || empleadoHorarioDTO.getHorarios().size() <= 0){
														empleadoHorarioDTO.setHorarios(new Vector<HorarioDTO>());
													}
													if(cell.getNumericCellValue() != 0 && cell.getNumericCellValue() != 0.0)
													{
														HorarioDTO horarioDTO = new HorarioDTO();											
														horarioDTO.setClaveHorario(String.valueOf((int) cell.getNumericCellValue()));
														empleadoHorarioDTO.getHorarios().add(horarioDTO);
													}
												}
											}
										}
									}
									break;
								case STRING:
									//	    					System.out.print("columna: " + cell.getColumnIndex() + ",valor string: " + cell.getStringCellValue() + "\t");
									if(row.getRowNum() == 2 || row.getRowNum() == 3)
									{	    	    				
										response.getWriter().append("<th>"+cell.getStringCellValue()+"</th>");	    	    				
									}else{
										if(!cell.getStringCellValue().equals("#FIN#") && !terminado){
											if(!cell.getStringCellValue().equals("2.0")){
												switch (cell.getStringCellValue()) {
												case "D":
													response.getWriter().append("<td class='bg-primary'>"+cell.getStringCellValue()+"</td>");
													break;
												case "V":
													response.getWriter().append("<td class='bg-primary'>"+cell.getStringCellValue()+"</td>");
													break;
												default:
													response.getWriter().append("<td>"+cell.getStringCellValue()+"</td>");
													break;
												}
											}else{

												response.getWriter().append("<td class='bg-danger'>"+cell.getStringCellValue()+"</td>");
											}
										}
										if(row.getRowNum() > 3){	    	    					
											if(!terminado){

												if(cell.getColumnIndex() == 2) {
													if(empleadoDTO.getNombreCompletoEmpleado() == null){
														empleadoDTO.setNombreCompletoEmpleado(cell.getStringCellValue());
													}
												}else if (cell.getColumnIndex() > 3 && cell.getColumnIndex() < 35){
													if(empleadoHorarioDTO.getHorarios() == null || empleadoHorarioDTO.getHorarios().size() <= 0){
														empleadoHorarioDTO.setHorarios(new Vector<HorarioDTO>());
													}
													if(cell.getStringCellValue() != null && !cell.getStringCellValue().isEmpty() && !cell.getStringCellValue().equals("") && cell.getStringCellValue().length() > 0 && cell.getStringCellValue().charAt(0) != ' ')
													{
														HorarioDTO horarioDTO = new HorarioDTO();
														horarioDTO.setClaveHorario(cell.getStringCellValue());
														empleadoHorarioDTO.getHorarios().add(horarioDTO);
													}
												}else{
													if(cell.getStringCellValue().equals("#FIN#")){
														terminado = true;
														System.out.println("Terminado");
													}
												}
											}
										}
									}
									break;
								default:
									//	    					System.out.print("columna: " + cell.getColumnIndex() + ",tipo de celda desconocido!" + "\t");
									if(row.getRowNum() == 2 || row.getRowNum() == 3)
									{	    	    				
										response.getWriter().append("<th>unknown cell type!</th>");	    	    				
									}else{
										if(row.getRowNum() > 3){
											response.getWriter().append("<td>unknown cell type!</td>");
										}
									}
									break;
								}

								if(empleadoDTO.getSkuEmpleado() > 0 || empleadoDTO.getNombreCompletoEmpleado() != null){
									empleadoHorarioDTO.setEmpleado(empleadoDTO);	    		    		
								}	    				

							}//Termina ciclo while
							//	    			System.out.println("");
							if(empleadoHorarioDTO.getEmpleado() != null && (empleadoHorarioDTO.getEmpleado().getSkuEmpleado() > 0 || empleadoDTO.getNombreCompletoEmpleado() != null)){
								v.add(empleadoHorarioDTO);
							}
							if(row.getRowNum() == 2)
							{	response.getWriter().append("<tr>");	    				    				

							}else if(row.getRowNum() == 3){
								response.getWriter().append("</thead>");	
							}else{
								response.getWriter().append("</tr>");
							}
						}//Termina ciclo while	    		
						response.getWriter().append("</tbody>");
						book.close();  
						session.setAttribute("horariosEmpleadosDTO", v);
						session.setAttribute("archivoEmpleadosDTO", doc);

					}catch(Exception e){
						System.out.println("E: " + e);
					}
					IOUtils.closeQuietly(fileContent);
				
				}else{
					System.out.println("archivo no xlsx!");
				}
			}
		}else{
			System.out.println("archivo vacio!");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void guardarHorarioArchivoService(HttpServletRequest request, HttpServletResponse response, UserDTO user){
		HttpSession session = request.getSession();
		EmpleadoHorarioDAO empleadoHorarioDAO = new EmpleadoHorarioDAO();
				
		Vector<EmpleadoHorarioDTO> v = null;
		if (session.getAttribute("horariosEmpleadosDTO") != null && session.getAttribute("horariosEmpleadosDTO") instanceof Vector<?>){			
			v = (Vector<EmpleadoHorarioDTO>) session.getAttribute("horariosEmpleadosDTO");
			if(v != null && v.size() > 0){				
				DocumentoHorarioDTO doc = null;
				int ultimoId = 0;
				if (session.getAttribute("archivoEmpleadosDTO") != null && session.getAttribute("archivoEmpleadosDTO") instanceof DocumentoHorarioDTO){			
					doc = (DocumentoHorarioDTO) session.getAttribute("archivoEmpleadosDTO");
					if(doc != null){
						doc.setUsuarioDocumento(user.getUserId());
						int res = getDocumentosDAO().insertRegistroDocumentoEmpleadosHorariosDTO(doc);
						Locale mexico = new Locale("es","MX");
						Month mes = Month.from(doc.getFechaValidezDocumento());
						if(res > 0){
							System.out.println("registro de archivo Insertado!");
							
							ultimoId = res;
							try {
								
								response.getWriter().append("<div class='alert alert-success'><strong>Correcto!</strong>El horario de "+ mes.getDisplayName(TextStyle.FULL, mexico) +" ha sido insertado.</div>");
								session.removeAttribute("archivoEmpleadosDTO");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}else{
							System.out.println("registro de archivo no Insertado!");
							try {
								response.getWriter().append("<div class='alert alert-danger'><strong>Error!</strong>El horario de "+ mes.getDisplayName(TextStyle.FULL, mexico) +" no ha sido insertado.</div>");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					
					}else{
						System.out.println("documento horario es nulo pero la sesion existe!");
					}
				}else{
					System.out.println("documento horario no existe en sesion!");
				}
				
				int res = empleadoHorarioDAO.insertHorarioExcel(v, ultimoId, doc, user);
				if(res > 0){
					System.out.println("Insertado!");
					try {
						response.getWriter().append("<div class='alert alert-success'><strong>Correcto!</strong>El archivo "+ doc.getNombreDocumento() +" ha sido guardado.</div>");
						session.removeAttribute("horariosEmpleadosDTO");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					System.out.println("HORARIO NO Insertado!");
						
				}
			}else{
				System.out.println("El vector de horario es nulo!");
			}
		}
	}
	
	public void selectDocumentosHorarios(HttpServletRequest request, HttpServletResponse response){
		Vector<DocumentoHorarioDTO> docs = getDocumentosDAO().selectDocumentosHorarios();
		if(docs != null && docs.size() > 0){
			request.setAttribute("documentosHorarios", docs);
		}else{
			System.out.println("NO DATA IN SCHEDULES");
		}
	}

	public void descargarArchivo( HttpServletRequest request, HttpServletResponse response){

		int id = (request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0);
		
		if (id > 0){
			System.out.println("id de archivo "+id);
			String ruta = "";
			String fileType = "";
			// Find this file id in database to get file name, and file type

			// You must tell the browser the file type you are going to send
			// for example application/pdf, text/plain, text/html, image/jpg
			response.setContentType(fileType);
			ArchivoDTO documento = getDocumentosDAO().selectArchivoDTO(id);
			// Make sure to show the download dialog
			response.setHeader("Content-disposition","attachment; filename="+documento.getNombreDocumento());

			// Assume file name is retrieved from database
			// For example D:\\file\\test.pdf
			
			ruta = documento.getRutaDocumento() + documento.getIdDocumento() + "_" + documento.getNombreDocumento();

			File my_file = new File(ruta);

			// This should send the file to browser

			OutputStream out = null;
			try {
				out = response.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			FileInputStream in = null;
			try {
				in = new FileInputStream(my_file);
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
			byte[] buffer = new byte[4096];
			int length;
			try {
				while ((length = in.read(buffer)) > 0){
					out.write(buffer, 0, length);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("id = 0 descargarArchivo");
		}
	}
	
	public void descargarArchivoOrdenes( HttpServletRequest request, HttpServletResponse response){

		int id = (request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0);
		
		if (id > 0){
			System.out.println("id de archivo "+id);
			//String ruta = "";
			String fileType = "";
			// Find this file id in database to get file name, and file type

			// You must tell the browser the file type you are going to send
			// for example application/pdf, text/plain, text/html, image/jpg
			response.setContentType(fileType);
			DocumentoDTO documento = new OrdenesDAO().selectDocumentoDTO(id);
			if(documento != null){
				if(documento.getArchivo() != null){
					if(Boolean.parseBoolean(request.getParameter("download"))){
						// Make sure to show the download dialog
						response.setHeader("Content-disposition","attachment; filename="+documento.getNombreArchivo());
	
						// Assume file name is retrieved from database
						// For example D:\\file\\test.pdf
	
						//ruta = documento.getRutaDocumento() + documento.getIdDocumento() + "_" + documento.getNombreDocumento();
	
						//File my_file = new File(documento.getArchivo());
	
						// This should send the file to browser
						System.out.println("desc? "+request.getParameter("download"));
						OutputStream out = null;
						try {
							out = response.getOutputStream();
						} catch (IOException e) {
							e.printStackTrace();
						}
						InputStream in = null;
						try {
							in = documento.getArchivo().getBinaryStream();
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						byte[] buffer = new byte[4096];
						int length = 0;
						try {
							while ((length = in.read(buffer)) != -1){
								out.write(buffer, 0, length);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						try {
							in.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						try {
							out.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else{
						try {
							response.getWriter().write("1");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else{
					System.out.println("documento.archivo == null en descargarArchivoOrdenes");
				}
			}else{
				System.out.println("documento null en descargarArchivoOrdenes");
			}
		}else{
			System.out.println("id = 0 descargarArchivoOrdenes");
		}
	}
	

}
