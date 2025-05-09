package test;

import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

public class Test extends JFrame{

	public static void main(String[] args) {

		//nullチェック
		Object test = null;
		if(test == null) {
			System.out.println("==null");
		} 
		if(Objects.isNull(test)) {
			System.out.println("Objects.isNull()");
		}
		
		//BorderLayout
		
		System.out.println("1");
		
		//UIManagerのLook&Feelの一覧を取得
		UIManager.LookAndFeelInfo infos[] = UIManager.getInstalledLookAndFeels();

		for(int i = 0 ; i < infos.length ; i++){
		  System.out.println(infos[i].getClassName());
		  System.out.println(infos[i].getName());
		}
		
		
		
		try{
		      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		    }catch(Exception ex){
		      System.out.println("Error L&F Setting");
		    }

		    LookAndFeel laf = UIManager.getLookAndFeel();
		    System.out.println(laf.getSupportsWindowDecorations());

		    try{
		      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		    }catch(Exception ex){
		      System.out.println("Error L&F Setting");
		    }

		    laf = UIManager.getLookAndFeel();
		    System.out.println(laf.getSupportsWindowDecorations());

		    try{
		      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    }catch(Exception ex){
		      System.out.println("Error L&F Setting");
		    }

		    laf = UIManager.getLookAndFeel();
		    System.out.println(laf.getSupportsWindowDecorations());

		    try{
		      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		    }catch(Exception ex){
		      System.out.println("Error L&F Setting");
		    }

		    laf = UIManager.getLookAndFeel();
		    System.out.println(laf.getSupportsWindowDecorations());
		   
		    
		System.out.println("2");
	}

}
