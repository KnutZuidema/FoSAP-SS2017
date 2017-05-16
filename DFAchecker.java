import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;

/**
 * Created by Knut on 5/11/2017.
 */
public class DFAchecker {
    public static int[][] expressionChecker(String fileName) throws IOException{
        LinkedList<char[]> expressions= readFromFile(fileName);
        int[][] results = new int[2][expressions.size()];
        Arrays.fill(results[0], -1);
        for(int x = 0; x < expressions.size(); x++) {
            char[] expression = expressions.get(x);
            int floor = 1;
            boolean emergency = false;
            boolean open = false;
            int i = 0;
            for (i = 0; i < expression.length - 1; i++) {
                int word = wordChecker(expression[i], floor, open, emergency);
                if (0 != word) {
                    results[0][x] = i + 1;
                    results[1][x] = word;
                    break;
                }
                if (expression[i] == 'U') {
                    floor++;
                } else if (expression[i] == 'D') {
                    floor--;
                } else if (expression[i] == 'A') {
                    open = true;
                } else if (expression[i] == 'Z') {
                    open = false;
                } else if (expression[i] == 'R') {
                    emergency = !emergency;
                }

            }
        }
        return results;
    }

    private static int wordChecker(char x, int floor, boolean open, boolean emergency) {
        if(x == 'A'){
            if(open){
                return 1;
            }
        }else if(x == 'Z'){
            if(!open){
                return 1;
            }
        }else if(x == 'U'){
            if(floor >= 4 && open){
                return 3;
            }else if(open){
                return 4;
            }else if(floor >= 4){
                return 5;
            }
        }else if(x == 'D'){
            if(floor <= 1 && open){
                return 6;
            }else if(open){
                return 4;
            }else if(floor <= 1){
                return 7;
            }
        }
        return 0;
    }

    private static LinkedList<char[]> readFromFile(String fileName) throws IOException{
        FileReader reader = new FileReader(fileName);
        int x = reader.read();
        LinkedList<char[]> expressions = new LinkedList<>();
        while(x != -1) {
            char[] expression = new char[0];
            while (x != (int) '\n') {
                expression = Arrays.copyOf(expression, expression.length + 1);
                expression[expression.length - 1] = (char) x;
                x = reader.read();
            }
            expressions.addLast(expression);
            x = reader.read();
        }
        reader.close();
        return expressions;
    }

    public static void main(String args[]) throws IOException {
        System.out.println("Dateinamen eingeben: ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.next();
        long timeStart = System.nanoTime();
        int[][] results = expressionChecker(fileName);
        long timeEnd = System.nanoTime();
        float timeNeeded = (timeEnd - timeStart)/1000000000f;
        for (int i = 0; i < results[0].length; i++){
            if(results[0][i] == -1) {
                System.out.printf("Der %d. Ausdruck ist ein korrekter Ausdruck\n", i+1);
            }else{
                System.out.printf("Der %d. Ausdruck hat einen Fehler an der Stelle %d:\n", i+1, results[0][i]);
                if(results[1][i] == 1){
                    System.out.println("\tEs wurde versucht geoeffnete Tueren zu schliessen");
                }else if (results[1][i] == 2){
                    System.out.println("\tEs wurde versucht geschlossene Tueren zu oeffnen");
                }else if (results[1][i] == 3) {
                    System.out.println("\tEs wurde versucht in ein zu hohes Stockwerk mit geoeffneter Tuer zu fahren");
                }else if (results[1][i] == 4) {
                    System.out.println("\tEs wurde versucht den Fahrstuhl mit geoeffneter Tuer zu bewegen");
                }else if (results[1][i] == 5) {
                    System.out.println("\tEs wurde versucht in ein zu hohes Stockwerk zu fahren");
                }else if (results[1][i] == 6) {
                    System.out.println("\tEs wurde versucht in ein zu niedriges Stockwerk mit geoeffneter Tuer zu fahren");
                }else if (results[1][i] == 7) {
                    System.out.println("\tEs wurde versucht in ein zu niedriges Stockwerk zu fahren");
                }
            }
        }
        System.out.printf("Benoetigte Zeit fuer Ueberpruefung: %fs\n", timeNeeded);
    }
}

