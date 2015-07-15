/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Normalization;

import Point.Point;
import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class FeatureScaling
{
    private double xMin;

    private double xMax;

    private double yMin;

    private double yMax;

    public FeatureScaling()
    {
        this.xMin = 0.0;
        this.xMax = 1.0;
        this.yMin = 0.0;
        this.yMax = 1.0;
    }

    public FeatureScaling(double xMin, double xMax, double yMin, double yMax)
    {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    /**
     * @return the xMin
     */
    public double getxMin()
    {
        return xMin;
    }

    /**
     * @param xMin the xMin to set
     */
    public void setxMin(double xMin)
    {
        this.xMin = xMin;
    }

    /**
     * @return the xMax
     */
    public double getxMax()
    {
        return xMax;
    }

    /**
     * @param xMax the xMax to set
     */
    public void setxMax(double xMax)
    {
        this.xMax = xMax;
    }

    /**
     * @return the yMin
     */
    public double getyMin()
    {
        return yMin;
    }

    /**
     * @param yMin the yMin to set
     */
    public void setyMin(double yMin)
    {
        this.yMin = yMin;
    }

    /**
     * @return the yMax
     */
    public double getyMax()
    {
        return yMax;
    }

    /**
     * @param yMax the yMax to set
     */
    public void setyMax(double yMax)
    {
        this.yMax = yMax;
    }

    /**
     *
     * @param dataPoints
     * @return
     */
    public static FeatureScaling getExtents(final ArrayList<Point> dataPoints)
    {
        FeatureScaling pointExtents;
        pointExtents = new FeatureScaling(Double.MAX_VALUE, Double.MIN_VALUE, Double.MAX_VALUE, Double.MIN_VALUE);
        for(Point dataPoint : dataPoints)
        {
            if(dataPoint.getX() < pointExtents.getxMin())
            {
                pointExtents.setxMin(dataPoint.getX());
            }
            if(dataPoint.getX() > pointExtents.getxMax())
            {
                pointExtents.setxMax(dataPoint.getX());
            }
            if(dataPoint.getY() < pointExtents.getyMin())
            {
                pointExtents.setyMin(dataPoint.getY());
            }
            if(dataPoint.getY() > pointExtents.getyMax())
            {
                pointExtents.setyMax(dataPoint.getY());
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
        final double originalX;
        final double originalY;
        final double oldXMin;
        final double oldXMax;
        final double oldYMin;
        final double oldYMax;
        final double newXMin;
        final double newXMax;
        final double newYMin;
        final double newYMax;
        originalX = originalPoint.getX();
        originalY = originalPoint.getY();
        oldXMin = oldPointExtents.getxMin();
        oldXMax = oldPointExtents.getxMax();
        oldYMin = oldPointExtents.getyMin();
        oldYMax = oldPointExtents.getyMax();
        newXMin = newPointExtents.getxMin();
        newXMax = newPointExtents.getxMax();
        newYMin = newPointExtents.getyMin();
        newYMax = newPointExtents.getyMax();
        normalizedPoint = new Point();
        normalizedPoint.setX(((originalX - oldXMin) * newXMin + (oldXMax- originalX) * newXMax) / (oldXMax - oldXMin));
        normalizedPoint.setY(((originalY- oldYMin) * newYMin+ (oldYMax- originalY) * newYMax) / (oldYMax- oldYMin));
        normalizedPoint.setType(originalPoint.getType());
        return normalizedPoint;
    }
}
