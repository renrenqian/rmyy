/**
 * 
 */
package com.kevin.common.action.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.SystemParameter;
import com.kevin.common.service.system.ISystemParameterService;

import com.opensymphony.xwork2.Action;


/**
 * @author wj
 * 
 */

@Controller("parameterAction")
@Scope("prototype")
public class ParameterAction extends AbstractBaseAction {

    private ISystemParameterService sysParameterService;
    private SystemParameter sysparameter;
    private List<SystemParameter> sysParameterList;

    /**
     * @return the sysParameterList
     */
    public final List<SystemParameter> getSysParameterList() {
        return sysParameterList;
    }
    /**
     * @param sysParameterList the sysParameterList to set
     */
    public final void setSysParameterList(List<SystemParameter> sysParameterList) {
        this.sysParameterList = sysParameterList;
    }
    /**
     * @param sysParameterService the sysParameterService to set
     */
    @Autowired
    public final void setSysParameterService(
            ISystemParameterService sysParameterService) {
        this.sysParameterService = sysParameterService;
    }
    public String listAllparameters(){
        try {
            sysParameterList = sysParameterService.listAll();
            setResultCode(1);
        } catch (CommonServiceException e) {
            setResultCode(-1);
            setMessage(e.getMessage());
        }
        return Action.SUCCESS;
    }
    public String updatParameter(){
        try {
            sysParameterService.update(sysparameter);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setResultCode(-1);
            setMessage(e.getMessage());
        }
        return Action.SUCCESS;
    }
     public String searchparametersById(){//获得用户基本信息
            try {
                sysparameter  = sysParameterService.search(sysparameter);
                setResultCode(1);
            } catch (CommonServiceException e) {
                setResultCode(-1);
                setMessage(e.getMessage());
            }
            return Action.SUCCESS;
        }
    

    /**
     * @return the sysparameter
     */
    public final SystemParameter getSysparameter() {
        return sysparameter;
    }

    /**
     * @param sysparameter the sysparameter to set
     */
    public final void setSysparameter(SystemParameter sysparameter) {
        this.sysparameter = sysparameter;
    }

}
