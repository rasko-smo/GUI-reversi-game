package gameMaster;

import frame.MainFrame;
import operation.Calculate;
import operation.DataProcess;
import tableValue.TableInt;

//ゲームに必要なデータを保持し、ゲーム進行等の処理を行うクラス
public class GameMaster {
	
	private final MainFrame frame;
	
	public DataProcess process = new DataProcess();
	public Calculate calculate = new Calculate();
	
	public int[][] table = new TableInt().getTableInt();
	public int turnCount = 1;
	public int totalTurnCount = 60;
	public int[] result = {0, 0, 0};
	public int inputableCheck = 0;
	public int playMode = 0;
	public int firstOrSecondPlayer = 0;
	public String selectedPlaceString;
	public int[] selectedPlaceInt = {9, 9};
	
	public GameMaster(MainFrame f) {
		this.frame = f;
	}
	
	//各ターンがスタートする際に使用
	public void playGame() {
		if(this.turnCount == 1) {
			if(this.playMode == 2 && this.firstOrSecondPlayer == 2) {
				this.frame.playPanel.displayEachTurn();
			}
			this.frame.changePanel("Play");
		} else {
			this.frame.playPanel.displayEachTurn();
		}
	}
	
	//「CPUと対戦」モードでCPUのターンを行う際に使用
	public void cpuTurn() {
		this.resultCheck(this.calculate.selectByCpu(this.table, this.turnCount));
	}

	//各ターンの結果を確認する際に使用
	public void resultCheck(String input) {
		this.selectedPlaceString = this.process.convertStringToString(input);
		this.selectedPlaceInt = this.process.convertStringToInt(input);
		this.table = this.process.updateTableByString(this.table, this.turnCount, input);
		this.result = this.calculate.checkResult(this.table, this.turnCount, this.totalTurnCount);
	 
		if(this.result[0] == 0) {
			this.turnCount++;
			this.inputableCheck = this.process.checkInputable(this.table, this.turnCount);
			
			if(this.inputableCheck == 2) {
				this.turnCount++;
				this.totalTurnCount++;
			}
		}
	}
	
	public void resetGame() {
		this.table = TableInt.resetTableInt(this.table);
		this.turnCount = 1;
		this.totalTurnCount = 60;
		this.result[0] = 0;
		this.result[1] = 0;
		this.result[2] = 0;
		this.inputableCheck = 0;
		this.playMode = 0;
		this.firstOrSecondPlayer = 0;
		this.selectedPlaceString = null;
		this.selectedPlaceInt[0] = 9;
		this.selectedPlaceInt[1] = 9;
	}
	
}
