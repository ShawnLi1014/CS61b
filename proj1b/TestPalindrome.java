import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    */
    static Palindrome palindrome = new Palindrome();
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome(){
        String test1 = "";
        String test2 = "a";
        String test3 = "noooon";
        String test4 = "abcd";
        String test5 = "abcdefgkkdkakd";
        assertTrue(palindrome.isPalindrome(test1));
        assertTrue(palindrome.isPalindrome(test2));
        assertTrue(palindrome.isPalindrome(test3));
        assertFalse(palindrome.isPalindrome(test4));
        assertFalse(palindrome.isPalindrome(test5));
    }

    CharacterComparator obo = new OffByOne();
    @Test
    public void testOffByOneIsPalindrome() {
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertFalse(palindrome.isPalindrome("noon", obo));
    }
}
