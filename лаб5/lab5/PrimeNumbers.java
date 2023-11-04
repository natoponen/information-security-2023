import java.util.Random;

public class PrimeNumbers {
    public static void main(String[] args) {

    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public int modPow(int a, int b, int c) {
        int res = 1;
        for (int i = 0; i < b; i++) {
            res *= a;
            res %= c;
        }
        return res % c;
    }

    private String fermat(int n) {
        if (n % 2 == 0 && n < 5) {
            throw new RuntimeException("n must uneven and be greater or equal than 5");
        }

        int a = getRandomInt(2, n - 2);
        int r = modPow(a, n - 1, n);

        if (r == 1) {
            return String.format("%s is probably prime", n);
        } else {
            return String.format("%s is probably composite", n);
        }
    }

    private int jacobi(int n, int a) {
        if (n % 2 == 0 || n < 3 || a < 0 || a >= n) {
            throw new RuntimeException("n must be uneven and more than 2, " +
                "and a must be positive and less than n");
        }

        int g = 1;
        int a1;
        int s = 0;

        do {
            if (a == 0) {
                return 0;
            }
            if (a == 1) {
                return g;
            }

            int k = 0;
            a1 = a;
            while (a1 % 2 == 0) {
                a1 = a1 / 2;
                k++;
            }

            if (k % 2 == 0) {
                s = 1;
            } else {
                if ((n - 1) % 8 == 0 || (n + 1) % 8 == 0) {
                    s = 1;
                } else if ((n - 3) % 8 == 0 || (n + 3) % 8 == 0) {
                    s = -1;
                }
            }

            if (a1 != 1) {

                if ((n - 3) % 4 == 0 && (a1 - 3) % 4 == 0) {
                    s = -s;
                }

                a = modPow(n, 1, a1);
                n = a1;
                g = g * s;
            }
        } while (a1 != 1);

        return g * s;
    }

    private String solovejShtrassen(int n) {
        if (n % 2 == 0 || n < 5) {
            throw new RuntimeException("n must be uneven and more than 4");
        }

        int a = getRandomInt(2, n - 2);
        int r = modPow(a, (n-1)/2, n);

        if (r != 1 && r != (n - 1)) {
            return String.format("%s is composite", n);
        }

        int s = jacobi(n, a);
        if ((r - s) % n == 0) {
            return String.format("%s is composite", n);
        } else {
            return String.format("%s is probably prime", n);
        }
    }

    private String millerRabin(int n) {
        if (n % 2 == 0 || n < 5) {
            throw new RuntimeException("n must be uneven and more than 4");
        }

        int s = 0;
        int r = 0;
        int nEven = n - 1;

        while (nEven % 2 == 0) {
            nEven = nEven / 2;
            s++;
        }
        r = nEven;

        int a = getRandomInt(2, n-2);
        int y = modPow(a, r, n);

        if (y != 1 && y != (n - 1)) {
            for (int i = 1; i <= (s - 1) && y != (n - 1) && y != 1; i++) {
                y = modPow(y, 2, n);
            }
            if (y == 1 || y != (n - 1)) {
                return String.format("%s is composite", n);
            }
        }

        return String.format("%s is probably prime", n);
    }
}
