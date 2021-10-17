/*
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class JsonParser {

    class Node {
        String key, value;
        boolean array;
        List<Node> list;
    }

    class Token {

    }

    //{"asdf":"adf","asdf":[{},{},{}]}
    public Node parse(String str) {
        Stack<Object> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            List<Node> curr = null;
            char ch = str.charAt(i);
            switch (ch) {
                case '}':
                    while (!stack.isEmpty()) {
                        Object top = stack.peek();
                        if (top instanceof Character) {

                        }
                    }
                    break;
                case ']':
                    break;
                case '{':
                    Node node = new Node();
                    break;
                case '[':
                    curr = new ArrayList<>();
                    break;
                default:
                    stack.push(ch);
            }
        }
        return parse(str, 0);
    }

    private void parse(String str, int idx) {
        if (idx >= str.length()) {
            return;
        }
        char ch = str.charAt(idx);
        if (ch == '{') {

        }
    }
}
*/
