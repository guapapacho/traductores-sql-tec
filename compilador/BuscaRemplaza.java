/*
	Programa: Editor Compilador en java
	Autor: Borja
	Web: http://todojava.awardspace.com/
	Version: 1.0
	
	Descripción: Editor de texto que compila y ejecuta programas en java
	
	Dificultad: Media
*/

//	Clase de Buscar y Remplazar

import java.awt.*;
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;

class BuscaRemplaza extends JDialog implements ActionListener
{
	JLabel LBusc=new JLabel("Buscar                 ",JLabel.LEFT);
	JLabel LRemp=new JLabel("Reemplazar por:",JLabel.LEFT);
	JTextField TBusc=new JTextField(15);
	JTextField TRemp=new JTextField(15);
	
	
	
	JButton Buscar=new JButton("Buscar");
	JButton ReemplazarTodo=new JButton("Reemplazar Todo");
	JButton Reemplazar=new JButton("Reemplazar");
	
	Editor ed;
	int Posicion=0;
	BuscaRemplaza(JFrame DBusRem,String s,boolean b)
	{
	super(DBusRem);
	
	ed=(Editor)DBusRem;
	setLayout(new FlowLayout());
	add(LBusc);
	add(TBusc);
	add(LRemp);
	add(TRemp);
	JPanel Botones=new JPanel();
	Botones.add(Buscar);
	Buscar.addActionListener(this);
	Botones.add(Reemplazar);
	Reemplazar.addActionListener(this);
	Botones.add(ReemplazarTodo);
	ReemplazarTodo.addActionListener(this);
	add(Botones,"South");
	
	setTitle("Buscar y reemplazar");
	setSize(350,130);
	setResizable(false);
	setLocation(200,250);
    setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==Buscar)
		{
			Buscame();
		}
		if(ae.getSource()==ReemplazarTodo)
		{
			ed.Texto.requestFocus();
			String Texto="";
			Texto=ed.Texto.getText();
			String s=Texto.replaceAll(TBusc.getText(),TRemp.getText()); //Remplazar todo
			ed.Texto.setText(s);
		}
		if(ae.getSource()==Reemplazar)
		{
			ed.Texto.requestFocus();
			String Texto="";
			Texto=ed.Texto.getText();
			String s=Texto.replaceFirst(TBusc.getText(),TRemp.getText()); //Remplazar todo
			ed.Texto.setText(s);
		}
		
	}
	void Buscame()
	{
			ed.Texto.requestFocus();
			String Palabra="";
			String TextoBusc="";
		 	Palabra=TBusc.getText();
		 	TextoBusc=ed.Texto.getText();
		 	//busca
		 	Posicion=TextoBusc.indexOf(Palabra,Posicion+1);
		 	if(Posicion!=-1)
		 	ed.Texto.select(Posicion,Posicion + Palabra.length()); //Reemplaza 
		 	else JOptionPane.showMessageDialog(this,"No se ha encontrado: " + Palabra);	 
	}
}