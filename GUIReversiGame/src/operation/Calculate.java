package operation;

//データの計算を行うクラス
public class Calculate {
	
	//盤面の入力可能な場所を確認するメソッド
	public static int[][] checkInputablePlace(int[][] tableInt, int turnCount){
		
		int[][] inputablePlace = new int[tableInt.length][tableInt[0].length];
		
		int myStoneInt = 0;
		int opponentStoneInt = 0;
		if(turnCount % 2 == 1) {
			myStoneInt = 2;
			opponentStoneInt = 4;
		} else if(turnCount % 2 == 0) {
			myStoneInt = 4;
			opponentStoneInt = 2;
		}
		
		for(int i = 0; i < tableInt.length; i++) {
			
			for(int j = 0; j < tableInt[i].length; j++) {
				
				if(tableInt[i][j] != 0) {
					continue;
				}
				
				//右
				if(tableInt[i].length - j > 2) {
					if(tableInt[i][j + 1] == opponentStoneInt) {
						int countMyStone = 0;
						int countBlank = 0;
						for(int l = j + 2; l < 8; l++) {
							if(tableInt[i][l] == myStoneInt) {
								countMyStone++;
							}else if(tableInt[i][l] == 0) {
								countBlank++;
							}
							if(countMyStone > 0 && countBlank == 0) {
								inputablePlace[i][j] = 7;
								break;
							} else if(countMyStone == 0 && countBlank > 0) {
								break;
							}
						}
					}
				}
				
				//左
				if(tableInt[i].length - j < 7) {
					if(tableInt[i][j - 1] == opponentStoneInt) {
						int countMyStone = 0;
						int countBlank = 0;
						for(int l = j - 2; l >= 0; l--) {
							if(tableInt[i][l] == myStoneInt) {
								countMyStone++;
							}else if(tableInt[i][l] == 0) {
								countBlank++;
							}
							if(countMyStone > 0 && countBlank == 0) {
								inputablePlace[i][j] = 7;
								break;
							} else if(countMyStone == 0 && countBlank > 0) {
								break;
							}
						}
					}
				}
				
				//下
				if(tableInt.length - i > 2) {
					if(tableInt[i + 1][j] == opponentStoneInt) {
						int countMyStone = 0;
						int countBlank = 0;
						for(int l = i + 2; l < 8; l++) {
							if(tableInt[l][j] == myStoneInt) {
								countMyStone++;
							}else if(tableInt[l][j] == 0) {
								countBlank++;
							}
							if(countMyStone > 0 && countBlank == 0) {
								inputablePlace[i][j] = 7;
								break;
							} else if(countMyStone == 0 && countBlank > 0) {
								break;
							}
						}
					}
				}
				
				//上
				if(tableInt.length - i < 7) {
					if(tableInt[i - 1][j] == opponentStoneInt) {
						int countMyStone = 0;
						int countBlank = 0;
						for(int l = i - 2; l >= 0; l--) {
							if(tableInt[l][j] == myStoneInt) {
								countMyStone++;
							}else if(tableInt[l][j] == 0) {
								countBlank++;
							}
							if(countMyStone > 0 && countBlank == 0) {
								inputablePlace[i][j] = 7;
								break;
							} else if(countMyStone == 0 && countBlank > 0) {
								break;
							}
						}
					}
				}
				
				//斜め　右下
				if(tableInt[i].length - j > 2 && tableInt.length - i > 2) {
					if(tableInt[i + 1][j + 1] == opponentStoneInt) {
						int countMyStone = 0;
						int countBlank = 0;
						for(int l = i + 2, m = j + 2; l < 8 && m < 8; l++, m++) {
							if(tableInt[l][m] == myStoneInt) {
								countMyStone++;
							}else if(tableInt[l][m] == 0) {
								countBlank++;
							}
							if(countMyStone > 0 && countBlank == 0) {
								inputablePlace[i][j] = 7;
								break;
							} else if(countMyStone == 0 && countBlank > 0) {
								break;
							}
						}
					}
				}
				
				//斜め　左上
				if(tableInt[i].length - j < 7 && tableInt.length - i < 7) {
					if(tableInt[i - 1][j - 1] == opponentStoneInt) {
						int countMyStone = 0;
						int countBlank = 0;
						for(int l = i - 2, m = j - 2; l >= 0 && m >= 0; l--, m--) {
							if(tableInt[l][m] == myStoneInt) {
								countMyStone++;
							}else if(tableInt[l][m] == 0) {
								countBlank++;
							}
							if(countMyStone > 0 && countBlank == 0) {
								inputablePlace[i][j] = 7;
								break;
							} else if(countMyStone == 0 && countBlank > 0) {
								break;
							}
						}
					}
				}
				
				//斜め　右上
				if(tableInt[i].length - j > 2 && tableInt.length - i < 7) {
					if(tableInt[i - 1][j + 1] == opponentStoneInt) {
						int countMyStone = 0;
						int countBlank = 0;
						for(int l = i - 2, m = j + 2; l >= 0 && m < 8; l--, m++) {
							if(tableInt[l][m] == myStoneInt) {
								countMyStone++;
							}else if(tableInt[l][m] == 0) {
								countBlank++;
							}
							if(countMyStone > 0 && countBlank == 0) {
								inputablePlace[i][j] = 7;
								break;
							} else if(countMyStone == 0 && countBlank > 0) {
								break;
							}
						}
					}
				}
				
				//斜め　左下
				if(tableInt[i].length - j < 7 && tableInt.length - i > 2) {
					if(tableInt[i + 1][j - 1] == opponentStoneInt) {
						int countMyStone = 0;
						int countBlank = 0;
						for(int l = i + 2, m = j - 2; l < 8 && m >= 0; l++, m--) {
							if(tableInt[l][m] == myStoneInt) {
								countMyStone++;
							}else if(tableInt[l][m] == 0) {
								countBlank++;
							}
							if(countMyStone > 0 && countBlank == 0) {
								inputablePlace[i][j] = 7;
								break;
							} else if(countMyStone == 0 && countBlank > 0) {
								break;
							}
						}
					}
				}
			}
			
		}
		return inputablePlace;
	}
	
