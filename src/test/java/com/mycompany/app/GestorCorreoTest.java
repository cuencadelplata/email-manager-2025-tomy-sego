package com.mycompany.app;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class GestorCorreoTest {

    private GestorCorreo gestor;

    @Before
    public void setUp() {
        gestor = new GestorCorreo();
    }

    @Test
    public void alEnviarUnCorreoSeGuardaEnLaBandejaDeEnviados() {
        // Crear remitente y destinatarios
        Contacto remitente = new Contacto("Segovia", "sego@gmail.com");
        List<Contacto> destinatarios = List.of(
                new Contacto("Pit", "pit@gmail.com"),
                new Contacto("Sanfer", "sanfer@gmail.com")
        );

        //Crear correo
        Correo correo = new Correo("Hola", "como estan los pibes", remitente, destinatarios);

        // Enviar correo
        gestor.enviarCorreo(correo);

        // Verificar que se guarde en enviados para sego
        assertEquals(1, gestor.getBandejaEnviados().size());
        assertTrue(gestor.getBandejaEnviados().contains(correo));
    }

    @Test(expected = IllegalArgumentException.class)
    public void noSePuedeEnviarCorreoNull() {
        gestor.enviarCorreo(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noSePuedeEnviarCorreoSinRemitente() {
        // Lista de destinatarios válida
        List<Contacto> destinatarios = List.of(
                new Contacto("Destino", "destino@gmail.com")
        );

        // Remitente nulo
        Correo correo = new Correo("Asunto", "Mensaje", null, destinatarios);

        gestor.enviarCorreo(correo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noSePuedeEnviarCorreoConRemitenteSinEmail() {
        // Remitente con email vacío
        Contacto remitenteVacio = new Contacto("tonysoprano", "");

        List<Contacto> destinatarios = List.of(
                new Contacto("Destino", "destino@gmail.com")
        );

        Correo correo = new Correo("Asunto", "Mensaje", remitenteVacio, destinatarios);

        gestor.enviarCorreo(correo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noSePuedeEnviarCorreoSinDestinatarios() {
        // Remitente válido
        Contacto remitente = new Contacto("Yo", "yo@gmail.com");

        // Lista vacía de destinatarios
        Correo correo = new Correo("Asunto", "Mensaje", remitente, List.of());

        gestor.enviarCorreo(correo);
    }

    @Test
    public void buscarCorreoPorTexto() {
        Contacto remitente = new Contacto("Sego", "sego@gmail.com");
        Contacto dest1 = new Contacto("Pit", "pituqui@gmail.com");
        Contacto dest2 = new Contacto("Sanfer", "sanfer@gmail.com");

        Usuario usuario = new Usuario(new Contacto("Laucha", "lauchita@gmail.com"));

        Correo correo1 = new Correo("Reunión", "No te olvides la necar", remitente, List.of(dest1));
        Correo correo2 = new Correo("Recordatorio", "Traer birra", remitente, List.of(dest2));

        usuario.recibirCorreo(correo1);
        usuario.recibirCorreo(correo2);

        List<Correo> resultado = usuario.buscarEnBandejaEntrada("Reunión");

        assertEquals(1, resultado.size());
    }

}