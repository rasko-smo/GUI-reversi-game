package tableValue;

public class TableInt {
	
	private int[][] tableInt = new int[8][8];

	public TableInt() {
		for(int i = 0; i < tableInt.length; i++) {
			for(int j = 0; j < tableInt[i].length; j++) {
				this.tableInt[i][j] = 0;
			}
		}
		//2:●、4:〇
		this.tableInt[3][3] = 4;
		this.tableInt[4][4] = 4;
		this.tableInt[3][4] = 2;
		this.tableInt[4][3] = 2;
	}
	
	public int[][] getTableInt(){
		return this.tableInt;
	}
	
	public void setTableInt(int[][] setTable) {
		this.tableInt = setTable;
	}
	
	public static int[][] resetTableInt(int[][] previousTable){
		for(int i = 0; i < previousTable.length; i++) {
			for(int j = 0; j < previousTable[i].length; j++) {
				previousTable[i][j] = 0;
			}
		}
		previousTable[3][3] = 4;
		previousTable[4][4] = 4;
		previousTable[3][4] = 2;
		previousTable[4][3] = 2;
		
		return previousTable;
	}
}
