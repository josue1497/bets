// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 22/07/2006 10:18:06
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ManejoFecha.java

package com.jade.util.luti;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.jade.util.Date;

public class ManejoFecha
{
	static Logger log =Logger.getLogger(ManejoFecha.class);

    public ManejoFecha()
    {
    }

    public static String retornaFechaActual(String s)
    {
        Date date = new Date();
        SimpleDateFormat simpledateformat = new SimpleDateFormat(s);
        return simpledateformat.format(date);
    }

    public static int anoCurso()
    {
        return (new GregorianCalendar()).get(1);
    }

    public static int mesCurso()
    {
        return (new GregorianCalendar()).get(2) + 1;
    }

    public static int diadelMesCurso()
    {
        return (new GregorianCalendar()).get(5);
    }

    public static int diasdeunMesporAno(int i, int j)
    {
        byte byte0;
        switch(j)
        {
        case 4: // '\004'
        case 6: // '\006'
        case 9: // '\t'
        case 11: // '\013'
            byte0 = 30;
            break;

        case 2: // '\002'
            if(A(i))
                byte0 = 29;
            else
                byte0 = 28;
            break;

        case 3: // '\003'
        case 5: // '\005'
        case 7: // '\007'
        case 8: // '\b'
        case 10: // '\n'
        default:
            byte0 = 31;
            break;
        }
        return byte0;
    }

    private static boolean A(int i)
    {
        return i % 4 == 0 && i != 1900;
    }

    public static Date convertirStringAFecha(String s)
    {
        Date date = null;
        try
        {
            SimpleDateFormat simpledateformat = new SimpleDateFormat("dd/MM/yyyy");
            date = Date.convert(simpledateformat.parse(s));
            log.info(date.toString());
        }
        catch(ParseException parseexception)
        {
            log.info(parseexception.getMessage());
        }
        catch(Exception exception)
        {
            log.info(exception.getMessage());
        }
        return date;
    }

    public static boolean comparaFecha1MenorFecha2(Calendar calendar, Calendar calendar1)
    {
        return calendar.before(calendar1);
    }

    public static boolean comparaFecha1MayorFecha2(Calendar calendar, Calendar calendar1)
    {
        return calendar.after(calendar1);
    }

    public static boolean comparaFecha1IgualFecha2(Calendar calendar, Calendar calendar1)
    {
        return calendar.equals(calendar1);
    }

    public boolean comparaFecha1MenorIgualFecha2(Calendar calendar, Calendar calendar1)
    {
        return calendar.before(calendar1) || calendar.equals(calendar1);
    }

    public static boolean comparaFecha1MayorIgualFecha2(Calendar calendar, Calendar calendar1)
    {
        return calendar.after(calendar1) || calendar.equals(calendar1);
    }
}
