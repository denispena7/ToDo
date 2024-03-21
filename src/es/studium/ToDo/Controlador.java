package es.studium.ToDo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Controlador implements WindowListener, ActionListener
{
	Modelo modelo;
	Vista vista;
	
	Controlador(Modelo m, Vista v)
	{
		this.modelo = m;
		this.vista = v;
		
		vista.ventana.addWindowListener(this);
		vista.dlgMensaje.addWindowListener(this);
		vista.btnNueva.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		if(actionEvent.getSource().equals(vista.btnNueva)) 
		{
			String tarea = vista.txtTarea.getText();
			String fecha = vista.txtFecha.getText();
			String precio = vista.txtPrecio.getText();
			
			// Validación
			if(tarea.length() < 5 || fecha.equals("") || precio.isBlank())
			{
				vista.lblMensaje.setText("Los campos no pueden estar vacíos");
				vista.dlgMensaje.setVisible(true);
			}
			else
			{
				try
				{
					Double precioNumero = Double.parseDouble(precio);
					// Validación fecha
					if(modelo.validarFecha(fecha) == false)
					{
						vista.lblMensaje.setText("La fecha es incorrecta");
						vista.dlgMensaje.setVisible(true);
					}
					else
					{
						vista.txtTarea.setText("");
						vista.txtFecha.setText("");
						vista.txtPrecio.setText("");
						vista.txtTarea.requestFocus();
						
						vista.txaLista.append(tarea + "#" + fecha + "#" + precio + "\n");
						
						// Conectar BD
						modelo.conectar();
						// Dar de alta un registro (INSERT)
						String sentencia = "INSERT INTO tareas VALUES (NULL, '"+tarea+"', '"+modelo.fechaMySQL(fecha)+"', "+precioNumero+");";
						// Ejecutar la sentencia
						// Desconectar 
						modelo.desconectar();
					}
				}
				catch(NumberFormatException nfe)
				{
					vista.lblMensaje.setText("El precio debe ser un número");
					vista.dlgMensaje.setVisible(true);
				}
			}
		}
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		if(vista.dlgMensaje.isActive())
		{
			vista.dlgMensaje.setVisible(false);
		}
		else
		{
			System.exit(0);	
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosed(WindowEvent e){}
	@Override
	public void windowIconified(WindowEvent e){}
	@Override
	public void windowDeiconified(WindowEvent e){}
	@Override
	public void windowActivated(WindowEvent e){}
	@Override
	public void windowDeactivated(WindowEvent e){}
}