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
    private ArrayList<Double> Beta;

    /**
     *
     */
    public Linear()
    {
        this.Beta = new ArrayList<Double>();
    }

    /**
     *
     * @param linear
     */
    public Linear(Linear linear)
    {
        this.Beta = new ArrayList<Double>(linear.Beta);
    }

    /**
     *
     * @param i
     */
    public Linear(int i)
    {
        this.Beta = new ArrayList<Double>(i);
    }

    /**
     *
     * @param Beta
     */
    public Linear(ArrayList<Double> Beta)
    {
        this.Beta = new ArrayList<Double>(Beta);
    }

    /**
     * @param i
     * @return
     */
    public double getBeta(int i)
    {
        return this.Beta.get(i);
    }

    /**
     * @param i
     * @param d
     */
    public void setBeta(int i, double d)
    {
        this.Beta.set(i, d);
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
        hypothesis = regressionCoefficients.getBeta(0);
        for (int i = 0; i < point.getDimensions(); i++)
        {
            hypothesis += regressionCoefficients.getBeta(i + 1) * point.getCoordinate(i);
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
        for (int i = 0; i < newRegressionCoefficients.Beta.size(); i++)
        {
            newRegressionCoefficients.setBeta(i, oldRegressionCoefficients.getBeta(i) + alpha * sumOfErrors);
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
        final double epsilon;
        double distance;
        epsilon = 0.005;
        distance = 0.0;
        for(int i = 0; i < oldRegressionCoefficients.Beta.size(); i++)
        {
            distance += (oldRegressionCoefficients.getBeta(i) - newRegressionCoefficients.getBeta(i)) * (oldRegressionCoefficients.getBeta(i) - newRegressionCoefficients.getBeta(i));
        }
        return Math.sqrt(distance) < epsilon;
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
        newRegressionCoefficients.setBeta(0, 0.0);
        covariancePoint = Covariance.covarianceWithRespectToTarget(dataPoints);
        for(int i = 0; i < dimensions; i++)
        {
            newRegressionCoefficients.setBeta(i + 1, covariancePoint.getCoordinate(i));
        }
        while(!isCloseEnough(oldRegressionCoefficients, newRegressionCoefficients))
        {
            oldRegressionCoefficients = newRegressionCoefficients;
            newRegressionCoefficients = updateRegressionCoefficients(oldRegressionCoefficients, dataPoints);
        }
        return newRegressionCoefficients;
    }
}
