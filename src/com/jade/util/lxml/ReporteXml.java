// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 22/07/2006 10:18:51
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ReporteXml.java

package com.jade.util.lxml;

import com.jade.util.lbda.*;
import java.sql.*;
import java.util.ArrayList;

public class ReporteXml
{

    public ReporteXml()
    {
    }

    public static String ejecutaQueryRetornaXml(String s, ArrayList arraylist)
        throws EjecutorSqlManejaError
    {
        StringBuffer stringbuffer;
        Connection connection = null;
        Object obj = null;
        Object obj1 = null;
        byte byte0 = 10;
        char c = (char)byte0;
        stringbuffer = new StringBuffer();
        try
        {
            connection = A.iniciarconexion();
            PreparedStatement preparedstatement = connection.prepareStatement(s);
            if(arraylist != null)
            {
                for(int i = 0; i < arraylist.size(); i++)
                    preparedstatement.setObject(i + 1, arraylist.get(i));

            }
            ResultSet resultset = preparedstatement.executeQuery();
            if(!resultset.next())
                throw new IllegalArgumentException("No existe datos en la tabla");
            stringbuffer.append("<secciones>" + c);
            for(; resultset.next(); stringbuffer.append("</seccion>" + c))
            {
                stringbuffer.append("<seccion nombre=\"\">" + c);
                stringbuffer.append("<dato>" + resultset.getString(1) + "</dato>" + c);
                stringbuffer.append("<dato>" + resultset.getString(2) + "</dato>" + c);
                stringbuffer.append("<dato>" + resultset.getString(3) + "</dato>" + c);
            }

            stringbuffer.append("</secciones>" + c);
            resultset.close();
            preparedstatement.close();
        }
        catch(SQLException sqlexception)
        {
            throw new EjecutorSqlManejaError("Ocurrio una Excepcion al ejecutar el Query: " + s + "Package: com.bauxilum.util.basededatos " + "Metodo: ejecutaQuery " + "Clase: EjecutorSql " + "Estatus: " + sqlexception.getMessage());
        }
        catch(Exception exception)
        {
            throw new EjecutorSqlManejaError("Ocurrio una Excepcion al ejecutar el Query: " + s + "Package: com.bauxilum.util.basededatos " + "Metodo: ejecutaQuery " + "Clase: EjecutorSql " + "Estatus: " + exception.getMessage());
        }
        finally
        {
            try
            {
                A.cerrarConexion(connection);
            }
            catch(ConexionManejaError conexionmanejaerror)
            {
                throw new EjecutorSqlManejaError("Excepcion al liberar la conexion con la base de datos Package: com.bauxilum.util.basededatos Metodo: ejecutaSqlRetornaNumRegAct Clase: EjecutorSql Estatus: " + conexionmanejaerror.getMessage());
            }
        }
        return stringbuffer.toString();
    }

    public static String ejecutaQueryRetornaXml(String s, ArrayList arraylist, ArrayList arraylist1)
        throws EjecutorSqlManejaError
    {
        StringBuffer stringbuffer;
        Connection connection = null;
        Object obj = null;
        Object obj1 = null;
        byte byte0 = 10;
        char c = (char)byte0;
        stringbuffer = new StringBuffer();
        int i = arraylist1.size();
        String as[] = new String[i];
        for(int j = 0; j < i; j++)
            as[j] = "";

        try
        {
            connection = A.iniciarconexion();
            PreparedStatement preparedstatement = connection.prepareStatement(s);
            if(arraylist != null)
            {
                for(int k = 0; k < arraylist.size(); k++)
                    preparedstatement.setObject(k + 1, arraylist.get(k));

            }
            ResultSet resultset;
            for(resultset = preparedstatement.executeQuery(); resultset.next();)
            {
                for(int l = 0; l < i; l++)
                    as[l] = as[l] + "<dato>" + resultset.getString(l + 1) + "</dato>" + c;

            }

            stringbuffer.append("<doc>" + c);
            for(int i1 = 0; i1 < i; i1++)
            {
                stringbuffer.append("<seccion nombre=\"" + arraylist1.get(i1) + "\">" + c);
                stringbuffer.append(as[i1]);
                stringbuffer.append("</seccion>" + c);
            }

            stringbuffer.append("</doc>" + c);
            resultset.close();
            preparedstatement.close();
        }
        catch(SQLException sqlexception)
        {
            throw new EjecutorSqlManejaError("Ocurrio una Excepcion al ejecutar el Query: " + s + "Package: com.bauxilum.util.basededatos " + "Metodo: ejecutaQuery " + "Clase: EjecutorSql " + "Estatus: " + sqlexception.getMessage());
        }
        catch(Exception exception)
        {
            throw new EjecutorSqlManejaError("Ocurrio una Excepcion al ejecutar el Query: " + s + "Package: com.bauxilum.util.basededatos " + "Metodo: ejecutaQuery " + "Clase: EjecutorSql " + "Estatus: " + exception.getMessage());
        }
        finally
        {
            try
            {
                A.cerrarConexion(connection);
            }
            catch(ConexionManejaError conexionmanejaerror)
            {
                throw new EjecutorSqlManejaError("Excepcion al liberar la conexion con la base de datos Package: com.bauxilum.util.basededatos Metodo: ejecutaSqlRetornaNumRegAct Clase: EjecutorSql Estatus: " + conexionmanejaerror.getMessage());
            }
        }
        return stringbuffer.toString();
    }

    private static Conexion A = new Conexion();

}
