/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Point;

import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class Point
{
    private ArrayList<Double> coordinates;

    /**
     *
     */
    public Point()
    {
        this.coordinates = new ArrayList<Double>();
    }

    /**
     *
     * @param i
     */
    public Point(int i)
    {
        this.coordinates = new ArrayList<Double>(i);
    }

    /**
     *
     * @param point
     */
    public Point(Point point)
    {
        this.coordinates = point.coordinates;
    }

    /**
     *
     * @param coordinates
     */
    public Point(ArrayList<Double> coordinates)
    {
        this.coordinates = coordinates;
    }

    /**
     *
     * @param coordinates
     */
    public Point(double[] coordinates)
    {
        this.coordinates = new ArrayList<Double>();
        for(double coordinate : coordinates)
        {
            this.coordinates.add(coordinate);
        }
    }

    /**
     * @param i
     * @return the coordinate
     */
    public double getCoordinate(int i)
    {
        return coordinates.get(i);
    }

    /**
     * @param i
     * @param coordinate the coordinate to set
     */
    public void setCoordinate(int i, double coordinate)
    {
        this.coordinates.set(i, coordinate);
    }

    /**
     *
     * @return
     */
    public int getDimensions()
    {
        return this.coordinates.size();
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
        else if(obj instanceof Point)
        {
            Point point = (Point) obj;
            final int dimensions = this.getDimensions();
            test = dimensions == point.getDimensions();
            if(test)
            {
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
        stringBuilder = new StringBuilder("(");
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
    public static Point add(final Point a, final Point b)
    {
        Point result;
        final int dimensions = a.getDimensions();
        result = new Point(dimensions);
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
    public static Point subtract(final Point a, final Point b)
    {
        Point result;
        final int dimensions = a.getDimensions();
        result = new Point(dimensions);
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
    public static Point scale(final Point point, final double factor)
    {
        Point result;
        final int dimensions = point.getDimensions();
        result = new Point(dimensions);
        for(int i = 0; i < dimensions; i++)
        {
            result.setCoordinate(i, point.getCoordinate(i)  * factor);
        }
        return result;
    }

    /**
     *
     * @param source
     * @param destination
     * @return
     */
    public static double euclideanDistance(final Point source, final Point destination)
    {
        double distance;
        final int dimensions = source.getDimensions();
        distance = 0.0;
        if(dimensions== source.getDimensions())
        {
            for(int i = 0; i < dimensions; i++)
            {
                distance += (source.getCoordinate(i) - source.getCoordinate(i)) * (source.getCoordinate(i) - source.getCoordinate(i));
            }
        }
        else
        {
            throw new IllegalArgumentException("Coordinate dimensions do not match.");
        }
        return Math.sqrt(distance);
    }

    /**
     *
     * @param source
     * @param destination
     * @return
     */
    public static double manhattanDistance(final Point source, final Point destination)
    {
        double distance;
        final int dimensions = source.getDimensions();
        distance = 0.0;
        if(dimensions== source.getDimensions())
        {
            for(int i = 0; i < dimensions; i++)
            {
                distance += Math.abs(source.getCoordinate(i) - source.getCoordinate(i));
            }
        }
        else
        {
            throw new IllegalArgumentException("Coordinate dimensions do not match.");
        }
        return distance;
    }
    
}
