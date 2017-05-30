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

    /**
     *
     */
    public StandardScore()
    {
        this.means = new ArrayList<Double>();
        this.standardDeviations = new ArrayList<Double>();
    }

    /**
     *
     * @param means
     * @param standardDeviations
     */
    public StandardScore(ArrayList<Double> means, ArrayList<Double> standardDeviations)
    {
        this.means = means;
        this.standardDeviations = standardDeviations;
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
