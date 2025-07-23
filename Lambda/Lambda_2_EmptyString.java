package Lambda;

public class Lambda_2_EmptyString {

    public interface EmptyCheck {
        Boolean run(String a);
    }

    public static void main(String args[]){
        String name = "Deepak";
        String emptyTest = "";
        EmptyCheck strchecker = (String a) -> {
            if(a.isEmpty()){
                return true;
            }else{
                return false;
            }
        };
        System.out.println("Is Empty : "+strchecker.run(emptyTest));
        System.out.println("Is Empty : "+strchecker.run(name));
    }
}
