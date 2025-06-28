package factory;

import java.util.ServiceLoader;
import sortingservice.SortingServiceFactory;

/**
 * Factory Service Finder.
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class ServiceFinder {

    /**
     * Load SortingServiceFactory.
     *
     * @return SortingServiceFactory or null if not found.
     */
    public static SortingServiceFactory getFactory() {

        var factoryLoader = ServiceLoader.load(SortingServiceFactory.class).findFirst();

        if (factoryLoader.isPresent()) {
            return factoryLoader.get();
        } else {
            System.err.println("Could not load SortingServiceFactory");
            return null;
        }
    }

    
}
