/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classification;

import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
class Observation
{
    private ArrayList<Boolean> featureVector;

    private boolean target;

    public Observation()
    {
        this.featureVector = new ArrayList<Boolean>();
        this.target = false;
    }

    public Observation(int i, boolean target)
    {
        this.featureVector = new ArrayList<Boolean>(i);
        this.target = target;
    }

    public Observation(ArrayList<Boolean> featureVector, boolean target)
    {
        this.featureVector = featureVector;
        this.target = target;
    }

    public boolean getFeature(int i)
    {
        return this.featureVector.get(i);
    }

    public boolean setFeature(int i, boolean b)
    {
        return this.featureVector.set(i, b);
    };

    public boolean getTarget()
    {
        return this.target;
    }

    public int getSize()
    {
        return this.featureVector.size();
    }

}
