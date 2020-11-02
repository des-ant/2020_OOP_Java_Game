// package ghost;

// import processing.core.PApplet;
// import java.util.Scanner;

// public class Map {

//   // Helper method for opening file
// 	public static Scanner openFile(String filename) {
// 		try {
// 			File f = new File(filename);
// 			Scanner scan = new Scanner(f);
// 			return scan;
// 		} catch (FileNotFoundException e) {
// 			return null;
// 		}
// 	}

//   // Transform the input into a grid.
//   // Returns list of lists of Cells
//   public static List<List<Cell>> readBook(String filename) {
//     // If the file doesn't exist, return null.
//     if (filename == null) {
//       return null;
//     }
//     Scanner scan = openFile(filename);
//     if (scan == null) {
//       return null;
//     }
    
//     // If the file does exist, parse file

//     // Create list to hold list of cells
//     List listListCells = new ArrayList<List<Cell>>();

    

//     scan.nextLine();
//     while (scan.hasNextLine()) {
//       String line = scan.nextLine();
//       String[] lineInfo = line.split(",");
//       String fileSerial = lineInfo[0];
//       // Compare serial number
//       if (serialNumber.equals(fileSerial)) {
//         String fileTitle = lineInfo[1];
//         String fileAuthor = lineInfo[2];
//         String fileGenre = lineInfo[3];
//         Book fileBook = new Book(fileTitle, fileAuthor, fileGenre, fileSerial);
//         // Return the newly created book. 
//         return fileBook;
//       }
//     }
//     // Book not found in file
//     return null;
//   }

  
// }