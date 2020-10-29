import java.io.ByteArrayOutputStream;

public class Datagram {

    private byte[] datagram;
    private ByteArrayOutputStream baos;

    public Datagram(long size){
        this.baos = new ByteArrayOutputStream();
        this.datagram = new byte[(int)size];
    }

    public byte[] getDatagram(){
        this.datagram = baos.toByteArray();
        return datagram;
    }

    public void buildHead(String f_type) throws Exception {
        Header h = new Header(f_type);
        baos.write(h.getHeader());

        System.out.println("\nHeader adicionado");
    }

    public void buildData(byte[] data) throws Exception{
        baos.write(data);
        System.out.println("Data adicionado ao datagrama");

    }

    public int getDataSize(){
        return this.datagram.length;
    }

}
