package numbersums;

import java.util.ArrayList;

public class Solver {
    
    public Solver() {
        
    }
    
    private void run() {
    	long before = System.currentTimeMillis();
        ArrayList<String> lines = new ArrayList<>();
        FileUtils.load("resources/input.txt", lines);
        final int N = lines.size() - 1;
        System.out.println("N = " + N);
        int[][] matrix = new int[N][N];
        boolean[][] usedInRows = new boolean[N][N];
        boolean[][] usedInColumns = new boolean[N][N];
        int[] rowSum = new int[N];
        int[] columnSum = new int[N];
        for ( int i = 0; i < N; i++ ) {
            String line = lines.get(i);
            String[] token = line.split("\\s");
            for ( int j = 0; j < N; j++ ) {
                matrix[ i ][ j ] = Integer.valueOf(token[ j ]);
            }
            rowSum[ i ] = Integer.valueOf( token[ N ] );
        }
        String lastLine = lines.get( N );
        String[] token = lastLine.split("\\s");
        for ( int i = 0; i < N; i++ ) {
            columnSum[ i ] = Integer.valueOf(token[ i ]);
        }
        ArrayList<ArrayList<boolean[]>> rowCombinations = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ArrayList<boolean[]> combinations = solve(matrix[ i ], rowSum[ i ]);
            for ( int k = 0; k < combinations.size(); k++ ) {
                boolean[] combination = combinations.get(k);
                for ( int j = 0; j < N; j++) {
                    if (combination[j]) {
                        usedInRows[ i ][ j ] = true;
                    }
                }
            }
            rowCombinations.add(combinations);
        }
        ArrayList<ArrayList<boolean[]>> columnCombinations = new ArrayList<>();
        for (int j = 0; j < N; j++) {
            int[] column = new int[ N ];
            for( int i = 0; i < N; i++) {
                column[ i ] = matrix[ i ][ j ];
            }
            ArrayList<boolean[]> combinations = solve(column, columnSum[ j ]);
            for ( int k = 0; k < combinations.size(); k++ ) {
                boolean[] combination = combinations.get(k);
                for ( int i = 0; i < N; i++) {
                    if (combination[i]) {
                        usedInColumns[ i ][ j ] = true;
                    }
                }
            }
            columnCombinations.add(combinations);
        }
        boolean[][] used = new boolean[ N ][ N ];
        for ( int i = 0; i < N; i++ ) {
            for ( int j = 0; j < N; j++ ) {
                used[ i ][ j ] = usedInRows[ i ][ j ] && usedInColumns[ i ][ j ];
            }
        }
        for (int i = 0; i < rowCombinations.size(); i++) { // for each row
        	ArrayList<boolean[]> combinations = rowCombinations.get( i );
        	for (int k = combinations.size() - 1; k >= 0; k--) { // for each combination of that row
        		boolean[] sequence = combinations.get( k );
        		boolean removeThisSequence = false;
        		for (int j = 0; j < N; j++) {
        			if (!used[ i ][ j ] && sequence[ j ]) {
        				removeThisSequence = true;
        			}
        		}
        		if (removeThisSequence) {
        			combinations.remove(k);
        		}
        	}
        }
        for (int j = 0; j < columnCombinations.size(); j++) { // for each row
        	ArrayList<boolean[]> combinations = columnCombinations.get( j );
        	for (int k = combinations.size() - 1; k >= 0; k--) { // for each combination of that row
        		boolean[] sequence = combinations.get( k );
        		boolean removeThisSequence = false;
        		for (int i = 0; i < N; i++) {
        			if (!used[ i ][ j ] && sequence[ i ]) {
        				removeThisSequence = true;
        			}
        		}
        		if (removeThisSequence) {
        			combinations.remove(k);
        		}
        	}
        }
        ArrayList<String> rowCandidates = new ArrayList<String>();
        ArrayList<String> columnCandidates = new ArrayList<String>();
        if (N == 6) {
	        rowCandidates = SolveFor6.solveRows(rowCombinations, matrix, columnSum);
	        columnCandidates = SolveFor6.solveColumns(columnCombinations, matrix, rowSum);
        }
        if (N == 7) {
	        rowCandidates = SolveFor7.solveRows(rowCombinations, matrix, columnSum);
	        columnCandidates = SolveFor7.solveColumns(columnCombinations, matrix, rowSum);
        }
        if (N == 8) {
	        rowCandidates = SolveFor8.solveRows(rowCombinations, matrix, columnSum);
	        columnCandidates = SolveFor8.solveColumns(columnCombinations, matrix, rowSum);
        }
        printSolution(rowCandidates, columnCandidates, N);
        long after = System.currentTimeMillis();
        double time = (double) (after - before) / 1000.0;
        System.out.println("elapsed time = " + time);
    }
    
    private void printSolution(ArrayList<String> rowCandidates, ArrayList<String> columnCandidates, int N) {
    	System.out.println("rowCandidates = " + rowCandidates.size());
        System.out.println("columnCandidates = " + columnCandidates.size());
        for (int i = 0; i < rowCandidates.size(); i++) {
        	for (int j = 0; j < columnCandidates.size(); j++) {
        		if (rowCandidates.get(i).equals(columnCandidates.get(j))) {
        			String solution = rowCandidates.get(i);
        			int count = 0;
        			for (int x = 0; x < N; x++) {
        				for (int y = 0; y < N; y++) {
        					if (y > 0) {
        						System.out.print(" ");
        					}
        					System.out.print(solution.charAt(count++));
        				}
        				System.out.println();
        			}
        		}
        	}
        	System.out.println();
        }
    }
    
    private ArrayList<boolean[]> solve(int[] a, int target) {
        ArrayList<boolean[]> combination = new ArrayList<>();
        for( int i = 0; i < a.length; i++ ) {
            if ( a[ i ] == target ) {
                boolean[] array = new boolean[ a.length ];
                array[ i ] = true;
                combination.add(array);
            }
        }
        if (a.length >= 2) {
            for ( int i = 0; i < a.length - 1; i++ ) {
                for ( int j = i + 1; j < a.length; j++ ) {
                    if ( a[ i ] + a[ j ] == target ) {
                        boolean[] array = new boolean[ a.length ];
                        array[ i ] = array[ j ] = true;
                        combination.add(array);
                    }
                }
            }
        }
        if (a.length >= 3) {
            for ( int i = 0; i < a.length - 2; i++ ) {
                for ( int j = i + 1; j < a.length - 1; j++ ) {
                    for ( int k = j + 1; k < a.length; k++ ) {
                        if ( a[ i ] + a[ j ] + a[ k ] == target ) {
                            boolean[] array = new boolean[ a.length ];
                            array[ i ] = array[ j ] = array[ k ] = true;
                            combination.add(array);
                        }
                    }
                }
            }
        }
        if (a.length >= 4) {
            for ( int i = 0; i < a.length - 3; i++ ) {
                for ( int j = i + 1; j < a.length - 2; j++ ) {
                    for ( int k = j + 1; k < a.length - 1; k++ ) {
                        for ( int x = k + 1; x < a.length; x++ ) {
                            if ( a[ i ] + a[ j ] + a[ k ] + a[ x ] == target ) {
                                boolean[] array = new boolean[ a.length ];
                                array[ i ] = array[ j ] = array[ k ] = array[ x ] = true;
                                combination.add(array);
                            }
                        }
                    }
                }
            }
        }
        if (a.length >= 5) {
            for ( int i = 0; i < a.length - 4; i++ ) {
                for ( int j = i + 1; j < a.length - 3; j++ ) {
                    for ( int k = j + 1; k < a.length - 2; k++ ) {
                        for ( int x = k + 1; x < a.length - 1; x++ ) {
                            for ( int y = x + 1; y < a.length; y++ ) {
                                if ( a[ i ] + a[ j ] + a[ k ] + a[ x ] + a[ y ] == target ) {
                                    boolean[] array = new boolean[ a.length ];
                                    array[ i ] = array[ j ] = array[ k ] = array[ x ] = array[ y ] = true;
                                    combination.add(array);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (a.length >= 6) {   
            for ( int i = 0; i < a.length - 5; i++ ) {
                for ( int j = i + 1; j < a.length - 4; j++ ) {
                    for ( int k = j + 1; k < a.length - 3; k++ ) {
                        for ( int x = k + 1; x < a.length - 2; x++ ) {
                            for ( int y = x + 1; y < a.length - 1; y++ ) {
                                for ( int z = y + 1; z < a.length; z++ ) {
                                    if ( a[ i ] + a[ j ] + a[ k ] + a[ x ] + a[ y ] + a[ z ] == target ) {
                                        boolean[] array = new boolean[ a.length ];
                                        array[ i ] = array[ j ] = array[ k ] = array[ x ] = array[ y ] = array[ z ] = true;
                                        combination.add(array);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (a.length >= 7) {   
            for ( int i = 0; i < a.length - 6; i++ ) {
                for ( int j = i + 1; j < a.length - 5; j++ ) {
                    for ( int k = j + 1; k < a.length - 4; k++ ) {
                        for ( int x = k + 1; x < a.length - 3; x++ ) {
                            for ( int y = x + 1; y < a.length - 2; y++ ) {
                                for ( int z = y + 1; z < a.length - 1; z++ ) {
                                    for ( int w = z + 1; w < a.length; w++ ) {
                                        if ( a[ i ] + a[ j ] + a[ k ] + a[ x ] + a[ y ] + a[ z ] + a[ w ] == target ) {
                                            boolean[] array = new boolean[ a.length ];
                                            array[ i ] = array[ j ] = array[ k ] = array[ x ] = array[ y ] = array[ z ] = array[ w ] = true;
                                            combination.add(array);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (a.length >= 8) {   
            for ( int i = 0; i < a.length - 7; i++ ) {
                for ( int j = i + 1; j < a.length - 6; j++ ) {
                    for ( int k = j + 1; k < a.length - 5; k++ ) {
                        for ( int x = k + 1; x < a.length - 4; x++ ) {
                            for ( int y = x + 1; y < a.length - 3; y++ ) {
                                for ( int z = y + 1; z < a.length - 2; z++ ) {
                                    for ( int w = z + 1; w < a.length - 1; w++ ) {
                                    	for ( int v = w + 1; v < a.length; v++ ) {
	                                        if ( a[ i ] + a[ j ] + a[ k ] + a[ x ] + a[ y ] + a[ z ] + a[ w ] + a[ v ] == target ) {
	                                            boolean[] array = new boolean[ a.length ];
	                                            array[ i ] = array[ j ] = array[ k ] = array[ x ] = array[ y ] = array[ z ] = array[ w ] = array[ v ] = true;
	                                            combination.add(array);
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
        return combination;
    }
    
    public static void main(String[] args) {
        new Solver().run();
    }

}

