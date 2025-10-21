package com.mycompany.app;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class GestorCorreoTest {

    private GestorCorreo gestor;

    @Before
    public void setUp() {
        gestor = new GestorCorreo();
    }

    @Test
    public void alEnviarUnCorreoSeGuardaEnLaBandejaDeEnviados() {
        String remitente = "sego@gmail.com";
        List<String> destinatarios = List.of("pit@gmail.com", "sanfer@gmail.com");

        Correo correo = new Correo("Saludo", "Hola los pibes", remitente, destinatarios);
        gestor.enviarCorreo(correo);

        assertEquals(1, gestor.getBandejaEnviados().size());
        assertTrue(gestor.getBandejaEnviados().contains(correo));
    }

    @Test(expected = IllegalArgumentException.class)
    public void noSePuedeEnviarCorreoNull() {
        gestor.enviarCorreo(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noSePuedeEnviarCorreoSinRemitente() {
        Correo correo = new Correo("Asunto", "Mensaje", "", List.of("destino@gmail.com"));
        gestor.enviarCorreo(correo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noSePuedeEnviarCorreoSinDestinatarios() {
        Correo correo = new Correo("Asunto", "Mensaje", "yo@gmail.com", List.of());
        gestor.enviarCorreo(correo);
    }
}
