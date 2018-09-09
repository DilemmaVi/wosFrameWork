package com.wos.exception;

import com.wos.pojo.WosReturnModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenyuwei
 * @date 2018/9/9
 */
@ControllerAdvice
public class WosExceptionHandler {

    private static Logger logger=LogManager.getLogger(WosExceptionHandler.class);

    public static final String WOS_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest reqest,
                               HttpServletResponse response, Exception e) throws Exception {

        logger.error("系统发生异常:",e);
        if (isAjax(reqest)) {
            return WosReturnModel.errorException(e.getMessage());
        } else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("exception", e);
            mav.addObject("url", reqest.getRequestURL());
            mav.setViewName(WOS_ERROR_VIEW);
            return mav;
        }
    }

    /**
     *
     * @Title: WosExceptionHandler.java
     * @Package com.wos.exception
     * @Description: 判断是否是ajax请求
     *
     * @author DilemmaV
     * @date 2018年9月9日
     */
    public static boolean isAjax(HttpServletRequest httpRequest){
        return  (httpRequest.getHeader("X-Requested-With") != null
                && "XMLHttpRequest"
                .equals( httpRequest.getHeader("X-Requested-With").toString()) );
    }
}
