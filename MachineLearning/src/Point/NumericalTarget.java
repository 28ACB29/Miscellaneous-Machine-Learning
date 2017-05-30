/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Point;

import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class NumericalTarget extends Point
{
    private ArrayList<Double> coordinates;

    private double target;

    /**
     *
     */
    public NumericalTarget()
    {
        this.coordinates = new ArrayList<Double>();
        this.target = 0.0;
    }

    /**
     *
     * @param i
     */
    public NumericalTarget(int i)
    {
        this.coordinates = new ArrayList<Double>(i);
        this.target = 0.0;
    }

    /**
     *
     * @param point
     */
    public NumericalTarget(NumericalTarget point)
    {
        this.coordinates = point.coordinates;
        this.target = point.target;
    }

    /**
     *
     * @param coordinates
     * @param target
     */
    public NumericalTarget(ArrayList<Double> coordinates, double target)
    {
        this.coordinates = coordinates;
        this.target = target;
    }

    /**
     *
     * @param coordinates
     * @param target
     */
    public NumericalTarget(double[] coordinates, double target)
    {
        this.coordinates = new ArrayList<Double>();
        for(double coordinate : coordinates)
        {
            this.coordinates.add(coordinate);
        }
        this.target = target;
    }

    /**
     * @return the target
     */
    public double getTarget()
    {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(double target)
    {
        this.target = target;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj)
    {
        boolean test;
        if(obj == null)
        {
            test = false;
        }
        else if(obj instanceof NumericalTarget)
        {
            NumericalTarget point = (NumericalTarget) obj;
            final int dimensions = this.getDimensions();
            test = dimensions == point.getDimensions();
            if(test)
            {
                test &= this.getTarget() == point.getTarget();
                for(int i = 0; i < dimensions; i++)
                {
                    test &= this.getCoordinate(i) == point.getCoordinate(i);
                }
            }
            else
            {
                test = false;
            }
        }
        else
        {
            test = false;
        }
        return test;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {

        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder(Double.toString(this.target));
        stringBuilder.append("(");
        final int lastIndex = this.getDimensions() - 1;
        for(int i = 0; i < lastIndex; i++)
        {
            stringBuilder.append(this.getCoordinate(i));
            stringBuilder.append(", ");
        }
            stringBuilder.append(this.getCoordinate(lastIndex));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static NumericalTarget add(final NumericalTarget a, final NumericalTarget b)
    {
        NumericalTarget result;
        final int dimensions = a.getDimensions();
        result = new NumericalTarget(dimensions);
        if(dimensions== b.getDimensions())
        {
            for(int i = 0; i < dimensions; i++)
            {
                result.setCoordinate(i, a.getCoordinate(i) + b.getCoordinate(i));
            }
        }
        else
        {
            throw new IllegalArgumentException("Coordinate dimensions do not match.");
        }
        return result;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static NumericalTarget subtract(final NumericalTarget a, final NumericalTarget b)
    {
        NumericalTarget result;
        final int dimensions = a.getDimensions();
        result = new NumericalTarget(dimensions);
        if(dimensions== b.getDimensions())
        {
            for(int i = 0; i < dimensions; i++)
            {
                result.setCoordinate(i, a.getCoordinate(i) - b.getCoordinate(i));
            }
        }
        else
        {
            throw new IllegalArgumentException("Coordinate dimensions do not match.");
        }
        return result;
    }

    /**
     *
     * @param point
     * @param factor
     * @return
     */
    public static NumericalTarget scale(final NumericalTarget point, final double factor)
    {
        NumericalTarget result;
        final int dimensions = point.getDimensions();
        result = new NumericalTarget(dimensions);
        result.setTarget(point.getTarget());
        for(int i = 0; i < dimensions; i++)
        {
            result.setCoordinate(i, point.getCoordinate(i) * factor);
        }
        return result;
    }
}
