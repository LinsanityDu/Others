Compute Log Using Sqrt
Question:

Write a function that computes log2() using sqrt().

Answer:

As we know,

log ab = log a + log b

log a = ln a / ln 2 = 1.4427 * ln a

ln 1+x = x when x is very small

Now combining those equations together and we have our solution.

For example, log 10 = 3.3219

Here is our computing flow, 10 = 1.25 * 2^3  // normalize the given number to [1, 2)
1.25 = 1.0000068098 ^ (2^15)  // sqrt until it reaches a small value
log 10 = log 1.25 * 2^3
= 3 + ln 1.25 / ln 2
= 3 + 1.4427 * 2^15 * ln 1.0000068098
= 3 + 1.4427 * 2^15 * 0.0000068098
= 3.3219

public double log2WithSqrt (double x) {
    if (x <= 0) return null;

    double result_normalize = 0.0;
    while (x < 1) {
        x *= 2;
        double++;
    }
    while (x >= 2) {
        x /= 2;
        double++;
    }

    double power_sqrt = 1.0;
    while (x - 1.0 > 0.00001) {
        x = Math.sqrt(x);
        power_sqrt = power_sqrt * 2.0;
    }

    return result_normalize + 1.4427 * power_sqrt * (x - 1.0);
}