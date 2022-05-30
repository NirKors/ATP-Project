package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream{

    InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
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
            if (data == -1)
                return count;
            amount = in.read() - 128;

            for(int i = 0; i < amount; i++)
                b[count++] = (byte) data;

        }while(amount != -1);
        return count;

    }

}
