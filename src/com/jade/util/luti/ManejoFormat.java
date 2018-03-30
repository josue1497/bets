// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 22/07/2006 10:18:09
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ManejoFormat.java

package com.jade.util.luti;

import java.text.DecimalFormat;

public class ManejoFormat
{

    public ManejoFormat()
    {
    }

    public String formatNumero(String s, String s1)
    {
        double d = Double.valueOf(s).doubleValue();
        DecimalFormat decimalformat = null;
        decimalformat = new DecimalFormat(s1);
        return decimalformat.format(d);
    }
}
