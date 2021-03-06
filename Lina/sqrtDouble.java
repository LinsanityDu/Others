public double sqrt(double x, double error) {

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


class SQRT 
{ 
public static void main(String args[]) 
{ 
Scanner sc=new Scanner(System.in); 
System.out.println("Enter a positive no. to find sqrt"); 
float n=sc.nextFloat(); 
if(n<0) 
{ 
System.out.println("negative don't have square roots"); 
System.exit(0); 
} 
float y=sqrt(n); 
System.out.println("sqrt is "+y); 
} 
static float sqrt(float n) 
{ 
float low=0,high=n; 
float mid=(low+high)/2; 
while(Math.abs(mid*mid-n)>0.00001) 
{ 

if(mid*mid<n) 
low=mid; 
else if(mid*mid>n) 
high=mid; 
mid=(low+high)/2; 
} 
return mid; 
} 
}

// Nine Chapter
class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // find the last number which square of it <= x
        long start = 1, end = x;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * mid <= x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (end * end <= x) {
            return (int) end;
        }
        return (int) start;
    }
}