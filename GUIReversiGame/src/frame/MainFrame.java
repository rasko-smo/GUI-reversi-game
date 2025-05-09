package frame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import gameMaster.GameMaster;

//各パネルを管理するクラス
public class MainFrame extends JFrame{

	public GameMaster gameMaster = new GameMaster(this);
	public String[] PanelNames = {"Title", "Play"};
	public TitlePanel titlePanel = new TitlePanel(this, PanelNames[0]);
	public PlayPanel playPanel = new PlayPanel(this, PanelNames[1]);
	public JPanel[] jps = {titlePanel, playPanel};
	private final CardLayout layout = new CardLayout();
	JPanel mainPanel;
	
	private static final int HORIZONTAL = 500;
	private static final int VERTICAL = 610;
	String titleName = "～リバーシゲーム～";
	private String path1 = new File(".").getAbsoluteFile().getParent() + "\\src\\frame\\img\\frame_icon.png";
	private String path2 = new File(".").getAbsoluteFile().getParent() + "\\src\\frame\\img\\peace_animal.png";
	private final ImageIcon icon1 = new ImageIcon(path1);
	private final ImageIcon icon2 = new ImageIcon(path2);
	
	//コンストラクタ
	public MainFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowClosing());
		setSize(HORIZONTAL, VERTICAL);
		setLocationRelativeTo(null);
		setTitle(titleName);
		setIconImage(icon1.getImage());
		
		Container contentPane = getContentPane();
		contentPane.setBackground(new Color(222, 249, 196));
		mainPanel = new JPanel();
		mainPanel.setOpaque(false);
		mainPanel.setLayout(layout);
		for(JPanel panel : jps) {
			mainPanel.add(panel, panel.getName());
		}
		contentPane.add(mainPanel);
		
		String lafClassName = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
		try{
			UIManager.setLookAndFeel(lafClassName);
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		setResizable(false);
		setVisible(true);
	}

	//ウィンドウを閉じる際に確認を行うメソッド
	class WindowClosing extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			int ans = JOptionPane.showConfirmDialog(MainFrame.this, "本当に終了しますか？", "選択ダイアログ", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, icon2);
			if(ans == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
	
	//各パネルの表示を変更するメソッド
	public void changePanel(String str) {
		int index = Arrays.asList(PanelNames).indexOf(str);
		layout.show(jps[index].getParent(),  jps[index].getName());
	}

}