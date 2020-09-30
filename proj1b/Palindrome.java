public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        int length = word.length();
        LinkedListDeque<Character> wd = new LinkedListDeque<>();
        for (int i = 0; i < length; i++) {
            wd.addLast(word.charAt(i));
        }
        return (Deque<Character>) wd;
    }

    public boolean isPalindrome(String word) {
        int length = word.length();
        int middle = length / 2;
        for (int i = 0; i < middle; i++) {
            if (word.charAt(i) != word.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int length = word.length();
        int middle = length / 2;
        for (int i = 0; i < middle; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(length - i - 1))) {
                return false;
            }
        }
        return true;
    }
}
