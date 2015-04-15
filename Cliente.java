import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;

public class Cliente{
    public static void main(String[] args) {
        String servidor = "127.0.0.1";
        int puerto = 5000;
        String entradaTeclado="";
        try{
            Socket socket= new Socket (servidor,puerto);
            BufferedReader in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            //Instancia Escaner
            System.out.println("Selecciona el numero que deseas:");
            System.out.println("1:- Nombre del Usuario");
            System.out.println("2:- Nombre de la Maquina e IP");
            System.out.println("3:- Fecha y Hora");
            System.out.println("4:- Unidad de Discos");
            System.out.println("5:- Direccion MAC");
            Scanner entradaEscaner = new Scanner (System.in);
            entradaTeclado = entradaEscaner.nextLine ();
            
            //String c = "Este es el cliente ";
            out.println(entradaTeclado);
            String line = "";
            
            while  ((line = in.readLine()) != null){
                System.out.println(line);
                break;
            }
            socket.close();
        } catch (IOException e)
        {
            System.out.println("Error en conexión!!!");
        }
        
    }
}
