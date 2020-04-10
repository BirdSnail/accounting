package com.birdsnail.accouting.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Objects;

/**
 * 支持 不同的HTTP method对应不同的Filter。完成在相同的URL情况下，根据不同的http method进行不同的权限认证。
 * 1. 定义的URL要进行改造，加入我们的http method
 * 2. 自定义我们的Filter
 * 3. 匹配逻辑进行该表，加入对方法的匹配
 *
 * @author BirdSnail
 * @date 2020/4/9
 */
@Slf4j
public class HttpMethodAndPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {

    private static final String DEFAULT_PATH_SEPARATOR = "/";

    @Override
    public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
        FilterChainManager filterChainManager = getFilterChainManager();
        if (!filterChainManager.hasChains()) {
            return null;
        }

        String currentPath = getPathWithinApplication(request);

        // in spring web, the requestURI "/resource/menus" ---- "resource/menus/" bose can access the resource
        // but the pathPattern match "/resource/menus" can not match "resource/menus/"
        // user can use requestURI + "/" to simply bypassed chain filter, to bypassed shiro protect
        if (currentPath != null && !DEFAULT_PATH_SEPARATOR.equals(currentPath)
                && currentPath.endsWith(DEFAULT_PATH_SEPARATOR)) {
            currentPath = currentPath.substring(0, currentPath.length() - 1);
        }

        //the 'chain names' in this implementation are actually path patterns defined by the user.  We just use them
        //as the chain name for the FilterChainManager's requirements
        for (String pathPattern : filterChainManager.getChainNames()) {
            if (pathPattern != null && !DEFAULT_PATH_SEPARATOR.equals(pathPattern)
                    && pathPattern.endsWith(DEFAULT_PATH_SEPARATOR)) {
                pathPattern = pathPattern.substring(0, pathPattern.length() - 1);
            }


            // If the path does match, then pass on to the subclass implementation for specific checks:
            if (isHttpMethodMatcher(pathPattern, currentPath, request)) {
                return filterChainManager.proxy(originalChain, pathPattern);
            }
        }

        return null;
    }

    private boolean isHttpMethodMatcher(String pathPattern, String currentPath, ServletRequest request) {
        final String[] urlAndMethod = Objects.requireNonNull(pathPattern).split("::");
        String url = urlAndMethod[0];
        boolean isHttpMethodMatcher = true;
        if (urlAndMethod.length > 1) {
            String currentHttpMethod = WebUtils.toHttp(request).getMethod();
            isHttpMethodMatcher = currentHttpMethod.equals(urlAndMethod[1]);
        }

        return pathMatches(url, currentPath) && isHttpMethodMatcher;
    }

}

