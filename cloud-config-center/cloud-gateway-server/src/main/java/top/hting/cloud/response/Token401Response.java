package top.hting.cloud.response;

import lombok.Data;

/**
 * token无效
 */
@Data
public class Token401Response extends BaseResponse {

    public Token401Response(String message){
        super(401, message);
    }

}
