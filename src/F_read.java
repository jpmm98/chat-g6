import java.io.File;
import java.io.FileInputStream;
import org.apache.commons.io.FilenameUtils;



public class F_read {
    private FileInputStream inStream;
    private String fName;
    private long fSize;

    public F_read(String fn) throws Exception {
        this.fName = fn;

        File dir = new File(".\\to_transfer\\");
        dir.mkdir();

        try {
            File f = new File(".\\to_transfer\\" + this.fName);
            this.fSize = f.length();
            System.out.println("File size: " + this.fSize);
            this.inStream = new FileInputStream(f);
        } catch (Exception e) {
            System.out.println("Erro a encontrar ficheiro");
            e.printStackTrace();
        }
    }

    public byte[] getData() throws Exception{
        byte[] data;

        data = new byte[(int)this.fSize];
        this.inStream.read(data);

        return data;
    }
    public String getFExtension(){ return FilenameUtils.getExtension(this.fName); }
    public long getFSize()   {return this.fSize;}
    public String getfName() {return fName;}

}
