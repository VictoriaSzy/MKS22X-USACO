import java.util.* ;
import java.io.* ;

public class USACO {
  public static void main(String[] args) {
    System.out.println("**********************BRONZE**********************") ;
    System.out.println("We are now testing to see how the bronze problem is going!") ;
    try {
      if (args.length == 0) {
        System.out.println(bronze("testCases/makelake.1.in")) ;
      }
      else {
        bronze(args[0]) ;
      }
    } catch (FileNotFoundException e) {
      System.out.println("We couldn't find the file! UH OH") ;
    } catch (Exception e) {
      System.out.println("There's an exception being thrown but it's not caused by a missing file!") ;
      System.out.println("Here is the specific exception: \n" + e) ;
    }
    System.out.println("**********************SILVER**********************") ;
    System.out.println("We are now testing to see how the silver problem is going!") ;
  }
  // Bronze level problem
  public static int bronze(String filename) throws FileNotFoundException {
    File f = new File(filename) ;
    Scanner s = new Scanner(f) ;
    /*
    while (s.hasNextLine()) {
      String lin = s.nextLine() ;
      System.out.println(lin) ;
    }
    */
    // read through first row to find out basic info
    int[] firstRow = new int[4] ;
    for (int i = 0 ; i < 4 ; i++) {
      firstRow[i] = Integer.parseInt(s.next()) ;
    }
    int numRows = firstRow[0] ;
    int numCols = firstRow[1] ;
    if (numRows < 3 || numRows > 100 || numCols < 3 || numCols > 100) {
      throw new IllegalArgumentException("The number of rows and/or columns is not valid!") ;
    }
    int[][] field = new int[numRows][numCols] ;
    String line ;
    //adding the values to the 2D array
    for (int r = 0 ; r < numRows ; r++) {
      for (int c = 0 ; c < numCols ; c++) {
      	field[r][c] = Integer.parseInt(s.next()) ;
      }
    }
    //System.out.println(toString(field)) ;
    // finding and doing the stomping directions
    for (int a = 0 ; a < firstRow[3] ; a++) {
    	int stompingR = Integer.parseInt(s.next()) - 1 ;
    	int stompingC = Integer.parseInt(s.next()) - 1 ;
    	int stompingD = Integer.parseInt(s.next()) ;
    	int max = 0 ;
      // finding max elevation
    	for (int m = stompingR ; m < stompingR + 3 ; m++) {
        for (int n = stompingC ; n < stompingC + 3 ; n++) {
          if (field[m][n] > max) max = field[m][n] ;
        }
      }
      //System.out.println("We found the max to be: " + max) ;
      // stomping happens here --> changing the elevations
    	for (int p = stompingR ; p < stompingR + 3 ; p++) {
    		for (int q = stompingC ; q < stompingC + 3 ; q++) {
    			if (max - field[p][q] < stompingD) {
            // if it's within the interval
            int diff = stompingD - max ;
            diff += field[p][q] ;
    				field[p][q] -= diff ;
            //System.out.println("The position of row " + p + "and col " + q + "is now: " + field[p][q]) ;
    			}
    		}
    	}
    }
    // final part: calculating the ans
    int ans = 0 ;
    for (int ro = 0 ; ro < numRows ; ro++) {
      for (int co = 0 ; co < numCols ; co++) {
        if (field[ro][co] < firstRow[2]) ans += firstRow[2] - field[ro][co] ;
      }
    }
    //System.out.println("We have gone through all of the for loops and are about to return an answer!") ;
    //System.out.println(ans * 72 * 72) ;
    return ans * 72 * 72 ;
  }

  public static String toString(int[][] ar) {
    String res = "" ;
    for (int r = 0 ; r < ar.length ; r++) {
      for (int c = 0 ; c < ar[0].length ; c++) {
        res += ar[r][c] + " " ;
      }
      res += "\n" ;
    }
    return res ;
  }

  /* Silver level problem
  Given a map with '.'s for open pasture space
  and '*' for trees,
  calculate # of possible ways
  to travel from (R1, C1) to (R2, C2) in T seconds*/
  public static int silver(String filename) throws FileNotFoundException {
    File f = new File(filename) ;
    Scanner s = new Scanner(f) ;
    // read through first row to find out basic info (only 3 compared to bronze problem)
    int[] firstRow = new int[3] ;
    for (int i = 0 ; i < 3 ; i++) {
      firstRow[i] = Integer.parseInt(s.next()) ;
    }
    int numRows = firstRow[0] ;
    int numCols = firstRow[1] ;
    if (numRows < 2 || numRows > 100 || numCols < 2 || numCols > 100) {
      throw new IllegalArgumentException("The number of rows and/or columns is not valid!") ;
    }
    int time = firstRow[2] ;
    if (time < 0 || time > 15) {
      throw new IllegalArgumentException("The time is greater than 15 seconds or less than 0, making it invalid!") ;
    }
    s.nextLine() ;
    int[][] field = new int[numRows][numCols] ;
    for (int r = 0; r < numRows ; r++) {
      String line = s.nextLine() ;
      for (int c = 0 ; c < numCols ; c++) {
        if (line.charAt(c) == '*') field[r][c] = -1 ;
        else {
          field[r][c] = 0 ;
        }
      }
    }
    int[] lastRow = new int[4] ;
    for (int a = 0 ; a < 4 ; a++) {
      lastRow[a] = Integer.parseInt(s.next()) ;
    }
    int startingRow = lastRow[0] ;
    int startingCol = lastRow[1] ;
    int finalRow = lastRow[2] ;
    int finalCol = lastRow[3] ;
    // finished finding out basic info
    field[startingRow][startingCol] = 1 ; //starting spot
    for (int i = 0 ; i < time ; i++) {
      field = update(field) ; //updating the field
    }
    return field[finalRow][finalCol] ;
  }
  public static int[][] update(int[][] field) {
    // this helps update the field
    int[][] moves = { {0,1}, {0,-1}, {1,0}, {-1,0} } ;
    int[][] newField = new int[field.length][field[0].length] ;
    for (int r = 0 ; r < field.length ; r++) {
      for (int c = 0 ; c < field[0].length ; c++) {
        if (field[r][c] != -1) {
          int sum = 0 ;
          for (int i = 0 ; i < 4 ; i ++) {
            int tempR = r + moves[i][0] ;
            int tempC = c + moves[i][1] ;
            if (tempR < field.length && tempR > -1 && tempC < field[0].length && tempC > -1 && field[tempR][tempC] != -1) {
              // if it's in bounds, then we can add it to the sum
              sum += field[tempR][tempC] ;
            }
          }
          newField[r][c] = sum ;
        }
        else {
          // we also make the new field have -1 at this spot
          newField[r][c] = -1;
        }
      }
    }
    return newField ;
  }
}
