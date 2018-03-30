#include <iostream>
#include <fstream>

using namespace std;

int main(int argc, char *argv[])
{

  if (argc != 3)
    cout << "Uso: printer cadena puerto\n" << endl;
  else
    {
	  char* cadena;
      char* puerto;
      char* palabras;
	  int i = 0; 
	  
      cadena = argv[1];
      puerto = argv[2];
	  
	  palabras=strtok(cadena,"_");
 
	  ofstream impresora;
      impresora.open(puerto);
      for(i=0;palabras;i++){
        //printf("%s\n",palabras);
	    impresora << palabras << "\f" << endl;
        palabras=strtok(0,"_");
       }	  
      impresora.close();
	  
    }

return 0;
}

