package top.hting.cloud.response.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一的返回类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestfulResponse<T> extends BaseResponse{

    private T data;

    public RestfulResponse success(T data){
        this.data = data;
        return this;
    }



}
