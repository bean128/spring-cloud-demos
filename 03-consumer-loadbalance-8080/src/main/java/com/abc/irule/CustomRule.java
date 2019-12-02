package com.abc.irule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 自定义负载均衡策略：
 * 从所有可用的provider中排除掉指定端口号的provider，对剩余provider进行随机选择
 */
public class CustomRule implements IRule {
    private ILoadBalancer lb;
    private List<Integer> excludePorts;

    public CustomRule(List<Integer> excludePorts) {
        this.excludePorts = excludePorts;
    }

    @Override
    public Server choose(Object key) {
        List<Server> servers = lb.getReachableServers();
        List<Server> availableServers = this.getAvailableServers(servers);
        return this.getAvailableRandomServers(availableServers);
    }

    private List<Server> getAvailableServers(List<Server> servers) {
        List<Server> aservers = new ArrayList<>();
        for (Server server : servers) {
            boolean isExclude = false;
            for(Integer port : excludePorts) {
                if(server.getPort() == port) {
                    isExclude = true;
                    break;
                }
            }
            if (!isExclude) {
                aservers.add(server);
            }
        }

        /*List<Server> aservers = servers.stream().filter(server -> excludePorts.stream().noneMatch(port -> port == server.getPort()))
                .collect(Collectors.toList());*/
        return aservers;
    }

    private Server getAvailableRandomServers(List<Server> availableServers) {
        int index = new Random().nextInt(availableServers.size());
        return availableServers.get(index);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.lb = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }
}
