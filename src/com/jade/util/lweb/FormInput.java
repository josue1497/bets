// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 22/07/2006 10:18:31
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FormInput.java

package com.jade.util.lweb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import sun.jdbc.rowset.CachedRowSet;

import com.jade.util.lbda.ConexionManejaError;
import com.jade.util.lbda.EjecutorSqlManejaError;
import com.jade.util.luti.ManejoString;

public class FormInput
{
	static Logger log =Logger.getLogger(FormInput.class);
    public FormInput(String s)
    {
        E = new ManejoString();
        D = "document.";
        C = s + ".";
        B = D + C;
    }

    public String contruyeContenidoFuncionMain(ArrayList arraylist)
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); stringbuffer.append(iterator.next() + "\n"));
        return stringbuffer.toString();
    }

    public String contruyeSelect(String s, String s1, CachedRowSet cachedrowset, String s2)
        throws ConexionManejaError, EjecutorSqlManejaError
    {
        boolean flag = false;
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("function " + s + "()" + "\n" + "{" + "\n");
        stringbuffer.append(B + s1 + "." + "options" + E.entreCorchetes("0") + " = " + "new Option" + E.entreParentesis(E.entreComillas("Seleccione un Item")) + ";" + "\n");
        stringbuffer.append(B + s1 + "." + "options" + E.entreCorchetes("0") + "." + "value" + " = " + E.entreComillas("") + ";" + "\n");
        int i = 1;
        try
        {
            do
            {
                if(!cachedrowset.next())
                    break;
                String s3 = Integer.toString(i);
                stringbuffer.append(B + s1 + "." + "options" + E.entreCorchetes(s3) + " = " + "new Option" + E.entreParentesis(E.entreComillas(cachedrowset.getString(2))) + ";" + "\n");
                stringbuffer.append(B + s1 + "." + "options" + E.entreCorchetes(s3) + "." + "value" + " = " + E.entreComillas(cachedrowset.getString(1)) + ";" + "\n");
                i++;
                if(cachedrowset.getString(1).equalsIgnoreCase(s2))
                    stringbuffer.append(B + s1 + "." + "options" + E.entreCorchetes(s3) + "." + "selected" + " = " + "true" + ";" + "\n");
            } while(true);
            stringbuffer.append("}");
        }
        catch(Exception exception) { }
        return stringbuffer.toString();
    }

    public String construyeSelectAll(String s, String s1, CachedRowSet cachedrowset, String s2)
        throws ConexionManejaError, EjecutorSqlManejaError
    {
        boolean flag = false;
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("function " + s + "()" + "\n" + "{" + "\n");
        stringbuffer.append(B + s1 + "." + "options" + E.entreCorchetes("0") + " = " + "new Option" + E.entreParentesis(E.entreComillas("Seleccione un Item")) + ";" + "\n");
        stringbuffer.append(B + s1 + "." + "options" + E.entreCorchetes("0") + "." + "value" + " = " + E.entreComillas("") + ";" + "\n");
        stringbuffer.append(B + s1 + "." + "options" + E.entreCorchetes("1") + " = " + "new Option" + E.entreParentesis(E.entreComillas("Todas las opciones")) + ";" + "\n");
        stringbuffer.append(B + s1 + "." + "options" + E.entreCorchetes("1") + "." + "value" + " = " + E.entreComillas("%") + ";" + "\n");
        int i = 2;
        try
        {
            do
            {
                if(!cachedrowset.next())
                    break;
                String s3 = Integer.toString(i);
                stringbuffer.append(B + s1 + "." + "options" + E.entreCorchetes(s3) + " = " + "new Option" + E.entreParentesis(E.entreComillas(cachedrowset.getString(2))) + ";" + "\n");
                stringbuffer.append(B + s1 + "." + "options" + E.entreCorchetes(s3) + "." + "value" + " = " + E.entreComillas(cachedrowset.getString(1)) + ";" + "\n");
                i++;
                if(cachedrowset.getString(1).equalsIgnoreCase(s2))
                    stringbuffer.append(B + s1 + "." + "options" + E.entreCorchetes(s3) + "." + "selected" + " = " + "true" + ";" + "\n");
            } while(true);
            stringbuffer.append("}");
        }
        catch(Exception exception) { }
        return stringbuffer.toString();
    }

    public String contruyeSelectAnidado(ArrayList arraylist, CachedRowSet cachedrowset)
        throws ConexionManejaError, EjecutorSqlManejaError
    {
        boolean flag = false;
        StringBuffer stringbuffer = new StringBuffer();
        String s = "no definido";
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";
        String s6 = "";
        String s7 = "";
        s6 = "var list" + arraylist.get(0) + " = " + "new DynamicOptionList";
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext();)
            s7 = s7 + E.entreComillas("slt" + iterator.next()) + ",";

        s7 = s7.substring(0, s7.length() - 1);
        s6 = s6 + E.entreParentesis(s7);
        stringbuffer.append(s6 + ";" + "\n");
        try
        {
            while(cachedrowset.next()) 
            {
                String s8 = cachedrowset.getString(1);
                String s9 = cachedrowset.getString(2);
                String s10 = cachedrowset.getString(3);
                if(!flag)
                {
                    s = s8;
                    flag = true;
                    s4 = s4 + E.entreComillas(s9) + "," + E.entreComillas(s10) + ",";
                } else
                {
                    if(s.equalsIgnoreCase(s8))
                    {
                        s4 = s4 + E.entreComillas(s9) + "," + E.entreComillas(s10) + ",";
                    } else
                    {
                        s5 = E.entreComillas(s) + "," + s4;
                        s5 = s5.substring(0, s5.length() - 1);
                        s5 = "list" + arraylist.get(0) + ".addOptions" + E.entreParentesis(s5) + ";" + "\n";
                        stringbuffer.append(s5);
                        s4 = "";
                        s = s8;
                        s4 = s4 + E.entreComillas(s9) + "," + E.entreComillas(s10) + ",";
                    }
                    s = s8;
                }
            }
        }
        catch(Exception exception) { }
        s5 = E.entreComillas(s) + "," + s4;
        s5 = s5.substring(0, s5.length() - 1);
        s5 = "list" + arraylist.get(0) + ".addOptions" + E.entreParentesis(s5) + ";" + "\n";
        stringbuffer.append(s5);
        return stringbuffer.toString();
    }

    public String declaraArr(int i)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("var arrItems");
        stringbuffer.append(i);
        stringbuffer.append(" = new Array();\r\n");
        stringbuffer.append("var arrItemsGrp");
        stringbuffer.append(i);
        stringbuffer.append(" = new Array();\r\n");
        stringbuffer.append("var arrItemsval");
        stringbuffer.append(i);
        stringbuffer.append(" = new Array();\r\n");
        return stringbuffer.toString();
    }

    public String constructmain(CachedRowSet cachedrowset, int i)
        throws ConexionManejaError, EjecutorSqlManejaError
    {
        StringBuffer stringbuffer = new StringBuffer();
        try
        {
            stringbuffer.append(declaraArr(i));
            stringbuffer.append(constructArrAnid(cachedrowset, i));
        }
        catch(Exception exception)
        {
            log.info("Existe un Error en :constructmain");
            log.info(exception.getMessage());
        }
        return stringbuffer.toString();
    }

    public String constructArrAnid(CachedRowSet cachedrowset, int i)
        throws SQLException
    {
        StringBuffer stringbuffer = new StringBuffer();
        int j = 0;
        try
        {
            if(cachedrowset != null)
                while(cachedrowset.next()) 
                {
                    stringbuffer.append("arrItems" + i + "[" + j + "] = \"" + cachedrowset.getString(1) + "\";\r\n");
                    stringbuffer.append("arrItemsGrp" + i + "[" + j + "] = \"" + cachedrowset.getString(2) + "\";\r\n");
                    stringbuffer.append("arrItemsval" + i + "[" + j + "] = \"" + cachedrowset.getString(3) + "\";\r\n");
                    j++;
                }
        }
        catch(SQLException sqlexception)
        {
            log.info("Existe un Error en :constructArrAnid");
            log.info(sqlexception.getMessage());
        }
        return stringbuffer.toString();
    }

    private String D;
    private String C;
    private String A;
    private String B;
    private ManejoString E;
}
