// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 22/07/2006 10:18:42
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FormNavegacion.java

package com.jade.util.lweb;

import java.sql.SQLException;
import sun.jdbc.rowset.CachedRowSet;

public class FormNavegacion
{

    public FormNavegacion()
    {
        A = 1;
        E = null;
    }

    public String tabla()
    {
        C = E.size();
        F = C / D;
        if(C % D != 0)
            F++;
        try
        {
            E.absolute((A * D - D) + 1);
            G = E.getRow();
            B = (E.getRow() + D) - 1;
            for(int i = 0; i < D && !E.isAfterLast(); i++);
        }
        catch(SQLException sqlexception)
        {
            sqlexception.printStackTrace();
        }
        return "";
    }

    public int getNumPaginacion()
    {
        return D;
    }

    public void setNumPaginacion(int i)
    {
        D = i;
    }

    public int getPrimerRegistroPagina()
    {
        return G;
    }

    public void setPrimerRegistroPagina(int i)
    {
        G = i;
    }

    public int getUltimoRegistroPagina()
    {
        return B;
    }

    public void setUltimoRegistroPagina(int i)
    {
        B = i;
    }

    public int getPaginaActual()
    {
        return A;
    }

    public void setPaginaActual(int i)
    {
        A = i;
    }

    public CachedRowSet getOCachedRowSet()
    {
        return E;
    }

    public void setOCachedRowSet(CachedRowSet cachedrowset)
    {
        E = cachedrowset;
    }

    private int D;
    private int C;
    private int F;
    private int G;
    private int B;
    private int A;
    private CachedRowSet E;
}
