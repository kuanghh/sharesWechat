package com.khh.web.vo;

/**
 * Created by 951087952@qq.com on 2018/4/1.
 */
public interface SharesInterface {

     Integer getOpenPrice();

     void setOpenPrice(Integer openPrice);

     Integer getClosePrice() ;

     void setClosePrice(Integer closePrice);

     Integer getCeillingPrice();

     void setCeillingPrice(Integer ceillingPrice);

     Integer getFloorPrice();

     void setFloorPrice(Integer floorPrice);

     Integer getRiseAndFallRange();

     void setRiseAndFallRange(Integer riseAndFallRange) ;

     Integer getRiseAndFallQuota();

     void setRiseAndFallQuota(Integer riseAndFallQuota);

     Integer getVolume() ;
     void setVolume(Integer volume) ;

     Integer getTurnVolume();

     void setTurnVolume(Integer turnVolume);

     Integer getTurnoverRate();

     void setTurnoverRate(Integer turnoverRate) ;

     Integer getAmplitude() ;

     void setAmplitude(Integer amplitude) ;

     Integer getpERatio() ;

     void setpERatio(Integer pERatio);
}
