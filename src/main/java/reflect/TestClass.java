package reflect;

/**
 * 要使用反射，首先需要获取
 */
public class TestClass {
    protected int a;
    private int b;
    public int c;

    public static void methodA() {
        System.out.println("this is public static method A...");
    }

    public void methodB() {
        System.out.println("this is public  method b...");
    }

    private void methodC() {
        System.out.println("this is  private void  method C...");
    }


    public static void main(String[] args) {



    }
}
