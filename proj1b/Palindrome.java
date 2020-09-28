public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        int length = word.length();
        LinkedListDeque<Character> wd = new LinkedListDeque<>();
        for(int i = 0; i<length;i++){
            wd.addLast(word.charAt(i));
        }
        return wd;
    }
}
