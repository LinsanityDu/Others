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


    public double cubic(double x, double error) {
        int sign = 1;
        if (x == 0) return 0;
        if (x < 0) {
            sign = -1;
            x = -x;
        } 
        double start = 0;
        double end = x;
        if (x < 1) end = 1;
        while (start + error < end) {
            double mid = (start + end) / 2;
            double midCub = mid * mid * mid;
            if (midSqr == x) {
                return mid;
            } else if (midCub < x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return (start + end) / 2;
    }


double sqrt(double a)
{
 //firstly check if a is non-negative value
 if(a<0) return -1;
 //also check if a==0 or a==1 because in these two cases sqrt(a) = a
 if(a==0 || a==1) return a;
 
 //now start the core part of the code
 double precision = 0.00001;//define the precision, so we stop when precision is achieved
 double start = 0;
 double end = a;
 //we define these two start/end values because usually 0<sqrt(a)<a
 //however, if a<1; then 0<a<sqrt(a)
 if(a<1)
  end = 1;
 
 //define a loop to continue if the precision is not yet achieved
 while(end-start>precision)
 {
  double mid = (start+end)/2;
  double midSqr = mid*mid;
  if(midSqr==a) return mid;//we find the exact sqrt value!
  else if(midSqr<a) start = mid;//we shift our focus to bigger half
  else end = mid;//shift focus to smaller half
 }
 
 //if we did not find exact sqrt value, we return the approxiated value with the defined precision
 return (start+end)/2;
}

int main()
{
printf("%lf",sqrt(120));
return 0;
}