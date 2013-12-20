package de.tuberlin.aura.core.descriptors;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import de.tuberlin.aura.core.task.usercode.UserCode;

public final class Descriptors {

    // Disallow instantiation.
    private Descriptors() {}

    /**
     *
     */
    public static final class MachineDescriptor implements Serializable {

        private static final long serialVersionUID = -826435800717651651L;

        public MachineDescriptor( UUID uid, InetAddress address, int dataPort, int controlPort ) {
            // sanity check.
            if( uid == null )
                throw new IllegalArgumentException( "uid == null" );
            if( address == null )
                throw new IllegalArgumentException( "address == null" );

            this.uid = uid;

            this.address = address;

            this.dataPort = dataPort;

            this.controlPort = controlPort;

            this.dataAddress = new InetSocketAddress( address, dataPort );

            this.controlAddress = new InetSocketAddress( address, controlPort );
        }

        public final UUID uid;

        public final int dataPort;

        public final int controlPort;

        public final InetAddress address;

        // TODO: redundant...
        public final InetSocketAddress dataAddress;

        public final InetSocketAddress controlAddress;

        @Override
        public boolean equals( Object other ) {
            if( this == other ) return true;
            if( other == null ) return false;
            if( other.getClass() != getClass() ) return false;

            if( !( uid.equals( ( (MachineDescriptor)other ).uid ) ) )
                return false;
            if( !( dataAddress.equals( ( (MachineDescriptor)other ).dataAddress ) ) )
                return false;
            return true;
        }

        @Override
        public String toString() {
            return (new StringBuilder())
                    .append( "MachineDescriptor = {" )
                    .append( " uid = " + uid.toString() + ", " )
                    .append( " netAddress = " + dataAddress )
                    .append( " }" ).toString();
        }
    }

    /**
     *
     */
    public static final class TaskDescriptor implements Serializable {

        private static final long serialVersionUID = 7425151926496852885L;

        public TaskDescriptor( UUID uid, String name, UserCode userCode ) {
            // sanity check.
            if( uid == null )
                throw new IllegalArgumentException( "uid == null" );
            if( name == null )
                throw new IllegalArgumentException( "name == null" );
            if( userCode == null )
                throw new IllegalArgumentException( "userCode == null" );

            this.uid = uid;

            this.name = name;

            this.userCode = userCode;
        }

        public final UUID uid;

        public final String name;

        public final UserCode userCode;

        private MachineDescriptor machine;

        public void setMachineDescriptor( final MachineDescriptor machine ) {
            // sanity check.
            if( machine == null )
                throw new IllegalArgumentException( "machine == null" );
            if( this.machine != null )
                throw new IllegalStateException( "machine is already set" );

            this.machine = machine;
        }

        public MachineDescriptor getMachineDescriptor() {
            return machine;
        }

        @Override
        public boolean equals( Object other ) {
            if( this == other ) return true;
            if( other == null ) return false;
            if( other.getClass() != getClass() ) return false;

            if( ( machine == null && ((TaskDescriptor)other).machine == null ) ||
                !( machine.equals( ( (TaskDescriptor)other ).machine ) ) )
                return false;
            if( !( uid.equals( ( (TaskDescriptor)other ).uid ) ) )
                return false;
            if( !( name.equals( ( (TaskDescriptor)other ).name ) ) )
                return false;
            return true;
        }

        @Override
        public String toString() {
            return (new StringBuilder())
                    .append( "TaskDescriptor = {" )
                    .append( " machine = " + machine.toString() + ", " )
                    .append( " uid = " + uid.toString() + ", " )
                    .append( " name = " + name )
                    .append( " }" ).toString();
        }
    }

    /**
     *
     */
    public static final class TaskBindingDescriptor implements Serializable {

        private static final long serialVersionUID = -2803770527065206844L;

        public TaskBindingDescriptor( final TaskDescriptor task,
                                      final List<List<TaskDescriptor>> inputGateBindings,
                                      final List<List<TaskDescriptor>> outputGateBindings ) {
            // sanity check.
            if( task == null )
            throw new IllegalArgumentException( "taskID == null" );
            if( inputGateBindings == null )
            throw new IllegalArgumentException( "inputGateBindings == null" );
            if( outputGateBindings == null )
            throw new IllegalArgumentException( "outputGateBindings == null" );

            this.task = task;

            this.inputGateBindings = Collections.unmodifiableList( inputGateBindings );

            this.outputGateBindings = Collections.unmodifiableList( outputGateBindings );
        }

        public final TaskDescriptor task;

        public final List<List<TaskDescriptor>> inputGateBindings;

        public final List<List<TaskDescriptor>> outputGateBindings;

        @Override
        public boolean equals( Object other ) {
            if( this == other ) return true;
            if( other == null ) return false;
            if( other.getClass() != getClass() ) return false;

            if( !( task.equals( ( (TaskBindingDescriptor)other ).task ) ) )
                return false;
            if( !( inputGateBindings.equals( ( (TaskBindingDescriptor)other ).inputGateBindings ) ) )
                return false;
            if( !( outputGateBindings.equals( ( (TaskBindingDescriptor)other ).outputGateBindings ) ) )
                return false;
            return true;
        }

        @Override
        public String toString() {
            return (new StringBuilder())
                    .append( "TaskBindingDescriptor = {" )
                    //.append( " task = " + task.toString() + ", " )
                    .append( " inputGates = " + inputGateBindings.toString() + ", " )
                    .append( " outputGates = " + outputGateBindings.toString() )
                    .append( " }" ).toString();
        }
    }

    /**
     *
     */
    public static final class TaskDeploymentDescriptor implements Serializable {

        private static final long serialVersionUID = 6533439159854768522L;

        public TaskDeploymentDescriptor( final TaskDescriptor taskDescriptor,
                                         final TaskBindingDescriptor taskBindingDescriptor ) {
            // sanity check.
            if( taskDescriptor == null )
                throw new IllegalArgumentException( "taskDescriptor == null" );
            if( taskBindingDescriptor == null )
                throw new IllegalArgumentException( "taskBindingDescriptor == null" );

            this.taskDescriptor = taskDescriptor;

            this.taskBindingDescriptor = taskBindingDescriptor;
        }

        public final TaskDescriptor taskDescriptor;

        public final TaskBindingDescriptor taskBindingDescriptor;

        /*public final Edge.DeploymentType taskDeploymentType;

        public final Edge.DataPersistenceType taskDataPersistenceType;

        public final Edge.ExecutionType taskExecutionType;

        public final Edge.PartitioningType taskPartitioningType;*/

        @Override
        public boolean equals( Object other ) {
            if( this == other ) return true;
            if( other == null ) return false;
            if( other.getClass() != getClass() ) return false;

            if( !( taskDescriptor.equals( ( (TaskDeploymentDescriptor)other ).taskDescriptor ) ) )
                return false;
            if( !( taskBindingDescriptor.equals( ( (TaskDeploymentDescriptor)other ).taskBindingDescriptor ) ) )
                return false;
            return true;
        }

        @Override
        public String toString() {
            return (new StringBuilder())
                    .append( "TaskDeploymentDescriptor = {" )
                    .append( " taskDescriptor = " + taskDescriptor.toString() + ", " )
                    .append( " taskBindingDescriptor = " + taskBindingDescriptor.toString() )
                    .append( " }" ).toString();
        }
    }
}
