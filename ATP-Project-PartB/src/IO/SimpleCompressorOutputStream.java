package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream{
    OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    /**
     * Helper function
     */
    private void write(byte b, int amount) throws IOException {
        byte count;
        do {
            out.write(b);
            count = (byte) Math.min(255, amount);
            out.write(count);
            amount -= 255;

        } while (amount > 0);
    }



    @Override
    public void write(int b) throws IOException {}


    @Override
    public void write(byte[] b) throws IOException {
        if (b.length == 0) return;

        byte data = b[0];
        int count = 1;

        for (int i = 1; i < b.length; i++) {
            if (data == b[i] && i != b.length - 1) count++;
            else {
                write(data, count);
                data = b[i];
                count = 1;
            }
        }
        write(data, count);
    }
}
