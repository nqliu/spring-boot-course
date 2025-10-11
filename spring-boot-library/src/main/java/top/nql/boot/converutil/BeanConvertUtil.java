package top.nql.boot.converutil;

import org.springframework.beans.BeanUtils;
import top.nql.boot.dto.BookCreateDTO;
import top.nql.boot.vo.BookVO;
import top.nql.boot.entity.BookEntity;

public class BeanConvertUtil {
    // Entity转VO
    public static BookVO toVO(BookEntity entity) {
        if (entity == null) return null;
        BookVO vo = new BookVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    // CreateDTO转Entity
    public static BookEntity toEntity(BookCreateDTO dto) {
        BookEntity entity = new BookEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
