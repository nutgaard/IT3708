/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes.spiketrain;

import no.utgdev.ga.core.GALoop;

/**
 *
 * @author Nicklas
 */
public abstract class SpikeTrainGenerator<S> {
    protected GALoop ga;

    public SpikeTrainGenerator(GALoop ga) {
        this.ga = ga;
    }
    
    public abstract RawSpikeTrain generate(S datainput);
}
