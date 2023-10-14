package com.yczuoxin.myservice.config;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Statement;

/**
 * 打印 sql 执行的时间插件
 */
@Intercepts(
        {@Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})}
)
@Component
public class SqlExecuteTimePrintMybatisPlugin implements Interceptor {

    protected Logger logger = LoggerFactory.getLogger(SqlExecuteTimePrintMybatisPlugin.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        // 此处简单处理一下，只打印不带参数的 sql，目的是演示自定义插件
        String sql = boundSql.getSql();
        long start = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            logger.info("sql -> {}, takes time -> {}", sql, System.currentTimeMillis() - start);
        }
    }
}
