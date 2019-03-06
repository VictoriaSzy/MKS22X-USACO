import java.util.* ;
import java.io.* ;

public class USACO{
  // Bronze level problem
  private int[][] field ;
  public static int bronze(String filename) throws FileNotFoundException {
    File f = new File(filename) ;
    Scanner s = new Scanner(f) ;
    int len = 0 ;
    int w = s.length() ;
    String line ;
    int[] firstRow ;
    int[][] stompingDirections ;
    // this first while loop helps me determine
    for (int i = 0 ; s.hasNextLine() ; i++) {
      if (i == 0) {
        firstRow = line.split(" ") ;
      }
      if (firstRow.length > 0 && i >= firstRow[0] + 1) {
        // we have finished scanning through the field and now we can read the stomping instructions

      }
      line = s.nextLine() ;
    }
    field = new int[firstRow[0]][firstRow[1]] ;
    ////////////////////////////////////////////////////
    Scanner ss = new Scanner(f) ;
    while (s.hasNextLine()) {

    }
    return 0 ;
  }
  // Silver level problem
  public static int silver(String filename) throws FileNotFoundException {
    File f = new File(filename) ;
    Scanner s = new Scanner(f) ;
    return 0 ;
  }
}
