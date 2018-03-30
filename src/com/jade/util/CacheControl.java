package com.jade.util;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class CacheControl
    {

	private static Logger log = Logger.getLogger(CacheControl.class);
	private static HashMap<String, Object> CacheRepository = new HashMap<String, Object>();

	/**
	 * Sirve para almacenar objetos en cache.
	 * 
	 * @param clave
	 * @param valor
	 */
	public static void saveObject(String clave, Object valor)
	    {
		if (valor == null)
		    {
			log.info("Guardando en cache el key " + clave + " De tipo NULL");
		    } else
		    {

			log.info("Guardando en cache el key " + clave + " De tipo " + valor.getClass().getName());
		    }
		CacheRepository.put(clave, valor);
	    }

	/**
	 * Valida la existencia de un Objeto.
	 * 
	 * @param clave
	 * @return
	 */
	public static boolean existInCache(String clave)
	    {
		return CacheRepository.containsKey(clave);
	    }

	/**
	 * Retorna el Objeto almacenado, siempre quien lo invoca debe hacer el
	 * Casting al tipo de Objeto que guardo.
	 * 
	 * @param clave
	 * @return
	 */
	public static Object getObject(String clave)
	    {
		log.info("Recuperando de cache el key " + clave);
		return CacheRepository.get(clave);
	    }

	private static void resetCache()
	    {
		log.info("Borrando todos los objetos de Cache.");
		CacheRepository.clear();
	    }

	/**
	 * Se encarga de eliminar todo lo que se encuentra en cache.
	 * 
	 * @param request
	 * @throws Exception
	 */
	public static void cleanCache(Object request) throws Exception
	    {
		log.info("Eliminando del Cache");
		HttpServletRequest req = null;
		try
		    {
			req = (HttpServletRequest) request;

			log.info("Borrando invocado Desde:" + req.getRemoteAddr());
		    } catch (Exception e)
		    {
			// TODO: handle exception
		    }

		log.info("Borrando todos los objetos de cache.");

		resetCache();

	    }

	/**
	 * Se encarga de eliminar todo lo que se encuentra en cache.
	 * 
	 * @param request
	 * @throws Exception
	 */
	public static void removeObject(String key) throws Exception
	    {
		log.info("Eliminando Objecto" + key);
		CacheRepository.remove(key);

	    }

    }
