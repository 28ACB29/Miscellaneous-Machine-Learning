/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Regression;

import Point.TypeTarget;
import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class Logistic
{
    private ArrayList<Double> theta;
    
    private double theta0;

    private double theta1;

    private double theta2;

    /**
     *
     */
    public Logistic()
    {
        this.theta = new ArrayList<Double>();
    }

    public Logistic(ArrayList<Double> theta)
    {
        this.theta = theta;
    }

    /**
     *
     * @param theta0
     * @param theta1
     * @param theta2
     */
    public Logistic(double theta0, double theta1, double theta2)
    {
        this.theta0 = theta0;
        this.theta1 = theta1;
        this.theta2 = theta2;
    }

    /**
     *
     * @param theta1
     * @param theta2
     */
    public Logistic(double theta1, double theta2)
    {
        this.theta1 = theta1;
        this.theta2 = theta2;
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
     * @return the theta0
     */
    public double getTheta0()
    {
        return theta0;
    }

    /**
     * @param theta0 the theta0 to set
     */
    public void setTheta0(double theta0)
    {
        this.theta0 = theta0;
    }

    /**
     * @return the theta1
     */
    public double getTheta1()
    {
        return theta1;
    }

    /**
     * @param theta1 the theta1 to set
     */
    public void setTheta1(double theta1)
    {
        this.theta1 = theta1;
    }

    /**
     * @return the theta2
     */
    public double getTheta2()
    {
        return theta2;
    }

    /**
     * @param theta2 the theta2 to set
     */
    public void setTheta2(double theta2)
    {
        this.theta2 = theta2;
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
     * @param oldRegressionCoefficients
     * @param dataTypeTargets
     * @return
     */
    public static Logistic updateRegressionCoefficients(final String targetType, final Logistic oldRegressionCoefficients, final ArrayList<TypeTarget> dataTypeTargets)
    {
        Logistic newRegressionCoefficients;
        double alpha;
        double hypothesisError;
        newRegressionCoefficients = new Logistic(oldRegressionCoefficients.theta0, oldRegressionCoefficients.theta1, oldRegressionCoefficients.theta2);
        alpha = 1.0 / dataTypeTargets.size();
        for(TypeTarget point : dataTypeTargets)
        {
            if(point.getTarget().equals(targetType))
            {
                hypothesisError = hypothesisError(1.0, point, newRegressionCoefficients);
            }
            else
            {
                hypothesisError = hypothesisError(0.0, point, newRegressionCoefficients);
            }
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
        final double oldTheta0;
        final double oldTheta1;
        final double oldTheta2;
        final double newTheta0;
        final double newTheta1;
        final double newTheta2;
        double distance;
        threshold = 0.005;
        oldTheta0 = oldRegressionCoefficients.getTheta0();
        oldTheta1 = oldRegressionCoefficients.getTheta1();
        oldTheta2 = oldRegressionCoefficients.getTheta2();
        newTheta0 = newRegressionCoefficients.getTheta0();
        newTheta1 = newRegressionCoefficients.getTheta1();
        newTheta2 = newRegressionCoefficients.getTheta2();
        distance = Math.sqrt((oldTheta0 - newTheta0) * (oldTheta0 - newTheta0) + (oldTheta1 - newTheta1) * (oldTheta1 - newTheta1) + (oldTheta2 - newTheta2) * (oldTheta2 - newTheta2));
        return distance < threshold;
    }

    /**
     *
     * @param targetType
     * @param dataTypeTargets
     * @return
     */
    public static Logistic logisticRegression(final String targetType, final ArrayList<TypeTarget> dataTypeTargets)
    {
        Logistic oldRegressionCoefficients;
        Logistic newRegressionCoefficients;
        oldRegressionCoefficients = new Logistic(-1.0, 1.0, 1.0);
        newRegressionCoefficients = new Logistic(1.0, 1.0, 1.0);
        while(!isCloseEnough(oldRegressionCoefficients, newRegressionCoefficients))
        {
            oldRegressionCoefficients = newRegressionCoefficients;
            newRegressionCoefficients = updateRegressionCoefficients(targetType, oldRegressionCoefficients, dataTypeTargets);
        }
        return newRegressionCoefficients;
    }
}
