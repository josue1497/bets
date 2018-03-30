import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.log4j.Logger;

public class ReplaceInFiles {

	static Logger log =Logger.getLogger(ReplaceInFiles.class); 
	
	private String ext = "";
	private String old = "";
	private String nueva = "";

	public ReplaceInFiles() {

	}

	public ReplaceInFiles(String ext, String old, String nueva) {
		this.ext = ext;
		this.old = old;
		this.nueva = nueva;
	}

	public void start(String dir)  throws IOException {
		File[] lista = new File[] { new File(dir) };

		leerDirectorio(lista);
	}

	private void leerDirectorio(File[] lista) throws IOException  {
		String[] ex = ext.split(",");
		boolean hacer = false;
		for (int i = 0; i < lista.length; i++) {
			// log.info(lista[i].getAbsolutePath());
			if (lista[i].isDirectory()) {
				leerDirectorio(lista[i].listFiles());
			} else {
				hacer = false;
				String name = lista[i].getAbsolutePath();
				for(int x=0;x<ex.length;x++) {
					if(name.endsWith(ex[x])) {
						hacer=true;
						break;
					}
				}
				if(!hacer) 
					continue;

				if(name.endsWith("taglibs.jsp")) {
					int xxx=0;
				}
				
				if (!name.endsWith("ReplaceInFiles.java") && (hacer || ext.length()==0)) {
					String sb = readFile(new File(name)).toString();
					if(sb.indexOf(old)!=-1) {
						log.info("archivo=" + name); 
						sb = sb.replaceAll(old, nueva);
						write(name,sb);
					}

				}
			}
		}
	}

	private StringBuffer readFile(File file) {
		StringBuffer sb = new StringBuffer();
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;

		try {
			fis = new FileInputStream(file);

			// Here BufferedInputStream is added for fast reading.
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			// dis.available() returns 0 if the file does not have more lines.
			while (dis.available() != 0) {

				// this statement reads the line from the file and print it to
				// the console.
				sb.append(dis.readLine()).append("\n");
				//log.info(dis.readLine());
			}

			// dispose all the resources after using them.
			fis.close();
			bis.close();
			dis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}

	/** Write fixed content to the given file. */
	public void write(String nameFile, String cadena) throws IOException {
		Writer out = new OutputStreamWriter(new FileOutputStream(nameFile));
		try {
			out.write(cadena);
		} finally {
			out.close();
		}
	}

	public static void main(String[] args)  throws IOException { 
		//ReplaceInFiles c = new ReplaceInFiles(".jsp", "891900", "B78009");
		//ReplaceInFiles c = new ReplaceInFiles(".jsp", "8d6307", "815a06");
		ReplaceInFiles c = new ReplaceInFiles(".jsp,.java", "PERFIL_", "ROL_");
		
		//c.start("c:/temp");
		c.start("D:/appProyectos/betcesc/workspacebetcesc2/game");
		log.info("Finalizado");
	}
}
