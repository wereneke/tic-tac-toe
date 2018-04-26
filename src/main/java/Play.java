import java.util.Scanner;

public class Play {

    Player player0;
    Player player1;
    char[] usedChars = new char[2];
    Board board;
    Game game;

    Scanner scan = new Scanner(System.in);

    public Play() {
        player0 = createPlayer(0);
        player1 = createPlayer(1);
        board = createBoard();
        game = new Game(player0, player1, board);
    }

    private Player createPlayer(int playerNumb) {
        System.out.println(String.format("what is your name, %d player?", playerNumb));
        String name = scan.nextLine();
        System.out.println("what sign do you want to use?");
        String answer = scan.nextLine();
        while (!isProperSign(answer)) {
            System.out.println("use one sign which is not used yet");
            answer = scan.nextLine();
        }
        char sign = answer.charAt(0);

        return new Player(name, sign);
    }

    private boolean isProperSign(String answer) {
        if (answer.length()!=1) return false;
        for (char c: usedChars) {
            if (answer.charAt(0)==c) {
                System.out.println("used chars");
                for(char ch: usedChars) {
                    System.out.println(ch);
                }
                return false;
            }
        }
        return true;
    }

    private boolean isProperSize(int size) {
        return size>=3;
    }

    private Board createBoard() {
        System.out.println("what size board should be?");
        int size = scan.nextInt();
        while (!isProperSize(size)) {
            System.out.println("size hast to be integer greater or equal 3");
            size = scan.nextInt();
        }
        return new Board(size);
    }

    public static void main(String[] args) {
        Play play = new Play();
        play.game.play();
    }
}
