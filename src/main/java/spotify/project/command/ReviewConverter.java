package spotify.project.command;

import spotify.project.models.Review;
import spotify.project.models.User;

import java.util.ArrayList;

public class ReviewConverter {

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
