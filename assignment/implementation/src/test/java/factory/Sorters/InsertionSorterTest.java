/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.Sorters;

import factory.InsertionSorter;
import factory.SimpleQueue;
import static factory.ServiceFinder.getFactory;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import sortingservice.Queue;
import sortingservice.SorterConfiguration;
import sortingservice.SortingServiceFactory;

/**
 *
 * @author jorge
 */
public class InsertionSorterTest {

    @Test
    void getSortedQueueStringsTest() {
        //Arrange
        InsertionSorter InsertionSorter = new InsertionSorter();
        SimpleQueue<String> simpleQueue = new SimpleQueue<>();
        Comparator comparator = Comparator.comparing(String::toString);
        //Act
        //Example A S O R T I N G 
        simpleQueue.put("A");
        simpleQueue.put("S");
        simpleQueue.put("O");
        simpleQueue.put("R");
        simpleQueue.put("T");
        simpleQueue.put("I");
        simpleQueue.put("N");
        simpleQueue.put("G");

        Queue actual = InsertionSorter.sort(simpleQueue, comparator);

        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actual.get()).isEqualTo("A");
            softly.assertThat(actual.get()).isEqualTo("G");
            softly.assertThat(actual.get()).isEqualTo("I");
            softly.assertThat(actual.get()).isEqualTo("N");
            softly.assertThat(actual.get()).isEqualTo("O");
            softly.assertThat(actual.get()).isEqualTo("R");
            softly.assertThat(actual.get()).isEqualTo("S");
            softly.assertThat(actual.get()).isEqualTo("T");
        });
    }

    @Test
    void getAlreadySortedArrayTest() {
        //Arrange
        InsertionSorter InsertionSorter = new InsertionSorter();
        SimpleQueue<String> simpleQueue = new SimpleQueue<>();
        Comparator comparator = Comparator.comparing(String::toString);
        //Act
        //Example A S O R T I N G 
        simpleQueue.put("A");
        simpleQueue.put("B");

        Queue actual = InsertionSorter.sort(simpleQueue, comparator);

        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actual.get()).isEqualTo("A");
            softly.assertThat(actual.get()).isEqualTo("B");
        });
    }

    @Test
    void getReversedSortedArrayTest() {
        //Arrange
        InsertionSorter InsertionSorter = new InsertionSorter();
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();
        Comparator<Integer> comparator = (Integer one, Integer two) -> {
            if (one > two) {
                return 1;
            } else if (Objects.equals(one, two)) {
                return 0;
            } else {
                return -1;
            }
        };
        simpleQueue.put(5);
        simpleQueue.put(4);
        simpleQueue.put(3);
        simpleQueue.put(2);
        simpleQueue.put(1);
        simpleQueue.put(6);

        Queue actual = InsertionSorter.sort(simpleQueue, comparator);

        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actual.get()).isEqualTo(1);
            softly.assertThat(actual.get()).isEqualTo(2);
            softly.assertThat(actual.get()).isEqualTo(3);
            softly.assertThat(actual.get()).isEqualTo(4);
            softly.assertThat(actual.get()).isEqualTo(5);
        });
    }

    @Test
    void getSortedQueueIntegersTest() {
        //Arrange
        InsertionSorter InsertionSorter = new InsertionSorter();
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();
        Comparator<Integer> comparator = (Integer one, Integer two) -> {
            if (one > two) {
                return 1;
            } else if (Objects.equals(one, two)) {
                return 0;
            } else {
                return -1;
            }
        };
        //Act
        //Example A S O R T I N G 
        simpleQueue.put(1);
        simpleQueue.put(3);
        simpleQueue.put(8);
        simpleQueue.put(6);
        simpleQueue.put(1);

        Queue actual = InsertionSorter.sort(simpleQueue, comparator);

        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actual.get()).isEqualTo(1);
            softly.assertThat(actual.get()).isEqualTo(1);
            softly.assertThat(actual.get()).isEqualTo(3);
            softly.assertThat(actual.get()).isEqualTo(6);
            softly.assertThat(actual.get()).isEqualTo(8);
        });
    }

    @Test
    void getSortedQueueBigIntegersTest() {
        //Arrange
        InsertionSorter InsertionSorter = new InsertionSorter();
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();
        Comparator<Integer> comparator = (Integer one, Integer two) -> {
            if (one > two) {
                return 1;
            } else if (Objects.equals(one, two)) {
                return 0;
            } else {
                return -1;
            }
        };
        //Act
        simpleQueue.put(300);
        simpleQueue.put(1);
        simpleQueue.put(3);
        simpleQueue.put(8);
        simpleQueue.put(6);
        simpleQueue.put(2);
        simpleQueue.put(500);

        Queue actual = InsertionSorter.sort(simpleQueue, comparator);

        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actual.get()).isEqualTo(1);
            softly.assertThat(actual.get()).isEqualTo(2);
            softly.assertThat(actual.get()).isEqualTo(3);
            softly.assertThat(actual.get()).isEqualTo(6);
            softly.assertThat(actual.get()).isEqualTo(8);
            softly.assertThat(actual.get()).isEqualTo(300);
            softly.assertThat(actual.get()).isEqualTo(500);
        });
    }

    @Test
    void emptyQueueTest() {
        //Arrange
        InsertionSorter InsertionSorter = new InsertionSorter();
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();
        Comparator<Integer> comparator = (Integer one, Integer two) -> {
            if (one > two) {
                return 1;
            } else if (Objects.equals(one, two)) {
                return 0;
            } else {
                return -1;
            }
        };
        //Act

        Queue actual = InsertionSorter.sort(simpleQueue, comparator);

        //Assert
        assertThat(actual).isEmpty();
    }

}
