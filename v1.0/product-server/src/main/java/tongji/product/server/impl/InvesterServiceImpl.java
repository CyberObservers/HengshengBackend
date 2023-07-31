package tongji.product.server.impl;


import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;
import tongji.product.api.InvesterService;
import tongji.product.api.pojo.InvesterDTO;
import tongji.product.server.mapper.InvesterMapper;

import java.util.ArrayList;
import java.util.List;

@CloudComponent
public class InvesterServiceImpl implements InvesterService {
    @Autowired
    private InvesterMapper investerMapper;

    public String createInvester(InvesterDTO invester){
        InvesterDTO existInvester =investerMapper.getInvester(invester.getCerNumber());
        if(null==existInvester){
            investerMapper.createInvester(invester);
            return invester.getCerNumber();
        } else if(!existInvester.getAvailable()){
            existInvester.setAvailable(true);
            investerMapper.updateInvester(invester);
            return invester.getCerNumber();
        }

        throw new IllegalArgumentException("已存在相同的证件号的投资者");
        //return existInvester.getCerNumber();
    }

    public  InvesterDTO getInvester(String cerNumber){
        return investerMapper.getInvester(cerNumber);
    }

    public List<InvesterDTO> getAllInvester() {
        return investerMapper.getAllInvester();
    }

    public List<InvesterDTO> searchInvesterViaName(String keyWord) {
        String key = '%' + keyWord + '%';
        return investerMapper.searchInvesterViaName(key);
    }

    public List<InvesterDTO> searchInvesterViaNumber(String keyWord) {
        String key = '%' + keyWord + '%';
        return investerMapper.searchInvesterViaNumber(key);
    }

    public String deleteInvester(String cerNumber){
        investerMapper.deleteInvester(cerNumber);
        return "删除成功";
    }

    public String updateInvester(InvesterDTO invester){
        InvesterDTO existInvester = investerMapper.getInvester(invester.getCerNumber());
        if(null == existInvester){
            return "不存在该用户";
        }
        else{
            investerMapper.updateInvester(invester);
            return "更新成功";
        }
    }

    public InvesterDTO getAvailableInvester(String cerNumber) {
        return investerMapper.getAvailableInvester(cerNumber);
    }
}
