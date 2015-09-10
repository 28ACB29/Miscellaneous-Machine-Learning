/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clustering;

import Point.Point;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Arthur C. Baroi
 */
public class KMeans
{
    public static int[] kMeansClustering(final ArrayList<Point> pointSet, final int k)
    {
        ArrayList<Point> temporaryPointSet;
        int index;
        Point[] centroids;
        final int length;
        int[] clusters;
        Point point;
        double distance;
        double similarity;
        boolean changed;
        Point mean;
        int count;
        length = pointSet.size();
        temporaryPointSet = new ArrayList<Point>(pointSet);
        centroids = new Point[k];
        clusters = new int[length];
        for(int i = 0; i < k; i++)
        {
            index = (int) (Math.random() * (length - i));
            centroids[i] = temporaryPointSet.get(index);
            temporaryPointSet.remove(centroids[i]);
        }
        for(int i = 0; i < length; i++)
        {
            point = pointSet.get(i);
            distance = Integer.MAX_VALUE;
            index = -1;
            for(int j = 0; j < k; j++)
            {
                similarity = Point.manhattanDistance(point, centroids[j]);
                if(similarity < distance && !point.equals(centroids[j]))
                {
                    distance = similarity;
                    index = j;
                }
            }
            clusters[i] = index;
        }
        changed = true;
        while(changed)
        {
            changed = false;
            for(int i = 0; i < length; i++)
            {
                point = pointSet.get(i);
                distance = Integer.MAX_VALUE;
                index = -1;
                for(int j = 0; j < k; j++)
                {
                    similarity = Point.manhattanDistance(point, centroids[j]);
                    if(similarity < distance && !point.equals(centroids[j]))
                    {
                        distance = similarity;
                        index = j;
                    }
                }
                if(index != clusters[i])
                {
                    clusters[i] = index;
                    changed = true;
                }
            }
            if(changed)
            {
                for(int i = 0; i < k; i++)
                {
                    mean = new Point();
                    count = 0;
                    for(int j = 0; j < length; j++)
                    {
                        point = pointSet.get(i);
                        if(clusters[j] == i)
                        {
                            mean = Point.add(mean, point);
                            count++;
                        }
                    }
                    centroids[i] = Point.scale(mean, 1.0 / (double) count);
                }
            }
        }
        return clusters;
    }

    public static String printClusters(final ArrayList<Point> pointSet, final int k, final int[] clusterList)
    {
        StringBuilder stringBuilder;
        ArrayList<ArrayList<Point>> pointClusterArray;
        Iterator<Point> iterator;
        stringBuilder = new StringBuilder("");
        pointClusterArray = new ArrayList<ArrayList<Point>>(k);
        if(pointSet.size() == clusterList.length)
        {
            for(int i = 0; i < k; i++)
            {
                pointClusterArray.add(i, new ArrayList<Point>());
            }
            for(int i = 0; i < pointSet.size(); i++)
            {
                pointClusterArray.get(clusterList[i]).add(pointSet.get(i));
            }
            for(int i = 0; i < k; i++)
            {
                stringBuilder.append(i + 1);
                stringBuilder.append(": {");
                for(iterator = pointClusterArray.get(i).iterator(); iterator.hasNext();)
                {
                    stringBuilder.append(iterator.next());
                    if(iterator.hasNext())
                    {
                        stringBuilder.append(", ");
                    }
                }
                stringBuilder.append("}\n");
            }
        }
        return stringBuilder.toString();
    }

}
