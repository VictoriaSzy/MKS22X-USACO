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
  ////////////////////////// SILVER ///////////////////////////////////////////////////////
  /* Silver level problem
  Given a map with '.'s for open pasture space
  and '*' for trees,
  calculate # of possible ways
  to travel from (R1, C1) to (R2, C2) in T seconds*/
  public static int silver(String filename) throws FileNotFoundException {
    File f = new File(filename) ;
    Scanner s = new Scanner(f) ;
    // read through first row to find out basic info (only 3 compared to bronze problem)
    String[] firstRow = s.nextLine().split(" ") ;
    int numRows = Integer.parseInt(firstRow[0]) ;
    int numCols = Integer.parseInt(firstRow[1]) ;
    if (numRows < 2 || numRows > 100 || numCols < 2 || numCols > 100) {
      throw new IllegalArgumentException("The number of rows and/or columns is not valid!") ;
    }
    int time = Integer.parseInt(firstRow[2]) ;
    if (time < 0 || time > 15) {
      throw new IllegalArgumentException("The time is greater than 15 seconds or less than 0, making it invalid!") ;
    }
    int[][] field = new int[numRows][numCols] ;
    for (int r = 0 ; r < numRows ; r++) {
      String line = s.nextLine() ;
      for (int c = 0 ; c < numCols ; c++) {
        if (line.charAt(c) == '*') field[r][c] = -1 ;
        else {
          field[r][c] = 0 ;
        }
      }
    }
    String[] lastRow = s.nextLine().split(" ") ;
    int startingRow = Integer.parseInt(lastRow[0]) - 1 ;
    int startingCol = Integer.parseInt(lastRow[1]) - 1 ;
    int finalRow = Integer.parseInt(lastRow[2]) - 1 ;
    int finalCol = Integer.parseInt(lastRow[3]) - 1 ;
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
    int tempR, tempC ;
    for (int r = 0 ; r < field.length ; r++) {
      for (int c = 0 ; c < field[0].length ; c++) {
        if (field[r][c] == -1) newField[r][c] = -1 ;
        else {
          int temp = 0 ;
          for (int x = 0 ; x < moves.length ; x++) {
            tempR = r + moves[x][0] ;
            tempC = c + moves[x][1] ;
            if (tempR > -1 && tempR < field.length && tempC > -1 && tempC < field[0].length && field[tempR][tempC] != -1) {
              // if it's in bounds
              temp += field[tempR][tempC] ;
            }
          }
          newField[r][c] = temp ;
        }
      }
    }
    ///////// end of for loop
    return newField ;
  }
}
