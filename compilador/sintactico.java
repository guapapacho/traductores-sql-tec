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
	
		if(tablalex[1][contador].equals("select")){
			contador++;
			
			
			if(tablalex[1][contador].equals("*")){
				contador++;
				if(tablalex[1][contador].equals("from")){
					
					
					
				
				}				
	
			}else if(tablalex[2][contador].equals("Identificador")){
				contador++;
				select1(tablalex, contador);
				
				
			}else{
				return "no pifa en token: "+contador;
			}
			
			
			
		}
		return "no pifa en token: "+contador;
		
	}
	
	
	public void select1(String[][] tabla, int contador){
		
		if(tabla[2][contador].equals("from")){
		select2(tabla,contador++);
		} else if (tabla[2][contador].equals(",")){
		select1(tabla, contador++);	
		} System.out.println("");
	}
	
	public void select2(String[][] tabla, int contador){
		
	}
	public void select3(){
	
	}
	public void select4(){
	
	}
	public void select5(){
	
	}
	public void select6(){
	
	}
	public void select7(){
		
	}


}
