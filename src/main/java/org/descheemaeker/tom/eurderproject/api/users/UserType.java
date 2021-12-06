package org.descheemaeker.tom.eurderproject.api.users;

import java.util.List;

public enum UserType {
    CUSTOMER(List.of("df"));

    private List<String> features;

    UserType(List<String> features) {
        this.features = features;
    }

    public boolean hasFeature(String feature) {
        return features.contains(feature);
    }
}
