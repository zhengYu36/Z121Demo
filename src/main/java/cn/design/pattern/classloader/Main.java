package cn.design.pattern.classloader;

import java.io.FileInputStream;
import java.lang.reflect.Method;

public class Main {
    public static void main(String args[]) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("C:\\Users\\h\\Desktop\\Z121Demo\\src\\main\\java");
        Class clazz = classLoader.loadClass("cn.design.pattern.classloader.Person");
        Object obj = clazz.newInstance();
        Method helloMethod = clazz.getDeclaredMethod("hello", null);
        helloMethod.invoke(obj, null);
    }

    ;

    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name
                    + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;

        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

    }
}