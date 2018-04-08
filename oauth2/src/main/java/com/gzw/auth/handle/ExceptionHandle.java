package com.gzw.auth.handle;

import com.gzw.auth.domain.Result;
import com.gzw.auth.enums.ResultEnum;
import com.gzw.auth.exception.CommonException;
import com.gzw.auth.exception.ValidateCodeException;
import com.gzw.auth.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by qzj on 2018/2/25
 */
@ControllerAdvice
public class ExceptionHandle {
        private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

        @ExceptionHandler(value = Exception.class)
        @ResponseBody
        public Result handle(Exception e) {
            if (e instanceof CommonException) {
                CommonException commonException = (CommonException) e;
                return ResultUtil.error(commonException.getCode(), commonException.getMessage());
            } else if (e instanceof ValidateCodeException) {
                ValidateCodeException validateCodeException = (ValidateCodeException) e;
                return ResultUtil.error(validateCodeException.getCode(), validateCodeException.getMessage());
            } else {
                logger.error("【系统异常】{}", e);
                return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
            }
        }

}
