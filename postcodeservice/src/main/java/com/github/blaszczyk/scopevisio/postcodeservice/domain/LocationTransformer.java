package com.github.blaszczyk.scopevisio.postcodeservice.domain;

import com.github.blaszczyk.scopevisio.postcodeservice.persistence.PostcodeEntity;

public final class LocationTransformer {

    public static Location transform(final PostcodeEntity entity) {
        return new Location(entity.getRegion1(), entity.getRegion3(),
                entity.getOrt(), entity.getPostleitzahl(), entity.getArea1());
    }

    private LocationTransformer() {}
}
