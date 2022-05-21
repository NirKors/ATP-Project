package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }
    
    @Override
    public int read() throws IOException { //TODO
        int count = -1;
        int ch = -1;
        do {
            ch = in.read();
            for (int i = 0; i < in.read(); i++) {
                // add chars
            }
        }while(ch != -1);
        return count;

    }
}