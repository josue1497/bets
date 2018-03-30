import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/*
 * Creado : 25-feb-2007 Modificado : 25-feb-2007 Autor : JRrivero
 * 
 * Sistema Administrativo
 *  
 */

public class TestCone {
	static Logger log =Logger.getLogger(TestCone.class);
    public TestCone() {
        Connection conec = null;
        try {
            log.info("Voy a cargar los Drivers");
            Class.forName("com.mysql.jdbc.Driver");
            log.info("Drivers cargados");
            
            log.info("Obteniendo conexion");
            conec = DriverManager.getConnection("jdbc:mysql://localhost/jugada","jugada","jugada147");
            log.info("conectado");
            
//            log.info("creando Statement");            
//            Statement st = conec.createStatement();
//            log.info("Statement creado");
//            
//            log.info("consultando");
//            ResultSet resul = st.executeQuery("SELECT cedula,nombres,DATE_FORMAT(fecha_ingreso,'%d/%m/%Y')fecha_ingreso,sexo FROM alumno ");
//            resul = st.executeQuery("SELECT * FROM materia ");
//            
//            log.info("consultado");
//            
//            log.info("imprimiendo");
//            while(resul.next()){
//                log.info("\ncedula:"+resul.getInt("cedula"));
//                log.info("nombre:"+resul.getString("nombres"));
//                log.info("fecha:"+resul.getString("fecha_ingreso"));
//                log.info("sexo:"+resul.getString("sexo"));
//                
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("finally");
            try{
                log.info("cerrando conexion");
                if ( conec!=null) {
                    conec.close();
                }
                log.info("conexion cerrada");
            }catch(SQLException s){
                s.printStackTrace();
                
            }
            
        }
        
    }

    public static void main(String[] args) {
        
		TestCone cA = new TestCone();
        
    }
}
