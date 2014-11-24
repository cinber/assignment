class StringToFloat
{
    public static void main(String[] args)
    {

	if(args.length != 1 || (args[0].length() != 32) || !args[0].matches("[01]{32}"))
        {
            System.out.println("usage: javac StringToFloat x\n - x has the format: 1 bit for Negation, 8 bit for the Exponent and 23 bit mantisse");
            System.exit(1);
        }
        String str = args[0];
        int e = bin2int(str.substring(1,9)) - 127;
        int m = bin2int(str.substring(9));
        

        
        int s = (str.charAt(0) == '1') ? -1 : 1;
        //Boolean s = (str.charAt(0) == '1');
        for (int i = 1; i < 8; i++)
        {
            System.out.print(str.charAt(i));
        }
        float f = e * Math.pow(2,e) * m;
        System.out.println("s = " + s);
        System.out.println("e = " + e);
        System.out.println("m = " + m);
        System.out.println();

    }
    public static int bin2int(String s) {
        System.out.println("substring: " + s);
        return 0;
    }
}

