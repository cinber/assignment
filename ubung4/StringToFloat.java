class StringToFloat
{
    public static void main(String[] args)
    {
	    if(args.length != 1 || (args[0].length() != 32) || !args[0].matches("[01]{32}"))
        {
            System.out.println("NaN");
            System.exit(1);
        }
        String str = args[0];

        System.out.println(str.substring(0,1)+"|"+str.substring(1,9)+"|"+str.substring(9));

        int e = bin2int(str.substring(1,9)) - 127;
        int m = bin2int(str.substring(9));
        int s = (str.charAt(0) == '1') ? -1 : 1;
        
        float f = s * (float)Math.pow(2,e) * m;
        System.out.println("s = " + s);
        System.out.println("e = " + e);
        System.out.println("m = " + m);
    }
    public static int bin2int(String in){
        int result=0, exp=0;
        for(int i=in.length()-1; i>=0; i--){
            result += (in.charAt(i)=='1')?Math.pow(2,exp):0;
            exp++;
        }
        return result;
    }

}


//usage: javac StringToFloat x\n - x has the format: 1 bit for Negation, 8 bit for the Exponent and 23 bit mantisse");
