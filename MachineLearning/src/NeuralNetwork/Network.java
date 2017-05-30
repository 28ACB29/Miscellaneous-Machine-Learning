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
public class Network implements Serializable
{
    private static final long serialVersionUID = 1L;

    private ArrayList<Layer> networkLayers;

    private ArrayList<String> users;

    /**
     *
     */
    public Network()
    {
    }

    /**
     *
     * @param layerSizes
     */
    public Network(int[] layerSizes)
    {
        this.networkLayers = new ArrayList<Layer>();
        for(int i = 0; i < layerSizes.length - 1; i++)
        {
            this.networkLayers.add(new Layer(layerSizes[i], layerSizes[i + 1]));
        }
        this.users = new ArrayList<String>();
        for(int i = 0; i < layerSizes[layerSizes.length - 1]; i++)
        {
            this.users.add("");
        }
    }

    /**
     *
     * @param networkLayers
     */
    public Network(ArrayList<Layer> networkLayers)
    {
        this.networkLayers = networkLayers;
        this.users = new ArrayList<String>(this.networkLayers.get(this.networkLayers.size() - 1).getLayerOutputSize());
    }

    /**
     *
     * @param i
     * @return
     */
    public double getNetworkInput(int i)
    {
        return this.networkLayers.get(0).getLayerInput(i);
    }

    /**
     *
     * @param i
     * @param d
     */
    public void setNetworkInput(int i,double d)
    {
        this.networkLayers.get(0).setLayerInput(i, d);
    }

    /**
     *
     * @param i
     * @return
     */
    public double getNetworkOutput(int i)
    {
           return this.networkLayers.get(this.networkLayers.size() - 1).getLayerOutput(i);
    }

    /**
     *
     * @param i
     * @return
     */
    public String getUser(int i)
    {
        return this.users.get(i);
    }

    /**
     *
     * @param i
     * @param user
     */
    public void setUser(int i, String user)
    {
        this.users.set(i, user);
    }

    /**
     *
     * @return
     */
    public int getInputSize()
    {
        return this.networkLayers.get(0).getLayerInputSize();
    }

    /**
     *
     * @return
     */
    public int getOutputSize()
    {
        return this.networkLayers.get(this.networkLayers.size() - 1).getLayerOutputSize();
    }

    /**
     *
     * @param layer
     */
    public void addPerceptron(int layer)
    {
        this.networkLayers.get(layer).addPerceptron();
        if(layer > this.networkLayers.size() - 1)
        {
            this.networkLayers.get(layer + 1).addInput();
        }
    }

    /**
     *
     * @param user
     */
    public void addUser(String user)
    {
        this.users.add(user);
        this.networkLayers.get(this.networkLayers.size() - 1).addPerceptron();
    }

    /**
     *
     * @param user
     * @return
     */
    public int indexOf(String user)
    {
        return this.users.indexOf(user);
    }

    /**
     *
     * @param user
     * @return
     */
    public ArrayList<Double> generateTargetList(final String user)
    {
        ArrayList<Double> target;
        int index;
        target = new ArrayList<Double>();
        index = this.users.indexOf(user);
        for(int i = 0; i < this.users.size(); i++)
        {
            if(i == index)
            {
                target.add(1.0);
            }
            else
            {
                target.add(0.0);
            }
        }
        return target;
    }

    /**
     *
     * @param input
     */
    public void forwardPropagate(final ArrayList<Double> input)
    {
        for(int i = 0; i < input.size(); i++)
        {
            this.networkLayers.get(0).setLayerInput(i, input.get(i));
        }
        for(int i = 1; i < this.networkLayers.size(); i++)
        {
            for(int j = 0; j < this.networkLayers.get(i).getLayerOutputSize(); j++)
            {
                this.networkLayers.get(i).setLayerInput(i - 1, this.networkLayers.get(i).getLayerOutput(j));
            }
        }
    }

    private double backPropagationError(final int layer, final int perceptron, final double target)
    {
        double error;
        final double output;
        final int nextLayerOutputSize;
        error = 0.0;
        output = this.networkLayers.get(layer).getLayerOutput(perceptron);
        if(layer == this.networkLayers.size() - 1)
        {
            error = output * (1 - output) * (target - output);
            this.networkLayers.get(layer).setPerceptronError(perceptron, error);
        }
        else
        {
            nextLayerOutputSize = this.networkLayers.get(layer + 1).getLayerOutputSize();
            for(int j = 0; j < nextLayerOutputSize; j++)
            {
                error += output * (1 - output) * this.networkLayers.get(layer + 1).getPerceptronError(j) * this.networkLayers.get(layer + 1).getPerceptronWeight(j, perceptron + 1);
            }
            this.networkLayers.get(layer).setPerceptronError(perceptron, error);
        }
        return error;
    }

    private boolean isGoodEnough(final ArrayList<Double> target)
    {
        final int layerOutputSize;
        double layerOutputComponent;
        Double targetComponent;
        double distance;
        double threshold;
        distance = 0.0;
        threshold = 0.05;
        layerOutputSize = this.networkLayers.get(this.networkLayers.size() - 1).getLayerOutputSize();
        for(int i = 0; i < layerOutputSize; i++)
        {
            layerOutputComponent = this.networkLayers.get(this.networkLayers.size() - 1).getLayerOutput(i);
            targetComponent = target.get(i);
            distance += (layerOutputComponent - targetComponent) * (layerOutputComponent - targetComponent);
        }
        distance = Math.sqrt(distance);
        return distance <= threshold;
    }

    private void updateWeights(final ArrayList<Double> target)
    {
        double alpha;
        int networkSize;
        int currentLayerOutputSize;
        int currentLayerPerceptronSize;
        Double targetComponent;
        double backPropagationError;
        alpha = 0.001;
        networkSize = this.networkLayers.size();
        for(int i = networkSize - 1; i > -1; i--)
        {
            currentLayerOutputSize = this.networkLayers.get(i).getLayerOutputSize();
            for(int j = 0; j < currentLayerOutputSize; j++)
            {
                currentLayerPerceptronSize = this.networkLayers.get(i).getLayerInputSize() + 1;
                for(int k = 0; k < currentLayerPerceptronSize; k++)
                {
                    if(i != (networkSize - 1))
                    {
                        targetComponent = 0.0;
                    }
                    else
                    {
                        targetComponent = target.get(j);
                    }
                    backPropagationError = this.backPropagationError(i, j, targetComponent);
                    this.networkLayers.get(i).setPerceptronWeight(j, k, this.networkLayers.get(i).getPerceptronWeight(j, k) + alpha * backPropagationError);
                }
            }
        }
    }

    /**
     *
     * @param input
     * @param target
     */
    public void backPropagate(final ArrayList<Double> input, final ArrayList<Double> target)
    {
        if(target.size() == this.networkLayers.get(this.networkLayers.size() - 1).getLayerOutputSize())
        {
//            while(!this.isGoodEnough(target))
//            {
                this.forwardPropagate(input);
                this.updateWeights(target);
//            }
        }
    }
}
