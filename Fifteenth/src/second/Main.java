package second;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Main {
    public static Student readStudent(){
        try(FileChannel fileChannel = (FileChannel) Files.newByteChannel(Paths.get("student.txt"),
                StandardOpenOption.READ)) {

            ByteBuffer nameLengthBuffer = ByteBuffer.allocate(4);
            fileChannel.read(nameLengthBuffer);
            nameLengthBuffer.flip();
            int nameLength = nameLengthBuffer.getInt();

            ByteBuffer body = ByteBuffer.allocate(nameLength + 4 + 8);
            fileChannel.read(body);
            byte[] bytes = new byte[nameLength];
            body.flip();
            body.get(bytes);
            String name = new String(bytes);

            char gender = body.getChar();
            long time = body.getLong();

            return new Student(name, gender, new Date(time));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeStudent(Student student){
        try(FileChannel fileChannel = (FileChannel) Files.newByteChannel(Paths.get("student.txt"),
                StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {

            //(4)nameLength, (length)name, (2)gender, (8)dateOfBirth
            int nameLength = student.getFullName().length();
            int length = 4 + nameLength + 4 + 8;
            ByteBuffer buffer = ByteBuffer.allocate(length).putInt(nameLength).put(student.getFullName().getBytes()).putChar(student.getGender()).putLong(student.getDateOfBirth().getTime());

            buffer.rewind();
            fileChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Student student = new Student("Dmitry Alexandrov", 'M', new Date(2001, 1, 13));
        System.out.println(student);
        writeStudent(student);
        System.out.println(readStudent());
    }
}
