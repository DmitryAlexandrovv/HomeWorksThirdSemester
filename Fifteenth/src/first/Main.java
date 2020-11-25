package first;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\tanke\\IdeaProjects\\untitled\\data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
// создаем буфер размера 48 байтов (!)
        ByteBuffer buf = ByteBuffer.allocate(48);
// читаем из канала в буфер, возвращается реальное количество считанных байтов
        int bytesRead = inChannel.read(buf);
        ArrayList arr = new ArrayList();
        StringBuffer str = new StringBuffer();
        while (bytesRead != -1) {
            buf.flip();
            while(buf.hasRemaining()){
                str.append((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.writeBytes(str.toString().toLowerCase());
        aFile.close();
    }
}
