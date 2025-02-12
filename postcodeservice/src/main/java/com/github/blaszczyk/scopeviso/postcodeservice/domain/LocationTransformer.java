package com.github.blaszczyk.scopeviso.postcodeservice.domain;

import com.github.blaszczyk.scopeviso.postcodeservice.persistence.PostcodeEntity;

public final class LocationTransformer {

    public static Location transform(final PostcodeEntity entity) {
        return new Location(entity.getRegion1(), entity.getRegion3(),
                entity.getOrt(), entity.getPostleitzahl(), entity.getArea1());
    }

    private LocationTransformer() {}
}
