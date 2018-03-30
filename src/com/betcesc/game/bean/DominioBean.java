package com.betcesc.game.bean;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import sun.jdbc.rowset.CachedRowSet;

import com.jade.util.lbda.EjecutorSql;

public class DominioBean
    {

	static Logger log = Logger.getLogger(DominioBean.class);
	public static Hashtable<String, Franquicia> listaFranquicias = null;
	public static Hashtable<String, String> nombreDominios = null;

	private Franquicia franquicia = null;

	private String dominio;
	private String ip;
	private long time = 0;
	private int indice = 0;

	public DominioBean(String ip, String dominio, long time)
	    {
		if (dominio.equals("localhost"))

		    dominio = "www.betcesc.com";
		
		if (dominio.startsWith("prueba"))
		{
			dominio= dominio.replaceAll("prueba", "www");
		}
		if (dominio.startsWith("historical.us-west-2."))
		{
			dominio = "www.betcesc.com";
		}
		
		setDominio(dominio);
		setIp(ip);
		setTime(time);
		try
		    {
			load();
		    } catch (Throwable e)
		    {
		    	log.error(e);
			
		    }
	    }

	private void load() throws Throwable
	    {

		if (listaFranquicias == null)
		    {

			log.info("Cargando dominios");

			String sql = "SELECT * from franquicia where active =1 ";
			listaFranquicias = new Hashtable<String, Franquicia>();

			nombreDominios = new Hashtable<String, String>();

			CachedRowSet rs = new CachedRowSet();
			EjecutorSql oEjecutorSql = new EjecutorSql();
			rs = oEjecutorSql.ejecutaQuery(sql, null);

			log.info("Buscando: " + dominio.trim());
			while (rs.next())
			    {

				Franquicia franquicia = new Franquicia();
				franquicia.setId(rs.getString("id"));
				franquicia.setColor(rs.getString("color"));
				franquicia.setColor_font(rs.getString("color_font"));
				franquicia.setColor_front(rs.getString("color_front"));
				franquicia.setColor_line(rs.getString("color_line"));
				franquicia.setDominio(rs.getString("dominio"));
				franquicia.setEmail(rs.getString("email"));
				franquicia.setMoneda(rs.getString("moneda"));
				franquicia.setPath_imagen(rs.getString("path_imagen"));
				franquicia.setPhone(rs.getString("phone"));
				franquicia.setTwitter(rs.getString("twitter"));
				franquicia.setFacebook(rs.getString("facebook"));
				franquicia.setUser_admin(rs.getString("user_admin"));
				franquicia.setUser_supervisor(rs.getString("user_supervisor"));
				franquicia.setUser_taquilla(rs.getString("user_taquilla"));
				listaFranquicias.put("www." + rs.getString("dominio").trim(), franquicia);

				nombreDominios.put(rs.getString("id"), rs.getString("dominio"));

			    }

		    }

		franquicia = listaFranquicias.get(dominio.trim());

		if (franquicia == null)
		    {
			log.warn("Lista de Franquicias" +listaFranquicias.toString());

			throw new Exception("No esta registrado el dominio: " + dominio);
		    }

	    }

	public String getIdDominio()
	    {
		return franquicia.getId();
	    }

	public String getDominio()
	    {
		return franquicia.getId();
	    }

	public void setDominio(String dominio)
	    {
		this.dominio = dominio;
	    }

	public String getIp()
	    {
		return ip;
	    }

	public void setIp(String ip)
	    {
		this.ip = ip;
	    }

	public long getTime()
	    {
		return time;
	    }

	public void setTime(long time)
	    {
		this.time = time;
	    }

	public String getNombre()
	    {
		return franquicia.getDominio();
	    }

	public String getColor()
	    {
		return franquicia.getColor();
	    }

	public String getColorFront()
	    {
		return franquicia.getColor_front();
	    }

	public String getIdAdmin()
	    {
		return franquicia.getUser_admin();
	    }

	public String getIdAdminTaquilla()
	    {
		return franquicia.getUser_taquilla();
	    }

	public String getIdAdminSupervisores()
	    {
		return franquicia.getUser_supervisor();
	    }

	public String getPathImages()
	    {
		return franquicia.getPath_imagen();
	    }

	public String getColorFont()
	    {
		return franquicia.getColor_font();
	    }

	public String getColorLine()
	    {
		return franquicia.getColor_line();
	    }

	public String getTwitter()
	    {
		return franquicia.getTwitter();
	    }

	public String getMail()
	    {
		return franquicia.getEmail();
	    }

	public String getTelefono()
	    {
		return franquicia.getPhone();
	    }

	public int getIndice()
	    {
		return indice;
	    }

	public void setIndice(int indice)
	    {
		this.indice = indice;
	    }

	public String getMoneda()
	    {
		return franquicia.getMoneda();
	    }

    }
