package com.nmkip.weather.service;

import com.nmkip.weather.domain.Summary;
import com.nmkip.weather.exception.NotFoundException;
import com.nmkip.weather.repository.SummaryRepository;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SummaryServiceTest {

    private static final long SUMMARY_ID = 1L;

    @Mock
    SummaryRepository repository;

    private SummaryService service;

    @BeforeEach
    void setUp() {
        service = new SummaryService(repository);
    }

    @ParameterizedTest
    @CsvSource({
            "1", "10"
    })
    void search_for_forecast_by_day(Integer years) {
        final Long optimalCount = 100L;
        final Long draughtCount = 30L;
        final Long rainyCount = 30L;
        final Long unknownCount = 200L;
        Integer mostIntenseRainyDay = 20;
        given(repository.findById(SUMMARY_ID))
                .willReturn(Optional.of(new Summary(SUMMARY_ID,
                                                    optimalCount,
                                                    draughtCount,
                                                    rainyCount,
                                                    unknownCount,
                                                    mostIntenseRainyDay)));

        assertThat(service.summaryForNext(years), is(
                                                    new Summary(SUMMARY_ID,
                                                    optimalCount * years,
                                                    draughtCount * years,
                                                    rainyCount * years,
                                                    unknownCount * years,
                                                    mostIntenseRainyDay)));
    }

    @Test
    void throw_an_exception_when_day_is_not_found() {
        given(repository.findById(SUMMARY_ID)).willReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> service.summaryForNext(1));
    }
}
