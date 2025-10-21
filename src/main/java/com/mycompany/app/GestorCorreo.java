package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class GestorCorreo {

    private final List<Correo> bandejaEnviados = new ArrayList<>();

    public void enviarCorreo(Correo correo) {
        if (correo == null) {
            throw new IllegalArgumentException("El correo no puede ser null.");
        }
        if (correo.getRemitente() == null || correo.getRemitente().isEmpty()) {
            throw new IllegalArgumentException("El correo debe tener un remitente.");
        }
        if (correo.getDestinatarios() == null || correo.getDestinatarios().isEmpty()) {
            throw new IllegalArgumentException("El correo debe tener al menos un destinatario.");
        }

        // Simulación de envío (Lo almaceno en la bandeja de enviados)
        bandejaEnviados.add(correo);
    }

    public List<Correo> getBandejaEnviados() {
        return bandejaEnviados;
    }
}

