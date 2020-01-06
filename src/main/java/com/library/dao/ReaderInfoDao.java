package com.library.dao;

import com.github.pagehelper.PageHelper;
import com.library.bean.ReaderInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReaderInfoDao {

    private final static String NAMESPACE = "com.library.dao.ReaderInfoDao.";
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public ArrayList<ReaderInfo> getAllReaderInfo(int page,int size) {
        PageHelper.startPage(page, size);
        List<ReaderInfo> result = sqlSessionTemplate.selectList(NAMESPACE + "getAllReaderInfo");
        return (ArrayList<ReaderInfo>) result;
    }

    public ReaderInfo findReaderInfoByReaderId(final long reader_id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "findReaderInfoByReaderId", reader_id);
    }

    public int deleteReaderInfo(final long reader_id) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteReaderInfo", reader_id);
    }

    public int editReaderInfo(final ReaderInfo readerInfo) {
        System.out.println(readerInfo);
        return sqlSessionTemplate.update(NAMESPACE + "editReaderInfo", readerInfo);
    }

    public int editReaderCard(final ReaderInfo readerInfo) {
        System.out.println(readerInfo);
        return sqlSessionTemplate.update(NAMESPACE + "editReaderCard", readerInfo);
    }

    public final long addReaderInfo(final ReaderInfo readerInfo) {
        if (sqlSessionTemplate.insert(NAMESPACE + "addReaderInfo", readerInfo) > 0) {
            return sqlSessionTemplate.selectOne(NAMESPACE + "getReaderId", readerInfo);
        } else {
            return -1;
        }
    }
}
