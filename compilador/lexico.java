import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JTextArea;


public class lexico {

	String tablaSim[][];
	final static String alfabeto="abcdefghijklmnñopqrstuvwxyz";
	final static String digitos="1234567890";
	final static String simbolos=",{}=[]@";
	final static String caracterEspecial="'().;#\"";
	final static String operadores="+-*/";
	final static String relacionales[]={"<",">","<=",">=","==","!="};
	final static String logicos[] ={"^","","","","",""};  //falta agregar los caracteres logicos
 	final static String reservadas[]={"all","and","as","char","character","create","date","default","float","foreign","from","insert","intro","key","not","numeric","or","primary","references","schema","select","table","unique","user","values","where","null" };
	
	
	lexico(String Texto, JTextArea Resultado){
		
		Resultado.setText("");
		StringTokenizer st = new StringTokenizer(Texto);
		tablaSim=new String[3][st.countTokens()];
	    int contador =0; 
		while (st.hasMoreTokens()) {		   
			 String token =st.nextToken();
	    	 
			 tablaSim[0][contador]=contador+"";
	    	 tablaSim[1][contador]=token.toLowerCase();
	    	 tablaSim[2][contador]=getTipo(token);
	         
	    	 Resultado.append(tablaSim[0][contador]+"     "+tablaSim[1][contador]+"       "+tablaSim[2][contador]);
	         Resultado.append("\n");
	         contador++;
	     }
		
	}
	
	
	public String getTipo(String token){
		 String tipo="";			 
		 
		 token=token.toLowerCase();
		 
		 if(identificador(token)){
			 if(reservada(token))
				 return tipo="Palabra Reservada";
			 return tipo ="Identificador";
		 }else {
			 String numero =numero(token);
			 if(numero != ""){
			 return tipo =numero;
		 }else{ 
			 String simbolo =simbolo(token);			 
			 if(simbolo != ""){
			 return tipo =simbolo;
			 }
		 }
		 }
		 return tipo =" Caracter No valido"; 
	 }
	

	private String simbolo(String token) {
		String tipo="";
		if(checarSimbolo(token.charAt(0))){
			return tipo = "Simbolo";
		}else if(checarEspecial(token.charAt(0))){
			return tipo = "Caracter Especial";
		}else if(checarOperador(token.charAt(0))){
			return tipo = "Operador";
		}else if(checarRelacional(token)){
			return tipo = "Operador Relacional";
		}
		
		return tipo="";
	
	}



	private boolean reservada(String token) {
		for(int i=0;i<reservadas.length;i++){
			if(token.equals(reservadas[i]))
			{
				return true;
			}
			}
		return false;
		
	}



	public boolean identificador(String token){
		
		if(checarAlfabeto(token.charAt(0))){
			for(int i=1;i<token.length();i++){
			
				if(checarAlfabeto(token.charAt(i)) | checarDigito(token.charAt(i))){				
			     }else{
			    	 return false;
			     }
			}
			return true;	
		}
		return false;
	
	}
	
	public boolean checarSimbolo(char simbolo){
		for(int i=0;i<simbolos.length();i++){
			if(simbolo == simbolos.charAt(i) )
			{
				return true;
			}
			}
		return false;
	}
	
	public boolean checarOperador(char operador){
		for(int i=0;i<operadores.length();i++){
			if(operador == operadores.charAt(i) )
			{
				return true;
			}
			}
		return false;
	}
	
	public boolean checarRelacional(String relacional){
		for(int i=0;i<relacionales.length;i++){
			if(relacional.equals(relacionales[i]))
			{
				return true;
			}
			}
		return false;
	}
	
	public boolean checarEspecial(char especial){
		for(int i=0;i<caracterEspecial.length();i++){
			if(especial == caracterEspecial.charAt(i))
			{
				return true;
			}
			}
		return false;
	}
	
	public boolean checarAlfabeto(char caracter){
		for(int i=0;i<alfabeto.length();i++){
			if(caracter == alfabeto.charAt(i) )
			{
				return true;
			}
			}
		return false;
	}
	
	public boolean checarDigito(char digito){
		for(int i=0;i<digitos.length();i++){
			if(digito== digitos.charAt(i) )
			{
				return true;
			}
			}
		return false;
	}
	
	
	public String numero(String token){
		String tipo="";
		 int contador = 0;
		 for(int i =0;i<token.length();i++){
			 if(checarDigito(token.charAt(i))|token.charAt(i)=='.'){ 
				 if(token.charAt(i)=='.'){
					contador = contador +1; 
					if (contador == 1){
					 for(int j=i+1;j<token.length();j++){
						 if(checarDigito(token.charAt(j))){
							
						 }else{
							 return tipo ="";
						 }
					 }
					 return tipo="Flotante";
					}else{
						 return tipo="";
					 } 
				 }
				 
			 }else{
				 return tipo="";
			 }
		}
	return tipo="Numero";	 
	}
	
}
