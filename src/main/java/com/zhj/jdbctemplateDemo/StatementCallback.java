package com.zhj.jdbctemplateDemo;

import java.sql.Statement;

public interface StatementCallback {
    Object doWithStatement(Statement stmt);
}
