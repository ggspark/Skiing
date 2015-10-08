/*  
    Author: Gaurav Gupta
    Date:   Oct 7, 2015
 */

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.util.ArrayList;

public class Main {

    static PathNode [][] input; //Input from file
    static PathNode longestPath; //To store the longest and steepest path

    public static void main(String[] args) throws Exception {
        ParserdoubtR2 sc = new ParserdoubtR2(System.in);//Fast input reader
        PrintWriter pw = new PrintWriter(System.out);//Fast output writer

        //Initializations
        int N = sc.nextInt();
        int M = sc.nextInt();
        input = new PathNode[N][M];
        longestPath = new PathNode();

        //Read file
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                input[i][j] = new PathNode(i,j,sc.nextInt());
            }
        }

        //Find longest and steepest path for each element using DP
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                PathNode path = findLongestSteepest(i, j);
                if (longestPath.compareTo(path) < 0) {
                    longestPath = path.copy();
                }
            }
        }
        pw.println(longestPath.toString());
        pw.close();
    }

    //Recursive method to find the longest and steepest path
    public static PathNode findLongestSteepest(int x, int y) {
        if (input[x][y].visited) {
            return input[x][y];
        }

        ArrayList<PathNode> sideNodes = getAdjacent(x, y);
        PathNode max = new PathNode();
        if (!sideNodes.isEmpty()) {
            for (PathNode n : sideNodes) {
                PathNode path = findLongestSteepest(n.x, n.y).copy();
                path.maxDistance = path.maxDistance + 1;
                path.slope = path.slope + (input[x][y].elevation - input[n.x][n.y].elevation) ;
                if (max.compareTo(path) < 0) {
                    max = path.copy();
                }
            }
        }

        input[x][y].maxDistance = max.maxDistance;
        input[x][y].slope = max.slope;
        input[x][y].visited = true;

        return input[x][y];
    }


    //Get the adjacent nodes which have lesser elevation
    public static ArrayList<PathNode> getAdjacent(int x, int y) {
        ArrayList<PathNode> list = new ArrayList<PathNode>();

        // x-1, y
        if (x > 0 && input[x - 1][y].elevation < input[x][y].elevation) {
            list.add(input[x - 1][y]);
        }

        // x+1, y
        if (x < input.length - 1 && input[x + 1][y].elevation < input[x][y].elevation) {
            list.add(input[x + 1][y]);
        }

        // x, y-1
        if (y > 0 && input[x][y - 1].elevation < input[x][y].elevation) {
            list.add(input[x][y - 1]);
        }

        // x, y+1
        if (y < input[0].length - 1 && input[x][y + 1].elevation < input[x][y].elevation) {
            list.add(input[x][y + 1]);
        }

        return list;
    }


    //Input reader
    private static class ParserdoubtR2 {
        final private int BUFFER_SIZE = 1 << 19;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public ParserdoubtR2(InputStream in) {
            din = new DataInputStream(in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String nextString() throws Exception {
            StringBuffer sb = new StringBuffer("");
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                sb.append((char) c);
                c = read();
            } while (c > ' ');
            return sb.toString();
        }

        public char nextChar() throws Exception {
            byte c = read();
            while (c <= ' ')
                c = read();
            return (char) c;
        }

        public int nextInt() throws Exception {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = c == '-';
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
                c = read();
            } while (c > ' ');
            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws Exception {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = c == '-';
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
                c = read();
            } while (c > ' ');
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws Exception {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws Exception {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }
}
