package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//ゲームの最初の画面を構成するクラス
public class TitlePanel extends JPanel implements ActionListener {

	private final MainFrame frame;
	private final String str;
	private String path = new File(".").getAbsoluteFile().getParent() + "\\src\\frame\\img\\othello_game.png";
	private final ImageIcon icon = new ImageIcon(path);
	JPanel buttonPanel;
	private int btnOnOff = 0;
	
	//コンストラクタ
	public TitlePanel(MainFrame f, String s) {
		this.frame = f;
		this.str = s;
		this.setName(s);
		setLayout(new BorderLayout());
		this.setOpaque(false);
		
		JLabel titleLabel = new JLabel("～リバーシゲーム～");
		titleLabel.setOpaque(false);
		titleLabel.setForeground(new Color(70, 133, 133));
		titleLabel.setPreferredSize(new Dimension(500, 75));
		titleLabel.setFont(new Font("ＭＳ ゴシック", Font.ITALIC, 40));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel iconLabel = new JLabel();
		iconLabel.setIcon(this.icon);
		iconLabel.setHorizontalAlignment(JLabel.CENTER);
		iconLabel.setVerticalAlignment(JLabel.CENTER);
		
		this.buttonPanel = new JPanel();
		this.buttonPanel.setOpaque(false);
		this.buttonPanel.setPreferredSize(new Dimension(200, 135));
		this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.Y_AXIS));
		
		JPanel childButtonPanel1 = new JPanel();
		childButtonPanel1.setOpaque(false);
		childButtonPanel1.setAlignmentX(0.5f);
		childButtonPanel1.setPreferredSize(new Dimension(500, 10));
		
		JButton btn2Player = new JButton("2人で対戦");
			btn2Player.setPreferredSize(new Dimension(100, 30));
			btn2Player.setBackground(new Color(70, 133, 133));
			btn2Player.setForeground(new Color(222, 249, 196));
			btn2Player.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 15));
			btn2Player.setActionCommand("2player");
			btn2Player.addActionListener(this);
		JButton btnCpu = new JButton("CPUと対戦");
			btnCpu.setPreferredSize(new Dimension(100, 30));
			btnCpu.setBackground(new Color(70, 133, 133));
			btnCpu.setForeground(new Color(222, 249, 196));
			btnCpu.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 15));
			btnCpu.setActionCommand("vsCpu");
			btnCpu.addActionListener(this);
		
		childButtonPanel1.add(btn2Player);
		childButtonPanel1.add(btnCpu);
		this.buttonPanel.add(childButtonPanel1);
		
		add(titleLabel, BorderLayout.PAGE_START);
		add(iconLabel, BorderLayout.CENTER);
		add(this.buttonPanel, BorderLayout.PAGE_END);
		
	}
	
	//CPU対戦モードにおいて、先行か後攻かを選択するボタンを表示するメソッド
	public void displayFirstOrSecondButton() {
		
		if(this.btnOnOff == 1) {
			return;
		}
		
		JLabel selectLabel = new JLabel("CPUと対戦します。先行・後攻をお選びください。");
		selectLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 15));
		selectLabel.setForeground(new Color(70, 133, 133));
		selectLabel.setAlignmentX(0.5f);
		
		JPanel childButtonPanel2 = new JPanel();
		childButtonPanel2.setAlignmentX(0.5f);
		childButtonPanel2.setOpaque(false);
		
		JButton btnFirstPlayer = new JButton("先行");
		btnFirstPlayer.setPreferredSize(new Dimension(100, 30));
		btnFirstPlayer.setBackground(new Color(70, 133, 133));
		btnFirstPlayer.setForeground(new Color(222, 249, 196));
		btnFirstPlayer.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 15));
		btnFirstPlayer.setActionCommand("firstPlayer");
		btnFirstPlayer.addActionListener(this);
		JButton btnSecondPlayer = new JButton("後攻");
		btnSecondPlayer.setPreferredSize(new Dimension(100, 30));
		btnSecondPlayer.setBackground(new Color(70, 133, 133));
		btnSecondPlayer.setForeground(new Color(222, 249, 196));
		btnSecondPlayer.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 15));
		btnSecondPlayer.setActionCommand("secondPlayer");
		btnSecondPlayer.addActionListener(this);
		
		childButtonPanel2.add(btnFirstPlayer);
		childButtonPanel2.add(btnSecondPlayer);
		
		this.buttonPanel.add(selectLabel);
		this.buttonPanel.add(childButtonPanel2);
		
		this.btnOnOff = 1;
		
		this.revalidate();
		this.repaint();
	}
	
	//先行・後攻を選択するボタンを非表示にするメソッド
	public void closeFirstOrSecondButton() {
		JLabel tempLabel = (JLabel)this.buttonPanel.getComponent(1);
		JPanel tempPanel = (JPanel)this.buttonPanel.getComponent(2);
		
		this.buttonPanel.remove(tempLabel);
		this.buttonPanel.remove(tempPanel);
		
		this.btnOnOff = 0;
		
		this.revalidate();
		this.repaint();
	}
	
	//ボタン押下時のイベント処理
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if(command.equals("vsCpu")) {
			this.frame.gameMaster.playMode = 2;
			this.displayFirstOrSecondButton();
		} else {
			if(command.equals("2player")) {
				this.frame.gameMaster.playMode = 1;
			} else {
				if(command.equals("firstPlayer")) {
					this.frame.gameMaster.firstOrSecondPlayer = 1;
				} else {
					this.frame.gameMaster.firstOrSecondPlayer = 2;
				}
			}
			if(this.btnOnOff == 1) {
				this.frame.titlePanel.closeFirstOrSecondButton();
			}
			this.frame.gameMaster.playGame();
		}
	}
	
}
