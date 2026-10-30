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

    public int resolveRequest(Technician technician, int requestId) {
        if (technician == null) {
            throw new IllegalArgumentException("Technician cannot be null");
        }

        List<SupportRequest> assigned = assignments.get(technician);

        if (assigned == null) {
            throw new IllegalStateException("Technician has no assigned requests");
        }

        for (SupportRequest request : assigned) {
            if (request.getId() == requestId) {
                return request.resolveFor(technician);
            }
        }

        throw new IllegalStateException("Request is not assigned to this technician");
    }

    public void printAllRequests() {
        System.out.println("All Support Requests:");

        for (SupportRequest request : requestsById.values()) {
            System.out.println(request);
        }
    }

    public void printAssignmentsFor(Technician technician) {
        if (technician == null) {
            throw new IllegalArgumentException("Technician cannot be null");
        }

        System.out.println("Assignments for " + technician + ":");

        List<SupportRequest> assigned = assignments.get(technician);

        if (assigned == null || assigned.isEmpty()) {
            System.out.println("No assigned requests");
            return;
        }

        for (SupportRequest request : assigned) {
            System.out.println(request);
        }
    }
}

