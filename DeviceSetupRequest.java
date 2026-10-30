package Proj;

// Setup of one or more devices
public class DeviceSetupRequest extends AbstractSupportRequest {

	private final int deviceCount;

	// Validate deviceCount (positive)
	public DeviceSetupRequest(int id, String title, int basePoints, int deviceCount) {
		super(id, title, basePoints);
		if (deviceCount <= 0) {
			throw new IllegalArgumentException("deviceCount must be positive");
		}
		this.deviceCount = deviceCount;
	}

	// Award basePoints * deviceCount
	@Override
	public int resolveFor(Technician technician) {
		if (technician == null) {
			throw new IllegalArgumentException("technician cannot be null");
		}
		markResolved();
		int points = getBasePoints() * deviceCount;
		technician.addPoints(points);
		return points;
	}

	// Request info plus device count
	@Override
	public String toString() {
		return "DeviceSetup " + super.toString() + " devices=" + deviceCount;
	}
}
