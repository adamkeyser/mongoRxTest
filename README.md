# RatpackMongoRxTest
The following project represents an issue I'm having where I must wrap observables in a promise. 

The default route will return an observable, this seems to cause a ratpack.handling.internal.DoubleTransmissionException: attempt at double transmission for: / 

If you go do localhost:5050/works where I wrap the observable in a Promise - this will return a result


