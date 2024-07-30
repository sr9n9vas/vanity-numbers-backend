package com.vanity;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class VanityNumbersApp {
    public static void main(final String[] args) {
        App app = new App();

        new VanityNumbersStack(app, "VanityNumbersStack", StackProps.builder()
                .env(Environment.builder()
                        .account("230182948704")
                        .region("ap-south-1")
                        .build())
                .build());

        app.synth();
    }
}

