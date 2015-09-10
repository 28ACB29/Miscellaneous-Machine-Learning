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
public class NaiveBayesian
{

    /**
     *
     * @param Observations
     * @param target
     * @return
     */
    public static int[] countOccurences(Observation[] Observations, boolean target)
    {
        int[] occurences;
        occurences = new int[Observations[0].getSize()];
        for(Observation observation : Observations)
        {
            for(int j = 0; j < occurences.length; j++)
            {
                if(observation.getFeature(j) && observation.getTarget() == target)
                {
                    occurences[j]++;
                }
            }
        }
        return occurences;
    }

    /**
     *
     * @param Observations
     * @param target
     * @param applySmoothing
     * @return
     */
    public static double[] calculateProbabilities(Observation[] Observations, boolean target, boolean applySmoothing)
    {
        double[] probabilities;
        int[] occurences;
        probabilities = new double[Observations[0].getSize()];
        occurences = NaiveBayesian.countOccurences(Observations, target);
        for(int i = 0; i < probabilities.length; i++)
        {
            if(applySmoothing)
            {
                probabilities[i] = (double) occurences[i] / (double) Observations.length;
            }
            else
            {
                probabilities[i] = (double) (occurences[i] + 1) / (double) (Observations.length + 2);
            }
        }
        return probabilities;
    }

    /**
     *
     * @param Observations
     * @param applySmoothing
     * @return
     */
    public static double[][] trainClassifier(Observation[] Observations, boolean applySmoothing)
    {
        double[][] weights;
        weights = new double[2][Observations[0].getSize()];
        weights[0] = NaiveBayesian.calculateProbabilities(Observations, false, applySmoothing);
        weights[1] = NaiveBayesian.calculateProbabilities(Observations, true, applySmoothing);
        return weights;
    }

    /**
     *
     * @param featureVector
     * @param Observations
     * @param applySmoothing
     * @return
     */
    public static boolean predictFeatures(ArrayList<Boolean> featureVector, Observation[] Observations, boolean applySmoothing)
    {
        double[][] weights;
        double probabilityTargetIsFalse;
        double probabilityTargetIsTrue;
        weights = NaiveBayesian.trainClassifier(Observations, applySmoothing);
        probabilityTargetIsFalse = 1.0;
        probabilityTargetIsTrue = 1.0;
        for(int i = 0; i < featureVector.size(); i++)
        {
            if(featureVector.get(i))
            {
                probabilityTargetIsFalse *= weights[0][i];
                probabilityTargetIsTrue *= weights[1][i];
            }
            else
            {
                probabilityTargetIsFalse *= 1 - weights[0][i];
                probabilityTargetIsTrue *= 1 - weights[1][i];
            }
        }
        return probabilityTargetIsTrue > probabilityTargetIsFalse;
    }
}
