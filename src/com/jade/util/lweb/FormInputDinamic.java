// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 22/07/2006 10:18:34
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FormInputDinamic.java

package com.jade.util.lweb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import sun.jdbc.rowset.CachedRowSet;

import com.jade.util.lbda.ConexionManejaError;
import com.jade.util.lbda.EjecutorSqlManejaError;

// Referenced classes of package com.jade.util.lweb:
//            FormInputJs

public class FormInputDinamic
{

    public FormInputDinamic()
    {
    }

    public ArrayList DinamicFormElementosJs(String s, CachedRowSet cachedrowset, ArrayList arraylist)
        throws ConexionManejaError, EjecutorSqlManejaError, SQLException
    {
        ArrayList arraylist1 = new ArrayList();
        FormInputJs forminputjs = new FormInputJs(s);
        Iterator iterator = arraylist.iterator();
        cachedrowset.first();
        while(iterator.hasNext()) 
        {
            String s1 = (String)iterator.next();
            StringTokenizer stringtokenizer = new StringTokenizer(s1, ":");
            String s2 = stringtokenizer.nextToken();
            String s3 = stringtokenizer.nextToken();
            if(s3.equalsIgnoreCase("TXT"))
                arraylist1.add(forminputjs.construyeSetInputText(s2, cachedrowset.getString(s2) == null ? "" : cachedrowset.getString(s2)));
            else
            if(s3.equalsIgnoreCase("RAD"))
                arraylist1.add(forminputjs.construyeSetInputRadio(s2, cachedrowset.getString(s2) == null ? "" : cachedrowset.getString(s2)));
            else
            if(s3.equalsIgnoreCase("CHK"))
                arraylist1.add(forminputjs.construyeSetInputCheck(s2, cachedrowset.getString(s2) == null ? "" : cachedrowset.getString(s2)));
            else
            if(s3.equalsIgnoreCase("SEL"))
                arraylist1.add(forminputjs.construyeSelectInput(s2, cachedrowset.getString(s2) == null ? "" : cachedrowset.getString(s2)));
            else
            if(s3.equalsIgnoreCase("IMG"))
                arraylist1.add(forminputjs.construyeSetImg(s2, cachedrowset.getString(s2) == null ? "" : cachedrowset.getString(s2)));
            else
            if(s3.equalsIgnoreCase("NOTHING"))
                arraylist1.add(s2);
        }
        return arraylist1;
    }
}
