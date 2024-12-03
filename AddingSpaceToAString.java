public class AddingSpaceToAString {
    public String addSpaces(String s, int[] spaces) {
        int n=s.length();
        int m=spaces.length;
        char[] chrs=new char[n+m];

        int i=0,j=0;
        for(char chr:s.toCharArray()){
            if(j<m && i==spaces[j]){
                chrs[i+j]=' ';
                j++;
            }
            chrs[i+j]=chr;
            i++;
        }

        return new String(chrs);
    }
}
