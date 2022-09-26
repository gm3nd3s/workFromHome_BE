package spotify.project.services;

import org.springframework.stereotype.Service;
import spotify.project.models.Review;
import spotify.project.repositories.ReviewRepository;

@Service
public class ReviewService {

	private ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	public Review saveReview(Review review) {
		return reviewRepository.save(review);
	}
}
