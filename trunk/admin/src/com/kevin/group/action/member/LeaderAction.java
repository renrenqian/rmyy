/**
 * LeaderAction.java
 * kevin 2012-6-17
 * @version 0.1
 */
package com.kevin.group.action.member;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private File file;
    private String fileFileName;
    private String fileContentType;

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
     * @return the file
     */
    public final File getFile() {
        return file;
    }

    /**
     * @param file
     *            the file to set
     */
    public final void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the fileFileName
     */
    public final String getFileFileName() {
        return fileFileName;
    }

    /**
     * @param fileFileName
     *            the fileFileName to set
     */
    public final void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    /**
     * @return the fileContentType
     */
    public final String getFileContentType() {
        return fileContentType;
    }

    /**
     * @param fileContentType
     *            the fileContentType to set
     */
    public final void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    /**
     * @param leaderService
     *            the leaderService to set
     */
    @Autowired
    public final void setLeaderService(ILeaderService leaderService) {
        this.leaderService = leaderService;
    }

    public String addLeader() {// add new leader
        try {
            if (null != file) {
                // make the parent folder when each month
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM", Locale.ENGLISH);
                String monthlize = dateFormat.format(new Date());
                File storeFolder = new File("/upload/leaderHeader/" + monthlize);
                if (!storeFolder.exists())
                    storeFolder.mkdirs();
                File storeFile = new File(storeFolder, System.currentTimeMillis() + "_" + fileFileName);
                String storePath = storeFile.getAbsolutePath();
                leader.setLiPortrait(storePath.substring(storePath .indexOf("upload") - 1));
                // FileUtils.copyFile(file, storeFile);
                // storeFile.createNewFile();
                FileUtils.moveFile(file, storeFile);
            }
            Serializable id = leaderService.save(leader);
            setResultCode((Integer) id);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        } catch (IOException e) {
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
            if (null != file) {
                // make the parent folder when each month
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM", Locale.ENGLISH);
                String monthlize = dateFormat.format(new Date());
                File storeFolder = new File("/upload/leaderHeader/" + monthlize);
                if (!storeFolder.exists())
                    storeFolder.mkdirs();
                File storeFile = new File(storeFolder, System.currentTimeMillis() + "_" + fileFileName);
                String storePath = storeFile.getAbsolutePath();
                leader.setLiPortrait(storePath.substring(storePath .indexOf("upload") - 1));
                // FileUtils.copyFile(file, storeFile);
                // storeFile.createNewFile();
                FileUtils.moveFile(file, storeFile);
            }
            int result = leaderService.update(leader);
            setResultCode(result);
        } catch (CommonServiceException e) {
            setMessage(e.getMessage());
            setResultCode(-1);
        } catch (IOException e) {
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
