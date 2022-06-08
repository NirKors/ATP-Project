package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {return 0;}

    /**
     * Decompresses data compressed by {@link MyCompressorOutputStream}.
     * @param b - the buffer into which the data is read.
     * @return the total number of bytes read into the buffer, or -1 if there is no more data because the end of the
     * stream has been reached.
     */
    @Override
    public int read(byte[] b) throws IOException {
        // Maze row:
        int[] mazeInfo = new int[6]; // Row size, column size, start row, start col, goal row, goal col;
        for (int j = 0; j < 6; j++) {
            byte[] reads = new byte[2];
            for (int i = 0; i < 2; i++) {
                reads[i] = (byte) in.read();
            }
            mazeInfo[j] = ((0xFF & reads[0]) << 8) | (0xFF & reads[1]);
        }
        int i = 0;
        int read = -1;
        byte[] converted;
        do {
            if (i == mazeInfo[0]) {
                b[i++] = 2;
                continue;
            }
            read = in.read();
            if (read == -1)
                break;
            converted = BinToByte((byte) read);
            for (int j = 7; j >= 0; j--) {
                if (i == b.length) {
                    in.readAllBytes();
                    break;
                }
                byte temp = converted[j];
                if (i == mazeInfo[1])
                    b[i++] = 2;
                b[i++] = temp;
            }

        } while (read != -1);

        int index = mazeInfo[2] * mazeInfo[1]; // Row * column amount
        index = index > 0 ? index + 1 : 0;
        index += mazeInfo[3]; // + col
        b[index] = 3;

        index = mazeInfo[4] * mazeInfo[1]; // Row * column amount
        index = index > 0 ? index + 1 : 0;
        index += mazeInfo[5]; // + col
        b[index] = 4;

        return i;
    }


    private byte[] BinToByte(byte b) {
        byte[] bin = new byte[8];

        char[] c = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0').toCharArray();
        for (int i = 0; i < 8; i++)
            if (c[i] == '1')
                bin[i] = 1;

        return bin;
    }

}