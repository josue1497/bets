// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 22/07/2006 10:18:38
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FormInputJs.java

package com.jade.util.lweb;


public class FormInputJs
{

    public FormInputJs(String s)
    {
        D = "document.";
        C = s;
        B = D + C + ".";
    }

    public String construyeSelectInput(String s, String s1)
    {
        String s2 = "setSelectInput(" + B + s + ",'" + s1 + "');";
        return s2;
    }

    public String construyeSetInputText(String s, String s1)
    {
        String s2 = "setInputText(" + B + s + ",'" + s1 + "');";
        return s2;
    }

    public String construyeSetInputRadio(String s, String s1)
    {
        String s2 = "setRadioInput(" + B + s + ",'" + s1 + "');";
        return s2;
    }

    public String construyeSetInputCheck(String s, String s1)
    {
        String s2 = "setCheckboxInput(" + B + s + ",'" + s1 + "');";
        return s2;
    }

    public String construyeSetImg(String s, String s1)
    {
        String s2 = "setSrcImg(" + B + s + ",'" + s1 + "');";
        return s2;
    }

    private String D;
    private String C;
    private String A;
    private String B;
}
