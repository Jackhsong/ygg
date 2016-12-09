package com.ygg.webapp.service.bargain;

import java.util.List;

import com.ygg.webapp.entity.bargain.HcbBargain;
import com.ygg.webapp.entity.bargain.HcbParticipant;

public interface HcbBargainService {

  public int deleteByPrimaryKey(Integer fakeId);

  public  int insert(HcbBargain record);

  public  int insertSelective(HcbBargain record);

  public  HcbBargain selectByPrimaryKey(Integer fakeId);

  public  int updateByPrimaryKeySelective(HcbBargain record);

  public  int updateByPrimaryKey(HcbBargain record);
  
  public HcbBargain selectByBargainerUuid(String bargainerUuid); 
  
  public List<HcbBargain> listSelectByUserUuid(String userUuid);
  
  public Integer countTotalPayed();
  
  public Integer countTotalBargained(String originalPrice);
}
