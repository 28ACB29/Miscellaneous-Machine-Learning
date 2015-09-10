/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics.Normalization;

import Point.Point;
import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class FeatureScaling
{
    private ArrayList<Double> minima;

    private ArrayList<Double> maxima;

    public FeatureScaling()
    {
        this.minima = new ArrayList<Double>();
        this.maxima = new ArrayList<Double>();
    }

    public FeatureScaling(FeatureScaling featureScaling)
    {
        this.minima = new ArrayList<Double>(featureScaling.minima);
        this.maxima = new ArrayList<Double>(featureScaling.maxima);
    }

    public FeatureScaling(int i)
    {
        this.minima = new ArrayList<Double>(i);
        this.maxima = new ArrayList<Double>(i);
        for(int j = 0; j < i; j++)
        {
            this.minima.set(i, 0.0);
            this.maxima.set(i, 1.0);
        }
    }

    /**
     * @param i
     * @return the Min
     */
    public double getMin(int i)
    {
        return this.minima.get(i);
    }

    /**
     * @param i
     * @param Min the Min to set
     */
    public void setMin(int i, double Min)
    {
        this.minima.set(i, Min);
    }

    /**
     * @param i
     * @return the Max
     */
    public double getMax(int i)
    {
        return this.maxima.get(i);
    }

    /**
     * @param i
     * @param Max the Max to set
     */
    public void setMax(int i, double Max)
    {
        this.maxima.set(i, Max);
    }

    /**
     *
     * @param dataPoints
     * @return
     */
    public static FeatureScaling getExtents(final ArrayList<Point> dataPoints)
    {
        FeatureScaling pointExtents;
        final int dimensions;
        dimensions = dataPoints.get(0).getDimensions();
        pointExtents = new FeatureScaling(dimensions);
        for(Point dataPoint : dataPoints)
        {
            for(int i = 0; i < dimensions; i++)
            {
                if(dataPoint.getCoordinate(i) < pointExtents.getMin(i))
                {
                    pointExtents.setMin(i, dataPoint.getCoordinate(i));
                }
                if(dataPoint.getCoordinate(i) > pointExtents.getMax(i))
                {
                    pointExtents.setMax(i, dataPoint.getCoordinate(i));
                }
            }
        }
        return pointExtents;
    }

    /**
     *
     * @param originalPoint
     * @param oldPointExtents
     * @param newPointExtents
     * @return
     */
    public static Point normalize(final Point originalPoint, final FeatureScaling oldPointExtents, final FeatureScaling newPointExtents)
    {
        Point normalizedPoint;
        final int dimensions;
        double originalCoordinate;
        double oldMin;
        double oldMax;
        double newMin;
        double newMax;
        dimensions = originalPoint.getDimensions();
        normalizedPoint = new Point(dimensions);
        for(int i = 0; i < dimensions; i++)
        {
            originalCoordinate = originalPoint.getCoordinate(i);
            oldMin = oldPointExtents.getMin(i);
            oldMax = oldPointExtents.getMax(i);
            newMin = newPointExtents.getMin(i);
            newMax = newPointExtents.getMax(i);
            normalizedPoint.setCoordinate(i, ((originalCoordinate - oldMin) * newMin + (oldMax- originalCoordinate) * newMax) / (oldMax - oldMin));
        }
        return normalizedPoint;
    }
}
