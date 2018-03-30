// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 22/07/2006 10:18:13
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ManejoProperties.java

package com.jade.util.luti;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class ManejoProperties
{

    public ManejoProperties(String s)
    {
        A = s;
        B = new Properties();
    }

    public String getProperty(String s)
    {
        return B.getProperty(s);
    }

    public void setProperty(String s, String s1)
    {
        s = s.trim();
        B.setProperty(s, s1);
    }

    public void guardarProperties()
    {
        FileOutputStream fileoutputstream = null;
        try
        {
            fileoutputstream = new FileOutputStream(A);
        }
        catch(FileNotFoundException filenotfoundexception) { }
        try
        {
            B.store(fileoutputstream, "MyProperties v0.0 implementation");
            fileoutputstream.close();
        }
        catch(IOException ioexception) { }
    }

    public void cargarArchivoPropertie()
    {
        try
        {
            java.io.InputStream inputstream = getClass().getClassLoader().getResourceAsStream(A);
            B.load(inputstream);
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public Enumeration propertyNames()
    {
        return B.propertyNames();
    }

    private String A;
    private Properties B;
}
