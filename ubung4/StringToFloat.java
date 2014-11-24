class StringToFloat
{
    public static void main(String[] args)
    {
	    if(args.length != 1 || (args[0].length() != 32) || !args[0].matches("[01]{32}"))
        {
            System.out.println("wrong format");
            System.exit(1);
        }
        String str = args[0];

        System.out.println(str.substring(0,1)+"|"+str.substring(1,9)+"|"+str.substring(9));

        int e = bin2int(str.substring(1,9));
        int m;
        if(e==0) {
            m = bin2int(str.substring(9));          //Denormalisiert
            //System.out.print("Den");
        } else {
            m = bin2int("1" + str.substring(9));    //Normalisiert
        }
        int s = (str.charAt(0)=='1') ? -1 : 1;
        
        System.out.printf("%1s|%8s|%23s\n", (s==1 ? '+' : '-'), e, m);
        
        float f = s * (float)Math.pow(2,e-127) * m * (float)Math.pow(2,-23);

        String output;
        if(e==0 && m==0) {  //Exponent nur Nullen und Mantisse leer
            output = (s==1 ? "+0" : "-0");
        } else if(e==255) { //Exponent nur Einsen
            if (m==8388608) {
                output = (s==1 ? "POSITIVE_INFINITY" : "NEGATIVE_INFINITY");
            } else {
                output = "NaN";
            }
        } else {
            System.out.println("Ohne Vorzeichen: " + s*f);  //macht das Vorzeichen aus f wieder weg
            output = f + "";
        }
        System.out.println(output);
            
        
    }
    public static int bin2int(String in)
    {
        int result=0, exp=0;
        for(int i=in.length()-1; i>=0; i--)
        {
            result += (in.charAt(i)=='1')?Math.pow(2,exp):0;
            exp++;
        }
        return result;
    }

}


//usage: javac StringToFloat x\n - x has the format: 1 bit for Negation, 8 bit for the Exponent and 23 bit mantisse");
