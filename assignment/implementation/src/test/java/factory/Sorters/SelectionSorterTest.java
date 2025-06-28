/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.Sorters;

import factory.SelectionSorter;
import factory.SimpleQueue;
import java.util.Comparator;
import java.util.Objects;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import sortingservice.Queue;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;

/**
 *
 * @author jorge
 */
public class SelectionSorterTest {

    @Test
    void getSortedQueueIntegersTest() {
        //Arrange
        SelectionSorter selectionSorter = new SelectionSorter();
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
        simpleQueue.put(5);
        simpleQueue.put(2);
        simpleQueue.put(1);
        simpleQueue.put(3);
        simpleQueue.put(4);
        Queue actual = selectionSorter.sort(simpleQueue, comparator);

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
    void getAmountOfRepeatedValuesTest() {
        //Arrange
        SelectionSorter selectionSorter = new SelectionSorter();
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
        simpleQueue.put(25);
        simpleQueue.put(25);
        simpleQueue.put(25);
        simpleQueue.put(25);
        simpleQueue.put(25);
        Queue actual = selectionSorter.sort(simpleQueue, comparator);
        //Assert
        assertThat(actual.size()).isEqualTo(5);
    }
    
    @Test
    void getSortOfTwoValuesTest() {
        //Arrange
        SelectionSorter selectionSorter = new SelectionSorter();
        SimpleQueue<String> simpleQueue = new SimpleQueue<>();
        Comparator<String> comparator = Comparator.comparing(String::toString);
        //Act
        simpleQueue.put("B");
        simpleQueue.put("A");
        Queue actual = selectionSorter.sort(simpleQueue, comparator);
        //Assert
        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actual.get()).isEqualTo("A");
            softly.assertThat(actual.get()).isEqualTo("B");
        });
    }
    
        @Test
    void getSortedListTest() {
        //Arrange
        SelectionSorter selectionSorter = new SelectionSorter();
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
        simpleQueue.put(10);
        simpleQueue.put(9);
        simpleQueue.put(8);
        simpleQueue.put(1);
        simpleQueue.put(0);
        simpleQueue.put(2);
        Queue actual = selectionSorter.sort(simpleQueue, comparator);
        //Assert
        assertThat(actual.size()).isEqualTo(6);
    }
}
