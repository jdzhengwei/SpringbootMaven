package com.example.demoM.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.example.demoM.vo.UserInfoVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    private final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(500);
    private final transient ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 3, 2, TimeUnit.MINUTES, blockingQueue, new ThreadPoolExecutor.DiscardPolicy());
    public static final String CLAIM_KEY_CREATED = "created";

    public static final String CLAIM_KEY_ID = "id"; //用户ID
    public static final String CLAIM_KEY_PID = "pid"; //父用户ID,如果没有父用户，则为-1
    public static final String CLAIM_KEY_NAME = "name";//供应商用户名称
    public static final String CLAIM_KEY_DEPT_ID = "deptId"; //科室id
    public static final String CLAIM_KEY_DEPT_CODE = "deptCode"; //科室编码
    public static final String CLAIM_KEY_DEPT_NAME = "deptName"; //科室名称
    public static final String CLAIM_KEY_STAFF_NAME = "staffName"; //员工姓名

    public static final String CLAIM_KEY_ADMIN = "admin";//用户所属角色
    public static final String CLAIM_KEY_STATUS = "status";//用户状态  1：信息不完整(非会员) 2：待审核，3：信息完整,审核通过(会员), 4:审核不通过，5：禁用，6：删除'
    public static final String CLAIM_KEY_CAMP = "camp";//用户所属阵营
    private static final String ID_HASH_KEY = "id";
    private static final String USER_HASH_KEY = "user";
    private static final String TOKEN_HASH_KEY = "token";
    public static final String CLAIM_KEY_PHONE = "phone";//用户手机号
    public static final String CLAIM_KEY_HOSPITAL = "hospital";//当前医院
    public static final String CLAIM_KEY_SUPPLIER = "supplier";//当前供应商
    public static final String CLAIM_KEY_HOSPITAL_MODEL = "hospitalModel";//当前医院模式


    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Autowired
    private transient StringRedisTemplate strRedisTemplate;
    @Autowired
    private transient RedisTemplate<String, Object> redisTemplate;

    public UserInfoVO getUserFromToken(String token) {
        UserInfoVO user = null;
        //打印临时策略更改
        Claims claims = getClaimsFromToken(token);
        if (claims != null && claims.containsKey(CLAIM_KEY_ID)) {
            Long id = Long.valueOf("" + claims.get(CLAIM_KEY_ID));
            String name = claims.get(CLAIM_KEY_NAME, String.class);
            Integer status = claims.get(CLAIM_KEY_STATUS, Integer.class);
            Integer camp = claims.get(CLAIM_KEY_CAMP, Integer.class);
            String phone = claims.get(CLAIM_KEY_PHONE, String.class);
            Integer hospital = claims.get(CLAIM_KEY_HOSPITAL, Integer.class);
            Integer supplier = claims.get(CLAIM_KEY_SUPPLIER, Integer.class);
            Integer hospitalModel = claims.get(CLAIM_KEY_HOSPITAL_MODEL, Integer.class);
            user = new UserInfoVO();
            user.setId(id);
            user.setUserName(name);
            user.setStatus(status);
            user.setUserType(camp);
            user.setMobile(phone);
            user.setHospitalId( Long.valueOf(hospital==null?0:hospital));
            user.setSupplierInfoId( Long.valueOf(supplier==null?0:supplier));
            user.setHospitalModel(hospitalModel);
            
            Long pid = Long.valueOf("" + claims.get(CLAIM_KEY_PID));
            user.setPid(pid);
            Long deptId = Long.valueOf("" + claims.get(CLAIM_KEY_DEPT_ID));
            user.setDeptId(deptId);
            user.setDeptCode(claims.get(CLAIM_KEY_DEPT_CODE, String.class));
            user.setDeptName(claims.get(CLAIM_KEY_DEPT_NAME, String.class));
            user.setStaffName(claims.get(CLAIM_KEY_STAFF_NAME, String.class));
        }
        return user;
    }

    public String getUnameFromToken(String token) {
        String uname = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            if (claims != null) {
                uname = claims.get(CLAIM_KEY_NAME, String.class);
            }
        } catch (Exception ex) {
            uname = null;
            logger.error("error when get uname from token {}, uname is null.", token, ex);
        }
        return uname;
    }

    public Long getUidFromToken(String token) {
        Long uid = -1l;
        try {
            final Claims claims = getClaimsFromToken(token);
            if (claims != null) {
                uid = Long.valueOf(claims.get(CLAIM_KEY_ID).toString());
            }
        } catch (Exception ex) {
            uid = -1l;
            logger.error("error when get uid from token {}, uid is -1", token, ex);
        }
        return uid;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            if (claims != null) {
                created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
            }
        } catch (Exception e) {
            created = null;
            logger.error("error when get CreatedDate from token {}, sname is null", token, e);
        }
        return created;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            if ("printToken".equals(token)) {
                claims = null;
            } else {
                claims = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody();
            }
        } catch (Exception ex) {
            claims = null;
            logger.error("error when get cliams from token {} ", token, ex);
        }
        return claims;
    }

    public String generateToken(UserInfoVO user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_NAME, user.getUserName());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_ID, user.getId());
        claims.put(CLAIM_KEY_STATUS, user.getStatus());
        claims.put(CLAIM_KEY_CAMP, user.getUserType());
        claims.put(CLAIM_KEY_PHONE, user.getMobile());
        claims.put(CLAIM_KEY_HOSPITAL, user.getHospitalId());
        claims.put(CLAIM_KEY_SUPPLIER, user.getSupplierInfoId());
        claims.put(CLAIM_KEY_PID, user.getPid());
        claims.put(CLAIM_KEY_DEPT_ID, user.getDeptId());
        claims.put(CLAIM_KEY_DEPT_CODE, user.getDeptCode());
        claims.put(CLAIM_KEY_DEPT_NAME, user.getDeptName());
        claims.put(CLAIM_KEY_STAFF_NAME, user.getStaffName());
        claims.put(CLAIM_KEY_HOSPITAL_MODEL, user.getHospitalModel());
        String token = generateToken(claims);
        // 同步设置 token ttl
        Long uid = user.getId();
        Map<String, Object> map = new HashMap<>();
        map.put(ID_HASH_KEY, uid);
        map.put(USER_HASH_KEY, user);
        map.put(TOKEN_HASH_KEY, token);
        redisTemplate.opsForHash().putAll(RedisKey.USER_KEY_PREFIX + uid, map);
        redisTemplate.expire(RedisKey.USER_KEY_PREFIX + uid, expiration, TimeUnit.SECONDS);

        return token;
    }

    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
