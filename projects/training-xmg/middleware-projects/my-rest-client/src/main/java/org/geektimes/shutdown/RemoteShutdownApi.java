package org.geektimes.shutdown;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

public interface RemoteShutdownApi {
    @POST
    @Path("/actuator/shutdown")
    public void shutdown();
}
