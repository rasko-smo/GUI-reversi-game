package operation;

//データの加工処理を行うクラス
public class DataProcess {
	
	public static int[][] combineTableIntToInputablePlaceInt(int[][] tableInt, int turnCount){
		
		int firstOrSecondPlayer = 0;
		if(turnCount % 2 == 1) {
			firstOrSecondPlayer = 1;
		} else if(turnCount % 2 == 0) {
			firstOrSecondPlayer = 2;
		}
		
		int[][] tempTable = new int[tableInt.length][tableInt[0].length];
		int[][] inputablePlaceInt = Calculate.checkInputablePlace(tableInt, firstOrSecondPlayer);
		
		for(int i = 0; i < tableInt.length; i++) {
			for(int j = 0; j < tableInt[i].length; j++) {
				if(tableInt[i][j] == 2) {
					tempTable[i][j] = 2;
				} else if(tableInt[i][j] == 4) {
					tempTable[i][j] = 4;
				} else if(inputablePlaceInt[i][j] == 7) {
					tempTable[i][j] = 7;
				} else {
					tempTable[i][j] = 0;
				}
			}
		}
		
		return tempTable;
		
	}
	
	//文字情報のテーブルの位置(例：11)を、他の文字情報(例：b2)に変換するメソッド
	public String convertStringToString(String str) {
		
		String s = null;
		
		String columnString = str.substring(1, 2);
		switch(columnString) {
		case "0" -> { s = "a";}
		case "1" -> { s = "b";}
		case "2" -> { s = "c";}
		case "3" -> { s = "d";}
		case "4" -> { s = "e";}
		case "5" -> { s = "f";}
		case "6" -> { s = "g";}
		case "7" -> { s = "h";}
		}
		
		String rowString = str.substring(0, 1);
		switch(rowString) {
			case "0" -> {s += "1";}
			case "1" -> {s += "2";}
			case "2" -> {s += "3";}
			case "3" -> {s += "4";}
			case "4" -> {s += "5";}
			case "5" -> {s += "6";}
			case "6" -> {s += "7";}
			case "7" -> {s += "8";}
		}
		
		return s;
	}
	
	//文字情報のテーブルの位置(例：11)を、整数配列の情報(例：配列iに対して、i[0] = 1,、i[1] = 1)に変換するメソッド
	public int[] convertStringToInt(String str) {
		
		String inputRowString = str.substring(0, 1);
		int inputRow = Integer.parseInt(inputRowString);
		
		String inputColumnString = str.substring(1, 2);
		int inputColumn = Integer.parseInt(inputColumnString);
		
		int[] selectedPlace = {inputRow, inputColumn};
		return selectedPlace;
		
	}
	
	//入力可能な場所があるか確認するメソッド
	public static int checkInputable(int[][] tableInt, int turnCount) {
		int inputableInt = 0;
		int[][] myTempTable = Calculate.checkInputablePlace(tableInt, turnCount);
		int myCount = 0;
		int[][] opponentTempTable = Calculate.checkInputablePlace(tableInt, turnCount + 1);
		int opponentCount = 0;
		
		for(int i = 0; i < myTempTable.length; i++) {
			for(int j = 0; j < myTempTable[0].length; j++) {
				if(myTempTable[i][j] == 7) {
					myCount++;
				}
				if(opponentTempTable[i][j] == 7) {
					opponentCount++;
				}
			}
		}
		
		if(myCount > 0) {
			inputableInt = 1;
		}else if (myCount == 0 && opponentCount > 0){
			inputableInt = 2;
		} else {
			inputableInt = 3;
		}
		
		return inputableInt;
	}
	
	//String型の入力値を使用してtableIntの値を更新するメソッド
	public int[][] updateTableByString(int[][] tableInt, int turnCount, String input){
		
		String inputRowString = input.substring(0, 1);
		int inputRow = Integer.parseInt(inputRowString);
		
		String inputColumnString = input.substring(1, 2);
		int inputColumn = Integer.parseInt(inputColumnString);
		
		int[] selectedPlace = {inputRow, inputColumn};
		
		tableInt = new Calculate().updateTableInt(tableInt, turnCount, selectedPlace);
		return tableInt;
	}
	
}
