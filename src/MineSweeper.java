import java.util.Scanner;
public class MineSweeper {
    private char [][] grid;
    private char [][] mines;
    private int rows;
    private int cols;
    private int totalmines;

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = '-';
                mines[i][j] = '0';
            }
        }
    }

    private void generateMines(){
    int placedMines = 0;
    while (placedMines < totalmines){
        int row = (int) (Math.random() * rows);
        int col = (int) (Math.random() * cols);
        if (mines[row][col] != '*'){
            mines[row][col] = '*';
            placedMines++;
        }

    }

    }

    public MineSweeper (int rows , int cols){
        this.rows = rows;
        this.cols = cols;
        this.totalmines = (rows * cols) / 4;
        this.mines = new char[rows][cols];
        this.grid = new char[rows][cols];
        initializeGrid() ;
        generateMines();

    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private void control (int row , int col){
        if(!isValid(row,col) || grid[row][col] != '-')
            return;

        if (mines[row][col] == '*') {
            grid[row][col] = '*';
            System.out.print("Mayına bastınız! Kaybettiniz.");
            return;
        }
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isValid(row + i, col + j) && mines[row + i][col + j] == '*') {
                    count++;
                }
            }
        }

        if (count > 0) {
            grid[row][col] = (char) (count + '0');
        } else {
            grid[row][col] = ' ';

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    control(row + i, col + j);
                }
            }
        }

    }

    public void play (){

        Scanner scanner = new Scanner(System.in);

        while(true) {
           printGrid();
            System.out.print("Satır numarasını girin (0'dan başlayarak): ");
            int row = scanner.nextInt();
            System.out.print("Sütun numarasını girin (0'dan başlayarak): ");
            int col = scanner.nextInt();

            if (!isValid(row, col)) {
                System.out.println("Geçersiz bir nokta girdiniz. Lütfen tekrar deneyin.");
                continue;
        }
            control(row, col);

            boolean allRevealed = true;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '-' && mines[i][j] != '*') {
                        allRevealed = false;
                        break;
                    }
                }
            }
            if (allRevealed) {
                System.out.println("Tebrikler! Tüm mayınsız bölgeleri buldunuz. Oyunu kazandınız!");
                break;
            }
        }
        scanner.close();
    }
    private void printGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Satır sayısını girin: ");
        int rows = input.nextInt();
        System.out.print("Sütun sayısını girin: ");
        int cols = input.nextInt();

        MineSweeper game = new MineSweeper(rows, cols);
        game.play();
        input.close();
    }
}
