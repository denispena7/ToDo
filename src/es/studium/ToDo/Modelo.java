package es.studium.ToDo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Modelo
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/admintarea";
	String login = "todo";
	String password = "Studium2023;";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	Modelo()
	{
		
	}
	
	public boolean conectar()
	{
		boolean conexionCorrecta = true;
		//Cargar el Driver
		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Se ha producido un error al cargar el Driver");
			conexionCorrecta = false;
		}

		//Establecer la conexión con la base de datos
		try
		{
			connection = DriverManager.getConnection(url, login, password);
		}
		catch(SQLException e)
		{
			System.out.println("Se produjo un error al conectar a la Base de Datos");
			conexionCorrecta = false;
		}
		return conexionCorrecta;
	}
	
	public void desconectar()
	{
		try
		{
			statement.close();
			connection.close();
		}
		catch(SQLException e)
		{
			System.out.println("Error al cerrar " + e.toString());
		}
	}
	
	public boolean validarFecha(String fecha)
	{
		try
		{
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			formatoFecha.setLenient(false);
			formatoFecha.parse(fecha);	
		}
		catch(ParseException e)
		{
			return false;
		}
		return true;
	}

	public String fechaMySQL(String fecha)
	{
		// 21/03/2024 --> 2024/03/21
		String[] fechaCambiada = fecha.split("/");
		return fechaCambiada[2] + "-" + fechaCambiada[1] + "-" + fechaCambiada[0];
	}
	
	public String fechaEuropea(String fecha)
	{
		String[] fechaCambiada = fecha.split("-");
		return fechaCambiada[2] + "-" + fechaCambiada[1] + "-" + fechaCambiada[0];
	}
}