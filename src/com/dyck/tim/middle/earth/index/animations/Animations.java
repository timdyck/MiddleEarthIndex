package com.dyck.tim.middle.earth.index.animations;

import com.dyck.tim.middle.earth.index.activities.State;

/**
 * Contains all the methods to perform operations from one {@link State} to another. Implementations
 * will perform these animations on a particular visual object.
 */
public interface Animations {

    /**
     * Transition from {@link State#HOME} to {@link State#CONTENT}.
     *
     * @param duration
     */
    public void homeToContent(int duration);

    /**
     * Transition from {@link State#CONTENT} to {@link State#DATA}.
     *
     * @param duration
     */
    public void contentToData(int duration);

    /**
     * Transition from {@link State#CONTENT} to {@link State#HOME}.
     *
     * @param duration
     */
    public void contentToHome(int duration);

    /**
     * Transition from {@link State#DATA} to {@link State#CONTENT}.
     *
     * @param duration
     */
    public void dataToContent(int duration);

    /**
     * Transition from {@link State#DATA} to {@link State#HOME}.
     *
     * @param duration
     */
    public void dataToHome(int duration);

}
