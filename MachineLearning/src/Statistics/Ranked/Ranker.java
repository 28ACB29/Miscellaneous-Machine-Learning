/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics.Ranked;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Arthur C. Baroi
 * @param <T>
 */
public class Ranker<T extends Comparable<T>> implements Iterable<Map.Entry<Integer, T>>
{
    private ArrayList<T> rank;

    /**
     *
     */
    public Ranker()
    {
        this.rank = new ArrayList<T>();
    }

    /**
     *
     * @param data
     */
    public Ranker(Iterable<T> data)
    {
        this.rank = new ArrayList<T>();
        for(T datum : data)
        {
            if(!this.rank.contains(datum))
            {
                this.rank.add(datum);
            }
        }
        Collections.sort(rank);
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<Map.Entry<Integer, T>> iterator()
    {
        return new RankerIterator();
    }

    /**
     *
     * @param element
     */
    public void put(T element)
    {
        int index;
        if(!this.rank.contains(element))
        {
            for(index = 0; this.rank.get(index).compareTo(element) < 0 && index < this.rank.size(); index++)
            {
                ;
            }
            this.rank.add(index, element);
        }
    }

    /**
     *
     * @param element
     */
    public void remove(T element)
    {
        if(this.rank.contains(element))
        {
            this.rank.remove(element);
        }
    }

    /**
     *
     * @param element
     * @return
     */
    public int getRank(T element)
    {
        return this.rank.indexOf(element);
    }

    public List<Integer> convertToRankedList(Iterable<T> data)
    {
        ArrayList<Integer> rankedList;
        rankedList = new ArrayList<Integer>();
        for(T datum : data)
        {
            if(this.rank.contains(datum))
            {
                rankedList.add(this.rank.indexOf(datum));
            }
        }
        return rankedList;
    }

    private class RankerIterator implements Iterator<Map.Entry<Integer, T>>
    {

        private int index;

        public RankerIterator()
        {
            this.index = -1;
        }

        @Override
        public boolean hasNext()
        {
            return this.index < rank.size();
        }

        @Override
        public Map.Entry<Integer, T> next()
        {
            this.index++;
            return new AbstractMap.SimpleEntry<Integer, T>(this.index, rank.get(index));
        }

        @Override
        public void remove()
        {
            rank.remove(this.index);
        }
    }
}
