/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clustering;

import Point.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Arthur C. Baroi
 */
public class HierarchicalAgglomerative
{
    private Point node;

    private HierarchicalAgglomerative left;

    private HierarchicalAgglomerative right;

    /**
     *
     */
    public HierarchicalAgglomerative()
    {
        this.node = null;
        this.left = null;
        this.right = null;
    }

    /**
     *
     * @param node
     */
    public HierarchicalAgglomerative(Point node)
    {
        this.node = node;
        this.left = null;
        this.right = null;
    }

    /**
     *
     * @param left
     * @param right
     */
    public HierarchicalAgglomerative(HierarchicalAgglomerative left, HierarchicalAgglomerative right)
    {
        this.node = null;
        this.left = left;
        this.right = right;
    }

    /**
     *
     * @param node
     * @param left
     * @param right
     */
    public HierarchicalAgglomerative(Point node, HierarchicalAgglomerative left, HierarchicalAgglomerative right)
    {
        this.node = node;
        this.left = left;
        this.right = right;
    }

    /**
     * @return the node
     */
    public Point getNode()
    {
        return node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(Point node)
    {
        this.node = node;
    }

    /**
     * @return the left
     */
    public HierarchicalAgglomerative getLeft()
    {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(HierarchicalAgglomerative left)
    {
        this.left = left;
    }

    /**
     * @return the right
     */
    public HierarchicalAgglomerative getRight()
    {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(HierarchicalAgglomerative right)
    {
        this.right = right;
    }

    /**
     *
     * @return
     */
    public int getCount()
    {
        int currentCount;
        int leftCount;
        int rightCount;
        currentCount = 1;
        if(this.isLeaf())
        {
            leftCount = 0;
            rightCount = 0;
        }
        else
        {
            leftCount = this.left.getCount();
            rightCount = this.right.getCount();
        }
        return currentCount + leftCount + rightCount;
    }

    /**
     *
     * @return
     */
    public int getDepth()
    {
        int currentDepth;
        int leftDepth;
        int rightDepth;
        currentDepth = 1;
        if(this.isLeaf())
        {
            leftDepth = 0;
            rightDepth = 0;
        }
        else
        {
            leftDepth = this.left.getDepth();
            rightDepth = this.right.getDepth();
        }
        return currentDepth + Math.max(leftDepth, rightDepth);
    }

    /**
     *
     * @return
     */
    public boolean isLeaf()
    {
        return (this.left == null) && (this.right == null);
    }

    /**
     *
     * @return
     */
    public Set<Point> getPoints()
    {
        Set<Point> points;
        points = new TreeSet<Point>();
        if(this.isLeaf())
        {
            points.add(this.node);
        }
        else
        {
            points.addAll(this.left.getPoints());
            points.addAll(this.right.getPoints());
        }
        return points;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder("");
        if(this.isLeaf())
        {
            stringBuilder.append(this.node.toString());
        }
        else
        {
            stringBuilder.append("{");
            stringBuilder.append(this.left.toString());
            stringBuilder.append(", ");
            stringBuilder.append(this.right.toString());
            stringBuilder.append("}");
        }
        return stringBuilder.toString();
    }

    /**
     *
     * @param left
     * @param right
     * @return
     */
    public static double singleLinkage(final HierarchicalAgglomerative left, final HierarchicalAgglomerative right)
    {
        Set<Point> leftPoints;
        Set<Point> rightPoints;
        double distance;
        if((left != null) && (right != null))
        {
            distance = Double.MAX_VALUE;
            leftPoints = left.getPoints();
            rightPoints = right.getPoints();
            for(Point leftPoint : leftPoints)
            {
                for(Point rightPoint : rightPoints)
                {
                    distance = Math.min(distance, Point.euclideanDistance(leftPoint, rightPoint));
                }
            }
        }
        else
        {
            distance = -1.0;
        }
        return distance;
    }

    /**
     *
     * @param left
     * @param right
     * @return
     */
    public static double completeLinkage(final HierarchicalAgglomerative left, final HierarchicalAgglomerative right)
    {
        Set<Point> leftPoints;
        Set<Point> rightPoints;
        double distance;
        if((left != null) && (right != null))
        {
            distance = 0.0;
            leftPoints = left.getPoints();
            rightPoints = right.getPoints();
            for(Point leftPoint : leftPoints)
            {
                for(Point rightPoint : rightPoints)
                {
                    distance = Math.max(distance, Point.euclideanDistance(leftPoint, rightPoint));
                }
            }
        }
        else
        {
            distance = -1.0;
        }
        return distance;
    }

    /**
     *
     * @param left
     * @param right
     * @return
     */
    public static double centroid(final HierarchicalAgglomerative left, final HierarchicalAgglomerative right)
    {
        Set<Point> leftPoints;
        Set<Point> rightPoints;
        double distance;
        if((left != null) && (right != null))
        {
            distance = 0.0;
            leftPoints = left.getPoints();
            rightPoints = right.getPoints();
            for(Point leftPoint : leftPoints)
            {
                for(Point rightPoint : rightPoints)
                {
                    distance += Point.euclideanDistance(leftPoint, rightPoint);
                }
            }
            distance /= (double) leftPoints.size() * (double) rightPoints.size();
        }
        else
        {
            distance = -1.0;
        }
        return distance;
    }

    public Set<HierarchicalAgglomerative> split(int clusters)
    {
        Set<HierarchicalAgglomerative> clusterSet;
        if(clusters < 1)
        {
            clusterSet = null;
        }
        else
        {
            clusterSet = new HashSet<HierarchicalAgglomerative>();
            if(clusters == 1 || this.isLeaf())
            {
                clusterSet.add(this);
            }
            else if(!this.isLeaf())
            {
                if(this.left.getCount() >= this.right.getCount())
                {
                    clusterSet.addAll(this.left.split(clusters / 2 + (clusters % 2)));
                    clusterSet.addAll(this.right.split(clusters / 2));
                }
                else
                {
                    clusterSet.addAll(this.left.split(clusters / 2));
                    clusterSet.addAll(this.right.split(clusters / 2 + (clusters % 2)));
                }
            }
        }
        return clusterSet;
    }

    public static HierarchicalAgglomerative hierarchicalagglomerativeSingleLinkage(final Point[] points)
    {
        final int length;
        HierarchicalAgglomerative[] clusters;
        HierarchicalAgglomerative finalCluster;
        double[][] similarityMatrix;
        boolean[] activeCluster;
        double distance;
        int row;
        int column;
        length = points.length;
        clusters = new HierarchicalAgglomerative[length];
        finalCluster = new HierarchicalAgglomerative();
        similarityMatrix = new double[length][length];
        activeCluster = new boolean[length];
        row = -1;
        column = -1;
        for(int i = 0; i < length; i++)
        {
            clusters[i] = new HierarchicalAgglomerative(points[i]);
        }
        for(int o = 0; o < length; o++)
        {
            for(int p = 0; p < length; p++)
            {
                similarityMatrix[o][p] = HierarchicalAgglomerative.singleLinkage(clusters[o], clusters[p]);
            }
            activeCluster[o] = true;
        }
        for(int k = 0; k < length - 1; k++)
        {
            distance = Double.MAX_VALUE;
            for(int i = 0; i < length; i++)
            {
                for(int m = 0; m < length; m++)
                {
                    if((similarityMatrix[i][m] < distance) && (i != m) && activeCluster[i] && activeCluster[m])
                    {
                        distance = similarityMatrix[i][m];
                        row = i;
                        column = m;
                    }
                }
            }
            clusters[row] = new HierarchicalAgglomerative(clusters[row], clusters[column]);
            for(int j = 0; j < length; j++)
            {
                similarityMatrix[row][j] = HierarchicalAgglomerative.singleLinkage(clusters[row], clusters[j]);
                similarityMatrix[j][row] = HierarchicalAgglomerative.singleLinkage(clusters[row], clusters[j]);
            }
            activeCluster[column] = false;
        }
        for(int i = 0; i < length; i++)
        {
            if(activeCluster[i])
            {
                finalCluster = clusters[i];
            }
        }
        return finalCluster;
    }

    public static HierarchicalAgglomerative hierarchicalagglomerativeCompleteLinkage(final Point[] points)
    {
        final int length;
        HierarchicalAgglomerative[] clusters;
        HierarchicalAgglomerative finalCluster;
        double[][] similarityMatrix;
        boolean[] activeCluster;
        double distance;
        int row;
        int column;
        length = points.length;
        clusters = new HierarchicalAgglomerative[length];
        finalCluster = new HierarchicalAgglomerative();
        similarityMatrix = new double[length][length];
        activeCluster = new boolean[length];
        row = -1;
        column = -1;
        for(int i = 0; i < length; i++)
        {
            clusters[i] = new HierarchicalAgglomerative(points[i]);
        }
        for(int o = 0; o < length; o++)
        {
            for(int p = 0; p < length; p++)
            {
                similarityMatrix[o][p] = HierarchicalAgglomerative.completeLinkage(clusters[o], clusters[p]);
            }
            activeCluster[o] = true;
        }
        for(int k = 0; k < length - 1; k++)
        {
            distance = Double.MAX_VALUE;
            for(int i = 0; i < length; i++)
            {
                for(int m = 0; m < length; m++)
                {
                    if((similarityMatrix[i][m] < distance) && (i != m) && activeCluster[i] && activeCluster[m])
                    {
                        distance = similarityMatrix[i][m];
                        row = i;
                        column = m;
                    }
                }
            }
            clusters[row] = new HierarchicalAgglomerative(clusters[row], clusters[column]);
            for(int j = 0; j < length; j++)
            {
                similarityMatrix[row][j] = HierarchicalAgglomerative.completeLinkage(clusters[row], clusters[j]);
                similarityMatrix[j][row] = HierarchicalAgglomerative.completeLinkage(clusters[row], clusters[j]);
            }
            activeCluster[column] = false;
        }
        for(int i = 0; i < length; i++)
        {
            if(activeCluster[i])
            {
                finalCluster = clusters[i];
            }
        }
        return finalCluster;
    }

    public static HierarchicalAgglomerative hierarchicalagglomerativeCentroid(final Point[] points)
    {
        final int length;
        HierarchicalAgglomerative[] clusters;
        HierarchicalAgglomerative finalCluster;
        double[][] similarityMatrix;
        boolean[] activeCluster;
        double distance;
        int row;
        int column;
        length = points.length;
        clusters = new HierarchicalAgglomerative[length];
        finalCluster = new HierarchicalAgglomerative();
        similarityMatrix = new double[length][length];
        activeCluster = new boolean[length];
        row = -1;
        column = -1;
        for(int i = 0; i < length; i++)
        {
            clusters[i] = new HierarchicalAgglomerative(points[i]);
        }
        for(int o = 0; o < length; o++)
        {
            for(int p = 0; p < length; p++)
            {
                similarityMatrix[o][p] = HierarchicalAgglomerative.centroid(clusters[o], clusters[p]);
            }
            activeCluster[o] = true;
        }
        for(int k = 0; k < length - 1; k++)
        {
            distance = Double.MAX_VALUE;
            for(int i = 0; i < length; i++)
            {
                for(int m = 0; m < length; m++)
                {
                    if((similarityMatrix[i][m] < distance) && (i != m) && activeCluster[i] && activeCluster[m])
                    {
                        distance = similarityMatrix[i][m];
                        row = i;
                        column = m;
                    }
                }
            }
            clusters[row] = new HierarchicalAgglomerative(clusters[row], clusters[column]);
            for(int j = 0; j < length; j++)
            {
                similarityMatrix[row][j] = HierarchicalAgglomerative.centroid(clusters[row], clusters[j]);
                similarityMatrix[j][row] = HierarchicalAgglomerative.centroid(clusters[row], clusters[j]);
            }
            activeCluster[column] = false;
        }
        for(int i = 0; i < length; i++)
        {
            if(activeCluster[i])
            {
                finalCluster = clusters[i];
            }
        }
        return finalCluster;
    }

    public static String printClusterSplit(HierarchicalAgglomerative cluster, int divisions)
    {
        StringBuilder stringBuilder;
        Set<HierarchicalAgglomerative> clusterSet;
        Set<Point> pointSet;
        stringBuilder = new StringBuilder();
        clusterSet = cluster.split(divisions);
        for(Iterator<HierarchicalAgglomerative> clusterIterator = clusterSet.iterator(); clusterIterator.hasNext();)
        {
            pointSet = clusterIterator.next().getPoints();
            stringBuilder.append("{");
            for(Iterator<Point> pointIterator = pointSet.iterator(); pointIterator.hasNext();)
            {
                stringBuilder.append(pointIterator.next());
                if(pointIterator.hasNext())
                {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append("}\n");
        }
        return stringBuilder.toString();
    }
}
