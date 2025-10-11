package top.nql.boot.service;

import top.nql.boot.common.PageResult;
import top.nql.boot.dto.BookCreateDTO;
import top.nql.boot.dto.BookPageQuery;
import top.nql.boot.dto.BookUpdateDTO;
import top.nql.boot.dto.StockAdjustDTO;
import top.nql.boot.vo.BookVO;


public interface BookService {
    BookVO create(BookCreateDTO dto);

    BookVO update(Long id, BookUpdateDTO dto);

    BookVO adjustStock(Long id, StockAdjustDTO dto);

    BookVO getById(Long id);

    boolean checkIsbnUnique(String isbn);

    PageResult<BookVO> pageQuery(BookPageQuery query);

    void delete(Long id);

    BookVO restore(Long id);
}
