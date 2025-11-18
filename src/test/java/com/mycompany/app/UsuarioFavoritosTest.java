package com.mycompany.app;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UsuarioFavoritosTest {

    private Usuario usuario;
    private Correo correo1;
    private Correo correo2;

    @Before
    public void setUp() {
        Contacto contactoUsuario = new Contacto("Usuario", "usuario@gmail.com");
        usuario = new Usuario(contactoUsuario);

        Contacto remitente = new Contacto("Juan", "juan@gmail.com");
        Contacto dest1 = new Contacto("Pedro", "pedro@gmail.com");
        Contacto dest2 = new Contacto("Maria", "maria@gmail.com");

        correo1 = new Correo("Reunión importante", "Nos vemos mañana", remitente, List.of(dest1));
        correo2 = new Correo("Promo imperdible", "Descuentos en toda la tienda", remitente, List.of(dest2));

        usuario.recibirCorreo(correo1);
        usuario.recibirCorreo(correo2);
    }

    @Test
    public void sePuedeMarcarComoFavoritoYQuedaEnBandejaFavoritos() {
        assertTrue(usuario.getBandejaFavoritos().isEmpty());

        usuario.marcarComoFavorito(correo1);

        assertEquals(1, usuario.getBandejaFavoritos().size());
        assertTrue(usuario.getBandejaFavoritos().contains(correo1));
        assertTrue(correo1.esFavorito());
    }


    @Test
    public void sePuedeAccederAlCorreoDesdeBandejaFavoritos() {
        usuario.marcarComoFavorito(correo2);

        List<Correo> favoritos = usuario.getBandejaFavoritos();

        assertFalse(favoritos.isEmpty());
        Correo correoFavorito = favoritos.get(0);

        assertEquals("Promo imperdible", correoFavorito.getAsunto());
        assertEquals("Descuentos en toda la tienda", correoFavorito.getContenido());
        assertTrue(correoFavorito.esFavorito());
    }

    @Test
    public void buscarPorFiltroDevuelveTodosLosFavoritos() {
        usuario.marcarComoFavorito(correo1);
        usuario.marcarComoFavorito(correo2);

        List<Correo> favoritosFiltrados = usuario.buscarEnFavoritos("");

        assertEquals(2, favoritosFiltrados.size());
        assertTrue(favoritosFiltrados.contains(correo1));
        assertTrue(favoritosFiltrados.contains(correo2));
    }
}
