package com.dyck.tim.middle.earth.index.activities;


import android.app.Activity;

import com.dyck.tim.middle.earth.index.animations.CategoryAnimations;
import com.dyck.tim.middle.earth.index.animations.ContentAnimations;
import com.dyck.tim.middle.earth.index.animations.TitleAnimations;

import java.util.ArrayList;
import java.util.List;

public enum Transition {
    HOME_TO_CONTENT(State.HOME, State.CONTENT),
    CONTENT_TO_HOME(State.CONTENT, State.HOME),
    CONTENT_TO_DATA(State.CONTENT, State.DATA),
    DATA_TO_HOME(State.DATA, State.HOME),
    DATA_TO_CONTENT(State.DATA, State.CONTENT);

    private final int DURATION = 1000;

    private final State from;
    private final State to;

    Transition(State from, State to) {
        this.from = from;
        this.to = to;
    }

    public State getFrom() {
        return from;
    }

    public State getTo() {
        return to;
    }

    /**
     * Interface outlying all of the animation methods associated with each transition.
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

    /**
     * Performs the transition animation.
     *
     * @param instance     current {@link Activity}
     * @param currentState current {@link State}
     * @return The new current state
     */
    public State performTransitionAnimations(Activity instance, State currentState) {
        if (currentState.equals(this.getTo())) {
            return currentState;
        }

        List<Animations> animations = new ArrayList<Animations>();
        animations.add(new TitleAnimations(instance));
        animations.add(new CategoryAnimations(instance));
        animations.add(new ContentAnimations(instance));

        for (Animations animation : animations) {
            this.performTransition(animation, DURATION);
        }

        return this.getTo();
    }

    private void performTransition(Animations animation, int duration) {
        switch (this) {
            case HOME_TO_CONTENT:
                animation.homeToContent(duration);
                break;
            case CONTENT_TO_HOME:
                animation.contentToHome(duration);
                break;
            case CONTENT_TO_DATA:
                animation.contentToData(duration);
                break;
            case DATA_TO_HOME:
                animation.dataToHome(duration);
                break;
            case DATA_TO_CONTENT:
                animation.dataToContent(duration);
                break;
        }
    }
}
