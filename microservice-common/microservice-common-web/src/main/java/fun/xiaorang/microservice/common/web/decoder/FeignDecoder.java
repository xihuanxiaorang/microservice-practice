package fun.xiaorang.microservice.common.web.decoder;

import cn.hutool.http.HttpStatus;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import fun.xiaorang.microservice.common.base.model.Result;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @link https://zhuanlan.zhihu.com/p/545505705
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/05 02:55
 */
public class FeignDecoder implements Decoder {
    private final SpringDecoder decoder;

    public FeignDecoder(SpringDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        Method method = response.request().requestTemplate().methodMetadata().method();
        // 如果 FeignClient 接口中的方法返回值不是 Result 类型，而远程调用的服务接口返回的是 Result 类型，则需要对返回值进行转换
        boolean notTheSame = method.getReturnType() != Result.class;
        if (notTheSame) {
            Type newType = new ParameterizedType() {
                @Override
                public Type[] getActualTypeArguments() {
                    return new Type[]{type};
                }

                @Override
                public Type getRawType() {
                    return Result.class;
                }

                @Override
                public Type getOwnerType() {
                    return null;
                }
            };
            Result<?> result = (Result<?>) this.decoder.decode(response, newType);
            if (Result.isSuccess(result)) {
                return result.getData();
            } else {
                throw new DecodeException(HttpStatus.HTTP_INTERNAL_ERROR, result.getMsg(), response.request());
            }
        }
        return this.decoder.decode(response, type);
    }
}
