package numbersums;

import java.util.ArrayList;

public class Solver {
    
    public Solver() {
        
    }
    
    private void run() {
        ArrayList<String> lines = new ArrayList<>();
        FileUtils.load("resources/input.txt", lines);
        for (String line : lines) {
            System.out.println(line);
        }
        final int N = lines.size() - 2;
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
        for (int i = 0; i < N; i++) {
            System.out.println( "Possible solutions for line " + i + ":" );
            ArrayList<boolean[]> combinations = solve(matrix[ i ], rowSum[ i ]);
            for ( int k = 0; k < combinations.size(); k++ ) {
                boolean[] combination = combinations.get(k);
                boolean atLeastOne = false;
                for ( int j = 0; j < N; j++) {
                    if (combination[j]) {
                        if (atLeastOne) {
                            System.out.print(" + ");
                        }
                        System.out.print("a[ " + j + " ]");
                        usedInRows[ i ][ j ] = true;
                        atLeastOne = true;
                    }
                }
                if (atLeastOne) {
                    System.out.println();
                }
            }
        }
        
        for (int j = 0; j < N; j++) {
            System.out.println( "Possible solutions for column " + j + ":" );
            int[] column = new int[ N ];
            for( int i = 0; i < N; i++) {
                column[ i ] = matrix[ i ][ j ];
            }
            ArrayList<boolean[]> combinations = solve(column, columnSum[ j ]);
            for ( int k = 0; k < combinations.size(); k++ ) {
                boolean[] combination = combinations.get(k);
                boolean atLeastOne = false;
                for ( int i = 0; i < N; i++) {
                    if (combination[i]) {
                        if (atLeastOne) {
                            System.out.print(" + ");
                        }
                        System.out.print("a[ " + i + " ]");
                        usedInColumns[ i ][ j ] = true;
                        atLeastOne = true;
                    }
                }
                if (atLeastOne) {
                    System.out.println();
                }
            }
        }
        for ( int i = 0; i < N; i++ ) {
            for ( int j = 0; j < N; j++ ) {
                if ( j > 0 ) {
                    System.out.print( " ");
                }
                System.out.print(usedInRows[ i ][ j ] ? "O" : "X");
            }
            System.out.println();
        }
        System.out.println();
        for ( int i = 0; i < N; i++ ) {
            for ( int j = 0; j < N; j++ ) {
                if ( j > 0 ) {
                    System.out.print( " ");
                }
                System.out.print(usedInColumns[ i ][ j ] ? "O" : "X");
            }
            System.out.println();
        }
        System.out.println();
        for ( int i = 0; i < N; i++ ) {
            for ( int j = 0; j < N; j++ ) {
                if ( j > 0 ) {
                    System.out.print( " ");
                }
                System.out.print((usedInRows[ i ][ j ] && usedInColumns[ i ][ j ])? "O" : "X");
            }
            System.out.println();
        }
    }
    
    private ArrayList<boolean[]> solve(int[] a, int target) {
        ArrayList<boolean[]> combination = new ArrayList<>();
        for( int i = 0; i < a.length; i++ ) {
            if ( a[ i ] == target ) {
                // System.out.println( "a[ " + i + " ]");
                boolean[] array = new boolean[ a.length ];
                array[ i ] = true;
                combination.add(array);
            }
        }
        if (a.length >= 2) {
            for ( int i = 0; i < a.length - 1; i++ ) {
                for ( int j = i + 1; j < a.length; j++ ) {
                    if ( a[ i ] + a[ j ] == target ) {
                        // System.out.println( "a[ " + i + " ] + a[ " + j + " ]" );
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
                            // System.out.println( "a[ " + i + " ] + a[ " + j + " ] + a[ " + k + " ]" );
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
                                // System.out.println( "a[ " + i + " ] + a[ " + j + " ] + a[ " + k + " ] + a[ " + x + "]" );
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
                                    // System.out.println( "a[ " + i + " ] + a[ " + j + " ] + a[ " + k + " ] + a[ " + x + "] + a[ " + y + "]" );
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
                                        // System.out.println( "a[ " + i + " ] + a[ " + j + " ] + a[ " + k + " ] + a[ " + x + "] + a[ " + y + "] + a[ " + z + " ]" );
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
        return combination;
    }
    
    public static void main(String[] args) {
        new Solver().run();
    }

}
