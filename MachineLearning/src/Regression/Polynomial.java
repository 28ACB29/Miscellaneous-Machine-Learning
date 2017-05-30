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
public class Polynomial
{

    private ArrayList<Double> Alpha;

    /**
     *
     */
    public Polynomial()
    {
        this.Alpha = new ArrayList<Double>();
    }

    /**
     *
     * @param linear
     */
    public Polynomial(Polynomial polynomial)
    {
        this.Alpha = new ArrayList<Double>(polynomial.Alpha);
    }

    /**
     *
     * @param degrees
     */
    public Polynomial(int degrees)
    {
        this.Alpha = new ArrayList<Double>(degrees);
    }

    /**
     *
     * @param Beta
     */
    public Polynomial(ArrayList<Double> Beta)
    {
        this.Alpha = new ArrayList<Double>(Beta);
    }

    /**
     * @param degree
     * @return
     */
    public double getAlpha(int degree)
    {
        return this.Alpha.get(degree);
    }

    /**
     * @param degree
     * @param d
     */
    public void setAlpha(int degree, double d)
    {
        this.Alpha.set(degree, d);
    }

    private static ArrayList<Double> generatePowers(double x, int degrees)
    {
        ArrayList<Double> powers;
        int terms;
        terms = degrees + 1;
        powers = new ArrayList<Double>(terms);
        for(int i = 0; i < terms; i++)
        {
            powers.add(Math.pow(x, (double) i));
        }
        return powers;
    }

    /**
     *
     * @param regressionCoefficients
     * @param point
     * @return
     */
    public static double calculateHypothesis(final Polynomial regressionCoefficients, final NumericalTarget point)
    {
        double hypothesis;
        hypothesis = regressionCoefficients.getAlpha(0);
        for (int i = 0; i < point.getDimensions(); i++)
        {
            hypothesis += regressionCoefficients.getAlpha(i + 1) * point.getCoordinate(i);
        }
        return hypothesis;
    }

    /**
     *
     * @param point
     * @param regressionCoefficients
     * @return
     */
    public static double hypothesisError(final NumericalTarget point, final Polynomial regressionCoefficients)
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
    public static Polynomial updateRegressionCoefficients(final Polynomial oldRegressionCoefficients, final ArrayList<NumericalTarget> dataNumericalTargets)
    {
        Polynomial newRegressionCoefficients;
        double alpha;
        double hypothesisError;
        double sumOfErrors;
        newRegressionCoefficients = new Polynomial();
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
        for (int i = 0; i < newRegressionCoefficients.Alpha.size(); i++)
        {
            newRegressionCoefficients.setAlpha(i, oldRegressionCoefficients.getAlpha(i) + alpha * sumOfErrors);
        }
        return newRegressionCoefficients;
    }

    /**
     *
     * @param oldRegressionCoefficients
     * @param newRegressionCoefficients
     * @return
     */
    public static boolean isCloseEnough(Polynomial oldRegressionCoefficients, Polynomial newRegressionCoefficients)
    {
        final double epsilon;
        double distance;
        epsilon = 0.005;
        distance = 0.0;
        for(int i = 0; i < oldRegressionCoefficients.Alpha.size(); i++)
        {
            distance += (oldRegressionCoefficients.getAlpha(i) - newRegressionCoefficients.getAlpha(i)) * (oldRegressionCoefficients.getAlpha(i) - newRegressionCoefficients.getAlpha(i));
        }
        return Math.sqrt(distance) < epsilon;
    }

    /**
     *
     * @param dataPoints
     * @param degree
     * @return
     */
    public static Polynomial polynomialRegression(final ArrayList<NumericalTarget> dataPoints, int degree)
    {
        final int dimensions;
        ArrayList<NumericalTarget> X;
        Polynomial oldRegressionCoefficients;
        Polynomial newRegressionCoefficients;
        Point covariancePoint;
        dimensions = degree + 1;
        X = new ArrayList<NumericalTarget>(dimensions);
        for(NumericalTarget dataPoint : dataPoints)
        {
            X.add(new NumericalTarget(Polynomial.generatePowers(dataPoint.getCoordinate(0), degree), dataPoint.getTarget()));
        }
        oldRegressionCoefficients = new Polynomial(dimensions);
        newRegressionCoefficients = new Polynomial(dimensions);
        newRegressionCoefficients.setAlpha(0, 0.0);
        covariancePoint = Covariance.covarianceWithRespectToTarget(X);
        for(int i = 1; i < dimensions; i++)
        {
            newRegressionCoefficients.setAlpha(i, covariancePoint.getCoordinate(i));
        }
        while(!isCloseEnough(oldRegressionCoefficients, newRegressionCoefficients))
        {
            oldRegressionCoefficients = newRegressionCoefficients;
            newRegressionCoefficients = updateRegressionCoefficients(oldRegressionCoefficients, X);
        }
        return newRegressionCoefficients;
    }

}
