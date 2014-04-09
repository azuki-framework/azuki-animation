package jp.afw.azuki.animation;

import jp.afw.azuki.animation.frame.TestFrame;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	public static void main(String[] args) {
		TestFrame frame = new TestFrame();
		frame.setVisible(true);
	}

	/**
	 * Create the test case
	 * 
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}
}
