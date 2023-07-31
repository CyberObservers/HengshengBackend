package tongji.product.server.impl;

import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tongji.product.api.RedemptionService;
import tongji.product.api.pojo.RedemptionDTO;
import tongji.product.server.mapper.RedemptionMapper;

import java.util.Date;
import java.util.List;

@CloudComponent
public class RedemptionServiceImpl implements RedemptionService {
    @Autowired
    private RedemptionMapper redemptionMapper;

    public String createRedemption(RedemptionDTO redemption) {
        RedemptionDTO existRedemption = redemptionMapper.getOneRedemption(
                redemption.getCerNumber(),
                redemption.getFundNumber(),
                redemption.getRedDate(),
                redemption.getRedCardNumber());
        if(null == existRedemption){
            redemptionMapper.createRedemption(redemption);
            return redemption.getCerNumber();
        }
        throw new IllegalArgumentException("已存在相同的赎回申请");
    }

    public String deleteRedemption(RedemptionDTO redemption) {
        RedemptionDTO existRedemption = redemptionMapper.getOneRedemption(
                redemption.getCerNumber(),
                redemption.getFundNumber(),
                redemption.getRedDate(),
                redemption.getRedCardNumber());
        if(null == existRedemption){
            throw new IllegalArgumentException("不存在相关赎回申请");
        }
        redemptionMapper.deleteRedemption(redemption);
        return redemption.getCerNumber();
    }

    public List<RedemptionDTO> getRedemption(String cerNumber) {
        Assert.hasLength(cerNumber, "缺少查询的证件号");
        return redemptionMapper.getRedemption(cerNumber);
    }

    public RedemptionDTO getOneRedemption(String cerNumber, String fundNumber, Date redDate, String redCardNumber) {
        Assert.hasLength(cerNumber, "缺少查询的证件号");
        Assert.hasLength(fundNumber, "缺少查询的基金代码");
        Assert.notNull(redDate,"缺少查询日期");
        Assert.hasLength(redCardNumber, "缺少查询的银行卡号");
        return redemptionMapper.getOneRedemption(cerNumber, fundNumber, redDate, redCardNumber);
    }
}
