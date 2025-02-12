package com.github.blaszczyk.scopeviso.praemienservice.bi;

import com.github.blaszczyk.scopeviso.praemienservice.domain.Fahrzeugtyp;
import com.github.blaszczyk.scopeviso.praemienservice.domain.PraemienAnfrageRequest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.blaszczyk.scopeviso.praemienservice.domain.Fahrzeugtyp.*;

public class PraemienCalculatorTest {


    // test cases for
    // * each bundesland
    // * each combination of km-factor and fahrzeugtyp-factor
    // * boundaries of the km-factors
    private static Stream<Arguments> praemienAnfrageTestCases() {
        return Stream.of(
                Arguments.of(1555, LKW, "Baden-Württemberg", 900),
                Arguments.of(1555, PKW, "Bayern", 500),
                Arguments.of(1555, ZWEIRAD, "Berlin", 210),
                Arguments.of(15555, LKW, "Brandenburg", 900),
                Arguments.of(15555, PKW, "Bremen", 675),
                Arguments.of(15555, ZWEIRAD, "Hamburg", 639),
                Arguments.of(155555, LKW, "Hessen", 2260),
                Arguments.of(155555, PKW, "Mecklenburg-Vorpommern", 960),
                Arguments.of(155555, ZWEIRAD, "Niedersachsen", 660),
                Arguments.of(5000, LKW, "Nordrhein-Westfalen", 500),
                Arguments.of(5001, PKW, "Rheinland-Pfalz", 425),
                Arguments.of(10000, ZWEIRAD, "Saarland", 240),
                Arguments.of(10001, LKW, "Sachsen", 600),
                Arguments.of(20000, PKW, "Sachsen-Anhalt", 375),
                Arguments.of(20001, ZWEIRAD, "Schleswig-Holstein", 780),
                Arguments.of(196883, LKW, "Thüringen", 1400)
        );
    }

    @ParameterizedTest
    @MethodSource("praemienAnfrageTestCases")
    public void testBi(final int kilometerleistung, final Fahrzeugtyp fahrzeugtyp,
                       final String bundesland, final int expectedPraemie) {
        final PraemienAnfrageRequest request = new PraemienAnfrageRequest(kilometerleistung, fahrzeugtyp, bundesland,
                null, null, null, null );
        final int actualPraemie = PraemienCalculator.calculate(request);
        assertEquals(expectedPraemie, actualPraemie);
    }
}
