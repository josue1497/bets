package com.betcesc.game.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.InvalidContentTypeException;
import org.apache.log4j.Logger;

import com.jade.util.CacheControl;
import com.jade.util.lbda.EjecutorSql;


/*
 * Clase para subir el manejo de algunas funciones de archivo
 */
public class FileUtil {
	static Logger log =Logger.getLogger(FileUtil.class);

	public FileUtil() {

	}

	/*
	 * Permite subir los ficheros que estan en el request a la carpeta temporal
	 */
	public Properties procesaFicheros(HttpServletRequest req) {
		return procesaFicheros(req, null);
	}

	/*
	 * Permite subir los ficheros que estan en el request a la carpeta temporal
	 */
	public Properties procesaFicheros(HttpServletRequest req, String newNameFile) {
		Properties campos = new Properties(); 
		try {


			DiskFileUpload fu = new DiskFileUpload();
			// log.info("Procesando requerimiento.....");
			int lim = 50; // 10 megabytes
			fu.setSizeMax(1024 * 1024 * lim);
			fu.setSizeThreshold(4096);
			String path = System.getProperty("java.io.tmpdir");

			fu.setRepositoryPath(path);
			List fileItems = fu.parseRequest(req);
			// log.info("fileItems = " + fileItems);
			if (fileItems == null)
			    {
				// log.info("La lista es nula");
				return campos;
			    }
			Iterator i = fileItems.iterator();
			FileItem actual = null;

			while (i.hasNext())
			    {
				actual = (FileItem) i.next();
				String name = actual.getFieldName();
				String fileName = actual.getName();
				if (fileName != null && !fileName.equals(""))
				    {
					File fichero = new File(fileName);

					if (newNameFile != null)
					    {
						newNameFile = newNameFile.concat(".").concat(getExtensionFile(fichero.getName()));
						newNameFile = newNameFile.toLowerCase();
					    }

					campos.put(name, newNameFile != null ? newNameFile : fichero.getName().toLowerCase());
					campos.put(name.concat("-").concat("ContextType"), actual.getContentType());
					// fichero = new File("C:\\Program Files\\up\\" + fichero.getName());
					// fichero = new File(path.concat("/").concat(newNameFile!=null ? newNameFile : fichero.getName()));
					// fichero = new File(path.concat(getOnlyNameFile(newNameFile != null ? newNameFile : fichero.getName().toLowerCase())));
					if (fichero.exists())
					    {
						System.gc();
						fichero.delete();
					    }
					actual.write(fichero);

					CacheControl.removeObject("imagen-" + newNameFile);
					EjecutorSql sqlExec = new EjecutorSql();
					sqlExec.saveImage(newNameFile, fichero);
					log.info("Eliminado " + fichero.delete());

				    } else
				    {
					campos.put(name, actual.getString());
					log.info(name + "=" + actual.getString());
				    }
			    }

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return campos;
	}
	
	/*
	 * Permite subir los ficheros que estan en el request a la carpeta temporal
	 */
	public Properties leerParametros(HttpServletRequest req) {
		Properties campos = new Properties();
		try {
			DiskFileUpload fu = new DiskFileUpload();
			List fileItems = fu.parseRequest(req);
			if (fileItems == null) {
				//log.info("La lista es nula");
				return campos;
			}
			Iterator i = fileItems.iterator();
			FileItem actual = null;
			
			while (i.hasNext()) {
				actual = (FileItem) i.next();
				String name = actual.getFieldName();
				String fileName = actual.getName();
				campos.put(name, actual.getString());
			}
		} catch (InvalidContentTypeException e) {
			//log.info("La lista es nula");
			return campos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return campos;
	}


	public static String getOnlyNameFile(String nameFile) {
		if (nameFile==null || nameFile.trim().equals(""))
			return nameFile;
		
		char back = (char)92;
		char slash = '/';
		
		StringBuffer name = new StringBuffer(nameFile);
		nameFile = name.reverse().toString();
		
		if(nameFile.indexOf(String.valueOf(back))!=-1) {
			nameFile = nameFile.substring(0,nameFile.indexOf(String.valueOf(back)));
		} 
		if(nameFile.indexOf(String.valueOf(slash))!=-1) {
			nameFile = nameFile.substring(0,nameFile.indexOf(String.valueOf(slash)));
		}
			
		name.setLength(0);
		name.append(nameFile);
		return name.reverse().toString();
		
	}
	
	public static String getExtensionFile(String nameFile) {
		String[] sep = nameFile.split("\\.");
		return sep[sep.length-1];
	}

	/*
	 * Permite subir los ficheros que estan en el request a la carpeta temporal
	 */
	public Properties procesaFicherosAsociado(HttpServletRequest req, String newNameFile, String asociado) {
		Properties campos = new Properties();
		try {
			DiskFileUpload fu = new DiskFileUpload();
			//log.info("Procesando requerimiento.....");
			int lim = 50; // 10 megabytes
			fu.setSizeMax(1024 * 1024 * lim);
			fu.setSizeThreshold(4096);
			//String path = req.getSession().getServletContext().getRealPath("/").replace((char) 92, '/') + "asociados/"+asociado+"/";

			String path = System.getProperty("java.io.tmpdir");
			fu.setRepositoryPath(path);
			List fileItems = fu.parseRequest(req);
			//log.info("fileItems = " + fileItems);
			if (fileItems == null) {
				//log.info("La lista es nula");
				return campos;
			}
			Iterator i = fileItems.iterator();
			FileItem actual = null;
			
			while (i.hasNext()) {
				actual = (FileItem) i.next();
				String name = actual.getFieldName();
				String fileName = actual.getName();
				if (fileName != null && !fileName.equals("")) {
					File fichero = new File(fileName);
					if(newNameFile!=null) {
						newNameFile = newNameFile.concat(".").concat(getExtensionFile(fichero.getName()));
						newNameFile = newNameFile.toLowerCase();
					}
					campos.put(name, newNameFile!=null ? newNameFile : fichero.getName().toLowerCase() );
					campos.put(name.concat("-").concat("ContextType"),actual.getContentType());
					// fichero = new File("C:\\Program Files\\up\\" + fichero.getName());
					//fichero = new File(path.concat("/").concat(newNameFile!=null ? newNameFile : fichero.getName()));
					// fichero = new File(path.concat(getOnlyNameFile(newNameFile!=null ? newNameFile : fichero.getName().toLowerCase())));
					if(fichero.exists()) {
						System.gc();
						fichero.delete();
					}
					actual.write(fichero);
					File f = new File(newNameFile);
					CacheControl.removeObject("imagen-" + f.getName());
					EjecutorSql sqlExec = new EjecutorSql();


					sqlExec.saveImage(f.getName(), fichero);
					log.info("Eliminado " + fichero.delete());

				} else {
					campos.put(name, actual.getString());
					log.info(name+"="+actual.getString());
				}
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return campos;
	}

}
