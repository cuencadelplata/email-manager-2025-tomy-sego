package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private Contacto contacto;
    private List<Correo> bandejaEnviados = new ArrayList<>();
    private List<Correo> bandejaRecibidos = new ArrayList<>();

    public Usuario(Contacto contacto){
        this.contacto = contacto;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public List<Correo> getBandejaEnviados() {
        return bandejaEnviados;
    }

    public List<Correo> getBandejaRecibidos() {
        return bandejaRecibidos;
    }

    public void recibirCorreo(Correo correo){
        bandejaRecibidos.add(correo);
    }
    public void enviarCorreo(Correo correo){
        bandejaEnviados.add(correo);
    }

    public List<Correo> buscarEnBandejaEntrada(String textoBusqueda) {
    List<Correo> resultados = new ArrayList<>();

    for (Correo correo : bandejaRecibidos) {

        if (correo.getAsunto().contains(textoBusqueda)
            || correo.getContenido().contains(textoBusqueda)
            || correo.getRemitente().getNombre().contains(textoBusqueda)
            || correo.getRemitente().getEmail().contains(textoBusqueda)) {

            resultados.add(correo);
        } else {
            for (Contacto destinatario : correo.getDestinatarios()) {
                if (destinatario.getNombre().contains(textoBusqueda) || destinatario.getEmail().contains(textoBusqueda)) {
                    resultados.add(correo);
                    break; 
                }
            }
        }
    }

    return resultados;
    }   

}