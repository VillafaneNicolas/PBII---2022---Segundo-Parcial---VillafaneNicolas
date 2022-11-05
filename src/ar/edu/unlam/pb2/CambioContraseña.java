package ar.edu.unlam.pb2;

public class CambioContrase�a extends NoMonetaria implements Monitoreable{
	
	String usuario;
	String contrase�aAnterior;
	String contrase�aNueva;
	
	//Constructores
	public CambioContrase�a(Dispositivo dispositivo,Cliente cliente, Banco banco) {
		super(dispositivo,cliente, banco); 
	}
	
	

	public CambioContrase�a(Dispositivo dispositivo, Cliente cliente, Banco banco, String usuario, String contrase�aAnterior,
			String contrase�aNueva) {
		super(dispositivo,cliente, banco); 
		this.usuario = usuario;
		this.contrase�aAnterior = contrase�aAnterior;
		this.contrase�aNueva = contrase�aNueva;
	}
	
	//Getters and setters
	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getContrase�aAnterior() {
		return contrase�aAnterior;
	}



	public void setContrase�aAnterior(String contrase�aAnterior) {
		this.contrase�aAnterior = contrase�aAnterior;
	}



	public String getContrase�aNueva() {
		return contrase�aNueva;
	}



	public void setContrase�aNueva(String contrase�aNueva) {
		this.contrase�aNueva = contrase�aNueva;
	}

	



	
	
	

}
