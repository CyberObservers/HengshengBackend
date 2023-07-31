package tongji.product.server.impl;

import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tongji.product.api.RiskTraceService;
import tongji.product.api.pojo.RiskTraceDTO;
import tongji.product.server.mapper.RiskTraceMapper;

import java.util.Date;

@CloudComponent
public class RiskTraceServiceImpl implements RiskTraceService {
    @Autowired
    private RiskTraceMapper riskTraceMapper;

    public String createRiskTrace(RiskTraceDTO riskTrace) {
        RiskTraceDTO existRiskTrace = riskTraceMapper.getRiskTrace(riskTrace.getCerNumber(), riskTrace.getRedDate());
        if(null == existRiskTrace){
            riskTraceMapper.createRiskTrace(riskTrace);
            return riskTrace.getCerNumber();
        }
        throw new IllegalArgumentException("已存在风险留痕");
    }

    public RiskTraceDTO getRiskTrace(String cerNumber, Date date) {
        Assert.hasLength(cerNumber, "缺少证件号");
        Assert.hasLength(date.toString(), "缺少日期");
        return riskTraceMapper.getRiskTrace(cerNumber, date);
    }
}
