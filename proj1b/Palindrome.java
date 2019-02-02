public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        Deque<Character> d = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word){
        Deque d = wordToDeque(word);
        return isPalindromeDeque(d);
    }

    private boolean isPalindromeDeque(Deque d) {
        if(d.size() == 0 || d.size() == 1) {
            return true;
        }
        if(d.size() == 2) {
            return d.removeFirst() == d.removeFirst();
        }
        if(d.removeFirst() == d.removeLast()) {
            return isPalindromeDeque(d);
        }
        return false;

    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque d = wordToDeque(word);
        return isPalindromeDeque(d, cc);
    }

    private boolean isPalindromeDeque(Deque d, CharacterComparator cc) {
        if(d.size() == 0 || d.size() == 1) {
            return true;
        }
        if(d.size() == 2) {
            return cc.equalChars((char)d.removeFirst(), (char)d.removeLast());
        }
        if(cc.equalChars((char)d.removeFirst(), (char)d.removeLast())) {
            return isPalindromeDeque(d, cc);
        }
        return false;

    }
}
