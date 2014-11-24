class Bin2Int {
    public static void main(String[] args) {
        System.out.println(bin2int("0000"));
        System.out.println(bin2int("0001"));
        System.out.println(bin2int("0010"));
        System.out.println(bin2int("0011"));
        System.out.println(bin2int("0100"));
        System.out.println(bin2int("0101"));
        System.out.println(bin2int("0110"));
        System.out.println(bin2int("0111"));
        System.out.println(bin2int("1000"));
        System.out.println(bin2int("1001"));
        System.out.println(bin2int("1010"));
        System.out.println(bin2int("1011"));
        System.out.println(bin2int("1100"));
        System.out.println(bin2int("1101"));
        System.out.println(bin2int("1110"));
        System.out.println(bin2int("1111"));
    }

    public static int bin2int(String in){
        int result=0, curr=0;
        for(int i=in.length()-1; i>=0; i--){
            result += (in.charAt(i)=='1')?Math.pow(2,curr):0;
            curr++;
        }
        return result;
    }


}