	//入力値に従い盤面の内容を更新するメソッド
	public int[][] updateTableInt(int[][] tableInt, int turnCount, int[] selectedPlace){
		
		int[][] updatedTableInt = new int[tableInt.length][tableInt[0].length];
		
		int myStoneInt = 0;
		int opponentStoneInt = 0;
		if(turnCount % 2 == 1) {
			myStoneInt = 2;
			opponentStoneInt = 4;
		} else if(turnCount % 2 == 0) {
			myStoneInt = 4;
			opponentStoneInt = 2;
		}
		int row = selectedPlace[0];
		int column = selectedPlace[1];
		
		tableInt[row][column] = myStoneInt;
		
		//右
		if(tableInt[row].length - column > 2) {
			if(tableInt[row][column + 1] == opponentStoneInt) {
				int countOpponentStone = 1;
				for(int i = column + 2; i < 8; i++) {
					if(tableInt[row][i] == 0) {
						break;
					}else if(tableInt[row][i] == opponentStoneInt) {
						countOpponentStone++;
					}else if(tableInt[row][i] == myStoneInt) {
						for(int j = 1; j - 1 < countOpponentStone; j++) {
							tableInt[row][column + j] = myStoneInt;
						}
					}
				}
			}
		}
		
		//左
		if(tableInt[row].length - column < 7) {
			if(tableInt[row][column - 1] == opponentStoneInt) {
				int countOpponentStone = 1;
				for(int i = column - 2; i >= 0; i--) {
					if(tableInt[row][i] == 0) {
						break;
					}else if(tableInt[row][i] == opponentStoneInt) {
						countOpponentStone++;
					}else if(tableInt[row][i] == myStoneInt) {
						for(int j = 1; j - 1 < countOpponentStone ; j++) {
							tableInt[row][column - j] = myStoneInt;
						}
					}
				}
			}
		}
		
		//下
		if(tableInt.length - row > 2) {
			if(tableInt[row + 1][column] == opponentStoneInt) {
				int countOpponentStone = 1;
				for(int i = row + 2; i < 8; i++) {
					if(tableInt[i][column] == 0) {
						break;
					}else if(tableInt[i][column] == opponentStoneInt) {
						countOpponentStone++;
					}else if(tableInt[i][column] == myStoneInt) {
						for(int j = 1; j - 1 < countOpponentStone ; j++) {
							tableInt[row + j][column] = myStoneInt;
						}
					}
				}
			}
		}
		
		//上
		if(tableInt.length - row < 7) {
			if(tableInt[row - 1][column] == opponentStoneInt) {
				int countOpponentStone = 1;
				for(int i = row - 2; i >= 0; i--) {
					if(tableInt[i][column] == 0) {
						break;
					}else if(tableInt[i][column] == opponentStoneInt) {
						countOpponentStone++;
					}else if(tableInt[i][column] == myStoneInt) {
						for(int j = 1; j - 1 < countOpponentStone ; j++) {
							tableInt[row - j][column] = myStoneInt;
						}
					}
				}
			}
		}
		
		
		//斜め　右下
		if(tableInt[row].length - column > 2 && tableInt.length - row > 2) {
			if(tableInt[row + 1][column + 1] == opponentStoneInt) {
				int countOpponentStone = 1;
				for(int i = row + 2, j = column + 2; i < 8 && j < 8; i++, j++) {
					if(tableInt[i][j] == 0) {
						break;
					}else if(tableInt[i][j] == opponentStoneInt) {
						countOpponentStone++;
					}else if(tableInt[i][j] == myStoneInt) {
						for(int k = 1, l = 1; k - 1 < countOpponentStone && l - 1 < countOpponentStone; k++, l++) {
							tableInt[row + k][column + l] = myStoneInt;
						}
					}
				}
			}
		}
				
		//斜め　左上
		if(tableInt[row].length - column < 7 && tableInt.length - row < 7) {
			if(tableInt[row - 1][column - 1] == opponentStoneInt) {
				int countOpponentStone = 1;
				for(int i = row - 2, j = column - 2; i >= 0 && j >= 0; i--, j--) {
					if(tableInt[i][j] == 0) {
						break;
					}else if(tableInt[i][j] == opponentStoneInt) {
						countOpponentStone++;
					}else if(tableInt[i][j] == myStoneInt) {
						for(int k = 1, l = 1; k - 1 < countOpponentStone && l - 1 < countOpponentStone; k++, l++) {
							tableInt[row - k][column - l] = myStoneInt;
						}
					}
				}
			}
		}
				
		//斜め　右上
		if(tableInt[row].length - column > 2 && tableInt.length - row < 7) {
			if(tableInt[row - 1][column + 1] == opponentStoneInt) {
				int countOpponentStone = 1;
				for(int i = row - 2, j = column + 2; i >= 0 && j < 8; i--, j++) {
					if(tableInt[i][j] == 0) {
						break;
					}else if(tableInt[i][j] == opponentStoneInt) {
						countOpponentStone++;
					}else if(tableInt[i][j] == myStoneInt) {
						for(int k = 1, l = 1; k - 1 < countOpponentStone && l - 1 < countOpponentStone; k++, l++) {
							tableInt[row - k][column + l] = myStoneInt;
						}
					}
				}
			}
		}
		
		//斜め　左下
		if(tableInt[row].length - column < 7 && tableInt.length - row > 2) {
			if(tableInt[row + 1][column - 1] == opponentStoneInt) {
				int countOpponentStone = 1;
				for(int i = row + 2, j = column - 2; i < 8 && j >= 0; i++, j--) {
					if(tableInt[i][j] == 0) {
						break;
					}else if(tableInt[i][j] == opponentStoneInt) {
						countOpponentStone++;
					}else if(tableInt[i][j] == myStoneInt) {
						for(int k = 1, l = 1; k - 1 < countOpponentStone && l - 1 < countOpponentStone; k++, l++) {
							tableInt[row + k][column - l] = myStoneInt;
						}
					}
				}
			}
		}
		
		return tableInt;
	}
	
