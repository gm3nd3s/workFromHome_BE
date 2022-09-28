package spotify.project.mocks;

import spotify.project.command.CreateReviewDto;
import spotify.project.command.ReviewDto;
import spotify.project.models.Review;

import java.time.LocalDate;
import java.util.List;

import static spotify.project.mocks.MockedCitys.getMockedCity1;
import static spotify.project.mocks.MockedCitys.getMockedCity2;

public class MockedReviews {


    public static Review getMockedReview1(){
        return Review.builder()
                .id(1)
                .cityName("porto")
                .scoreAverage(5)
                .localDate(LocalDate.now())
                .city(getMockedCity1())
                .build();
    }


    public static Review getMockedReview2(){
        return Review.builder()
                .id(2)
                .cityName("lisboa")
                .scoreAverage(6)
                .localDate(LocalDate.now())
                .city(getMockedCity2())
                .build();
    }

    public static List<Review> getMockedReviews(){
        return List.of(Review.builder()
                        .id(1)
                        .cityName("porto")
                        .scoreAverage(5)
                        .localDate(LocalDate.now())
                        .city(getMockedCity1())
                        .build(),
                Review.builder()
                        .id(2)
                        .cityName("lisbon")
                        .scoreAverage(5)
                        .localDate(LocalDate.now())
                        .city(getMockedCity2())
                        .build()

        );
    }

    public static ReviewDto convertEntityToReviewDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .cityName(review.getCity().getName())
                .scoreAverage(review.getScoreAverage())
                .localDate(review.getLocalDate())
                .build();
    }

    public static Review convertReviewDtoToEntity(ReviewDto reviewDto) {
        return Review.builder()
                .id(reviewDto.getId())
                .scoreAverage(reviewDto.getScoreAverage())
                .localDate(reviewDto.getLocalDate())
                .build();
    }

    public static Review convertCreateReviewDtoToEntity(CreateReviewDto createReviewDto) {
        return Review.builder()
                .scoreAverage(createReviewDto.getScoreAverage())
                .localDate(createReviewDto.getLocalDate())
                .build();
    }

}
