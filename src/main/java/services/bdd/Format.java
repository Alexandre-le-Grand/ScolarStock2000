package services.bdd;

public class Format {

    public static boolean verif(String text){
        return text.matches("['\"\n\r\t()]+"); //regex de formatage pour banir ces char et empecher les injections sql (non testee)
    }
    /*
    text.indexOf("'")+
    text.indexOf('\n')+
    text.indexOf('\r')+
    text.indexOf('\t')+
    text.indexOf('(')+
    text.indexOf(')')
    */
    public static boolean verif(String[] texts){
        for (String text : texts) {if (!verif(text)) {return false;}}
        return true;
    }
}