	//勝敗がついたか確認するメソッド
	public int[] checkResult(int[][] tableInt, int turnCount, int totalTurnCount) {
		int[] resultInt = new int[3];
		int countBlack = 0;
		int countWhite = 0;
		
		for(int i = 0; i < tableInt.length; i++) {
			for(int j = 0; j < tableInt[i].length; j++) {
				if(tableInt[i][j] == 2) {
					countBlack++;
				} else if(tableInt[i][j] == 4) {
					countWhite++;
				}
			}
		}
		
		if(turnCount < totalTurnCount) {
			if(countBlack == 0) {
				resultInt[0] = 2;
			} else if(countWhite == 0) {
				resultInt[0] = 1;
			} else {
				int inputableInt = DataProcess.checkInputable(tableInt, turnCount);
				if(inputableInt == 3) {
					turnCount = totalTurnCount;
				} else {
					resultInt[0] = 0;
				}
			}
		} 
		if(turnCount == totalTurnCount){
			if(countBlack > countWhite) {
				resultInt[0] = 1;
			} else if(countWhite > countBlack) {
				resultInt[0] = 2;
			} else {
				resultInt[0] = 3;
			}
		}
		
		resultInt[1] = countBlack;
		resultInt[2] = countWhite;
		
		return resultInt;
	}
	
	//CPUによるランダムな選択を行うメソッド
	public String selectByCpu(int[][] tableInt,int turnCount){
		
		int[][] inputablePlace = this.checkInputablePlace(tableInt, turnCount);
		String input = null;
		int count = 0;
		
		for(int i = 0; i < inputablePlace.length; i++) {
			for(int j = 0; j < inputablePlace[i].length; j++) {
				if(inputablePlace[i][j] == 7) {
					count++;
				}
			}
		}

		if(count > 0) {
			int random = new java.util.Random().nextInt(count);
			count = 0;
			
			select :
				for(int i = 0; i < inputablePlace.length; i++) {
					for(int j = 0; j < inputablePlace[i].length; j++) {
						if(inputablePlace[i][j] == 7) {
							if(random == count) {
								input = Integer.toString(i);
								input += Integer.toString(j);
								break select;
							} else {
								count++;
							}
						}
					}
				}
		}
		
		return input;
	}
	
}
