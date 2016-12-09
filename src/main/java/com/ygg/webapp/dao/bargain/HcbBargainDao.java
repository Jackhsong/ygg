package com.ygg.webapp.dao.bargain;

import java.util.List;
import com.ygg.webapp.entity.bargain.HcbBargain;;
    
public interface HcbBargainDao {
	
    int deleteByPrimaryKey(Integer fakeId);

    int insert(HcbBargain record);

    int insertSelective(HcbBargain record);

    HcbBargain selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(HcbBargain record);

    int updateByPrimaryKey(HcbBargain record);
    
    HcbBargain selectByBargainerUuid(String bargainerUuid); 
    
    List<HcbBargain> listSelectByUserUuid(String userUuid);
    
    Integer countTotalPayed();
    
    Integer countTotalBargained(String originalPrice);
}
