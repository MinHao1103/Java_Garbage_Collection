package com.example.service.servlet;

import com.example.service.http.utils.httpResult.CommonHttpResult;
import com.example.service.http.utils.log.Log;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Path("/v1")
public class MemoryLeak {
    private static final String TAG = "MemoryLeak";

    @GET
    @Path("/memoryLeak")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CommonHttpResult<Boolean> memoryLeak() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        Random r = new Random();
        int maxEntries = 10000000;
        for (int i = 0; i < maxEntries; i++) {
            map.put(r.nextInt(), "value");
            Log.d(TAG, String.valueOf(i));
        }
        return new CommonHttpResult<Boolean>(0, true);
    }

}
