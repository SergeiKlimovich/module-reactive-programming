package com.javaprogram.modulereactiveprogramming.backpressure;

import org.reactivestreams.Subscription;
import org.springframework.stereotype.Component;

import com.javaprogram.modulereactiveprogramming.entity.Sport;

import reactor.core.publisher.BaseSubscriber;

@Component
public class CustomBackPressureSubscriber extends BaseSubscriber<Sport> {

    public static final int ELEMENT_LIMIT = 20;

    public void hookOnSubscribe(Subscription subscription) {
        request(ELEMENT_LIMIT);
    }

    public void hookOnNext(Sport sport) {
        request(ELEMENT_LIMIT);
    }
}
