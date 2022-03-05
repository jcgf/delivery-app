package com.jcgfdev.deliveryapp.security.services.impl;

import com.jcgfdev.deliveryapp.security.services.IBuildEmailService;
import org.springframework.stereotype.Service;

@Service
public class BuildEmailService implements IBuildEmailService {

    @Override
    public String buildEmail(String name, String link) {
        return "<div style=\"\n" +
                "    display: grid;\n" +
                "    justify-content: center;\n" +
                "    margin: 20px;\n" +
                "    font-family: Roboto, 'Helvetica Neue', sans-serif;\n" +
                "    color: #0000008a;\">\n" +
                "        <div style=\"\n" +
                "        max-width: 780px; \n" +
                "        padding: 20px; \n" +
                "        border-radius: 20px; \n" +
                "        text-align: center;\n" +
                "        box-shadow: 0px 2px 1px -1px rgb(0 0 0 / 20%), \n" +
                "                    0px 1px 1px 0px rgb(0 0 0 / 14%), \n" +
                "                    0px 1px 3px 0px rgb(0 0 0 / 12%);\">\n" +
                "            <div style=\"text-align: center;\"> \n" +
                "                <img src=\"https://img.freepik.com/vector-gratis/paquete-entrega-gratis-plantilla-banner-scooter_318482-25.jpg?size=626&ext=jpg\" alt=\"logo\" width=\"600px\">\n" +
                "            </div>\n" +
                "            <h2 style=\"text-align: center; font-size: 24px;\">Hola, " + name + "</h2>\n" +
                "            <h1 style=\"\n" +
                "            text-align: center; \n" +
                "            color: #0000008a;\n" +
                "            font-size: 36px;\n" +
                "            font-weight: bold;\n" +
                "            line-height: normal;\">Bienvenido a <strong style=\"color: #3e1314;\">DeliveryAPP</strong></h1>\n" +
                "            <div style=\"padding: 20px 0px; font-size: 18px; text-align: justify;\">\n" +
                "                <div>\n" +
                "                    Gracias por ingresar a DeliveryApp de clic en el siguiente boton para activar su cuenta.\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div style=\"padding-top: 40px; text-align: center;\">\n" +
                "                <a href=\"" + link + "\" target=\"_blank\" style=\"\n" +
                "                color: #3e1314;\n" +
                "                font-weight: bold;\n" +
                "                font-size: 18px;\n" +
                "                text-decoration: none; \n" +
                "                border-radius: 5px; \n" +
                "                border: 1px solid rgba(0, 0, 0, 0.12);\n" +
                "                padding: 10px 20px;\">Activar Cuenta</a>\n" +
                "            </div>\n" +
                "            <div><br></br><br></br><br></br></div>" +
                "            <div>" +
                "               <strong style=\"color:#000000;font-size:18px\"> " +
                "                       El enlace de confirmación caducará en 15 minutos.\n" +
                "               </strong>" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>";
    }
}
