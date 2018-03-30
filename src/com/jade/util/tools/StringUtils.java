// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 22/07/2006 10:19:05
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   StringUtils.java

package com.jade.util.tools;


public class StringUtils
{

    public StringUtils()
    {
    }

    public static String lpad(String s, char c, int i)
    {
        if(s == null)
            s = new String();
        if(s.length() >= i)
            return s;
        StringBuffer stringbuffer = new StringBuffer();
        int j = i - s.length();
        for(int k = 0; k < j; k++)
            stringbuffer.append(c);

        stringbuffer.append(s);
        return stringbuffer.toString();
    }

    public static String rpad(String s, char c, int i)
    {
        if(s == null)
            s = new String();
        if(s.length() >= i)
            return s;
        StringBuffer stringbuffer = new StringBuffer(s);
        int j = i - s.length();
        for(int k = 0; k < j; k++)
            stringbuffer.append(c);

        return stringbuffer.toString();
    }
}
