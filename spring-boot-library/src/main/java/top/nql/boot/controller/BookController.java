package top.nql.boot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.nql.boot.dto.BookCreateDTO;
import top.nql.boot.dto.BookPageQuery;
import top.nql.boot.dto.BookUpdateDTO;
import top.nql.boot.dto.StockAdjustDTO;
import top.nql.boot.vo.BookVO;
import top.nql.boot.common.PageResult;
import top.nql.boot.service.BookService;

import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
//新增图书
    @PostMapping
    public BookVO create(@Valid @RequestBody BookCreateDTO dto) {
        return bookService.create(dto);
    }
//更新图书（标题、作者等信息）
    @PutMapping("/{id}")
    public BookVO update(@PathVariable Long id, @Valid @RequestBody BookUpdateDTO dto) {
        return bookService.update(id, dto);
    }
//库存调整（入库 / 出库）
    @PatchMapping("/{id}/stock/adjust")
    public BookVO adjustStock(@PathVariable Long id, @Valid @RequestBody StockAdjustDTO dto) {
        return bookService.adjustStock(id, dto);
    }
//按 ID 查询单本图书
    @GetMapping("/{id}")
    public BookVO getById(@PathVariable Long id) {
        return bookService.getById(id);
    }
//ISBN 唯一性检查
    @GetMapping("/exists/isbn/{isbn}")
    public Map<String, Boolean> checkIsbnUnique(@PathVariable String isbn) {
        return Map.of("exists", !bookService.checkIsbnUnique(isbn));
    }
//图书分页检索（带条件 / 排序）
    @GetMapping("/page")
    public PageResult<BookVO> pageQuery(BookPageQuery query) {
        return bookService.pageQuery(query);
    }
//逻辑删除图书
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
//逻辑恢复已删除图书
    @PutMapping("/{id}/restore")
    public BookVO restore(@PathVariable Long id) {
        return bookService.restore(id);
    }
}