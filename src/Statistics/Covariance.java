/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics;

import Point.Point;
import static Statistics.Moments.means;
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
    public static Point covariances(final ArrayList<Point> dataPoints)
    {
        Point covariancePoint;
        Point meanPoint;
        covariancePoint = new Point();
        meanPoint = means(dataPoints);
        for(Point dataPoint : dataPoints)
        {
            covariancePoint.setX1(covariancePoint.getX1() + (dataPoint.getX1() - meanPoint.getX1()) * (dataPoint.getY() - meanPoint.getY()));
            covariancePoint.setX2(covariancePoint.getX2() + (dataPoint.getX2() - meanPoint.getX2()) * (dataPoint.getY() - meanPoint.getY()));
            covariancePoint.setX3(covariancePoint.getX3() + (dataPoint.getX3() - meanPoint.getX3()) * (dataPoint.getY() - meanPoint.getY()));
            covariancePoint.setX4(covariancePoint.getX4() + (dataPoint.getX4() - meanPoint.getX4()) * (dataPoint.getY() - meanPoint.getY()));
            covariancePoint.setY(covariancePoint.getY() + (dataPoint.getY() - meanPoint.getY()) * (dataPoint.getY() - meanPoint.getY()));
        }
        covariancePoint = Point.scale(covariancePoint, 1.0 / (double) dataPoints.size());
        return covariancePoint;
    }

}
