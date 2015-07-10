/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Normalization;

import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class StandardScore
{
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
        final double size;
        int countZeroes;
        distributionMoments = new StandardScore(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        size = (double) dataPoints.size();
        countZeroes = 0;
        for(Point dataPoint : dataPoints)
        {
            distributionMoments.setMean1(distributionMoments.getMean1() + dataPoint.getX1());
            distributionMoments.setMean2(distributionMoments.getMean2() + dataPoint.getX2());
            distributionMoments.setMean3(distributionMoments.getMean3() + dataPoint.getX3());
            distributionMoments.setMean4(distributionMoments.getMean4() + dataPoint.getX4());
            if(dataPoint.getY() != 0.0)
            {
                distributionMoments.setMeanY(distributionMoments.getMeanY() + dataPoint.getY());
            }
            else
            {
                countZeroes++;
            }
        }
        distributionMoments.setMean1(distributionMoments.getMean1() / size);
        distributionMoments.setMean2(distributionMoments.getMean2() / size);
        distributionMoments.setMean3(distributionMoments.getMean3() / size);
        distributionMoments.setMean4(distributionMoments.getMean4() / size);
        distributionMoments.setMeanY(distributionMoments.getMeanY() / (size - countZeroes));
        for(Point dataPoint : dataPoints)
        {
            distributionMoments.setStandardDeviation1(distributionMoments.getStandardDeviation1() + (dataPoint.getX1() - distributionMoments.getMean1()) * (dataPoint.getX1() - distributionMoments.getMean1()));
            distributionMoments.setStandardDeviation2(distributionMoments.getStandardDeviation2() + (dataPoint.getX2() - distributionMoments.getMean2()) * (dataPoint.getX2() - distributionMoments.getMean2()));
            distributionMoments.setStandardDeviation3(distributionMoments.getStandardDeviation3() + (dataPoint.getX3() - distributionMoments.getMean3()) * (dataPoint.getX3() - distributionMoments.getMean3()));
            distributionMoments.setStandardDeviation4(distributionMoments.getStandardDeviation4() + (dataPoint.getX4() - distributionMoments.getMean4()) * (dataPoint.getX4() - distributionMoments.getMean4()));
            if(dataPoint.getY() == 0.0)
            {
                dataPoint.setY(distributionMoments.getMeanY());
            }
            else
            {
                distributionMoments.setStandardDeviationY(distributionMoments.getStandardDeviationY()+ (dataPoint.getY()- distributionMoments.getMeanY()) * (dataPoint.getY()- distributionMoments.getMeanY()));
            }
        }
        distributionMoments.setStandardDeviation1(Math.sqrt(distributionMoments.getStandardDeviation1() / size));
        distributionMoments.setStandardDeviation2(Math.sqrt(distributionMoments.getStandardDeviation2() / size));
        distributionMoments.setStandardDeviation3(Math.sqrt(distributionMoments.getStandardDeviation3() / size));
        distributionMoments.setStandardDeviation4(Math.sqrt(distributionMoments.getStandardDeviation4() / size));
        distributionMoments.setStandardDeviationY(Math.sqrt(distributionMoments.getStandardDeviationY()/ (size - countZeroes)));
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
        normalizedPoint = new Point();
        final double oldX1;
        final double oldX2;
        final double oldX3;
        final double oldX4;
        final double oldY;
        final double oldMean1;
        final double oldStandardDeviation1;
        final double oldMean2;
        final double oldStandardDeviation2;
        final double oldMean3;
        final double oldStandardDeviation3;
        final double oldMean4;
        final double oldStandardDeviation4;
        final double oldMeanY;
        final double oldStandardDeviationY;
        final double newMean1;
        final double newStandardDeviation1;
        final double newMean2;
        final double newStandardDeviation2;
        final double newMean3;
        final double newStandardDeviation3;
        final double newMean4;
        final double newStandardDeviation4;
        final double newMeanY;
        final double newStandardDeviationY;
        oldX1 = originalPoint.getX1();
        oldX2 = originalPoint.getX2();
        oldX3 = originalPoint.getX3();
        oldX4 = originalPoint.getX4();
        oldY = originalPoint.getY();
        oldMean1 = oldPointDistributionMoments.getMean1();
        oldStandardDeviation1 = oldPointDistributionMoments.getStandardDeviation1();
        oldMean2 = oldPointDistributionMoments.getMean2();
        oldStandardDeviation2 = oldPointDistributionMoments.getStandardDeviation2();
        oldMean3 = oldPointDistributionMoments.getMean3();
        oldStandardDeviation3 = oldPointDistributionMoments.getStandardDeviation3();
        oldMean4 = oldPointDistributionMoments.getMean4();
        oldStandardDeviation4 = oldPointDistributionMoments.getStandardDeviation4();
        oldMeanY = oldPointDistributionMoments.getMeanY();
        oldStandardDeviationY = oldPointDistributionMoments.getStandardDeviationY();
        newMean1 = newPointDistributionMoments.getMean1();
        newStandardDeviation1 = newPointDistributionMoments.getStandardDeviation1();
        newMean2 = newPointDistributionMoments.getMean2();
        newStandardDeviation2 = newPointDistributionMoments.getStandardDeviation2();
        newMean3 = newPointDistributionMoments.getMean3();
        newStandardDeviation3 = newPointDistributionMoments.getStandardDeviation3();
        newMean4 = newPointDistributionMoments.getMean4();
        newStandardDeviation4 = newPointDistributionMoments.getStandardDeviation4();
        newMeanY = newPointDistributionMoments.getMeanY();
        newStandardDeviationY = newPointDistributionMoments.getStandardDeviationY();
        normalizedPoint.setX1(((oldX1 - oldMean1) / oldStandardDeviation1) * newStandardDeviation1 + newMean1);
        normalizedPoint.setX2(((oldX2 - oldMean2) / oldStandardDeviation2) * newStandardDeviation2 + newMean2);
        normalizedPoint.setX3(((oldX3 - oldMean3) / oldStandardDeviation3) * newStandardDeviation3 + newMean3);
        normalizedPoint.setX4(((oldX4 - oldMean4) / oldStandardDeviation4) * newStandardDeviation4 + newMean4);
        normalizedPoint.setY(((oldY - oldMeanY) / oldStandardDeviationY) * newStandardDeviationY + newMeanY);
        return normalizedPoint;
    }

}
