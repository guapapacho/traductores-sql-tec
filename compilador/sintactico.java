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
		select();
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

	
//Aqu� empieza el Create.	
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
		return "Create (Sint�ctico)";
		
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
		pantallita.append("no pifa en token: "+contador+"Falto , � )");
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
	
	
	//Aqu� empieza el Update.	
	public String update(){
		int contador =0;
		
		if(tablalex[1][0].equals("update")){
			contador++;		
			update1(contador);
			System.out.println(tablalex[1][0]);
		
		}else{
			pantallita.append("no pifa en token: "+contador+", esperaba update");
			return "no pifa en token: "+contador+", esperaba update";
		}		
		return "Update (Sint�ctico)";
		
	}

   public String update1 (int x){
	   int contador = x;
	   if (tablalex[2][contador].equals("Identificador")){
			contador++;
			if(tablalex[1][contador].equals("set")){
				contador++;
				update2(contador);
			}else {
				pantallita.append("no pifa en token: "+contador+"se esperaba SET");
		}
		}
	   else{
			pantallita.append("no pifa en token: "+contador+", esperaba Identificador");
			}	
				
		
	   return "no pifa en token: "+contador+", esperaba Iden";
   }

   public String update2 (int x){
	   int contador = x;
	   if (tablalex[2][contador].equals("Identificador")){
			contador++;
			if(tablalex[1][contador].equals("=")){
				contador++;
				update2a(contador);
			}else {
				pantallita.append("no pifa en token: "+contador+"se esperaba =");
		}
		}
	   else{
			pantallita.append("no pifa en token: "+contador+", esperaba Identificador");
			}	
				
		
	   return "no pifa en token: "+contador+", esperaba";
   }
   
   public String update2a (int x){
	   int contador = x;
	   if (tablalex[2][contador].equals("Numero")){
			contador++;
			update3(contador);
		}
		
	   else if(tablalex[1][contador].equals("\"")){
			contador++;
		    if(tablalex[2][contador].equals("Identificador")){
		    	contador++;
		    	if(tablalex[1][contador].equals("\"")){
		    		contador++;
		    		update3(contador);
		    	} else {pantallita.append("no pifa en token: "+contador+", esperaba \"");}
		    } else {pantallita.append("no pifa en token: "+contador+", esperaba Identificador");}
	   }else {pantallita.append("no pifa en token: "+contador+", esperaba \" � Numero");}
	   
				
		
	   return "no pifa en token: "+contador+", esperaba";
   }
   
   public String update3 (int x){
	   int contador = x;
	   
	   if (tablalex[1][contador].equals(",")){
			contador++;
			update2(contador);
		}
	  
	   else if(tablalex[1][contador].equals("where")){
			contador++;
			update4(contador);
		}
	   
	   else if(tablalex[1][contador].equals(";")){
		   pantallita.append("Update acabo correctamente");
		   return "se acabo update";
	   }else {pantallita.append("no pifa en token: "+contador+", esperaba ; � WHERE � ,");}
	   
	   return "no pifa en token: "+contador+", esperaba";
   }
   
   public String update4 (int x){
	   int contador = x;
	   if (tablalex[2][contador].equals("Identificador")){
			contador++;
			if(tablalex[1][contador].equals("=")){
				contador++;
				update4a(contador);
			}else {
				pantallita.append("no pifa en token: "+contador+"se esperaba =");
		}
		}
	   else{
			pantallita.append("no pifa en token: "+contador+", esperaba Identificador");
			}	
				
		
	   return "no pifa en token: "+contador+", esperaba";
   }
   
   public String update4a (int x){
	   int contador = x;
	   if (tablalex[2][contador].equals("Numero")){
			contador++;
			update5(contador);
		}
		
	   if(tablalex[1][contador].equals("\"")){
			contador++;
		    if(tablalex[2][contador].equals("Identificador")){
		    	contador++;
		    	if(tablalex[1][contador].equals("\"")){
		    		contador++;
		    		update5(contador);
		    	} else {pantallita.append("no pifa en token: "+contador+", esperaba \"");}
		    } else {pantallita.append("no pifa en token: "+contador+", esperaba Identificador");}
			}else {pantallita.append("no pifa en token: "+contador+", esperaba \" � Numero");}	
				
		
	   return "no pifa en token: "+contador+", esperaba";
   }
   
   public String update5 (int x){
	   int contador = x;
	   
	   if (tablalex[1][contador].equals("and")){
			contador++;
			update4(contador);
		} 
	   
	   else if(tablalex[1][contador].equals("or")){
			contador++;
			update4(contador);
		}
	   
	   else if(tablalex[1][contador].equals(";")){
		   pantallita.append("Update acabo correctamente");
		   return "se acabo update";
	   }else {pantallita.append("no pifa en token: "+contador+", esperaba ; � AND u OR");}
	   
	   return "no pifa en token: "+contador+", esperaba";
   }
	
	

	//empieza el delete
	public String delete(){
		int contador=0;
		if(tablalex[1][contador].equals("delete") && checkArray(contador)){
			contador++;
			if(tablalex[1][contador].equals("from") && checkArray(contador)){
				contador++;
				if(tablalex[2][contador].equals("Identificador") && checkArray(contador)){
					delete1(contador);
				}else{
					pantallita.append("Error token "+contador+" Se esperaba un 'ID' segudio de un ; o 'where'");
				}
				
			}else{
				pantallita.append("Error token "+contador+" Se esperaba un 'from' seguido de un ID");
			}
			
		}else{
			pantallita.append("Error token "+contador+" Se esperaba un 'delete' seguido de from");
			
		}
		return null;
		
	}
	
	private void delete1(int contador) {
		contador++;
		if(tablalex[1][contador].equals(";")){
			pantallita.append("Exito en DELETE");
		}else if(tablalex[1][contador].equals("where")&& checkArray(contador)){
			contador++;
				if(tablalex[2][contador].equals("Identificador")&& checkArray(contador)){
					contador++;
					if(tablalex[2][contador].equals("OR")&& checkArray(contador)){
						delete2(contador);
					}else{
						pantallita.append("Error token "+contador+" Se esperaba un 'OR' seguido de un numero o sentencia");
					}
				}else{
					pantallita.append("Error token "+contador+" Se esperaba un 'ID' seguido de OR");
				}
		}else {
			pantallita.append("Error token "+contador+" Se esperaba un ';' o un 'where' seguido de un ID");
		}
		
	}
	
	public void delete2(int contador){
		contador++;
			if(tablalex[2][contador].equals("Numero")&& checkArray(contador)){
				delete5(contador);
			}else if(tablalex[1][contador].equals("\"") && checkArray(contador)){
				delete3(contador);
			}else pantallita.append("Error token "+contador+" Se espera Numero seguido de ';'  o inicio de (\") seguido de un argumento");
		}

		public void delete3(int contador){
		contador++;
			if((tablalex[1][contador].equals("\""))&& checkArray(contador)){
				pantallita.append("Error token "+contador+" Se espera valor diferente de Null y algo seguido de las comillas");
			}else{
				contador--;
				delete4(contador);
			}
		}
		
		
		public void delete4(int contador){
		contador++;
			if(tablalex[1][contador]!=null){		
			  if(tablalex[1][contador].equals("\"") && checkArray(contador)){
				  delete5(contador);
			  } else{
				  if(checkArray(contador) ){
				  delete4(contador);
				  }
				  else{
					  pantallita.append("Error token "+(contador +1)+" Se espera comillas o algo mas, o comillas seguido de ';'");
				  }
			  }
			}
		}
		public void delete5(int contador){
			contador++;
			if(tablalex[1][contador].equals(";")){
				pantallita.append("Fin, exito en el DELETE");
			}else{
				pantallita.append("Error: Se esperaba \";\"");
			}
		}
		
		public boolean checkArray(int contador){
			if(contador < (tablalex[0].length -1) )
				return true;
			return false;
		}	




}
