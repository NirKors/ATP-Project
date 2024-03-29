package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyCompressorOutputStream extends OutputStream {

    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    /**
     * Compresses a maze the following way:
     * <p>
     * Six couples of bytes are used for integers at the start, signifying the following information in order:
     * <p>
     * Row amount, column amount, start position row, start position column, goal position row, goal position column.
     * </p>
     * The following information is then converted to "binary". Since information about walls is either 0 or 1,
     * we can use one byte to show information about eight bytes. For example:
     * <p>
     * <example>
     * <code>
     * Series of bytes: [0, 1, 1, 0, 0, 1, 0, 1]
     * Will be displayed with byte 101 - its binary value matching the series.
     * </code>
     * </example>
     * </p>
     * </p>
     * @param b - The data.
     * @return compressed byte array.
     */
    public static byte[] compress(byte[] b) {
        int[] size = getMazeSize(b);

        ArrayList<Byte> final_array = new ArrayList<>();

        // Maze size
        for (int i = 0; i < 2; i++) {
            int length = size[i];
            final_array.add((byte) (length >> 24));
            final_array.add((byte) (length >> 16));
            final_array.add((byte) (length >> 8));
            final_array.add((byte) (length));
        }

        // Maze start and goal
        for (int i = 0; i < 16; i++)
            final_array.add((byte) 0);

        byte[] temp;
        int i = 0;
        byte two = 0;
        while (i < b.length) {
            temp = new byte[8];
            for (int j = 0; j < 8; j++) {
                if (i + j == b.length) {
                    i = b.length;
                    break;
                }
                byte cell = b[i + j];
                switch (cell) {
                    case 0, 1 -> temp[j] = cell;
                    case 2 -> {
                        two = 1;
                        j--;
                        i++;
                    }
                    case 3, 4 -> {
                        temp[j] = 0;
                        int[] pos = new int[2];
                        while (pos[0] * (size[1]) < i + j - size[1]) pos[0]++;
                        pos[1] = i + j - (pos[0] * (size[1])) - two;

                        int k = cell == 3 ? 8 : 16;
                        for (int m = 0; m < 2; m++) {
                            int length = pos[m];
                            final_array.set(k++, (byte) (length >> 24));
                            final_array.set(k++, (byte) (length >> 16));
                            final_array.set(k++, (byte) (length >> 8));
                            final_array.set(k++, (byte) (length));
                        }
                    }
                }
            }
            final_array.add(ByteToBin(temp));
            i += 8;
        }


        byte[] array = new byte[final_array.size()];
        for (int j = 0; j < final_array.size(); j++) {
            array[j] = final_array.get(j);
        }
        return array;
    }

    private static int[] getMazeSize(byte[] b) {
        int row_amount, col_amount = 0;
        while (b[col_amount] != 2) col_amount++;
        row_amount = (b.length - 1) / (col_amount);
        return new int[]{row_amount, col_amount};
    }

    private static byte ByteToBin(byte[] b) {
        byte bin = 0;
        for (int i = 0; i < b.length; i++) {
            if (b[i] == 1) bin += (byte) Math.pow(2, i);
        }
        return bin;
    }

    @Override
    public void write(int b) throws IOException {}

    /**
     * Compresses the given byte array via {@link #compress(byte[])}}.
     * @param b - Given data.
     */
    public void write(byte[] b) throws IOException {
        for (Byte aByte : compress(b)) out.write(aByte);
    }
}