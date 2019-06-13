/**
 * Copyright (C), 2019
 * FileName: RedisConfig
 * Author:   zhangjian
 * Date:     2019/6/13 15:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.redis.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class RedisConfig {

        @Value("${redis.maxIdle}")
        private Integer maxIdle;

        @Value("${redis.maxTotal}")
        private Integer maxTotal;

        @Value("${redis.maxWaitMillis}")
        private Integer maxWaitMillis;

        @Value("${redis.minEvictableIdleTimeMillis}")
        private Integer minEvictableIdleTimeMillis;

        @Value("${redis.numTestsPerEvictionRun}")
        private Integer numTestsPerEvictionRun;

        @Value("${redis.timeBetweenEvictionRunsMillis}")
        private long timeBetweenEvictionRunsMillis;

        @Value("${redis.testOnBorrow}")
        private boolean testOnBorrow;

        @Value("${redis.testWhileIdle}")
        private boolean testWhileIdle;

        @Value("${spring.redis.cluster.nodes}")
        private String clusterNodes;

        @Value("${spring.redis.password}")
        private String password;

        /**
         * JedisPoolConfig 连接池
         * @return
         */
        @Bean
        public JedisPoolConfig jedisPoolConfig() {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            // 最大空闲数
            jedisPoolConfig.setMaxIdle(maxIdle);
            // 连接池的最大数据库连接数
            jedisPoolConfig.setMaxTotal(maxTotal);
            // 最大建立连接等待时间
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
            // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
            jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
            jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
            // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
            jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
            jedisPoolConfig.setTestOnBorrow(testOnBorrow);
            // 在空闲时检查有效性, 默认false
            jedisPoolConfig.setTestWhileIdle(testWhileIdle);

            return jedisPoolConfig;
        }

        /**
         * Redis集群的配置
         * @return RedisClusterConfiguration
         * @autor lpl
         * @date 2017年12月22日
         * @throws
         */
        @Bean
        public RedisClusterConfiguration redisClusterConfiguration(){
            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
            //Set<RedisNode> clusterNodes
            String[] serverArray = clusterNodes.split(",");

            Set<RedisNode> nodes = new HashSet<RedisNode>();

            for(String ipPort:serverArray){
                String[] ipAndPort = ipPort.split(":");
                System.out.println(ipAndPort);
                nodes.add(new RedisNode(ipAndPort[0].trim(),Integer.valueOf(ipAndPort[1])));
            }

            redisClusterConfiguration.setClusterNodes(nodes);
            redisClusterConfiguration.setPassword(password);

            return redisClusterConfiguration;
        }

    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(om);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);

        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);

        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);

        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();

        return template;
    }

}