//                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 刷新token失效时间，并不新生成token
     *
     * @param token
     */
    public void refreshToken(String token) {
        threadPoolExecutor.submit(new RefreshTokenDelegator(token));
    }


    private final class RefreshTokenDelegator implements Runnable {
        private String token;

        public RefreshTokenDelegator(final String token) {
            this.token = token;
        }

        public void run() {
            try {
                if (isTokenValid(token)) {
                    Long uid = getUidFromToken(token);
                    if(strRedisTemplate.getExpire(RedisKey.USER_KEY_PREFIX + uid)>0) {
                        strRedisTemplate.expire(RedisKey.USER_KEY_PREFIX + uid, expiration, TimeUnit.SECONDS);
                    }
                    logger.info("token {}", token);
                    logger.info("token expireation {}", expiration);
                    logger.info("token size {}", blockingQueue.size());
                } else {
                    logger.warn("token is invalid, can not refresh.");
                }
            } catch (Exception ex) {
                logger.error("refresh token {} error.", ex);
            }
        }
    }

    public Boolean isTokenValid(String token) {
        if (StringUtils.isEmpty(token))
            return false;
//    	1. 可parse
        Long uid = getUidFromToken(token);
        if (uid == -1)
            return false;
//    	2. uid - token 映射关系正确 (登录校验完成，记录token-uid映射关系)
//    	3. token 未失效
        String tokenInCache = getTokenFromCache(uid);
        return token.equals(tokenInCache);
    }

    private String getTokenFromCache(Long uid) {
        Object token = redisTemplate.opsForHash().get(RedisKey.USER_KEY_PREFIX + uid, TOKEN_HASH_KEY);
        if (ObjectUtils.isEmpty(token)) {
            return "";
        }
        return (String) token;
    }

    public void destroyToken(String token) {
        Long uid = getUidFromToken(token);
        redisTemplate.delete(RedisKey.USER_KEY_PREFIX + uid);
    }

}