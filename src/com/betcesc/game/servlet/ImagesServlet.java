package com.betcesc.game.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import sun.jdbc.rowset.CachedRowSet;

import com.jade.util.CacheControl;
import com.jade.util.lbda.EjecutorSql;

/**
 * Servlet implementation class ImagesServlet
 */
public class ImagesServlet extends HttpServlet
    {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ImagesServlet.class);

	static ServletContext context;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImagesServlet()
	    {
		super();
		// TODO Auto-generated constructor stub
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	    {

		process(request, response);
	    }

	/**
	 * Metodo para procesar bien sea get o post la peticion.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	    {
		// TODO Auto-generated method stub
		context = getServletContext();

		String[] uri = request.getRequestURI().split("dbimg");

		String nomrbreArchivo = uri[1].replace("%20", " ");
		nomrbreArchivo = nomrbreArchivo.replaceAll("/", "");
		String ContentType = "image/jpeg";

		response.setContentType(ContentType);

		ServletOutputStream out = null;
		try
		    {

			byte[] binario = null;

			binario = (byte[]) CacheControl.getObject("imagen-" + nomrbreArchivo);

			if (binario == null)
			    {
				binario = getByteArrayFromDataBase(nomrbreArchivo);
				CacheControl.saveObject("imagen-" + nomrbreArchivo, binario);
			    }

			out = response.getOutputStream();
			out.write(binario);

		    } catch (FileNotFoundException e)
		    {
			response.sendError(404);
		    } catch (Exception e)
		    {
			log.error("Error cargando la imagen " + nomrbreArchivo, e);
		    } finally
		    {

			if (out != null)
			    {
				out.close();
			    }
		    }
	    }

	/**
	 * Se encarga de leer un archivo Binario y retornarlo como un arreglo de Bytes.
	 * 
	 * @param archivo
	 * @return
	 * @throws IOException
	 */
	public static byte[] getByteArrayFromDataBase(String archivo) throws Exception
	    {

		log.debug("Se buscara " + archivo);
		byte[] blobAsBytes = new byte[0];
		try
		    {

			EjecutorSql sql = new EjecutorSql();
			CachedRowSet rs = sql.ejecutaQuery("SELECT * from imagenes where nombre='" + archivo + "'", null);
			if (rs.next())
			    {
				blobAsBytes = rs.getBytes("imagen");



			    } else
			    {
				throw new FileNotFoundException("No se encontro en la base de datos " + archivo);
			    }
		    } catch (IndexOutOfBoundsException e)
		    {
			log.error("Imagen no encontrada " + archivo + " en BD");
			throw new FileNotFoundException();
		    } catch (Exception e1)
		    {
			log.error("Error recuperando la imagen", e1);
			throw e1;

		    }

		// release the blob and free up memory. (since JDBC 4.0)

		return blobAsBytes;
	    }

	/**
	 * Se encarga de leer un archivo Binario y retornarlo como un arreglo de Bytes.
	 * 
	 * @param archivo
	 * @return
	 * @throws IOException
	 */
	public static byte[] getByteArrayFromfile(String archivo) throws IOException
	    {

		String rutaAbsoluta = context.getInitParameter("rutaImagenes") + archivo;

		byte[] b = null;
		FileInputStream fileInputStream = null;

		try
		    {
			File file = new File(rutaAbsoluta);

			b = new byte[(int) file.length()];

			fileInputStream = new FileInputStream(file);
			fileInputStream.read(b);
		    } catch (Exception e)
		    {
			throw new IOException("Error abriendo el archivo", e);
		    } finally
		    {
			if (fileInputStream != null)
			    {
				fileInputStream.close();
			    }
		    }
		return b;
	    }
    }