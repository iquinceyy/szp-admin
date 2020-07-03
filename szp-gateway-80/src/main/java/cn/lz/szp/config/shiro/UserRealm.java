package cn.lz.szp.config.shiro;

import cn.lz.szp.service.UserServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * creator：Administrator
 * date:2019/10/22
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Resource
    UserServiceFeign userServiceFegin;
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    public static final String USER_REALM = "userRealm";

    /**
     * 认证身份用的
     * subject.login(token)调用之后，会进入到这个方法
     *
     * @param authenticationToken 携带了用户名和密码的
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug("开始认证用户");
        Session session = SecurityUtils.getSubject().getSession();
        String phone = authenticationToken.getPrincipal().toString();// 传的手机号
        // 根据手机号去查询用户的基本信息（因为一旦密码，是需要保存用户的一些基本信息的），包括密码
//        UserVO loginUser = (UserVO) session.getAttribute("loginUser");// 把用户登陆信息传过来
//        User dbUser = userServiceFegin.loginByPhone(phone);
//        if (loginUser.getIsCodeLogin()) {// 是短信登陆
//            Object o = redisTemplate.opsForValue().get(RedisKeyPrefixEnum.LOGIN_SMS_CODE + phone);
//            if (o == null) {
//                throw new UnknownAccountException("验证码已经失效");
//            }
//            if (!o.equals(loginUser.getCode())) {// 验证码错误
//                throw new UnknownAccountException("验证码错误!");
//            }
//            // 能到这里说明手机号和验证码已经通过了
//            if (dbUser == null) {// 数据库没有用户，那么就马上新创建一个用户
//                dbUser = new User();
//                dbUser.setNickName("飞车梦想");
//                dbUser.setPassword("123456");
//                dbUser.setPhone(loginUser.getPhone());
//                dbUser.setNote("验证码登陆注册而来");
//                dbUser.setPrincipal("消费者");
//                ResponseDTO add = userServiceFegin.add(dbUser);
//                dbUser = JSON.parseObject(JSON.toJSONString(add.getData()), User.class);
//            }
//
//        } else { // 密码登录
//            Object errorCount = redisTemplate.opsForValue().get(RedisKeyPrefixEnum.LOGIN_ERROR_COUNT + phone);
//            Integer e = 0;
//            if (errorCount instanceof Integer) {
//                e = (Integer) errorCount;
//                if (e >= 5) {
//                    throw new LockedAccountException("短时间内密码错误次数过多，请使用短信登陆");
//                }
//            }
//            if (dbUser == null) {// 表示账户名不存在
//                e++;
//                redisTemplate.opsForValue().set(RedisKeyPrefixEnum.LOGIN_ERROR_COUNT + phone, e, 60, TimeUnit.MINUTES);
//                throw new UnknownAccountException("账户名或密码错误!");
//            } else {
//                // 首先就应该对传过来密码进行加密，必须使用之前的加密方式。
////                String userPassword = PasswordDecoderUtil.getDecodePassword(String.valueOf((char[]) authenticationToken.getCredentials()));
//                String userPassword = String.valueOf((char[]) authenticationToken.getCredentials());
//                if (!userPassword.equals(dbUser.getPassword())) {
//                    e++;
//                    redisTemplate.opsForValue().set(RedisKeyPrefixEnum.LOGIN_ERROR_COUNT + phone, e, 60, TimeUnit.MINUTES);
//                    throw new CredentialsException("账户名或密码错误");
//                }
//            }
//        }
//
//        session.setAttribute("userId", dbUser.getUserId());
//        session.setAttribute("nickName", dbUser.getNickName());
//        session.setAttribute("photo", dbUser.getPhoto());
//        session.setAttribute("phone", dbUser.getPhone());
//        session.setAttribute("realName", dbUser.getRealName());
//        session.setAttribute("password", dbUser.getPassword());
//        if (!StringUtils.isEmpty(dbUser.getRoles())) {
//            ResponseDTO res = userServiceFegin.findUserRolesByPhone(dbUser.getRoles());
//            session.setAttribute("hisRoles", res.getData());
//        }
//        // 这里表示登陆成功了，那么需要更新用户最近登陆时间和登陆ip以及登陆的地方
//        User user = new User();
//        user.setLastLoginIp(IpUtil.getIpAddr());
//        user.setLastLoginTime(new Date());
//        user.setUserId(dbUser.getUserId());
//        userServiceFegin.updateUser(user);
        return new SimpleAuthenticationInfo(phone, authenticationToken.getCredentials(), USER_REALM);
    }

    /**
     * 对身份进行授权用的
     *
     * @param principalCollection
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        // 第一次，会去数据库查询当前用户的角色和权限，查出来后存入缓存，后面再次验证角色和权限的时候就不用再查数据库了
//        角色或者是权限，那么其实都是两个 Set<String>的集合
        // 角色和权限实际上是从数据库查询出来的。
        String phone = principalCollection.getPrimaryPrincipal().toString();
//        Set<String> roles = userServiceFegin.findRolesByPhone(phone);
//        auth.setRoles(roles);
//        Set<String> permissions = userServiceFegin.findPermissionsByPhone(phone);
//        auth.setStringPermissions(permissions);
        return auth;
    }

    /**
     * 清空用户的授权信息，下次没有授权信息，用户就会重新去获取
     * 满足立刻给在线用户调整权限，立刻生效
     * 这只是获取当前用户，没有权限就会重新再次去（授权）再次执行。doGetAuthorizationInfo
     */
    public void clearCachedAuthorizationInfo(Integer userId) {// 把id传来，创建一个用户身份，当用户身份已经被更改的时候，要清除用户的权限信息
//        User user = userServiceFegin.selectUserSimpleInfo(userId);
//        PrincipalCollection p = new SimplePrincipalCollection(user.getPhone(), USER_REALM);
//        super.clearCachedAuthorizationInfo(p);// 清空用户权限的操作
    }
}
