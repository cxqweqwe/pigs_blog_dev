package com.pigs.pigsblog.config;


import com.pigs.pigsblog.shiro.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author PIGS-猪农·杨
 * @version 1.0
 * @date 2020/3/18 22:47
 * @effect shiro 配置类
 */
@Configuration
public class ShiroConfig {

    private Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * 过滤器工厂
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        logger.info("启动 ShiroConfiguration.shirFilter()");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * 拦截器 map 集合.
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        /**
         * 配置不会被拦截的链接 顺序判断
         * anon 匿名 访问
         */
        filterChainDefinitionMap.put("/static/blogHtml/login.html", "anon");
        filterChainDefinitionMap.put("/static/blogHtml/index.html", "anon");
        filterChainDefinitionMap.put("/static/blogHtml/contact.html", "anon");
        filterChainDefinitionMap.put("/static/blogHtml/text.html", "anon");
        filterChainDefinitionMap.put("/pgUsers/login", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");

        filterChainDefinitionMap.put("/pgSlideshow/queryPgSlideshow", "anon");

        /**
         * 文章过滤链
         */
        filterChainDefinitionMap.put("/pgArticles/queryPgArticles", "anon");
        filterChainDefinitionMap.put("/pgArticles/queryPgArticle", "anon");
        filterChainDefinitionMap.put("/pgArticles/queryPgArticleViews", "anon");

        /**
         *  轮播图过滤链
         */
        filterChainDefinitionMap.put("/pgSlideshow/queryGalleryPage", "anon");

        filterChainDefinitionMap.put("/swagger-ui.html#/", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");

        /**
         * 配置退出 过滤器,其中的具体的退出代码Shiro已经实现了
         */
        filterChainDefinitionMap.put("/logout", "logout");

        /**
         * authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
         * 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
         */
        filterChainDefinitionMap.put("/**", "authc");

        /**
         * 如果不设置默认会自动寻找Web工程根目录下的"/login"页面
         */
        shiroFilterFactoryBean.setLoginUrl("/static/blogHtml/index.html");

        /**
         *
         *  登录成功后要跳转的链接
         *  */
        shiroFilterFactoryBean.setSuccessUrl("/index.html");


        /**
         * 未授权界面
         */
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.html");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 身份认证realm; (账号密码校验；权限等)
     *
     * @return MyShiroRealm
     */
    @Bean
    public UserRealm myShiroRealm() {
        UserRealm myShiroRealm = new UserRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }


    /**
     * 安全管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }


    /**
     * 凭证匹配器
     * （密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 修改下doGetAuthenticationInfo中的代码;
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        /**
         * 散列算法:使用MD5算法
         * 加密一次
         */
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }


    /**
     * Shiro生命周期处理器 * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 顾问自动代理创建者
     *
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 授权属性来源顾问
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 配置会话ID生成器
     *
     * @return
     */
    @Bean
    public SessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }


}
