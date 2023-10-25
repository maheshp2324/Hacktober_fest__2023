//Leetcode hard - Sudoku Solver


class Solution {
    boolean flag = false;
    public void solveSudoku(char[][] board) {
        //backtracking solution
        int n = board.length;
        int[][] rows = new int[n][n+1];
        int[][] cols = new int[n][n+1];
        int[][] quad = new int[n][n+1];

        //do a full table scan
        for (int i = 0 ; i < n; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] != '.'){
                    int num = board[i][j] - '0';
                    rows[i][num] = 1;
                    cols[j][num] = 1;
                    int q = (i/3) * 3 + j/3;
                    quad[q][num] = 1;
                }
            }
        }


        boolean result = dfs(0,0,board,rows,cols,quad);

    }

    public boolean dfs(int i, int j,char[][] board,int[][] rows,int[][] cols,int[][] quad){
        if (i == 9){
            //flag = true;
            return true;
        }else if(j == 9){
            return dfs(i+1,0,board,rows,cols,quad);
        }

        if (board[i][j] == '.'){
            int q = (i/3)*3 + j/3;
            for (int k = 1;k < board.length+1; k++){
                if (rows[i][k] + cols[j][k] + quad[q][k] == 0){
                    //place this number and move forward
                    board[i][j] = (char)(k+'0');
                    rows[i][k] = 1;
                    cols[j][k] = 1;
                    quad[q][k] = 1;
                    if (dfs(i,j+1,board,rows,cols,quad)){
                        return true;
                    }else{
                        board[i][j] = '.';
                        rows[i][k] = 0;
                        cols[j][k] = 0;
                        quad[q][k] = 0;
                    }
                }
            }
        }else{
            return dfs(i,j+1,board,rows,cols,quad);
        }

        return false;
    }

}
