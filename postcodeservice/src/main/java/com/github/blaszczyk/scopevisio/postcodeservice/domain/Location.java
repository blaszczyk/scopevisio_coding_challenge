package com.github.blaszczyk.scopevisio.postcodeservice.domain;

public record Location(String bundesland, String kreis, String stadt, String postleitzahl, String bezirk) {
}