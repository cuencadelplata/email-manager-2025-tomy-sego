package com.mycompany.app;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

//TEST NUEVO, PRUEBA

public class UsuarioTest {

    private Usuario usuario;
    private Contacto remitente;
    private Contacto destinatario1;
    private Contacto destinatario2;

    @Before
    public void setUp() {
        // Crear contactos
        remitente = new Contacto("Agustin", "agustin@gmail.com");
        destinatario1 = new Contacto("Juan", "juan@gmail.com");
        destinatario2 = new Contacto("Maria", "maria@gmail.com");

        // Crear usuario con su contacto principal
        usuario = new Usuario(remitente);
    }

    // RF-01: Creación de correos
    @Test
    public void sePuedeCrearUnCorreoConRemitenteYDestinatarios() {
        Correo correo = new Correo(
                "Asunto de prueba",
                "Contenido del mensaje",
                remitente,
                List.of(destinatario1, destinatario2)
        );

        assertEquals("Asunto de prueba", correo.getAsunto());
        assertEquals("Contenido del mensaje", correo.getContenido());
        assertEquals(remitente, correo.getRemitente());
        assertEquals(2, correo.getDestinatarios().size());
        assertTrue(correo.getDestinatarios().contains(destinatario1));
    }

    // RF-02: Envío de correos
    @Test
    public void alEnviarUnCorreoSeGuardaEnLaBandejaDeEnviados() {
        Correo correo = new Correo(
                "Hola",
                "Mensaje de prueba",
                remitente,
                List.of(destinatario1, destinatario2)
        );

        usuario.enviarCorreo(correo);

        assertEquals(1, usuario.getBandejaEnviados().size());
        assertTrue(usuario.getBandejaEnviados().contains(correo));
    }

    // RF-02 (parte 2): Recepción de correos
    @Test
    public void alRecibirUnCorreoSeGuardaEnLaBandejaDeRecibidos() {
        Correo correo = new Correo(
                "Recordatorio",
                "Tenés una reunión mañana",
                remitente,
                List.of(destinatario1)
        );

        usuario.recibirCorreo(correo);

        assertEquals(1, usuario.getBandejaRecibidos().size());
        assertTrue(usuario.getBandejaRecibidos().contains(correo));
    }
}