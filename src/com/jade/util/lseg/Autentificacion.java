
package com.jade.util.lseg;

import java.util.ArrayList;

import sun.jdbc.rowset.CachedRowSet;

import com.jade.util.lbda.ConexionManejaError;
import com.jade.util.lbda.EjecutorSql;
import com.jade.util.lbda.EjecutorSqlManejaError;

public class Autentificacion
{

    public Autentificacion()
    {
    }

    public boolean verificaClave(String s, String s1)
        throws ConexionManejaError, EjecutorSqlManejaError
    {
        ArrayList arraylist = new ArrayList();
        boolean flag = false;
        String s2 = "";
        arraylist.add(s);
        String s3 = "select clave_usuario from usuario where alias_usuario =? ";
        try
        {
            for(CachedRowSet cachedrowset = A.ejecutaQuery(s3, arraylist); cachedrowset.next();)
                s2 = cachedrowset.getString(1);

            if(s1.equals(s2))
                flag = true;
        }
        catch(Exception exception) { }
        return flag;
    }

    public boolean verificaAliasUsuario(String s)
        throws ConexionManejaError, EjecutorSqlManejaError
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(s);
        boolean flag = false;
        String s2 = "select * from usuario where alias_usuario =? ";
        try
        {
            CachedRowSet cachedrowset = A.ejecutaQuery(s2, arraylist);
            if(cachedrowset.next())
                flag = true;
        }
        catch(Exception exception) { }
        return flag;
    }

    public int autentifica(String s, String s1)
    {
        byte byte0 = 0;
        try
        {
            if(verificaAliasUsuario(s))
                if(verificaClave(s, s1))
                    byte0 = 1;
                else
                    byte0 = 2;
        }
        catch(Exception exception) { }
        return byte0;
    }

    private static EjecutorSql A = new EjecutorSql();

}
