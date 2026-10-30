package Proj;

// Contract for all support requests
public interface SupportRequest {

	// Request ID
	int getId();

	// Request title
	String getTitle();

	// Base points
	int getBasePoints();

	// Resolved or not
	boolean isResolved();

	// Mark resolved, award points, return points awarded
	int resolveFor(Technician technician);
}
