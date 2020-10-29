import com.fazecast.jSerialComm.*;

import java.io.ByteArrayOutputStream;

public class serCom {

    private String portN;
    private SerialPort sPort;
    private int baudrate;


    public serCom(String nomePorta, int bRate){
        this.portN = nomePorta;
        this.baudrate = bRate;

        this.sPort = SerialPort.getCommPort(this.portN);
        this.sPort.openPort();
        this.sPort.setComPortParameters(this.baudrate, 8,1,0);

    }

    public boolean portStatus(){
        return this.sPort.openPort();
    }

    public void send(byte[] packet, int size) {


        try {
            sPort.writeBytes(packet, (long) size);

        } catch (Exception e) {
            this.sPort.closePort();
            this.reconect();
            this.send(packet, size);
        }

    }
    byte[] receive() throws InterruptedException {
        System.out.println("\nReceiving File...");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //        try {
        while (sPort.bytesAvailable() == 0) {
            Thread.sleep(3000);
        }
        int pastbytes = 0;
        while(sPort.bytesAvailable() > pastbytes){
            Thread.sleep(500);
            pastbytes = sPort.bytesAvailable();
        }

            System.out.println("Bytes na trama: " + sPort.bytesAvailable());

            byte[] readBuffer = new byte[pastbytes];
            sPort.readBytes(readBuffer, pastbytes);
            baos.writeBytes(readBuffer);
            for (byte aReadBuffer : readBuffer) {
                System.out.print((aReadBuffer & 0xFF) + " ");
            }

        return baos.toByteArray();
        }




/*
            } catch (Exception e) {
                this.sPort.closePort();
                System.out.println("Thread interrupted");
                System.out.println("Reconnecting");
                this.reconect();
                this.receive();


            }
  */


    public byte receiveConf() {

        byte[] t = new byte[4];

        if (this.sPort.openPort()) {

            try {

                while (sPort.bytesAvailable() != 4) {
                    Thread.sleep(300);
                }

                byte[] readBuffer = new byte[sPort.bytesAvailable()];
                sPort.readBytes(readBuffer, 4);


                t = readBuffer;

            } catch (Exception e) {
                e.printStackTrace();
                this.sPort.closePort();
                this.reconect();
                this.receiveConf();

            }
        }

        System.out.println("\nResponse received: ");
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i] +" ");
        }
        return t[1];
    }

    public void reconect() {
        int[] timer = new int[100];
        System.out.println("\nTrying to open port...");

        for (int t : timer) {
            while (!this.sPort.isOpen()) {

                try {
                    this.sPort.openPort();
                    System.out.print(".");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
    }

    public int getBytesAv() {
        return this.sPort.bytesAvailable();
    }

}




