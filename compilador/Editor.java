import java.util.*;
import java.awt.*;
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

class Editor extends JFrame implements ActionListener
{
	
	
	
	//Menu
	JMenuBar MBarra=new JMenuBar();
	JMenu MArchivo=new JMenu("Archivo");
	JMenuItem MNuevo=new JMenuItem("Nuevo");
	JMenuItem MAbrir=new JMenuItem("Abrir");
	JMenuItem MSalir=new JMenuItem("Salir");	
	JMenuItem MGuardar=new JMenuItem("Guardar");
	JMenu MEdicion=new JMenu("Edicion");
	JMenuItem MCortar=new JMenuItem("Cortar");
	JMenuItem MCopiar=new JMenuItem("Copiar");
	JMenuItem MPegar=new JMenuItem("Pegar");
	JMenuItem MBuscar=new JMenuItem("Buscar");
	JMenuItem MRemplazar=new JMenuItem("Reemplazar");
	JMenuItem MSelec=new JMenuItem("Seleccionar todo");
	 
	JMenuItem MLexico=new JMenuItem("Lexico");
	JMenuItem MSintactico=new JMenuItem("Sintactico");
	//Toolbar
	JToolBar TBarra=new JToolBar(); 
		JButton BNuevo=new JButton();
		JButton BAbrir=new JButton();
		JButton BCopiar=new JButton();
		JButton BCortar=new JButton();
		JButton BPegar=new JButton();	
		JButton BGuardar=new JButton();
		
	//PopUpMenu Boton derecho	
	JPopupMenu PopMenu=new JPopupMenu (); 
  	JMenuItem PopCortar=new JMenuItem ("Cortar");
  	JMenuItem PopCopiar=new JMenuItem ("Copiar");
  	JMenuItem PopPegar=new JMenuItem ("Pegar");
  	JMenuItem PopSelTodo=new JMenuItem ("Seleccionar Todo");
	
				
	//Imagenes 
	ImageIcon INuevo=new ImageIcon("nuevo.gif");
	ImageIcon IAbrir=new ImageIcon("abrir.gif");
	ImageIcon ICopiar=new ImageIcon("copiar.gif");
	ImageIcon ICortar=new ImageIcon("cortar.gif");
	ImageIcon IPegar=new ImageIcon("pegar.gif");
	ImageIcon IGuardar=new ImageIcon("guardar.gif");
	ImageIcon IBuscar=new ImageIcon("buscar.gif");
	ImageIcon ISalir=new ImageIcon("salir.gif");
	
		String nombre=" "; //nobre del programa
	
	//cajas de texto
	JTextArea Texto = new JTextArea();
	
	JTextArea Errores=new JTextArea(6,1);
	
	String Copiar="";
	
