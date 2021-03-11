package com.admon.bss.util;

import java.nio.charset.Charset;

/**
 * @author luis
 */
public class Aes {
    public final static String str_encode_utf8 ="UTF-8";
    public final static String str_encode_iso88591 ="ISO-8859-1";
    public static final Charset utf8charset = Charset.forName("UTF-8");
    public static final Charset iso88591charset = Charset.forName("ISO-8859-1");
    private final static String pwd = "XmdukHGRv45cDF";/*llave original*/
//    private final static String pwd = "XmdukHGRv45cDFa";


    /**
     *
     * @param plaintext
     * @param enc
     * @return
     */
    public static String encrypt(String plaintext) {
        return encrypt(plaintext, pwd);
    }

    /**
     * Funcion que desencripta una cadena
     *
     * @param ciphertext
     * @param enc
     * @return el texto desencriptado como cadena
     * @throws Exception
     */
    public static String decrypt(String plaintext) throws Exception {
            return decrypt(plaintext, pwd);
    }

    /**
     * Funcion que desencripta una cadena que contenga numeros
     *
     * @param plaintext
     * @return el numero desencriptado como Integer
     * @throws ExcepcionCrypto
     */
    public static Integer decryptInt(String plaintext) throws Exception{
            String resp = decrypt(plaintext, pwd);
            return Integer.parseInt(resp.equals("")?"0":resp);
    }

    /**
     * Funcion que desencripta una cadena que contenga numeros
     *
     * @param plaintext
     * @return el numero desencriptado como Double
     * @throws ExcepcionCrypto
     */
    public static Double decryptDouble(String plaintext) throws Exception {
            String resp = decrypt(plaintext, pwd);
            return Double.parseDouble(resp.equals("")?"0":resp);
    }

    public static Float decryptFloat(String plaintext) throws Exception {
            String resp = decrypt(plaintext, pwd);
            return Float.parseFloat(resp.equals("")?"0":resp);
    }

    private static String decrypt(String ciphertext, String password) throws Exception {
        return SuperBoletosCryptiOS.AES128DecryptText(ciphertext, password);
    }

    private static String encrypt(String plaintext, String password) {
        try {
            return SuperBoletosCryptiOS.AES128EncryptText(plaintext, password);
        } catch (Exception ex) {
            return "";
        }
    }
}
