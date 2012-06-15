package com.kevin.common.action.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.PageBean;
import com.kevin.common.pojo.system.OperationLog;
import com.kevin.common.service.system.IOperationLogService;

import com.opensymphony.xwork2.Action;


@Component("operationLogAction")
@Scope("prototype")
    public class OperationLogAction extends AbstractBaseAction {
        private IOperationLogService operationLogService;
        private List<OperationLog> logList;
        private PageBean<OperationLog> page;
        private OperationLog log;
        
        
        public List<OperationLog> getLogList() {
            return logList;
        }
        @Autowired
        public void setOperationLogService(IOperationLogService operationLogService) {
            this.operationLogService = operationLogService;
        }
        public void setLogList(List<OperationLog> logList) {
            this.logList = logList;
        }
        public PageBean<OperationLog> getPage() {
            return page;
        }
        public void setPage(PageBean<OperationLog> page) {
            this.page = page;
        }
        public OperationLog getLog() {
            return log;
        }
        public void setLog(OperationLog log) {
            this.log = log;
        }
        
        public String listOperationLog() {
            try {
                if(null != log)
                    page.setCondition(log);
                page = operationLogService.list(page);
                setResultCode(1);
            } catch (CommonServiceException e) {
                setMessage(e.getMessage());
                setResultCode(-1);
            }
            return Action.SUCCESS;
        }

        
}
