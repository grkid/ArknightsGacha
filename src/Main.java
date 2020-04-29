import java.io.IOException;

public class Main {
    public static void main(String[] arg) throws IOException
    {
        GachaException ge=new GachaException(new GachaNewOperator());
        ge.exceptionSingle("新池_单捞.csv");
        System.out.println("1");
//        ge.exceptionDouble("普池_双捞.csv");
//        System.out.println("2");
        ge.exceptionHold(300,"新池_300发概率.csv");
    }
}
