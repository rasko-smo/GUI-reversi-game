package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import gameMaster.GameMaster;

//ゲームをプレイする画面を構成するクラス
public class PlayPanel extends JPanel implements ActionListener  {

	private final MainFrame frame;
	private final String str;
	private GameMaster gm;
	final String[] columnNames = {"a", "b", "c", "d", "e", "f", "g", "h"};
	private String path = new File(".").getAbsoluteFile().getParent();
	private String icon1Path = this.path + "\\src\\frame\\img\\othello_ishi_black.png";
	private String icon2Path = this.path + "\\src\\frame\\img\\othello_ishi_white.png";
	private String icon3Path = this.path + "\\src\\frame\\img\\hatena_animal.png";
	private final ImageIcon icon1 = new ImageIcon(this.icon1Path);
	private final ImageIcon icon2 = new ImageIcon(this.icon2Path);
	private final ImageIcon icon3 = new ImageIcon(this.icon3Path);
	int[][] table;
	String myPlayer;
	String opponentPlayer;
	String[] playerName = {"黒の先行プレイヤー", "白の後攻プレイヤー", "CPU"};
	String resultText;
	public String[] playPanelNames = {"North", "South", "Center"};
	
	int sec = 0;
	String[] timerString = {"CPU選択中    ", "CPU選択中・  ", "CPU選択中・・"}; 
	JLabel timerLabel;
	Timer timer;
	
	//コンストラクタ
	public PlayPanel(MainFrame f, String s) {
		this.frame = f;
		this.str = s;
		this.gm = frame.gameMaster;
		this.setName(s);
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		createNorthPanel(playPanelNames[0]);
		createSouthPanel(playPanelNames[1]);
		createCenterPanel(playPanelNames[2]);
	}
	
