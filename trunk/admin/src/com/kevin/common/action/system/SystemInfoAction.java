package com.kevin.common.action.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.SystemInfo;
import com.kevin.common.service.system.ISystemInfoService;

import com.opensymphony.xwork2.Action;
@Controller("sysinfoAction")
public class SystemInfoAction extends AbstractBaseAction {
    private SystemInfo sys;
    private ISystemInfoService sysService;

    @Autowired
    public void setSysService(ISystemInfoService sysService) {
        this.sysService = sysService;
    }

    public SystemInfo getSys() {
        return sys;
    }

    public void setSys(SystemInfo sys) {
        this.sys = sys;
    }

    public String findSystemInfo() {
        try {
            sys = sysService.getSystemInfo();
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
}
