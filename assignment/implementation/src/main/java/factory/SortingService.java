package factory;

import factory.Queues.QueueForHeap;
import factory.Queues.QueueForQuick;
import factory.Sorters.QuickSorter;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import sortingservice.PriorityQueue;
import sortingservice.Queue;
import sortingservice.SortKind;
import sortingservice.Sorter;
import sortingservice.SorterConfiguration;
import sortingservice.SortingServiceFactory;

/**
 * Factory class to create Sorters and appropriate queues.
 *
 * @author Richard van den Ham {@code r.vandenham@fontys.nl}
 */
public class SortingService implements SortingServiceFactory {

    enum Sorters implements SorterConfiguration {

        // Constructor parameters: applyTeacherTests?, sortKind, queueSupplier, sorterSupplier
        //TODO Configure your sorters below
        SELECTION(true, SortKind.SELECTION, () -> new SimpleQueue(), () -> new SelectionSorter()),
        INSERTION(true, SortKind.INSERTION, () -> new SimpleQueue(), () -> new InsertionSorter()),
        QUICK(true, SortKind.QUICK, () -> new QueueForQuick(), () -> new QuickSorter()),
        HEAP(true,
                SortKind.HEAP,
                null, null) {

            @Override
            public Function<Comparator, PriorityQueue> getPriorityQueueSupplier() {
                return c -> new QueueForHeap(c);
            }

            @Override
            public <T> Sorter<T> getSorter() {
                return (q, c) -> q;
            }
        };

        private final boolean applyTeacherTests;
        private final SortKind sortKind;
        private final Supplier<Queue> queueSupplier;
        final Supplier<Sorter> sorterSupplier;

        private Sorters(boolean applyTeacherTests, SortKind sortKind, Supplier<Queue> queueSupplier, Supplier<Sorter> sorterSupplier) {
            this.applyTeacherTests = applyTeacherTests;
            this.sortKind = sortKind;
            this.queueSupplier = queueSupplier;
            this.sorterSupplier = sorterSupplier;
        }

        public boolean doApplyTeacherTests() {
            return applyTeacherTests;
        }

        @Override
        public String getName() {
            return this.name();
        }

        @Override
        public SortKind getSortKind() {
            return sortKind;
        }

        /**
         * By default, sorters don't have priority queue supplier.
         *
         * @return null
         */
        public Function<Comparator, PriorityQueue> getPriorityQueueSupplier() {
            return null;
        }

        @Override
        public boolean usesPriorityQueue() {
            return getPriorityQueueSupplier() != null;
        }

        @Override
        public <T> PriorityQueue<T> getPriorityQueue(Comparator<T> comparator) {
            return getPriorityQueueSupplier().apply(comparator);
        }

        @Override
        public <T> Queue<T> getQueue() {
            return queueSupplier.get();
        }

        @Override
        public <T> Sorter<T> getSorter() {
            return sorterSupplier.get();
        }
    }

    @Override
    public SorterConfiguration[] getSorterConfigurations() {
        return Stream.of(Sorters.values())
                .filter(sorter -> sorter.doApplyTeacherTests())
                .toArray(SorterConfiguration[]::new);
    }
}
