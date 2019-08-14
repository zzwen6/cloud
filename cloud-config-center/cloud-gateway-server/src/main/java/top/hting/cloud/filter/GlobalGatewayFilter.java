package top.hting.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * 全局网关过滤器，所有进入网关的请求都要经过此处
 */
@Slf4j
@Configuration
public class GlobalGatewayFilter implements GlobalFilter {

    /**
     * 网关请求用 /api前缀开头
     */
    private static final String GATE_WAY_PREFIX = "/api";


    @Value("${auth.pass:/auth/token}")
    private String startWith;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // uri 请求变更
        LinkedHashSet requiredAttribute = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);

        // 获取当前请求的地址
        ServerHttpRequest request = exchange.getRequest();
        String requestUri = request.getPath().pathWithinApplication().value();

        if (requiredAttribute != null) {
            Iterator<URI> iterator = requiredAttribute.iterator();
            while (iterator.hasNext()) {

                URI next = iterator.next();
                if (next.getPath().startsWith(GATE_WAY_PREFIX)) {
                    // 截取
                    requestUri = next.getPath().substring(GATE_WAY_PREFIX.length());
                }
            }
        }
        log.info("请求路径:{}", requestUri);
        // 请求类型  post get 等
        String method = request.getMethod().toString();

        // 先设置当前会话为空 TODO


        ServerHttpRequest.Builder mutate = request.mutate();

        // 是否是放行的请求 如获取token之类的请求
        if (isPassUrl(requestUri)) {
            // 重新包装request
            return chain.filter(exchange.mutate().request(mutate.build()).build());
        }

        // 获取用户请求的相关信息，判断是否有权限，是否限流等 TODO


        // 先直接放行 TODO
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    /**
     * 判断url是否以特定类型开头
     * @param requestUri
     * @return
     */
    private boolean isPassUrl(String requestUri) {
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }

        }
        return false;

    }
}
