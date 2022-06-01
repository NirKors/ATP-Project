package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MyDecompressorInputStream extends InputStream { //TODO understand if builtin libraries are allowed.

    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }


    @Override
    public int read(byte[] b) throws IOException {
        // Maze row:
        int[] mazeInfo = new int[6]; // Row size, column size, start row, start col, goal row, goal col;
        for (int j = 0; j < 6; j++){
            byte[] reads = new byte[4];
            for (int i = 0; i < 4; i++) {
                reads[i] = (byte) in.read();
            }
            mazeInfo[j] = ((0xFF & reads[0]) << 24) | ((0xFF & reads[1]) << 16) |
                    ((0xFF & reads[2]) << 8) | (0xFF & reads[3]);
        }
        for (int i = 0; i < b.length; i++) {
            if (i == mazeInfo[0] + 1){
                b[i] = 2;
                continue;
            }
            // TODO: unfold into byte array
        }



        return 0;
    }




    private byte[] BinToByte(byte b){
        byte[] bin = new byte[8];

        char[] c = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0').toCharArray();
        for (int i = 0; i < 8; i++)
            if (c[i] == '1')
                bin[i] = 1;

        return bin;
    }

}