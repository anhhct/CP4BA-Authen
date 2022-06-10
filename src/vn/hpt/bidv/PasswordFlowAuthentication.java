package vn.hpt.bidv;


import sun.misc.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class PasswordFlowAuthentication {
    public static String doAuthen(String url, String username, String Password) throws Exception{
        URLConnection connection = new URL(url).openConnection();
        connection.setDoOutput(true); // Triggers POST.
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + "UTF-8");
        String query = "grant_type=password&username=" + username + "&password=" + Password +"&scope=openid";
        try (OutputStream output = connection.getOutputStream()) {
            output.write(query.getBytes("UTF-8"));
        }

        InputStream response = connection.getInputStream();
        Scanner s = new Scanner(response).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        return result;
    }
    public static void main(String[] args) {
        try{
            //String resp = doAuthen(args[0], args[1], args[2]);
            String resp = doAuthen("https://cp-console.apps.ibmocp.ldapudtest.com/idprovider/v1/auth/identitytoken", "hptadmin", "123456a@");
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
