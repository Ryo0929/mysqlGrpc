package com.example.mysqljdbc;

import com.google.gson.Gson;
import io.grpc.stub.StreamObserver;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.*;

public class itemServiceImpl extends db_servicesGrpc.db_servicesImplBase{
    Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
    JedisCluster jedis;
    Gson gson = new Gson();

    public itemServiceImpl(){
        jedisClusterNodes.add(new HostAndPort("34.106.102.92", 5001));
        jedisClusterNodes.add(new HostAndPort("34.106.20.60", 5002));
        jedisClusterNodes.add(new HostAndPort("34.106.3.246", 5003));
        jedisClusterNodes.add(new HostAndPort("34.125.251.158", 5004));
        jedisClusterNodes.add(new HostAndPort("34.106.148.135", 5005));
        jedis = new JedisCluster(jedisClusterNodes);
    }

    @Override
    public void saveItem(item input_item, StreamObserver<Empty> empty){
        String json = gson.toJson(input_item);
        Integer item_id=input_item.getItemId();
        Integer seller_id=input_item.getSellerId();
        String seller_key="seller_"+seller_id.toString();
        jedis.set(item_id.toString(),json);
        jedis.sadd(seller_key,item_id.toString());

        System.out.print("saved item : "+ item_id);

        String foobar = jedis.get(item_id.toString());
        System.out.println(foobar);

        Empty emptyResponse=Empty.newBuilder().build();
        empty.onNext(emptyResponse);
        empty.onCompleted();
    }
    @Override
    public void getItem(item input_item, StreamObserver<itemList> items){
        Integer search_item_id = input_item.getItemId();
        Integer search_seller_id = input_item.getSellerId();
        itemList.Builder response = itemList.newBuilder();
        System.out.println("search_seller_id "+search_seller_id);
        if(search_seller_id!=0){
            String seller_key="seller_"+search_seller_id;
            Set<String> item_keys_by_seller = jedis.smembers(seller_key);
            Iterator<String> keyIterator = item_keys_by_seller.iterator();
            while(keyIterator.hasNext()){
                String key=keyIterator.next();
                String json_obj = jedis.get(key);
                item i=gson.fromJson(json_obj, item.class);
                response.addItems(i);
            }
        }else{
            String json_obj = jedis.get(search_item_id.toString());
            item i=gson.fromJson(json_obj, item.class);
            response.addItems(i);
        }
        items.onNext(response.build());
        items.onCompleted();
    }
    @Override
    public void delItem(item input_item, StreamObserver<Empty> empty){
        Integer search_item_id = input_item.getItemId();
        String seller_key= "seller_"+input_item.getSellerId();
        jedis.del(search_item_id.toString());
        jedis.srem(seller_key,search_item_id.toString());
        Empty emptyResponse=Empty.newBuilder().build();
        empty.onNext(emptyResponse);
        empty.onCompleted();
    }
}

