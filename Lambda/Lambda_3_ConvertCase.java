package Lambda;

interface InnerConvertCase {
    void run(String a);
}

public class Lambda_3_ConvertCase{

    public static void main(String args[]){
        String a = "Deepak";
        InnerConvertCase ConvertCase = (String strInput) -> {
            if(!strInput.isEmpty()){
                String lowerCase = strInput.toLowerCase();
                String upperCase = strInput.toUpperCase();
                System.out.println(lowerCase + " "+ upperCase);
            }else{
                System.out.println("Invalid input");
            }
        };

        ConvertCase.run(a);
    }
}
