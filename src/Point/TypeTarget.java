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
public class TypeTarget extends Point
{
    private ArrayList<Double> coordinates;

    private String target;

    public TypeTarget()
    {
        this.coordinates = new ArrayList<Double>();
        this.target = "";
    }

    public TypeTarget(int i)
    {
        this.coordinates = new ArrayList<Double>(i);
        this.target = "";
    }

    public TypeTarget(TypeTarget point)
    {
        this.coordinates = point.coordinates;
        this.target = point.target;
    }

    public TypeTarget(ArrayList<Double> coordinates, String target)
    {
        this.coordinates = coordinates;
        this.target = target;
    }

    public TypeTarget(double[] coordinates, String target)
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
    public String getTarget()
    {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(String target)
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
        else if(obj instanceof TypeTarget)
        {
            TypeTarget point = (TypeTarget) obj;
            final int dimensions = this.getDimensions();
            test = dimensions == point.getDimensions();
            if(test)
            {
                test &= this.getTarget().equals(point.getTarget());
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
        stringBuilder = new StringBuilder(this.target);
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
    public static TypeTarget add(final TypeTarget a, final TypeTarget b)
    {
        TypeTarget result;
        final int dimensions = a.getDimensions();
        result = new TypeTarget(dimensions);
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
    public static TypeTarget subtract(final TypeTarget a, final TypeTarget b)
    {
        TypeTarget result;
        final int dimensions = a.getDimensions();
        result = new TypeTarget(dimensions);
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
    public static TypeTarget scale(final TypeTarget point, final double factor)
    {
        TypeTarget result;
        final int dimensions = point.getDimensions();
        result = new TypeTarget(dimensions);
        result.setTarget(point.getTarget());
        for(int i = 0; i < dimensions; i++)
        {
            result.setCoordinate(i, point.getCoordinate(i)  * factor);
        }
        return result;
    }
}
