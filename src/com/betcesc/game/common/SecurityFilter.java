package com.betcesc.game.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class SecurityFilter implements Filter {
	static Logger log =Logger.getLogger(SecurityFilter.class);
	private static HashMap USUARIOS = new HashMap();

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //log.info("PROTOCOLO = " + request.getScheme());
        boolean seguro = false; // colocar en true para trabajar en modo seguro
        
        if ( !seguro || request.getScheme().equals("https")) {
            //log.info("Es seguro");
            chain.doFilter(request, response);
        } else {
            log.info("No es seguro cambiamos a modo seguro");
            RequestDispatcher rd = request.getRequestDispatcher("/secureStart.do");
            rd.forward(request, response);
        }

        //if(USUARIOS.size()==0) {
        //    RequestDispatcher rd = request.getRequestDispatcher("/start.do");
        //    rd.forward(request, response);
        //}
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
