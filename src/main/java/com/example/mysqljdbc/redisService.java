package com.example.mysqljdbc;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class redisService {
    public static void main(String[] args) {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("34.106.102.92", 5001));
        jedisClusterNodes.add(new HostAndPort("34.106.20.60", 5002));
        jedisClusterNodes.add(new HostAndPort("34.106.3.246", 5003));
        JedisCluster jedis = new JedisCluster(jedisClusterNodes);

        jedis.set("foo2", "bar123");
        String foobar = jedis.get("foo");
        System.out.println(foobar);
    }
}
