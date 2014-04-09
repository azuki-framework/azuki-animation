package jp.afw.azuki.animation;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * Hello world!
 * 
 */
public class App {
	
	public static void main(String[] args) {
		System.out.println("Hello World!");

		CLibrary.INSTANCE.printf("Hello, World\n");
	}

	public interface CLibrary extends Library {
		CLibrary INSTANCE = (CLibrary) Native.loadLibrary("msvcrt", CLibrary.class);

		void printf(String format, Object... args);
	}
	
}
