package com.admon.bss.util;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {
 // String key = UUID.randomUUID().toString().replaceAll("-", "");
    public String key = "a48aff8392354db0b59b44c074be1027";
    private static MessageDigest messageDigest = null;


    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException exception) {
        }
    }

    /**
     *     Descripcion : Metodo encargado de encriptar un cadena especifica
     *
     *     Historico   : 29-ene-2009 - Jorge J Leon A - creacion de metodo
     *
     *     @param cadena Cadena a encriptada
     *     @return cadena encriptada
     */
    public static String encriptar(String cadena) {

        byte[] cadenaByte = cadena.getBytes();
        messageDigest.update(cadenaByte, 0, cadenaByte.length);
        byte[] cadenaEncriptadaByte = messageDigest.digest();
        return toHexString(cadenaEncriptadaByte);
    }

    /**
     *     Descripcion : Metodo encargado de tranformar un arreglo de bytes a una cadena con formato hexadecimal
     *
     *     Historico   : 29-ene-2009 - Jorge J Leon A - creacion de metodo
     *
     *     @param buf arreglo con elementos e tipo byte
     *     @return cadena en formato hexadecimal
     */
    private static String toHexString(byte[] buf) {
        char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuffer strBuf = new StringBuffer(buf.length * 2);
        for (int i = 0; i < buf.length; i++) {
            strBuf.append(hexChar[(buf[i] & 0xf0) >>> 4]); // fill left wit
            // zero bits
            //strBuf.append(':');
            strBuf.append(hexChar[buf[i] & 0x0f]);
        }

        return strBuf.toString();
    }
    
public String encrypt(String value) throws GeneralSecurityException {
SecretKeySpec sks = new SecretKeySpec(hexStringToByteArray(key), "AES");
Cipher cipher = Cipher.getInstance("AES");
cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
byte[] encrypted = cipher.doFinal(value.getBytes());
return byteArrayToHexString(encrypted);
}

public String decrypt(String message) throws GeneralSecurityException {
SecretKeySpec sks = new SecretKeySpec(hexStringToByteArray(key), "AES");
Cipher cipher = Cipher.getInstance("AES");
cipher.init(Cipher.DECRYPT_MODE, sks);
byte[] decrypted = cipher.doFinal(hexStringToByteArray(message));
return new String(decrypted);
}

private String byteArrayToHexString(byte[] b) {
StringBuilder sb = new StringBuilder(b.length * 2);
for (int i = 0; i < b.length; i++) {
int v = b[i] & 0xff;
if (v < 16) {
sb.append('0');
}
sb.append(Integer.toHexString(v));
}
return sb.toString().toUpperCase();
}

private byte[] hexStringToByteArray(String s) {
byte[] b = new byte[s.length() / 2];
for (int i = 0; i < b.length; i++) {
int index = i * 2;
int v = Integer.parseInt(s.substring(index, index + 2), 16);
b[i] = (byte) v;
}
return b;
}
}

