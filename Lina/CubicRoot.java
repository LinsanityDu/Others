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





#Compute an approximate cube root using
#the bisection method (binary search).
#Of course, you can write x**(1.0/3) and
#achieve the same effect.  The point of the
#demo is more to show you how to code such an
#algorithm than to create a tool that you will
#actually use.

#Find the approximate cube root of x.
#You cannot do this by writing the loop
#in the form
#   while high != low:
#because you may find that the loop never
#terminates.  So there is a second argument
#to control the accuracy of the result.

#If the first argument x is negative, the algorithm
#begins by remembering the sign and working with -x
#instead.

#To take a cube root of 0 < x < 1,
#use [0,1] as the initial interval,
#otherwise, use [1,x]

#If the tolerance is set too small, the
#algorithm might never terminate, so there
#is a fail-safe statement included to break out of the loop
#if the number of iterations gets too large.

def cuberoot(x,tolerance):
    if x<0:
        sign = -1
        x = -x
    else:
        sign = 1
    if x < 1:
        low = 0
        high = 1
    else:
        low = 1
        high = x
    numiterations = 0
    while high - low > tolerance:
        mid = (high+low)/2.0
        numiterations += 1
        if numiterations > 80:
            break
        if mid**3>x:
            high = mid
        else:
            low = mid
    return sign * mid