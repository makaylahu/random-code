import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;

public class WordSearchMain {
    private static final int numValidWords = 8159;
    private static final ArrayList<String> validWords;

    static {
        try {
            validWords = readFile("words/valid_word_list.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        /*grid = scannerMode();
        Grid grid = randomMode();
        System.out.println("Here is your crossword puzzle:");
        System.out.println(grid.toString());*/

        guiMode();
    }

    private static void guiMode() {
        JFrame frame = new JFrame("Crossword");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon homePage = new ImageIcon("gui-images\\title-page.jpg");
        JLabel home = new JLabel(homePage);
        JButton startButton = new JButton();
        startButton.setBounds(80, 405, 400, 160);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt){
                frame.remove(home);
                frame.add(gamePage(frame));
                frame.pack();
            }
        });
        home.add(startButton);
        frame.add(home);

        frame.pack();
        frame.setVisible(true);
    }

    private static JLabel gamePage(JFrame frame) {
        JLabel gamePage = new JLabel(new ImageIcon("gui-images\\blank.jpg")); //fill out file path
        Grid grid = randomMode();
        System.out.println(grid.toString());

        //character grid
        char[][] charGrid = grid.getChars();
        JLabel gridLabel = new JLabel();
        gridLabel.setLayout(new GridLayout(10, 10));
        gridLabel.setBounds(100, 100, 500, 500);
        for (char[] row : charGrid) {
            for (char letter : row) {
                gridLabel.add(new JLabel(String.valueOf(letter)));
            }
        }
        gamePage.add(gridLabel);

        //list of words
        ArrayList<String> wordsList = grid.getWords();
        String words = "<html>";
        for (String word : wordsList) {
            words += word + "<br/>";
        }
        words += "</html>";
        JLabel wordsLabel = new JLabel(words);
        wordsLabel.setBounds(700, 100, 300, 300);
        gamePage.add(wordsLabel);

        //regenerate button
        JButton newGridButton = new JButton();
        newGridButton.setBounds(700, 500, 100, 100);
        newGridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt){
                frame.remove(gamePage);
                frame.add(gamePage(frame));
                frame.pack();
            }
        });
        gamePage.add(newGridButton);

        return gamePage;
    }

    private static Grid randomMode() {
        int size = 10;
        Grid grid = new Grid(size);

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
        return grid;
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

    private static Grid scannerMode() {
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
        if(!grid.loadGrid(words)) {
            System.out.println("Words are not fitting in the grid.");
        }
        return grid;
    }
}