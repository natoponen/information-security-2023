import static java.lang.Math.abs;

public class Pollard {
    public static void main(String[] args) {
        Long result = pMethod(1359331L, 1L);
        System.out.printf("Result is %s%n", result);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private static Long pMethod(Long n, Long c) {
        Long a = c;
        Long b = c;
        Long d = 1L;

        while (d.equals(1L)) {
            a = f(a) % n;
            b = f(f(b)) % n;
            if (b < 0) b += n;
            d = GCD(abs(a - b), n);

            System.out.printf("a = %s; b = %s; d = %s%n", a, b, d);

            if (1 < d && d < n) {
                return d;
            } else if (d.equals(n)) {
                throw new RuntimeException(String.format("Divider for %s not found", n));
            }
        }

        return 0L;
    }

    private static Long f(Long x) {
        return x*x + 5;
    }

    private static Long GCD(Long a, Long b) {
        if (b == 0) {
            return a;
        }
        return GCD(b, a % b);
    }
}
