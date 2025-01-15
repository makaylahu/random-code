import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;

public class WordSearchMain {
    private static Grid grid = new Grid();
    private static final int numValidWords = 8159;

    public static void main(String[] args) throws FileNotFoundException {
        //scannerMode();
        randomMode();
        showFrame();

        System.out.println("Here is your crossword puzzle:");
        System.out.println(grid.toString());
    }

    private static void showFrame() {
        JFrame frame = new JFrame("Crossword");
        frame.setSize(1024, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon homePage = new ImageIcon("gui-images\\title-page.jpg");
        frame.add(new JLabel(homePage));

        frame.pack();
        frame.setVisible(true);
    }

    private static void randomMode() throws FileNotFoundException {
        int size = 10;
        grid = new Grid(size);

        ArrayList<String> validWords = readFile("words/valid_word_list.txt");
        int minNumWords = 10;
        boolean success = false;
        while (!success) {
            grid.clearGrid();
            int randNumWords = minNumWords + (int) (Math.random() * 4);
            int[] indices = new int[randNumWords];
            for (int rep = 0; rep < randNumWords; rep++) {
                int randNum = (int) (Math.random() * numValidWords);
                while (inArray(indices, randNum)) {
                    randNum = (int) (Math.random() * numValidWords);
                }
                indices[rep] = randNum;
            }
            ArrayList<String> words = new ArrayList<>();
            for (int index : indices) {
                words.add(validWords.get(index));
            }
            success = grid.loadGrid(words);
            //System.out.println(success + " " + randNumWords);
        }
    }

    private static ArrayList<String> readFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        ArrayList<String> validWords = new ArrayList<>();

        while (scanner.hasNextLine()) {
            validWords.add(scanner.nextLine());
        }
        scanner.close();
        return validWords;
    }

    private static boolean inArray(int[] arr, int num) {
        for (int arrNum : arr) {
            if (num == arrNum)
                return true;
        }
        return false;
    }

    private static void scannerMode() {
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
        if(!grid.loadGrid(words)) {
            System.out.println("Words are not fitting in the grid.");
        }
    }
}