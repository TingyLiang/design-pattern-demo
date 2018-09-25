package reflect;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 要使用反射，首先需要获取Class 对象
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

    public void methodB(int a) {
        System.out.println(a);
    }

    public void methodB(int a, int b) {
        System.out.println(a + b);
    }

    private void methodC() {
        System.out.println("this is  private void  method C...");
    }


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException, IOException {
        /*获取class 对象的三种方法*/
        //1. object 的getClass()方法
        //2. 任何对象的.class 属性
        //3. class.forName(className) 这个方式非常常用

        TestClass testClass = new TestClass();
        Class tClass = testClass.getClass();

        //返回类中所有的方法对象，包括公有，私有和protected
        Method[] methods = tClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);

        }
        //获取公有方法并调用，需要绑定到一个对象上
        Method b = tClass.getDeclaredMethod("methodB");
        System.out.println(b.invoke(testClass));
        //如果方法带有参数，则需要制定方法的形参的类型，即 形参类型.class
        b = tClass.getDeclaredMethod("methodB", int.class);
        System.out.println(b.invoke(testClass, 1));
        b = tClass.getDeclaredMethod("methodB", int.class, int.class);
        System.out.println(b.invoke(testClass, 1, 2));

        Method a = tClass.getDeclaredMethod("methodA");
        System.out.println(a.invoke(testClass));

        Method c = tClass.getDeclaredMethod("methodC");
        //私有方法需要解除私有锁定,实际不解除也能正常使用。。？？
//        c.setAccessible(true);
        System.out.println(c.invoke(testClass));

        //这里测试最常用的第三种方式，class.forName
        tClass = Class.forName(getValue("className"));
        Method B = tClass.getDeclaredMethod(getValue("methodName"), int.class);
        System.out.println(b.invoke(testClass, 110,1));

    }

    private static String getValue(String key) throws IOException {
        Properties properties = new Properties();
        FileReader reader = new FileReader("src/main/resources/class.properties");
        properties.load(reader);
        reader.close();
        return properties.getProperty(key);
    }


}
