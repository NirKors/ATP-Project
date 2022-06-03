package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

    String tempDirectoryPath = System.getProperty("java.io.tmpdir");
    int id;

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {

        Maze clientInfo;
        try {
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);


            clientInfo = (Maze) fromClient.readObject();

            // Does it exist in file?
            Solution sol = ReadFiles(clientInfo);
            if (sol == null) { // Doesn't exist.
                sol = SolveMaze(clientInfo);
                OutputAsFile(clientInfo, sol);
            }

            // Return solution.
            toClient.writeObject(sol);

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private Solution SolveMaze(Maze maze) {
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch solver = new BestFirstSearch();
        return solver.solve(searchableMaze);
    }

    private void OutputAsFile(Maze maze, Solution sol) throws IOException {

        int temp = id++;
        FileOutputStream f = new FileOutputStream(tempDirectoryPath + "\\maze_" + temp);
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(MyCompressorOutputStream.compress(maze.toByteArray()));
        o.writeObject(sol);
        o.flush();
        o.close();
    }

    /**
     * Compresses the maze's byte representation and compares it to files beginning with prefix "maze_". If the length of
     * the compared arrays is equal, continues to compare both arrays. If equal, returns solution stored in the file.
     * If no comparision is found, returns null.
     *
     * @param maze - Maze to compare
     * @return - Solution if found, else null.
     */
    private Solution ReadFiles(Maze maze) throws IOException {
        Solution sol = null;

        byte[] compressed = MyCompressorOutputStream.compress(maze.toByteArray());


        File dir = new File(tempDirectoryPath);
        File[] foundFiles = dir.listFiles((dir1, name) -> name.startsWith("maze_"));
        if (foundFiles == null) {
            id = 0;
            return null;
        }
        id = foundFiles.length;

        FileInputStream fi;
        ObjectInputStream oi;
        boolean flag;

        for (File file : foundFiles) {
            flag = true;
            fi = new FileInputStream(file);
            oi = new ObjectInputStream(fi);

            try {
                byte[] temp = (byte[]) oi.readObject();

                if (temp.length == compressed.length)
                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i] != compressed[i]) {
                            flag = false;
                            break;
                        }
                    }
                if (flag) {
                    sol = (Solution) oi.readObject();
                    break;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        return sol;
    }
}
