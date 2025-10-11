package top.nql.boot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.datafaker.shaded.snakeyaml.constructor.DuplicateKeyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.nql.boot.dto.BookCreateDTO;
import top.nql.boot.dto.BookPageQuery;
import top.nql.boot.dto.BookUpdateDTO;
import top.nql.boot.dto.StockAdjustDTO;
import top.nql.boot.vo.BookVO;
import top.nql.boot.common.PageResult;
import top.nql.boot.converutil.BeanConvertUtil;
import top.nql.boot.entity.BookEntity;
import top.nql.boot.exception.BusinessException;
import top.nql.boot.mapper.BookMapper;
import top.nql.boot.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 图书服务实现类
 *
 * @author nqliu
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public BookVO create(BookCreateDTO dto) {
        if (!checkIsbnUnique(dto.getIsbn())) {
            throw new BusinessException("ISBN已存在");
        }
        BookEntity entity = new BookEntity();
        BeanUtils.copyProperties(dto, entity);
        try {
            bookMapper.insert(entity); // 可能触发唯一约束异常
        } catch (DuplicateKeyException e) {
            throw new BusinessException("ISBN已存在（并发冲突）");
        }
        return BeanConvertUtil.toVO(entity);
    }

    @Override
    public BookVO update(Long id, BookUpdateDTO dto) {
        // 1. 验证ID有效性（确保抛出明确异常）
        if (id == null || id <= 0) {
            throw new BusinessException("图书ID无效");
        }

        // 2. 获取实体并校验
        BookEntity entity = getValidBook(id);
        if (entity == null) {
            throw new BusinessException("图书不存在或已删除");
        }

        // 3. 安全复制属性（避免DTO空值覆盖原有数据）
        if (dto.getTitle() != null) {
            entity.setTitle(dto.getTitle().trim()); // 去除首尾空格
        }
        if (dto.getAuthor() != null) {
            entity.setAuthor(dto.getAuthor().trim());
        }
        if (dto.getCategory() != null) {
            entity.setCategory(dto.getCategory().trim());
        }

        // 4. 执行更新并验证结果
        int rows = bookMapper.updateById(entity);
        if (rows <= 0) {
            throw new BusinessException("更新失败，未找到可更新的图书或数据未变更");
        }

        // 5. 转换并返回VO
        BookVO vo = new BookVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    public BookVO adjustStock(Long id, StockAdjustDTO dto) {
        BookEntity entity = getValidBook(id);
        Integer amount = dto.getAmount();
        String type = dto.getType();
        if ("in".equals(type)) {
            entity.setStock(entity.getStock() + amount);
        } else if ("out".equals(type)) {
            if (entity.getStock() < amount) {
                throw new BusinessException("库存不足");
            }
            entity.setStock(entity.getStock() - amount);
        } else {
            throw new BusinessException("调整类型错误，应为in或out");
        }
        bookMapper.updateById(entity);
        BookVO vo = new BookVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    public BookVO getById(Long id) {
        BookEntity entity = getValidBook(id);
        BookVO vo = new BookVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    public boolean checkIsbnUnique(String isbn) {
        QueryWrapper<BookEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("isbn", isbn).eq("deleted", 0);
        return bookMapper.selectCount(wrapper) == 0;
    }

    @Override
    public PageResult<BookVO> pageQuery(BookPageQuery query) {
        Page<BookEntity> page = new Page<>(query.getPageNum(), query.getPageSize());
        QueryWrapper<BookEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted", 0);
        if (query.getTitle() != null) {
            wrapper.like("title", query.getTitle());
        }
        if (query.getCategory() != null) {
            wrapper.like("category", query.getCategory());
        }
        wrapper.orderByDesc("update_time");
        Page<BookEntity> entityPage = bookMapper.selectPage(page, wrapper);
        List<BookVO> voList = entityPage.getRecords().stream().map(entity -> {
            BookVO vo = new BookVO();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }).collect(Collectors.toList());
        PageResult<BookVO> pageResult = new PageResult<>();
        pageResult.setList(voList);
        pageResult.setTotal(entityPage.getTotal());
        pageResult.setPageNum(query.getPageNum());
        pageResult.setPageSize(query.getPageSize());
        pageResult.setPages(entityPage.getPages());
        return pageResult;
    }

    @Override
    public void delete(Long id) {
        BookEntity entity = getValidBook(id);
        bookMapper.deleteById(id);
    }

    @Override
    public BookVO restore(Long id) {
        // 使用 Wrapper 强制查询所有记录（忽略逻辑删除）
        QueryWrapper<BookEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.last("LIMIT 1"); // 确保只查一条

        BookEntity entity = bookMapper.selectOne(wrapper);

        if (entity == null) {
            throw new BusinessException("图书不存在");
        }
        if (entity.getDeleted() == 0) {
            throw new BusinessException("图书未被删除，无需恢复");
        }
        entity.setDeleted(0);
        bookMapper.updateById(entity);
        BookVO vo = new BookVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    private BookEntity getValidBook(Long id) {
        BookEntity entity = bookMapper.selectById(id);
        if (entity == null) {
            throw new BusinessException("图书不存在");
        }
        if (entity.getDeleted() == 1) {
            throw new BusinessException("图书已被逻辑删除");
        }
        return entity;
    }
}