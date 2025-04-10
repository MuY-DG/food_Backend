package com.muybaby.food.exception;

import com.muybaby.food.bean.Result;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.angus.mail.smtp.SMTPAddressFailedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity; // ResponseEntity 似乎没有用到，可以移除
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.method.HandlerMethod; // 不再需要 HandlerMethod

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 通用异常处理 - 捕获所有未被特定处理器捕获的 Exception
     * @param e 异常对象
     * @return 统一错误响应
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) { // 移除了 HandlerMethod 参数
        // 记录详细错误日志，包含堆栈跟踪
        log.error("系统发生未捕获异常: {}", e.getMessage(), e);
        // 不建议在返回给前端的消息中直接暴露 e.getMessage()，除非确认安全
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "服务器内部错误，请稍后重试");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result<Object> handleDuplicateKeyException(DuplicateKeyException e) {
        log.warn("唯一键冲突异常: {}", e.getMessage()); // 可以用 warn 级别，并记录消息即可
        return Result.error(HttpStatus.BAD_REQUEST, "数据已存在，请勿重复添加");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Object> handleJsonParseError(HttpMessageNotReadableException e) {
        log.warn("请求体解析失败: {}", e.getMessage());
        return Result.error(HttpStatus.BAD_REQUEST, "请求格式错误"); // 返回更通用的消息
    }

    /**
     * 运行时异常处理
     * 注意：Exception.class 处理器已经能覆盖 RuntimeException。
     * 如果需要对 RuntimeException 做特殊处理（不同于其他 Exception），则保留此方法。
     * 否则，可以考虑移除此方法，让通用的 Exception 处理器处理。
     * @param e 运行时异常
     * @return 统一错误响应
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<Object> handleRuntimeException(RuntimeException e) {
        log.error("发生运行时错误: {}", e.getMessage(), e);
        // 根据需要决定是否暴露 e.getMessage()
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "服务器运行时错误"); // 通常归为服务器内部错误
    }

    @ExceptionHandler(SMTPAddressFailedException.class)
    public Result<Object> handleSMTPAddressFailedException(SMTPAddressFailedException e) {
        log.error("邮件地址错误 (SMTPAddressFailedException): {}", e.getMessage());
        // 不建议在响应中重复异常类型，消息应面向用户
        return Result.error(HttpStatus.BAD_REQUEST, "提供的电子邮箱地址无效或不存在");
    }

    @ExceptionHandler(MessagingException.class)
    public Result<Object> handleMessagingException(MessagingException e) {
        log.error("邮件发送错误 (MessagingException): {}", e.getMessage(), e);
        // 提供通用、友好的错误信息
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "邮件发送失败，请稍后重试或联系管理员");
    }
}