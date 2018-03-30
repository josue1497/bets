
package com.jade.util.lseg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import sun.jdbc.rowset.CachedRowSet;

import com.jade.util.lbda.ConexionManejaError;
import com.jade.util.lbda.EjecutorSql;
import com.jade.util.lbda.EjecutorSqlManejaError;
import com.jade.util.luti.ManejoString;

public class ConstruyeOpcion
{

	static Logger log =Logger.getLogger(ConstruyeOpcion.class);
    public ConstruyeOpcion(String s)
    {
        L = new StringBuffer("");
        K = "";
        W = new HashMap();
        O = "";
        V = "";
        F = true;
        J = 0;
        P = new HashMap();
        U = new HashMap();
        N = "";
        T = "";
        D = true;
        I = 0;
        E = new HashMap();
        X = "";
        H = "";
        M = "";
        G = "";
        Q = "";
        R = "";
        K = s;
    }

    public String opcion()
        throws ConexionManejaError, EjecutorSqlManejaError
    {
        Object obj = null;
        String as[][] = new String[20][1];
        ArrayList arraylist = new ArrayList();
        String s = "";
        String s1 = "";
        int i = 1;
        try
        {
            String s2 = "SELECT id_menu, relacionado,desc_opcion,url_opcion,orden_opcion from aplicacion_opcion where id_aplicacion='CGB' order by relacionado,id_menu";
            CachedRowSet cachedrowset = S.ejecutaQuery(s2, arraylist);
            log.info("[Iniciando Construccion de nodos para el Sistema de Opcion]");
            log.info("[Construccion de nodos padres (000)]");
            while(cachedrowset.next()) 
            {
                X = (String)cachedrowset.getObject("id_menu");
                H = (String)cachedrowset.getObject("desc_opcion");
                M = (String)cachedrowset.getObject("url_opcion");
                G = (String)cachedrowset.getObject("orden_opcion");
                Q = (String)cachedrowset.getObject("relacionado");
                R = "";
                if(Q.equalsIgnoreCase("000"))
                {
                    R = construyeOpcionJs("Opcion" + i, H, M, G);
                    log.info("[Nodo Padre:] " + R);
                    W.put(new String(X), new String(R));
                    i++;
                } else
                if(W.containsKey(Q))
                {
                    O = (String)W.get(Q);
                    B = new StringTokenizer(O, "=");
                    V = B.nextToken();
                    if(s.equalsIgnoreCase(Q))
                        J++;
                    else
                    if(!F)
                    {
                        J = 1;
                    } else
                    {
                        J++;
                        F = false;
                    }
                    R = construyeOpcionJs(V + "_" + J, H, M, G);
                    log.info("[Nodo Hijo: Relacion Directa con Padre:] " + R);
                    actNumItemsNodoPadre(Q, J);
                    U.put(new String(X), new String(R));
                    s = Q;
                } else
                if(U.containsKey(Q))
                {
                    N = (String)U.get(Q);
                    A = new StringTokenizer(N, "=");
                    T = A.nextToken();
                    if(s1.equalsIgnoreCase(Q))
                        I++;
                    else
                    if(!D)
                    {
                        I = 1;
                    } else
                    {
                        I++;
                        D = false;
                    }
                    R = construyeOpcionJs(T + "_" + I, H, M, G);
                    log.info("[Nodo Hijo: Relacion No Directa con Padre:] " + R);
                    actNumItemsNodoHijo(Q, I);
                    U.put(new String(X), new String(R));
                    s1 = Q;
                }
            }
        }
        catch(Exception exception) { }
        log.info("Fin de asignacion de nodos para Sistema de Opcion");
        asignaNumItems();
        L.append(retornaContenMap(W));
        L.append(retornaContenMap(U));
        return L.toString();
    }

    public String construyeOpcionJs(String s, String s1, String s2, String s3)
    {
        String s4 = s + "=new Array(\"" + s1 + "\",\"" + s2 + "\",\"\"," + "0" + ",21,165,\"\",\"\",\"\",\"\",\"\",\"\",-1,-1,-1,\"\",\"\");" + "\n";
        return s4;
    }

    public void actNumItemsNodoPadre(String s, int i)
    {
        P.put(new String(s), new String(Integer.toString(i)));
    }

    public void actNumItemsNodoHijo(String s, int i)
    {
        E.put(new String(s), new String(Integer.toString(i)));
    }

    public void asignaNumItems()
    {
        actualizaContenMap(P, W);
        actualizaContenMap(E, U);
    }

    public String retornaScriptJs()
    {
        L.append("1");
        return L.toString();
    }

    public String retornaContenMap(Map map)
    {
        StringBuffer stringbuffer = new StringBuffer("");
        Set set = map.keySet();
        String s1;
        for(Iterator iterator = set.iterator(); iterator.hasNext(); stringbuffer.append(s1))
        {
            String s = (String)iterator.next();
            s1 = (String)map.get(s);
        }

        return stringbuffer.toString();
    }

    public void actualizaContenMap(Map map, Map map1)
    {
        Set set = map.keySet();
        for(Iterator iterator = set.iterator(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            String s1 = (String)map.get(s);
            if(map1.containsKey(s))
            {
                String s2 = (String)map1.get(s);
                String s3 = C.replace(s2, "0", s1);
                map1.put(new String(s), new String(s3));
            }
        }

    }

    private static EjecutorSql S = new EjecutorSql();
    private static ManejoString C = new ManejoString();
    private StringBuffer L;
    private String K;
    private Map W;
    private String O;
    private String V;
    private boolean F;
    private int J;
    private Map P;
    private Map U;
    private String N;
    private String T;
    boolean D;
    private int I;
    private Map E;
    private StringTokenizer B;
    private StringTokenizer A;
    private String X;
    private String H;
    private String M;
    private String G;
    private String Q;
    private String R;

}
