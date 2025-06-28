/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import factory.Queues.QueueForHeap;
import static factory.ServiceFinder.getFactory;
import factory.Sorters.HeapSorter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import sortingservice.PriorityQueue;
import sortingservice.Queue;
import sortingservice.SortKind;
import sortingservice.Sorter;
import sortingservice.SorterConfiguration;
import sortingservice.SortingServiceFactory;

/**
 *
 * @author jorge
 */
public class SortingServiceTest {

    @Test
    void getNameTest() {
        //Arrange
        SortingServiceFactory sorterConfig = getFactory();
        SorterConfiguration sorter = sorterConfig.getSorterConfigurations()[0];// SELECTION
        //Act
        String actual = sorter.getName();
        String expected = SortKind.SELECTION.name();
        //Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getSortTypeTest() {
        //Arrange
        SortingServiceFactory sorterConfig = getFactory();
        SorterConfiguration sorter = sorterConfig.getSorterConfigurations()[0];// SELECTION
        //Act
        SortKind actual = sorter.getSortKind();
        SortKind expected = SortKind.SELECTION;
        //Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void usesPriorityQueueTest() {
        //Arrange
        SortingServiceFactory sorterConfig = getFactory();
        SorterConfiguration sorter = sorterConfig.getSorterConfigurations()[0];// SELECTION
        //Act
        boolean actual = sorter.usesPriorityQueue();
        boolean expected = false;
        //Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getQueueTest() {
        //Arrange
        SortingServiceFactory sorterConfig = getFactory();
        SorterConfiguration sorter = sorterConfig.getSorterConfigurations()[0];// SELECTION
        //Act
        Queue actual = sorter.getQueue();
        //Assert
        assertThat(actual).isExactlyInstanceOf(SimpleQueue.class);
    }

    @Test
    void getSorterTest() {
        //Arrange
        SortingServiceFactory sorterConfig = getFactory();
        SorterConfiguration sorter = sorterConfig.getSorterConfigurations()[0];// SELECTION
        //Act
        Sorter actual = sorter.getSorter();
        //Assert
        assertThat(actual).isExactlyInstanceOf(SelectionSorter.class);
    }

    @Test
    void getSortOfTwoValuesTest() {
        //Arrange
        SortingServiceFactory sorterConfig = getFactory();
        SorterConfiguration sorter = sorterConfig.getSorterConfigurations()[0];// SELECTION
        Queue simpleQueue = sorter.getQueue();
        Comparator comparator = Comparator.comparing(String::toString);
        //Act
        simpleQueue.put("B");
        simpleQueue.put("A");

//        Queue<String> sortedQueue = sorter.sort( simpleQueue, comparator );
        var sortedQueue = sorter.getSorter().sort(simpleQueue, comparator);
        long sizeExpected = 2;
        //Assert
        SoftAssertions.assertSoftly(softly -> {
            long sizeActual = 0;
            for (Iterator i = simpleQueue.iterator(); i.hasNext();) {
                i.next();
                sizeActual++;
            }
            softly.assertThat(sizeActual).isEqualTo(sizeExpected);
            softly.assertThat(sortedQueue.get()).isEqualTo("A");
            softly.assertThat(sortedQueue.get()).isEqualTo("B");
        });
    }

    @Test
    void sortLongStringTest() {
        //Arrange
        SortingServiceFactory sorterConfig = getFactory();
        SorterConfiguration sorter = sorterConfig.getSorterConfigurations()[0];// SELECTION
        Queue simpleQueue = sorter.getQueue();
        Comparator comparator = Comparator.comparing(String::toString);
        //Act
        simpleQueue.put("ODAyNTIwOTUxMjY3NDk0MzMwNA");
        simpleQueue.put("LTYwMTE3Mjc0MjE2NTI3MTA1MTA");
        simpleQueue.put("LTg2MjM0ODIwOTQ4NjczMzQ1NQ");

//        Queue<String> sortedQueue = sorter.sort( simpleQueue, comparator );
        var sortedQueue = sorter.getSorter().sort(simpleQueue, comparator);
        long sizeExpected = 3;
        //Assert
        SoftAssertions.assertSoftly(softly -> {
            long sizeActual = 0;
            for (Iterator i = simpleQueue.iterator(); i.hasNext();) {
                i.next();
                sizeActual++;
            }
            softly.assertThat(sizeActual).isEqualTo(sizeExpected);
            softly.assertThat(sortedQueue.get()).isEqualTo("LTYwMTE3Mjc0MjE2NTI3MTA1MTA");
            softly.assertThat(sortedQueue.get()).isEqualTo("LTg2MjM0ODIwOTQ4NjczMzQ1NQ");
            softly.assertThat(sortedQueue.get()).isEqualTo("ODAyNTIwOTUxMjY3NDk0MzMwNA");
        });
    }

    @Test
    void getSorterHeapTest() {
        //Arrange
        SortingServiceFactory sorterConfig = getFactory();
        SorterConfiguration sorter = sorterConfig.getSorterConfigurations()[3];// HEAP
        //Assert
        assertThat(sorter.getSorter()).isNotNull();
    }

    @Test
    void getPriorityQueueHeapTest() {
        //Arrange
        SortingServiceFactory sorterConfig = getFactory();
        SorterConfiguration sorter = sorterConfig.getSorterConfigurations()[3];// HEAP
        Comparator comparator = Comparator.comparing(String::toString);
        PriorityQueue queue = sorter.getPriorityQueue(comparator);
//        var sortedQueue = sorter.getSorter().sort(simpleQueue, comparator);

        //Assert
        assertThat(queue).isNotNull();
    }
}
