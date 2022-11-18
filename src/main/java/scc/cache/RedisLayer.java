package scc.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import scc.dataclasses.AuctionDAO;

import java.util.Set;

public class RedisLayer {
    private static final String RedisHostname = "scc23cache-58569.redis.cache.windows.net";
    private static final String RedisKey = "VQZ6deAFTRCoIC4uuO8596JA4f6JuoNL3AzCaDEns1g=";

    private static JedisPool pool;

    private static RedisLayer instance;

    public synchronized static RedisLayer getInstance() {
        if( instance != null)
            return instance;
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        JedisPool jp = new JedisPool(poolConfig, RedisHostname, 6380, 1000, RedisKey, true);
        instance = new RedisLayer(jp);
        return instance;

    }

    public RedisLayer(JedisPool jp) {
        this.pool = jp;
    }

    public void updateAuction(AuctionDAO auction) {
        try (Jedis jedis = pool.getResource()) {
            ObjectMapper mapper = new ObjectMapper();
            String key = "auctions:" + auction.getId();
            jedis.hset(key, "auction", mapper.writeValueAsString(auction));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
