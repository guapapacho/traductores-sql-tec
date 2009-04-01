import java.util.StringTokenizer;

import javax.swing.JTextArea;


public class sintactico {

	String tablalex[][];
	JTextArea pantallita;
	
	public sintactico(String text, JTextArea Resultado) {
		Resultado.setText("");		
		lexico lx = new lexico(text,Resultado);
		tablalex = lx.tablaSim;
		pantallita = Resultado;
		create();
		
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
	
//Aquí empieza el Create.	
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
			pantallita.append("no pifa en token: "+contador+", esperaba table");	
			return "no pifa en token: "+contador+", esperaba table";
			}
		}else{
			pantallita.append("no pifa en token: "+contador+", esperaba create");
			return "no pifa en token: "+contador+", esperaba create";
		}		
		return "Create (Sintáctico)";
		
	}

	
	public String regla1Create(int x){
		int contador = x;
		if (tablalex[2][contador].equals("Identificador")){
			contador++;
			if (tablalex[1][contador].equals("(")){
				contador++;
				regla2Create(contador);
			}else{
			pantallita.append("no pifa en token: "+contador+", esperaba create");	
			return "no pifa en token: "+contador+", esperaba (";}
		}else{
		pantallita.append("no pifa en token: "+contador+", esperaba Identificador");	
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
		pantallita.append("no pifa en token: "+contador+"faltaba ID");		
		return "no pifa en token: "+contador;}
		System.out.println("REGLA2");
		return "paso regla2";
	}
	
	public String regla2aCreate(int x){
		int contador = x;
		System.out.println(contador);
		if (tablalex[2][contador].equals("Palabra Reservada")){
			contador++;
				if(tablalex[1][contador].equals("(")){
				contador++;
					if(tablalex[2][contador].equals("Numero")){
					contador++;
						if(tablalex[1][contador].equals(")")){
						contador++;
						regla3Create(contador);
					}else{
						pantallita.append("no pifa en token: "+contador+"Falta )");	
					return "no pifa en token: "+contador;}
				}else{
					pantallita.append("no pifa en token: "+contador+"Falta Numero");
				return "no pifa en token: "+contador;}
			}else{
				regla3Create(contador);}
		}else{
		pantallita.append("no pifa en token: "+contador+"Falta Palabra Reservada");	
		return "no pifa en token: "+contador+"Falta Palabra Reservada";
			}
		
		System.out.println("REGLA2A");
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
		pantallita.append("no pifa en token: "+contador+"Falto , ó )");
		return "no pifa en token: "+contador;}
		System.out.println("REGLA3");
		return "paso regla3";
	}
	
	public String regla4Create(int x){
		int contador = x;
		if (tablalex[1][contador].equals(";")){
			pantallita.append("La cadena de Tokens es un Create Valido");
			return "La cadena de Tokens es un Create Valido";
			}else {
				pantallita.append("no pifa en token: "+contador+"se esperaba ;");
			}
		System.out.println("REGLA4");
		return "paso regla4";
	}

	




}
