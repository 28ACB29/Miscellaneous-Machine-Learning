/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetwork;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class Perceptron implements Serializable
{
    private static final long serialVersionUID = 1L;

    private ArrayList<Double> inputs;

    private ArrayList<Double> weights;

    private double error;

    /**
     *
     */
    public Perceptron()
    {
        this.weights = new ArrayList<Double>(1);
        this.inputs = new ArrayList<Double>(1);
        this.inputs.set(0, 1.0);
        this.error = 0.0;
    }


    /**
     *
     * @param size
     */
    public Perceptron(int size)
    {
        this.weights = new ArrayList<Double>();
        this.inputs = new ArrayList<Double>();
        this.weights.add(1.0);
        this.inputs.add(1.0);
        for(int i = 1; i < size; i++)
        {
            this.weights.add(1.0);
            this.inputs.add(0.0);
        }
        this.error = 0.0;
    }

    /**
     *
     * @param weights
     */
    public Perceptron(ArrayList<Double> weights)
    {
        this.weights = weights;
        this.inputs = new ArrayList<Double>(weights.size());
        this.inputs.set(0, 1.0);
        this.error = 0.0;
    }

    private static double sigmoid(double input)
    {
        return (1.0 + Math.tanh(input / 2.0)) / 2.0;
    }

    /**
     *
     * @param i
     * @return
     */
    public double getInput(int i)
    {
        return this.inputs.get(i);
    }

    /**
     *
     * @param i
     * @param d
     */
    public void setInput(int i,double d)
    {
        this.inputs.set(i, d);
    }

    /**
     *
     * @param i
     * @return
     */
    public double getWeight(int i)
    {
        return this.weights.get(i);
    }

    /**
     *
     * @param i
     * @param d
     */
    public void setWeight(int i,double d)
    {
        this.weights.set(i, d);
    }

    /**
     * @return the error
     */
    public double getError()
    {
        return this.error;
    }

    /**
     * @param error the error to set
     */
    public void setError(double error)
    {
        this.error = error;
    }

    /**
     *
     */
    public void addWeightAndInput()
    {
        this.weights.add(1.0);
        this.inputs.add(0.0);
    }

    /**
     *
     * @return
     */
    public double output()
    {
        double result;
        result = 0.0;
        for(int i = 0; i < this.inputs.size(); i++)
        {
            result += this.weights.get(i) * this.inputs.get(i);
        }
        result = Perceptron.sigmoid(result);
        return result;
    }
}
