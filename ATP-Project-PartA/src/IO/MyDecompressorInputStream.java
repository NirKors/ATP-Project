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
        return 0;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int count = 0;
        int data, amount;
        do {
            data = in.read();
            amount = in.read();

            for(int i = 0; i < amount; i++)
                b[count++] = (byte) data;

        }while(amount != -1);
        return count;

    }


}