package com.example.billoa.sys.service.impl;

import com.example.billoa.sys.entity.Book;
import com.example.billoa.sys.mapper.BookMapper;
import com.example.billoa.sys.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanyi
 * @since 2023-03-14
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
