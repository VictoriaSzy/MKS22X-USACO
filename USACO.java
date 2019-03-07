import java.util.* ;
import java.io.* ;

public class USACO {
  public static void main(String[] args) {
    System.out.println("**********************BRONZE**********************") ;
    System.out.println("We are now testing to see how the bronze problem is going!") ;
    try {
      bronze("testCases/makelake.1.in") ;
    } catch (FileNotFoundException e) {
      System.out.println("We couldn't find the file! UH OH") ;
    } catch (Exception e) {
      System.out.println("There's an exception being thrown but it's not caused by a missing file!") ;
    }
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
    int[] firstRow = new int[4] ;
    for (int i = 0 ; i < 4 ; i++) {
      firstRow[i] = Integer.parseInt(s.next()) ;
    }
    String line ;
    int numRows = firstRow[0] ;
    int numCols = firstRow[1] ;
    int[][] field = new int[numRows][numCols] ;
    //adding the values to the 2D array
    for (int r = 0 ; r < numRows ; r++) {
      for (int c = 0 ; c < numCols ; c++) {
      	field[r][c] = Integer.parseInt(s.next()) ;
      }
    }
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
      System.out.println("We found the max to be: " + max) ;
      // stomping happens here --> changing the elevations
    	for (int p = stompingR ; p < stompingR + 3 ; p++) {
    		for (int q = stompingC ; q < stompingC + 3 ; q++) {
    			if (max - field[p][q] < stompingD) {
            // if it's within the interval
            int diff = stompingD - max ;
            diff += field[p][q] ;
    				field[p][q] -= diff ;
            System.out.println("The position of row " + p + "and col " + q + "is now: " + field[p][q]) ;
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
    System.out.println("We have gone through all of the for loops and are about to return an answer!") ;
    System.out.println(ans * 72 * 72) ;
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
  // Silver level problem
  public static int silver(String filename) throws FileNotFoundException {
    File f = new File(filename) ;
    Scanner s = new Scanner(f) ;
    return 0 ;
  }
}
