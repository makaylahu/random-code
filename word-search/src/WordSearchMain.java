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

        JLabel home = homePage(frame);
        frame.add(home);

        frame.pack();
        frame.setVisible(true);
    }

    private static JLabel homePage(JFrame frame) {
        ImageIcon homePage = new ImageIcon("gui-images\\title-page.jpg");
        JLabel home = new JLabel(homePage);
        JButton startButton = new JButton();
        startButton.setBounds(110, 425, 400, 160);
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

        return home;
    }

    private static JLabel gamePage(JFrame frame) {
        JLabel gamePage = new JLabel(new ImageIcon("gui-images\\game-screen.jpg")); //fill out file path
        Grid grid = randomMode();
        System.out.println(grid.toString());

        //properties of letters that have been clicked
        ArrayList<JButton> clickedLetters = new ArrayList<>();

        //character grid
        char[][] charGrid = grid.getChars();
        JLabel gridLabel = new JLabel();
        gridLabel.setLayout(new GridLayout(10, 10));
        gridLabel.setBounds(100, 100, 500, 500);
        for (char[] row : charGrid) {
            for (char letter : row) {
                JButton button = new JButton (String.valueOf(letter));
                button.setFont(new Font("Verdana", Font.PLAIN, 16));
                button.setBackground(Color.getHSBColor(210, 25, 90));
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!button.isOpaque()) {
                            button.setOpaque(true);
                            clickedLetters.add(button);
                        } else {
                            button.setOpaque(false);
                            button.repaint();
                            clickedLetters.remove(button);
                        }
                    }
                });
                gridLabel.add(button);
            }
        }
        gamePage.add(gridLabel);

        //list of words
        ArrayList<String> wordsList = grid.getWords();
        String words = "<html>";
        for (String word : wordsList) {
            words += word.toUpperCase() + "<br/>";
        }
        words += "</html>";
        JLabel wordsLabel = new JLabel(words);
        wordsLabel.setBounds(710, 85, 300, 300);
        wordsLabel.setForeground(Color.white);
        gamePage.add(wordsLabel);

        //undo highlight button
        JButton undoButton = new JButton();
        undoButton.setBounds(888, 445, 80, 80);
        undoButton.setOpaque(false);
        undoButton.setBorderPainted(false);
        undoButton.setContentAreaFilled(false);
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (!clickedLetters.isEmpty()) {
                    JButton b = clickedLetters.get(0);
                    b.setOpaque(false);
                    b.repaint();
                    clickedLetters.remove(b);
                }
            }
        });
        gamePage.add(undoButton);

        //regenerate button
        JButton newGridButton = new JButton();
        newGridButton.setBounds(650, 590, 100, 100);
        newGridButton.setOpaque(false);
        newGridButton.setContentAreaFilled(false);
        newGridButton.setBorderPainted(false);
        newGridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt){
                frame.remove(gamePage);
                frame.add(gamePage(frame));
                frame.pack();
            }
        });
        gamePage.add(newGridButton);

        //home button
        JButton homeButton = new JButton();
        homeButton.setBounds(835, 595, 100, 100);
        homeButton.setOpaque(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setBorderPainted(false);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(gamePage);
                frame.add(homePage(frame));
                frame.pack();
            }
        });
        gamePage.add(homeButton);

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