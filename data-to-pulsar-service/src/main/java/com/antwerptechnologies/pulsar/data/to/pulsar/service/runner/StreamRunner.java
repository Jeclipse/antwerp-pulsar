package com.antwerptechnologies.pulsar.data.to.pulsar.service.runner;

import twitter4j.TwitterException;

public interface StreamRunner {
    void start() throws TwitterException;
}
