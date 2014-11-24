/* 1) Normalisiert/Denormalisiert: Ohne führende Nullen (die wir bei floats nicht haben) ist die vorderste Stelle einer Binärzahl immer 1. Deshalb wird sie einfach weggelassen um Platz zu sparen. => Normalisiert, die 1 muss wieder angefügt werden.
 * Ist Zahl zu klein geht das nicht (Exponent minimal). => Denormalisiert, einfach so lassen.
 * Der Exponent liegt als normale positive Zahl vor. Deshalb müssen noch 127 von ihm abgezogen werden.
 * 2) Floatdarstellung ist wohl für die Klausur wichtig (meinte der eine Tutor)
*/
 

class StringToFloat
{
    public static void main(String[] args)
    {
	    if(args.length != 1 || (args[0].length() != 32) || !args[0].matches("[01]{32}")) //reihenfolge wichtig, weil args[0] nicht zwingend existieren muss - java überprüft nicht weiter, wenn die 1. Bedingung true ergibt. matches("[01]{32}") überprüft, ob args[0] aus 32 mal [1 oder 0] besteht
        {
            System.out.println("usage: javac StringToFloat x\n - x has the format: 1 bit for Negation, 8 bit for the Exponent and 23 bit mantisse");
            System.exit(1);
        }
        String str = args[0];
        
        System.out.println(str.substring(0,1)+"|"+str.substring(1,9)+"|"+str.substring(9)); //Zeigt an, wo der String aufgetrennt wird
        
        int e = bin2int(str.substring(1,9));    //extrahiert den Exponenten; substring gibt ab Stelle 1 bis 9 zurück
        int m;
        if(e==0) {
            m = bin2int(str.substring(9));          //Denormalisiert
        } else {
            m = bin2int("1" + str.substring(9));    //Normalisiert
        }
        int s = (str.charAt(0)=='1') ? -1 : 1;      //steht am Anfang eine 0 so wird die Zahl negativ
        
        System.out.printf("%1s|%8s|%23s\n", (s==1 ? '+' : '-'), e, m);      //gibt die Werte für s, e, m aus
        
        float f = (float)Math.pow(2,e-127) * m * (float)Math.pow(2,-23);    //(float) braucht man, weil Math.pow double zurückgibt und man ihn zwingen muss zur not zu runden - Vorzeichen kommt noch

        if(e==0 && m==0) {  //Exponent nur Nullen und Mantisse leer
            System.out.println((s==1 ? "+0" : "-0"));
        } else if(e==255) { //Exponent nur Einsen
            if (!str.substring(9).contains("1")) {  //Mantisse nur Nullen
                System.out.println((s==1 ? "POSITIVE_INFINITY" : "NEGATIVE_INFINITY")); //Unendlichkeiten
            } else {
                System.out.println("NaN");      //Not a Number - hab wir nicht definiert
            }
        } else {
            System.out.println("Ohne Vorzeichen: " + f);
            System.out.println("Mit Vorzeichen:  " + s * f);
        }
            
        
    }
    public static int bin2int(String in)    //wandelt einen String aus 1 und 0 in eine Integer
    {
        int result=0, exp=0;
        for(int i=in.length()-1; i>=0; i--) //zählt von der letzten zur ersten Stelle herrunter
        {
            result += (in.charAt(i)=='1') ? Math.pow(2,exp) : 0;    //die entsprechende 2er-Potenz wird aufaddiert wenn die aktuelle Stelle =1 ist
            exp++;
        }
        return result;
    }

}
