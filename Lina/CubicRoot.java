public static double GetSquareRoot(double n, double low, double high) {
    double sqrt = (low + high) / 2;
    if (sqrt*sqrt > n)
        return GetSquareRoot(n, low, sqrt);
    if (sqrt*sqrt < n)
        return GetSquareRoot(n, sqrt, high);
    return sqrt;
}
public static double Sqrt(double n){
    return GetSquareRoot(n, 0, n);
}

public static double GetCubicRoot(double n, double low, double high) {
    double cbrt = (low + high) / 2;
    if (cbrt*cbrt*cbrt > n)
        return GetCubicRoot(n, low, cbrt);
    if (cbrt*cbrt*cbrt < n)
        return GetCubicRoot(n, cbrt, high);
    return cbrt;
}
public static double Cbrt(double n) {
    return GetCubicRoot(n, 0, n);
}


