import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        // YOUR CODE HERE
        // We use three Lists of HashSets to track seen numbers.
        // rowList.get(i) holds numbers seen in row 'i'
        List<HashSet<Integer>> rowList = new ArrayList<>();
        List<HashSet<Integer>> colList = new ArrayList<>();
        List<HashSet<Integer>> boxList = new ArrayList<>();
        
        // Initialize the 9 sets for rows, cols, and boxes
        for (int i = 0; i < 9; i++) {
            rowList.add(new HashSet<>());
            colList.add(new HashSet<>());
            boxList.add(new HashSet<>());
        }
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Skip empty cells
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    
                    // 1. Check Row: If 'num' is already in row 'i', it's invalid
                    if (rowList.get(i).contains(num)) {
                        return false;
                    }
                    rowList.get(i).add(num);
                    
                    // 2. Check Column: If 'num' is already in col 'j', it's invalid
                    if (colList.get(j).contains(num)) {
                        return false;
                    }
                    colList.get(j).add(num);
                    
                    // 3. Check 3x3 Box: 
                    // i / 3 gives the "box row" (0, 1, or 2)
                    // j / 3 gives the "box col" (0, 1, or 2)
                    // (box_row * 3) + box_col flatterns this into a 0-8 index.
                    int boxIndex = (i / 3) * 3 + (j / 3);
                    if (boxList.get(boxIndex).contains(num)) {
                        return false;
                    }
                    boxList.get(boxIndex).add(num);
                }
            }
        }
        return true;
        
        /* 
         * PRO-TIP (The O(1) space optimization using raw Booleans instead of objects):
         * Instead of HashSets, you can use:
         * boolean[][] rows = new boolean[9][9];
         * boolean[][] cols = new boolean[9][9];
         * boolean[][] boxes = new boolean[9][9];
         * 
         * Then just check: if (rows[i][num - 1]) return false; else rows[i][num - 1] = true;
         * This uses virtually no memory and avoids object creation overhead!
         */
    }

    public static void main(String[] args) {
        ValidSudoku solution = new ValidSudoku();

        // Test Case 1: Valid board
        char[][] board1 = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        System.out.println("Test 1 (expect true): " + solution.isValidSudoku(board1));

        // Test Case 2: Invalid board (duplicate 8 in box)
        char[][] board2 = {
                { '8', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        System.out.println("Test 2 (expect false): " + solution.isValidSudoku(board2));
    }
}
