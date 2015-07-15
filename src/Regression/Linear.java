/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Regression;

import Point.NumericalTarget;
import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class Linear
{
    private ArrayList<Double> theta;
    
    private double theta0;

    private double theta1;

    private double theta2;

    private double theta3;

    private double theta4;

    /**
     *
     */
    public Linear()
    {
        this.theta0 = 0.0;
        this.theta1 = 0.0;
        this.theta2 = 0.0;
        this.theta3 = 0.0;
        this.theta4 = 0.0;
    }

    public Linear(ArrayList<Double> theta)
    {
        this.theta = theta;
    }

    /**
     *
     * @param theta0
     * @param theta1
     * @param theta2
     * @param theta3
     * @param theta4
     */
    public Linear(double theta0, double theta1, double theta2, double theta3, double theta4)
    {
        this.theta0 = theta0;
        this.theta1 = theta1;
        this.theta2 = theta2;
        this.theta3 = theta3;
        this.theta4 = theta4;
    }

    /**
     *
     * @param theta1
     * @param theta2
     * @param theta3
     * @param theta4
     */
    public Linear(double theta1, double theta2, double theta3, double theta4)
    {
        this.theta1 = theta1;
        this.theta2 = theta2;
        this.theta3 = theta3;
        this.theta4 = theta4;
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
     * @return the theta3
     */
    public double getTheta3()
    {
        return theta3;
    }

    /**
     * @param theta3 the theta3 to set
     */
    public void setTheta3(double theta3)
    {
        this.theta3 = theta3;
    }

    /**
     * @return the theta4
     */
    public double getTheta4()
    {
        return theta4;
    }

    /**
     * @param theta4 the theta4 to set
     */
    public void setTheta4(double theta4)
    {
        this.theta4 = theta4;
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
        final double oldTheta0;
        final double oldTheta1;
        final double oldTheta2;
        final double oldTheta3;
        final double oldTheta4;
        final double newTheta0;
        final double newTheta1;
        final double newTheta2;
        final double newTheta3;
        final double newTheta4;
        double distance;
        threshold = 0.05;
        oldTheta0 = oldRegressionCoefficients.getTheta0();
        oldTheta1 = oldRegressionCoefficients.getTheta1();
        oldTheta2 = oldRegressionCoefficients.getTheta2();
        oldTheta3 = oldRegressionCoefficients.getTheta3();
        oldTheta4 = oldRegressionCoefficients.getTheta4();
        newTheta0 = newRegressionCoefficients.getTheta0();
        newTheta1 = newRegressionCoefficients.getTheta1();
        newTheta2 = newRegressionCoefficients.getTheta2();
        newTheta3 = newRegressionCoefficients.getTheta3();
        newTheta4 = newRegressionCoefficients.getTheta4();
        distance = Math.sqrt((oldTheta0 - newTheta0) * (oldTheta1 - newTheta1) + (oldTheta1 - newTheta1) * (oldTheta1 - newTheta1) + (oldTheta2 - newTheta2) * (oldTheta2 - newTheta2) + (oldTheta3 - newTheta3) * (oldTheta3 - newTheta3) + (oldTheta4 - newTheta4) * (oldTheta4 - newTheta4));
        return distance < threshold;
    }

    /**
     *
     * @param dataPoints
     * @return
     */
    public static Linear linearRegression(final ArrayList<NumericalTarget> dataPoints)
    {
        Linear oldRegressionCoefficients;
        NumericalTarget covarianceNumericalTarget;
        Linear newRegressionCoefficients;
        oldRegressionCoefficients = new Linear(0.0, 0.0, 0.0, 0.0, 0.0);
        covarianceNumericalTarget = NumericalTarget.covariances(dataPoints);
        newRegressionCoefficients = new Linear(0.0, covarianceNumericalTarget.getX1(), covarianceNumericalTarget.getX2(), covarianceNumericalTarget.getX3(), covarianceNumericalTarget.getX4());
        while(!isCloseEnough(oldRegressionCoefficients, newRegressionCoefficients))
        {
            oldRegressionCoefficients = newRegressionCoefficients;
            newRegressionCoefficients = updateRegressionCoefficients(oldRegressionCoefficients, dataPoints);
        }
        return newRegressionCoefficients;
    }
}
