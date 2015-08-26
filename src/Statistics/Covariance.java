/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics;

import Classification.Logistic;
import Point.NumericalTarget;
import Point.Point;
import Point.TypeTarget;
import java.util.ArrayList;

/**
 *
 * @author Arthur C. Baroi
 */
public class Covariance
{

    /**
     *
     * @param dataPoints
     * @return
     */
    public static Matrix covariances(final ArrayList<Point> dataPoints)
    {
        final int vectors = dataPoints.size();
        final int dimensions = dataPoints.get(0).getDimensions();
        Matrix covarianceMatrix;
        Point meanPoint;
        covarianceMatrix = new Matrix(dimensions, dimensions);
        meanPoint = Moments.means(dataPoints);
        for(Point dataPoint : dataPoints)
        {
            for(int i = 0; i < dimensions; i++)
            {
                for(int j = 0; j < dimensions; j++)
                {
                    covarianceMatrix.setElement(i, j, covarianceMatrix.getElement(i, j) + (dataPoint.getCoordinate(i) - meanPoint.getCoordinate(i)) * (dataPoint.getCoordinate(j) - meanPoint.getCoordinate(j)));
                }
            }
        }
        covarianceMatrix = Matrix.scale(covarianceMatrix, 1.0 / (double) (vectors - 1));
        return covarianceMatrix;
    }

    /**
     *
     * @param dataPoints
     * @return
     */
    public static Point covarianceWithRespectToTarget(final ArrayList<NumericalTarget> dataPoints) {
        final int dimensions;
        Point covariancePoint;
        double coordinateResidual;
        dimensions = dataPoints.get(0).getDimensions();
        covariancePoint = new Point(dimensions);
        for(NumericalTarget dataPoint : dataPoints)
        {
            for(int i = 0; i < dimensions; i++)
            {
                coordinateResidual = dataPoint.getTarget() - dataPoint.getCoordinate(i);
                covariancePoint.setCoordinate(i, covariancePoint.getCoordinate(i) + (coordinateResidual * coordinateResidual));
            }
        }
        return covariancePoint;
    }

    /**
     *
     * @param targetType
     * @param dataPoints
     * @return
     */
    public static Point covarianceWithRespectToTarget(final String targetType, final ArrayList<TypeTarget> dataPoints) {
        final int dimensions;
        Point covariancePoint;
        double coordinateResidual;
        dimensions = dataPoints.get(0).getDimensions();
        covariancePoint = new Point(dimensions);
        for(TypeTarget dataPoint : dataPoints)
        {
            for(int i = 0; i < dimensions; i++)
            {
                coordinateResidual = Logistic.convertTypeToNumber(targetType, dataPoint) - dataPoint.getCoordinate(i);
                covariancePoint.setCoordinate(i, covariancePoint.getCoordinate(i) + (coordinateResidual * coordinateResidual));
            }
        }
        return covariancePoint;
    }

}
