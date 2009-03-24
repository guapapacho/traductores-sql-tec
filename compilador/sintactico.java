import java.util.StringTokenizer;

import javax.swing.JTextArea;


public class sintactico {

	String tablalex[][];
	
	public sintactico(String text, JTextArea Resultado) {
		Resultado.setText("");		
		lexico lx = new lexico(text,Resultado);
		tablalex = lx.tablaSim;
		String respuesta=select();
		Resultado.append(respuesta+" ");
		
		
	}
	
	
	public String select(){
		int contador =0;
	
		if(tablalex[1][0].equals("select")){
			contador++;
			
			
			if(tablalex[1][1].equals("*")){
				if(tablalex[1][2].equals("from")){
					if(tablalex[1][3].equals("")){
						//mandar a regla 1
					}
					
				
				}				
	
			}else if(tablalex[2][1].equals("Identificador")){
				
		//mandar a regla 1
				
				
			}else{
				return "no pifa en token: "+contador;
			}
			
			
			
		}
		return "no pifa en token: "+contador;
	}

}
