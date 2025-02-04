package io.github.minecraftchampions.dodoopenjava.event.events.v2.personal;

import io.github.minecraftchampions.dodoopenjava.event.Event;

/**
 * 私信相关事件
 */
public abstract class PersonalEvent extends Event {
    public PersonalEvent(boolean isAsync) {
        super(isAsync);
    }

    public PersonalEvent() {
        this(false);
    }
}
