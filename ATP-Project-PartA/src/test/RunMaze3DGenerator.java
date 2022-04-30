package test;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;


public class RunMaze3DGenerator {
    public static void main(String[] args) {
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        System.out.println(mg.measureAlgorithmTimeMillis(500, 500, 500));
    }
}
