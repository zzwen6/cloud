package top.hting.cloud.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.hting.cloud.jwt.JWTUserInfo;
import top.hting.cloud.jwt.JWTUtils;
import top.hting.cloud.response.domain.BaseResponse;
import top.hting.cloud.response.Token401Response;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Consumer;

/**
 * 全局网关过滤器，所有进入网关的请求都要经过此处
 */
@Slf4j
@Configuration
public class GlobalGatewayFilter implements GlobalFilter {

    /**
     * 网关请求用 /api前缀开头
     */
    private static final String GATE_WAY_PREFIX = "/api/authserver";


    // @Value("${auth.pass:/jwt/token,/auth/jwt/}")
    private String startWith = "/jwt/token,/auth/jwt/";

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
        JWTUserInfo jwtUserInfo = null;
        final String token = getToken(request);
        try {
            // 未过期
            if (!JWTUtils.isExpire(token)) {
                jwtUserInfo = JWTUtils.getInfoFromToken(token);
            }else {
                return getResponse(exchange, new Token401Response("用户凭证过期或无效"));
            }

        } catch (Exception e) {
            return getResponse(exchange, new Token401Response("用户凭证过期或无效"));
        }

        // 重新把token放进header中
        Consumer<HttpHeaders> newHeaders = httpHeaders -> {
            httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
            httpHeaders.add("x-client-token",token);
        };
        mutate.headers(newHeaders);

        // TODO 权限校验
        // 获取当前用户的权限


        // 先直接放行 TODO
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    private String getToken(ServerHttpRequest request) {
        String token = null;
        // 获取请求头
        List<String> headers = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
        List<String> headers2 = request.getHeaders().get("x-client-token");

        if (headers != null) {
            token = headers.get(0);
        }else if(headers2 != null){
            token = headers2.get(0);
        }

        // 不在请求头，从查询参数获取token
        if (StringUtils.isBlank(token)) {
            // 获取url参数是否还有 token
            List<String> strings = request.getQueryParams().get("token");
            if (strings != null ){
                token = strings.get(0);
            }

        }


        return token;
    }

    /**
     * 判断url是否以特定类型开头
     *
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


    private Mono<Void> getResponse(ServerWebExchange exchange, BaseResponse baseResponse){
        // 状态码设置为200
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        String json = JSONObject.toJSONString(baseResponse);
        DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));
        return exchange.getResponse().writeWith(Flux.just(dataBuffer));
    }


}
