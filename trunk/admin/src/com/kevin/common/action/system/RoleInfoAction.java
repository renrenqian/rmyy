package com.kevin.common.action.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.common.pojo.system.PrivilegeInfo;
import com.kevin.common.pojo.system.RoleInfo;
import com.kevin.common.pojo.system.RolePrivilegeRef;
import com.kevin.common.service.system.IRoleInfoService;

import com.opensymphony.xwork2.Action;

@Component("roleInfoAction")
@Scope("prototype")
public class RoleInfoAction extends AbstractBaseAction {
    private List<PrivilegeInfo> piList;
    private RoleInfo ri;
    private List<RoleInfo> riList;
    private List<RolePrivilegeRef> refList;
    private IRoleInfoService roleInfoSerice;
    /**
     * @param roleInfoSerice the roleInfoSerice to set
     */
    @Autowired
    public void setRoleInfoSerice(IRoleInfoService roleInfoSerice) {
        this.roleInfoSerice = roleInfoSerice;
    }

    /**
     * @return the refList
     */
    public List<RolePrivilegeRef> getRefList() {
        return refList;
    }

    /**
     * @param refList the refList to set
     */
    public void setRefList(List<RolePrivilegeRef> refList) {
        this.refList = refList;
    }

    /**
     * @return the ri
     */
    public RoleInfo getRi() {
        return ri;
    }

    /**
     * @param ri the ri to set
     */
    public void setRi(RoleInfo ri) {
        this.ri = ri;
    }

    /**
     * @return the riList
     */
    public List<RoleInfo> getRiList() {
        return riList;
    }

    /**
     * @param riList the riList to set
     */
    public void setRiList(List<RoleInfo> riList) {
        this.riList = riList;
    }

    /**
     * @return the piList
     */
    public List<PrivilegeInfo> getPiList() {
        return piList;
    }

    /**
     * @param piList the piList to set
     */
    public void setPiList(List<PrivilegeInfo> piList) {
        this.piList = piList;
    }
    /**************************ACTIONS************************************/
    /*****************ROLE*************************/
    public String listRole(){
        try{
            riList = roleInfoSerice.listAll();
            setResultCode(1);
        }catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    public String addRole(){
        try {
            int result = roleInfoSerice.addRole(ri, refList);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    public String updateRole(){
        try {
            int result = roleInfoSerice.updateRole(ri, refList);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    
    public String deleteRole(){
        try {
            int result = roleInfoSerice.deleteRole(ri);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    public String bacthDeleteRole(){
        try {
            int result = roleInfoSerice.bacthDeleteRole(riList);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    /***************PRIVILEGE**********************/
    public String listPrivilege(){
        try {
            piList = roleInfoSerice.listPrivilege();
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
    public String findPrivilegeByRoleId(){
        try {
            piList = roleInfoSerice.findPrivilegeByRoleId(ri);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
}
