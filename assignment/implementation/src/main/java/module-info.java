open module asortingservice {
    requires java.logging;
    requires sortingserviceapi;
    uses sortingservice.SortingServiceFactory;
    provides sortingservice.SortingServiceFactory with factory.SortingService;
}
