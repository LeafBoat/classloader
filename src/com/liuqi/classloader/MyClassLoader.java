package com.liuqi.classloader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

	private String root;

	public MyClassLoader(String root) {
		this.root = root;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classData;
		try {
			classData = loadClassData(name);
			if(null == classData){
				throw new ClassNotFoundException();
			}else{
				return defineClass(name, classData, 0, classData.length);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private byte[] loadClassData(String name) throws IOException {
		String fileName = root + File.separatorChar
                + name.replace('.', File.separatorChar) + ".class";
		FileInputStream inputStream = new FileInputStream(fileName);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		inputStream.close();
		bos.close();
		return bos.toByteArray();
	}
}
