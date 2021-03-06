package de.tuberlin.aura.core.taskmanager.spi;


import java.util.List;
import java.util.UUID;

import de.tuberlin.aura.core.descriptors.Descriptors;
import de.tuberlin.aura.core.iosystem.IOEvents;
import de.tuberlin.aura.core.memory.MemoryView;
import de.tuberlin.aura.core.memory.spi.IAllocator;
import de.tuberlin.aura.core.taskmanager.gates.OutputGate;

public interface IDataProducer {

    // ---------------------------------------------------
    // Public Methods.
    // ---------------------------------------------------

    public abstract void emit(final int gateIndex, final int channelIndex, final IOEvents.DataIOEvent event);

    public abstract void emit(final int gateIndex, final int channelIndex, final MemoryView buffer);

    public abstract void broadcast(final int gateIndex, final MemoryView buffer);

    public abstract void done(final int outputGateIndex);

    public abstract void shutdown(boolean awaitExhaustion);

    public abstract UUID getOutputTaskIDFromChannelIndex(int channelIndex);

    public abstract int getOutputGateIndexFromTaskID(final UUID taskID);

    public abstract int getChannelIndexFromTaskID(final UUID taskID);

    public abstract void bind(final List<List<Descriptors.AbstractNodeDescriptor>> outputBinding, final IAllocator allocator);

    public abstract IAllocator getAllocator();

    public abstract List<OutputGate> getOutputGates();
}
