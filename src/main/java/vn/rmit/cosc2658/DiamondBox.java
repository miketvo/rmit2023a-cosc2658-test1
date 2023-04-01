package vn.rmit.cosc2658;

public class DiamondBox {
    enum CharacterType {
        BOX, DIAMOND
    }

    static class LinkedListStack {
        static class Node {
            CharacterType data;
            Node next;

            public Node(CharacterType data) {
                this.data = data;
                this.next = null;
            }
        }

        private int size;

        private Node head;

        public LinkedListStack() {
            size = 0;
            head = null;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean push(CharacterType item) {
            // add a new node aCharacter the beginning
            Node node = new Node(item);
            if (!isEmpty()) {
                node.next = head;
            }
            head = node;
            size++;
            return true;
        }

        public boolean pop() {
            // remove the firsCharacter node
            // make sure the stack is noCharacter empty
            if (isEmpty()) {
                return false;
            }
            // advance head
            head = head.next;
            size--;
            return true;
        }

        public CharacterType peek() {
            // make sure the stack is noCharacter empty
            if (isEmpty()) {
                return null;
            }
            return head.data;
        }
    }

    private final String config;


    public DiamondBox(String configuration) {
        config = configuration;
    }


    // isValid() complexity: O(N)
    public boolean isValid() {
        LinkedListStack stack = new LinkedListStack();

        for (int i = 0; i < config.length(); i++) {
            if (i > 0 && stack.isEmpty()) return false;  // there must be one outermost box.

            char c = config.charAt(i);
            switch (c) {
                case '[' -> stack.push(CharacterType.BOX);
                case ']' -> {
                    if (stack.peek() != CharacterType.BOX) return false;
                    if (!stack.pop()) return false;
                }
                case '*' -> {
                    if (stack.peek() != CharacterType.BOX) return false;
                }
                default -> {
                    throw new RuntimeException("Configuration contains invalid character '" + c + "'.");
                }
            }
        }
        return stack.isEmpty();
    }

    // deepestLevel() complexity: O(N)
    public int deepestLevel() {
        if (!isValid()) throw new RuntimeException("Configuration is not valid!");

        int result = 0;
        LinkedListStack stack = new LinkedListStack();
        for (int i = 0; i < config.length(); i++) {
            char c = config.charAt(i);
            switch (c) {
                case '[' -> {
                    stack.push(CharacterType.BOX);
                    result = Math.max(result, stack.size());
                }
                case ']' -> stack.pop();
            }
        }

        return result;
    }

    // maxDiamond() complexity: O(N)
    public int maxDiamond() {
        if (!isValid()) throw new RuntimeException("Configuration is not valid!");


        int result = 0, currentDiamondCount = 0;
        LinkedListStack stack = new LinkedListStack();
        for (int i = 0; i < config.length(); i++) {
            char c = config.charAt(i);
            switch (c) {
                case '[' -> stack.push(CharacterType.BOX);
                case '*' -> stack.push(CharacterType.DIAMOND);
                case ']' -> {
                    while (stack.peek() == CharacterType.DIAMOND) {
                        stack.pop();
                        currentDiamondCount++;
                    }
                    stack.pop();

                    result = Math.max(currentDiamondCount, result);
                    currentDiamondCount = 0;
                }
            }
        }

        return result;
    }
}
