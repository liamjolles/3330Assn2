package Proj;

// Password or account reset
public class PasswordResetRequest extends AbstractSupportRequest {

	private final String accountName;

	// Validate accountName (not null or blank)
	public PasswordResetRequest(int id, String title, int basePoints, String accountName) {
		super(id, title, basePoints);
		if (accountName == null || accountName.isBlank()) {
			throw new IllegalArgumentException("accountName cannot be null or blank");
		}
		this.accountName = accountName;
	}

	// Award exactly basePoints
	@Override
	public int resolveFor(Technician technician) {
		if (technician == null) {
			throw new IllegalArgumentException("technician cannot be null");
		}
		markResolved();
		int points = getBasePoints();
		technician.addPoints(points);
		return points;
	}

	// Request info plus account name
	@Override
	public String toString() {
		return "PasswordReset " + super.toString() + " account=" + accountName;
	}
}
