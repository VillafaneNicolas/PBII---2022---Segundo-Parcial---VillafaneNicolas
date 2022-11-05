package ar.edu.unlam.pb2;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.AssertionFailedError;

public class jUnitTestCases {

	@Test
	public void queSePuedaCrearUnCliente() {
		String nombre = "jorge";
		String cuit = "20456842455";
		double saldo = 2300;
		Cliente cliente = new Cliente(nombre,cuit,saldo);
		
		assertNotNull(cliente);
		assertTrue(nombre.equals(cliente.getNombre()));
		assertTrue(cuit.equals(cliente.getCuit()));
	}
	
	@Test
	public void queSePuedaCrearUnDispositivo() {
		
		String so = "windows";
		String ip = "192.168.1.1";
		String localidad = "Ituzaingo";
		String imei = "120984";
		boolean biometrico = true;
		
		Dispositivo dispositivo = new Pc(so, ip, localidad);
		
		Dispositivo dispositivo2 = new DispositivoMovil(imei,biometrico);
		
		assertNotNull(dispositivo);
		assertNotNull(dispositivo2);
	}
	
	@Test
	public void queSePuedaMonitorearUnaExtraccion() {
		
		
		Dispositivo dispositivo = new Pc("windows", "192.168.1.1", "Ituzaingo");
		Cliente cliente = new Cliente("jorge","20456842455",400);
		Banco banco = new Banco();
		Monitoreable extraccion = new Extraccion(dispositivo, cliente,banco,"123",200.2);
		
		
		extraccion.monitorear();
		
		
		
		assertFalse(cliente.transaccionesVacio());
	}
	
	@Test
	public void queSePuedaMonitorearUnaTransferencia() {
		
		
		Dispositivo dispositivo = new Pc("windows", "192.168.1.1", "Ituzaingo");
		
		Cliente cliente = new Cliente("jorge","20456842455",400);
		Banco banco = new Banco();
		
		Monitoreable transferencia = new Transferencia(dispositivo, cliente, banco,"123", "51515",200.2);
		
		
		
		transferencia.monitorear();
		
		assertFalse(cliente.transaccionesVacio());
		assertEquals(1, cliente.transaccionesTamaño() );
	}
	
	@Test
	public void queSePuedaMonitorearUnPagoConQR() {
		
		Dispositivo dispositivo = new Pc("windows", "192.168.1.1", "Ituzaingo");
		Cliente cliente = new Cliente("jorge","20456842455",400);
		Banco banco = new Banco();
		Monitoreable qr = new PagoConQR(dispositivo, cliente,banco, "123", 1234);
		
		
		
		qr.monitorear();
		
		assertFalse(cliente.transaccionesVacio());
		assertEquals(1, cliente.transaccionesTamaño() );
	}
	
	@Test
	public void queSePuedaMonitorearUnPagoDeServicio() {
		Dispositivo dispositivo = new Pc("windows", "192.168.1.1", "Ituzaingo");
		Cliente cliente = new Cliente("jorge","20456842455",400);
		Banco banco = new Banco();
		Monitoreable pServicio = new PagoDeServicio(dispositivo, cliente,banco,"123","Edenor",200.2);
		
		
		
		pServicio.monitorear();
		
		assertFalse(cliente.transaccionesVacio());
		assertEquals(1, cliente.transaccionesTamaño() );
	}
	
	@Test
	public void queSePuedaMonitorearUnAltaDeUsuario() {
		Dispositivo dispositivo = new Pc("windows", "192.168.1.1", "Ituzaingo");
		Cliente cliente = new Cliente("jorge","20456842455",400);
		Banco banco = new Banco();
		Monitoreable alta = new AltaUsuario(dispositivo, cliente,banco,"jorge","jorge123");
		
		
		
		alta.monitorear();
		
		assertFalse(cliente.transaccionesVacio());
		assertEquals(1, cliente.transaccionesTamaño());
	}
	
