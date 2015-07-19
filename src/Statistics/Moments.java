/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics;

import Point.Point;
import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class Moments
{

    /**
     *
     * @param dataPoints
     * @return
     */
    public static Point means(final ArrayList<Point> dataPoints)
    {
        Point meanPoint;
        final int dimensions;
        double meanCoordinate;
        double dataCoordinate;
        dimensions = dataPoints.get(0).getDimensions();
        meanPoint = new Point(dimensions);
        for(Point dataPoint : dataPoints)
        {
            for(int i = 0; i < dimensions; i++)
            {
                meanCoordinate = meanPoint.getCoordinate(i);
                dataCoordinate = dataPoint.getCoordinate(i);
                meanPoint.setCoordinate(i, meanCoordinate + dataCoordinate);
            }
        }
        meanPoint = Point.scale(meanPoint, 1.0 / (double) dataPoints.size());
        return meanPoint;
    }

    /**
     *
     * @param dataPoints
     * @return
     */
    public static Point standardDeviations(final ArrayList<Point> dataPoints)
    {
        Point standardDeviationPoint;
        final int dimensions;
        Point meanPoint;
        double standardDeviationCoordinate;
        double dataCoordinate;
        double meanCoordinate;
        dimensions = dataPoints.get(0).getDimensions();
        meanPoint = means(dataPoints);
        standardDeviationPoint = means(dataPoints);
        for(Point dataPoint : dataPoints)
        {
            for(int i = 0; i < dimensions; i++)
            {
                standardDeviationCoordinate = standardDeviationPoint.getCoordinate(i);
                dataCoordinate = dataPoint.getCoordinate(i);
                meanCoordinate = meanPoint.getCoordinate(i);
                standardDeviationPoint.setCoordinate(i, standardDeviationCoordinate + (dataCoordinate - meanCoordinate) * (dataCoordinate - meanCoordinate));
            }
        }
        standardDeviationPoint = Point.scale(standardDeviationPoint, 1.0 / (double) dataPoints.size());
        for(int i = 0; i < dimensions; i++)
        {
            standardDeviationCoordinate = standardDeviationPoint.getCoordinate(i);
            standardDeviationPoint.setCoordinate(i, Math.sqrt(standardDeviationCoordinate));
        }
        return standardDeviationPoint;
    }

}
