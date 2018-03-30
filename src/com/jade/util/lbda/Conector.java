
package com.jade.util.lbda;

import com.jade.util.luti.ManejoProperties;

public class Conector {

	public Conector() {
		C = null;
		N = null;
	}

	public void iniciarConector() {
		ManejoProperties manejoproperties = new ManejoProperties("BD.properties");
		manejoproperties.cargarArchivoPropertie();
		I = manejoproperties.getProperty("basedatos");
		P = manejoproperties.getProperty("jdbc.driver");
		A = manejoproperties.getProperty("jarfile");
		M = manejoproperties.getProperty("jdbc.url.tipo");
		if (M != null)
			try {
				H = Integer.parseInt(M);
			} catch (NumberFormatException numberformatexception) {
				System.err.println("Valor Ilegal para el key jdbc.url.type. Set a 0.");
				H = 0;
			}
		G = manejoproperties.getProperty("jdbc.protocolo");
		K = manejoproperties.getProperty("nombre.basedatos");
		F = manejoproperties.getProperty("host.basedatos");
		O = manejoproperties.getProperty("puerto.basedatos");
		E = manejoproperties.getProperty("ruta.basedatos");
		L = manejoproperties.getProperty("url.basedatos");
		B = manejoproperties.getProperty("suffix.basedatos");
		D = manejoproperties.getProperty("nombre.usuario");
		J = manejoproperties.getProperty("password");
		if (P != null)
			P = P.trim();
		else
			P = "sun.jdbc.odbc.JdbcOdbcDriver";
	}

	public String getURL() {
		String s = null;
		if (H == 4) {
			s = L;
		} else {
			if (G != null)
				G = G.trim();
			else
				G = "odbc";
			s = "jdbc:" + G + ":";
			if (C == null)
				C = K;
			if (H == 1 || H == 3) {
				s = s + "//";
				if (F == null)
					F = "localhost";
				s = s + F;
				if (O != null) {
					s = s + ":";
					s = s + O;
				}
				s = s + "/";
			}
			if (H == 2 || H == 3) {
				if (N == null)
					N = E;
				if (N != null) {
					s = s + N;
					s = s + "/";
				}
			}
			s = s + C;
			if ((H == 2 || H == 3) && B != null)
				s = s + B;
		}
		return s;
	}

	public String getJdbcDriver() {
		return P;
	}

	public String getNombreUsuario() {
		return D;
	}

	public String getPassword() {
		return J;
	}

	private static String I;
	private String P;
	private static String A;
	private static String M;
	private String C;
	private String N;
	private static int H = 0;
	private static String G;
	private static String K;
	private static String F;
	private static String O;
	private static String E;
	private static String L;
	private static String B;
	private String D;
	private String J;

}
