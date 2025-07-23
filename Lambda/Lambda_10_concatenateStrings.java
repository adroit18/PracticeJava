package Lambda;
public class Lambda_10_concatenateStrings {

    public interface InnerconcatenateStrings {
        String run(String a, String b);
    }

    public static void main(String[] args) {
        InnerconcatenateStrings concatenateStrings = (String a, String b) -> {
            return (a+b);
        };
        System.out.println(concatenateStrings.run("Deepak", " Uniyal"));
    }
}