	Editor()
	{
		
		//Menu
		MArchivo.add(MNuevo);
		MNuevo.setIcon(INuevo);
		MNuevo.addActionListener(this);
		MArchivo.add(MAbrir);
		MAbrir.setIcon(IAbrir);
		MAbrir.addActionListener(this);
		MArchivo.add(MSalir);
		MSalir.addActionListener(this);
		MArchivo.add(MGuardar);
		MGuardar.setIcon(IGuardar);
		MGuardar.addActionListener(this);
		MArchivo.addSeparator();
		MArchivo.add(MSalir);
		MSalir.setIcon(ISalir);
		MSalir.addActionListener(this);
		MEdicion.add(MCortar);
		MCortar.setIcon(ICortar);
		MCortar.addActionListener(this);
		MEdicion.add(MCopiar);
		MCopiar.setIcon(ICopiar);
		MCopiar.addActionListener(this);
		MEdicion.add(MPegar);
		MPegar.setIcon(IPegar);
		
		MEdicion.addSeparator();
		MEdicion.add(MBuscar);
		MBuscar.setIcon(IBuscar);
		MBuscar.addActionListener(this);
		MPegar.addActionListener(this);
		MEdicion.add(MRemplazar);
		MRemplazar.addActionListener(this);
		MEdicion.addSeparator();
		MEdicion.add(MSelec);
		MSelec.addActionListener(this);
		MBarra.add(MArchivo);
		//MBarra.add(MEdicion);
		setJMenuBar(MBarra);
		
		//ToolBar
		TBarra.add(MLexico);
		MLexico.addActionListener(this);
		TBarra.add(MSintactico);
		MSintactico.addActionListener(this);
		TBarra.add(BNuevo);
		BNuevo.addActionListener(this);
		BNuevo.setIcon(INuevo);
		TBarra.add(BAbrir);
		BAbrir.addActionListener(this);
		BAbrir.setIcon(IAbrir);
		TBarra.add(BGuardar);
		BGuardar.setIcon(IGuardar);
		BGuardar.addActionListener(this);
		TBarra.addSeparator();
		/*TBarra.add(BCopiar);
		BCopiar.setIcon(ICopiar);
		BCopiar.addActionListener(this);
		TBarra.add(BCortar);
		BCortar.setIcon(ICortar);
		BCortar.addActionListener(this);
		TBarra.add(BPegar);
		BPegar.setIcon(IPegar);		
		BPegar.addActionListener(this);*/
		BGuardar.setToolTipText ("Guardar");
		BNuevo.setToolTipText ("Nuevo");
		BAbrir.setToolTipText ("Abrir");
		//BCopiar.setToolTipText ("Copiar");
		//BCortar.setToolTipText ("BCortar");
		//BPegar.setToolTipText ("Pegar");
		
		add(TBarra,"North");
		Texto.requestFocus();
		TBarra.setFloatable(false);
		
		//PopUpMenu
		PopCortar.addActionListener (this);
        PopMenu.add (PopCortar); 
        PopCopiar.addActionListener(this);
        PopMenu.add(PopCopiar);
        PopPegar.addActionListener(this);
        PopMenu.add(PopPegar);
               
        PopCortar.setIcon (ICortar);
        PopCopiar.setIcon(ICopiar);
        PopPegar.setIcon(IPegar);
        Texto.setComponentPopupMenu (PopMenu);
		
		//Aadir barras de scroll a la caja de texto principal
		JScrollPane barrillas=new JScrollPane(Texto,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(barrillas);
		
		//Aadir barras de scroll a la caja de texto de los errores
		JScrollPane barrasError=new JScrollPane(Errores,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(barrasError,"South");
		
		
		//Cerrar Ventana
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			 {
			 	System.exit(0);
			 }
		});
		
		setTitle("Editor");	
	    setSize(800,600);
		setVisible(true);
			
	}
	
	
		public void actionPerformed(ActionEvent ae)
		 {
			if(ae.getSource()==MLexico) {
								
		      lexico lx = new lexico(Texto.getText(),Errores);	
			}
			if(ae.getSource()==MSintactico) {
				
			      sintactico sint = new sintactico(Texto.getText(),Errores);	
				}
		 			 	
		 	if(ae.getSource()==MSalir) dispose();
		 	
		 	if(ae.getSource()==MNuevo || ae.getSource()==BNuevo) Texto.setText("");
		 	
		 	if(ae.getSource()==MCopiar || ae.getSource()==PopCopiar || ae.getSource()==BCopiar)	Texto.copy();
		 	
		 	if(ae.getSource()==MPegar || ae.getSource()==PopPegar || ae.getSource()==BPegar)	Texto.paste();
		 	
		 	if(ae.getSource()==MCortar || ae.getSource()==PopCortar || ae.getSource()==BCortar) Texto.cut();
		 	
		 	if(ae.getSource()==MSelec) 
		 	{
		 		Texto.requestFocus();
		 	    Texto.selectAll();
		 	}		
		 	
		 	if(ae.getSource()==BAbrir) Abrir();
		 	
		 	if(ae.getSource()==BGuardar )
		 	{
		 		Guardar ();
		 	}
		 	
		 	if(ae.getSource()==MAbrir)
		 	{
		 	    Abrir ();	
		 	}
		 	
		 	if(ae.getSource()==MGuardar)
		 	{
			Guardar (); 	   
		 	}
		 	
		 	if(ae.getSource()==MBuscar)
		 	{
		 		Busca Bes=new Busca(this,"Buscar...",true);
		 	}
		 	if(ae.getSource()==MRemplazar)
		 	{
		 		BuscaRemplaza ByR=new BuscaRemplaza(this,"Buscar y Reemplazar",true);
		 	}
		 	
		 			 }
		
		
		 void Abrir ()
		 {
		 		String Text="";
	            try
				{
	           		JFileChooser fc=new JFileChooser(System.getProperty("user.dir"));
		 			fc.showOpenDialog(this);
		 			File Abrir=fc.getSelectedFile(); //Devuelve el File que vamos a leerName
		 			
		 			if(Abrir!=null)
		 			{
		 				nombre=fc.getSelectedFile().getName();
		 			}
		 	
		 			if(Abrir!=null)
		 			{
		 				Texto.setText("");	
		 				FileReader Fichero=new FileReader(Abrir);
		 				BufferedReader leer=new BufferedReader(Fichero);
		 				while((Text=leer.readLine())!=null)
		 					{
		 					 Texto.append(Text+ "\n"); //append Concatena la linea leida
		 					}
		 					
		 		  		leer.close();
		 			}
		 			
		 		}
		 		catch(IOException ioe)
					{
					  System.out.println(ioe);
					}
		 }
		 
		 void Guardar ()
		 {
		 		String Text="";
		 		try
		 		{
		 			JFileChooser fc=new JFileChooser(System.getProperty("user.dir"));
		 			fc.showSaveDialog(this);
		 			File Guardar =fc.getSelectedFile();
		 			if(Guardar !=null)
		 			{
			 			nombre=fc.getSelectedFile().getName();
			 			FileWriter  Guardx=new FileWriter(Guardar);
			 			Guardx.write(Texto.getText());
			 			Guardx.close();
		 		    }
		 				 			
		 		 }
		 	   catch(IOException ioe)
					{
					  System.out.println(ioe);
					}	
		 }
		 
	 
		 
		 public static void main (String []args)
		 {
		 	new Editor();
		 }
		 
		 public String getTipo(String token){
			 String tipo="";			 
			 
			 
				 if(token.charAt(0)==('a')){
					 for(int i =1;i<token.length();i++){
						 if(token.charAt(i)==('a')||token.charAt(i)==('1')){
							 return tipo ="Identificador";
						 }
			 }}
			 
			 return tipo ="No valido"; 
		 }
}