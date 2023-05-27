import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Read {

    public static String[][] read_command(String fileName) {
        String[][] command_table = new String[100][100];
        int j = 0;
        try {
            Scanner scanner = new Scanner(new FileReader(fileName));
            while (scanner.hasNextLine()) {
                String[] command_array = scanner.nextLine().split("\t");
                if (command_array.length != 1) {
                    for (int i = 0; i < 3; i++) {
                        command_table[j][i] = command_array[i];
                    }
                } else {
                    command_table[j][0] = command_array[0];
                }
                j++;
            }
            return command_table;

        } catch (IOException ioe) {
            System.out.println("There is an error with reading the command text file.\n");
            return null;
        }
    }

    public static String[][] read_text(String fileName) {
        String[][] table = new String[100][100];
        int row = 0;
        try {
            Scanner scanner = new Scanner(new FileReader(fileName));
            while (scanner.hasNextLine()) {
                String[] array = scanner.nextLine().split("\t");
                for (int i = 0; i < array.length; i++) {
                    table[row][i] = array[i];
                }
                row++;
            }
            return table;
        } catch (IOException ioe) {
            System.out.println("There is an error with reading the '" + fileName + "'.\n");
            return null;
        }
    }

    public static int row_counter(String fileName) {
        int row = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while (reader.readLine() != null) {
                row++;
            }
            reader.close();
        } catch (IOException ioe) {
            System.out.println("There is an error with reading the '" + fileName + "'.\n");
        }
        return row;
    }
}
