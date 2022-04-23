package lab5.shell;

import java.util.ArrayDeque;

public class CommandHistory {
        ArrayDeque<String> stack = new ArrayDeque<>();
        int maxSize = 12;

        public CommandHistory() {}
        public CommandHistory(int maxSize) {this.maxSize = maxSize;}

        public void add(Command command) {
            add(command.getCommandName());
        }

        public void add(String string) {
            if (stack.size() == maxSize) {
                stack.removeFirst();
            }
            stack.add(string);
        }

        public ArrayDeque<String> getStack() {
            return stack;
        }

        @Override
        public String toString(){
            String s = "";
            int max = stack.size();
            for (int i = 0; i < max; i++) {
                String s1 = stack.poll();
                s+= s1 + System.lineSeparator();
                stack.addLast(s1);
            }
            return s;
        }
}
