package top.nql.mapper; // 包路径必须是 top.yxq.mapper，与@MapperScan对应

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.nql.entity.UserAccount;

@Mapper
// 继承 BaseMapper，泛型指定实体类 UserAccount（必须与数据库表映射）
public interface UserAccountMapper extends BaseMapper<UserAccount> {
    // 无需额外定义方法，BaseMapper已提供selectById、selectCount等CRUD方法
}