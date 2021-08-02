package com.github.drinkjava2.frog.env;

import java.io.File;

import javax.swing.JFrame;

/**
 * Application will build env, frogs and let them run
 */
public class Application {
	public static final String CLASSPATH;
	static {
		String classpath = new File("").getAbsolutePath();
		int core = classpath.indexOf("core");
		CLASSPATH = classpath.substring(0, core);
	}
	public static JFrame mainFrame = new JFrame();
	public static Env env = new Env();
	public static BrainStructure brainStructure = new BrainStructure();

	public static void main(String[] args) throws InterruptedException {
		mainFrame.setLayout(null);
		mainFrame.setSize(1320, 840); // 窗口大小
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭时退出程序

		mainFrame.add(env);
		mainFrame.add(brainStructure);

		mainFrame.setVisible(true);

		env.run();
	}

}
