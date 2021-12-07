package org.descheemaeker.tom.eurderproject.api.users;

import java.util.List;

public enum UserType {
    CUSTOMER,
    ADMIN;

    private List<String> features;

    UserType() {
//        this.features = features;
    }

    public boolean hasFeature(String feature) {
        return features.contains(feature);
    }
}
