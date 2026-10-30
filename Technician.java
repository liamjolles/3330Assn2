package Proj;

// IT technician who earns service points
public class Technician {

	private final String name;
	private int servicePoints;

	// Validate name, points start at 0
	public Technician(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("name cannot be null or blank");
		}
		this.name = name;
		this.servicePoints = 0;
	}

	// Add points, amount must be positive
	public void addPoints(int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be positive");
		}
		servicePoints += amount;
	}

	// Current service points
	public int getServicePoints() {
		return servicePoints;
	}

	// Equal when names match exactly
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Technician other = (Technician) obj;
		return name.equals(other.name);
	}

	// Based on name
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	// Name and service points
	@Override
	public String toString() {
		return name + " (" + servicePoints + " points)";
	}
}
