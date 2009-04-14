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
	public void select(){
		int contador =0;
	
		if(tablalex[1][contador].equals("select")){
			contador++;
			if(tablalex[1][contador].equals("*")){
				contador++;
				if(tablalex[1][contador].equals("from")){
				
				select2(tablalex, contador);	
					
				}else pantallita.append("Error token "+contador+" Se espera un 'from'");				
	
			}else if(tablalex[2][contador].equals("Identificador")){
				
				select1(tablalex, contador);
			}else{
				pantallita.append("Error token "+contador+" Se espera un 'ID'");
			}
		}else{
			pantallita.append("Error token "+contador+" Se espera un 'Select'");
		}
	}
	
	
	public void select1(String[][] tabla, int contador){
		contador++;
		
		if(tabla[1][contador].equals("from")){
			pantallita.append("bien en el from");
		select2(tablalex,contador);
		} else if (tabla[1][contador].equals(",")){
			contador++;
			if(tabla[2][contador].equals("Identificador")){
				pantallita.append("bien en el ID");
		select1(tablalex,contador);		
			}else pantallita.append("Error token "+contador+" Se espera un identificador");
		}else pantallita.append("Error token "+contador+" Se espera 'from' o ','");
	}
	
	public void select2(String[][] tabla, int contador){
		contador++;
		if(tabla[2][contador].equals("Identificador")){
			select3(contador);
		}else pantallita.append("Error token "+contador+" Se espera Idendificador");
	}
	
	public void select3(int contador){
	contador++;
		if(tablalex[1][contador].equals(";")){
			pantallita.append("You Win XD");
		}else if(tablalex[1][contador].equals("where")){
			contador++;
			if(tablalex[2][contador].equals("Identificador")){
			contador++;
				if(tablalex[2][contador].equals("OR")){
					select4(contador);
				}
			}
		}
	}
	
	public void select4(int contador){
	contador++;
		if(tablalex[2][contador].equals("Numero")){
			select7(contador);
		}else if(tablalex[1][contador].equals("\"")){
			select5(contador);
		}else pantallita.append("Error token "+contador+" Se espera Numero o \"");
	}

	public void select5(int contador){
	contador++;
		if((tablalex[1][contador].equals("\""))){
			pantallita.append("Error token "+contador+" Se espera valor diferente de Null");
		}else{
			contador--;
			select6(contador);
		}
	}
	
	
	public void select6(int contador){
	contador++;
		if(tablalex[1][contador]!=null){		
		  if(tablalex[1][contador].equals("\"")){
			  select7(contador);
		  } else{
			  pantallita.append(" contador " + contador+ " length "+ tablalex[0].length+"\n");
			  if(contador < (tablalex[0].length -1) ){
			  select6(contador);
			  }
			  else{
				  pantallita.append("Error token "+(contador +1)+" Se espera comillas o something");
			  }
		  }
		}
	}
	public void select7(int contador){
		contador++;
		if(tablalex[1][contador].equals(";")){
			pantallita.append("rox! \\m/_ >o<");
		}else{
			pantallita.append("Error: Se esperaba \";\"");
		}
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
