/**
 * 
 */
package com.epam.microservices.limitsservice.bean;

/**
 * @author Krishnakanth_Yachare
 *
 */
public class LimitConfiguration {
    private int maximum;
    private int minimum;

    protected LimitConfiguration() {
        // Default Configuration
    }

    public LimitConfiguration(int maximum, int minimum) {
        super();
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public int getMinimum() {
        return minimum;
    }
}
