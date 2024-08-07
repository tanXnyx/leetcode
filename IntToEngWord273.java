public class IntToEngWord273 {
    /*
     * class Solution {
     public String numberToWords(int num) {
    if(num == 0)
        return "Zero";
    String[] bigString = new String[]{"Thousand","Million","Billion"};
    String result =  numberToWordsHelper(num%1000);
    num = num/1000;
    if(num > 0 && num%1000>0){
        result = numberToWordsHelper(num%1000) + "Thousand " + result;
    }
    num = num/1000;
    if(num > 0 && num%1000>0){
        result = numberToWordsHelper(num%1000) + "Million " + result;
    }
    num = num/1000;
    if(num > 0){
        result = numberToWordsHelper(num%1000) + "Billion " + result;
    }
    return result.trim();
}

public String numberToWordsHelper(int num){
    String[] digitString = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] teenString = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen","Eighteen", "Nineteen"};
    String[] tenString = new String[]{"","","Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String result = "";
    if(num > 99){
        result += digitString[num/100] + " Hundred ";
    }
    num = num % 100;
    if(num < 20 && num > 9){
        result += teenString[num%10]+" ";
    }else{
        if(num > 19){
            result += tenString[num/10]+" ";
        }
        num = num % 10;
        if(num > 0)
            result += digitString[num]+" ";
    }
    return result;
}
}
     */
    
    private final String[] belowTwenty = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        return helper(num);
    }

    private String helper(int num) {
        StringBuilder result = new StringBuilder();
        if (num < 20) {
            result.append(belowTwenty[num]);
        } else if (num < 100) {
            result.append(tens[num / 10]).append(" ").append(belowTwenty[num % 10]);
        } else if (num < 1000) {
            result.append(helper(num / 100)).append(" Hundred ").append(helper(num % 100));
        } else if (num < 1000000) {
            result.append(helper(num / 1000)).append(" Thousand ").append(helper(num % 1000));
        } else if (num < 1000000000) {
            result.append(helper(num / 1000000)).append(" Million ").append(helper(num % 1000000));
        } else {
            result.append(helper(num / 1000000000)).append(" Billion ").append(helper(num % 1000000000));
        }
        return result.toString().trim();
        /*
         * class Solution {
     public String numberToWords(int num) {
    if(num == 0)
        return "Zero";
    String[] bigString = new String[]{"Thousand","Million","Billion"};
    String result =  numberToWordsHelper(num%1000);
    num = num/1000;
    if(num > 0 && num%1000>0){
        result = numberToWordsHelper(num%1000) + "Thousand " + result;
    }
    num = num/1000;
    if(num > 0 && num%1000>0){
        result = numberToWordsHelper(num%1000) + "Million " + result;
    }
    num = num/1000;
    if(num > 0){
        result = numberToWordsHelper(num%1000) + "Billion " + result;
    }
    return result.trim();
}

public String numberToWordsHelper(int num){
    String[] digitString = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] teenString = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen","Eighteen", "Nineteen"};
    String[] tenString = new String[]{"","","Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String result = "";
    if(num > 99){
        result += digitString[num/100] + " Hundred ";
    }
    num = num % 100;
    if(num < 20 && num > 9){
        result += teenString[num%10]+" ";
    }else{
        if(num > 19){
            result += tenString[num/10]+" ";
        }
        num = num % 10;
        if(num > 0)
            result += digitString[num]+" ";
    }
    return result;
}
}
         */
    }
}
