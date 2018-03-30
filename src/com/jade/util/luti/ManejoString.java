// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 22/07/2006 10:18:16
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ManejoString.java

package com.jade.util.luti;


public class ManejoString
{

    public ManejoString()
    {
    }

    public String entreComillas(String s)
    {
        return "\"" + s + "\"";
    }

    public String entreCorchetes(String s)
    {
        return "[" + s + "]";
    }

    public String entreParentesis(String s)
    {
        return "(" + s + ")";
    }

    public String replace(String s, String s1, String s2)
    {
        int i = 0;
        int j = 0;
        StringBuffer stringbuffer = new StringBuffer();
        while((j = s.indexOf(s1, i)) >= 0) 
        {
            stringbuffer.append(s.substring(i, j));
            stringbuffer.append(s2);
            i = j + s1.length();
        }
        stringbuffer.append(s.substring(i));
        return stringbuffer.toString();
    }

    public String cString(Object obj)
    {
        if(obj == null)
            return "";
        else
            return (String)obj;
    }
}
