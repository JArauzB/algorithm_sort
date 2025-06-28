/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.Queues;

import factory.SimpleQueue;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.SoftAssertions;

/**
 *
 * @author jorge
 */
public class SimpleQueueTest {

    @Test
    void checkGetMethodWithoutAnyValuesTest() {
        //Arrange
        SimpleQueue queue = new SimpleQueue();
        //Assert
        assertThat(queue.get()).isEqualTo(null);
    }

    @Test
    void checkPutAndGetMethodTest() {
        //Arrange
        SimpleQueue queue = new SimpleQueue();
        String firstActualValue = "First";
        String secondActualValue = "Second";
        String thirdActualValue = "Third";
        String fourthActualValue = "Fourth";
        //Act
        queue.put(firstActualValue);
        queue.put(secondActualValue);
        queue.put(thirdActualValue);
        queue.put(fourthActualValue);
//        queue.showValues();
        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(queue.size()).isEqualTo(4);
            softly.assertThat(queue.get()).isEqualTo(firstActualValue);
        });
    }

    @Test
    void checkThatGetRemovesFirstNodeTest() {
        //Arrange
        SimpleQueue queue = new SimpleQueue();
        String firstActualValue = "First";
        String secondActualValue = "Second";
        String thirdActualValue = "Third";
        String fourthActualValue = "Fourth";
        //Act
        queue.put(firstActualValue);
        queue.put(secondActualValue);
        queue.put(thirdActualValue);
        queue.put(fourthActualValue);
        //Assert
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(queue.get()).isEqualTo(firstActualValue);
            softly.assertThat(queue.size()).isEqualTo(3);
            softly.assertThat(queue.get()).isEqualTo(secondActualValue);

        });
    }

    @Test
    void checkThatQueueIsEmptyTest() {
        //Arrange
        SimpleQueue queue = new SimpleQueue();
        //Assert
        assertThat(queue.isEmpty()).isFalse();
    }

    @Test
    void checkThatQueueHasValuesTest() {
        //Arrange
        SimpleQueue queue = new SimpleQueue();
        String firstActualValue = "First";
        //Act
        queue.put(firstActualValue);
        //Assert
        assertThat(queue.isEmpty()).isTrue();
    }
}