	//ボタン押下時のイベント処理
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		if(command.matches("\\d*")) {
			this.gm.resultCheck(command);
			this.gm.playGame();
		} else if(command.equals("timer")) {
			if(this.sec < 2) {
				this.timerLabel.setText(this.timerString[sec + 1]);
				this.sec++;
				this.revalidate();
				this.repaint();
			} else {
				timer.stop();
				this.sec = 0;
				this.gm.cpuTurn();
				this.gm.playGame();
			}
		} else if(command.equals("toTitle")) {
			int option = JOptionPane.showConfirmDialog(this.frame, "タイトルに戻りますか？", "選択ダイアログ", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, icon3);
			if(option == JOptionPane.YES_OPTION) {
				this.gm.resetGame();
				this.displayEachTurn();
				this.frame.changePanel("Title");
			}
		}
	}
	
	//各ターンが経過するごとに、盤面の表示を更新するメソッド
	public void displayEachTurn() {
		JPanel tempSouthPanel = (JPanel)this.getComponent(1);
		JPanel tempCenterPanel = (JPanel)this.getComponent(2);
		this.remove(tempSouthPanel);
		this.remove(tempCenterPanel);
		
		createSouthPanel(playPanelNames[1]);
		createCenterPanel(playPanelNames[2]);
		
		this.revalidate();
		this.repaint();
	}
	
	//ウィンドウ上部に表示するパネルを作成するメソッド。「タイトルに戻る」ボタンの実装個所。
	public void createNorthPanel(String name) {
		JPanel northPanel = new JPanel();
		northPanel.setName(name);
		northPanel.setOpaque(false);
		
		FlowLayout northLayout = new FlowLayout(FlowLayout.LEFT);
		northPanel.setLayout(northLayout);
		northPanel.setPreferredSize(new Dimension(500, 33));
		JButton toTitleBtn = new JButton("タイトルに戻る");
		toTitleBtn.setBackground(new Color(70, 133, 133));
		toTitleBtn.setForeground(new Color(222, 249, 196));
		toTitleBtn.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 15));
		toTitleBtn.addActionListener(this);
		toTitleBtn.setActionCommand("toTitle");
		northPanel.add(toTitleBtn);
		
		this.add(northPanel, BorderLayout.PAGE_START);
	}	
	
	//ウィンドウ下部に表示するパネルを作成するメソッド。主に文章の表示を担当。
	public void createSouthPanel(String name) {
		JPanel southPanel = new JPanel();
		southPanel.setName(name);
		southPanel.setOpaque(false);
		southPanel.setPreferredSize(new Dimension(500, 87));
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
		JLabel southLabel1 = new JLabel();
		southLabel1.setAlignmentX(0.5f);
		southLabel1.setForeground(new Color(70, 133, 133));
		southLabel1.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 15));
		JLabel southLabel2 = new JLabel();
		southLabel2.setForeground(new Color(70, 133, 133));
		southLabel2.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 15));
		southLabel2.setAlignmentX(0.5f);
		
		if(this.gm.result[0] == 0) {
			if(this.gm.playMode == 2 &&((this.gm.firstOrSecondPlayer == 1 && this.gm.turnCount % 2 == 0)
					|| (this.gm.firstOrSecondPlayer == 2 && this.gm.turnCount % 2 == 1))) {
				
				this.timer = new Timer(1000, this);
				this.timer.setActionCommand("timer");
				
				this.timerLabel = new JLabel(this.timerString[0]);
				this.timerLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 15));
				this.timerLabel.setForeground(new Color(70, 133, 133));
				this.timerLabel.setAlignmentX(0.5f);
				
				southPanel.add(timerLabel);
				this.add(southPanel, BorderLayout.PAGE_END);
				timer.start();
				return;
			}
		
			if(this.gm.playMode == 1) {
				if(this.gm.turnCount % 2 == 1) {
					this.myPlayer = this.playerName[0];
					this.opponentPlayer = this.playerName[1];
				} else {
					this.myPlayer = this.playerName[1];
					this.opponentPlayer = this.playerName[0];
				}
			} else {
				this.opponentPlayer = this.playerName[2];
				if(this.gm.turnCount % 2 == 1) {
					this.myPlayer = "黒のプレイヤー";
				} else {
					this.myPlayer = "白のプレイヤー";
				}
			}
			
			if(this.gm.inputableCheck == 1) {
				if(this.gm.playMode == 1) {
					southLabel1.setText("「" + this.gm.selectedPlaceString + "」が選択されました。");
				} else {
					southLabel1.setText(this.playerName[2] + "は「" + this.gm.selectedPlaceString + "」を選択しました。");
				}
				southPanel.add(southLabel1);
			} else if(this.gm.inputableCheck == 2) {
				southLabel1.setText("入力可能な場所がないため、" + this.opponentPlayer + "のターンをスキップしました。");
				southPanel.add(southLabel1);
			}
			southLabel2.setText(this.myPlayer + "は、石を置く場所をクリックしてください。");
			southPanel.add(southLabel2);
		} else {
			if(this.gm.inputableCheck == 3) {
				southLabel1.setText("両者とも選択可能な場所がないため、勝敗がつきました。");
			}
			if(this.gm.result[0] == 1) {
				if(this.gm.firstOrSecondPlayer == 2) {
					this.resultText = "黒の" + playerName[2] + "の勝利です！";
				} else {
					this.resultText = playerName[0] + "の勝利です！";
				}
			} else if(this.gm.result[0] == 2) {
				if(this.gm.firstOrSecondPlayer == 1) {
					this.resultText = "白の" + playerName[2] + "の勝利です！";
				} else {
					this.resultText = playerName[1] + "の勝利です！";
				}
			} else if(this.gm.result[0] == 3) {
				this.resultText = "結果は引き分けです！";
			}
			southLabel2.setText(this.gm.result[1] + "対" + this.gm.result[2] + "で、" + this.resultText);
			
			southPanel.add(southLabel1);
			southPanel.add(southLabel2);
		}
		this.add(southPanel, BorderLayout.PAGE_END);
	}
	
	//ウィンドウの真ん中に表示するパネルを作成するメソッド。オセロの盤面表示を担当。
	public void createCenterPanel(String name) {
		JPanel centerPanel = new JPanel();
		centerPanel.setName(name);
		centerPanel.setOpaque(false);
		GridLayout centerLayout = new GridLayout();
		centerLayout.setRows(10);
		centerLayout.setColumns(10);
		centerPanel.setLayout(centerLayout);
		this.table = gm.process.combineTableIntToInputablePlaceInt(gm.table, gm.turnCount);
		for(int i = -1; i < this.table.length + 1; i++) {
			for(int j = -1; j < this.table[0].length + 1; j++) {
				
				if(i == -1) {
					if(j == -1) {
						centerPanel.add(this.createFrameCorner(3, 3, 8));
					} else if(j == this.table[0].length){
						centerPanel.add(this.createFrameCorner(3, 3, 6));
					} else {
						centerPanel.add(this.createFrameLine(3, 1, 2, this.columnNames[j]));
					}
					continue;
				} else if(i == this.table[0].length) {
					if(j == -1) {
						centerPanel.add(this.createFrameCorner(3, 3, 2));
					} else if(j == this.table[0].length){
						centerPanel.add(this.createFrameCorner(3, 3, 0));
					} else {
						centerPanel.add(this.createFrameLine(3, 1, 0, null));
					}
					continue;
				}
				
				if(j == -1) {
					centerPanel.add(this.createFrameLine(1, 3, 2, Integer.toString(i + 1)));
					continue;
				} else if(j == this.table[0].length) {
					centerPanel.add(this.createFrameLine(1, 3, 0, null));
					continue;
				}
				
				if(this.table[i][j] == 7 && !(this.gm.playMode == 2 &&((this.gm.firstOrSecondPlayer == 1 && this.gm.turnCount % 2 == 0)
						|| (this.gm.firstOrSecondPlayer == 2 && this.gm.turnCount % 2 == 1)))) {
					JButton centerBtn = new JButton();
					centerBtn.setActionCommand(Integer.toString(i) + Integer.toString(j));
					centerBtn.setHorizontalAlignment(JButton.CENTER);
					centerBtn.addActionListener(this);
					centerBtn.setBorder(new LineBorder(new Color(51, 51, 51), 2, false));
					centerBtn.setBackground(new Color(156, 219, 166));
					centerPanel.add(centerBtn);
				} else {
					JLabel centerLabel = new JLabel();
					centerLabel.setBorder(new LineBorder(Color.BLACK, 2, false));
					centerLabel.setBorder(new LineBorder(new Color(51, 51, 51), 2, false));
					centerLabel.setHorizontalAlignment(JLabel.CENTER);
					centerLabel.setOpaque(true);
					centerLabel.setBackground(new Color(80, 180, 152));
					if(this.table[i][j] == 0) {
						centerLabel.setText("");
					} else if(this.table[i][j] == 2) {
						centerLabel.setIcon(this.icon1);
					} else if(this.table[i][j] == 4) {
						centerLabel.setIcon(this.icon2);
					}
					if(i == this.gm.selectedPlaceInt[0] && j == this.gm.selectedPlaceInt[1]) {
						centerLabel.setBackground(Color.YELLOW);
					}
					centerPanel.add(centerLabel);
				}
			}
		}
		this.add(centerPanel, BorderLayout.CENTER);
	}
	
	//createCenterPanelメソッドにおける、盤面外枠の角の作成を外部化したメソッド
	public JPanel createFrameCorner(int row, int column, int blackPlace) {
		JPanel tempPanel = new JPanel();
		tempPanel.setOpaque(false);
		
		GridLayout tempLayout = new GridLayout();
		tempLayout.setRows(row);
		tempLayout.setColumns(column);
		tempPanel.setLayout(tempLayout);
		
		JLabel blankLabel;
		JLabel centerLabel = new JLabel();
		centerLabel.setOpaque(true);
		centerLabel.setBackground(new Color(51, 51, 51));
		
		for(int k = 0; k < row * column; k++) {
			if(k != blackPlace) {
				blankLabel = new JLabel();
				tempPanel.add(blankLabel);
			} else {
				tempPanel.add(centerLabel);
			}
		}
		
		return tempPanel;
	}

	//createCenterPanelメソッドにおける、盤面外枠の線の作成を外部化したメソッド
	public JPanel createFrameLine(int row, int column, int blackPlace, String names) {
		JPanel tempPanel = new JPanel();
		tempPanel.setOpaque(false);
		
		GridLayout tempLayout = new GridLayout();
		tempLayout.setRows(row);
		tempLayout.setColumns(column);
		tempPanel.setLayout(tempLayout);
		
		JLabel blankLabel;
		
		JLabel centerLabel = new JLabel();
		centerLabel.setOpaque(true);
		centerLabel.setBackground(new Color(51, 51, 51));
		if(names != null) {
			centerLabel.setText(names);
			centerLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 15));
			centerLabel.setHorizontalAlignment(JLabel.CENTER);
			centerLabel.setForeground(Color.WHITE);
		}
		
		for(int k = 0; k < row * column;k++) {
			if(k != blackPlace) {
				blankLabel = new JLabel();
				tempPanel.add(blankLabel);
			} else {
				tempPanel.add(centerLabel);
			}
		}
		
		return tempPanel;
	}
	
}
