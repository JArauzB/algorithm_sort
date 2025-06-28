/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.Sorters;

import java.util.Comparator;
import sortingservice.Queue;
import sortingservice.Sorter;

/**
 *
 * @author jorge
 * @param <T>
 */
public class HeapSorter<T> implements Sorter<T>{

    @Override
    public Queue<T> sort(Queue<T> q, Comparator<T> comparator) {
        return q;
    }
    
}
