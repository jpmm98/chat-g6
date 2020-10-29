import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Header {

    private byte[] f_type;
    private ByteArrayOutputStream baos;


    public Header(String ftype) throws IOException {

        baos = new ByteArrayOutputStream();
        baos.write(ftype.getBytes());
        baos.write(46);
        this.f_type = baos.toByteArray();
        System.out.println(baos.toString());

    }

    public byte[] getHeader() { return this.f_type;}


}