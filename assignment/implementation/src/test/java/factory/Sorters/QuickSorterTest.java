/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.Sorters;


import factory.Queues.QueueForQuick;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import sortingservice.Queue;

/**
 *
 * @author jorge
 */
public class QuickSorterTest {

    @Test
    void getSortedQueueIntegersTest() {
        //Arrange
        QuickSorter quickSorter = new QuickSorter();
        QueueForQuick<Integer> simpleQueue = new QueueForQuick<>();
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
        simpleQueue.put(12);
        simpleQueue.put(9);
        simpleQueue.put(7);
        simpleQueue.put(5);
        simpleQueue.put(2);
        Queue actual = quickSorter.sort(simpleQueue, comparator);

        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actual.get()).isEqualTo(2);
            softly.assertThat(actual.get()).isEqualTo(5);
            softly.assertThat(actual.get()).isEqualTo(7);
            softly.assertThat(actual.get()).isEqualTo(9);
            softly.assertThat(actual.get()).isEqualTo(12);
        });
    }

    @Test
    void queueAlreadySortedTest() {
        //Arrange
        QuickSorter quickSorter = new QuickSorter();
        QueueForQuick<Integer> simpleQueue = new QueueForQuick<>();
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
        simpleQueue.put(1);
        simpleQueue.put(2);
        simpleQueue.put(3);
        simpleQueue.put(4);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);

        Queue actual = quickSorter.sort(simpleQueue, comparator);
        //Asser
        assertThat(actual.size()).isEqualTo(8);
    }

    @Test
    void queueReversedSortedTest() {
        //Arrange
        QuickSorter quickSorter = new QuickSorter();
        QueueForQuick<Integer> simpleQueue = new QueueForQuick<>();
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
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(4);
        simpleQueue.put(3);
        simpleQueue.put(2);
        simpleQueue.put(2);

        Queue actual = quickSorter.sort(simpleQueue, comparator);
        //Asser
        assertThat(actual.size()).isEqualTo(8);
    }

    @Test
    void getSortOfStringValuesTest() {
        //Arrange
        QuickSorter quickSorter = new QuickSorter();
        QueueForQuick<String> simpleQueue = new QueueForQuick<>();
        Comparator<String> comparator = Comparator.comparing(String::toString);
        //Act
        simpleQueue.put("A");
        simpleQueue.put("B");
        simpleQueue.put("R");
        simpleQueue.put("A");
        simpleQueue.put("C");
        simpleQueue.put("A");
        simpleQueue.put("C");
        simpleQueue.put("A");
        simpleQueue.put("B");
        simpleQueue.put("R");
        simpleQueue.put("A");
        simpleQueue.put("B");
        simpleQueue.put("C");
        simpleQueue.put("D");
        simpleQueue.put("C");
        Queue actual = quickSorter.sort(simpleQueue, comparator);
        //Assert
        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actual.get()).isEqualTo("A");
            softly.assertThat(actual.get()).isEqualTo("A");
            softly.assertThat(actual.get()).isEqualTo("A");
            softly.assertThat(actual.get()).isEqualTo("A");
            softly.assertThat(actual.get()).isEqualTo("A");
            softly.assertThat(actual.get()).isEqualTo("B");
            softly.assertThat(actual.get()).isEqualTo("B");
            softly.assertThat(actual.get()).isEqualTo("B");
            softly.assertThat(actual.get()).isEqualTo("C");
            softly.assertThat(actual.get()).isEqualTo("C");
            softly.assertThat(actual.get()).isEqualTo("C");
            softly.assertThat(actual.get()).isEqualTo("C");
            softly.assertThat(actual.get()).isEqualTo("D");
            softly.assertThat(actual.get()).isEqualTo("R");
            softly.assertThat(actual.get()).isEqualTo("R");
        });
    }

    @Test
    void getAmountOfRepeatedValuesTest() {
        //Arrange
        QuickSorter quickSorter = new QuickSorter();
        QueueForQuick<Integer> simpleQueue = new QueueForQuick<>();
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
        Queue actual = quickSorter.sort(simpleQueue, comparator);
        //Assert
        assertThat(actual.size()).isEqualTo(5);
    }

    @Test
    void getSortOfOneValuesTest() {
        //Arrange
        QuickSorter quickSorter = new QuickSorter();
        QueueForQuick<String> queue = new QueueForQuick<>();
        Comparator<String> comparator = Comparator.comparing(String::toString);
        //Act
        queue.put("B");
        Queue actual = quickSorter.sort(queue, comparator);
        //Assert
        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actual.get()).isEqualTo("B");
        });
    }

    @Test
    void getSortOfTwoValuesTest() {
        //Arrange
        QuickSorter quickSorter = new QuickSorter();
        QueueForQuick<String> queue = new QueueForQuick<>();
        Comparator<String> comparator = Comparator.comparing(String::toString);
        //Act
        queue.put("B");
        queue.put("A");
        Queue actual = quickSorter.sort(queue, comparator);
        //Assert
        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actual.get()).isEqualTo("A");
            softly.assertThat(actual.get()).isEqualTo("B");
        });
    }

    @Test
    void getSortOfThreeValuesTest() {
        //Arrange
        QuickSorter quickSorter = new QuickSorter();
        QueueForQuick<String> queue = new QueueForQuick<>();
        Comparator<String> comparator = Comparator.comparing(String::toString);
        //Act
        queue.put("LTMwOTY3OTE3MzcxODE2Njc5MTc");
        queue.put("Njc3NjA1MTA1MTk0Nzg1MDM");
        queue.put("MjE5MjM3NzE3Mjg5NjM5NTQ2MA");
        Queue actual = quickSorter.sort(queue, comparator);
        //Assert
        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actual.get()).isEqualTo("LTMwOTY3OTE3MzcxODE2Njc5MTc");
            softly.assertThat(actual.get()).isEqualTo("MjE5MjM3NzE3Mjg5NjM5NTQ2MA");
            softly.assertThat(actual.get()).isEqualTo("Njc3NjA1MTA1MTk0Nzg1MDM");
        });
    }

    @Test
    void getSortEmptyQueueTest() {
        //Arrange
        QuickSorter quickSorter = new QuickSorter();
        QueueForQuick<String> queue = new QueueForQuick<>();
        Comparator<String> comparator = Comparator.comparing(String::toString);
        //Act
        Queue actual = quickSorter.sort(queue, comparator);
        //Assert
        //Assert
        assertThat(actual).isEqualTo(queue);
    }

    @Test
    void longQueueTest() {
        //Arrange
        QuickSorter quickSorter = new QuickSorter();
        QueueForQuick<Integer> simpleQueue = new QueueForQuick<>();
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
        simpleQueue.put(6);
        simpleQueue.put(6);
        simpleQueue.put(5);
        simpleQueue.put(6);
        simpleQueue.put(6);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(24);
        simpleQueue.put(20);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(99);
        simpleQueue.put(30);
        simpleQueue.put(9);
        simpleQueue.put(103);
        simpleQueue.put(31);
        simpleQueue.put(27);
        simpleQueue.put(29);
        simpleQueue.put(6);
        simpleQueue.put(6);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(24);
        simpleQueue.put(20);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(99);
        simpleQueue.put(30);
        simpleQueue.put(9);
        simpleQueue.put(103);
        simpleQueue.put(31);
        simpleQueue.put(27);
        simpleQueue.put(29);
        simpleQueue.put(6);
        simpleQueue.put(6);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(24);
        simpleQueue.put(20);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(99);
        simpleQueue.put(30);
        simpleQueue.put(9);
        simpleQueue.put(103);
        simpleQueue.put(31);
        simpleQueue.put(27);
        simpleQueue.put(29);
        simpleQueue.put(6);
        simpleQueue.put(6);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(24);
        simpleQueue.put(20);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(99);
        simpleQueue.put(30);
        simpleQueue.put(9);
        simpleQueue.put(103);
        simpleQueue.put(31);
        simpleQueue.put(27);
        simpleQueue.put(29);
        simpleQueue.put(6);
        simpleQueue.put(6);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(24);
        simpleQueue.put(20);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(99);
        simpleQueue.put(30);
        simpleQueue.put(9);
        simpleQueue.put(103);
        simpleQueue.put(31);
        simpleQueue.put(27);
        simpleQueue.put(29);
        simpleQueue.put(6);
        simpleQueue.put(6);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(24);
        simpleQueue.put(20);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(99);
        simpleQueue.put(30);
        simpleQueue.put(9);
        simpleQueue.put(103);
        simpleQueue.put(31);
        simpleQueue.put(27);
        simpleQueue.put(29);
        simpleQueue.put(6);
        simpleQueue.put(6);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(24);
        simpleQueue.put(20);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(99);
        simpleQueue.put(30);
        simpleQueue.put(9);
        simpleQueue.put(103);
        simpleQueue.put(31);
        simpleQueue.put(27);
        simpleQueue.put(29);
        simpleQueue.put(6);
        simpleQueue.put(6);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(24);
        simpleQueue.put(20);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(99);
        simpleQueue.put(30);
        simpleQueue.put(9);
        simpleQueue.put(103);
        simpleQueue.put(31);
        simpleQueue.put(27);
        simpleQueue.put(29);
        simpleQueue.put(6);
        simpleQueue.put(6);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(24);
        simpleQueue.put(20);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(99);
        simpleQueue.put(30);
        simpleQueue.put(9);
        simpleQueue.put(103);
        simpleQueue.put(31);
        simpleQueue.put(27);
        simpleQueue.put(29);
        simpleQueue.put(6);
        simpleQueue.put(6);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(5);
        simpleQueue.put(24);
        simpleQueue.put(20);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(100);
        simpleQueue.put(99);
        simpleQueue.put(30);
        simpleQueue.put(9);
        simpleQueue.put(103);
        simpleQueue.put(31);
        simpleQueue.put(27);
        simpleQueue.put(29);

        simpleQueue.put(14);
        simpleQueue.put(6);
        simpleQueue.put(14);
        simpleQueue.put(44);
        simpleQueue.put(24);
        simpleQueue.put(20);
        simpleQueue.put(30);
        simpleQueue.put(9);
        simpleQueue.put(31);
        simpleQueue.put(27);
        quickSorter.sort(simpleQueue, comparator);

        boolean allAreEqualOrBigger = true;

        long countElements = simpleQueue.size();
        Integer previous = simpleQueue.get();
        for (int i = 0; i < countElements - 1; i++) {
            Integer current = simpleQueue.get();
            if (comparator.compare(previous, current) > 0) {
                allAreEqualOrBigger = false;
            }
            previous = current;
        }
        //Assert
        assertThat(allAreEqualOrBigger).isTrue();

    }

    @Test
    void longQueueWithDuplicatesTest() {
        //Arrange
        QuickSorter quickSorter = new QuickSorter();
        QueueForQuick<Integer> simpleQueue = new QueueForQuick<>();
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
        simpleQueue.put(6);
        simpleQueue.put(38);
        simpleQueue.put(11);
        simpleQueue.put(22);
        simpleQueue.put(6);
        simpleQueue.put(21);
        simpleQueue.put(34);
        simpleQueue.put(47);
        simpleQueue.put(30);
        simpleQueue.put(18);


        quickSorter.sort(simpleQueue, comparator);

        boolean allAreEqualOrBigger = true;

        long countElements = simpleQueue.size();
        Integer previous = simpleQueue.get();
        for (int i = 0; i < countElements - 1; i++) {
            Integer current = simpleQueue.get();
            if (comparator.compare(previous, current) > 0) {
                allAreEqualOrBigger = false;
            }
            previous = current;
        }
        //Assert
        assertThat(allAreEqualOrBigger).isTrue();

    }
}
