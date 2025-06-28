/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.Queues;

import java.util.Comparator;
import java.util.Objects;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author jorge
 */
public class QueueForHeapTest {
    
    private Comparator<Integer> comparator = (Integer one, Integer two) -> {
        if (one > two) {
            return 1;
        } else if (Objects.equals(one, two)) {
            return 0;
        } else {
            return -1;
        }
    };
    
    @Test
    void getComparatorTest() {
        //Arrange
        QueueForHeap queue = new QueueForHeap(comparator);
        //Assert
        assertThat(queue.getComparator()).isEqualTo(comparator);
    }
    
    @Test
    void getElementsTest() {
        //Arrange
        QueueForHeap queue = new QueueForHeap(comparator);
        //Act
        queue.put(6);
        queue.put(5);
        queue.put(8);
        queue.put(9);
        queue.put(4);
        queue.put(7);
        queue.put(10);
        queue.put(15);
        queue.put(17);

        //Assert
        assertThat(queue.get()).isEqualTo(4);
    }
    
    @Test
    void getOneTest() {
        //Arrange
        QueueForHeap queue = new QueueForHeap(comparator);
        //Act
        queue.put(6);

        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(queue.iterator().next()).isEqualTo(6);
            softly.assertThat(queue.iterator().hasNext()).isFalse();
            
        });
        
    }
    
    @Test
    void getTwoTest() {
        //Arrange
        QueueForHeap queue = new QueueForHeap(comparator);
        //Act
        queue.put(6);
        queue.put(5);

        //Assert
        assertThat(queue.get()).isEqualTo(5);
    }
    
    @Test
    void getEmptyQueueTest() {
        //Arrange
        QueueForHeap queue = new QueueForHeap(comparator);

        //Assert
        assertThat(queue.iterator().hasNext()).isFalse();
    }
    
    @Test
    void getSizeQueueTest() {
        //Arrange
        QueueForHeap queue = new QueueForHeap(comparator);
        //Act
        queue.put(6);
        queue.put(5);
        queue.put(10);
        queue.put(8);
        queue.put(9);
        queue.put(4);
        queue.put(7);
        queue.put(10);
        queue.put(15);
        queue.put(17);
        queue.put(1);
        //Assert
        assertThat(queue.size()).isEqualTo(11);
    }
    
    @Test
    void getElementsTest2() {
        //Arrange
        QueueForHeap queue = new QueueForHeap(comparator);
        //Act
        queue.put(27);
        queue.put(9);
        queue.put(4);
        queue.put(1);
        queue.put(23);
        queue.put(30);
        queue.put(24);
        queue.put(32);
        queue.put(7);
        queue.put(43);
        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(queue.get()).isEqualTo(1);
            softly.assertThat(queue.get()).isEqualTo(4);
            softly.assertThat(queue.get()).isEqualTo(7);
            softly.assertThat(queue.get()).isEqualTo(9);
            softly.assertThat(queue.get()).isEqualTo(23);
            softly.assertThat(queue.get()).isEqualTo(24);
            softly.assertThat(queue.get()).isEqualTo(27);
            softly.assertThat(queue.get()).isEqualTo(30);
            softly.assertThat(queue.get()).isEqualTo(32);
            softly.assertThat(queue.get()).isEqualTo(43);
            
        });
    }
    
    @Test
    void getElementsTest3() {
        //Arrange
        QueueForHeap queue = new QueueForHeap(comparator);
        //Act
        queue.put(35);
        queue.put(49);
        queue.put(49);
        queue.put(28);
        queue.put(17);
        queue.put(1);
        queue.put(44);
        queue.put(30);
        queue.put(44);
        queue.put(34);
        boolean allAreEqualOrBigger = true;
        
        long countElements = queue.size();
        Integer previous = (Integer) queue.get();
        for (int i = 0; i < countElements - 1; i++) {
            Integer current = (Integer) queue.get();
            if (comparator.compare(previous, current) > 0) {
                allAreEqualOrBigger = false;
            }
            previous = current;
        }
        //Assert
        assertThat(allAreEqualOrBigger).isTrue();
    }
    
    @Test
    void getElementsTest4() {
        //Arrange
        QueueForHeap queue = new QueueForHeap(comparator);
        //Act
        queue.put(47);
        queue.put(39);
        queue.put(29);
        queue.put(14);
        queue.put(0);
        queue.put(36);
        queue.put(34);
        queue.put(0);
        queue.put(3);
        queue.put(7);
        queue.put(47);
        queue.put(39);
        queue.put(29);
        queue.put(14);
        queue.put(0);
        queue.put(36);
        queue.put(34);
        queue.put(0);
        queue.put(3);
        queue.put(7);
        queue.put(47);
        queue.put(39);
        queue.put(29);
        queue.put(14);
        queue.put(0);
        queue.put(36);
        queue.put(34);
        queue.put(0);
        queue.put(3);
        queue.put(7);
        queue.put(47);
        queue.put(39);
        queue.put(29);
        queue.put(14);
        queue.put(0);
        queue.put(36);
        queue.put(34);
        queue.put(0);
        queue.put(3);
        queue.put(7);
        queue.put(47);
        queue.put(39);
        queue.put(29);
        queue.put(14);
        queue.put(0);
        queue.put(36);
        queue.put(34);
        queue.put(0);
        queue.put(3);
        queue.put(7);
        
        boolean allAreEqualOrBigger = true;
        
        long countElements = queue.size();
        Integer previous = (Integer) queue.get();
        for (int i = 0; i < countElements - 1; i++) {
            Integer current = (Integer) queue.get();
            if (comparator.compare(previous, current) > 0) {
                allAreEqualOrBigger = false;
            }
            previous = current;
        }
        //Assert
        assertThat(allAreEqualOrBigger).isTrue();
    }
    
    @Test
    void getElementsTest5() {
        //Arrange
        QueueForHeap queue = new QueueForHeap(comparator);
        //Act
        for (int i = 0; i < 100; i++) {
            queue.put(i);
        }
        
        boolean allAreEqualOrBigger = true;
        
        long countElements = queue.size();
        Integer previous = (Integer) queue.get();
        for (int i = 0; i < countElements - 1; i++) {
            Integer current = (Integer) queue.get();
            if (comparator.compare(previous, current) > 0) {
                allAreEqualOrBigger = false;
            }
            previous = current;
        }
        //Assert
        assertThat(allAreEqualOrBigger).isTrue();
    }
    
    @Test
    void sortedElement() {
        //Arrange
        QueueForHeap queue = new QueueForHeap(comparator);
        //Act
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(4);
        queue.put(6);
        queue.put(7);
        queue.put(8);
        queue.put(9);
        queue.put(10);
        queue.put(10);
        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(queue.get()).isEqualTo(1);
            softly.assertThat(queue.get()).isEqualTo(2);
            softly.assertThat(queue.get()).isEqualTo(3);
            softly.assertThat(queue.get()).isEqualTo(4);
            softly.assertThat(queue.get()).isEqualTo(6);
            softly.assertThat(queue.get()).isEqualTo(7);
            softly.assertThat(queue.get()).isEqualTo(8);
            softly.assertThat(queue.get()).isEqualTo(9);
            softly.assertThat(queue.get()).isEqualTo(10);
            softly.assertThat(queue.get()).isEqualTo(10);
            
        });
    }
    
}
