ifdef::env-github[]
:imagesdir: images/
endif::[]
:imagesdir: images

== Introduction to the sorting exercises
During the second part of the ALDA course, the practical work will focus on the implementation of different sorting algorithms. The Appointment Planner exercise had a focus on the usage of _Data Structures_; the API was complex, the 'algorithms' more simple. Finding the right test scenarios was key. The sorter assignments have a focus on _Algorithms_; the API is relatively simple and re-usable (for different sorting implementations) tests can easily be written. The efficiency of the implementations is important. 

=== Todo and Planning
You have to write 4 different sorter implementations:

* Basic sorters: <<selection_sorter>> and <<insertion_sorter>> -> Week 8 + 9
* <<quick_sorter>> -> Week 10 + 11
* <<heap_sorter>> -> Week 12 + 13

There will be one final deadline for all sorter implementations (see Canvas assignment deadline).

=== API Explanation
Just like in the previous assignments, you have to implement a pre-defined API. Part of that API is the `SortingServiceFactory` interface. We already provide you with an implementation of this service, by means of class `SortingService` in package `factory` of your implementation project. The `module-info.java` in this project is predefined (see default package) and makes sure that the `SortingService` can be automatically loaded (provides...with...). In the Test Packages, we provide you with a `ServiceFinder` containing a static method to retrieve a `SortingServiceFactory` object. We'll use the `ServiceFinder` in our teacher tests; you could use it as well in your own tests. 

In the `SortingService` class, you have to configure which sorters you implemented, which resources they need (a queue generator and a sorter generator), and if you want to have the sorter tested by our teacher tests. See our configuration below. We defined for example a sorter named `SELECTION` that we want to have tested (parameter `true`), that is of `SortKind.SELECTION` and that uses a `SingleLinkedList` queue and a sorter of class `SelectionSorter`. This way, you could (if you would like ;-)) add muliple implementations of one sorter. In the end, we expect at least ONE implementation per `SortKind`. That you can configure which sorters are implemented and shoud be tested by the teacher tests, is convenient, since you avoid a lot of red tests for sorters that you didn't implement yet. The `HEAP` sorter is based on a `PriorityQueue`, more about that specific configuration later.  

