/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clustering;

import Point.Point;
import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class DensityBased
{

    /**
     *
     * @param dataPoints
     * @param point
     * @param epsilon
     * @return
     */
    public static ArrayList<Point> withinNeighborhood(final ArrayList<Point> dataPoints, final Point point, final double epsilon)
    {
        ArrayList<Point> neighbors;
        neighbors = new ArrayList<Point>();
        for(Point dataPoint : dataPoints)
        {
            if(Point.euclideanDistance(point, dataPoint) < epsilon)
            {
                neighbors.add(dataPoint);
            }
        }
        return neighbors;
    }

    /**
     *
     * @param dataPoints
     * @param point
     * @param neighbors
     * @param cluster
     * @param epsilon
     * @param minimumPoints
     */
    public static void expandCluster(ArrayList<Point> dataPoints, final Point point, final ArrayList<Point> neighbors, ArrayList<Point> cluster, final double epsilon, final int minimumPoints)
    {
        ArrayList<Point> currentNeighbors;
        cluster.add(point);
        for(Point neighbor : neighbors)
        {
            dataPoints.remove(neighbor);
            currentNeighbors = DensityBased.withinNeighborhood(dataPoints, neighbor, epsilon);
            if(currentNeighbors.size() >= minimumPoints)
            {
                neighbors.addAll(currentNeighbors);
            }
        }
    }
}
