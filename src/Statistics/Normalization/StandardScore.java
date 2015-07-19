/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics.Normalization;

import Point.Point;
import Statistics.Moments;
import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class StandardScore
{
    private ArrayList<Double> means;

    private ArrayList<Double> standardDeviations;

    private double mean1;

    private double standardDeviation1;

    private double mean2;

    private double standardDeviation2;

    private double mean3;

    private double standardDeviation3;

    private double mean4;

    private double standardDeviation4;

    private double meanY;

    private double standardDeviationY;

    /**
     *
     */
    public StandardScore()
    {
        this.mean1 = 0.0;
        this.standardDeviation1 = 1.0;
        this.mean2 = 0.0;
        this.standardDeviation2 = 1.0;
        this.mean3 = 0.0;
        this.standardDeviation3 = 1.0;
        this.mean4 = 0.0;
        this.standardDeviation4 = 1.0;
        this.meanY = 0.0;
        this.standardDeviationY = 1.0;
    }

    /**
     *
     * @param mean1
     * @param standardDeviation1
     * @param mean2
     * @param standardDeviation2
     * @param mean3
     * @param standardDeviation3
     * @param mean4
     * @param standardDeviation4
     */
    public StandardScore(double mean1, double standardDeviation1, double mean2, double standardDeviation2, double mean3, double standardDeviation3, double mean4, double standardDeviation4)
    {
        this.mean1 = mean1;
        this.standardDeviation1 = standardDeviation1;
        this.mean2 = mean2;
        this.standardDeviation2 = standardDeviation2;
        this.mean3 = mean3;
        this.standardDeviation3 = standardDeviation3;
        this.mean4 = mean4;
        this.standardDeviation4 = standardDeviation4;
        this.meanY = 0.0;
        this.standardDeviationY = 1.0;
    }

    /**
     *
     * @param mean1
     * @param standardDeviation1
     * @param mean2
     * @param standardDeviation2
     * @param mean3
     * @param standardDeviation3
     * @param mean4
     * @param standardDeviation4
     * @param meanY
     * @param standardDeviationY
     */
    public StandardScore(double mean1, double standardDeviation1, double mean2, double standardDeviation2, double mean3, double standardDeviation3, double mean4, double standardDeviation4, double meanY, double standardDeviationY)
    {
        this.mean1 = mean1;
        this.standardDeviation1 = standardDeviation1;
        this.mean2 = mean2;
        this.standardDeviation2 = standardDeviation2;
        this.mean3 = mean3;
        this.standardDeviation3 = standardDeviation3;
        this.mean4 = mean4;
        this.standardDeviation4 = standardDeviation4;
        this.meanY = meanY;
        this.standardDeviationY = standardDeviationY;
    }

    /**
     * @return the mean1
     */
    public double getMean1()
    {
        return mean1;
    }

    /**
     * @param mean1 the mean1 to set
     */
    public void setMean1(double mean1)
    {
        this.mean1 = mean1;
    }

    /**
     * @return the standardDeviation1
     */
    public double getStandardDeviation1()
    {
        return standardDeviation1;
    }

    /**
     * @param standardDeviation1 the standardDeviation1 to set
     */
    public void setStandardDeviation1(double standardDeviation1)
    {
        this.standardDeviation1 = standardDeviation1;
    }

    /**
     * @return the mean2
     */
    public double getMean2()
    {
        return mean2;
    }

    /**
     * @param mean2 the mean2 to set
     */
    public void setMean2(double mean2)
    {
        this.mean2 = mean2;
    }

    /**
     * @return the standardDeviation2
     */
    public double getStandardDeviation2()
    {
        return standardDeviation2;
    }

    /**
     * @param standardDeviation2 the standardDeviation2 to set
     */
    public void setStandardDeviation2(double standardDeviation2)
    {
        this.standardDeviation2 = standardDeviation2;
    }

    /**
     * @return the mean3
     */
    public double getMean3()
    {
        return mean3;
    }

    /**
     * @param mean3 the mean3 to set
     */
    public void setMean3(double mean3)
    {
        this.mean3 = mean3;
    }

    /**
     * @return the standardDeviation3
     */
    public double getStandardDeviation3()
    {
        return standardDeviation3;
    }

    /**
     * @param standardDeviation3 the standardDeviation3 to set
     */
    public void setStandardDeviation3(double standardDeviation3)
    {
        this.standardDeviation3 = standardDeviation3;
    }

    /**
     * @return the mean4
     */
    public double getMean4()
    {
        return mean4;
    }

    /**
     * @param mean4 the mean4 to set
     */
    public void setMean4(double mean4)
    {
        this.mean4 = mean4;
    }

    /**
     * @return the standardDeviation4
     */
    public double getStandardDeviation4()
    {
        return standardDeviation4;
    }

    /**
     * @param standardDeviation4 the standardDeviation4 to set
     */
    public void setStandardDeviation4(double standardDeviation4)
    {
        this.standardDeviation4 = standardDeviation4;
    }

    /**
     * @return the meanY
     */
    public double getMeanY()
    {
        return meanY;
    }

    /**
     * @param meanY the meanY to set
     */
    public void setMeanY(double meanY)
    {
        this.meanY = meanY;
    }

    /**
     * @return the standardDeviationY
     */
    public double getStandardDeviationY()
    {
        return standardDeviationY;
    }

    /**
     * @param standardDeviationY the standardDeviationY to set
     */
    public void setStandardDeviationY(double standardDeviationY)
    {
        this.standardDeviationY = standardDeviationY;
    }

    /**
     *
     * @param dataPoints
     * @return
     */
    public static StandardScore getMoments(final ArrayList<Point> dataPoints)
    {
        StandardScore distributionMoments;
        Point meanPoint;
        Point standardDeviationPoint;
        final int dimensions;
        double meanCoordinate;
        double standardDeviationCoordinate;
        distributionMoments = new StandardScore();
        meanPoint = Moments.means(dataPoints);
        standardDeviationPoint = Moments.standardDeviations(dataPoints);
        dimensions = dataPoints.get(0).getDimensions();
        for(int i = 0; i < dimensions; i++)
        {
            meanCoordinate = meanPoint.getCoordinate(i);
            standardDeviationCoordinate = standardDeviationPoint.getCoordinate(i);
            distributionMoments.means.set(i, meanCoordinate);
            distributionMoments.standardDeviations.set(i, standardDeviationCoordinate);
        }
        return distributionMoments;
    }

    /**
     *
     * @param originalPoint
     * @param oldPointDistributionMoments
     * @param newPointDistributionMoments
     * @return
     */
    public static Point normalize(final Point originalPoint, final StandardScore oldPointDistributionMoments, final StandardScore newPointDistributionMoments)
    {
        Point normalizedPoint;
        final int dimensions;
        normalizedPoint = new Point();
        dimensions = originalPoint.getDimensions();
        double oldX;
        double oldMean;
        double oldStandardDeviation;
        double newMean;
        double newStandardDeviation;
        for(int i = 0; i < dimensions; i++)
        {
            oldX = originalPoint.getCoordinate(i);
            oldMean = oldPointDistributionMoments.means.get(i);
            oldStandardDeviation = oldPointDistributionMoments.standardDeviations.get(i);
            newMean = newPointDistributionMoments.means.get(i);
            newStandardDeviation = newPointDistributionMoments.standardDeviations.get(i);
            normalizedPoint.setCoordinate(i, ((oldX - oldMean) / oldStandardDeviation) * newStandardDeviation + newMean);
        }
        return normalizedPoint;
    }

}
