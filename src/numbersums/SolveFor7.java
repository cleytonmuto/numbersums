package numbersums;

import java.util.ArrayList;

public class SolveFor7 {
	
	public SolveFor7() {
		
	}
	
	public static ArrayList<String> solveRows(ArrayList<ArrayList<boolean[]>> rowCombinations, int[][] matrix, int[] columnSum) {
		int N = columnSum.length;
		ArrayList<String> rowCandidates = new ArrayList<>();
        for (int a = 0; a < rowCombinations.size() - 6; a++) {
        	ArrayList<boolean[]> combinationsA = rowCombinations.get(a);
        	for (int a1 = 0; a1 < combinationsA.size(); a1++) {
        		boolean[] sequenceA = combinationsA.get(a1);
        		for (int b = a + 1; b < rowCombinations.size() - 5; b++) {
                	ArrayList<boolean[]> combinationsB = rowCombinations.get(b);
                	for (int b1 = 0; b1 < combinationsB.size(); b1++) {
                		boolean[] sequenceB = combinationsB.get(b1);
                		for (int c = b + 1; c < rowCombinations.size() - 4; c++) {
                        	ArrayList<boolean[]> combinationsC = rowCombinations.get(c);
                        	for (int c1 = 0; c1 < combinationsC.size(); c1++) {
                        		boolean[] sequenceC = combinationsC.get(c1);
                        		for (int d = c + 1; d < rowCombinations.size() - 3; d++) {
                                	ArrayList<boolean[]> combinationsD = rowCombinations.get(d);
                                	for (int d1 = 0; d1 < combinationsD.size(); d1++) {
                                		boolean[] sequenceD = combinationsD.get(d1);
                                		for (int e = d + 1; e < rowCombinations.size() - 2; e++) {
                                        	ArrayList<boolean[]> combinationsE = rowCombinations.get(e);
                                        	for (int e1 = 0; e1 < combinationsE.size(); e1++) {
                                        		boolean[] sequenceE = combinationsE.get(e1);
                                        		for (int f = e + 1; f < rowCombinations.size() - 1; f++) {
                                                	ArrayList<boolean[]> combinationsF = rowCombinations.get(f);
                                                	for (int f1 = 0; f1 < combinationsF.size(); f1++) {
                                                		boolean[] sequenceF = combinationsF.get(f1);
                                                		for (int g = f + 1; g < rowCombinations.size(); g++) {
                                                        	ArrayList<boolean[]> combinationsG = rowCombinations.get(g);
                                                        	for (int g1 = 0; g1 < combinationsG.size(); g1++) {
                                                        		boolean[] sequenceG = combinationsG.get(g1);
                                                        		boolean[][] candidateMatrix = new boolean[N][N];
                                                        		for (int y = 0; y < N; y++) {
                                                        			candidateMatrix[0][y] = sequenceA[y];
                                                        		}
                                                        		for (int y = 0; y < N; y++) {
                                                        			candidateMatrix[1][y] = sequenceB[y];
                                                        		}
                                                        		for (int y = 0; y < N; y++) {
                                                        			candidateMatrix[2][y] = sequenceC[y];
                                                        		}
                                                        		for (int y = 0; y < N; y++) {
                                                        			candidateMatrix[3][y] = sequenceD[y];
                                                        		}
                                                        		for (int y = 0; y < N; y++) {
                                                        			candidateMatrix[4][y] = sequenceE[y];
                                                        		}
                                                        		for (int y = 0; y < N; y++) {
                                                        			candidateMatrix[5][y] = sequenceF[y];
                                                        		}
                                                        		for (int y = 0; y < N; y++) {
                                                        			candidateMatrix[6][y] = sequenceG[y];
                                                        		}
                                                        		boolean allOkay = true;
                                                        		for (int y = 0; y < N; y++) {
                                                        			int sum = 0;
                                                        			for (int x = 0; x < N; x++) {
                                                        				if (candidateMatrix[x][y]) {
                                                        					sum += matrix[x][y];
                                                        				}
                                                        			}
                                                        			if (sum != columnSum[y]) {
                                                    					allOkay = false;
                                                    				}
                                                        		}
                                                        		if (allOkay) {
                                                        			String word = "";
                                                        			for (int x = 0; x < N; x++) {
                                                        				for (int y = 0; y < N; y++) {
                                                        					if (candidateMatrix[x][y]) {
                                                        						word += "X";
                                                        					}
                                                        					else {
                                                        						word += "O";
                                                        					}
                                                        				}
                                                        			}
                                                        			rowCandidates.add(word);
                                                        		}
                                                        	}
                                                		}
                                                	}
                                        		}
                                        	}
                                		}
                                	}
                        		}
                        	}
                		}
                	}
        		}
        	}
        }
        return rowCandidates;
	}
	
