package com.ygg.webapp.entity.bargain;

import java.util.Date;

public class HcbBargain {
    private Integer fakeId;

    private Date createDatetime;

    private Date updateDatetime;

    private String deleteAt;

    private String bargainerUuid;

    private String peopleBargain;

    private Double bargainAmount;

    private Integer isBargain;

    private String userUuid;
    
    private String status;
    
    private String currentPrice;
    
    private String isPay;
    
    private String activityUuid;
    
    private int activityid;

    @Override
	public String toString() {
		return "HcbBargain [fakeId=" + fakeId + ", createDatetime=" + createDatetime + ", updateDatetime="
				+ updateDatetime + ", deleteAt=" + deleteAt + ", bargainerUuid=" + bargainerUuid + ", peopleBargain="
				+ peopleBargain + ", bargainAmount=" + bargainAmount + ", isBargain=" + isBargain + ", userUuid="
				+ userUuid + ", status=" + status + ", currentPrice=" + currentPrice + ", isPay=" + isPay
				+ ", activityUuid=" + activityUuid + ", activityid=" + activityid + "]";
	}

	public int getActivityid() {
		return activityid;
	}

	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}

	public Integer getFakeId() {
        return fakeId;
    }

    public void setFakeId(Integer fakeId) {
        this.fakeId = fakeId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(String deleteAt) {
        this.deleteAt = deleteAt == null ? null : deleteAt.trim();
    }

    public String getBargainerUuid() {
        return bargainerUuid;
    }

    public void setBargainerUuid(String bargainerUuid) {
        this.bargainerUuid = bargainerUuid == null ? null : bargainerUuid.trim();
    }

    public String getPeopleBargain() {
        return peopleBargain;
    }

    public void setPeopleBargain(String peopleBargain) {
        this.peopleBargain = peopleBargain == null ? null : peopleBargain.trim();
    }

    public Double getBargainAmount() {
        return bargainAmount;
    }

    public void setBargainAmount(Double bargainAmount) {
        this.bargainAmount = bargainAmount;
    }

    public Integer getIsBargain() {
        return isBargain;
    }

    public void setIsBargain(Integer isBargain) {
        this.isBargain = isBargain;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public String getActivityUuid() {
		return activityUuid;
	}

	public void setActivityUuid(String activityUuid) {
		this.activityUuid = activityUuid;
	}
}
