package top.nql.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.nql.boot.entity.BookEntity;

@Mapper
public interface BookMapper extends BaseMapper<BookEntity> {
}
