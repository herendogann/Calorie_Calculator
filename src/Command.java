import java.io.IOException;
import java.util.ArrayList;

public class Command extends Calorie{

    public static void command(String fileName) {

        String[][] people_table = Read.read_text("people.txt");
        int people_row = Read.row_counter("people.txt");

        String[][] food_table = Read.read_text("food.txt");
        int food_row = Read.row_counter("food.txt");

        String[][] sports_table = Read.read_text("sport.txt");
        int sports_row = Read.row_counter("sport.txt");

        String[][] command_table = Read.read_command(fileName);
        int command_row = Read.row_counter(fileName);

        String[][] calorie_table = CalorieTable_Needed(people_table, people_row);


        for (int y = 0; y < command_row; y++)
            if (command_table[y][1] != null) {
                if (command_table[y][1].startsWith("1")) {
                    String person_id = command_table[y][0];
                    String food_id = command_table[y][1];
                    String portion = command_table[y][2];
                    calorie_table = CalorieTable_Taken(calorie_table, food_table, person_id, food_id, portion, food_row, people_row);

                } else if (command_table[y][1].startsWith("2")) {
                    String person_id = command_table[y][0];
                    String sport_id = command_table[y][1];
                    String time = command_table[y][2];
                    calorie_table = CalorieTable_Burned(calorie_table, sports_table, person_id, sport_id, time, sports_row, people_row);
                }
            } else if (command_table[y][1] == null) {
                if (command_table[y][0].startsWith("print(")) {
                    for (int p = 0; p < people_row; p++) {
                        if (command_table[y][0].equals("print(" + calorie_table[p][0] + ")")) {
                            int age = 2022 - Integer.parseInt(people_table[p][5]);
                            int need = Integer.parseInt(calorie_table[p][1]);
                            int taken = Integer.parseInt(calorie_table[p][2]);
                            int burned = Integer.parseInt(calorie_table[p][3]);
                            int diff = taken - burned - need;
                            Write.write(people_table[p][1] + "\t" + age + "\t" + calorie_table[p][1] + "kcal\t" + calorie_table[p][2] + "kcal\t" + calorie_table[p][3] + "kcal\t" + diff + "kcal\n***************\n");
                        }
                    }
                } else if (command_table[y][0].equals("printList")) {
                    printList(calorie_table, command_table, people_table, command_row, people_row);

                } else if (command_table[y][0].equals("printWarn")) {
                    printWarn(calorie_table, command_table, people_table, command_row, people_row);
                }
            }
    }

    public static void printList(String[][] calorie_table, String[][] command_table, String[][] people_table, int command_row, int people_row) {
        ArrayList<String> printList_array = new ArrayList<>();
        for (int l = 0; l < command_row; l++) {
            if (command_table[l][0].equals("printList"))
                for (int a = 0; a < l; a++)
                    if (printList_array.contains(command_table[a][0]) == false)
                        printList_array.add(command_table[a][0]);
        }
        String[] printList_id = new String[printList_array.size()];
        for (int pl = 0; pl < printList_id.length; pl++) {
            printList_id[pl] = printList_array.get(pl);
            for (int p = 0; p < people_row; p++) {
                if (printList_id[pl].equals(calorie_table[p][0])) {
                    int age = 2022 - Integer.parseInt(people_table[p][5]);
                    int need = Integer.parseInt(calorie_table[p][1]);
                    int taken = Integer.parseInt(calorie_table[p][2]);
                    int burned = Integer.parseInt(calorie_table[p][3]);
                    int diff = taken - burned - need;
                    String result;
                    if (diff > 0) {result = ("+" + String.valueOf(diff));}
                    else {result = String.valueOf(diff);}
                    if (taken != 0 || burned != 0)
                        Write.write(people_table[p][1] + "\t" + age + "\t" + calorie_table[p][1] + "kcal\t" + calorie_table[p][2] + "kcal\t" + calorie_table[p][3] + "kcal\t" + result + "kcal\n");

                }
            }
        }Write.write("***************\n");
    }
    public static void printWarn(String[][] calorie_table, String[][] command_table, String[][] people_table, int command_row, int people_row) {
            int warn_controller = 0;
            ArrayList<String> printWarn_array = new ArrayList<>();
            for (int l = 0; l < command_row; l++) {
                if (command_table[l][0].equals("printList"))
                    for (int a = 0; a < l; a++)
                        if (printWarn_array.contains(command_table[a][0]) == false)
                            printWarn_array.add(command_table[a][0]);
            }
            String[] printWarn_id = new String[printWarn_array.size()];
            for (int pl = 0; pl < printWarn_id.length; pl++) {
                for (int p = 0; p < people_row; p++) {
                    printWarn_id[pl] = printWarn_array.get(pl);
                    if (printWarn_id[pl].equals(calorie_table[p][0])) {
                        int age = 2022 - Integer.parseInt(people_table[p][5]);
                        int need = Integer.parseInt(calorie_table[p][1]);
                        int taken = Integer.parseInt(calorie_table[p][2]);
                        int burned = Integer.parseInt(calorie_table[p][3]);
                        int diff = taken - burned - need;
                        if (diff > 0) {
                            Write.write(people_table[p][1] + "\t" + age + "\t" + calorie_table[p][1] + "kcal\t" + calorie_table[p][2] + "kcal\t" + calorie_table[p][3] + "kcal\t+" + diff + "kcal\n");
                            warn_controller++;
                        }
                    }
                }
            }
            if (warn_controller == 0) {
                Write.write("There\tis\tno\tsuch\tperson.\n");
            } Write.write("***************\n");
        }
    }