	public static ArrayList<String> solveColumns(ArrayList<ArrayList<boolean[]>> columnCombinations, int[][] matrix, int[] rowSum) {
		int N = rowSum.length;
		ArrayList<String> columnCandidates = new ArrayList<>();
        for (int a = 0; a < columnCombinations.size() - 6; a++) {
        	ArrayList<boolean[]> combinationsA = columnCombinations.get(a);
        	for (int a1 = 0; a1 < combinationsA.size(); a1++) {
        		boolean[] sequenceA = combinationsA.get(a1);
        		for (int b = a + 1; b < columnCombinations.size() - 5; b++) {
                	ArrayList<boolean[]> combinationsB = columnCombinations.get(b);
                	for (int b1 = 0; b1 < combinationsB.size(); b1++) {
                		boolean[] sequenceB = combinationsB.get(b1);
                		for (int c = b + 1; c < columnCombinations.size() - 4; c++) {
                        	ArrayList<boolean[]> combinationsC = columnCombinations.get(c);
                        	for (int c1 = 0; c1 < combinationsC.size(); c1++) {
                        		boolean[] sequenceC = combinationsC.get(c1);
                        		for (int d = c + 1; d < columnCombinations.size() - 3; d++) {
                                	ArrayList<boolean[]> combinationsD = columnCombinations.get(d);
                                	for (int d1 = 0; d1 < combinationsD.size(); d1++) {
                                		boolean[] sequenceD = combinationsD.get(d1);
                                		for (int e = d + 1; e < columnCombinations.size() - 2; e++) {
                                        	ArrayList<boolean[]> combinationsE = columnCombinations.get(e);
                                        	for (int e1 = 0; e1 < combinationsE.size(); e1++) {
                                        		boolean[] sequenceE = combinationsE.get(e1);
                                        		for (int f = e + 1; f < columnCombinations.size() - 1; f++) {
                                                	ArrayList<boolean[]> combinationsF = columnCombinations.get(f);
                                                	for (int f1 = 0; f1 < combinationsF.size(); f1++) {
                                                		boolean[] sequenceF = combinationsF.get(f1);
                                                		for (int g = f + 1; g < columnCombinations.size(); g++) {
                                                        	ArrayList<boolean[]> combinationsG = columnCombinations.get(g);
                                                        	for (int g1 = 0; g1 < combinationsG.size(); g1++) {
                                                        		boolean[] sequenceG = combinationsG.get(g1);
                                                        		boolean[][] candidateMatrix = new boolean[N][N];
                                                        		for (int x = 0; x < N; x++) {
                                                        			candidateMatrix[x][0] = sequenceA[x];
                                                        		}
                                                        		for (int x = 0; x < N; x++) {
                                                        			candidateMatrix[x][1] = sequenceB[x];
                                                        		}
                                                        		for (int x = 0; x < N; x++) {
                                                        			candidateMatrix[x][2] = sequenceC[x];
                                                        		}
                                                        		for (int x = 0; x < N; x++) {
                                                        			candidateMatrix[x][3] = sequenceD[x];
                                                        		}
                                                        		for (int x = 0; x < N; x++) {
                                                        			candidateMatrix[x][4] = sequenceE[x];
                                                        		}
                                                        		for (int x = 0; x < N; x++) {
                                                        			candidateMatrix[x][5] = sequenceF[x];
                                                        		}
                                                        		for (int x = 0; x < N; x++) {
                                                        			candidateMatrix[x][6] = sequenceG[x];
                                                        		}
                                                        		boolean allOkay = true;
                                                        		for (int x = 0; x < N; x++) {
                                                        			int sum = 0;
                                                        			for (int y = 0; y < N; y++) {
                                                        				if (candidateMatrix[x][y]) {
                                                        					sum += matrix[x][y];
                                                        				}
                                                        			}
                                                        			if (sum != rowSum[x]) {
                                                    					allOkay = false;
                                                    				}
                                                        		}
                                                        		if (allOkay) {
                                                        			String word = "";
                                                        			for (int x = 0; x < N; x++) {
                                                        				for (int y = 0; y < N; y++) {
                                                        					if (candidateMatrix[x][y]) {
                                                        						word += "X";
                                                        					}
                                                        					else {
                                                        						word += "O";
                                                        					}
                                                        				}
                                                        			}
                                                        			columnCandidates.add(word);
                                                        		}
                                                        	}
                                                		}
                                                	}
                                        		}
                                        	}
                                		}
                                	}
                        		}
                        	}
                		}
                	}
        		}
        	}
        }
        return columnCandidates;
	}

}
