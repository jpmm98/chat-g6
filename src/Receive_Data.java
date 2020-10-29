import java.util.Arrays;
import java.util.Base64;

public class Receive_Data {

    private byte[] header;
    private byte[] data;


    public Receive_Data(byte[] data_r){
        int extend = 0;
        while(data_r[extend] != 46){
            extend++;
        }

        this.header = Arrays.copyOfRange(data_r, 0,extend);
        this.data = Arrays.copyOfRange(data_r, extend+1, data_r.length);

    }

    public String getHeader()   {

        String s = new String(this.header);
        return s;
    }

    public byte[] getRData()    {   return this.data;}

    public int getDataSize() {  return this.data.length;}
}
