package com.github.blaszczyk.scopeviso.postcodeservice.domain;

public record Location(String bundesland, String kreis, String stadt, String postleitzahl, String bezirk) {
}