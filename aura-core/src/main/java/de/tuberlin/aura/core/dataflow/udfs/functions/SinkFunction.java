package de.tuberlin.aura.core.dataflow.udfs.functions;

import de.tuberlin.aura.core.dataflow.udfs.contracts.ISinkFunction;


public abstract class SinkFunction<I> extends AbstractFunction implements ISinkFunction<I> {

    // ---------------------------------------------------
    // Public Methods.
    // ---------------------------------------------------

    public abstract void consume(final I in);
}
