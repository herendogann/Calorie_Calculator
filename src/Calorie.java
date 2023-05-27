public class Calorie {

    public static String[][] CalorieTable_Needed(String[][] people_table, int row) {
        String[][] calorie_table = new String[row][4];
        for (int y = 0; y < calorie_table.length; y++) {
            int weight = Integer.parseInt(people_table[y][3]);
            int height = Integer.parseInt(people_table[y][4]);
            int birth_year = Integer.parseInt(people_table[y][5]);
            int age = 2022 - birth_year;

            calorie_table[y][2] = "0";
            calorie_table[y][3] = "0";

            if (people_table[y][2].equals("male")) {
                double NeededCalorieDouble = 66 + (13.75 * weight) + (5 * height) - (6.8 * age);
                int NeededCalorie = (int) Math.round(NeededCalorieDouble);
                calorie_table[y][0] = people_table[y][0];
                calorie_table[y][1] = String.valueOf(NeededCalorie);
            } else if (people_table[y][2].equals("female")) {
                double NeededCalorieDouble = 665 + (9.6 * weight) + (1.7 * height) - (4.7 * age);
                int NeededCalorie = (int) Math.round(NeededCalorieDouble);
                calorie_table[y][0] = people_table[y][0];
                calorie_table[y][1] = String.valueOf(NeededCalorie);
            }
        }
        return calorie_table;
    }

    public static String[][] CalorieTable_Taken(String[][] calorie_table, String[][] food_table, String person_id, String food_id, String food_portion, int food_row, int calorie_row) {
        for (int x = 0; x < calorie_row; x++) {
            for (int f = 0; f < food_row; f++) {
                if (person_id.equals(calorie_table[x][0])) {
                    if (food_id.equals(food_table[f][0])) {
                        int food_calorie = Integer.parseInt(food_table[f][2]);
                        int portion = Integer.parseInt(food_portion);
                        int taken_calorie = food_calorie * portion;
                        Write.write(calorie_table[x][0] + "\thas\ttaken\t" + taken_calorie + "kcal\tfrom\t" + food_table[f][1] + "\n***************\n");
                        int all_taken_calorie = Integer.parseInt(calorie_table[x][2]);
                        all_taken_calorie += taken_calorie;
                        calorie_table[x][2] = String.valueOf(all_taken_calorie);
                    }
                }
            }
        } return calorie_table;
    }

    public static String[][] CalorieTable_Burned(String[][] calorie_table, String[][] sports_table, String person_id, String sport_id, String sport_time, int sports_row, int calorie_row) {
        for (int x = 0; x < calorie_row; x++) {
            for (int s = 0; s < sports_row; s++) {
                if (person_id.equals(calorie_table[x][0])) {
                    if (sport_id.equals(sports_table[s][0])) {
                        int sport_calorie_per_hour = Integer.parseInt(sports_table[s][2]);
                        int time = Integer.parseInt(sport_time);
                        int burned_calorie = sport_calorie_per_hour * time / 60;
                        Write.write(calorie_table[x][0] + "\thas\tburned\t" + burned_calorie + "kcal\tthanks to\t" + sports_table[s][1] + "\n***************\n");
                        int all_burned_calorie = Integer.parseInt(calorie_table[x][3]);
                        all_burned_calorie += burned_calorie;
                        calorie_table[x][3] = String.valueOf(all_burned_calorie);
                    }
                }
            }
        } return calorie_table;
    }
}