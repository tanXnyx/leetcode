import java.util.ArrayList;
/*
 * Given a string expression representing an expression of fraction addition and subtraction, return the calculation result in string format.

The final result should be an irreducible fraction. If your final result is an integer, change it to the format of a fraction that has a denominator 1. So in this case, 2 should be converted to 2/1.

 

Example 1:

Input: expression = "-1/2+1/2"
Output: "0/1"
Example 2:

Input: expression = "-1/2+1/2+1/3"
Output: "1/3"
Example 3:

Input: expression = "1/3-1/2"
Output: "-1/6"
 

Constraints:

The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
Each fraction (input and output) has the format ±numerator/denominator. If the first input fraction or the output is positive, then '+' will be omitted.
The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will always be in the range [1, 10]. If the denominator is 1, it means this fraction is actually an integer in a fraction format defined above.
The number of given fractions will be in the range [1, 10].
The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.
Logic Breakdown
Count Fractions:

Determine the number of fractions in the input string. This is done by counting the number of '/' characters in the string.
Initialize Data Structures:

Create arrays to store:
Numerators of the fractions.
Denominators of the fractions.
Signs associated with each fraction (whether it's positive or negative).
Parse the Input String:

Traverse the string to extract and parse:
Signs: Determine if the fraction is positive or negative.
Numerators and Denominators: Extract these values based on their position in the string.
Handle transitions between fractions and signs appropriately to fill the arrays.
Compute Least Common Multiple (LCM):

Calculate the LCM of all denominators. The LCM is required to find a common denominator so that all fractions can be added or subtracted correctly.
Convert Fractions to Common Denominator:

Adjust each fraction so that its denominator matches the computed LCM. This involves:
Multiplying the numerator by the factor required to convert its denominator to the LCM.
Sum the Numerators:

Sum all the numerators after they have been adjusted to the common denominator. The resulting value is the numerator of the final result.
Reduce the Resulting Fraction:

Simplify the resulting fraction by dividing both the numerator and the denominator by their greatest common divisor (GCD). This ensures the fraction is in its simplest form.
Format the Result:

Convert the simplified numerator and denominator into a string format representing the final fraction. If the numerator is zero, ensure the denominator is set to 1 to follow the problem’s requirements.
Detailed Steps
Counting Fractions:

Use the '/' character to count how many fractions are present in the expression.
Array Initialization:

Initialize arrays for numerators, denominators, and signs based on the number of fractions.
Parsing the Expression:

Traverse the string and use conditions to detect signs ('+' or '-') and extract numeric values for numerators and denominators.
LCM Calculation:

Compute the LCM of all denominators to facilitate addition/subtraction. This involves multiplying and dividing based on GCD calculations.
Adjusting Fractions:

For each fraction, adjust the numerator according to how much the denominator needs to be scaled up to match the LCM.
Summing Up:

After adjusting, sum all the numerators to get the final numerator of the result.
Simplification:

Divide the final numerator and denominator by their GCD to get the fraction in its simplest form.
Formatting:

Convert the simplified result into a string format for output.
Let's walk through the logic with an example:

Expression: "1/2+1/3-1/4"

Step-by-Step Explanation
Count Fractions:

There are three fractions in the expression ("1/2", "1/3", "1/4").
Initialize Data Structures:

Arrays to hold:
Numerators: [1, 1, -1] (for "1/2", "1/3", and "1/4")
Denominators: [2, 3, 4]
Signs: [1, 1, -1] (signs corresponding to each fraction)
Parse the Input String:

Traverse the string to extract the numerators, denominators, and signs:
"1/2": Numerator = 1, Denominator = 2, Sign = +
"1/3": Numerator = 1, Denominator = 3, Sign = +
"1/4": Numerator = 1, Denominator = 4, Sign = -
Compute Least Common Multiple (LCM):

Calculate the LCM of the denominators [2, 3, 4]:
LCM of 2 and 3 is 6.
LCM of 6 and 4 is 12.
So, the LCM of [2, 3, 4] is 12.
Convert Fractions to Common Denominator:

Convert each fraction to have a denominator of 12:
"1/2": To get denominator 12, multiply numerator by 12 / 2 = 6. So, it becomes 6/12.
"1/3": To get denominator 12, multiply numerator by 12 / 3 = 4. So, it becomes 4/12.
"1/4": To get denominator 12, multiply numerator by 12 / 4 = 3. So, it becomes -3/12 (considering the negative sign).
Sum the Numerators:

Sum the numerators after converting to a common denominator:
6 + 4 - 3 = 7.
So, the resulting fraction is 7/12.
Reduce the Resulting Fraction:

The greatest common divisor (GCD) of 7 and 12 is 1, so the fraction is already in its simplest form.
Format the Result:

The final result is "7/12".
Time Complexity: (O(n + c log d)), where (n) is the length of the input string, (c) is the number of fractions, and (d) is the magnitude of the denominators.
Space Complexity: (O(c)), where (c) is the number of fractions.
 */

