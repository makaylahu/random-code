import java.util.ArrayList;

public class Grid {

    private char[][] grid;
    private ArrayList<String> words;

    //directions
    public final int N = 0;
    public final int NE = 1;
    public final int E = 2;
    public final int SE = 3;
    public final int S = 4;
    public final int SW = 5;
    public final int W = 6;
    public final int NW = 7;

    //default: length = 5
    public Grid() {
        this.grid = new char[5][5];
    }

    public Grid(int width, int height) {
        this.grid = new char[height][width];
    }

    public Grid(int width, int height, ArrayList<String> words) {
        this.grid = new char[height][width];
        this.loadGrid(words);
    }

    // square grids
    public Grid(int length) {
        this.grid = new char[length][length];
    }

    public Grid(int length, ArrayList<String> words) {
        this.grid = new char[length][length];
        this.loadGrid(words);
    }

    public boolean loadGrid(ArrayList<String> words) {
        this.words = words;
        for (String word : words) {
            word = word.toUpperCase();
            if (!tryAddWord(word)) {
                return false;
            }
        }

        //fill in rest of grid
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == '\u0000') {
                    int randInt = (int)(Math.random() * 26);
                    char randChar = (char)('A' + randInt);
                    grid[y][x] = randChar;
                }
            }
        }
        return true;
    }

    private boolean tryAddWord(String word) {
        // for placing first letter of word
        int max = grid.length * grid[0].length;
        boolean placed = false;
        int startPosition = (int)(Math.random() * max);
        int position = startPosition;

        //word length
        int length = word.length();

        while (!placed && position != startPosition + max) {
            int y = (position % max) / grid[0].length;
            int x = position % grid[0].length;

            if (grid[y][x] == '\u0000' || grid[y][x] == word.charAt(0)) {
                int randDir = (int)(Math.random() * 8); // 8 possible directions
                int iter = 0;
                while (!placed && iter < 8) {
                    int dir = (randDir + iter) % 8;
                    placed = testWordDir(word, dir, x, y);
                    if (placed) {
                        addWord(word, dir, x, y);
                    }
                    iter++;
                }
            }
            position++;
        }
        return placed;
    }

    private boolean testWordDir (String word, int dir, int x, int y) {
        if (word.length() == 0)
            return true;

        //word does not fit on board
        if (x < 0 || y < 0 || x >= grid[0].length || y >= grid.length)
            return false;
        //spot filled with different letter
        char gridChar = grid[y][x];
        if (gridChar != '\u0000' && gridChar != word.charAt(0))
            return false;

        if (dir == N) {
            return testWordDir(word.substring(1), dir, x, y-1);
        } else if (dir == NE) {
            return testWordDir(word.substring(1), dir, x+1, y-1);
        } else if (dir == E) {
            return testWordDir(word.substring(1), dir, x+1, y);
        } else if (dir == SE) {
            return testWordDir(word.substring(1), dir, x+1, y+1);
        } else if (dir == S) {
            return testWordDir(word.substring(1), dir, x, y+1);
        } else if (dir == SW) {
            return testWordDir(word.substring(1), dir, x-1, y+1);
        } else if (dir == W) {
            return testWordDir(word.substring(1), dir, x-1, y);
        } else {
            return testWordDir(word.substring(1), dir, x-1, y-1);
        }
    }

    private void addWord (String word, int dir, int x, int y) {
        if (word.length() == 0)
            return;

        this.grid[y][x] = word.charAt(0);

        if (dir == N) {
            addWord (word.substring(1), dir, x, y-1);
        } else if (dir == NE) {
            addWord (word.substring(1), dir, x+1, y-1);
        } else if (dir == E) {
            addWord (word.substring(1), dir, x+1, y);
        } else if (dir == SE) {
            addWord (word.substring(1), dir, x+1, y+1);
        } else if (dir == S) {
            addWord (word.substring(1), dir, x, y+1);
        } else if (dir == SW) {
            addWord (word.substring(1), dir, x-1, y+1);
        } else if (dir == W) {
            addWord (word.substring(1), dir, x-1, y);
        } else {
            addWord (word.substring(1), dir, x-1, y-1);
        }
    }

    public void clearGrid() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                grid[y][x] = '\u0000';
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                result = result + grid[y][x] + " ";
            }
            result += "\n";
        }
        result += "\n";
        for (String word : this.words) {
            result += word;
            result += "\n";
        }

        return result;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public char[][] getChars() {
        return grid;
    }
}