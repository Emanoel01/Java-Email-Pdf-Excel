package com.planlhas.teste.TestePlanilha.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

import java.io.File;

public class EnvioEmail {

    public Boolean sendExtatoByEmail(String driverEmail,File extrato){

        Boolean envio = true;

        String companyEmail = "";
        String passwordEmail = "";

        MultiPartEmail multiPartEmail = new MultiPartEmail();
        multiPartEmail.setHostName("smtp.gmail.com");
        multiPartEmail.setSmtpPort(465);
        multiPartEmail.setAuthenticator(new DefaultAuthenticator(companyEmail,passwordEmail));
        multiPartEmail.setSSLOnConnect(true);

        try{

            multiPartEmail.setFrom(companyEmail);
            multiPartEmail.setSubject("Extrato Quinzenal");
            multiPartEmail.setMsg("Segue extrato referente a quinzena. Abraços equipe redefrete \n");
            multiPartEmail.addTo(driverEmail);

//            EmailAttachment anexo = new EmailAttachment();
//            anexo.setPath(extrato.getPath());

            multiPartEmail.attach(extrato);
            multiPartEmail.send();

            envio = true;
        }catch (Exception e){
            e.printStackTrace();
            envio = false;
        }

        return envio;
    }

}
