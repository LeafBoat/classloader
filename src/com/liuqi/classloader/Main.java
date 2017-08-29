package com.liuqi.classloader;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
	public static void main(String[] args) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		System.out.println(loader);
		System.out.println(loader.getParent());
		System.out.println(loader.getParent().getParent());
		System.out.println(File.separatorChar);
		MyClassLoader classLoader = new MyClassLoader("D:\\temp");
		try {
			Class<?> loadClass = classLoader.loadClass("Test");
			Constructor<?> constructor = loadClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			Object object = constructor.newInstance();
			Method method = loadClass.getMethod("say");
			method.setAccessible(true);
			method.invoke(object);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
