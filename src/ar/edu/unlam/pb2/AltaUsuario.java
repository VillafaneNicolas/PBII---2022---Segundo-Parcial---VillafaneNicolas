package ar.edu.unlam.pb2;

public class AltaUsuario extends NoMonetaria{
	
	String usuario;
	String contrase�a;
	
	//getters n' setters
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
	//Constructores

	public AltaUsuario(Dispositivo dispositivo,Cliente cliente,Banco banco, String usuario, String contrase�a) {
		super(dispositivo,cliente,banco);
		this.usuario = usuario;
		this.contrase�a = contrase�a;
	}

	public AltaUsuario(Dispositivo dispositivo,Cliente cliente, Banco banco) {
		super(dispositivo,cliente, banco); 
	}
	
	

}
