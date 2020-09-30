/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("./words.txt");
        Palindrome palindrome = new Palindrome();
        OffByOne cc = new OffByOne();
        OffByN cc1 = new OffByN(10);

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, cc1)) {
                System.out.println(word);
            }
        }
    }
}