.Example Sorters configuration
[source,java]
----

    enum Sorters implements SorterConfiguration {

        // Constructor parameters: applyTeacherTests?, sortKind, queueSupplier, sorterSupplier
        
        SELECTION(
                true,
                SortKind.SELECTION,
                () -> new SingleLinkedList<>(),
                () -> new SelectionSorter()),
        INSERTION(
                true, 
                SortKind.INSERTION,
                () -> new SingleLinkedList<>(),
                () -> new InsertionSorter()),
        QUICK(
                true,
                SortKind.QUICK,
                () -> new DoubleLinkedList<>(),
                () -> new QuickSorter()),
        QUICKBENTLEY(
                true,
                SortKind.QUICK,
                () -> new DoubleLinkedList<>(),
                () -> new QuickSorterBentleyMcllroy()),
        HEAP(   true,
                SortKind.HEAP,
                null, null) {

            @Override
            public Function<Comparator, PriorityQueue> getPriorityQueueSupplier() {
                return c -> new Heap<>(c);
            }

            @Override
            public <T> Sorter<T> getSorter() {
                return (q, c) -> q;
            }
        };
----

Please study the class Diagram below:

.sorting service class diagram
image::sorters-cd-2_0.svg[]


A sorter always needs to be able to compare individual items with each other. Therefore, we make use of a Java `Comparator`. Since we make use of Generic Queues, we can simply sort any type of object, as long as a `Comparator` is provided.  

This sorting service supports two sort approaches:

* Approach 1: Create and fill a normal `Queue` with values to be sorted; create a `Sorter` afterwards. The sorter object has a method `Queue<T> sort( Queue<T> q, Comparator<T> comparator);` The unsorted queue is passed as parameter, together with a Comparator, and returns *THE SAME* Queue in which the items are ordered. The sort simply sets all elements in the queu in the right order.
* Approach 2: Create and fill a `PriorityQueue`. During creation of such a queue, you have to provide the `Comparator` already. The priority queue makes sure that on each invocation of `get()`, the smallest element is returned. So, the `PriorityQueue` knows itself how to sort its elements. HeapSort uses this second approach.

Especially in the first approach, the use of a `Queue` interface might confuse you a bit, since you normally can only do `get()` and `put(...)` operations. How can you sort your queue without creating a new `Queue` object in that case? The idea is simple. The public interface, as you can see in the mentioned signature of the sort method, uses generic queues. However, since you as implementer know which Queue implementation is used (under the hood) for a specific sorter (as defined in the SorterConfiguration), within the `sort(...)` method you can cast the `Queue` object back to its original type. The underlying type is a type you have full access to as implementer. See the example below, in which you can see the first two lines of our Selection sorter's `sort(...)` method. This is what you typically do for each sorter.

.Down casting a Queue to its underlying list
[source,java]
----
    @Override
    public Queue<T> sort( Queue<T> queue,  Comparator<T> comparator ) {

        SingleLinkedList<T> input = (SingleLinkedList) queue;
        ...
----


=== Test Driven Development (TDD)

Use Test Driven Development to implement your solution. Herewith some hints / remarks:

* Testing if a queue is sorted (smallest first) simply means doing consecutive `get()` operations, asserting that the next `get()` never returns an item that is SMALLER than the previous one. Since our `Queue` interface requires that you make it `Iterable`, you can use a for each loop to iterate over it.

* Make sure to test scenarios that typically cause issues in your implementation:  sorting an empty queue, a queue with only one element, a queue with only two elements. 

* Make sure to test scenarios with different types of input, that might influence your sorter's performance: input that is already sorted, input that is already sorted reversed, input with a lot of or only equal values. Some sorters are sensible for different types of input. 

* Make sure that you can re-use your tests for the different sorters (e.g. by using parameterized tests or a test-super-class).

* In the output of the teacher tests, you will find some statistics that give an impression if your sorter implementation is in line with the Big-O of that specific sorter (the expected efficiency). Our statistics only have a look at the number of comparisons your implementation does.

* The tests should complete in reasonable time. For that, we use a JUnit timeout rule set to 2 seconds.

==== How to use the sorting service in your tests?

The method `getFactory()` in the `ServiceFinder` class returns a `SortingServiceFactory` instance. This factory has a method `SorterConfiguration[] getSorterConfigurations()`, which returns all sorters that you configured. A `SorterConfiguration` object knows everything needed (have a look at this interface in the API); the methods `getQueue()`, `getPriorityQueue(Comparator<T> comparator)` and `getSorter()` will retun objects that you can use in your tests.

.Typical test interaction with queue and sorter (just as example).
[source,java]
----
    Queue<TestType> queue = sorterConfig.getQueue( );
    queue = fillRandom( queue, 100 );

    Comparator<TestType> comparator = new Comparator<>( ( a, b ) -> a.compareTo( b ) );
    Sorter<TestType> sorter = sorterConfig.createSorter( );
    Queue<String> sortedQueue = sorter.sort( queue, comparator );
    
    assertThat( sortedQueue ).isSameAs( queue );
    assertThat( sortedQueue).isOrderedAccordingTo( comp);
    // more tests...
----


=== Non-functional requirements
* Use of `java.util.List`, `java.util.Queue`, `java.util.Stack` or any of its implementing classes is not allowed.

* Use of the utility class `java.util.Arrays` is not allowed.

* All data structures you use MUST be based on LinkedLists or Trees. Use of arrays as underlying data structure for your queues/sorting is not allowed. Re-use of self developed datastructres in the AppointmentPlanner assignment is of course allowed!

* The methods in the Queue and Sorter implementing classes should be package private or private with the exception of the API methods;
  the method stipulated by the interfaces should be `public`.

* The `Node`-s  of the queue may be inner classes or outer classes, thats up to you. If you use inner classes, use `static` inner classes, otherwise each node will have a reference to the outer class which in this case will be a waste of space.

* Tree or LinkedList Nodes are not allowed to have an index, since using indices this way causes inefficiency! Internally you may traverse the queue by using `node.next`  and `node.previous`, but `get(int i)` (element by index), which is what a `java.util.List` provides,
  should [red]*not* be used [red]*nor* supported. 

* The `sort` method sorts the queue and is supposed to return the _same instance_.
  Try to be frugal, both time (think big-O) and space wise,
  because that is the objective of this exercise. The less garbage you make the
  better your implementation will perform in the long run.


=== Sorters

==== Selection Sorter [[selection_sorter]]
The sorting method in this excercise has complexity O(N^2).

==== Insertion Sorter  [[insertion_sorter]]
The sorting method in this excercise has complexity O(N^2).

==== Quick Sorter  [[quick_sorter]]
The sorting method in this excercise has complexity O(N*log(N).

Additional requirements:

* Implement the quicksort algorithm using Bentley-McIlroy three-way partitioning, an optimization for the situation in which there are many duplicates.

* Apply Medianof-three-partitioning. The underlying data structure has to be a (double) linked queue.



==== Heap Sorter  [[heap_sorter]]
The sorting method in this excercise has complexity O(N*log(N).

Additional requirements:

* Implement the heapsort algorithm, using `TreeNode` as underlying data structure.

* Your HeapSorter Queue must be a `PriorityQueue`. The SorterConfiguration as shown at the beginning of this README, shows how a `PriorityQueue` based sorter can be configured. You can simply copy-paste this code and replace `new Heap<>(c)` by the creation of your own PriorityQueue. As you can see, the sorter called 'HEAP' does not have a regular queueSupplier and sorterSupplier (null, null). The `getSorter()` method simply returns a fake sorter that returns the queue as-is, on invoking sort(...).

.Example Sorters configuration
[source,java]
----

    enum Sorters implements SorterConfiguration {

        // Constructor parameters: applyTeacherTests?, sortKind, queueSupplier, sorterSupplier
        
        HEAP(   true,
                SortKind.HEAP,
                null, null) {

            @Override
            public Function<Comparator, PriorityQueue> getPriorityQueueSupplier() {
                return c -> new Heap<>(c);
            }

            @Override
            public <T> Sorter<T> getSorter() {
                return (q, c) -> q;
            }
        };
----

Be aware that an iterator on a queue that is implemented using a tree structure, CONSUMES this queue (see `PriorityQueue` in the API).

Hint: have a look at the lecture slides regarding Trees to see how you can use Queues to build a Tree. It is aslo helpful to keep track of a parent stack (A parent is a TreeNode that actually has at least one child node). This supports the heap-process.