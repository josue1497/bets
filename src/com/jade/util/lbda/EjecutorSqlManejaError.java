
package com.jade.util.lbda;

import org.apache.log4j.Logger;

public class EjecutorSqlManejaError extends Exception
{

	private static final long serialVersionUID = 1L;
	static Logger log =Logger.getLogger(EjecutorSqlManejaError.class);

	public EjecutorSqlManejaError(String s)
    {
        super(s);
        log.info(s);
    }
}
