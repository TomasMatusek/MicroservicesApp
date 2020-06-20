package solutions.matusek.mycroservicesapp.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscoveryRestController {

    private final DiscoveryClient discoveryClient;

    @Autowired
    public DiscoveryRestController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @RequestMapping("/service-instances/{serviceName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String serviceName) {
        return this.discoveryClient.getInstances(serviceName);
    }
}