package IO;

import java.io.*;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) { //TODO check if even needed
    }


    @Override
    public void write(byte[] b) { //TODO: 255 limit
        if (b.length == 0) return;

        byte singular = 0;
        int count = 0;

        for (int i = 1; i < b.length; i++) {
            if (singular == b[i] && i != b.length - 1) count++;
            else {
                write(count);

                if (singular == 0) singular = 1;
                else singular = 0;
                count = 1;
            }
        }
    }
}