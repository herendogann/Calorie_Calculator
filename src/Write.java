import java.io.*;

public class Write {
    public static void write(String output) {
        FileWriter writer = null;
        try {
                writer  = new FileWriter("monitoring.txt",true);
                writer.write(output);

        } catch (IOException ex) {
            System.out.println("There was an error opening the file.");
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    System.out.println("There was an error closing the file.");
                }
            }
        }
    }
}
