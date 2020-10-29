import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    public void menu() throws Exception{
        serCom cp = new serCom("COM4", 230400);

        Scanner scan= new Scanner(System.in);
        System.out.println("1-Receber ficheiro");
        System.out.println("2-Enviar ficheiros");
        int opcao=scan.nextInt();

        if(opcao==1){
            if(cp.portStatus()){
                System.out.println("Conexao efetuada!");

                byte[] datagram_r = cp.receive();
                Receive_Data rD = new Receive_Data(datagram_r);
                System.out.println("\nHeader: " + rD.getHeader());
                System.out.println(rD.getDataSize());

                Scanner scan_fn = new Scanner(System.in);
                String fn = scan_fn.nextLine();

                F_write fw = new F_write(fn+"."+rD.getHeader());
                fw.writeData(rD.getRData());

            }else{
                System.out.println("Erro na abertura de porta!");
            }

        }
        if(opcao==2){
            System.out.println("Nome do ficheiro:");
            Scanner scan1= new Scanner(System.in);
            String fn= scan1.nextLine();

            F_read f=new F_read(fn);
            Datagram d= new Datagram(f.getFSize());
            d.buildHead(f.getFExtension());
            d.buildData(f.getData());

            if(cp.portStatus()){


                byte[] datagram1 = d.getDatagram();
                cp.send(datagram1, d.getDataSize());
                System.out.println("Ligacao a porta efectuada, a comecar o envio");
            }else{
                System.out.println("Erro na abertura de porta para envio");
            }

        }
    }
}
