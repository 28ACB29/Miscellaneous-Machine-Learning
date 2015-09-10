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
public class Layer implements Serializable
{
    private static final long serialVersionUID = 1L;

    private ArrayList<Perceptron> perceptrons;

    private ArrayList<Double> layerInputs;

    /**
     *
     */
    public Layer()
    {
        this.layerInputs = new ArrayList<Double>();
        this.perceptrons = new ArrayList<Perceptron>();
    }

    /**
     *
     * @param inputSize
     * @param outputSize
     */
    public Layer(int inputSize, int outputSize)
    {
        this.layerInputs = new ArrayList<Double>();
        for(int i = 0; i < inputSize; i++)
        {
            this.layerInputs.add(0.0);
        }
        this.perceptrons = new ArrayList<Perceptron>();
        for(int i = 0; i < outputSize; i++)
        {
            this.perceptrons.add(new Perceptron(inputSize + 1));
        }
    }

    /**
     *
     * @param i
     * @return
     */
    public double getLayerInput(int i)
    {
        return this.layerInputs.get(i);
    }

    /**
     *
     * @param i
     * @param d
     */
    public void setLayerInput(int i,double d)
    {
        this.layerInputs.set(i, d);
        for(Perceptron perceptron : this.perceptrons)
        {
            perceptron.setInput(i + 1, d);
        }
    }

    /**
     *
     * @param i
     * @return
     */
    public double getLayerOutput(int i)
    {
        return this.perceptrons.get(i).output();
    }

    /**
     *
     * @return
     */
    public int getLayerInputSize()
    {
        return this.layerInputs.size();
    }

    /**
     *
     * @return
     */
    public int getLayerOutputSize()
    {
        return this.perceptrons.size();
    }

    /**
     *
     * @param perceptron
     * @param i
     * @return
     */
    public double getPerceptronWeight(int perceptron, int i)
    {
           return this.perceptrons.get(perceptron).getWeight(i);
    }

    /**
     *
     * @param perceptron
     * @param i
     * @param weight
     */
    public void setPerceptronWeight(int perceptron, int i, double weight)
    {
           this.perceptrons.get(perceptron).setWeight(i, weight);
    }

    /**
     *
     * @param perceptron
     * @return
     */
    public double getPerceptronError(int perceptron)
    {
        return this.perceptrons.get(perceptron).getError();
    }

    /**
     *
     * @param perceptron
     * @param error
     */
    public void setPerceptronError(int perceptron, double error)
    {
        this.perceptrons.get(perceptron).setError(error);
    }

    /**
     *
     */
    public void addInput()
    {
        this.layerInputs.add(0.0);
        for(Perceptron perceptron : this.perceptrons)
        {
            perceptron.addWeightAndInput();
        }
    }

    /**
     *
     */
    public void addPerceptron()
    {
        this.perceptrons.add(new Perceptron(this.layerInputs.size() + 1));
    }

}
