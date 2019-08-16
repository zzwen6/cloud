package top.hting.cloud.response;

import top.hting.cloud.response.domain.BaseResponse;

/**
 * 未授权请求或资源
 */
public class Token403Response extends BaseResponse {
    public Token403Response(String message){
        super(403 , message);
    }

}