public class FractionAddAndSub {
    public String fractionAddition(String expression) {
        int n = expression.length();
        int c = 0;
        for(int i=0;i<n;i++){
            if(expression.charAt(i)=='/'){
                c++;
            }
        }
        int[] num = new int[c];
        int[] den = new int[c];
        int[] s = new int[c];
        int i = 0;
        if(expression.charAt(0)=='-'){
            s[0]=-1;
            i=1;
        }
        else{
            s[0]=1;
        }
        int j=0,k=0,l=1;
        while (i < n) {
            if (expression.charAt(i) == '-' || expression.charAt(i) == '+') {
                if (expression.charAt(i) == '-') {
                    s[l] = -1;
                } else {
                    s[l] = 1;
                }
                l++;
                i++;
            }
            int nu=0;
            while (i < n && Character.isDigit(expression.charAt(i))) {
                nu=nu*10+(expression.charAt(i)-'0');
                i++;
            }
            num[j++] = nu;
            i++; 
            int de = 0;
            while (i < n && Character.isDigit(expression.charAt(i))) {
                de=de* 10+(expression.charAt(i)-'0');
                i++;
            }
            den[k++]=de;
        }
        int lcm = den[0];
        for(i=1;i<c;i++){
            lcm = lcm*(den[i]/helper(lcm,den[i]));;
        }
        for(i=0;i<c;i++){
            int a = (lcm/den[i])*s[i];
            num[i]*=a;
        }
        int sum=0;
        for(i=0;i<c;i++){
            sum+=num[i];
        }
        int x = helper(Math.abs(sum),lcm);
        sum=sum/x;
        lcm=lcm/x;
        String str = Integer.toString(sum)+"/"+Integer.toString(lcm);
        return str;
    }
    public static int helper(int a,int b) {
        if (b==0)
            return a;
        return helper(b, a % b);
    }
    //   class Fraction {
    //     public boolean isNegative;
    //     public int numerator;
    //     public int denominator;

    //     public void placeNumerator(char n) {
    //         if(numerator > 0) {
    //             numerator *= 10;
    //         }
    //         numerator += Character.getNumericValue(n);
    //     }
    //     public void placeDenominator(char n) {
    //         if(denominator > 0) {
    //             denominator *= 10;
    //         }
    //         denominator += Character.getNumericValue(n);
    //     }
    //     public int getSignedNumerator() {
    //         return isNegative ? -numerator : numerator;
    //     }
    //     public void add(Fraction other) {
    //         int finalResult = this.getSignedNumerator() +
    //             other.getSignedNumerator();

    //         this.isNegative = (finalResult < 0);
    //         this.numerator = Math.abs(finalResult);

    //         if(finalResult == 0) {
    //             this.denominator = 1;
    //         }
    //     }
    //     public void reduce() {
    //         if(denominator == 1) {
    //             return;
    //         }
    //         if(denominator % numerator == 0) {
    //             denominator = denominator / numerator;
    //             numerator = 1;
    //             return;
    //         }
    //         if(numerator % denominator == 0) {
    //             numerator = numerator / denominator;
    //             denominator = 1;
    //             return;
    //         }
    //         int multipleCap = Math.min(numerator, denominator) / 2;

    //         for(int i = 2; i <= multipleCap; i++) {
    //             // If not evenly divisible, continue.
    //             if(numerator % i != 0 || denominator % i != 0) {
    //                 continue;
    //             }
    //             numerator = numerator / i;
    //             denominator = denominator / i;
    //             multipleCap = Math.min(numerator, denominator) / 2;
    //             i = 1;
    //         }
    //     }
    //     public String toString() {
    //         StringBuilder str = new StringBuilder();
    //         if(isNegative) {
    //             str.append('-');
    //         }
    //         str.append(numerator);
    //         str.append('/');
    //         str.append(denominator);
    //         return str.toString();
    //     }
    // }
    // public String fractionAddition(String expression) {

    //     ArrayList<Fraction> allFractions = parseFractions(expression);
    //     Fraction baseFraction = allFractions.get(0);

    //     for(int i = 1; i < allFractions.size(); i++) {
    //         Fraction actedUponFraction = allFractions.get(i);

    //         int baseDenominator = baseFraction.denominator;
    //         baseFraction.numerator *= actedUponFraction.denominator;
    //         baseFraction.denominator *= actedUponFraction.denominator;
    //         actedUponFraction.numerator *= baseDenominator;
    //         actedUponFraction.denominator *= baseDenominator;

    //         baseFraction.add(actedUponFraction);
    //         baseFraction.reduce();
    //     }
    //     return baseFraction.toString();
    // }
    // public ArrayList<Fraction> parseFractions(String expression) {
    //     ArrayList<Fraction> allFractions = new ArrayList<>();
    //     int i = 0;
    //     do {
    //         Fraction fraction = new Fraction();
    //         char currentChar = expression.charAt(i);

    //         if(currentChar == '-') {
    //             fraction.isNegative = true;
    //             i = i + 1;
    //         }
    //         if(currentChar == '+') {
    //             i = i + 1;
    //         }
    //         currentChar = expression.charAt(i);
    //         do {
    //             fraction.placeNumerator(currentChar);
    //             i = i + 1;
    //             currentChar = expression.charAt(i);
    //         } while(currentChar != '/');
    //         i = i + 1;

    //         currentChar = expression.charAt(i);
    //         do {
    //             fraction.placeDenominator(currentChar);
    //             i = i + 1;

    //             if(i >= expression.length()) {
    //                 break;
    //             }
    //             currentChar = expression.charAt(i);

    //         } while(i < expression.length() && 
    //             currentChar != '+' &&
    //             currentChar != '-');

    //         allFractions.add(fraction);

    //     } while(i < expression.length());

    //     return allFractions;
    // }
}
