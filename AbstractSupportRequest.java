package Proj;

// Shared data and behavior for all request types
public abstract class AbstractSupportRequest implements SupportRequest {

	private final int id;
	private final String title;
	private final int basePoints;
	private boolean resolved;

	// Validate and set fields
	public AbstractSupportRequest(int id, String title, int basePoints) {
		if (id <= 0) {
			throw new IllegalArgumentException("id must be positive");
		}
		if (title == null || title.isBlank()) {
			throw new IllegalArgumentException("title cannot be null or blank");
		}
		if (basePoints <= 0) {
			throw new IllegalArgumentException("basePoints must be positive");
		}
		this.id = id;
		this.title = title;
		this.basePoints = basePoints;
		this.resolved = false;
	}

	// Request ID
	@Override
	public int getId() {
		return id;
	}

	// Request title
	@Override
	public String getTitle() {
		return title;
	}

	// Base points
	@Override
	public int getBasePoints() {
		return basePoints;
	}

	// Resolved or not
	@Override
	public boolean isResolved() {
		return resolved;
	}

	// Mark resolved
	// Design choice: resolve only once, second attempt throws IllegalStateException
	protected void markResolved() {
		if (resolved) {
			throw new IllegalStateException("Request " + id + " is already resolved");
		}
		resolved = true;
	}

	// ID, title, base points, resolved state
	@Override
	public String toString() {
		return "Request #" + id + " [" + title + ", basePoints=" + basePoints + ", resolved=" + resolved + "]";
	}
}
