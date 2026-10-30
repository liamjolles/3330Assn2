package Proj;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HelpDesk {

    private Map<Integer, SupportRequest> requestsById;
    private Map<Technician, List<SupportRequest>> assignments;

    public HelpDesk() {
        requestsById = new HashMap<>();
        assignments = new HashMap<>();
    }

    public void addRequest(SupportRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (requestsById.containsKey(request.getId())) {
            throw new IllegalArgumentException("Request ID already exists");
        }

        requestsById.put(request.getId(), request);
    }

    public SupportRequest findRequest(int id) {
        return requestsById.get(id);
    }

    // Assign a request to a technician
    public void assignRequest(Technician technician, int requestId) {
        if (technician == null) {
            throw new IllegalArgumentException("Technician cannot be null");
        }

        SupportRequest request = findRequest(requestId);

        if (request == null) {
            throw new IllegalArgumentException("Request does not exist");
        }

        if (request.isResolved()) {
            throw new IllegalStateException("Cannot assign a resolved request");
        }

        // Each request can only be assigned to one technician
        for (List<SupportRequest> assigned : assignments.values()) {
            if (assigned.contains(request)) {
                throw new IllegalStateException("Request is already assigned");
            }
        }

        if (!assignments.containsKey(technician)) {
            assignments.put(technician, new ArrayList<>());
        }

        assignments.get(technician).add(request);
    }
}
