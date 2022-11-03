package hw4;

import java.util.Random;
import java.util.Scanner;

public class class1 {

    static final char DOT_HUMAN = 'X'; // фишка игрока - человек
    static final char DOT_AI = '0'; // фишка игрока - компьютер
    static final char DOT_EMPTY = '*'; // признак пустого поля
    static final Scanner scanner = new Scanner(System.in); // вспомогательный класс для ввода данных
    static final Random random = new Random(); // вспомогательный класс для генерации случайных чисел
    static char[][] field; // двумерный массив, хранит текущее состояние игрового поля
    static int fieldSizeX; // размерность игрового поля
    static int fieldSizeY; // размерность игрового поля

    /**
     * Инициализация объектов игры
     */
    private static void initialize() {
        // устанавливаем размерность игрового поля
        fieldSizeX = 3;
        fieldSizeY = 3;
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                // проинициализируем все элементы массива DOT_EMPTY
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * отрисовка игрового поля
     */
    static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            /* if (i % 2 == 0){
                System.out.print('-');
            }
            else {
                int a = i / 2 + 1;
                System.out.print(a);
            } */
            System.out.print(i % 2 == 0 ? "-" : i / 2 + 1);
        }
        System.out.println();

        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print(x + 1 + "|");
            for (int y = 0; y < fieldSizeY; y++) {
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }
        for (int i = 0; i <= fieldSizeX * 2 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Обработка хода игрока (человек)
     */
    static void humanTurn(){
        int x, y;

        do {
            System.out.println("Введите координаты хода X и Y \n(от 1 до 3) через пробел >>> ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!(isCallValid(x, y) && isCellEmpty(x, y)));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Обраотка хода игрока (компьютер)
     */
    static void aiTurn (){
        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    /**
     * Проверка, является ли ячейка пустой (DOT_EMPTY)
     * @param x
     * @param y
     * @return
     */
    static boolean isCellEmpty(int x, int y){
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка корректности ввода (координаты хода не должны превышат размерность массива,
     * описывающего игровое поле
     * @param x
     * @param y
     * @return
     */
    static boolean isCallValid(int x, int y){
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }
    /**
     * Проверка победы игрока (человек/компьютер)
     * @param c
     * @return
     */
    //TODO: Домашняя работа, переработаь метод checkWin (уникальный метод, использовать циклы for)
    static boolean checkWin(char c){
        // проверка по трем горизонтялям
        if (field[0][0] == c && field[0][1] == c && field[0][2] == c) return true;
        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) return true;
        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) return true;

        // проверка по трем вертикалям
        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) return true;
        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) return true;
        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) return true;

        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
        if (field[0][2] == c && field[1][1] == c && field[2][0] == c) return true;

        return false;
    }

    /**
     * Проверка на ничью (все поле заполнено фишками игрока и компьютера)
     * @param
     * @return
     */
    static boolean checkDraw() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y)) return false;

            }
        }
            return true;
    }

    /**
     * Метод проверки состояния игры
     * @param dot - игровая фишка
     * @param s - победный слоган
     * @return
     */
    static boolean gameChecks(char dot, String s){
        if (checkWin(dot)){
            System.out.println(s);
            return true; // завершение игры
        }
        if (checkDraw()){
            System.out.println("Ничья!");
            return true; // завершение игры
        }
        return false; // продолжение игры
    }

    public static void main (String[]args){
        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn(); // обработка хода игрока (человек)
                printField();
                if (gameChecks(DOT_HUMAN, "Вы победили!"))
                    break;
                aiTurn(); // обработка хода игрока (компьютер)
                printField();
                if (gameChecks(DOT_AI, "Победил компьютер!"))
                    break;
                }
            System.out.println("Хотите сыграть еще раз? (Y - да)");
            if (!scanner.next().equalsIgnoreCase("Y"))
            break;
            }
        }
}


