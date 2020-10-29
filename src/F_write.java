
import java.io.*;

public class F_write {

    private FileOutputStream oStream;
    private String fName;
    private long fSize;

    public F_write (String fn) throws FileNotFoundException {

        this.fName = fn;
        File dir = new File(".\\downloads\\");
        dir.mkdir();

        File f = new File (".\\downloads\\"+this.fName);

        this.oStream = new FileOutputStream(f);

    }

    public void writeData(byte[] data){

        try{
            this.oStream.write(data);

        }catch(IOException e){
            System.out.println("Exception: " + e);
        }

    }




}
