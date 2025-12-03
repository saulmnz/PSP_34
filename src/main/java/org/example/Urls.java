package org.example;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.util.Scanner;

public class Urls {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("INTRODUCE LA PRIMERA URL: ");
        String url1 = sc.nextLine();
        System.out.print("INTRODUCE LA SEGUNDA URL: ");
        String url2 = sc.nextLine();

        // CREAMOS EL CLIENTE HTTP
        HttpClient cliente = HttpClient.newHttpClient();

        // LANZAMOS AMBAS PETICIONES CON MEDICION DE TIEMPO EN MILISEGUNDOS CON LA CLASE SYSTEM
        long inicio1 = System.currentTimeMillis();

        HttpResponse<String> resp1 = cliente.send(

                // CREAMOS EL OBJETO URI A PARTIR DEL STRING QUE DA EL USUARIO ( URLs )
                HttpRequest.newBuilder(URI.create(url1))
                        // TIEMPO DE ESPERA PARA ESTA PETICIÓN
                        .timeout(Duration.ofSeconds(10))
                        // CONSTRUIMOS LA PETICIÓN REAL, LA INSTANCIA FINAL DE HTTPREQUEST
                        .build(),

                // OBTENEMOS EL CUERPO COMO STRING
                HttpResponse.BodyHandlers.ofString()
        );

        // CALCULAMOS LA DIFERENCIA DE TIEMPO ENTRE EL MOMENTO JUSTO QUE SE HACE LA PETICIÓN
        // Y EL MOMENTO DESPUÉS QUE SE ENVÍE Y TERMINE
        long tiempo1 = System.currentTimeMillis() - inicio1;
        // NÚMERO DE CARACTERES DEL CUERPO
        int tama1 = resp1.body().length();

        // LO MISMO PARA LA SEGUNDA URL -----------------------------------------------------------------------
        long inicio2 = System.currentTimeMillis();
        HttpResponse<String> resp2 = cliente.send(
                HttpRequest.newBuilder(URI.create(url2))
                        .timeout(Duration.ofSeconds(10))
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );
        long tiempo2 = System.currentTimeMillis() - inicio2;
        int tama2 = resp2.body().length();


        // IMPRIMIMOS LOS RESULTADOS POR PANTALLA
        System.out.print("LA WEB MÁS RÁPIDA HA SIDO: ");
        if (tiempo1 < tiempo2) {
            System.out.println(url1 + " COOON " + tiempo1 + " MS.");
        } else {
            System.out.println(url2 + " COOON " + tiempo2 + " MS.");
        }

        System.out.print("LA WEB CON MÁS CONTENIDO HA SIDO: ");
        if (tama1 > tama2) {
            System.out.println(url1 + " COOON " + tama1 + " CARACTERES.");
        } else {
            System.out.println(url2 + " COOON " + tama2 + " CARACTERES.");
        }
    }
}


