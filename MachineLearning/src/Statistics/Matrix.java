/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics;

import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class Matrix
{
    private ArrayList<ArrayList<Double>> elements;

    /**
     *
     */
    public Matrix()
    {
        this.elements = new ArrayList<ArrayList<Double>>();
    }

    /**
     *
     * @param rows
     * @param columns
     */
    public Matrix(int rows, int columns)
    {
        this.elements = new ArrayList<ArrayList<Double>>(rows);
        for(int i = 0; i < rows; i++)
        {
            this.elements.set(i, new ArrayList<Double>(columns));
        }
    }

    /**
     *
     * @param matrix
     */
    public Matrix(Matrix matrix)
    {
        this.elements = new ArrayList<ArrayList<Double>>(matrix.elements.size());
        for(int i = 0; i < matrix.elements.size(); i++)
        {
            this.elements.set(i, new ArrayList<Double>(matrix.elements.get(i)));
        }
    }

    /**
     *
     * @param elements
     */
    public Matrix(ArrayList<ArrayList<Double>> elements)
    {
        this.elements = elements;
    }

    /**
     *
     * @param row
     * @param column
     * @return
     */
    public double getElement(int row, int column)
    {
        return this.elements.get(row).get(column);
    }

    /**
     *
     * @param row
     * @param column
     * @param value
     */
    public void setElement(int row, int column, double value)
    {
        this.elements.get(row).set(column, value);
    }

    /**
     *
     * @param row
     * @return
     */
    public ArrayList<Double> getRow(int row)
    {
        return this.elements.get(row);
    }

    /**
     *
     * @param row
     * @param rowVector
     */
    public void setRow(int row, ArrayList<Double> rowVector)
    {
        this.elements.set(row, rowVector);
    }

    /**
     *
     * @param column
     * @return
     */
    public ArrayList<Double> getColumn(int column)
    {
        ArrayList<Double> columnVector;
        columnVector = new ArrayList<Double>();
        for(ArrayList<Double> rowVector : this.elements)
        {
            columnVector.add(rowVector.get(column));
        }
        return columnVector;
    }

    /**
     *
     * @param column
     * @param columnVector
     */
    public void setColumn(int column, ArrayList<Double> columnVector)
    {
        int rowIndex;
        rowIndex = 0;
        for(ArrayList<Double> rowVector : this.elements)
        {
            rowVector.set(column, columnVector.get(rowIndex));
            rowIndex++;
        }
    }

    /**
     *
     * @param matrix
     * @param factor
     * @return
     */
    public static Matrix scale(final Matrix matrix, final double factor)
    {
        Matrix result;
        final int rows = matrix.elements.size();
        final int columns = matrix.elements.get(0).size();
        result = new Matrix(rows, columns);
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                result.setElement(i, j, matrix.getElement(i, j) * factor);
            }
        }
        return result;
    }
}
