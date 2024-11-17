// Yash Gupta
// 500125397 
class NaiveStringMatching {
    public static void search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                System.out.println("Pattern found at index " + i);
            }
        }
    }
}

class RabinKarp {
    public static void search(String text, String pattern, int prime) {
        int n = text.length();
        int m = pattern.length();
        int h = 1;
        int d = 256;  // Number of characters in the input alphabet
        int pHash = 0;
        int tHash = 0;

        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % prime;
        }

        for (int i = 0; i < m; i++) {
            pHash = (d * pHash + pattern.charAt(i)) % prime;
            tHash = (d * tHash + text.charAt(i)) % prime;
        }

        for (int i = 0; i <= n - m; i++) {
            if (pHash == tHash) {
                int j;
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }
                if (j == m) {
                    System.out.println("Pattern found at index " + i);
                }
            }
            if (i < n - m) {
                tHash = (d * (tHash - text.charAt(i) * h) + text.charAt(i + m)) % prime;
                if (tHash < 0) {
                    tHash += prime;
                }
            }
        }
    }
}
class KMP {
    public static void search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] lps = computeLPSArray(pattern, m);
        int i = 0, j = 0;

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            if (j == m) {
                System.out.println("Pattern found at index " + (i - j));
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    private static int[] computeLPSArray(String pattern, int m) {
        int[] lps = new int[m];
        int len = 0, i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
public class String_matching_problem {
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        System.out.println("Naive Algorithm:");
        long startNaive = System.nanoTime();
        NaiveStringMatching.search(text, pattern);
        long endNaive = System.nanoTime();
        System.out.println("Naive Time: " + (endNaive - startNaive) / 1e6 + " ms");

        System.out.println("\nRabin-Karp Algorithm:");
        long startRabinKarp = System.nanoTime();
        RabinKarp.search(text, pattern, 101);
        long endRabinKarp = System.nanoTime();
        System.out.println("Rabin-Karp Time: " + (endRabinKarp - startRabinKarp) / 1e6 + " ms");

        System.out.println("\nKMP Algorithm:");
        long startKMP = System.nanoTime();
        KMP.search(text, pattern);
        long endKMP = System.nanoTime();
        System.out.println("KMP Time: " + (endKMP - startKMP) / 1e6 + " ms");
    }
}
