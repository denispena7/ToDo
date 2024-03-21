package es.studium.ToDo;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;

public class Vista
{
	Frame ventana = new Frame("ToDo");
	Label lblTarea = new Label("Tarea");
	TextField txtTarea = new TextField(20);
	Label lblFecha = new Label("Fecha");
	TextField txtFecha = new TextField(20);
	Label lblPrecio = new Label("Precio");
	TextField txtPrecio = new TextField(20);
	Button btnNueva = new Button("Nueva");
	TextArea txaLista = new TextArea(13, 30);
	
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);
	Label lblMensaje = new Label("");
	
	Vista()
	{
		ventana.setLayout(new FlowLayout());
		dlgMensaje.setLayout(new FlowLayout());
		
		ventana.add(lblTarea);
		ventana.add(txtTarea);
		ventana.add(lblFecha);
		ventana.add(txtFecha);
		ventana.add(lblPrecio);
		ventana.add(txtPrecio);
		ventana.add(btnNueva);
		ventana.add(txaLista);
		
		dlgMensaje.add(lblMensaje);
		
		ventana.setSize(270, 400);
		dlgMensaje.setSize(250, 80);
		ventana.setResizable(false);
		dlgMensaje.setResizable(false);
		ventana.setLocationRelativeTo(null);
		dlgMensaje.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
}