/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classification;

import Point.Point;
import Point.TypeTarget;
import Statistics.Covariance;
import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class Logistic
{
    private ArrayList<Double> theta;

    /**
     *
     */
    public Logistic()
    {
        this.theta = new ArrayList<Double>();
    }

    /**
     *
     * @param logistic
     */
    public Logistic(Logistic logistic)
    {
        this.theta = new ArrayList<Double>(logistic.theta);
    }

    /**
     *
     */
    public Logistic(int i)
    {
        this.theta = new ArrayList<Double>(i);
    }

    public Logistic(ArrayList<Double> theta)
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
    public static double calculateHypothesis(final Logistic regressionCoefficients, final TypeTarget point)
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
     * @param regressionCoefficients
     * @param point
     * @return
     */
    public static double calculateLogisticFunction(final Logistic regressionCoefficients, final TypeTarget point)
    {
        return 1.0 / (1.0 + Math.pow(Math.E, -(calculateHypothesis(regressionCoefficients, point))));
    }

    /**
     *
     * @param target
     * @param point
     * @param regressionCoefficients
     * @return
     */
    public static double hypothesisError(final double target, final TypeTarget point, final Logistic regressionCoefficients)
    {
        final double output;
        output = calculateLogisticFunction(regressionCoefficients, point);
        return (target - output);
    }

    /**
     *
     * @param targetType
     * @param point
     * @return
     */
    public static double convertTypeToNumber(final String targetType, TypeTarget point)
    {
        return point.getTarget().equals(targetType) ? 1.0 : 0.0;
    }

    /**
     *
     * @param targetType
     * @param oldRegressionCoefficients
     * @param dataPoints
     * @return
     */
    public static Logistic updateRegressionCoefficients(final String targetType, final Logistic oldRegressionCoefficients, final ArrayList<TypeTarget> dataPoints)
    {
        Logistic newRegressionCoefficients;
        double alpha;
        double hypothesisError;
        newRegressionCoefficients = new Logistic(oldRegressionCoefficients);
        alpha = 1.0 / dataPoints.size();
        for(TypeTarget point : dataPoints)
        {
            hypothesisError = hypothesisError(convertTypeToNumber(targetType, point), point, newRegressionCoefficients);
            newRegressionCoefficients.setTheta(0, newRegressionCoefficients.getTheta(0)+ alpha * hypothesisError);
            for (int i = 0; i < point.getDimensions(); i++)
            {
                newRegressionCoefficients.setTheta(i + 1, newRegressionCoefficients.getTheta(i + 1) + alpha * hypothesisError * point.getCoordinate(i));
            }
        }
        return newRegressionCoefficients;
    }

    /**
     *
     * @param oldRegressionCoefficients
     * @param newRegressionCoefficients
     * @return
     */
    public static boolean isCloseEnough(Logistic oldRegressionCoefficients, Logistic newRegressionCoefficients)
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
     * @param targetType
     * @param dataPoints
     * @return
     */
    public static Logistic logisticRegression(final String targetType, final ArrayList<TypeTarget> dataPoints)
    {
        final int dimensions;
        Logistic oldRegressionCoefficients;
        Logistic newRegressionCoefficients;
        Point covariancePoint;
        dimensions = dataPoints.get(0).getDimensions();
        oldRegressionCoefficients = new Logistic(dimensions + 1);
        newRegressionCoefficients = new Logistic(dimensions + 1);
        newRegressionCoefficients.setTheta(0, 0.0);
        covariancePoint = Covariance.covarianceWithRespectToTarget(targetType, dataPoints);
        for(int i = 0; i < dimensions; i++)
        {
            newRegressionCoefficients.setTheta(i + 1, covariancePoint.getCoordinate(i));
        }
        while(!isCloseEnough(oldRegressionCoefficients, newRegressionCoefficients))
        {
            oldRegressionCoefficients = newRegressionCoefficients;
            newRegressionCoefficients = updateRegressionCoefficients(targetType, oldRegressionCoefficients, dataPoints);
        }
        return newRegressionCoefficients;
    }

    /**
     *
     * @param regressionCoefficientsList
     * @param targetPoint
     * @param types
     * @return
     */
    public static int pickBestRegression(final ArrayList<Logistic> regressionCoefficientsList, final TypeTarget targetPoint, final ArrayList<String> types)
    {
        int index;
        double maximum;
        double probability;
        index = -1;
        maximum = Double.MIN_VALUE;
        for(int i = 0; i < regressionCoefficientsList.size(); i++)
        {
            probability = Logistic.calculateLogisticFunction(regressionCoefficientsList.get(i), targetPoint);
            System.out.println("probability for " + types.get(i) + ": " + probability);
            if(probability > maximum)
            {
                maximum = probability;
                index = i;
            }
        }
        return index;
    }
}
