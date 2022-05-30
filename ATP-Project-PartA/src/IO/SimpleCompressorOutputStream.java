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


    /**
     * Writes the specified byte to this output stream. The general
     * contract for {@code write} is that one byte is written
     * to the output stream. The byte to be written is the eight
     * low-order bits of the argument {@code b}. The 24
     * high-order bits of {@code b} are ignored.
     * <p>
     * Subclasses of {@code OutputStream} must provide an
     * implementation for this method.
     *
     * @param b the {@code byte}.
     * @throws IOException if an I/O error occurs. In particular,
     *                     an {@code IOException} may be thrown if the
     *                     output stream has been closed.
     */
    @Override
    public void write(int b) throws IOException {
        // TODO ?
    }


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
