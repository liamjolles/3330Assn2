
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
}

