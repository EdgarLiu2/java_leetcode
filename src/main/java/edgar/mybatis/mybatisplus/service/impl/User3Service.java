package edgar.mybatis.mybatisplus.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import edgar.mybatis.mybatisplus.mapper.User3Mapper;
import edgar.mybatis.mybatisplus.pojo.User3;
import edgar.mybatis.mybatisplus.service.IUser3Service;


@DS("master")
@Service
public class User3Service extends ServiceImpl<User3Mapper, User3> implements IUser3Service {

}
