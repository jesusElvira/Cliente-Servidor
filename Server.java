import java.net.*;
import java.io.*;
import java.lang.management.*;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Date;


public class Server{
    public static void main(String args[]){
        try {
            Server server= new Server();
            server.menu();
            
            
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    public void menu(){
        try {
            
            int puerto = 5000;
            
            ServerSocket s = new ServerSocket(puerto);
            String comandoSalir = "X";
            String entrada = "";
            System.out.println("Servidor iniciado en el puerto " + puerto + "...");
            while(true){
                Socket s1 = s.accept();
                System.out.println("Aceptando conexion...");
                PrintWriter out = new PrintWriter(s1.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(s1.getInputStream()));
                
                while ((entrada = in.readLine()) != null) {
                    System.out.println(entrada);
                    //out.println(entrada + " numero uno");
                    switch (entrada) {
                        case "1":
                            //nombre del usuario
                            String nombreuser = System.getProperty("user.name");
                            out.println("Usted Selecciono "+ entrada +" Nombre del Usuario: " + nombreuser);                            break;
                        case "2":
                            //nombre de la Maquina
                            InetAddress Address = InetAddress.getLocalHost();
                            out.println("Usted Selecciono "+ entrada +" Nombre de la Maquina/IP: " + Address);
                            break;
                        case "3":
                            //Fecha y hora
                            out.println("Usted Selecciono "+ entrada +" Fecha Y hora: " + new Date());
                            break;
                        case "4":
                            //Discos de unidad
                            File[] roots = File.listRoots();
                            for (int i=0; i<roots.length; i++) {
                            out.println("Usted Selecciono "+ entrada +" Discos de Unidad " + roots[i]);}
                            break;
                        case "5":
                            out.println("Usted Selecciono "+ entrada +" DireccionMac "+ direccionMac());
                            break;
                        default:
                            out.println("No Aplica esa Opcion");
                            break;
                            
                    }
                    
                    if (entrada.trim().equals(comandoSalir))
                        return;
                } 
                s1.close();
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    public StringBuilder direccionMac()throws UnknownHostException, SocketException, NumberFormatException{
        StringBuilder dirMac= new StringBuilder();
        try{
            NetworkInterface a = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            //Obtenemos su MAC Address, pero nos devuelve un array de bytes
            //Por lo que hay que convertirlos a Hexadecimal
            byte[] b = a.getHardwareAddress();
            String[] macAddres = new String[6];
            for (int i = 0; i < b.length; i++) {
                //Tratamos los valores que devuelven < 0 normalmente son el "3 y 5 par"
                if (b[i] < 0) {
                    //Convertimos el byte a Hexadecimal con la clase Integer
                    String tmp = Integer.toHexString(b[i]);
                    //Los numeros que son menores a cero al momento de convertirlo a string nos devuelven una cadena de este tipo ffffffAA por lo que unicamente tomamos los ultimos 2 caracteres que son lo que buscamos. y obtenemos esos ultimos caracteres con substring
                    macAddres [i]= (tmp.substring(tmp.length() - 2).toUpperCase());
                    continue;
                }else{
                    String aux = Integer.toHexString(b[i]);
                    if(aux.length() < 2){
                        macAddres [i]= ("0"+Integer.toHexString(b[i]));
                    }else{
                        macAddres [i]= (Integer.toHexString(b[i]));
                    }
                }
            }
            
            for(int x = 0;x < macAddres.length ;x++){
                
                if(x<macAddres.length-1){
                    dirMac.append(macAddres [x] + "-");
                }else{
                    dirMac.append(macAddres [x]);
                }
            }
            
        }catch(Throwable e){
            e.printStackTrace();
        }
        return dirMac;
    }
}

