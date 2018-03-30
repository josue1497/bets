
package com.jade.util.lbda;

import org.apache.log4j.Logger;

public class ConexionManejaError extends Exception
{
	static Logger log =Logger.getLogger(ConexionManejaError.class);

	private static final long serialVersionUID = 1L;

	public ConexionManejaError(String s)
    {
        super(s);
        log.info(s);
    }
}
