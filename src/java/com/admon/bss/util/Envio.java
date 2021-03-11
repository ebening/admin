package com.admon.bss.util;

import com.admon.bss.BaseBss;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

public class Envio extends BaseBss {

static Logger logger = Logger.getLogger("Envio.class");

public boolean envioMailCliente() {
boolean b = false;
 // b = envioMail("jorge.estrada@bsdenterprise.com", "Jorge Estrada", "Prueba de Correo", "sospe@hotmail.com", "buen dia Saludos");
return b;
}

public void getFile(String path, String archivo) {
logger.info("Inicia la crecion de archivos..");
try {
new File(path).mkdirs();
logger.info("Creacion de path y archivo");
PrintWriter s1 = new PrintWriter(new BufferedWriter(new FileWriter(path + archivo)));
logger.info("Abriendo y creando contenido");
s1.println(getCSV());
s1.close();
logger.info("Finalizo la carga");
} catch (Exception e) {
logger.error(e.getMessage());
} finally {
logger.info("Finalizo la crecion de archivos");
}
}

public void getFileDelete(String path, String archivo) {
try {
File file = new File(path + archivo);
boolean b = file.delete();
if (b) {
logger.info("Se elimino el archivo");
} else {
logger.info("No se pudo eliminar el archivo");
}
} catch (Exception e) {
logger.error(e.getMessage());
} finally {
}
}

public String getCSV() {
StringBuilder sb = new StringBuilder();
try {
sb.append("jorge");
sb.append(",");
sb.append("estrada");
sb.append("\n");
sb.append("fer");
sb.append(",");
sb.append("garcia");
sb.append("\n");
sb.append("chuy");
sb.append(",");
sb.append("el tigre");
sb.append("\n");
} catch (Exception e) {
logger.error(e.getMessage());
} finally {
}
return sb.toString();
}

/*
* Método que envía un correo electronico.
*
* @param titulo titulo del Mensaje
* @param mensaje
* @param remitente
* @param remitenteContrasena
* @param destinatario
* @param destinatarioNombre
* @param clienteSMTP
* @param puerto
* @param autenticacion
* @param copiasEmail
* @param pathAdjunto
* @return
*/
public boolean envioMail(
String titulo,
String mensaje,
String remitente,
String remitenteContrasena,
String destinatario,
String destinatarioNombre,
String clienteSMTP,
String puerto,
String autenticacion,
String copiasEmail,
String pathAdjunto) {
boolean envio = false;
try {
 // Propiedades de envio
java.util.Properties props = new java.util.Properties();
props.put("mail.smtp.user", remitente);
props.put("mail.smtp.password", remitenteContrasena);
props.put("mail.smtp.host", clienteSMTP);
props.put("mail.smtp.port", puerto);
 //props.put("mail.debug", "true"); 
props.put("mail.smtp.auth", autenticacion);
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.EnableSSL.enable", "true");
props.put("mail.smtp.ssl.trust", clienteSMTP);
 // Autenticar
final String user = remitente;
final String pass = remitenteContrasena;
javax.mail.Authenticator auth = new javax.mail.Authenticator() {
@Override
public PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(user, pass);
}
};
Session sesion = Session.getInstance(props, auth);
try {
logger.info("Preparando correo..");
 // Mensaje
MimeMessage message = new MimeMessage(sesion);
message.setContent("", "text/html");
message.setText(mensaje, "UTF-8", "html");
message.setSubject(titulo);
 // Remitente
Address fromAddress = new InternetAddress(remitente, titulo);
 // Destinatario
Address toAddress = new InternetAddress(destinatario, destinatarioNombre);
message.setFrom(fromAddress);
message.addRecipient(Message.RecipientType.TO, toAddress);
 // Con copia para
if (copiasEmail != null) {
String[] email = copiasEmail.split(",");
Address[] address = new Address[email.length];
for (int i = 0; i < email.length; i++) {
address[i] = new InternetAddress(email[i], titulo);
}
message.addRecipients(Message.RecipientType.BCC, address);
}
 //create the Multipart and add its parts to it
Multipart mp = new MimeMultipart();

 //create and fill the first message part
MimeBodyPart mbp1 = new MimeBodyPart();
mbp1.setText(mensaje);

mp.addBodyPart(mbp1);

if (pathAdjunto != null) {
 // create the second message part
MimeBodyPart mbp2 = new MimeBodyPart();
 // attach the file to the message
FileDataSource fds = new FileDataSource(pathAdjunto);
mbp2.setDataHandler(new DataHandler(fds));
mbp2.setFileName(fds.getName());
mp.addBodyPart(mbp2);
}

 // add the Multipart to the message
message.setContent(mp);
message.setSentDate(new java.util.Date());

Transport.send(message);
envio = true;
logger.info("Correo Enviando");
} catch (Exception e) {
logger.error("Falla debido a " + e.getMessage());
}
} catch (Exception ex) {
logger.error("Falla al envio de correo.." + ex.getMessage());
}
return envio;
}
}

