package com.wasu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 * @Author: cs
 * @Date: 2019/6/18 17:41
 */
@Component
public final class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 指定缓存的失效时间
     * @param key 键
     * @param  time 时间(秒)
     */
    public boolean expire(String key,long time){

        try {
            if(time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间 返回0表示永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true存在  false不存在
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 可以删除一个，或者多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key){
        if(key!=null&&key.length>0){
            if(key.length == 1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
    //****************以下是String类型操作********************//

    /**
     * 获取普通的缓存
     * @param key 键
     * @return 值
     */
    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    /**
     *存入普通缓存
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存入普通缓存并且设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean set(String key,Object value,long time){
        try {
            redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     * @param key 键
     * @param delta 要增加多少（大于0）
     * @return
     */
    public long incr(String key,long delta){
        if(delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }

    /**
     * 递减
     * @param key 键
     * @param delta 要减少多少（大于0）
     * @return
     */
    public long decr(String key,long delta){
        if(delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key,delta);
    }
    //****************以下是Map类型操作********************//
    /**
     * 获取Hash类型缓存
     * @param key 键
     * @param item 项
     * @return 值
     */
    public Object hget(String key,String item){
        return (key==null||item==null)?null:redisTemplate.opsForHash().get(key,item);
    }

    /**
     * 获取hash key对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object,Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 缓存hash 多个键值
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key,Map<String,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 缓存hash 多个键值并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间（秒）
     * @return true 成功 false 失败
     */
    public boolean hmset(String key,Map<String,Object> map,long time){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            if(time>0){
                expire(key,time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中存放数据，如果没有则创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return
     */
    public boolean hset(String key,String item,Object value){
        try {
            redisTemplate.opsForHash().put(key,item,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 向一张hash表中存放数据，如果没有则创建,并且设置时间
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time 时间（秒）注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return
     */
    public boolean hset(String key,String item,Object value,long time){
        try {
            redisTemplate.opsForHash().put(key,item,value);
            if(time>0){
                expire(key,time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key 键
     * @param item 项 可以为多个
     */
    public void hdel(String key,Object... item){
        redisTemplate.opsForHash().delete(key,item);
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key 键
     * @param item 项
     * @return true 存在 false 不存在
     */
    public boolean hHasKey(String key,String item){
        return redisTemplate.opsForHash().hasKey(key,item);
    }

    /**
     * hash递增
     * @param key 键
     * @param item 项
     * @param by 要增加多少（大于0）
     * @return
     */
    public double hincr(String key,String item,double by){
       return redisTemplate.opsForHash().increment(key,item,by);
    }
    /**
     * hash递减
     * @param key 键
     * @param item 项
     * @param by 要减少多少（大于0）
     * @return
     */
    public double hdecr(String key,String item,double by){
        return redisTemplate.opsForHash().increment(key,item,-by);
    }
    //****************以下是Set类型操作********************//
    /**
     * 根据key获取Set中所有的值
     * @param key 键
     * @return
     */
    public Set<Object> sget(String key){
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Set中value是否存在
     * @param key 键
     * @param value 值
     * @return true存在 false 不存在
     */
    public boolean sHasKey(String key,String value){
        try {
            return redisTemplate.opsForSet().isMember(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入Set缓存
     * @param key 键
     * @param values 值
     * @return 成功个数
     */
    public long sSet(String key,Object... values){
        return redisTemplate.opsForSet().add(key,values);
    }

    /**
     * 将数据放入Set缓存，并设置时间
     * @param key 键
     * @param time 时间（秒）
     * @param values  值 可以是多个
     * @return成功个数
     */
    public long sSet(String key,long time,Object... values){
        try {
            long count = redisTemplate.opsForSet().add(key,values);
            if (time>0){
                expire(key,time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 获取Set缓存长度
     * @param key 键
     * @return
     */
    public long sSize(String key){
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除Set缓存值value的
     * @param key 键
     * @param values 值，可以为多个
     * @return 移除个数
     */
    public long sRemove(String key,Object... values){
        try {
            long count = redisTemplate.opsForSet().remove(key,values);
            return  count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //****************以下是List类型操作********************//

    /**
     * 获取List缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束 0到-1 表示所有值
     * @return
     */
    public List<Object> lget(String key,long start,long end){
        try {
            return redisTemplate.opsForList().range(key,start,end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取List缓存的长度
     * @param key 键
     * @return
     */
    public long lsize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引获取List缓存中的值
     * @param key 键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lgetindex(String key,long index){
        try {
            return  redisTemplate.opsForList().index(key,index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean lset(String key,Object value){
        try {
            redisTemplate.opsForList().rightPush(key,value);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将List放入缓存，并且设置时间
     * @param key 键
     * @param value 值
     * @param time 时间（秒）
     * @return
     */
    public boolean lset(String key,Object value,long time){
        try {
            redisTemplate.opsForList().rightPush(key,value);
            if(time>0){
                expire(key,time);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值,list列表
     * @return
     */
    public boolean lset(String key,List<Object> value){
        try {
            redisTemplate.opsForList().rightPushAll(key,value);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 将List放入缓存，并且设置时间
     * @param key 键
     * @param value 值,list列表
     * @param time 时间（秒）
     * @return
     */
    public boolean lset(String key,List<Object> value,long time){
        try {
            redisTemplate.opsForList().rightPushAll(key,value);
            if(time>0){
                expire(key,time);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改List中某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lupdate(String key,long index,Object value){
        try {
            redisTemplate.opsForList().set(key,index,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除List中多个值为value的
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除成功的个数
     */
    public long lremove(String key,long count,Object value){
        try {
            return redisTemplate.opsForList().remove(key,count,value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


}
