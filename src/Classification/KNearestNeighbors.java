/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classification;

import Point.*;
import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class KNearestNeighbors
{

    /**
     *
     * @param dataPoints
     * @param targetPoint
     * @return
     */
    public static ArrayList<Point> sortByDistanceToPoint(final ArrayList<Point> dataPoints, final Point targetPoint)
    {
        ArrayList<Point> sortedPoints;
        double distance;
        int index;
        Point temporary;
        sortedPoints = (ArrayList<Point>) dataPoints.clone();
        for(int i = 0; i < sortedPoints.size(); i++)
        {
            distance = Double.MAX_VALUE;
            index = -1;
            for(int j = i; j < sortedPoints.size(); j++)
            {
                if(Point.euclideanDistance(sortedPoints.get(j), targetPoint) < distance)
                {
                    distance = Point.euclideanDistance(sortedPoints.get(j), targetPoint);
                    index = j;
                }
            }
            temporary = sortedPoints.get(index);
            sortedPoints.set(index, sortedPoints.get(i));
            sortedPoints.set(i, temporary);
        }
        return sortedPoints;
    }

    /**
     *
     * @param sortedPoints
     * @param numberNeighbors
     * @param targetPoint
     * @return
     */
    public static TypeTarget predictPoint(final ArrayList<TypeTarget> sortedPoints, final int numberNeighbors, final TypeTarget targetPoint)
    {
        TypeTarget resultPoint;
        ArrayList<String> types;
        ArrayList<Double> weights;
        int index;
        TypeTarget currentPoint;
        double inverseDistance;
        double maximum;
        Double weight;
        resultPoint = new TypeTarget(targetPoint);
        types = new ArrayList<String>();
        weights = new ArrayList<Double>();
        index = -1;
        for(int i = 0; i < numberNeighbors; i++)
        {
            currentPoint = sortedPoints.get(i);
            inverseDistance = 1.0 / TypeTarget.euclideanDistance(targetPoint, currentPoint);
            if(!types.contains(currentPoint.getTarget()))
            {
                types.add(currentPoint.getTarget());
                weights.add(inverseDistance);
            }
            else
            {
                index = types.indexOf(currentPoint.getTarget());
                weights.set(index, weights.get(index) + inverseDistance);
            }
        }
        maximum = 0.0;
        for(int i = 0; i < weights.size(); i++)
        {
            weight = weights.get(i);
            if(maximum < weight)
            {
                maximum = weight;
                index = i;
            }
        }
        resultPoint.setTarget(types.get(index));
        return resultPoint;
    }

}
