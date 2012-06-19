/**
 * LeaderAction.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.action.member;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kevin.common.action.AbstractBaseAction;
import com.kevin.common.exception.CommonServiceException;
import com.kevin.group.pojo.member.LeaderInfo;
import com.kevin.group.service.member.ILeaderService;
import com.opensymphony.xwork2.Action;

/**
 * @author kevin
 * @since jdk1.6
 */
@Component("leaderAction")
@Scope("prototype")
public class LeaderAction extends AbstractBaseAction {

    private ILeaderService leaderService;
    private LeaderInfo leader;
    private List<LeaderInfo> leaderList;
    private List<Serializable> ides;

    /**
     * @return the leader
     */
    public final LeaderInfo getLeader() {
        return leader;
    }

    /**
     * @param leader
     *            the leader to set
     */
    public final void setLeader(LeaderInfo leader) {
        this.leader = leader;
    }

    /**
     * @return the leaderList
     */
    public final List<LeaderInfo> getLeaderList() {
        return leaderList;
    }

    /**
     * @param leaderList
     *            the leaderList to set
     */
    public final void setLeaderList(List<LeaderInfo> leaderList) {
        this.leaderList = leaderList;
    }

    /**
     * @return the ides
     */
    public final List<Serializable> getIdes() {
        return ides;
    }

    /**
     * @param ides
     *            the ides to set
     */
    public final void setIdes(List<Serializable> ides) {
        this.ides = ides;
    }

    /**
     * @param leaderService
     *            the leaderService to set
     */
    public final void setLeaderService(ILeaderService leaderService) {
        this.leaderService = leaderService;
    }

    public String addLeader() {// add new leader
        try {
            Serializable id = leaderService.save(leader);
            setResultCode((Integer) id);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String searchLeader() {// search leader
        try {
            this.leader = leaderService.search(leader);
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String listLeader() {// list all the leader
        try {
            leaderList = leaderService.listAll();
            setResultCode(1);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String updateLeader() {// update the leader info
        try {
            int result = leaderService.update(leader);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String deleteLeader() {// delete the leader
        try {
            int result = leaderService.delete(leader);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }

    public String batchDeleteLeader() {// bath delete the leader
        try {
            int result = leaderService.batchDelete(ides);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        }
        return Action.SUCCESS;
    }
}
