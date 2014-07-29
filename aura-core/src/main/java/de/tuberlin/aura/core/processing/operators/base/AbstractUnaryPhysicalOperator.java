package de.tuberlin.aura.core.processing.operators.base;

import de.tuberlin.aura.core.processing.api.OperatorProperties;

/**
 *
 * @param <I>
 * @param <O>
 */
public abstract class AbstractUnaryPhysicalOperator<I,O> extends AbstractPhysicalOperator<O> {

    // ---------------------------------------------------
    // Fields.
    // ---------------------------------------------------

    protected final IPhysicalOperator<I> inputOp;

    // ---------------------------------------------------
    // Constructor.
    // ---------------------------------------------------

    public AbstractUnaryPhysicalOperator(final IOperatorEnvironment environment,
                                         final IPhysicalOperator<I> inputOp) {
        super(environment);
        this.inputOp = inputOp;
    }
}