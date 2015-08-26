/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Regression;

import Point.NumericalTarget;
import Point.Point;
import Statistics.Covariance;
import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class Linear
{
    private ArrayList<Double> theta;

    /**
     *
     */
    public Linear()
    {
        this.theta = new ArrayList<Double>();
    }

    /**
     *
     * @param linear
     */
    public Linear(Linear linear)
    {
        this.theta = new ArrayList<Double>(linear.theta);
    }

    /**
     *
     */
    public Linear(int i)
    {
        this.theta = new ArrayList<Double>(i);
    }

    public Linear(ArrayList<Double> theta)
    {
        this.theta = new ArrayList<Double>(theta);
    }

    /**
     * @param i
     * @return
     */
    public double getTheta(int i)
    {
        return this.theta.get(i);
    }

    /**
     * @param i
     * @param d
     */
    public void setTheta(int i, double d)
    {
        this.theta.set(i, d);
    }

    /**
     *
     * @param regressionCoefficients
     * @param point
     * @return
     */
    public static double calculateHypothesis(final Linear regressionCoefficients, final NumericalTarget point)
    {
        double hypothesis;
        hypothesis = regressionCoefficients.getTheta(0);
        for (int i = 0; i < point.getDimensions(); i++)
        {
            hypothesis += regressionCoefficients.getTheta(i + 1) * point.getCoordinate(i);
        }
        return hypothesis;
    }

    /**
     *
     * @param point
     * @param regressionCoefficients
     * @return
     */
    public static double hypothesisError(final NumericalTarget point, final Linear regressionCoefficients)
    {
        final double output;
        output = calculateHypothesis(regressionCoefficients, point);
        return (point.getTarget() - output);
    }

    /**
     *
     * @param oldRegressionCoefficients
     * @param dataNumericalTargets
     * @return
     */
    public static Linear updateRegressionCoefficients(final Linear oldRegressionCoefficients, final ArrayList<NumericalTarget> dataNumericalTargets)
    {
        Linear newRegressionCoefficients;
        double alpha;
        double hypothesisError;
        double sumOfErrors;
        newRegressionCoefficients = new Linear();
        alpha = 0.001;
        sumOfErrors = 0.0;
        for(NumericalTarget point : dataNumericalTargets)
        {
            hypothesisError = hypothesisError(point, newRegressionCoefficients);
            sumOfErrors += hypothesisError;
            for (int i = 0; i < point.getDimensions(); i++)
            {
                sumOfErrors += hypothesisError * point.getCoordinate(i);
            }
        }
        for (int i = 0; i < newRegressionCoefficients.theta.size(); i++)
        {
            newRegressionCoefficients.setTheta(i, oldRegressionCoefficients.getTheta(i) + alpha * sumOfErrors);
        }
        return newRegressionCoefficients;
    }

    /**
     *
     * @param oldRegressionCoefficients
     * @param newRegressionCoefficients
     * @return
     */
    public static boolean isCloseEnough(Linear oldRegressionCoefficients, Linear newRegressionCoefficients)
    {
        final double threshold;
        double distance;
        threshold = 0.005;
        distance = 0.0;
        for(int i = 0; i < oldRegressionCoefficients.theta.size(); i++)
        {
            distance += (oldRegressionCoefficients.getTheta(i) - newRegressionCoefficients.getTheta(i)) * (oldRegressionCoefficients.getTheta(i) - newRegressionCoefficients.getTheta(i));
        }
        return Math.sqrt(distance) < threshold;
    }

    /**
     *
     * @param dataPoints
     * @return
     */
    public static Linear linearRegression(final ArrayList<NumericalTarget> dataPoints)
    {
        final int dimensions;
        Linear oldRegressionCoefficients;
        Linear newRegressionCoefficients;
        Point covariancePoint;
        dimensions = dataPoints.get(0).getDimensions();
        oldRegressionCoefficients = new Linear(dimensions + 1);
        newRegressionCoefficients = new Linear(dimensions + 1);
        newRegressionCoefficients.setTheta(0, 0.0);
        covariancePoint = Covariance.covarianceWithRespectToTarget(dataPoints);
        for(int i = 0; i < dimensions; i++)
        {
            newRegressionCoefficients.setTheta(i + 1, covariancePoint.getCoordinate(i));
        }
        while(!isCloseEnough(oldRegressionCoefficients, newRegressionCoefficients))
        {
            oldRegressionCoefficients = newRegressionCoefficients;
            newRegressionCoefficients = updateRegressionCoefficients(oldRegressionCoefficients, dataPoints);
        }
        return newRegressionCoefficients;
    }
}
