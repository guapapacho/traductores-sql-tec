import java.util.StringTokenizer;

import javax.swing.JTextArea;


public class sintactico {

	String tablalex[][];
	JTextArea pantallita;
	
	public sintactico(String text, JTextArea Resultado) {
		Resultado.setText("");		
		lexico lx = new lexico(text,Resultado);
		tablalex = lx.tablaSim;
		String respuesta=create();
		Resultado.append(respuesta+" ");
		pantallita = Resultado;
		
	}
//Aqui empieza el select.
	public String select(){
		int contador =0;
	
		if(tablalex[1][contador].equals("select")){
			contador++;
			
			
			if(tablalex[1][contador].equals("*")){
				contador++;
				if(tablalex[1][contador].equals("from")){
				
				select2(tablalex, contador);	
					
				}				
	
			}else if(tablalex[2][contador].equals("Identificador")){
				
				select1(tablalex, contador);
				
				
			}else{
				return "no pifa en token: "+contador;
			}
			
			
			
		}
		return "no pifa en token: "+contador;
		
	}
	
	
	public void select1(String[][] tabla, int contador){
		contador++;
		
		if(tabla[2][contador].equals("from")){
		
		} else if (tabla[2][contador].equals(",")){
		
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
	
//Aqui empieza el Create.	
	public String create(){
		int contador =0;
		
		if(tablalex[1][0].equals("create")){
			contador++;
			System.out.println(tablalex[1][0]);
			if(tablalex[1][1].equals("table")){
				contador++;
				regla1Create(contador);		
			}
			else{
			return "no pifa en token: "+contador+", esperaba table";
			}
		}else{
			return "no pifa en token: "+contador+", esperaba create";
		}		
		return "Se acabo la funcion";
		
	}

	
	public String regla1Create(int x){
		int contador = x;
		System.out.println(tablalex[1][contador]);
		if (tablalex[2][contador].equals("Identificador")){
			contador++;
			System.out.println(tablalex[1][contador]);
			if (tablalex[1][contador].equals("(")){
				contador++;
				regla2Create(contador);
			}else{
			return "no pifa en token: "+contador+", esperaba (";}
		}else{
		return "no pifa en token: "+contador+", esperaba Identificador";}
		System.out.println("REGLA1");
		return "paso regla 1";
	}
	
	public String regla2Create(int x){
		int contador = x;
		if (tablalex[2][contador].equals("Identificador")){
			contador++;
			regla2aCreate(contador);
			
		}else{
		return "no pifa en token: "+contador;}
		System.out.println("REGLA2");
		return "paso regla2";
	}
	
	public String regla2aCreate(int x){
		int contador = x;
		if (tablalex[2][contador].equals("Palabra Reservada")){
			contador++;
				if(tablalex[1][contador].equals("(")){
				contador++;
					if(tablalex[2][contador].equals("Numero")){
					contador++;
						if(tablalex[1][contador].equals(")")){
						regla3Create(contador);
					}else{
					return "no pifa en token: "+contador;}
				}else{
				return "no pifa en token: "+contador;}
			}else{
				regla3Create(contador);}
		}else{
		
		return "no pifa en token: "+contador;}
		System.out.println("REGLA2A"+contador);
		return "paso regla 2a";
		}
	
	public String regla3Create(int x){
		int contador = x;
		if (tablalex[1][contador].equals(",")){
			contador++;
			regla2Create(contador);
			}
		else if(tablalex[1][contador].equals(")")){
			contador++;
			regla4Create(contador);
		}else{
		
		return "no pifa en token: "+contador;}
		System.out.println("REGLA3");
		return "paso regla3";
	}
	
	public String regla4Create(int x){
		int contador = x;
		if (tablalex[1][contador].equals(";")){
			return "La cadena de Tokens es un Create Valido";
			}
		System.out.println("REGLA4");
		return "paso regla4";
	}

	




}
