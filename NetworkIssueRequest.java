package Proj;

public class NetworkIssueRequest extends AbstractSupportRequest {

    private final int affectedUsers;

    public NetworkIssueRequest(int id, String title, int basePoints, int affectedUsers) {
        super(id, title, basePoints);

        if (affectedUsers <= 0) {
            throw new IllegalArgumentException("affectedUsers must be positive");
        }

        this.affectedUsers = affectedUsers;
    }
    
    @Override
    public int resolveFor(Technician technician) {
    	if(technician == null) {
    		throw new IllegalArgumentException("Technician cannot be null");
    		
    	}
    	int points = Math.addExact(getBasePoints(), Math.multiplyExact(affectedUsers, 2));
    	
    	technician.addPoints(points);
    	markResolved();
    	
    	return points;
    }
}
