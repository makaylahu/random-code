import java.util.Scanner;
import java.util.ArrayList;

public class CrosswordMain {
    public static void main(String[] args) {
        Grid grid = new Grid();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Square or Rectangle?");

        boolean valid = false;
        while (!valid) {
            String choice = scanner.nextLine();
            if (choice.equals("square") || choice.equals("Square")) {
                valid = true;
                System.out.println("Width?");
                int width = Integer.parseInt(scanner.nextLine());
                grid = new Grid(width);
            } else if (choice.equals("rectangle") || choice.equals("Rectangle")) {
                valid = true;
                System.out.println("Width?");
                int width = Integer.parseInt(scanner.nextLine());
                System.out.println("Height?");
                int height = Integer.parseInt(scanner.nextLine());
                grid = new Grid(width, height);
            } else {
                System.out.println("Check your spelling");
            }
        }

        //list of words
        ArrayList<String> words = new ArrayList<>();
        System.out.println("First Word?");
        words.add(scanner.nextLine());
        System.out.println("Add to List? T/F");
        boolean finish = false;
        String option = scanner.nextLine();
        if (option.equals("F") || option.equals("f")) {
            finish = true;
        }
        while (!finish) {
            System.out.println("Next Word?");
            words.add(scanner.nextLine());
            System.out.println("Add to List? T/F");
            String answer = scanner.nextLine();
            if (answer.equals("F") || answer.equals("f")) {
                finish = true;
            }
        }
        grid.loadGrid(words);

        System.out.println("Here is your crossword puzzle:");
        System.out.println(grid.toString());
    }
}