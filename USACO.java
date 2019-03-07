import java.util.* ;
import java.io.* ;

public class USACO{
  // Bronze level problem
  public static int bronze(String filename) throws FileNotFoundException {
    File f = new File(filename) ;
    Scanner s = new Scanner(f) ;
    while (s.hasNextLine()) {
      String lin = s.nextLine() ;
      System.out.println(lin) ;
    }
    /*int[] firstRow = new int[4] ;
    for (int i = 0 ; i < 4 ; i++) {
      firstRow[i] = Integer.parseInt(s.next()) ;
    }
    String line ;
    int[][] field = new int[firstRow[0]][firstRow[1]] ;
    for (int i = 0 ; s.hasNextLine() ; i++) {
      line = s.nextLine() ;
      if (firstRow.length > 0 && i >= firstRow[0] + 1) {
        // we have finished scanning through the field and now we can read the stomping instructions
        if (i == firstRow[0] + 1) {
          // we are receiving the first directions and we need to initiate the array holding the directions correctly
        }
        else {
        }
      }
    }
    field = new int[firstRow[0]][firstRow[1]] ;
    ////////////////////////////////////////////////////
    Scanner ss = new Scanner(f) ;
    while (s.hasNextLine()) {

    }
    return 0 ;*/
  }

  public static String toString(int[][] a) {
    String res = "" ;
    for (int r = 0 ; r < a.length ; r++) {
      for (int c = 0 ; c < a[0].length ; c++) {
        res += a[r][c] + " " ;
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
