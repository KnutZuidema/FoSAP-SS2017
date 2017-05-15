/**
 * Created by Knut on 5/11/2017.
 */
public class DFAchecker {
    public int expressionChecker(String x){
        char[] expression = x.toCharArray();
        int floor = 1;
        boolean emergency = false;
        for(int i = 0; i < expression.length; i++) {
            if (expression[i] == 'U') {
                floor++;
            } else if (expression[i] == 'D') {
                floor--;
            }
            if (!wordChecker(expression[i], expression[i + 1], floor, emergency)){
                return i;
            }
            if(!emergency && expression[i] == 'R'){
                emergency = true;
            }
        }
        return -1;
    }

    public boolean wordChecker(char x, char y, int floor, boolean emergency) {
        if (x == 'A') {
            if (emergency) {
                if (y == 'Z') {
                    return true;
                }
            } else if (y == 'Z' || y == 'R') {
                return true;
            }
        }
        if (x == 'Z') {
            if (emergency) {
                if (y == 'A' || (y == 'D' && floor > 1) || (y == 'U' && floor < 4)) {
                    return true;
                }
            } else if (y == 'A' || (y == 'D' && floor > 1) || (y == 'U' && floor < 4) || y == 'R') {
                return true;
            }
        }
        if (x == 'D') {
            if(emergency){
                if (y == 'A' || (y == 'D' && floor > 1) || (y == 'U' && floor < 4)) {
                    return true;
                }
            }else if(y == 'A' || (y == 'D' && floor > 1) || (y == 'U' && floor < 4) || y == 'R'){
                return true;
            }
        }
        return false;
    }
}

