package com.banary.nio.buffer;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description
 * @Author eden
 * @Date 2018/6/1 下午5:49
 */
public class ByteBufferMain {

    public static void main(String[] args) throws IOException {
        demo();
    }

    public static void demo() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/xiyongchun/channel.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int count = channel.read(buffer);
        while (count != -1){
            buffer.flip();
            if(buffer.hasRemaining()){
                System.out.println(String.valueOf(buffer.get()));
            }
            buffer.clear();
            count = channel.read(buffer);
        }
        randomAccessFile.close();
    }
}
