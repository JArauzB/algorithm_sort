package sortingservice;

/**
 * Abstract factory service for sorters and queue implementation classes.
 *
 * Provides The implementing class provides mandatory and optional sorters a
 * student may want to implement.
 *
 * @author Pieter van den Hombergh
 */
public interface SortingServiceFactory {
    SorterConfiguration[] getSorterConfigurations();
}