	@Test
	public void queSePuedaMonitorearUnCambioDeContraseña() {
		
		Dispositivo dispositivo = new Pc("windows", "192.168.1.1", "Ituzaingo");
		
		Cliente cliente = new Cliente("jorge","20456842455",4000);
		Banco banco = new Banco();
		Monitoreable cambio = new CambioContraseña(dispositivo, cliente,banco,"jorge","jorge123","jor123");
		
		
		
		cambio.monitorear();
		
		assertFalse(cliente.transaccionesVacio());
		assertEquals(1, cliente.transaccionesTamaño());
	}
	
	@Test
	public void queElScoreDeUnaTransaccionRechazableSinAntecedentesDeCero() throws FraudeException {
		Dispositivo dispositivo = new Pc("windows", "192.168.1.1", "Ituzaingo");
		Banco banco = new Banco();
		Cliente cliente = new Cliente("jorge","20456842455",4000);
		Rechazable transferencia = new Transferencia(dispositivo, cliente, banco,"1234","3214",200);
		transferencia.monitorear(1);
		
		assertEquals(0, cliente.getScore());
		
		
	}
	
	@Test
	public void queUnaTransaccionAlertablePuedaSerMarcadaComoFraudulenta(){
		Dispositivo dispositivo = new Pc("windows", "192.168.1.1", "Ituzaingo");
		Banco banco = new Banco();
		Cliente cliente = new Cliente("jorge","20456842455",4000);
		Monitoreable cambio = new CambioContraseña(dispositivo, cliente, banco,"jorge","123","321");
		cambio.monitorear();
		Dispositivo dispositivo2 = new DispositivoMovil("1234", false);
		Alertable transferencia = new Transferencia(dispositivo2, cliente, banco, "124","321",4000);
		transferencia.confirmarSiFueFraude();
		
		assertFalse(banco.fraudeVacio());
	}
	
	@Test
	public void queElScoreDeUnaTransaccionRechazableConNuevoDispositivoDe20Puntos() throws FraudeException {
		Dispositivo dispositivo = new Pc("windows", "192.168.1.1", "Ituzaingo");
		Banco banco = new Banco();
		Cliente cliente = new Cliente("jorge","20456842455",4000);
		Monitoreable alta = new AltaUsuario(dispositivo, cliente, banco,"jorge","1234");
		alta.monitorear();
		Dispositivo dispositivo2 = new DispositivoMovil("1234", false);
		Rechazable extraccion = new Extraccion(dispositivo2, cliente, banco, "123", 1231);
		
		extraccion.monitorear(1);
		
		assertEquals(20, cliente.getScore());
	}
	
	
	@Test
	public void QueUnaTransaccionDeMasDe60PuntosYMenosDeOchentaSeaAlertadaPeroAprobada() throws FraudeException{
		Dispositivo dispositivo = new Pc("windows", "192.168.1.1", "Ituzaingo");
		Banco banco = new Banco();
		Cliente cliente = new Cliente("jorge","20456842455",4000);
		cliente.setScore(5);
		Monitoreable cambio = new CambioContraseña(dispositivo, cliente, banco,"jorge","123","321");
		cambio.monitorear();
		Rechazable transferencia = new Transferencia(dispositivo, cliente, banco, "124","321",4000);
		transferencia.monitorear(1);
		
		assertEquals(65, cliente.getScore());		
		assertTrue(banco.fraudeVacio());
	}
	
	@Test
	public void queUnaTransaccionDeMasDe80PuntosLanceLaExcepcionFraudeException() throws FraudeException{
		Dispositivo dispositivo = new Pc("windows", "192.168.1.1", "Ituzaingo");
		Banco banco = new Banco();
		Cliente cliente = new Cliente("jorge","20456842455",4000);
		cliente.setScore(5);
		Monitoreable cambio = new CambioContraseña(dispositivo, cliente, banco,"jorge","123","321");
		cambio.monitorear();
		Dispositivo dispositivo2 = new DispositivoMovil("1234", false);
		Alertable transferencia = new Transferencia(dispositivo2, cliente, banco, "124","321",4000);
		transferencia.confirmarSiFueFraude();
		
		assertEquals(85, cliente.getScore());		
		assertFalse(banco.fraudeVacio());
	}
	
	

}
