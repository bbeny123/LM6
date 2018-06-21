package pl.beny.lm6.logic;

import java.util.Arrays;
import java.util.List;

public class Parser {

    private int i = 0;
    private List<String> s;
    private List<String> numbers = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
    private List<String> operators = Arrays.asList("*", ":", "+", "-", "^");

    public Parser(String s) {
        this.s = Arrays.asList(s.split(""));
    }

    public boolean parse() throws Exception {
        do {
            parseW();
            if (!s.get(i++).equals(";")) incorrect();
        } while (i != s.size() && inFirstW());
        return i == s.size();
    }

    private void parseW() throws Exception {
        do {
            if (inFirstL()) {
                parseL();
                if (s.get(i).equals(".")) {
                    nextI();
                    parseL();
                }
            } else if (s.get(i).equals("(")) {
                nextI();
                parseW();
                if (!s.get(i).equals(")")) {
                    System.out.println(s.get(i));
                    incorrect();
                }
                nextI();
            }
        } while (inFirstO());
    }

    private void parseL() throws Exception {
        do {
            if (!numbers.contains(s.get(i))) incorrect();
            nextI();
        } while (inFirstL());
    }

    private void parseO() throws Exception {
        if (!operators.contains(s.get(i))) incorrect();
        nextI();
    }

    private boolean inFirstW() {
        return numbers.contains(s.get(i)) || s.get(i).equals("(");
    }

    private boolean inFirstL() {
        return numbers.contains(s.get(i));
    }

    private boolean inFirstO() throws Exception {
        if (operators.contains(s.get(i))) {
            parseO();
            return true;
        }
        return false;
    }

    private void nextI() throws Exception {
        if (++i >= s.size()) incorrect();
    }

    private void incorrect() throws Exception {
        throw new Exception("incorrect");
    }

}
