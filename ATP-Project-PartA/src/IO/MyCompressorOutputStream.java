package IO;

import algorithms.mazeGenerators.Position;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.zip.Deflater;

public class MyCompressorOutputStream extends OutputStream {

    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {

    }


    public void write(byte[] b) throws IOException {
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
        while (i < b.length){
            temp = new byte[8];
            for (int j = 0; j < 8; j++) {
                if (i + j == b.length){
                    i = b.length;
                    break;
                }
                byte cell = b[i+j];
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
                        while (pos[0] * (size[0]) < i + j - size[0])
                            pos[0]++;
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
        }
        System.out.print("Compressed to:\n[");
        for(byte output : final_array){
            out.write(output);
            System.out.print(output + ", ");
        }
        System.out.println("]");
    }


    private int[] getMazeSize(byte[] b){
        int row_amount, col_amount=0;
        while (b[col_amount] != 2)
            col_amount++;
        row_amount = (b.length - 1) / (col_amount);
       return new int[]{row_amount, col_amount};
    }

    private byte ByteToBin(byte[] b){
        byte bin = 0;
        for (int i = 0; i < b.length; i++) {
            if (b[i] == 1)
                bin += (byte)Math.pow(2, i);
        }
        return bin;
    }
}