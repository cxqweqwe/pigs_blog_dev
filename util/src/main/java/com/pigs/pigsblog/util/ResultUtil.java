package com.pigs.pigsblog.util;

import com.pigs.pigsblog.model.ResultFormat;

/**
 * @author PIGS-猪农·杨
 * @version 1.0
 * @date 2020/2/17 14:53
 * 统一返回工具类
 */
public class ResultUtil {



    public static ResultFormat success(Object object) {
        ResultFormat result =new ResultFormat();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static ResultFormat success(Integer code, String msg,Object object) {
        ResultFormat result =new ResultFormat();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public static ResultFormat success(Integer code, String msg) {
        ResultFormat result =new ResultFormat();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static ResultFormat success( String msg) {
        ResultFormat result =new ResultFormat();
        result.setCode(200);
        result.setMsg(msg);
        return result;
    }
    public static ResultFormat success( String msg,Object object) {
        ResultFormat result =new ResultFormat();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public static ResultFormat success() {
        ResultFormat result =new ResultFormat();
        result.setCode(200);
        result.setMsg("成功");
        return result;
    }

    public static ResultFormat error(Integer code, String msg) {
        ResultFormat result =new ResultFormat();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


}
